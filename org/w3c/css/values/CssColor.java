//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.5  2003/04/08 09:21:24  sijtsche
 * SVG color values added for CSS3
 *
 * Revision 1.4  2002/07/26 14:45:43  sijtsche
 * HSL and HSLA colors have other angle values conform new color spec CSS3
 *
 * Revision 1.3  2002/07/21 13:02:13  sijtsche
 * added RGBA, HSL, HSLA colors and user prefs for hyperlinks
 *
 * Revision 1.2  2002/05/08 10:04:25  dejong
 * newer version with the new CSS 3 color descriptors HSL, RGBA and HSLA + new user preferences for hyperlink colors
 *
 * Revision 1.1  2002/03/13 19:57:22  plehegar
 * New
 *
 * Revision 2.2  1997/08/20 11:38:07  plehegar
 * Freeze
 *
 * Revision 1.1  1997/07/10 23:42:32  plehegar
 * Initial revision
 *
 */
package org.w3c.css.values;

import java.util.Hashtable;
import org.w3c.css.util.Util;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssAngle;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssNumber;

import java.util.Vector;

/**
 *   <H3>
 *     &nbsp;&nbsp; Color units
 *   </H3>
 *   <P>
 *   A color is a either a keyword or a numerical RGB specification.
 *   <P>
 *   The suggested list of keyword color names is: aqua, black, blue, fuchsia,
 *   gray, green, lime, maroon, navy, olive, purple, red, silver, teal, white,
 *   and yellow. These 16 colors are taken from the Windows VGA palette, and their
 *   RGB values are not defined in this specification.
 *   <PRE>
 *   BODY {color: black; background: white }
 *   H1 { color: maroon }
 *   H2 { color: olive }
 * </PRE>
 *   <P>
 *   The RGB color model is being used in numerical color specifications. These
 *   examples all specify the same color:
 *   <PRE>
 *   EM { color: #f00 }              / * #rgb * /
 *   EM { color: #ff0000 }           / * #rrggbb * /
 *   EM { color: rgb(255,0,0) }      / * integer range 0 - 255 * /
 *   EM { color: rgb(100%, 0%, 0%) } / * float range 0.0% - 100.0% * /
 * </PRE>
 *   <P>
 *   The format of an RGB value in hexadecimal notation is a '#' immediately followed
 *   by either three or six hexadecimal characters. The three-digit RGB notation
 *   (#rgb) is converted into six-digit form (#rrggbb) by replicating digits,
 *   not by adding zeros. For example, #fb0 expands to #ffbb00. This makes sure
 *   that white (#ffffff) can be specified with the short notation (#fff) and
 *   removes any dependencies on the color depth of the display.
 *   <P>
 *   The format of an RGB value in the functional notation is 'rgb(' followed
 *   by a comma-separated list of three numerical values (either three integer
 *   values in the range of 0-255, or three percentage values in the range of
 *   0.0% to 100.0%) followed by ')'. Whitespace characters are allowed around
 *   the numerical values.
 *   <P>
 *   Values outside the numerical ranges should be clipped. The three rules below
 *   are therefore equivalent:
 *   <PRE>
 *   EM { color: rgb(255,0,0) }       / * integer range 0 - 255 * /
 *   EM { color: rgb(300,0,0) }       / * clipped to 255 * /
 *   EM { color: rgb(110%, 0%, 0%) }  / * clipped to 100% * /
 * </PRE>
 *   <P>
 *   RGB colors are specified in the sRGB color space <A HREF="#ref9">[9]</A>.
 *   UAs may vary in the fidelity with which they represent these colors, but
 *   use of sRGB provides an unambiguous and objectively measurable definition
 *   of what the color should be, which can be related to international standards
 *   <A HREF="#ref10">[10]</A>.
 *   <P>
 *   UAs may limit their efforts in displaying colors to performing a gamma-correction
 *   on them. sRGB specifies a display gamma of 2.2 under specified viewing
 *   conditions. UAs adjust the colors given in CSS such that, in combination
 *   with an output device's "natural" display gamma, an effective display gamma
 *   of 2.2 is produced. <A HREF="#appendix-d">Appendix D</A> gives further details
 *   of this. Note that only colors specified in CSS are affected; e.g., images
 *   are expected to carry their own color information.
 *
 *
 *   <H2>
 *     <A NAME="appendix-d">Appendix D: Gamma correction</A>
 *   </H2>
 *   <P>
 *   (This appendix is informative, not normative)
 *   <P>
 *   See the <A href="http://www.w3.org/pub/WWW/TR/PNG-GammaAppendix">Gamma
 *   Tutorial</A> in the PNG specification <A href="#ref12">[12]</A> if you aren't
 *   already familiar with gamma issues.
 *   <P>
 *   In the computation, UAs displaying on a CRT may assume an ideal CRT and ignore
 *   any effects on apparent gamma caused by dithering. That means the minimal
 *   handling they need to do on current platforms is:
 *   <DL>
 *     <DT>
 *       PC using MS-Windows
 *     <DD>
 *       none
 *     <DT>
 *       Unix using X11
 *     <DD>
 *       none
 *     <DT>
 *       Mac using QuickDraw
 *     <DD>
 *       apply gamma 1.39 <A HREF="#ref13">[13]</A> (ColorSync-savvy applications
 *       may simply pass the sRGB ICC profile <A HREF="#ref14">[14]</A> to ColorSync
 *       to perform correct color correction)
 *     <DT>
 *       SGI using X
 *     <DD>
 *       apply the gamma value from <TT>/etc/config/system.glGammaVal</TT> (the default
 *       value being 1.70; applications running on Irix 6.2 or above may simply pass
 *       the sRGB ICC profile to the color management system)
 *     <DT>
 *       NeXT using NeXTStep
 *     <DD>
 *       apply gamma 2.22
 *   </DL>
 *   <P>
 *   "Applying gamma" means that each of the three R, G and B must be converted
 *   to R'=R<SUP>gamma</SUP>, G'=G<SUP>gamma</SUP>, G'=B<SUP>gamma</SUP>, before
 *   handing to the OS.
 *   <P>
 *   This may rapidly be done by building a 256-element lookup table once per
 *   browser invocation thus:
 *   <PRE>
 *   for i := 0 to 255 do
 *     raw := i / 255;
 *     corr := pow (raw, gamma);
 *     table[i] := trunc (0.5 + corr * 255.0)
 *   end
 * </PRE>
 *   <P>
 *   which then avoids any need to do transcendental math per color attribute,
 *   far less per pixel.

 * See also
 * <P>
 * <A NAME="ref9">[9]</A> M Anderson, R Motta, S Chandrasekar, M Stokes:
 * "<A HREF="http://www.hpl.hp.com/personal/Michael_Stokes/srgb.htm">Proposal
 * for a Standard Color Space for the Internet - sRGB</A>"
 * (http://www.hpl.hp.com/personal/Michael_Stokes/srgb.htm)
 *   <P>
 *  <A NAME="ref10">[10]</A> CIE Publication 15.2-1986,
 *  "<A HREF="http://www.hike.te.chiba-u.ac.jp/ikeda/CIE/publ/abst/15-2-86.html">Colorimetry,
 *  Second Edition</A>", ISBN 3-900-734-00-3
 *  (http://www.hike.te.chiba-u.ac.jp/ikeda/CIE/publ/abst/15-2-86.html)
 * <P>
 * <A NAME="ref12">[12]</A>
 * "<A href="http://www.w3.org/pub/WWW/TR/REC-png-multi.html">PNG (Portable
 * Network Graphics) Specification, Version 1.0 specification</A>"
 * (http://www.w3.org/pub/WWW/TR/REC-png-multi.html)
 * <P>
 * <A NAME="ref13">[13]</A> Charles A. Poynton:
 * "<A HREF="ftp://ftp.inforamp.net/pub/users/poynton/doc/Mac/Mac_gamma.pdf">Gamma
 * correction on the Macintosh Platform</A>"
 * (ftp://ftp.inforamp.net/pub/users/poynton/doc/Mac/Mac_gamma.pdf)
 * <P>
 * <A NAME="ref14">[14]</A> International Color Consortium:
 * "<A HREF="ftp://sgigate.sgi.com/pub/icc/ICC32.pdf">ICC Profile Format
 *  Specification, version 3.2</A>", 1995 (ftp://sgigate.sgi.com/pub/icc/ICC32.pdf)
 *
 * @version $Revision$
 */
