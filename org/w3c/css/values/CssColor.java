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
import org.w3c.css.util.Util;

import java.util.HashMap;

import static org.w3c.css.values.CssOperator.COMMA;

/**
 * @version $Revision$
 */
public class CssColor extends CssValue {

    public static final int type = CssTypes.CSS_COLOR;

    public final int getType() {
        return type;
    }

    Object color = null;
    RGB rgb = null;
    RGBA rgba = null;
    HSL hsl = null;
    HSLA hsla = null;
    HWB hwb = null;

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
        } else if (hsla != null) {
            return hsla.toString();
        } else {
            return hwb.toString();
        }
    }

    /**
     * Parse a RGB color.
     * format rgb(<num>%?, <num>%?, <num>%?)
     */
    public void setRGBColor(CssExpression exp, ApplContext ac)
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
        } else if ((hsla != null) && (otherColor.hsla != null)) {
            return hsla.equals(otherColor.hsla);
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
        rgba = new RGBA();
        __setRGBAColor(rgba, exp, ac);
    }

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

    public void setHSLColor(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        // HSL defined in CSS3 and onward
        if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("hsl(").append(exp.toStringFromStart()).append(')');
            throw new InvalidParamException("notversion", sb.toString(),
                    ac.getCssVersionString(), ac);
        }
        color = null;
        hsl = new HSL();

        CssValue val = exp.getValue();
        char op = exp.getOperator();
        // H
        if (val == null || op != COMMA) {
            throw new InvalidParamException("invalid-color", ac);
        }
        switch (val.getType()) {
            case CssTypes.CSS_ANGLE:
            case CssTypes.CSS_NUMBER:
                hsl.setHue(ac, val);
                break;
            default:
                throw new InvalidParamException("rgb", val, ac); // FIXME hsl
        }

        // S
        exp.next();
        val = exp.getValue();
        op = exp.getOperator();
        if (val == null || op != COMMA) {
            exp.starts();
            throw new InvalidParamException("invalid-color", ac);
        }
        // todo is (0 number) allowed ?
        if (val.getType() == CssTypes.CSS_PERCENTAGE) {
            hsl.setSaturation(ac, val);
        } else {
            exp.starts();
            throw new InvalidParamException("rgb", val, ac); // FIXME hsl
        }

        // L
        exp.next();
        val = exp.getValue();
        op = exp.getOperator();
        if (val == null) {
            exp.starts();
            throw new InvalidParamException("invalid-color", ac);
        }

        if (val.getType() == CssTypes.CSS_PERCENTAGE) {
            hsl.setLightness(ac, val);
        } else {
            exp.starts();
            throw new InvalidParamException("rgb", val, ac); // FIXME hsl
        }

        exp.next();
        if (exp.getValue() != null) {
            exp.starts();
            throw new InvalidParamException("rgb", exp.getValue(), ac);
        }
    }


    public void setHSLAColor(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        // RGBA defined in CSS3 and onward
        if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("hsla(").append(exp.toStringFromStart()).append(')');
            throw new InvalidParamException("notversion", sb.toString(),
                    ac.getCssVersionString(), ac);
        }

        color = null;
        hsla = new HSLA();

        CssValue val = exp.getValue();
        char op = exp.getOperator();
        // H
        if (val == null || op != COMMA) {
            throw new InvalidParamException("invalid-color", ac);
        }
        switch (val.getType()) {
            case CssTypes.CSS_ANGLE:
            case CssTypes.CSS_NUMBER:
                hsla.setHue(ac, val);
                break;
            default:
                throw new InvalidParamException("rgb", val, ac); // FIXME hsl
        }

        // S
        exp.next();
        val = exp.getValue();
        op = exp.getOperator();
        if (val == null || op != COMMA) {
            exp.starts();
            throw new InvalidParamException("invalid-color", ac);
        }
        if (val.getType() == CssTypes.CSS_PERCENTAGE) {
            hsla.setSaturation(ac, val);
        } else {
            exp.starts();
            throw new InvalidParamException("rgb", val, ac); // FIXME hsl
        }

        // L
        exp.next();
        val = exp.getValue();
        op = exp.getOperator();
        if (val == null || op != COMMA) {
            exp.starts();
            throw new InvalidParamException("invalid-color", ac);
        }
        if (val.getType() == CssTypes.CSS_PERCENTAGE) {
            hsla.setLightness(ac, val);
        } else {
            exp.starts();
            throw new InvalidParamException("rgb", val, ac); // FIXME hsl
        }

        // A
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
                hsla.setAlpha(ac, val);
                break;
            default:
                exp.starts();
                throw new InvalidParamException("rgb", val, ac); // FIXME rgba
        }
        // extra values?
        exp.next();
        if (exp.getValue() != null) {
            exp.starts();
            throw new InvalidParamException("rgb", exp.getValue(), ac);
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
        if (val == null || op != COMMA) {
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
        if (val == null || op != COMMA) {
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
        if (val == null || (op != COMMA && exp.getRemainingCount() > 1)) {
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

    private int clippedIntValue(int rgb, ApplContext ac) {
        if (rgb < 0 || rgb > 255) {
            ac.getFrame().addWarning("out-of-range", Util.displayFloat(rgb));
            return (rgb < 0) ? 0 : 255;
        }
        return rgb;
    }

    private float clippedPercentValue(float p, ApplContext ac) {
        if (p < 0. || p > 100.) {
            ac.getFrame().addWarning("out-of-range", Util.displayFloat(p));
            return (p < 0.) ? (float) 0 : (float) 100;
        }
        return p;
    }

    private float clippedAlphaValue(float p, ApplContext ac) {
        if (p < 0. || p > 1.) {
            ac.getFrame().addWarning("out-of-range", Util.displayFloat(p));
            return (float) ((p < 0.) ? 0. : 1.);
        }
        return p;
    }

    private float angleValue(float p, ApplContext ac) {
        if (p < 0.0 || p >= 360.0) {
            ac.getFrame().addWarning("out-of-range", Util.displayFloat(p));
        }
        return p;
    }

}

