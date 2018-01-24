// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import java.util.HashMap;

/**
 * @spec https://www.w3.org/TR/2016/WD-css-color-4-20160705/
 */
public class CssColorCSS3 {
    protected static final HashMap<String, RGB> definedRGBColorsCSS3;
    private static final RGBA trans;

    static final CssIdent currentColor = CssIdent.getIdent("currentColor");

    public static RGB getRGB(String ident) {
        return definedRGBColorsCSS3.get(ident);
    }

    public static RGBA getRGBA(String ident) {
        if ("transparent".equalsIgnoreCase(ident)) {
            return trans;
        }
        return null;
    }

    // those are the same as CSS2, let's use that
    // and note that they are deprecated...
    public static String getSystem(String ident) {
        return CssColorCSS2.definedSystemColorsCSS2.get(ident);
    }

    static {
        trans = new RGBA(0, 0, 0, 0.f);
        // https://www.w3.org/TR/2016/WD-css-color-4-20160705/#named-colors
        definedRGBColorsCSS3 = new HashMap<String, RGB>();

        definedRGBColorsCSS3.put("aliceblue", new RGB(240, 248, 255));
        definedRGBColorsCSS3.put("antiquewhite", new RGB(250, 235, 215));
        definedRGBColorsCSS3.put("aqua", new RGB(0, 255, 255));
        definedRGBColorsCSS3.put("aquamarine", new RGB(127, 255, 212));
        definedRGBColorsCSS3.put("azure", new RGB(240, 255, 255));
        definedRGBColorsCSS3.put("beige", new RGB(245, 245, 220));
        definedRGBColorsCSS3.put("bisque", new RGB(255, 228, 196));
        definedRGBColorsCSS3.put("black", new RGB(0, 0, 0));
        definedRGBColorsCSS3.put("blanchedalmond", new RGB(255, 235, 205));
        definedRGBColorsCSS3.put("blue", new RGB(0, 0, 255));
        definedRGBColorsCSS3.put("blueviolet", new RGB(138, 43, 226));
        definedRGBColorsCSS3.put("brown", new RGB(165, 42, 42));
        definedRGBColorsCSS3.put("burlywood", new RGB(222, 184, 135));
        definedRGBColorsCSS3.put("cadetblue", new RGB(95, 158, 160));
        definedRGBColorsCSS3.put("chartreuse", new RGB(127, 255, 0));
        definedRGBColorsCSS3.put("chocolate", new RGB(210, 105, 30));
        definedRGBColorsCSS3.put("coral", new RGB(255, 127, 80));
        definedRGBColorsCSS3.put("cornflowerblue", new RGB(100, 149, 237));
        definedRGBColorsCSS3.put("cornsilk", new RGB(255, 248, 220));
        definedRGBColorsCSS3.put("crimson", new RGB(220, 20, 60));
        definedRGBColorsCSS3.put("cyan", new RGB(0, 255, 255));
        definedRGBColorsCSS3.put("darkblue", new RGB(0, 0, 139));
        definedRGBColorsCSS3.put("darkcyan", new RGB(0, 139, 139));
        definedRGBColorsCSS3.put("darkgoldenrod", new RGB(184, 134, 11));
        definedRGBColorsCSS3.put("darkgray", new RGB(169, 169, 169));
        definedRGBColorsCSS3.put("darkgreen", new RGB(0, 100, 0));
        definedRGBColorsCSS3.put("darkgrey", new RGB(169, 169, 169));
        definedRGBColorsCSS3.put("darkkhaki", new RGB(189, 183, 107));
        definedRGBColorsCSS3.put("darkmagenta", new RGB(139, 0, 139));
        definedRGBColorsCSS3.put("darkolivegreen", new RGB(85, 107, 47));
        definedRGBColorsCSS3.put("darkorange", new RGB(255, 140, 0));
        definedRGBColorsCSS3.put("darkorchid", new RGB(153, 50, 204));
        definedRGBColorsCSS3.put("darkred", new RGB(139, 0, 0));
        definedRGBColorsCSS3.put("darksalmon", new RGB(233, 150, 122));
        definedRGBColorsCSS3.put("darkseagreen", new RGB(143, 188, 143));
        definedRGBColorsCSS3.put("darkslateblue", new RGB(72, 61, 139));
        definedRGBColorsCSS3.put("darkslategray", new RGB(47, 79, 79));
        definedRGBColorsCSS3.put("darkslategrey", new RGB(47, 79, 79));
        definedRGBColorsCSS3.put("darkturquoise", new RGB(0, 206, 209));
        definedRGBColorsCSS3.put("darkviolet", new RGB(148, 0, 211));
        definedRGBColorsCSS3.put("deeppink", new RGB(255, 20, 147));
        definedRGBColorsCSS3.put("deepskyblue", new RGB(0, 191, 255));
        definedRGBColorsCSS3.put("dimgray", new RGB(105, 105, 105));
        definedRGBColorsCSS3.put("dimgrey", new RGB(105, 105, 105));
        definedRGBColorsCSS3.put("dodgerblue", new RGB(30, 144, 255));
        definedRGBColorsCSS3.put("firebrick", new RGB(178, 34, 34));
        definedRGBColorsCSS3.put("floralwhite", new RGB(255, 250, 240));
        definedRGBColorsCSS3.put("forestgreen", new RGB(34, 139, 34));
        definedRGBColorsCSS3.put("fuchsia", new RGB(255, 0, 255));
        definedRGBColorsCSS3.put("gainsboro", new RGB(220, 220, 220));
        definedRGBColorsCSS3.put("ghostwhite", new RGB(248, 248, 255));
        definedRGBColorsCSS3.put("gold", new RGB(255, 215, 0));
        definedRGBColorsCSS3.put("goldenrod", new RGB(218, 165, 32));
        definedRGBColorsCSS3.put("gray", new RGB(128, 128, 128));
        definedRGBColorsCSS3.put("green", new RGB(0, 128, 0));
        definedRGBColorsCSS3.put("greenyellow", new RGB(173, 255, 47));
        definedRGBColorsCSS3.put("grey", new RGB(128, 128, 128));
        definedRGBColorsCSS3.put("honeydew", new RGB(240, 255, 240));
        definedRGBColorsCSS3.put("hotpink", new RGB(255, 105, 180));
        definedRGBColorsCSS3.put("indianred", new RGB(205, 92, 92));
        definedRGBColorsCSS3.put("indigo", new RGB(75, 0, 130));
        definedRGBColorsCSS3.put("ivory", new RGB(255, 255, 240));
        definedRGBColorsCSS3.put("khaki", new RGB(240, 230, 140));
        definedRGBColorsCSS3.put("lavender", new RGB(230, 230, 250));
        definedRGBColorsCSS3.put("lavenderblush", new RGB(255, 240, 245));
        definedRGBColorsCSS3.put("lawngreen", new RGB(124, 252, 0));
        definedRGBColorsCSS3.put("lemonchiffon", new RGB(255, 250, 205));
        definedRGBColorsCSS3.put("lightblue", new RGB(173, 216, 230));
        definedRGBColorsCSS3.put("lightcoral", new RGB(240, 128, 128));
        definedRGBColorsCSS3.put("lightcyan", new RGB(224, 255, 255));
        definedRGBColorsCSS3.put("lightgoldenrodyellow", new RGB(250, 250, 210));
        definedRGBColorsCSS3.put("lightgray", new RGB(211, 211, 211));
        definedRGBColorsCSS3.put("lightgreen", new RGB(144, 238, 144));
        definedRGBColorsCSS3.put("lightgrey", new RGB(211, 211, 211));
        definedRGBColorsCSS3.put("lightpink", new RGB(255, 182, 193));
        definedRGBColorsCSS3.put("lightsalmon", new RGB(255, 160, 122));
        definedRGBColorsCSS3.put("lightseagreen", new RGB(32, 178, 170));
        definedRGBColorsCSS3.put("lightskyblue", new RGB(135, 206, 250));
        definedRGBColorsCSS3.put("lightslategray", new RGB(119, 136, 153));
        definedRGBColorsCSS3.put("lightslategrey", new RGB(119, 136, 153));
        definedRGBColorsCSS3.put("lightsteelblue", new RGB(176, 196, 222));
        definedRGBColorsCSS3.put("lightyellow", new RGB(255, 255, 224));
        definedRGBColorsCSS3.put("lime", new RGB(0, 255, 0));
        definedRGBColorsCSS3.put("limegreen", new RGB(50, 205, 50));
        definedRGBColorsCSS3.put("linen", new RGB(250, 240, 230));
        definedRGBColorsCSS3.put("magenta", new RGB(255, 0, 255));
        definedRGBColorsCSS3.put("maroon", new RGB(128, 0, 0));
        definedRGBColorsCSS3.put("mediumaquamarine", new RGB(102, 205, 170));
        definedRGBColorsCSS3.put("mediumblue", new RGB(0, 0, 205));
        definedRGBColorsCSS3.put("mediumorchid", new RGB(186, 85, 211));
        definedRGBColorsCSS3.put("mediumpurple", new RGB(147, 112, 219));
        definedRGBColorsCSS3.put("mediumseagreen", new RGB(60, 179, 113));
        definedRGBColorsCSS3.put("mediumslateblue", new RGB(123, 104, 238));
        definedRGBColorsCSS3.put("mediumspringgreen", new RGB(0, 250, 154));
        definedRGBColorsCSS3.put("mediumturquoise", new RGB(72, 209, 204));
        definedRGBColorsCSS3.put("mediumvioletred", new RGB(199, 21, 133));
        definedRGBColorsCSS3.put("midnightblue", new RGB(25, 25, 112));
        definedRGBColorsCSS3.put("mintcream", new RGB(245, 255, 250));
        definedRGBColorsCSS3.put("mistyrose", new RGB(255, 228, 225));
        definedRGBColorsCSS3.put("moccasin", new RGB(255, 228, 181));
        definedRGBColorsCSS3.put("navajowhite", new RGB(255, 222, 173));
        definedRGBColorsCSS3.put("navy", new RGB(0, 0, 128));
        definedRGBColorsCSS3.put("oldlace", new RGB(253, 245, 230));
        definedRGBColorsCSS3.put("olive", new RGB(128, 128, 0));
        definedRGBColorsCSS3.put("olivedrab", new RGB(107, 142, 35));
        definedRGBColorsCSS3.put("orange", new RGB(255, 165, 0));
        definedRGBColorsCSS3.put("orangered", new RGB(255, 69, 0));
        definedRGBColorsCSS3.put("orchid", new RGB(218, 112, 214));
        definedRGBColorsCSS3.put("palegoldenrod", new RGB(238, 232, 170));
        definedRGBColorsCSS3.put("palegreen", new RGB(152, 251, 152));
        definedRGBColorsCSS3.put("paleturquoise", new RGB(175, 238, 238));
        definedRGBColorsCSS3.put("palevioletred", new RGB(219, 112, 147));
        definedRGBColorsCSS3.put("papayawhip", new RGB(255, 239, 213));
        definedRGBColorsCSS3.put("peachpuff", new RGB(255, 218, 185));
        definedRGBColorsCSS3.put("peru", new RGB(205, 133, 63));
        definedRGBColorsCSS3.put("pink", new RGB(255, 192, 203));
        definedRGBColorsCSS3.put("plum", new RGB(221, 160, 221));
        definedRGBColorsCSS3.put("powderblue", new RGB(176, 224, 230));
        definedRGBColorsCSS3.put("purple", new RGB(128, 0, 128));
        definedRGBColorsCSS3.put("rebeccapurple", new RGB(102, 51, 153));
        definedRGBColorsCSS3.put("red", new RGB(255, 0, 0));
        definedRGBColorsCSS3.put("rosybrown", new RGB(188, 143, 143));
        definedRGBColorsCSS3.put("royalblue", new RGB(65, 105, 225));
        definedRGBColorsCSS3.put("saddlebrown", new RGB(139, 69, 19));
        definedRGBColorsCSS3.put("salmon", new RGB(250, 128, 114));
        definedRGBColorsCSS3.put("sandybrown", new RGB(244, 164, 96));
        definedRGBColorsCSS3.put("seagreen", new RGB(46, 139, 87));
        definedRGBColorsCSS3.put("seashell", new RGB(255, 245, 238));
        definedRGBColorsCSS3.put("sienna", new RGB(160, 82, 45));
        definedRGBColorsCSS3.put("silver", new RGB(192, 192, 192));
        definedRGBColorsCSS3.put("skyblue", new RGB(135, 206, 235));
        definedRGBColorsCSS3.put("slateblue", new RGB(106, 90, 205));
        definedRGBColorsCSS3.put("slategray", new RGB(112, 128, 144));
        definedRGBColorsCSS3.put("slategrey", new RGB(112, 128, 144));
        definedRGBColorsCSS3.put("snow", new RGB(255, 250, 250));
        definedRGBColorsCSS3.put("springgreen", new RGB(0, 255, 127));
        definedRGBColorsCSS3.put("steelblue", new RGB(70, 130, 180));
        definedRGBColorsCSS3.put("tan", new RGB(210, 180, 140));
        definedRGBColorsCSS3.put("teal", new RGB(0, 128, 128));
        definedRGBColorsCSS3.put("thistle", new RGB(216, 191, 216));
        definedRGBColorsCSS3.put("tomato", new RGB(255, 99, 71));
        definedRGBColorsCSS3.put("turquoise", new RGB(64, 224, 208));
        definedRGBColorsCSS3.put("violet", new RGB(238, 130, 238));
        definedRGBColorsCSS3.put("wheat", new RGB(245, 222, 179));
        definedRGBColorsCSS3.put("white", new RGB(255, 255, 255));
        definedRGBColorsCSS3.put("whitesmoke", new RGB(245, 245, 245));
        definedRGBColorsCSS3.put("yellow", new RGB(255, 255, 0));
        definedRGBColorsCSS3.put("yellowgreen", new RGB(154, 205, 50));
    }
}
