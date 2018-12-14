//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.CssVersion;
import org.w3c.css.util.InvalidParamException;

import java.util.HashMap;

import static org.w3c.css.values.CssOperator.COMMA;
import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @version $Revision$
 */
public class CssColor extends CssValue {

    public static final int type = CssTypes.CSS_COLOR;

    public final int getType() {
        return type;
    }

    Object color = null;
    // FIXME TODO, replace with one color type + one type for converted color for comparison
    RGB rgb = null;
    RGBA rgba = null;
    HSL hsl = null;
    HWB hwb = null;
    LAB lab = null;
    LCH lch = null;

    /**
     * Create a new CssColor.
     */
    public CssColor() {
    }

    /**
     * Create a new CssColor with a color name.
     *
     * @param s The name color.
     * @throws InvalidParamException the color is incorrect
     */
    public CssColor(ApplContext ac, String s) throws InvalidParamException {
        //	setIdentColor(s.toLowerCase(), ac);
        setIdentColor(s, ac);
    }

    /**
     * Set the value from a defined color RBG.
     *
     * @param s the string representation of the color.
     * @throws InvalidParamException the color is incorrect.
     */
    public void set(String s, ApplContext ac) throws InvalidParamException {
        if (s.charAt(0) == '#') {
            setShortRGBColor(s.toLowerCase(), ac);
        } else {
            setIdentColor(s, ac);
        }
    }

