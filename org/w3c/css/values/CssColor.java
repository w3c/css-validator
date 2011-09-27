//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;

import java.util.HashMap;

import static org.w3c.css.values.CssOperator.COMMA;

/**
 * <H3>
 * &nbsp;&nbsp; Color units
 * </H3>
 * <p/>
 * A color is a either a keyword or a numerical RGB specification.
 * <p/>
 * The suggested list of keyword color names is: aqua, black, blue, fuchsia,
 * gray, green, lime, maroon, navy, olive, purple, red, silver, teal, white,
 * and yellow. These 16 colors are taken from the Windows VGA palette,
 * and their RGB values are not defined in this specification.
 * <PRE>
 * BODY {color: black; background: white }
 * H1 { color: maroon }
 * H2 { color: olive }
 * </PRE>
 * <p/>
 * The RGB color model is being used in numerical color specifications. These
 * examples all specify the same color:
 * <PRE>
 * EM { color: #f00 }              / * #rgb * /
 * EM { color: #ff0000 }           / * #rrggbb * /
 * EM { color: rgb(255,0,0) }      / * integer range 0 - 255 * /
 * EM { color: rgb(100%, 0%, 0%) } / * float range 0.0% - 100.0% * /
 * </PRE>
 * <p/>
 * The format of an RGB value in hexadecimal notation is a '#' immediately followed
 * by either three or six hexadecimal characters. The three-digit RGB notation
 * (#rgb) is converted into six-digit form (#rrggbb) by replicating digits,
 * not by adding zeros. For example, #fb0 expands to #ffbb00. This makes sure
 * that white (#ffffff) can be specified with the short notation (#fff) and
 * removes any dependencies on the color depth of the display.
 * <p/>
 * The format of an RGB value in the functional notation is 'rgb(' followed
 * by a comma-separated list of three numerical values (either three integer
 * values in the range of 0-255, or three percentage values in the range of
 * 0.0% to 100.0%) followed by ')'. Whitespace characters are allowed around
 * the numerical values.
 * <p/>
 * Values outside the numerical ranges should be clipped. The three rules below
 * are therefore equivalent:
 * <PRE>
 * EM { color: rgb(255,0,0) }       / * integer range 0 - 255 * /
 * EM { color: rgb(300,0,0) }       / * clipped to 255 * /
 * EM { color: rgb(110%, 0%, 0%) }  / * clipped to 100% * /
 * </PRE>
 * <p/>
 * RGB colors are specified in the sRGB color space <A HREF="#ref9">[9]</A>.
 * UAs may vary in the fidelity with which they represent these colors, but
 * use of sRGB provides an unambiguous and objectively measurable definition
 * of what the color should be, which can be related to international standards
 * <A HREF="#ref10">[10]</A>.
 * <p/>
 * UAs may limit their efforts in displaying colors to performing a gamma-correction
 * on them. sRGB specifies a display gamma of 2.2 under specified viewing
 * conditions. UAs adjust the colors given in CSS such that, in combination
 * with an output device's "natural" display gamma, an effective display gamma
 * of 2.2 is produced. <A HREF="#appendix-d">Appendix D</A> gives further details
 * of this. Note that only colors specified in CSS are affected; e.g., images
 * are expected to carry their own color information.
 * <p/>
 * <p/>
 * <H2>
 * <A NAME="appendix-d">Appendix D: Gamma correction</A>
 * </H2>
 * <p/>
 * (This appendix is informative, not normative)
 * <p/>
 * See the <A href="http://www.w3.org/pub/WWW/TR/PNG-GammaAppendix">Gamma
 * Tutorial</A> in the PNG specification <A href="#ref12">[12]</A> if you aren't
 * already familiar with gamma issues.
 * <p/>
 * In the computation, UAs displaying on a CRT may assume an ideal CRT and ignore
 * any effects on apparent gamma caused by dithering. That means the minimal
 * handling they need to do on current platforms is:
 * <DL>
 * <DT>
 * PC using MS-Windows
 * <DD>
 * none
 * <DT>
 * Unix using X11
 * <DD>
 * none
 * <DT>
 * Mac using QuickDraw
 * <DD>
 * apply gamma 1.39 <A HREF="#ref13">[13]</A> (ColorSync-savvy applications
 * may simply pass the sRGB ICC profile <A HREF="#ref14">[14]</A> to ColorSync
 * to perform correct color correction)
 * <DT>
 * SGI using X
 * <DD>
 * apply the gamma value from <TT>/etc/config/system.glGammaVal</TT> (the default
 * value being 1.70; applications running on Irix 6.2 or above may simply pass
 * the sRGB ICC profile to the color management system)
 * <DT>
 * NeXT using NeXTStep
 * <DD>
 * apply gamma 2.22
 * </DL>
 * <p/>
 * "Applying gamma" means that each of the three R, G and B must be converted
 * to R'=R<SUP>gamma</SUP>, G'=G<SUP>gamma</SUP>, G'=B<SUP>gamma</SUP>, before
 * handing to the OS.
 * <p/>
 * This may rapidly be done by building a 256-element lookup table once per
 * browser invocation thus:
 * <PRE>
 * for i := 0 to 255 do
 * raw := i / 255;
 * corr := pow (raw, gamma);
 * table[i] := trunc (0.5 + corr * 255.0)
 * end
 * </PRE>
 * <p/>
 * which then avoids any need to do transcendental math per color attribute,
 * far less per pixel.
 * <p/>
 * See also
 * <p/>
 * <A NAME="ref9">[9]</A> M Anderson, R Motta, S Chandrasekar, M Stokes:
 * "<A HREF="http://www.hpl.hp.com/personal/Michael_Stokes/srgb.htm">Proposal
 * for a Standard Color Space for the Internet - sRGB</A>"
 * (http://www.hpl.hp.com/personal/Michael_Stokes/srgb.htm)
 * <p/>
 * <A NAME="ref10">[10]</A> CIE Publication 15.2-1986,
 * "<A HREF="http://www.hike.te.chiba-u.ac.jp/ikeda/CIE/publ/abst/15-2-86.html">Colorimetry,
 * Second Edition</A>", ISBN 3-900-734-00-3
 * (http://www.hike.te.chiba-u.ac.jp/ikeda/CIE/publ/abst/15-2-86.html)
 * <p/>
 * <A NAME="ref12">[12]</A>
 * "<A href="http://www.w3.org/pub/WWW/TR/REC-png-multi.html">PNG (Portable
 * Network Graphics) Specification, Version 1.0 specification</A>"
 * (http://www.w3.org/pub/WWW/TR/REC-png-multi.html)
 * <p/>
 * <A NAME="ref13">[13]</A> Charles A. Poynton:
 * "<A HREF="ftp://ftp.inforamp.net/pub/users/poynton/doc/Mac/Mac_gamma.pdf">Gamma
 * correction on the Macintosh Platform</A>"
 * (ftp://ftp.inforamp.net/pub/users/poynton/doc/Mac/Mac_gamma.pdf)
 * <p/>
 * <A NAME="ref14">[14]</A> International Color Consortium:
 * "<A HREF="ftp://sgigate.sgi.com/pub/icc/ICC32.pdf">ICC Profile Format
 * Specification, version 3.2</A>", 1995 (ftp://sgigate.sgi.com/pub/icc/ICC32.pdf)
 *
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

    protected static HashMap<String, Object> definedColors;
    private static HashMap<String, String> deprecatedColors;
    static CssIdent inherit;

    /**
     * Create a new CssColor.
     */
    public CssColor() {
        color = inherit;
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
            if (color == inherit) {
                return null;
            } else {
                return color;
            }
        } else {
            return rgb;
        }
    }

    /**
     * Returns <code>true</code> if the internal value is the default value
     * (e.g. 'inherited').
     */
    public boolean isDefault() {
        return color == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (color != null) {
            if (color == inherit) {
                return inherit.toString();
            } else {
                return color.toString();
            }
        } else if (rgba != null) {
            return rgba.toString();
        } else if (hsl != null) {
            return hsl.toString();
        } else if (hsla != null) {
            return hsla.toString();
        } else {
            return rgb.toString();
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
                CssNumber number = (CssNumber) val;
                rgb.setRed(clippedIntValue(number.getInt(), ac));
                rgb.setPercent(false);
                break;
            case CssTypes.CSS_PERCENTAGE:
                CssPercentage percent = (CssPercentage) val;
                rgb.setRed(clippedPercentValue(percent.floatValue(), ac));
                rgb.setPercent(true);
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
                CssNumber number = (CssNumber) val;
                rgb.setGreen(clippedIntValue(number.getInt(), ac));
                break;
            case CssTypes.CSS_PERCENTAGE:
                if (!rgb.isPercent()) {
                    throw new InvalidParamException("integer", val, ac);
                }
                CssPercentage percent = (CssPercentage) val;
                rgb.setGreen(clippedPercentValue(percent.floatValue(), ac));
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
                CssNumber number = (CssNumber) val;
                rgb.setBlue(clippedIntValue(number.getInt(), ac));
                break;
            case CssTypes.CSS_PERCENTAGE:
                if (!rgb.isPercent()) {
                    throw new InvalidParamException("integer", val, ac);
                }
                CssPercentage percent = (CssPercentage) val;
                rgb.setBlue(clippedPercentValue(percent.floatValue(), ac));
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
    private void setShortRGBColor(String s, ApplContext ac)
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
        rgb.output = s;
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
    private void setIdentColor(String s, ApplContext ac)
            throws InvalidParamException {
        String lower_s = s.toLowerCase();
        if (computeIdentColor(definedColors, lower_s)) {
            return;
        } else if (deprecatedColors.get(lower_s) != null) {
            color = lower_s;
            ac.getFrame().addWarning("deprecated", s);
            return;
        }

        throw new InvalidParamException("value", s, "color", ac);
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

    public void setRGBAColor(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        CssValue val;
        char op;
        color = null;
        rgba = new RGBA();

        val = exp.getValue();
        op = exp.getOperator();
        if (val == null || op != COMMA) {
            throw new InvalidParamException("invalid-color", ac);
        }

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                CssNumber number = (CssNumber) val;
                rgba.setRed(clippedIntValue(number.getInt(), ac));
                rgba.setPercent(false);
                break;
            case CssTypes.CSS_PERCENTAGE:
                CssPercentage percent = (CssPercentage) val;
                rgba.setRed(clippedPercentValue(percent.floatValue(), ac));
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
                CssNumber number = (CssNumber) val;
                rgba.setGreen(clippedIntValue(number.getInt(), ac));
                break;
            case CssTypes.CSS_PERCENTAGE:
                if (!rgba.isPercent()) {
                    exp.starts();
                    throw new InvalidParamException("integer", val, ac);
                }
                CssPercentage percent = (CssPercentage) val;
                rgba.setGreen(clippedPercentValue(percent.floatValue(), ac));
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
                CssNumber number = (CssNumber) val;
                rgba.setBlue(clippedIntValue(number.getInt(), ac));
                break;
            case CssTypes.CSS_PERCENTAGE:
                if (!rgba.isPercent()) {
                    exp.starts();
                    throw new InvalidParamException("integer", val, ac);
                }
                CssPercentage percent = (CssPercentage) val;
                rgba.setBlue(clippedPercentValue(percent.floatValue(), ac));
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
        if (val.getType() == CssTypes.CSS_NUMBER) {
            CssNumber number = (CssNumber) val;
            rgba.setAlpha(clippedAlphaValue(number.getValue(), ac));
        } else {
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
        color = null;
        hsl = new HSL();

        CssValue val = exp.getValue();
        char op = exp.getOperator();
        // H
        if (val == null || op != COMMA) {
            throw new InvalidParamException("invalid-color", ac);
        }
        if (val.getType() == CssTypes.CSS_NUMBER) {
            CssNumber number = (CssNumber) val;
            hsl.setHue(angleValue(number.getValue(), ac));
        } else {
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
            CssPercentage percent = (CssPercentage) val;
            hsl.setSaturation(clippedPercentValue(percent.floatValue(), ac));
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
            CssPercentage percent = (CssPercentage) val;
            hsl.setLightness(clippedPercentValue(percent.floatValue(), ac));
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
        color = null;
        hsla = new HSLA();

        CssValue val = exp.getValue();
        char op = exp.getOperator();
        // H
        if (val == null || op != COMMA) {
            throw new InvalidParamException("invalid-color", ac);
        }
        if (val.getType() == CssTypes.CSS_NUMBER) {
            CssNumber number = (CssNumber) val;
            hsla.setHue(angleValue(number.getValue(), ac));
        } else {
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
            CssPercentage percent = (CssPercentage) val;
            hsla.setSaturation(clippedPercentValue(percent.floatValue(), ac));
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
            CssPercentage percent = (CssPercentage) val;
            hsla.setLightness(clippedPercentValue(percent.floatValue(), ac));
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
        if (val.getType() == CssTypes.CSS_NUMBER) {
            CssNumber number = (CssNumber) val;
            hsla.setAlpha(clippedAlphaValue(number.getValue(), ac));
        } else {
            exp.starts();
            throw new InvalidParamException("rgb", val, ac); // FIXME hsl
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

    /**
     * Gets the red component of this color.
     */
    //    public Object getRed() {
    //	return rgb.r;
    //}

    /**
     * Gets the green component of this color.
     */
    //    public Object getGreen() {
    //	return rgb.g;
    //}

    /**
     * Gets the blue component of this color.
     */
    //public Object getBlue() {
    //	return rgb.b;
    //}

    static {
        inherit = CssIdent.getIdent("inherit");
        definedColors = new HashMap<String, Object>();
        deprecatedColors = new HashMap<String, String>();

        definedColors.put("aliceblue",
                new RGB(240, 248, 255));
        definedColors.put("antiquewhite",
                new RGB(250, 235, 215));
        definedColors.put("aqua",
                new RGB(0, 255, 255));
        definedColors.put("aquamarine",
                new RGB(127, 255, 212));
        definedColors.put("azure",
                new RGB(240, 255, 255));
        definedColors.put("beige",
                new RGB(245, 245, 220));
        definedColors.put("bisque",
                new RGB(255, 228, 196));
        definedColors.put("black",
                new RGB(0, 0, 0));
        definedColors.put("blanchedalmond",
                new RGB(255, 235, 205));
        definedColors.put("blue",
                new RGB(0, 0, 255));
        definedColors.put("blueviolet",
                new RGB(138, 43, 226));
        definedColors.put("brown",
                new RGB(165, 42, 42));
        definedColors.put("burlywood",
                new RGB(222, 184, 135));
        definedColors.put("cadetBlue",
                new RGB(95, 158, 160));
        definedColors.put("chartreuse",
                new RGB(127, 255, 0));
        definedColors.put("chocolate",
                new RGB(210, 105, 30));
        definedColors.put("coral",
                new RGB(255, 127, 80));
        definedColors.put("cornflowerblue",
                new RGB(100, 149, 237));
        definedColors.put("cornsilk",
                new RGB(255, 248, 220));
        definedColors.put("crimson",
                new RGB(220, 20, 60));
        definedColors.put("cyan",
                new RGB(0, 255, 255));
        definedColors.put("darkblue",
                new RGB(0, 0, 139));
        definedColors.put("darkcyan",
                new RGB(0, 139, 139));
        definedColors.put("darkgoldenrod",
                new RGB(184, 134, 11));
        definedColors.put("darkgray",
                new RGB(169, 169, 169));
        definedColors.put("darkgreen",
                new RGB(0, 100, 0));
        definedColors.put("darkkhaki",
                new RGB(189, 183, 107));
        definedColors.put("darkmagenta",
                new RGB(139, 0, 139));
        definedColors.put("darkolivegreen",
                new RGB(85, 107, 47));
        definedColors.put("darkorange",
                new RGB(255, 140, 0));
        definedColors.put("darkorchid",
                new RGB(153, 50, 204));
        definedColors.put("darkred",
                new RGB(139, 0, 0));
        definedColors.put("darksalmon",
                new RGB(233, 150, 122));
        definedColors.put("darkseagreen",
                new RGB(143, 188, 143));
        definedColors.put("darkslateblue",
                new RGB(72, 61, 139));
        definedColors.put("darkslategray",
                new RGB(47, 79, 79));
        definedColors.put("darkturquoise",
                new RGB(0, 206, 209));
        definedColors.put("darkviolet",
                new RGB(148, 0, 211));
        definedColors.put("deeppink",
                new RGB(255, 20, 147));
        definedColors.put("deepskyblue",
                new RGB(0, 191, 255));
        definedColors.put("dimgray",
                new RGB(105, 105, 105));
        definedColors.put("dodgerblue",
                new RGB(30, 144, 255));
        definedColors.put("firebrick",
                new RGB(178, 34, 34));
        definedColors.put("floralwhite",
                new RGB(255, 250, 240));
        definedColors.put("forestgreen",
                new RGB(34, 139, 34));
        definedColors.put("fuchsia",
                new RGB(255, 0, 255));
        definedColors.put("gainsboro",
                new RGB(220, 220, 220));
        definedColors.put("ghostwhite",
                new RGB(248, 248, 255));
        definedColors.put("gold",
                new RGB(255, 215, 0));
        definedColors.put("goldenrod",
                new RGB(218, 165, 32));
        definedColors.put("gray",
                new RGB(128, 128, 128));
        definedColors.put("green",
                new RGB(0, 128, 0));
        definedColors.put("greenyellow",
                new RGB(173, 255, 47));
        definedColors.put("honeydew",
                new RGB(240, 255, 240));
        definedColors.put("hotpink",
                new RGB(255, 105, 180));
        definedColors.put("indianred",
                new RGB(205, 92, 92));
        definedColors.put("indigo",
                new RGB(75, 0, 130));
        definedColors.put("ivory",
                new RGB(255, 255, 240));
        definedColors.put("khaki",
                new RGB(240, 230, 140));
        definedColors.put("lavender",
                new RGB(230, 230, 250));
        definedColors.put("lavenderblush",
                new RGB(255, 240, 245));
        definedColors.put("lawngreen",
                new RGB(124, 252, 0));
        definedColors.put("lemonchiffon",
                new RGB(255, 250, 205));
        definedColors.put("lightblue",
                new RGB(173, 216, 230));
        definedColors.put("lightcoral",
                new RGB(240, 128, 128));
        definedColors.put("lightcyan",
                new RGB(224, 255, 255));
        definedColors.put("lightgoldenrodyellow",
                new RGB(250, 250, 210));
        definedColors.put("lightgreen",
                new RGB(144, 238, 144));
        definedColors.put("lightgrey",
                new RGB(211, 211, 211));
        definedColors.put("lightpink",
                new RGB(255, 182, 193));
        definedColors.put("lightsalmon",
                new RGB(255, 160, 122));
        definedColors.put("lightseagreen",
                new RGB(32, 178, 170));
        definedColors.put("lightskyblue",
                new RGB(135, 206, 250));
        definedColors.put("lightslategray",
                new RGB(119, 136, 153));
        definedColors.put("lightsteelblue",
                new RGB(176, 196, 222));
        definedColors.put("lightyellow",
                new RGB(255, 255, 224));
        definedColors.put("lime",
                new RGB(0, 255, 0));
        definedColors.put("limegreen",
                new RGB(50, 205, 50));
        definedColors.put("linen",
                new RGB(250, 240, 230));
        definedColors.put("magenta",
                new RGB(255, 0, 255));
        definedColors.put("maroon",
                new RGB(128, 0, 0));
        definedColors.put("mediumaquamarine",
                new RGB(102, 205, 170));
        definedColors.put("mediumblue",
                new RGB(0, 0, 205));
        definedColors.put("mediumorchid",
                new RGB(186, 85, 211));
        definedColors.put("mediumpurple",
                new RGB(147, 112, 219));
        definedColors.put("mediumseagreen",
                new RGB(60, 179, 113));
        definedColors.put("mediumslateblue",
                new RGB(123, 104, 238));
        definedColors.put("mediumspringgreen",
                new RGB(0, 250, 154));
        definedColors.put("mediumturquoise",
                new RGB(72, 209, 204));
        definedColors.put("mediumvioletred",
                new RGB(199, 21, 133));
        definedColors.put("midnightblue",
                new RGB(25, 25, 112));
        definedColors.put("mintcream",
                new RGB(245, 255, 250));
        definedColors.put("mistyrose",
                new RGB(255, 228, 225));
        definedColors.put("moccasin",
                new RGB(255, 228, 181));
        definedColors.put("navajowhite",
                new RGB(255, 222, 173));
        definedColors.put("navy",
                new RGB(0, 0, 128));
        definedColors.put("oldlace",
                new RGB(253, 245, 230));
        definedColors.put("olive",
                new RGB(128, 128, 0));
        definedColors.put("olivedrab",
                new RGB(107, 142, 35));
        definedColors.put("orange",
                new RGB(255, 165, 0));
        definedColors.put("orangered",
                new RGB(255, 69, 0));
        definedColors.put("orchid",
                new RGB(218, 112, 214));
        definedColors.put("palegoldenrod",
                new RGB(238, 232, 170));
        definedColors.put("palegreen",
                new RGB(152, 251, 152));
        definedColors.put("paleturquoise",
                new RGB(175, 238, 238));
        definedColors.put("palevioletred",
                new RGB(219, 112, 147));
        definedColors.put("papayawhip",
                new RGB(255, 239, 213));
        definedColors.put("peachpuff",
                new RGB(255, 218, 185));
        definedColors.put("peru",
                new RGB(205, 133, 63));
        definedColors.put("pink",
                new RGB(255, 192, 203));
        definedColors.put("plum",
                new RGB(221, 160, 221));
        definedColors.put("powderBlue",
                new RGB(176, 224, 230));
        definedColors.put("purple",
                new RGB(128, 0, 128));
        definedColors.put("red",
                new RGB(255, 0, 0));
        definedColors.put("rosybrown",
                new RGB(188, 143, 143));
        definedColors.put("royalblue",
                new RGB(65, 105, 225));
        definedColors.put("saddlebrown",
                new RGB(139, 69, 19));
        definedColors.put("salmon",
                new RGB(250, 128, 114));
        definedColors.put("sandybrown",
                new RGB(244, 164, 96));
        definedColors.put("seagreen",
                new RGB(46, 139, 87));
        definedColors.put("seashell",
                new RGB(255, 245, 238));
        definedColors.put("sienna",
                new RGB(160, 82, 45));
        definedColors.put("silver",
                new RGB(192, 192, 192));
        definedColors.put("skyblue",
                new RGB(135, 206, 235));
        definedColors.put("slateblue",
                new RGB(106, 90, 205));
        definedColors.put("slategray",
                new RGB(112, 128, 144));
        definedColors.put("snow",
                new RGB(255, 250, 250));
        definedColors.put("springgreen",
                new RGB(0, 255, 127));
        definedColors.put("steelblue",
                new RGB(70, 130, 180));
        definedColors.put("tan",
                new RGB(210, 180, 140));
        definedColors.put("teal",
                new RGB(0, 128, 128));
        definedColors.put("thistle",
                new RGB(216, 191, 216));
        definedColors.put("tomato",
                new RGB(255, 99, 71));
        definedColors.put("turquoise",
                new RGB(64, 224, 208));
        definedColors.put("violet",
                new RGB(238, 130, 238));
        definedColors.put("wheat",
                new RGB(245, 222, 179));
        definedColors.put("white",
                new RGB(255, 255, 255));
        definedColors.put("whitesmoke",
                new RGB(245, 245, 245));
        definedColors.put("yellow",
                new RGB(255, 255, 0));
        definedColors.put("yellowgreen",
                new RGB(154, 205, 50));

        definedColors.put("grey",
                new RGB(128, 128, 128));
        definedColors.put("darkslategrey",
                new RGB(47, 79, 79));
        definedColors.put("dimgrey",
                new RGB(105, 105, 105));
        definedColors.put("lightgray",
                new RGB(211, 211, 211));
        definedColors.put("lightslategrey",
                new RGB(119, 136, 153));
        definedColors.put("slategrey",
                new RGB(112, 128, 144));
        definedColors.put("transparent",
                new RGBA(0, 0, 0, (float) 0.0));

        //CSS2 System colors deprecated
        deprecatedColors.put("activeborder", "ActiveBorder");
        deprecatedColors.put("activecaption", "ActiveCaption");
        deprecatedColors.put("appworkspace", "AppWorkspace");
        deprecatedColors.put("background", "Background");
        deprecatedColors.put("buttonface", "ButtonFace");
        deprecatedColors.put("buttonhighlight", "ButtonHighlight");
        deprecatedColors.put("buttonshadow", "ButtonShadow");
        deprecatedColors.put("buttontext", "ButtonText");
        deprecatedColors.put("captiontext", "CaptionText");
        deprecatedColors.put("graytext", "GrayText");
        deprecatedColors.put("highlight", "Highlight");
        deprecatedColors.put("highlighttext", "HighlightText");
        deprecatedColors.put("inactiveborder", "InactiveBorder");
        deprecatedColors.put("inactivecaption", "InactiveCaption");
        deprecatedColors.put("inactivecaptiontext", "InactiveCaptionText");
        deprecatedColors.put("infobackground", "InfoBackground");
        deprecatedColors.put("infotext", "InfoText");
        deprecatedColors.put("menu", "Menu");
        deprecatedColors.put("menutext", "MenuText");
        deprecatedColors.put("scrollbar", "Scrollbar");
        deprecatedColors.put("threeddarkshadow", "ThreeDDarkShadow");
        deprecatedColors.put("threedface", "ThreeDFace");
        deprecatedColors.put("threedhighlight", "ThreeDHighlight");
        deprecatedColors.put("threedlightshadow", "ThreeDLightShadow");
        deprecatedColors.put("threedshadow", "ThreeDShadow");
        deprecatedColors.put("window", "Window");
        deprecatedColors.put("windowframe", "WindowFrame");
        deprecatedColors.put("windowtext", "WindowText");

        // CSS3 user preferences for hyperlink colors -> removed from spec
        /*
       definedColors.put("ActiveHyperlink", "ActiveHyperlink");
       definedColors.put("ActiveHyperlinkText", "ActiveHyperlinkText");
       definedColors.put("HoverHyperlink", "HoverHyperlink");
       definedColors.put("HoverHyperlinkText", "HoverHyperlinkText");
       definedColors.put("Hyperlink", "Hyperlink");
       definedColors.put("HyperlinkText", "HyperlinkText");
       definedColors.put("VisitedHyperlink", "VisitedHyperlink");
       definedColors.put("VisitedHyperlinkText", "VisitedHyperlinkText");
       */

        //Flavor system color
        definedColors.put("flavor", "flavor");
        definedColors.put("currentcolor", "currentColor");

    }

}

