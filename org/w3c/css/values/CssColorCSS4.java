// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import java.util.HashMap;

/**
 * @spec http://dev.w3.org/csswg/css-color/
 */
public class CssColorCSS4 {
	protected static final HashMap<String, RGB> definedRGBColorsCSS4;
	private static final RGBA trans;

	static final CssIdent currentColor = CssIdent.getIdent("currentColor");

	public static RGB getRGB(String ident) {
		return definedRGBColorsCSS4.get(ident);
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
		// http://dev.w3.org/csswg/css-color/#named-colors
		definedRGBColorsCSS4 = new HashMap<String, RGB>();

		definedRGBColorsCSS4.put("aliceblue", new RGB(240, 248, 255));
		definedRGBColorsCSS4.put("antiquewhite", new RGB(250, 235, 215));
		definedRGBColorsCSS4.put("aqua", new RGB(0, 255, 255));
		definedRGBColorsCSS4.put("aquamarine", new RGB(127, 255, 212));
		definedRGBColorsCSS4.put("azure", new RGB(240, 255, 255));
		definedRGBColorsCSS4.put("beige", new RGB(245, 245, 220));
		definedRGBColorsCSS4.put("bisque", new RGB(255, 228, 196));
		definedRGBColorsCSS4.put("black", new RGB(0, 0, 0));
		definedRGBColorsCSS4.put("blanchedalmond", new RGB(255, 235, 205));
		definedRGBColorsCSS4.put("blue", new RGB(0, 0, 255));
		definedRGBColorsCSS4.put("blueviolet", new RGB(138, 43, 226));
		definedRGBColorsCSS4.put("brown", new RGB(165, 42, 42));
		definedRGBColorsCSS4.put("burlywood", new RGB(222, 184, 135));
		definedRGBColorsCSS4.put("cadetblue", new RGB(95, 158, 160));
		definedRGBColorsCSS4.put("chartreuse", new RGB(127, 255, 0));
		definedRGBColorsCSS4.put("chocolate", new RGB(210, 105, 30));
		definedRGBColorsCSS4.put("coral", new RGB(255, 127, 80));
		definedRGBColorsCSS4.put("cornflowerblue", new RGB(100, 149, 237));
		definedRGBColorsCSS4.put("cornsilk", new RGB(255, 248, 220));
		definedRGBColorsCSS4.put("crimson", new RGB(220, 20, 60));
		definedRGBColorsCSS4.put("cyan", new RGB(0, 255, 255));
		definedRGBColorsCSS4.put("darkblue", new RGB(0, 0, 139));
		definedRGBColorsCSS4.put("darkcyan", new RGB(0, 139, 139));
		definedRGBColorsCSS4.put("darkgoldenrod", new RGB(184, 134, 11));
		definedRGBColorsCSS4.put("darkgray", new RGB(169, 169, 169));
		definedRGBColorsCSS4.put("darkgreen", new RGB(0, 100, 0));
		definedRGBColorsCSS4.put("darkgrey", new RGB(169, 169, 169));
		definedRGBColorsCSS4.put("darkkhaki", new RGB(189, 183, 107));
		definedRGBColorsCSS4.put("darkmagenta", new RGB(139, 0, 139));
		definedRGBColorsCSS4.put("darkolivegreen", new RGB(85, 107, 47));
		definedRGBColorsCSS4.put("darkorange", new RGB(255, 140, 0));
		definedRGBColorsCSS4.put("darkorchid", new RGB(153, 50, 204));
		definedRGBColorsCSS4.put("darkred", new RGB(139, 0, 0));
		definedRGBColorsCSS4.put("darksalmon", new RGB(233, 150, 122));
		definedRGBColorsCSS4.put("darkseagreen", new RGB(143, 188, 143));
		definedRGBColorsCSS4.put("darkslateblue", new RGB(72, 61, 139));
		definedRGBColorsCSS4.put("darkslategray", new RGB(47, 79, 79));
		definedRGBColorsCSS4.put("darkslategrey", new RGB(47, 79, 79));
		definedRGBColorsCSS4.put("darkturquoise", new RGB(0, 206, 209));
		definedRGBColorsCSS4.put("darkviolet", new RGB(148, 0, 211));
		definedRGBColorsCSS4.put("deeppink", new RGB(255, 20, 147));
		definedRGBColorsCSS4.put("deepskyblue", new RGB(0, 191, 255));
		definedRGBColorsCSS4.put("dimgray", new RGB(105, 105, 105));
		definedRGBColorsCSS4.put("dimgrey", new RGB(105, 105, 105));
		definedRGBColorsCSS4.put("dodgerblue", new RGB(30, 144, 255));
		definedRGBColorsCSS4.put("firebrick", new RGB(178, 34, 34));
		definedRGBColorsCSS4.put("floralwhite", new RGB(255, 250, 240));
		definedRGBColorsCSS4.put("forestgreen", new RGB(34, 139, 34));
		definedRGBColorsCSS4.put("fuchsia", new RGB(255, 0, 255));
		definedRGBColorsCSS4.put("gainsboro", new RGB(220, 220, 220));
		definedRGBColorsCSS4.put("ghostwhite", new RGB(248, 248, 255));
		definedRGBColorsCSS4.put("gold", new RGB(255, 215, 0));
		definedRGBColorsCSS4.put("goldenrod", new RGB(218, 165, 32));
		definedRGBColorsCSS4.put("gray", new RGB(128, 128, 128));
		definedRGBColorsCSS4.put("green", new RGB(0, 128, 0));
		definedRGBColorsCSS4.put("greenyellow", new RGB(173, 255, 47));
		definedRGBColorsCSS4.put("grey", new RGB(128, 128, 128));
		definedRGBColorsCSS4.put("honeydew", new RGB(240, 255, 240));
		definedRGBColorsCSS4.put("hotpink", new RGB(255, 105, 180));
		definedRGBColorsCSS4.put("indianred", new RGB(205, 92, 92));
		definedRGBColorsCSS4.put("indigo", new RGB(75, 0, 130));
		definedRGBColorsCSS4.put("ivory", new RGB(255, 255, 240));
		definedRGBColorsCSS4.put("khaki", new RGB(240, 230, 140));
		definedRGBColorsCSS4.put("lavender", new RGB(230, 230, 250));
		definedRGBColorsCSS4.put("lavenderblush", new RGB(255, 240, 245));
		definedRGBColorsCSS4.put("lawngreen", new RGB(124, 252, 0));
		definedRGBColorsCSS4.put("lemonchiffon", new RGB(255, 250, 205));
		definedRGBColorsCSS4.put("lightblue", new RGB(173, 216, 230));
		definedRGBColorsCSS4.put("lightcoral", new RGB(240, 128, 128));
		definedRGBColorsCSS4.put("lightcyan", new RGB(224, 255, 255));
		definedRGBColorsCSS4.put("lightgoldenrodyellow", new RGB(250, 250, 210));
		definedRGBColorsCSS4.put("lightgray", new RGB(211, 211, 211));
		definedRGBColorsCSS4.put("lightgreen", new RGB(144, 238, 144));
		definedRGBColorsCSS4.put("lightgrey", new RGB(211, 211, 211));
		definedRGBColorsCSS4.put("lightpink", new RGB(255, 182, 193));
		definedRGBColorsCSS4.put("lightsalmon", new RGB(255, 160, 122));
		definedRGBColorsCSS4.put("lightseagreen", new RGB(32, 178, 170));
		definedRGBColorsCSS4.put("lightskyblue", new RGB(135, 206, 250));
		definedRGBColorsCSS4.put("lightslategray", new RGB(119, 136, 153));
		definedRGBColorsCSS4.put("lightslategrey", new RGB(119, 136, 153));
		definedRGBColorsCSS4.put("lightsteelblue", new RGB(176, 196, 222));
		definedRGBColorsCSS4.put("lightyellow", new RGB(255, 255, 224));
		definedRGBColorsCSS4.put("lime", new RGB(0, 255, 0));
		definedRGBColorsCSS4.put("limegreen", new RGB(50, 205, 50));
		definedRGBColorsCSS4.put("linen", new RGB(250, 240, 230));
		definedRGBColorsCSS4.put("magenta", new RGB(255, 0, 255));
		definedRGBColorsCSS4.put("maroon", new RGB(128, 0, 0));
		definedRGBColorsCSS4.put("mediumaquamarine", new RGB(102, 205, 170));
		definedRGBColorsCSS4.put("mediumblue", new RGB(0, 0, 205));
		definedRGBColorsCSS4.put("mediumorchid", new RGB(186, 85, 211));
		definedRGBColorsCSS4.put("mediumpurple", new RGB(147, 112, 219));
		definedRGBColorsCSS4.put("mediumseagreen", new RGB(60, 179, 113));
		definedRGBColorsCSS4.put("mediumslateblue", new RGB(123, 104, 238));
		definedRGBColorsCSS4.put("mediumspringgreen", new RGB(0, 250, 154));
		definedRGBColorsCSS4.put("mediumturquoise", new RGB(72, 209, 204));
		definedRGBColorsCSS4.put("mediumvioletred", new RGB(199, 21, 133));
		definedRGBColorsCSS4.put("midnightblue", new RGB(25, 25, 112));
		definedRGBColorsCSS4.put("mintcream", new RGB(245, 255, 250));
		definedRGBColorsCSS4.put("mistyrose", new RGB(255, 228, 225));
		definedRGBColorsCSS4.put("moccasin", new RGB(255, 228, 181));
		definedRGBColorsCSS4.put("navajowhite", new RGB(255, 222, 173));
		definedRGBColorsCSS4.put("navy", new RGB(0, 0, 128));
		definedRGBColorsCSS4.put("oldlace", new RGB(253, 245, 230));
		definedRGBColorsCSS4.put("olive", new RGB(128, 128, 0));
		definedRGBColorsCSS4.put("olivedrab", new RGB(107, 142, 35));
		definedRGBColorsCSS4.put("orange", new RGB(255, 165, 0));
		definedRGBColorsCSS4.put("orangered", new RGB(255, 69, 0));
		definedRGBColorsCSS4.put("orchid", new RGB(218, 112, 214));
		definedRGBColorsCSS4.put("palegoldenrod", new RGB(238, 232, 170));
		definedRGBColorsCSS4.put("palegreen", new RGB(152, 251, 152));
		definedRGBColorsCSS4.put("paleturquoise", new RGB(175, 238, 238));
		definedRGBColorsCSS4.put("palevioletred", new RGB(219, 112, 147));
		definedRGBColorsCSS4.put("papayawhip", new RGB(255, 239, 213));
		definedRGBColorsCSS4.put("peachpuff", new RGB(255, 218, 185));
		definedRGBColorsCSS4.put("peru", new RGB(205, 133, 63));
		definedRGBColorsCSS4.put("pink", new RGB(255, 192, 203));
		definedRGBColorsCSS4.put("plum", new RGB(221, 160, 221));
		definedRGBColorsCSS4.put("powderblue", new RGB(176, 224, 230));
		definedRGBColorsCSS4.put("purple", new RGB(128, 0, 128));
		definedRGBColorsCSS4.put("rebeccapurple", new RGB(102, 51, 153));
		definedRGBColorsCSS4.put("red", new RGB(255, 0, 0));
		definedRGBColorsCSS4.put("rosybrown", new RGB(188, 143, 143));
		definedRGBColorsCSS4.put("royalblue", new RGB(65, 105, 225));
		definedRGBColorsCSS4.put("saddlebrown", new RGB(139, 69, 19));
		definedRGBColorsCSS4.put("salmon", new RGB(250, 128, 114));
		definedRGBColorsCSS4.put("sandybrown", new RGB(244, 164, 96));
		definedRGBColorsCSS4.put("seagreen", new RGB(46, 139, 87));
		definedRGBColorsCSS4.put("seashell", new RGB(255, 245, 238));
		definedRGBColorsCSS4.put("sienna", new RGB(160, 82, 45));
		definedRGBColorsCSS4.put("silver", new RGB(192, 192, 192));
		definedRGBColorsCSS4.put("skyblue", new RGB(135, 206, 235));
		definedRGBColorsCSS4.put("slateblue", new RGB(106, 90, 205));
		definedRGBColorsCSS4.put("slategray", new RGB(112, 128, 144));
		definedRGBColorsCSS4.put("slategrey", new RGB(112, 128, 144));
		definedRGBColorsCSS4.put("snow", new RGB(255, 250, 250));
		definedRGBColorsCSS4.put("springgreen", new RGB(0, 255, 127));
		definedRGBColorsCSS4.put("steelblue", new RGB(70, 130, 180));
		definedRGBColorsCSS4.put("tan", new RGB(210, 180, 140));
		definedRGBColorsCSS4.put("teal", new RGB(0, 128, 128));
		definedRGBColorsCSS4.put("thistle", new RGB(216, 191, 216));
		definedRGBColorsCSS4.put("tomato", new RGB(255, 99, 71));
		definedRGBColorsCSS4.put("turquoise", new RGB(64, 224, 208));
		definedRGBColorsCSS4.put("violet", new RGB(238, 130, 238));
		definedRGBColorsCSS4.put("wheat", new RGB(245, 222, 179));
		definedRGBColorsCSS4.put("white", new RGB(255, 255, 255));
		definedRGBColorsCSS4.put("whitesmoke", new RGB(245, 245, 245));
		definedRGBColorsCSS4.put("yellow", new RGB(255, 255, 0));
		definedRGBColorsCSS4.put("yellowgreen", new RGB(154, 205, 50));
	}
}
