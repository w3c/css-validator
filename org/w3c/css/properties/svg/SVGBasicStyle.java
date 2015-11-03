//
// $Id$
// From Sijtsche de Jong
//
// COPYRIGHT (c) 1995-2002 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

public class SVGBasicStyle extends SVGTinyStyle {

    AlignmentBaseline alignmentBaseline;
    ClipPath clipPath;
    ClipRule clipRule;
    ColorInterpolation colorInterpolation;
    ColorRendering colorRendering;
    EnableBackground enableBackground;
    WritingModeSVG writingModeSVG;
    FloodOpacity floodOpacity;
    FillOpacity fillOpacity;
    ImageRendering imageRendering;
    Mask mask;
    StopOpacity stopOpacity;
    Kerning kerning;
    PointerEvents pointerEvents;
    ShapeRendering shapeRendering;
    TextRendering textRendering;
    TextAnchor textAnchor;
    StrokeOpacity strokeOpacity;
    StopColor stopColor;
    SolidColor solidColor;
    ColorProfile colorProfile;
    DominantBaseLine dominantBaseLine;
    SolidOpacity solidOpacity;

    public AlignmentBaseline getAlignmentBaseline() {
	if (alignmentBaseline == null) {
	    alignmentBaseline =
		(AlignmentBaseline) style.CascadingOrder (
		    new AlignmentBaseline(), style, selector);
	}
	return alignmentBaseline;
    }

    public DominantBaseLine getDominantBaseLineSVG() {
	if (dominantBaseLine == null) {
	    dominantBaseLine =
		(DominantBaseLine) style.CascadingOrder (
		    new DominantBaseLine(), style, selector);
	}
	return dominantBaseLine;
    }

    public ClipPath getClipPath() {
	if (clipPath == null) {
	    clipPath =
		(ClipPath) style.CascadingOrder (
		    new ClipPath(), style, selector);
	}
	return clipPath;
    }

    public ClipRule getClipRule() {
	if (clipRule == null) {
	    clipRule =
		(ClipRule) style.CascadingOrder (
		    new ClipRule(), style, selector);
	}
	return clipRule;
    }

    public ColorInterpolation getColorInterpolation() {
	if (colorInterpolation == null) {
	    colorInterpolation =
		(ColorInterpolation) style.CascadingOrder (
		    new ColorInterpolation(), style, selector);
	}
	return colorInterpolation;
    }

    public ColorRendering getColorRendering() {
	if (colorRendering == null) {
	    colorRendering =
		(ColorRendering) style.CascadingOrder (
		    new ColorRendering(), style, selector);
	}
	return colorRendering;
    }

    public EnableBackground getEnableBackground() {
	if (enableBackground == null) {
	    enableBackground =
		(EnableBackground) style.CascadingOrder (
		    new EnableBackground(), style, selector);
	}
	return enableBackground;
    }

    public WritingModeSVG getWritingModeSVG() {
	if (writingModeSVG == null) {
	    writingModeSVG =
		(WritingModeSVG) style.CascadingOrder (
		    new WritingModeSVG(), style, selector);
	}
	return writingModeSVG;
    }

    public FloodOpacity getFloodOpacity() {
	if (floodOpacity == null) {
	    floodOpacity =
		(FloodOpacity) style.CascadingOrder (
		    new FloodOpacity(), style, selector);
	}
	return floodOpacity;
    }

    public FillOpacity getFillOpacity() {
	if (fillOpacity == null) {
	    fillOpacity =
		(FillOpacity) style.CascadingOrder (
		    new FillOpacity(), style, selector);
	}
	return fillOpacity;
    }

    public ImageRendering getImageRendering() {
	if (imageRendering == null) {
	    imageRendering =
		(ImageRendering) style.CascadingOrder (
		    new ImageRendering(), style, selector);
	}
	return imageRendering;
    }

    public Mask getMask() {
	if (mask == null) {
	    mask =
		(Mask) style.CascadingOrder (
		    new Mask(), style, selector);
	}
	return mask;
    }

    public StopOpacity getStopOpacity() {
	if (stopOpacity == null) {
	    stopOpacity =
		(StopOpacity) style.CascadingOrder (
		    new StopOpacity(), style, selector);
	}
	return stopOpacity;
    }

    public Kerning getKerning() {
	if (kerning == null) {
	    kerning =
		(Kerning) style.CascadingOrder (
		    new Kerning(), style, selector);
	}
	return kerning;
    }

    public PointerEvents getPointerEvents() {
	if (pointerEvents == null) {
	    pointerEvents =
		(PointerEvents) style.CascadingOrder (
		    new PointerEvents(), style, selector);
	}
	return pointerEvents;
    }

    public ShapeRendering getShapeRendering() {
	if (shapeRendering == null) {
	    shapeRendering =
		(ShapeRendering) style.CascadingOrder (
		    new ShapeRendering(), style, selector);
	}
	return shapeRendering;
    }

    public TextRendering getTextRendering() {
	if (textRendering == null) {
	    textRendering =
		(TextRendering) style.CascadingOrder (
		    new TextRendering(), style, selector);
	}
	return textRendering;
    }

    public TextAnchor getTextAnchor() {
	if (textAnchor == null) {
	    textAnchor =
		(TextAnchor) style.CascadingOrder (
		    new TextAnchor(), style, selector);
	}
	return textAnchor;
    }

    public StrokeOpacity getStrokeOpacity() {
	if (strokeOpacity == null) {
	    strokeOpacity =
		(StrokeOpacity) style.CascadingOrder (
		    new StrokeOpacity(), style, selector);
	}
	return strokeOpacity;
    }

    public StopColor getStopColor() {
	if (stopColor == null) {
	    stopColor =
		(StopColor) style.CascadingOrder (
		    new StopColor(), style, selector);
	}
	return stopColor;
    }

    public SolidColor getSolidColor() {
	if (solidColor == null) {
	    solidColor =
		(SolidColor) style.CascadingOrder (
		    new SolidColor(), style, selector);
	}
	return solidColor;
    }

    public ColorProfile getColorProfileSVG() {
	if (colorProfile == null) {
	    colorProfile =
		(ColorProfile) style.CascadingOrder (
		    new ColorProfile(), style, selector);
	}
	return colorProfile;
    }

    public SolidOpacity getSolidOpacity() {
	if (solidOpacity == null) {
	    solidOpacity =
		(SolidOpacity) style.CascadingOrder (
		    new SolidOpacity(), style, selector);
	}
	return solidOpacity;
    }

}
