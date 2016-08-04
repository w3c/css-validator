//
// $Id$
// From Sijtsche de Jong
//
// COPYRIGHT (c) 1995-2002 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.properties.css.CssClipPath;
import org.w3c.css.properties.css.CssClipRule;
import org.w3c.css.properties.css.CssColorInterpolation;
import org.w3c.css.properties.css.CssColorProfile;
import org.w3c.css.properties.css.CssColorRendering;
import org.w3c.css.properties.css.CssFillOpacity;
import org.w3c.css.properties.css.CssImageRendering;
import org.w3c.css.properties.css.CssKerning;
import org.w3c.css.properties.css.CssMask;
import org.w3c.css.properties.css.CssPointerEvents;
import org.w3c.css.properties.css.CssShapeRendering;
import org.w3c.css.properties.css.CssStopOpacity;
import org.w3c.css.properties.css.CssStrokeOpacity;
import org.w3c.css.properties.css.CssTextAnchor;
import org.w3c.css.properties.css.CssTextRendering;
import org.w3c.css.properties.css.CssWritingMode;

public class SVGBasicStyle extends SVGTinyStyle {

	EnableBackground enableBackground;
	StopColor stopColor;
	
	public CssColorInterpolation cssColorInterpolation;
	public CssColorRendering cssColorRendering;
	public CssShapeRendering cssShapeRendering;
	public CssTextRendering cssTextRendering;
	public CssImageRendering cssImageRendering;
	public CssTextAnchor cssTextAnchor;
	public CssFillOpacity cssFillOpacity;
	public CssStrokeOpacity cssStrokeOpacity;
	public CssKerning cssKerning;
	public CssWritingMode cssWritingMode;
	public CssClipPath cssClipPath;
	public CssClipRule cssClipRule;
	public CssMask cssMask;
	public CssColorProfile cssColorProfile;
	public CssPointerEvents cssPointerEvents;
	public CssStopOpacity cssStopOpacity;
	
	public CssColorInterpolation getColorInterpolation() {
		if (cssColorInterpolation == null) {
			cssColorInterpolation =
					(CssColorInterpolation) style.CascadingOrder(new CssColorInterpolation(),
							style, selector);
		}
		return cssColorInterpolation;
	}

	public CssImageRendering getImageRendering() {
		if (cssImageRendering == null) {
			cssImageRendering =
					(CssImageRendering) style.CascadingOrder(new CssImageRendering(),
							style, selector);
		}
		return cssImageRendering;
	}
	
	public CssColorRendering getColorRendering() {
		if (cssColorRendering == null) {
			cssColorRendering =
					(CssColorRendering) style.CascadingOrder(new CssColorRendering(),
							style, selector);
		}
		return cssColorRendering;
	}

	public CssShapeRendering getShapeRendering() {
		if (cssShapeRendering == null) {
			cssShapeRendering =
					(CssShapeRendering) style.CascadingOrder(new CssShapeRendering(),
							style, selector);
		}
		return cssShapeRendering;
	}

	public CssTextRendering getTextRendering() {
		if (cssTextRendering == null) {
			cssTextRendering =
					(CssTextRendering) style.CascadingOrder(new CssTextRendering(),
							style, selector);
		}
		return cssTextRendering;
	}

	public CssTextAnchor getTextAnchor() {
		if (cssTextAnchor == null) {
			cssTextAnchor =
					(CssTextAnchor) style.CascadingOrder(new CssTextAnchor(),
							style, selector);
		}
		return cssTextAnchor;
	}
	
	public CssFillOpacity getFillOpacity() {
		if (cssFillOpacity == null) {
			cssFillOpacity =
					(CssFillOpacity) style.CascadingOrder(new CssFillOpacity(),
							style, selector);
		}
		return cssFillOpacity;
	}

	public CssStrokeOpacity getStrokeOpacity() {
		if (cssStrokeOpacity == null) {
			cssStrokeOpacity =
					(CssStrokeOpacity) style.CascadingOrder(new CssStrokeOpacity(),
							style, selector);
		}
		return cssStrokeOpacity;
	}

	public CssKerning getKerning() {
		if (cssKerning == null) {
			cssKerning =
					(CssKerning) style.CascadingOrder(new CssKerning(),
							style, selector);
		}
		return cssKerning;
	}

	public CssWritingMode getWritingMode() {
		if (cssWritingMode == null) {
			cssWritingMode =
					(CssWritingMode) style.CascadingOrder(new CssWritingMode(),
							style, selector);
		}
		return cssWritingMode;
	}

	public CssClipPath getClipPath() {
		if (cssClipPath == null) {
			cssClipPath =
					(CssClipPath) style.CascadingOrder(new CssClipPath(),
							style, selector);
		}
		return cssClipPath;
	}

	public CssClipRule getClipRule() {
		if (cssClipRule == null) {
			cssClipRule =
					(CssClipRule) style.CascadingOrder(new CssClipRule(),
							style, selector);
		}
		return cssClipRule;
	}

	public CssMask getMask() {
		if (cssMask == null) {
			cssMask =
					(CssMask) style.CascadingOrder(new CssMask(),
							style, selector);
		}
		return cssMask;
	}

	public CssColorProfile getColorProfile() {
		if (cssColorProfile == null) {
			cssColorProfile =
					(CssColorProfile) style.CascadingOrder(new CssColorProfile(),
							style, selector);
		}
		return cssColorProfile;
	}

	public CssPointerEvents getPointerEvents() {
		if (cssPointerEvents == null) {
			cssPointerEvents =
					(CssPointerEvents) style.CascadingOrder(new CssPointerEvents(),
							style, selector);
		}
		return cssPointerEvents;
	}

	public CssStopOpacity getStopOpacity() {
		if (cssStopOpacity == null) {
			cssStopOpacity =
					(CssStopOpacity) style.CascadingOrder(new CssStopOpacity(),
							style, selector);
		}
		return cssStopOpacity;
	}
	
	
	

	public EnableBackground getEnableBackground() {
		if (enableBackground == null) {
			enableBackground =
					(EnableBackground) style.CascadingOrder(
							new EnableBackground(), style, selector);
		}
		return enableBackground;
	}

	public StopColor getStopColor() {
		if (stopColor == null) {
			stopColor =
					(StopColor) style.CascadingOrder(
							new StopColor(), style, selector);
		}
		return stopColor;
	}

}
