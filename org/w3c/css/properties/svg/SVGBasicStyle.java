//
// $Id$
// From Sijtsche de Jong
//
// COPYRIGHT (c) 1995-2002 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.properties.css.CssColorInterpolation;
import org.w3c.css.properties.css.CssColorRendering;
import org.w3c.css.properties.css.CssFillOpacity;
import org.w3c.css.properties.css.CssImageRendering;
import org.w3c.css.properties.css.CssKerning;
import org.w3c.css.properties.css.CssShapeRendering;
import org.w3c.css.properties.css.CssStrokeOpacity;
import org.w3c.css.properties.css.CssTextAnchor;
import org.w3c.css.properties.css.CssTextRendering;
import org.w3c.css.properties.css.CssWritingMode;

public class SVGBasicStyle extends SVGTinyStyle {

	ClipPath clipPath;
	ClipRule clipRule;
	EnableBackground enableBackground;
	Mask mask;
	StopOpacity stopOpacity;
	PointerEvents pointerEvents;
	StopColor stopColor;
	SolidColor solidColor;
	ColorProfile colorProfile;
	SolidOpacity solidOpacity;
	
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
	
	
	
	public ClipPath getClipPath() {
		if (clipPath == null) {
			clipPath =
					(ClipPath) style.CascadingOrder(
							new ClipPath(), style, selector);
		}
		return clipPath;
	}

	public ClipRule getClipRule() {
		if (clipRule == null) {
			clipRule =
					(ClipRule) style.CascadingOrder(
							new ClipRule(), style, selector);
		}
		return clipRule;
	}

	public EnableBackground getEnableBackground() {
		if (enableBackground == null) {
			enableBackground =
					(EnableBackground) style.CascadingOrder(
							new EnableBackground(), style, selector);
		}
		return enableBackground;
	}

	public Mask getMask() {
		if (mask == null) {
			mask =
					(Mask) style.CascadingOrder(
							new Mask(), style, selector);
		}
		return mask;
	}

	public StopOpacity getStopOpacity() {
		if (stopOpacity == null) {
			stopOpacity =
					(StopOpacity) style.CascadingOrder(
							new StopOpacity(), style, selector);
		}
		return stopOpacity;
	}

	public PointerEvents getPointerEvents() {
		if (pointerEvents == null) {
			pointerEvents =
					(PointerEvents) style.CascadingOrder(
							new PointerEvents(), style, selector);
		}
		return pointerEvents;
	}

	public StopColor getStopColor() {
		if (stopColor == null) {
			stopColor =
					(StopColor) style.CascadingOrder(
							new StopColor(), style, selector);
		}
		return stopColor;
	}

	public SolidColor getSolidColor() {
		if (solidColor == null) {
			solidColor =
					(SolidColor) style.CascadingOrder(
							new SolidColor(), style, selector);
		}
		return solidColor;
	}

	public ColorProfile getColorProfileSVG() {
		if (colorProfile == null) {
			colorProfile =
					(ColorProfile) style.CascadingOrder(
							new ColorProfile(), style, selector);
		}
		return colorProfile;
	}

	public SolidOpacity getSolidOpacity() {
		if (solidOpacity == null) {
			solidOpacity =
					(SolidOpacity) style.CascadingOrder(
							new SolidOpacity(), style, selector);
		}
		return solidOpacity;
	}

}
