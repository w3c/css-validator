//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import java.util.HashMap;

/**
 * <H3>
 * &nbsp;&nbsp; Color units
 * </H3>
 * <p/>
 * A color is a either a keyword or a numerical RGB specification.
 * <p/>
 * The suggested list of keyword color names is: aqua, black, blue, fuchsia,
 * gray, green, lime, maroon, navy, olive, purple, red, silver, teal, white,
 * and yellow. These 16 colors are taken from the Windows VGA palette, and their
 * RGB values are not defined in this specification.
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
public class CssColorCSS2 extends CssColor {

    static HashMap<String, Object> definedColorsCSS2;

    /**
     * Create a new CssColorCSS2.
     */
    public CssColorCSS2() {
        //color = new CssIdent("");
    }

    /**
     * Create a new CssColorCSS2 with a color name.
     *
     * @param ac The context
     * @param s  The name color.
     * @throws InvalidParamException the color is incorrect
     */
    public CssColorCSS2(ApplContext ac, String s) throws InvalidParamException {
        //	setIdentColor(s.toLowerCase(), ac);
        setIdentColor(s, ac);
    }

    /**
     * Parse an ident color.
     *
     * @param s  the color name as a <EM>String<EM>
     * @param ac The context
     * @throws org.w3c.css.util.InvalidParamException
     *          when the color definition is invalid
     */
    private void setIdentColor(String s, ApplContext ac)
            throws InvalidParamException {
        String lower_s = s.toLowerCase();
        if (!computeIdentColor(definedColorsCSS2, lower_s)) {
            throw new InvalidParamException("value", s, "color", ac);
        }
    }

    static {
        definedColorsCSS2 = new HashMap<String, Object>();
        definedColorsCSS2.put("black",
                new RGB(0, 0, 0));
        definedColorsCSS2.put("silver",
                new RGB(192, 192, 192));
        definedColorsCSS2.put("gray",
                new RGB(128, 128, 128));
        definedColorsCSS2.put("white",
                new RGB(255, 255, 255));
        definedColorsCSS2.put("maroon",
                new RGB(128, 0, 0));
        definedColorsCSS2.put("red",
                new RGB(255, 0, 0));
        definedColorsCSS2.put("purple",
                new RGB(128, 0, 128));
        definedColorsCSS2.put("fuchsia",
                new RGB(255, 0, 255));
        definedColorsCSS2.put("green",
                new RGB(0, 128, 0));
        definedColorsCSS2.put("lime",
                new RGB(0, 255, 0));
        definedColorsCSS2.put("olive",
                new RGB(128, 128, 0));
        definedColorsCSS2.put("yellow",
                new RGB(255, 255, 0));
        definedColorsCSS2.put("navy",
                new RGB(0, 0, 128));
        definedColorsCSS2.put("blue",
                new RGB(0, 0, 255));
        definedColorsCSS2.put("teal",
                new RGB(0, 128, 128));
        definedColorsCSS2.put("aqua",
                new RGB(0, 255, 255));
        definedColorsCSS2.put("activeborder", "ActiveBorder");
        definedColorsCSS2.put("activecaption", "ActiveCaption");
        definedColorsCSS2.put("appworkspace", "AppWorkspace");
        definedColorsCSS2.put("background", "Background");
        definedColorsCSS2.put("buttonface", "ButtonFace");
        definedColorsCSS2.put("buttonhighlight", "ButtonHighlight");
        definedColorsCSS2.put("buttonshadow", "ButtonShadow");
        definedColorsCSS2.put("buttontext", "ButtonText");
        definedColorsCSS2.put("captiontext", "CaptionText");
        definedColorsCSS2.put("graytext", "GrayText");
        definedColorsCSS2.put("highlight", "Highlight");
        definedColorsCSS2.put("highlighttext", "HighlightText");
        definedColorsCSS2.put("inactiveborder", "InactiveBorder");
        definedColorsCSS2.put("inactivecaption", "InactiveCaption");
        definedColorsCSS2.put("inactivecaptiontext", "InactiveCaptionText");
        definedColorsCSS2.put("infobackground", "InfoBackground");
        definedColorsCSS2.put("infotext", "InfoText");
        definedColorsCSS2.put("menu", "Menu");
        definedColorsCSS2.put("menutext", "MenuText");
        definedColorsCSS2.put("scrollbar", "Scrollbar");
        definedColorsCSS2.put("threeddarkshadow", "ThreeDDarkShadow");
        definedColorsCSS2.put("threedface", "ThreeDFace");
        definedColorsCSS2.put("threedhighlight", "ThreeDHighlight");
        definedColorsCSS2.put("threedlightshadow", "ThreeDLightShadow");
        definedColorsCSS2.put("threedshadow", "ThreeDShadow");
        definedColorsCSS2.put("window", "Window");
        definedColorsCSS2.put("windowframe", "WindowFrame");
        definedColorsCSS2.put("windowtext", "WindowText");
    }

}
