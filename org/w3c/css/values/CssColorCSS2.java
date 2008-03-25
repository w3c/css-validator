//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import java.util.HashMap;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;

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
public class CssColorCSS2 extends CssColor {

    /**
     * Create a new CssColorCSS2.
     */
    public CssColorCSS2() {
	//color = new CssIdent("");
    }

    /**
     * Create a new CssColorCSS2 with a color name.
     *
     * @param s The name color.
     * @exception InvalidParamException the color is incorrect
     */
    public CssColorCSS2(ApplContext ac, String s) throws InvalidParamException {
	//	setIdentColor(s.toLowerCase(), ac);
	setIdentColor(s, ac);
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
// 2007-05 - this warning on color string capitalization is plain silly, 
// commenting it out-- ot@w3.org
//		if (!obj.equals(s)) {
//		    ac.getFrame().addWarning("color.mixed-capitalization",
//					     s);
//		}
	    }
	    return;
	}

	throw new InvalidParamException("value", s, "color", ac);
    }

    static {
	definedColors = new HashMap<String,Object>();
	definedColors.put("black",
			  new RGB(0, 0, 0));
	definedColors.put("silver",
			  new RGB(192, 192, 192));
	definedColors.put("gray",
			  new RGB(128, 128, 128));
	definedColors.put("white",
			  new RGB(255, 255, 255));
	definedColors.put("maroon",
			  new RGB(128, 0, 0));
	definedColors.put("red",
			  new RGB(255, 0, 0));
	definedColors.put("purple",
			  new RGB(128, 0, 128));
	definedColors.put("fuchsia",
			  new RGB(255, 0, 255));
	definedColors.put("green",
			  new RGB(0, 128, 0));
	definedColors.put("lime",
			  new RGB(0, 255, 0));
	definedColors.put("olive",
			  new RGB(128, 128, 0));
	definedColors.put("yellow",
			  new RGB(255, 255, 0));
	definedColors.put("navy",
			  new RGB(0, 0, 128));
	definedColors.put("blue",
			  new RGB(0, 0, 255));
	definedColors.put("teal",
			  new RGB(0, 128, 128));
	definedColors.put("aqua",
			  new RGB(0, 255, 255));
        definedColors.put("activeborder", "ActiveBorder");
        definedColors.put("activecaption", "ActiveCaption");
        definedColors.put("appworkspace", "AppWorkspace");
        definedColors.put("background", "Background");
        definedColors.put("buttonface", "ButtonFace");
        definedColors.put("buttonhighlight", "ButtonHighlight");
        definedColors.put("buttonshadow", "ButtonShadow");
        definedColors.put("buttontext", "ButtonText");
        definedColors.put("captiontext", "CaptionText");
        definedColors.put("graytext", "GrayText");
        definedColors.put("highlight", "Highlight");
        definedColors.put("highlighttext", "HighlightText");
        definedColors.put("inactiveborder", "InactiveBorder");
        definedColors.put("inactivecaption", "InactiveCaption");
        definedColors.put("inactivecaptiontext", "InactiveCaptionText");
        definedColors.put("infobackground", "InfoBackground");
        definedColors.put("infotext", "InfoText");
        definedColors.put("menu", "Menu");
        definedColors.put("menutext", "MenuText");
        definedColors.put("scrollbar", "Scrollbar");
        definedColors.put("threeddarkshadow", "ThreeDDarkShadow");
        definedColors.put("threedface", "ThreeDFace");
        definedColors.put("threedhighlight", "ThreeDHighlight");
        definedColors.put("threedlightshadow", "ThreeDLightShadow");
        definedColors.put("threedshadow", "ThreeDShadow");
        definedColors.put("window", "Window");
        definedColors.put("windowframe", "WindowFrame");
        definedColors.put("windowtext", "WindowText");
    }

}
