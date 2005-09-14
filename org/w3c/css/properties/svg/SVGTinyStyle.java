//
// $Id$
// From Sijtsche de Jong
//
// COPYRIGHT (c) 1995-2002 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.parser.CssPrinterStyle;

public class SVGTinyStyle extends org.w3c.css.properties.css3.Css3Style {

    FillRule fillRule;
    StrokeLinejoin strokeLinejoin;
    StrokeLineCap strokeLineCap;
    StrokeMiterLimit strokeMiterLimit;
    StrokeWidth strokeWidth;
    StrokeDashOffset strokeDashOffset;
    StrokeDashArray strokeDashArray;
    Stroke stroke;
    Fill fill;

    public FillRule getFillRule() {
	if (fillRule == null) {
	    fillRule =
		(FillRule) style.CascadingOrder (
			new FillRule(), style, selector);
	}
	return fillRule;
    }

    public StrokeLinejoin getStrokeLinejoin() {
	if (strokeLinejoin == null) {
	    strokeLinejoin =
		(StrokeLinejoin) style.CascadingOrder (
			new StrokeLinejoin(), style, selector);
	}
	return strokeLinejoin;
    }

    public StrokeLineCap getStrokeLineCap() {
	if (strokeLineCap == null) {
	    strokeLineCap =
		(StrokeLineCap) style.CascadingOrder (
			new StrokeLineCap(), style, selector);
	}
	return strokeLineCap;
    }

    public StrokeMiterLimit getStrokeMiterLimit() {
	if (strokeMiterLimit == null) {
	    strokeMiterLimit =
		(StrokeMiterLimit) style.CascadingOrder (
			new StrokeMiterLimit(), style, selector);
	}
	return strokeMiterLimit;
    }

    public StrokeWidth getStrokeWidth() {
	if (strokeWidth == null) {
	    strokeWidth =
		(StrokeWidth) style.CascadingOrder (
			new StrokeWidth(), style, selector);
	}
	return strokeWidth;
    }

    public StrokeDashOffset getStrokeDashOffset() {
	if (strokeDashOffset == null) {
	    strokeDashOffset =
		(StrokeDashOffset) style.CascadingOrder (
			new StrokeDashOffset(), style, selector);
	}
	return strokeDashOffset;
    }

    public StrokeDashArray getStrokeDashArray() {
	if (strokeDashArray == null) {
	    strokeDashArray =
		(StrokeDashArray) style.CascadingOrder (
			new StrokeDashArray(), style, selector);
	}
	return strokeDashArray;
    }

    public Stroke getStroke() {
	if (stroke == null) {
	    stroke =
		(Stroke) style.CascadingOrder (
			new Stroke(), style, selector);
	}
	return stroke;
    }

    public Fill getFill() {
	if (fill == null) {
	    fill =
		(Fill) style.CascadingOrder (
			new Fill(), style, selector);
	}
	return fill;
    }

    /**
     * Print this style
     *
     * @param printer The printer interface
     */
    public void print(CssPrinterStyle printer) {
	super.print(printer);

	if (fillRule != null) {
	    fillRule.print(printer);
	}
	if (strokeLinejoin != null) {
	    strokeLinejoin.print(printer);
	}
	if (strokeLineCap != null) {
	    strokeLineCap.print(printer);
	}
	if (strokeMiterLimit != null) {
	    strokeMiterLimit.print(printer);
	}
	if (strokeWidth != null) {
	    strokeWidth.print(printer);
	}
	if (strokeDashOffset != null) {
	    strokeDashOffset.print(printer);
	}
	if (strokeDashArray != null) {
	    strokeDashArray.print(printer);
	}
	if (stroke != null) {
	    stroke.print(printer);
	}
	if (fill != null) {
	    fill.print(printer);
	}
    }

    /**
     * Returns the name of the actual selector
     */
    public String getSelector()
    {
	return (selector.getElement().toLowerCase());
    }

}
