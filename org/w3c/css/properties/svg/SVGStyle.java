//
// $Id$
// From Sijtsche de Jong
//
// COPYRIGHT (c) 1995-2002 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.parser.CssPrinterStyle;

public class SVGStyle extends SVGBasicStyle {

    Marker marker;
    MarkerStart markerStart;
    MarkerEnd markerEnd;
    MarkerMid markerMid;
    LightingColor lightingColor;
    ColorProfileSrc cpSrc;
    ColorProfileName cpName;
    CssRenderIntent cssRenderIntent;

    public Marker getMarker() {
	if (marker == null) {
	    marker =
		(Marker) style.CascadingOrder (
			new Marker(), style, selector);
	}
	return marker;
    }

    public MarkerStart getMarkerStart() {
	if (markerStart == null) {
	    markerStart =
		(MarkerStart) style.CascadingOrder (
			new MarkerStart(), style, selector);
	}
	return markerStart;
    }

    public MarkerEnd getMarkerEnd() {
	if (markerEnd == null) {
	    markerEnd =
		(MarkerEnd) style.CascadingOrder (
			new MarkerEnd(), style, selector);
	}
	return markerEnd;
    }

    public MarkerMid getMarkerMid() {
	if (markerMid == null) {
	    markerMid =
		(MarkerMid) style.CascadingOrder (
			new MarkerMid(), style, selector);
	}
	return markerMid;
    }

    public LightingColor getLightingColor() {
	if (lightingColor == null) {
	    lightingColor =
		(LightingColor) style.CascadingOrder(
			new LightingColor(), style, selector);
	}
	return lightingColor;
    }

    public ColorProfileSrc getColorProfileSrc() {
	if (cpSrc == null) {
	    cpSrc =
		(ColorProfileSrc) style.CascadingOrder (
			new ColorProfileSrc(), style, selector);
	}
	return cpSrc;
    }

    public ColorProfileName getColorProfileName() {
	if (cpName == null) {
	    cpName =
		(ColorProfileName) style.CascadingOrder (
			new ColorProfileName(), style, selector);
	}
	return cpName;
    }

    public CssRenderIntent getCssRenderIntent() {
	if (cssRenderIntent == null) {
	    cssRenderIntent =
		(CssRenderIntent) style.CascadingOrder (
			new CssRenderIntent(), style, selector);
	}
	return cssRenderIntent;
    }

    /**
     * Print this style
     *
     * @param printer The printer interface
     */
    public void print(CssPrinterStyle printer) {
	super.print(printer);

	if (marker != null) {
	    marker.print(printer);
	}
	if (markerStart != null) {
	    markerStart.print(printer);
	}
	if (markerEnd != null) {
	    markerEnd.print(printer);
	}
	if (markerMid != null) {
	    markerMid.print(printer);
	}
	if (lightingColor != null) {
	    lightingColor.print(printer);
	}
	if (cpSrc != null) {
	    cpSrc.print(printer);
	}
	if (cpName != null) {
	    cpName.print(printer);
	}
	if (cssRenderIntent != null) {
	    cssRenderIntent.print(printer);
	}
    }
}
