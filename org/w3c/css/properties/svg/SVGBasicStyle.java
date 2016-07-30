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
import org.w3c.css.properties.css.CssShapeRendering;
import org.w3c.css.properties.css.CssTextRendering;

public class SVGBasicStyle extends SVGTinyStyle {

	ClipPath clipPath;
	ClipRule clipRule;
	EnableBackground enableBackground;
	WritingModeSVG writingModeSVG;
	FillOpacity fillOpacity;
	ImageRendering imageRendering;
	Mask mask;
	StopOpacity stopOpacity;
	Kerning kerning;
	PointerEvents pointerEvents;
	TextAnchor textAnchor;
	StrokeOpacity strokeOpacity;
	StopColor stopColor;
	SolidColor solidColor;
	ColorProfile colorProfile;
	SolidOpacity solidOpacity;
	
	public CssColorInterpolation cssColorInterpolation;
	public CssColorRendering cssColorRendering;
	public CssShapeRendering cssShapeRendering;
	public CssTextRendering cssTextRendering;

	public CssColorInterpolation getColorInterpolation() {
		if (cssColorInterpolation == null) {
			cssColorInterpolation =
					(CssColorInterpolation) style.CascadingOrder(new CssColorInterpolation(),
							style, selector);
		}
		return cssColorInterpolation;
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

	public WritingModeSVG getWritingModeSVG() {
		if (writingModeSVG == null) {
			writingModeSVG =
					(WritingModeSVG) style.CascadingOrder(
							new WritingModeSVG(), style, selector);
		}
		return writingModeSVG;
	}

	public FillOpacity getFillOpacity() {
		if (fillOpacity == null) {
			fillOpacity =
					(FillOpacity) style.CascadingOrder(
							new FillOpacity(), style, selector);
		}
		return fillOpacity;
	}

	public ImageRendering getImageRendering() {
		if (imageRendering == null) {
			imageRendering =
					(ImageRendering) style.CascadingOrder(
							new ImageRendering(), style, selector);
		}
		return imageRendering;
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

	public Kerning getKerning() {
		if (kerning == null) {
			kerning =
					(Kerning) style.CascadingOrder(
							new Kerning(), style, selector);
		}
		return kerning;
	}

	public PointerEvents getPointerEvents() {
		if (pointerEvents == null) {
			pointerEvents =
					(PointerEvents) style.CascadingOrder(
							new PointerEvents(), style, selector);
		}
		return pointerEvents;
	}

	public TextAnchor getTextAnchor() {
		if (textAnchor == null) {
			textAnchor =
					(TextAnchor) style.CascadingOrder(
							new TextAnchor(), style, selector);
		}
		return textAnchor;
	}

	public StrokeOpacity getStrokeOpacity() {
		if (strokeOpacity == null) {
			strokeOpacity =
					(StrokeOpacity) style.CascadingOrder(
							new StrokeOpacity(), style, selector);
		}
		return strokeOpacity;
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