public class CssColor extends CssValue
        implements CssColorConstants, CssOperator {

    Object color;
    RGB rgb;
    RGBA rgba = null;
	HSL hsl = null;
	HSLA hsla = null;

    static Hashtable definedColors;
    static Hashtable deprecatedColors;
    static CssIdent inherit = new CssIdent("inherit");

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
     * @exception InvalidParamException the color is incorrect
     */
    public CssColor(ApplContext ac, String s) throws InvalidParamException {
	//	setIdentColor(s.toLowerCase(), ac);
	setIdentColor(s, ac);
    }

    /**
     * Set the value from a defined color RBG.
     *
     * @param s the string representation of the color.
     * @exception InvalidParamException the color is incorrect.
     */
    public void set(String s, ApplContext ac)  throws InvalidParamException {
	if (s.startsWith("#")) {
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
	    return rgb.r;
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

	if (((Float) val.get()).floatValue() < 0 ||
	    ((Float) val.get()).floatValue() > 255)
	    ac.getFrame().addWarning("out-of-range", Integer.toString(((Float) val.get()).intValue()));

	if (val instanceof CssNumber) {
	    rgb.r = new Integer(((Float) val.get()).intValue());
	} else if (val instanceof CssPercentage) {
	    rgb.r = ((Float) val.get());//.floatValue();
	} else {
	    throw new InvalidParamException("rgb", val, ac);
	}

	exp.next();
	val = exp.getValue();
	op = exp.getOperator();

	if (val == null || op != COMMA) {
	    throw new InvalidParamException("invalid-color", ac);
	}

	if (((Float) val.get()).floatValue() < 0 ||
	    ((Float) val.get()).floatValue() > 255)
	    ac.getFrame().addWarning("out-of-range", Integer.toString(((Float) val.get()).intValue()));

	if (val instanceof CssNumber) {
	    rgb.g = new Integer(((Float) val.get()).intValue());
	} else if (val instanceof CssPercentage) {
	    //	    rgb.g = ((Float) val.get());//.floatValue();
	    rgb.g = ((Float) val.get());//.floatValue();
	} else {
	    throw new InvalidParamException("rgb", val, ac);
	}

	exp.next();
	val = exp.getValue();
	op = exp.getOperator();

	if (val == null) {
	    throw new InvalidParamException("invalid-color", ac);
	}

	if (((Float) val.get()).floatValue() < 0 ||
	    ((Float) val.get()).floatValue() > 255)
	ac.getFrame().addWarning("out-of-range", Integer.toString(((Float) val.get()).intValue()));

	if (val instanceof CssNumber) {
	    rgb.b = new Integer(((Float) val.get()).intValue());
	} else if (val instanceof CssPercentage) {
	    rgb.b = ((Float) val.get());//.floatValue();
	} else {
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

	rgb = new RGB();
	color = null;
	s = s.substring(1);

	if (s.length() != 3 && s.length() != 6) {
	    throw new InvalidParamException("rgb", s, ac);
	}
	if (s.length() == 3) {
	    String sh = s.substring(0,1);
	    r = Integer.parseInt(sh+sh, 16);
	    sh = s.substring(1,2);
	    g = Integer.parseInt(sh+sh, 16);
	    sh = s.substring(2,3);
	    b = Integer.parseInt(sh+sh, 16);
	} else {
	    r = Integer.parseInt(s.substring(0,2), 16);
	    g = Integer.parseInt(s.substring(2,4), 16);
	    b = Integer.parseInt(s.substring(4,6), 16);
	}
	rgb.r = new Integer(r);
	rgb.g = new Integer(g);
	rgb.b = new Integer(b);
	rgb.output = "#" + s;
    }

    /**
     * Parse an ident color.
     */
    private void setIdentColor(String s, ApplContext ac)
	    throws InvalidParamException {
	String lower_s = s.toLowerCase();
	if (definedColors.get(lower_s) != null) {
	    Object obj = definedColors.get(lower_s);
	    if (obj instanceof RGB) {
		color = lower_s;
		rgb = (RGB) obj;
	    } else if (obj instanceof String) {
		color = (String) obj;
		if (!obj.equals(s)) {
		    ac.getFrame().addWarning("color.mixed-capitalization",
					     s);
		}
	    }
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
     * @param value The other value.
     */
    public boolean equals(Object cssColor) {
	return ((cssColor instanceof CssColor) &&
		((color != null && color.equals(((CssColor) cssColor).color))
		 || ((color == null)
		     && (rgb != null)
		     && (((CssColor) cssColor).rgb != null)
		     && (rgb.r.equals(((CssColor) cssColor).rgb.r)
			 && rgb.g.equals(((CssColor) cssColor).rgb.g)
			 && rgb.b.equals(((CssColor) cssColor).rgb.b)))));
    }

    public void setRGBAColor(Vector exp, ApplContext ac)
  	    throws InvalidParamException {

		color = null;
		rgba = new RGBA();
		Float r;
		Float g;
		Float b;
		Float a;

		try {
			r = new Float(((CssValue) exp.elementAt(0)).toString());
		} catch (Exception e) {
			throw new InvalidParamException("rgb", (exp.elementAt(0)).toString(), ac);
		}

		try {
			g = new Float(((CssValue) exp.elementAt(1)).toString());
		} catch (Exception e) {
			throw new InvalidParamException("rgb", (exp.elementAt(1)).toString(), ac);
		}

		try {
			b = new Float(((CssValue) exp.elementAt(2)).toString());
		} catch (Exception e) {
			throw new InvalidParamException("rgb", (exp.elementAt(2)).toString(), ac);
		}

		try {
			a = new Float(((CssValue) exp.elementAt(3)).toString());
		} catch (Exception e) {
			throw new InvalidParamException("rgb", (exp.elementAt(3)).toString(), ac);
		}

		if (r.floatValue() < 0 || r.floatValue() > 255) {
		    ac.getFrame().addWarning("out-of-range", Integer.toString(r.intValue()));
		    r = new Float(clampedValueRGB(ac, r.floatValue()));
		}
		if (g.floatValue() < 0 || g.floatValue() > 255) {
		    ac.getFrame().addWarning("out-of-range", Integer.toString(g.intValue()));
		    g = new Float(clampedValueRGB(ac, g.floatValue()));
		}
		if (b.floatValue() < 0 || b.floatValue() > 255) {
		    ac.getFrame().addWarning("out-of-range", Integer.toString(b.intValue()));
		    b = new Float(clampedValueRGB(ac, b.floatValue()));
		}

		if (a.floatValue() < 0 || a.floatValue() > 1) {
		    ac.getFrame().addWarning("out-of-range", Integer.toString(a.intValue()));
		    a = new Float(clampedValue(ac, a.floatValue()));
		}

		rgba.r = r;
		rgba.g = g;
		rgba.b = b;
		rgba.a = a;

	}

    public void setHSLColor(Vector exp, ApplContext ac)
  	    throws InvalidParamException {

		color = null;
		hsl = new HSL();

		//CssAngle h = new CssAngle();
		CssNumber h = new CssNumber();
		CssPercentage s = new CssPercentage();
		CssPercentage l = new CssPercentage();

		// no correct InvalidParamException in CssAngle in case of an error, therefore this double catch
		/*
		try {
			h.set((exp.elementAt(0)).toString(), ac);
		} catch (InvalidParamException e) {
			throw new InvalidParamException("angle", (exp.elementAt(0)).toString(), ac);
		} catch (Exception e) {
			throw new InvalidParamException("angle", (exp.elementAt(0)).toString(), ac);
		}
		*/

		h.set((exp.elementAt(0)).toString(), ac);

		if (((Float)h.get()).intValue() > 360 || ((Float)h.get()).intValue() < 0) {
			throw new InvalidParamException("angle", (exp.elementAt(0)).toString(), ac);
		}

		// here a correct InvalidParamException is thrown in case of an error
		s.set((exp.elementAt(1)).toString(), ac);
		l.set((exp.elementAt(2)).toString(), ac);

		hsl.h = h;
		hsl.s = s;
		hsl.l = l;

	}

    public void setHSLAColor(Vector exp, ApplContext ac)
  	    throws InvalidParamException {

		color = null;
		hsla = new HSLA();

		//CssAngle h = new CssAngle();
		CssNumber h = new CssNumber();
		CssPercentage s = new CssPercentage();
		CssPercentage l = new CssPercentage();
		Float a;

		// no correct InvalidParamException in CssAngle in case of an error, therefore this double catch
		/*
		try {
			h.set((exp.elementAt(0)).toString(), ac);
		} catch (InvalidParamException e) {
			throw new InvalidParamException("angle", (exp.elementAt(0)).toString(), ac);
		} catch (Exception e) {
			throw new InvalidParamException("angle", (exp.elementAt(0)).toString(), ac);
		}
		*/

		h.set((exp.elementAt(0)).toString(), ac);

		if (((Float)h.get()).intValue() > 360 || ((Float)h.get()).intValue() < 0) {
			throw new InvalidParamException("angle", (exp.elementAt(0)).toString(), ac);
		}

		// here a correct InvalidParamException is thrown in case of an error
		s.set((exp.elementAt(1)).toString(), ac);
		l.set((exp.elementAt(2)).toString(), ac);

		try {
			a = new Float(((CssValue) exp.elementAt(3)).toString());
		} catch (Exception e) {
			throw new InvalidParamException("rgb", (exp.elementAt(3)).toString(), ac);
		}

		if (a.floatValue() < 0 || a.floatValue() > 1) {
		    ac.getFrame().addWarning("out-of-range", Integer.toString(a.intValue()));
		    a = new Float(clampedValue(ac, a.floatValue()));
		}

		hsla.h = h;
		hsla.s = s;
		hsla.l = l;
		hsla.a = a;

	}

    /**
     * Brings all values back between 0 and 1
     *
     * @param opac Alpha value that indicates opacity
     */
   private float clampedValue(ApplContext ac, float opac) {
       if (opac < 0 || opac > 1) {
          ac.getFrame().addWarning("out-of-range", Util.displayFloat(opac));
          return ((opac<0)?0:1);
       }
       else return(opac);
   }

   private float clampedValueRGB(ApplContext ac, float rgb) {
       if (rgb < 0 || rgb > 255) {
          ac.getFrame().addWarning("out-of-range", Util.displayFloat(rgb));
          return ((rgb<0)?0:255);
       }
       else return(rgb);
   }

    /**
     * Gets the red component of this color.
     */
    public Object getRed() {
	return rgb.r;
    }

    /**
     * Gets the green component of this color.
     */
    public Object getGreen() {
	return rgb.g;
    }

    /**
     * Gets the blue component of this color.
     */
    public Object getBlue() {
	return rgb.b;
    }

    static {
		definedColors = new Hashtable();
		deprecatedColors = new Hashtable();

		definedColors.put("aliceblue",
              new RGB(new Integer(240),
	          new Integer(248),
	          new Integer(255)));
		definedColors.put("antiquewhite",
		      new RGB(new Integer(250),
			  new Integer(235),
			  new Integer(215)));
		definedColors.put("aqua",
			  new RGB(new Integer(0),
			  new Integer(255),
			  new Integer(255)));
		definedColors.put("aquamarine",
			  new RGB(new Integer(127),
			  new Integer(255),
			  new Integer(212)));
		definedColors.put("azure",
			  new RGB(new Integer(240),
			  new Integer(255),
			  new Integer(255)));
		definedColors.put("beige",
			  new RGB(new Integer(245),
			  new Integer(245),
			  new Integer(220)));
		definedColors.put("bisque",
			  new RGB(new Integer(255),
			  new Integer(228),
			  new Integer(196)));
		definedColors.put("black",
			  new RGB(new Integer(0),
			  new Integer(0),
			  new Integer(0)));
		definedColors.put("blanchedalmond",
			  new RGB(new Integer(255),
			  new Integer(235),
			  new Integer(205)));
		definedColors.put("blue",
			  new RGB(new Integer(0),
			  new Integer(0),
			  new Integer(255)));
		definedColors.put("blueviolet",
			  new RGB(new Integer(138),
			  new Integer(43),
			  new Integer(226)));
		definedColors.put("brown",
			  new RGB(new Integer(165),
			  new Integer(42),
			  new Integer(42)));
		definedColors.put("burlywood",
			  new RGB(new Integer(222),
			  new Integer(184),
			  new Integer(135)));
		definedColors.put("cadetBlue",
			  new RGB(new Integer(95),
			  new Integer(158),
			  new Integer(160)));
		definedColors.put("chartreuse",
			  new RGB(new Integer(127),
			  new Integer(255),
			  new Integer(0)));
		definedColors.put("chocolate",
			  new RGB(new Integer(210),
			  new Integer(105),
			  new Integer(30)));
		definedColors.put("coral",
			  new RGB(new Integer(255),
			  new Integer(127),
			  new Integer(80)));
		definedColors.put("cornflowerblue",
			  new RGB(new Integer(100),
			  new Integer(149),
			  new Integer(237)));
		definedColors.put("cornsilk",
			  new RGB(new Integer(255),
			  new Integer(248),
			  new Integer(220)));
		definedColors.put("crimson",
			  new RGB(new Integer(220),
			  new Integer(20),
			  new Integer(60)));
		definedColors.put("cyan",
			  new RGB(new Integer(0),
			  new Integer(255),
			  new Integer(255)));
		definedColors.put("darkblue",
			  new RGB(new Integer(0),
	          new Integer(0),
			  new Integer(139)));
		definedColors.put("darkcyan",
			  new RGB(new Integer(0),
			  new Integer(139),
			  new Integer(139)));
		definedColors.put("darkgoldenrod",
			  new RGB(new Integer(184),
			  new Integer(134),
			  new Integer(11)));
		definedColors.put("darkgray",
			  new RGB(new Integer(169),
			  new Integer(169),
			  new Integer(169)));
		definedColors.put("darkgreen",
			  new RGB(new Integer(0),
			  new Integer(100),
			  new Integer(0)));
		definedColors.put("darkkhaki",
			  new RGB(new Integer(189),
			  new Integer(183),
			  new Integer(107)));
		definedColors.put("darkmagenta",
			  new RGB(new Integer(139),
			  new Integer(0),
			  new Integer(139)));
		definedColors.put("darkolivegreen",
			  new RGB(new Integer(85),
			  new Integer(107),
			  new Integer(47)));
		definedColors.put("darkorange",
			  new RGB(new Integer(255),
			  new Integer(140),
			  new Integer(0)));
		definedColors.put("darkorchid",
			  new RGB(new Integer(153),
			  new Integer(50),
			  new Integer(204)));
		definedColors.put("darkred",
			  new RGB(new Integer(139),
			  new Integer(0),
			  new Integer(0)));
		definedColors.put("darksalmon",
			  new RGB(new Integer(233),
			  new Integer(150),
			  new Integer(122)));
		definedColors.put("darkseagreen",
			  new RGB(new Integer(143),
			  new Integer(188),
			  new Integer(143)));
		definedColors.put("darkslateblue",
			  new RGB(new Integer(72),
			  new Integer(61),
			  new Integer(139)));
		definedColors.put("darkslategray",
			  new RGB(new Integer(47),
			  new Integer(79),
			  new Integer(79)));
		definedColors.put("darkturquoise",
			  new RGB(new Integer(0),
			  new Integer(206),
			  new Integer(209)));
		definedColors.put("darkviolet",
			  new RGB(new Integer(148),
			  new Integer(0),
			  new Integer(211)));
		definedColors.put("deeppink",
			  new RGB(new Integer(255),
			  new Integer(20),
			  new Integer(147)));
		definedColors.put("deepskyblue",
			  new RGB(new Integer(0),
			  new Integer(191),
			  new Integer(255)));
		definedColors.put("dimgray",
			  new RGB(new Integer(105),
			  new Integer(105),
			  new Integer(105)));
		definedColors.put("dodgerblue",
			  new RGB(new Integer(30),
			  new Integer(144),
			  new Integer(255)));
		definedColors.put("firebrick",
			  new RGB(new Integer(178),
			  new Integer(34),
			  new Integer(34)));
		definedColors.put("floralwhite",
			  new RGB(new Integer(255),
			  new Integer(250),
			  new Integer(240)));
		definedColors.put("forestgreen",
			  new RGB(new Integer(34),
			  new Integer(139),
			  new Integer(34)));
		definedColors.put("fuchsia",
			  new RGB(new Integer(255),
			  new Integer(0),
			  new Integer(255)));
		definedColors.put("gainsboro",
			  new RGB(new Integer(220),
			  new Integer(220),
			  new Integer(220)));
		definedColors.put("ghostwhite",
			  new RGB(new Integer(248),
			  new Integer(248),
			  new Integer(255)));
		definedColors.put("gold",
			  new RGB(new Integer(255),
			  new Integer(215),
			  new Integer(0)));
		definedColors.put("goldenrod",
			  new RGB(new Integer(218),
			  new Integer(165),
			  new Integer(32)));
		definedColors.put("gray",
			  new RGB(new Integer(128),
			  new Integer(128),
			  new Integer(128)));
		definedColors.put("green",
			  new RGB(new Integer(0),
			  new Integer(128),
			  new Integer(0)));
		definedColors.put("greenyellow",
			  new RGB(new Integer(173),
			  new Integer(255),
			  new Integer(47)));
		definedColors.put("honeydew",
			  new RGB(new Integer(240),
			  new Integer(255),
			  new Integer(240)));
		definedColors.put("hotpink",
			  new RGB(new Integer(255),
			  new Integer(105),
			  new Integer(180)));
		definedColors.put("indianred",
			  new RGB(new Integer(205),
			  new Integer(92),
			  new Integer(92)));
		definedColors.put("indigo",
			  new RGB(new Integer(75),
			  new Integer(0),
			  new Integer(130)));
		definedColors.put("ivory",
			  new RGB(new Integer(255),
			  new Integer(255),
			  new Integer(240)));
		definedColors.put("khaki",
			  new RGB(new Integer(240),
			  new Integer(230),
			  new Integer(140)));
		definedColors.put("lavender",
			  new RGB(new Integer(230),
			  new Integer(230),
			  new Integer(250)));
		definedColors.put("lavenderblush",
			  new RGB(new Integer(255),
			  new Integer(240),
			  new Integer(245)));
		definedColors.put("lawngreen",
			  new RGB(new Integer(124),
			  new Integer(252),
			  new Integer(0)));
		definedColors.put("lemonchiffon",
			  new RGB(new Integer(255),
			  new Integer(250),
			  new Integer(205)));
		definedColors.put("lightblue",
			  new RGB(new Integer(173),
			  new Integer(216),
			  new Integer(230)));
		definedColors.put("lightcoral",
			  new RGB(new Integer(240),
			  new Integer(128),
			  new Integer(128)));
		definedColors.put("lightcyan",
			  new RGB(new Integer(224),
			  new Integer(255),
			  new Integer(255)));
		definedColors.put("lightgoldenrodyellow",
			  new RGB(new Integer(250),
			  new Integer(250),
			  new Integer(210)));
		definedColors.put("lightgreen",
			  new RGB(new Integer(144),
			  new Integer(238),
			  new Integer(144)));
		definedColors.put("lightgrey",
			  new RGB(new Integer(211),
			  new Integer(211),
			  new Integer(211)));
		definedColors.put("lightpink",
			  new RGB(new Integer(255),
			  new Integer(182),
			  new Integer(193)));
		definedColors.put("lightsalmon",
			  new RGB(new Integer(255),
			  new Integer(160),
			  new Integer(122)));
		definedColors.put("lightseagreen",
			  new RGB(new Integer(32),
			  new Integer(178),
			  new Integer(170)));
		definedColors.put("lightskyblue",
			  new RGB(new Integer(135),
			  new Integer(206),
			  new Integer(250)));
		definedColors.put("lightslategray",
			  new RGB(new Integer(119),
			  new Integer(136),
			  new Integer(153)));
		definedColors.put("lightsteelblue",
			  new RGB(new Integer(176),
			  new Integer(196),
			  new Integer(222)));
		definedColors.put("lightyellow",
			  new RGB(new Integer(255),
			  new Integer(255),
			  new Integer(224)));
		definedColors.put("lime",
			  new RGB(new Integer(0),
			  new Integer(255),
			  new Integer(0)));
		definedColors.put("limegreen",
			  new RGB(new Integer(50),
			  new Integer(205),
			  new Integer(50)));
		definedColors.put("linen",
			  new RGB(new Integer(250),
			  new Integer(240),
			  new Integer(230)));
		definedColors.put("magenta",
			  new RGB(new Integer(255),
			  new Integer(0),
			  new Integer(255)));
		definedColors.put("maroon",
			  new RGB(new Integer(128),
			  new Integer(0),
			  new Integer(0)));
		definedColors.put("mediumaquamarine",
			  new RGB(new Integer(102),
			  new Integer(205),
			  new Integer(170)));
		definedColors.put("mediumblue",
			  new RGB(new Integer(0),
			  new Integer(0),
			  new Integer(205)));
		definedColors.put("mediumorchid",
			  new RGB(new Integer(186),
			  new Integer(85),
			  new Integer(211)));
		definedColors.put("mediumpurple",
			  new RGB(new Integer(147),
			  new Integer(112),
			  new Integer(219)));
		definedColors.put("mediumseagreen",
			  new RGB(new Integer(60),
			  new Integer(179),
			  new Integer(113)));
		definedColors.put("mediumslateblue",
			  new RGB(new Integer(123),
			  new Integer(104),
			  new Integer(238)));
		definedColors.put("mediumspringgreen",
			  new RGB(new Integer(0),
			  new Integer(250),
			  new Integer(154)));
		definedColors.put("mediumturquoise",
			  new RGB(new Integer(72),
			  new Integer(209),
			  new Integer(204)));
		definedColors.put("mediumvioletred",
			  new RGB(new Integer(199),
			  new Integer(21),
			  new Integer(133)));
		definedColors.put("midnightblue",
			  new RGB(new Integer(25),
			  new Integer(25),
			  new Integer(112)));
		definedColors.put("mintcream",
			  new RGB(new Integer(245),
			  new Integer(255),
			  new Integer(250)));
		definedColors.put("mistyrose",
			  new RGB(new Integer(255),
			  new Integer(228),
			  new Integer(225)));
		definedColors.put("moccasin",
			  new RGB(new Integer(255),
			  new Integer(228),
			  new Integer(181)));
		definedColors.put("navajowhite",
			  new RGB(new Integer(255),
			  new Integer(222),
			  new Integer(173)));
		definedColors.put("navy",
			  new RGB(new Integer(0),
			  new Integer(0),
			  new Integer(128)));
		definedColors.put("oldlace",
			  new RGB(new Integer(253),
			  new Integer(245),
			  new Integer(230)));
		definedColors.put("olive",
			  new RGB(new Integer(128),
			  new Integer(128),
			  new Integer(0)));
		definedColors.put("olivedrab",
			  new RGB(new Integer(107),
			  new Integer(142),
			  new Integer(35)));
		definedColors.put("orange",
			  new RGB(new Integer(255),
			  new Integer(165),
			  new Integer(0)));
		definedColors.put("orangered",
			  new RGB(new Integer(255),
			  new Integer(69),
			  new Integer(0)));
		definedColors.put("orchid",
			  new RGB(new Integer(218),
			  new Integer(112),
			  new Integer(214)));
		definedColors.put("palegoldenrod",
			  new RGB(new Integer(238),
			  new Integer(232),
			  new Integer(170)));
		definedColors.put("palegreen",
			  new RGB(new Integer(152),
			  new Integer(251),
			  new Integer(152)));
		definedColors.put("paleturquoise",
			  new RGB(new Integer(175),
			  new Integer(238),
			  new Integer(238)));
		definedColors.put("palevioletred",
			  new RGB(new Integer(219),
			  new Integer(112),
			  new Integer(147)));
		definedColors.put("papayawhip",
			  new RGB(new Integer(255),
			  new Integer(239),
			  new Integer(213)));
		definedColors.put("peachpuff",
			  new RGB(new Integer(255),
			  new Integer(218),
			  new Integer(185)));
		definedColors.put("peru",
			  new RGB(new Integer(205),
			  new Integer(133),
			  new Integer(63)));
		definedColors.put("pink",
			  new RGB(new Integer(255),
			  new Integer(192),
			  new Integer(203)));
		definedColors.put("plum",
			  new RGB(new Integer(221),
			  new Integer(160),
			  new Integer(221)));
		definedColors.put("powderBlue",
			  new RGB(new Integer(176),
			  new Integer(224),
			  new Integer(230)));
		definedColors.put("purple",
			  new RGB(new Integer(128),
			  new Integer(0),
			  new Integer(128)));
		definedColors.put("red",
			  new RGB(new Integer(255),
			  new Integer(0),
			  new Integer(0)));
		definedColors.put("rosybrown",
			  new RGB(new Integer(188),
			  new Integer(143),
			  new Integer(143)));
		definedColors.put("royalblue",
			  new RGB(new Integer(65),
			  new Integer(105),
			  new Integer(225)));
		definedColors.put("saddlebrown",
			  new RGB(new Integer(139),
			  new Integer(69),
			  new Integer(19)));
		definedColors.put("salmon",
			  new RGB(new Integer(250),
			  new Integer(128),
			  new Integer(114)));
		definedColors.put("sandybrown",
			  new RGB(new Integer(244),
			  new Integer(164),
			  new Integer(96)));
		definedColors.put("seagreen",
			  new RGB(new Integer(46),
			  new Integer(139),
			  new Integer(87)));
		definedColors.put("seashell",
			  new RGB(new Integer(255),
			  new Integer(245),
			  new Integer(238)));
		definedColors.put("sienna",
			  new RGB(new Integer(160),
			  new Integer(82),
			  new Integer(45)));
		definedColors.put("silver",
			  new RGB(new Integer(192),
			  new Integer(192),
			  new Integer(192)));
		definedColors.put("skyblue",
			  new RGB(new Integer(135),
			  new Integer(206),
			  new Integer(235)));
		definedColors.put("slateblue",
			  new RGB(new Integer(106),
			  new Integer(90),
			  new Integer(205)));
		definedColors.put("slategray",
			  new RGB(new Integer(112),
			  new Integer(128),
			  new Integer(144)));
		definedColors.put("snow",
			  new RGB(new Integer(255),
			  new Integer(250),
			  new Integer(250)));
		definedColors.put("springgreen",
			  new RGB(new Integer(0),
			  new Integer(255),
			  new Integer(127)));
		definedColors.put("steelblue",
			  new RGB(new Integer(70),
			  new Integer(130),
			  new Integer(180)));
		definedColors.put("tan",
			  new RGB(new Integer(210),
			  new Integer(180),
			  new Integer(140)));
		definedColors.put("teal",
			  new RGB(new Integer(0),
			  new Integer(128),
			  new Integer(128)));
		definedColors.put("thistle",
			  new RGB(new Integer(216),
			  new Integer(191),
			  new Integer(216)));
		definedColors.put("tomato",
			  new RGB(new Integer(255),
			  new Integer(99),
			  new Integer(71)));
		definedColors.put("turquoise",
			  new RGB(new Integer(64),
			  new Integer(224),
			  new Integer(208)));
		definedColors.put("violet",
			  new RGB(new Integer(238),
			  new Integer(130),
			  new Integer(238)));
		definedColors.put("wheat",
			  new RGB(new Integer(245),
			  new Integer(222),
			  new Integer(179)));
		definedColors.put("white",
			  new RGB(new Integer(255),
			  new Integer(255),
			  new Integer(255)));
		definedColors.put("whitesmoke",
			  new RGB(new Integer(245),
			  new Integer(245),
			  new Integer(245)));
		definedColors.put("yellow",
		  	  new RGB(new Integer(255),
			  new Integer(255),
			  new Integer(0)));
    	definedColors.put("yellowgreen",
		  	  new RGB(new Integer(154),
			  new Integer(205),
			  new Integer(50)));

		definedColors.put("grey",
			  new RGB(new Integer(128),
			  new Integer(128),
			  new Integer(128)));
    	definedColors.put("darkslategrey",
		  	  new RGB(new Integer(47),
			  new Integer(79),
			  new Integer(79)));
		definedColors.put("dimgrey",
			  new RGB(new Integer(105),
			  new Integer(105),
			  new Integer(105)));
		definedColors.put("lightgray",
			  new RGB(new Integer(211),
			  new Integer(211),
			  new Integer(211)));
		definedColors.put("lightslategrey",
			  new RGB(new Integer(119),
			  new Integer(136),
			  new Integer(153)));
		definedColors.put("slategrey",
			  new RGB(new Integer(112),
			  new Integer(128),
			  new Integer(144)));

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
    	definedColors.put("flavor","flavor");
    	definedColors.put("currentcolor","currentColor");

    }

}