    /**
     * Return the internal value.
     */
    public Object get() {
        if (color != null) {
            return color;
        } else {
            return rgb;
        }
    }


    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (color != null) {
            return color.toString();
        } else if (rgb != null) {
            return rgb.toString();
        } else if (rgba != null) {
            return rgba.toString();
        } else if (hsl != null) {
            return hsl.toString();
        } else if (hwb != null) {
            return hwb.toString();
        } else if (lab != null) {
            return lab.toString();
        } else if (lch != null) {
            return lch.toString();
        }
        return "*invalid*";
    }


    public void setRGBColor(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        boolean isCss3 = (ac.getCssVersion().compareTo(CssVersion.CSS3) >= 0);
        if (!isCss3) {
            setLegacyRGBColor(exp, ac);
        } else {
            setModernRGBColor(exp, ac);
        }
    }

    /**
     * Parse a RGB color.
     * format rgb(<num>%?, <num>%?, <num>%?)
     */
    public void setLegacyRGBColor(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        CssValue val = exp.getValue();
        char op = exp.getOperator();
        color = null;
        rgb = new RGB();

        if (val == null || op != COMMA) {
            throw new InvalidParamException("invalid-color", ac);
        }

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                rgb.setPercent(false);
                rgb.setRed(ac, val);
                break;
            case CssTypes.CSS_PERCENTAGE:
                rgb.setPercent(true);
                rgb.setRed(ac, val);
                break;
            default:
                throw new InvalidParamException("rgb", val, ac);
        }

        exp.next();
        val = exp.getValue();
        op = exp.getOperator();

        if (val == null || op != COMMA) {
            throw new InvalidParamException("invalid-color", ac);
        }

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                if (rgb.isPercent()) {
                    throw new InvalidParamException("percent", val, ac);
                }
                rgb.setGreen(ac, val);
                break;
            case CssTypes.CSS_PERCENTAGE:
                if (!rgb.isPercent()) {
                    throw new InvalidParamException("integer", val, ac);
                }
                rgb.setGreen(ac, val);
                break;
            default:
                throw new InvalidParamException("rgb", val, ac);
        }

        exp.next();
        val = exp.getValue();
        op = exp.getOperator();

        if (val == null) {
            throw new InvalidParamException("invalid-color", ac);
        }

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                if (rgb.isPercent()) {
                    throw new InvalidParamException("percent", val, ac);
                }
                rgb.setBlue(ac, val);
                break;
            case CssTypes.CSS_PERCENTAGE:
                if (!rgb.isPercent()) {
                    throw new InvalidParamException("integer", val, ac);
                }
                rgb.setBlue(ac, val);
                break;
            default:
                throw new InvalidParamException("rgb", val, ac);
        }

        exp.next();
        if (exp.getValue() != null) {
            throw new InvalidParamException("rgb", exp.getValue(), ac);
        }
    }

    /**
     * Parse a RGB color.
     * format rgb( <percentage>{3} [ / <alpha-value> ]? ) |
     * rgb( <number>{3} [ / <alpha-value> ]? )
     */
    public void setModernRGBColor(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        CssValue val = exp.getValue();
        char op = exp.getOperator();
        color = null;
        rgba = new RGBA("rgb");
        boolean separator_space = (op == SPACE);

        if (val == null || (!separator_space && (op != COMMA))) {
            throw new InvalidParamException("invalid-color", ac);
        }

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                rgba.setPercent(false);
                rgba.setRed(ac, val);
                break;
            case CssTypes.CSS_PERCENTAGE:
                rgba.setPercent(true);
                rgba.setRed(ac, val);
                break;
            default:
                throw new InvalidParamException("rgb", val, ac);
        }

        exp.next();
        val = exp.getValue();
        op = exp.getOperator();

        if (val == null || (separator_space && (op != SPACE)) ||
                (!separator_space && (op != COMMA))) {
            throw new InvalidParamException("invalid-color", ac);
        }

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                if (rgba.isPercent()) {
                    throw new InvalidParamException("percent", val, ac);
                }
                rgba.setGreen(ac, val);
                break;
            case CssTypes.CSS_PERCENTAGE:
                if (!rgba.isPercent()) {
                    throw new InvalidParamException("integer", val, ac);
                }
                rgba.setGreen(ac, val);
                break;
            default:
                throw new InvalidParamException("rgb", val, ac);
        }

        exp.next();
        val = exp.getValue();
        op = exp.getOperator();

        if (val == null) {
            throw new InvalidParamException("invalid-color", ac);
        }

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                if (rgba.isPercent()) {
                    throw new InvalidParamException("percent", val, ac);
                }
                rgba.setBlue(ac, val);
                break;
            case CssTypes.CSS_PERCENTAGE:
                if (!rgba.isPercent()) {
                    throw new InvalidParamException("integer", val, ac);
                }
                rgba.setBlue(ac, val);
                break;
            default:
                throw new InvalidParamException("rgb", val, ac);
        }
        exp.next();

        // check for optional alpha channel
        if (!exp.end()) {
            // care for old syntax
            if (op == COMMA && !separator_space) {
                val = exp.getValue();
                switch (val.getType()) {
                    case CssTypes.CSS_NUMBER:
                    case CssTypes.CSS_PERCENTAGE:
                        rgba.setAlpha(ac, val);
                        break;
                    default:
                        throw new InvalidParamException("rgb", val, ac);
                }
            } else {
                // otherwise modern syntax
                if (op != SPACE) {
                    throw new InvalidParamException("invalid-color", ac);
                }
                // now we need an alpha.
                val = exp.getValue();
                op = exp.getOperator();

                if (val.getType() != CssTypes.CSS_SWITCH) {
                    throw new InvalidParamException("rgb", val, ac);
                }
                if (op != SPACE) {
                    throw new InvalidParamException("invalid-color", ac);
                }
                exp.next();
                // now we get the alpha value
                if (exp.end()) {
                    throw new InvalidParamException("rgb", exp.getValue(), ac);
                }
                val = exp.getValue();
                switch (val.getType()) {
                    case CssTypes.CSS_NUMBER:
                    case CssTypes.CSS_PERCENTAGE:
                        rgba.setAlpha(ac, val);
                        break;
                    default:
                        throw new InvalidParamException("rgb", val, ac);
                }
            }
            exp.next();

            if (!exp.end()) {
                throw new InvalidParamException("rgb", exp.getValue(), ac);
            }
        }
    }

    /**
     * Parse a HSL color.
     * format hsl( <percentage>{3} [ / <alpha-value> ]? ) |
     * hsl( <number>{3} [ / <alpha-value> ]? )
     */
    public void setHSLColor(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        // HSL defined in CSS3 and onward
        if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("hsl(").append(exp.toStringFromStart()).append(')');
            throw new InvalidParamException("notversion", sb.toString(),
                    ac.getCssVersionString(), ac);
        }

        CssValue val = exp.getValue();
        char op = exp.getOperator();
        color = null;
        hsl = new HSL();
        boolean separator_space = (op == SPACE);

        if (val == null || (!separator_space && (op != COMMA))) {
            throw new InvalidParamException("invalid-color", ac);
        }

        // H
        switch (val.getType()) {
            case CssTypes.CSS_ANGLE:
            case CssTypes.CSS_NUMBER:
                hsl.setHue(ac, val);
                break;
            default:
                throw new InvalidParamException("rgb", val, ac); // FIXME hsl
        }

        exp.next();
        val = exp.getValue();
        op = exp.getOperator();

        if (val == null || (separator_space && (op != SPACE)) ||
                (!separator_space && (op != COMMA))) {
            throw new InvalidParamException("invalid-color", ac);
        }

        // S
        if (val.getType() == CssTypes.CSS_PERCENTAGE) {
            hsl.setSaturation(ac, val);
        } else {
            exp.starts();
            throw new InvalidParamException("rgb", val, ac); // FIXME hsl
        }

        exp.next();
        val = exp.getValue();
        op = exp.getOperator();

        if (val == null) {
            throw new InvalidParamException("invalid-color", ac);
        }

        // L

        if (val.getType() == CssTypes.CSS_PERCENTAGE) {
            hsl.setLightness(ac, val);
        } else {
            exp.starts();
            throw new InvalidParamException("rgb", val, ac); // FIXME hsl
        }

        exp.next();

        // check for optional alpha channel
        if (!exp.end()) {
            // care for old syntax
            if (op == COMMA && !separator_space) {
                val = exp.getValue();
                switch (val.getType()) {
                    case CssTypes.CSS_NUMBER:
                    case CssTypes.CSS_PERCENTAGE:
                        hsl.setAlpha(ac, val);
                        break;
                    default:
                        throw new InvalidParamException("rgb", val, ac);
                }
            } else {
                // otherwise modern syntax
                if (op != SPACE) {
                    throw new InvalidParamException("invalid-color", ac);
                }
                // now we need an alpha.
                val = exp.getValue();
                op = exp.getOperator();

                if (val.getType() != CssTypes.CSS_SWITCH) {
                    throw new InvalidParamException("rgb", val, ac);
                }
                if (op != SPACE) {
                    throw new InvalidParamException("invalid-color", ac);
                }
                exp.next();
                // now we get the alpha value
                if (exp.end()) {
                    throw new InvalidParamException("rgb", exp.getValue(), ac);
                }
                val = exp.getValue();
                switch (val.getType()) {
                    case CssTypes.CSS_NUMBER:
                    case CssTypes.CSS_PERCENTAGE:
                        hsl.setAlpha(ac, val);
                        break;
                    default:
                        throw new InvalidParamException("rgb", val, ac);
                }
            }
            exp.next();

            if (!exp.end()) {
                throw new InvalidParamException("rgb", exp.getValue(), ac);
            }
        }
    }

    /**
     * Parse a RGB color.
     * format #(3-6)<hexnum>
     */
    public void setShortRGBColor(String s, ApplContext ac)
            throws InvalidParamException {
        int r;
        int g;
        int b;
        int v;
        int idx;
        boolean islong;

        v = s.length();
        islong = (v == 7);
        if (v != 4 && !islong) {
            throw new InvalidParamException("rgb", s, ac);
        }
        idx = 1;
        r = Character.digit(s.charAt(idx++), 16);
        if (r < 0) {
            throw new InvalidParamException("rgb", s, ac);
        }
        if (islong) {
            v = Character.digit(s.charAt(idx++), 16);
            if (v < 0) {
                throw new InvalidParamException("rgb", s, ac);
            }
            r = (r << 4) + v;
        } else {
            r |= (r << 4);
        }
        g = Character.digit(s.charAt(idx++), 16);
        if (g < 0) {
            throw new InvalidParamException("rgb", s, ac);
        }
        if (islong) {
            v = Character.digit(s.charAt(idx++), 16);
            if (v < 0) {
                throw new InvalidParamException("rgb", s, ac);
            }
            g = (g << 4) + v;
        } else {
            g |= (g << 4);
        }
        b = Character.digit(s.charAt(idx), 16);
        if (b < 0) {
            throw new InvalidParamException("rgb", s, ac);
        }
        if (islong) {
            v = Character.digit(s.charAt(++idx), 16);
            if (v < 0) {
                throw new InvalidParamException("rgb", s, ac);
            }
            b = (b << 4) + v;
        } else {
            b |= (b << 4);
        }


        color = null;
        rgb = new RGB(r, g, b);
        // we force the specific display of it
        rgb.setRepresentationString(s);
    }

    protected boolean computeIdentColor(HashMap<String, Object> definitions,
                                        String s) {
        Object obj = definitions.get(s);
        if (obj != null) {
            if (obj instanceof RGB) {
                color = s;
                rgb = (RGB) obj;
            } else if (obj instanceof RGBA) {
                color = s;
                rgba = (RGBA) obj;
            } else if (obj instanceof String) {
                color = (String) obj;
            }
            return true;
        }
        return false;
    }

    /**
     * Parse an ident color.
     */
    protected void setIdentColor(String s, ApplContext ac)
            throws InvalidParamException {
        String lower_s = s.toLowerCase();
        switch (ac.getCssVersion()) {
            case CSS1:
                rgb = CssColorCSS1.getRGB(lower_s);
                if (rgb == null) {
                    throw new InvalidParamException("value", s, "color", ac);
                }
                color = lower_s;
                break;
            case CSS2:
                rgb = CssColorCSS2.getRGB(lower_s);
                if (rgb == null) {
                    color = CssColorCSS2.getSystem(lower_s);
                    if (color == null) {
                        throw new InvalidParamException("value", s, "color", ac);
                    }
                } else {
                    color = lower_s;
                }
                break;
            case CSS21:
                rgb = CssColorCSS21.getRGB(lower_s);
                if (rgb == null) {
                    color = CssColorCSS21.getSystem(lower_s);
                    if (color == null) {
                        throw new InvalidParamException("value", s, "color", ac);
                    }
                } else {
                    color = lower_s;
                }
                break;
            case CSS3:
            case CSS_2015:
            case CSS:
                // test RGB colors, RGBA colors (transparent), deprecated system colors
                rgb = CssColorCSS3.getRGB(lower_s);
                if (rgb != null) {
                    color = lower_s;
                    break;
                }
                rgba = CssColorCSS3.getRGBA(lower_s);
                if (rgba != null) {
                    color = lower_s;
                    break;
                }
                color = CssColorCSS3.getSystem(lower_s);
                if (color != null) {
                    ac.getFrame().addWarning("deprecated", s);
                    break;
                }
                color = CssColorCSS3.getIdentColor(lower_s);
                if (color != null) {
                    break;
                }

                // inherit or current color will be handled in the property def
                throw new InvalidParamException("value", s, "color", ac);
            default:
                throw new InvalidParamException("value", s, "color", ac);
        }
    }

    /**
     * Compares two values for equality.
     *
     * @param cssColor The other value.
     */
    public boolean equals(Object cssColor) {
        if (!(cssColor instanceof CssColor)) {
            return false;
        }
        CssColor otherColor = (CssColor) cssColor;
        // FIXME we can have rgba(a,b,c,1) == rgb(a,b,c)
        if ((color != null) && (otherColor.color != null)) {
            return color.equals(otherColor.color);
        } else if ((rgb != null) && (otherColor.rgb != null)) {
            return rgb.equals(otherColor.rgb);
        } else if ((rgba != null) && (otherColor.rgba != null)) {
            return rgba.equals(otherColor.rgba);
        } else if ((hsl != null) && (otherColor.hsl != null)) {
            return hsl.equals(otherColor.hsl);
        }
        return false;
    }

    public void setATSCRGBAColor(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        rgba = new RGBA("atsc-rgba");
        __setRGBAColor(rgba, exp, ac);

    }

    public void setRGBAColor(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        // RGBA defined in CSS3 and onward
        if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("rgba(").append(exp.toStringFromStart()).append(')');
            throw new InvalidParamException("notversion", sb.toString(),
                    ac.getCssVersionString(), ac);
        }
        setModernRGBColor(exp, ac);
    }

    // use only for atsc profile, superseded by setModernRGBColor
    private void __setRGBAColor(RGBA rgba, CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        CssValue val;
        char op;
        color = null;

        val = exp.getValue();
        op = exp.getOperator();
        if (val == null || op != COMMA) {
            throw new InvalidParamException("invalid-color", ac);
        }

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                rgba.setRed(ac, val);
                rgba.setPercent(false);
                break;
            case CssTypes.CSS_PERCENTAGE:
                rgba.setRed(ac, val);
                rgba.setPercent(true);
                break;
            default:
                throw new InvalidParamException("rgb", val, ac); // FIXME rgba
        }
        exp.next();
        val = exp.getValue();
        op = exp.getOperator();
        if (val == null || op != COMMA) {
            throw new InvalidParamException("invalid-color", ac);
        }
        // green
        // and validate against the "percentageness"
        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                if (rgba.isPercent()) {
                    exp.starts();
                    throw new InvalidParamException("percent", val, ac);
                }
                rgba.setGreen(ac, val);
                break;
            case CssTypes.CSS_PERCENTAGE:
                if (!rgba.isPercent()) {
                    exp.starts();
                    throw new InvalidParamException("integer", val, ac);
                }
                rgba.setGreen(ac, val);
                break;
            default:
                exp.starts();
                throw new InvalidParamException("rgb", val, ac); // FIXME rgba
        }
        exp.next();
        val = exp.getValue();
        op = exp.getOperator();
        if (val == null || op != COMMA) {
            exp.starts();
            throw new InvalidParamException("invalid-color", ac);
        }
        // blue
        // and validate against the "percentageness"
        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                if (rgba.isPercent()) {
                    exp.starts();
                    throw new InvalidParamException("percent", val, ac);
                }
                rgba.setBlue(ac, val);
                break;
            case CssTypes.CSS_PERCENTAGE:
                if (!rgba.isPercent()) {
                    exp.starts();
                    throw new InvalidParamException("integer", val, ac);
                }
                rgba.setBlue(ac, val);
                break;
            default:
                exp.starts();
                throw new InvalidParamException("rgb", val, ac); // FIXME rgba
        }
        exp.next();
        val = exp.getValue();
        if (val == null) {
            exp.starts();
            throw new InvalidParamException("invalid-color", ac);
        }
        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                // starting with CSS Color 4
            case CssTypes.CSS_PERCENTAGE:
                rgba.setAlpha(ac, val);
                break;
            default:
                exp.starts();
                throw new InvalidParamException("rgb", val, ac); // FIXME rgba
        }
        exp.next();
        if (exp.getValue() != null) {
            exp.starts();
            throw new InvalidParamException("invalid-color", ac);
        }
    }

    public void setHWBColor(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        // HWB defined in CSSColor Level 4 and onward, currently used in the CSS level
        if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("hwb(").append(exp.toStringFromStart()).append(')');
            throw new InvalidParamException("notversion", sb.toString(),
                    ac.getCssVersionString(), ac);
        }

        color = null;
        hwb = new HWB();

        CssValue val = exp.getValue();
        char op = exp.getOperator();
        // H
        if (val == null || op != SPACE) {
            throw new InvalidParamException("invalid-color", ac);
        }
        switch (val.getType()) {
            case CssTypes.CSS_ANGLE:
            case CssTypes.CSS_NUMBER:
                hwb.setHue(ac, val);
                break;
            default:
                throw new InvalidParamException("rgb", val, ac); // FIXME hwb
        }

        // W
        exp.next();
        val = exp.getValue();
        op = exp.getOperator();
        if (val == null || op != SPACE) {
            exp.starts();
            throw new InvalidParamException("invalid-color", ac);
        }
        if (val.getType() == CssTypes.CSS_PERCENTAGE) {
            hwb.setWhiteness(ac, val);
        } else {
            exp.starts();
            throw new InvalidParamException("rgb", val, ac); // FIXME hwb
        }

        // B
        exp.next();
        val = exp.getValue();
        op = exp.getOperator();
        if (val == null || (op != SPACE && exp.getRemainingCount() > 1)) {
            exp.starts();
            throw new InvalidParamException("invalid-color", ac);
        }
        if (val.getType() == CssTypes.CSS_PERCENTAGE) {
            hwb.setBlackness(ac, val);
        } else {
            exp.starts();
            throw new InvalidParamException("rgb", val, ac); // FIXME hwb
        }
        hwb.normalize();

        // A
        exp.next();
        val = exp.getValue();
        if (val != null) {
            switch (val.getType()) {
                case CssTypes.CSS_NUMBER:
                case CssTypes.CSS_PERCENTAGE:
                    hwb.setAlpha(ac, val);
                    break;
                default:
                    exp.starts();
                    throw new InvalidParamException("rgb", val, ac); // FIXME hsl
            }
        }
        // extra values?
        exp.next();
        if (exp.getValue() != null) {
            exp.starts();
            throw new InvalidParamException("rgb", exp.getValue(), ac);
        }
    }


    public void setLABColor(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        // HWB defined in CSSColor Level 4 and onward, currently used in the CSS level
        if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("lab(").append(exp.toStringFromStart()).append(')');
            throw new InvalidParamException("notversion", sb.toString(),
                    ac.getCssVersionString(), ac);
        }

        color = null;
        lab = new LAB();
        CssValue val = exp.getValue();
        char op = exp.getOperator();
        // L
        if (val == null || op != SPACE) {
            throw new InvalidParamException("invalid-color", ac);
        }
        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                lab.setL(ac, val);
                break;
            default:
                throw new InvalidParamException("rgb", val, ac); // FIXME lab
        }

        // A
        exp.next();
        val = exp.getValue();
        op = exp.getOperator();
        if (val == null || op != SPACE) {
            exp.starts();
            throw new InvalidParamException("invalid-color", ac);
        }
        if (val.getType() == CssTypes.CSS_NUMBER) {
            lab.setA(ac, val);
        } else {
            exp.starts();
            throw new InvalidParamException("rgb", val, ac); // FIXME lab
        }

        // B
        exp.next();
        val = exp.getValue();
        op = exp.getOperator();
        if (val == null) {
            exp.starts();
            throw new InvalidParamException("invalid-color", ac);
        }
        if (val.getType() == CssTypes.CSS_NUMBER) {
            lab.setB(ac, val);
        } else {
            exp.starts();
            throw new InvalidParamException("rgb", val, ac); // FIXME lab
        }

        exp.next();
        if (!exp.end()) {
            if (op != COMMA) {
                throw new InvalidParamException("operator", exp.toStringFromStart(), ac);
            }
            // Alpha
            val = exp.getValue();
            if (val == null) {
                throw new InvalidParamException("invalid-color", exp.toStringFromStart(), ac);
            }
            switch (val.getType()) {
                case CssTypes.CSS_NUMBER:
                case CssTypes.CSS_PERCENTAGE:
                    lab.setAlpha(ac, val);
                    break;
                default:
                    exp.starts();
                    throw new InvalidParamException("rgb", val, ac); // FIXME lab
            }
            exp.next();
        }
        // extra values?
        if (!exp.end()) {
            exp.starts();
            throw new InvalidParamException("rgb", exp.toStringFromStart(), ac);
        }
    }


    public void setLCHColor(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        // HWB defined in CSSColor Level 4 and onward, currently used in the CSS level
        if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("lch(").append(exp.toStringFromStart()).append(')');
            throw new InvalidParamException("notversion", sb.toString(),
                    ac.getCssVersionString(), ac);
        }

        color = null;
        lch = new LCH();
        CssValue val = exp.getValue();
        char op = exp.getOperator();
        // L
        if (val == null || op != SPACE) {
            throw new InvalidParamException("invalid-color", ac);
        }
        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                lch.setL(ac, val);
                break;
            default:
                throw new InvalidParamException("rgb", val, ac); // FIXME lch
        }

        // A
        exp.next();
        val = exp.getValue();
        op = exp.getOperator();
        if (val == null || op != SPACE) {
            exp.starts();
            throw new InvalidParamException("invalid-color", ac);
        }
        if (val.getType() == CssTypes.CSS_NUMBER) {
            lch.setC(ac, val);
        } else {
            exp.starts();
            throw new InvalidParamException("rgb", val, ac); // FIXME lch
        }

        // B
        exp.next();
        val = exp.getValue();
        op = exp.getOperator();
        if (val == null) {
            throw new InvalidParamException("invalid-color", exp.toStringFromStart(), ac);
        }
        if (val.getType() == CssTypes.CSS_NUMBER) {
            lch.setH(ac, val);
        } else {
            exp.starts();
            throw new InvalidParamException("rgb", val, ac); // FIXME lch
        }

        exp.next();
        if (!exp.end()) {
            if (op != COMMA) {
                throw new InvalidParamException("operator", exp.toStringFromStart(), ac);
            }
            // Alpha
            val = exp.getValue();
            if (val == null) {
                throw new InvalidParamException("invalid-color", exp.toStringFromStart(), ac);
            }
            switch (val.getType()) {
                case CssTypes.CSS_NUMBER:
                case CssTypes.CSS_PERCENTAGE:
                    lch.setAlpha(ac, val);
                    break;
                default:
                    exp.starts();
                    throw new InvalidParamException("rgb", val, ac); // FIXME lch
            }
            exp.next();
        }
        // extra values?
        if (!exp.end()) {
            exp.starts();
            throw new InvalidParamException("rgb", exp.toStringFromStart(), ac);
        }
    }

}

