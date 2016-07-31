//
// $Id$
// From Sijtsche de Jong
//
// COPYRIGHT (c) 1995-2002 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.properties.css.CssMarker;
import org.w3c.css.properties.css.CssMarkerEnd;
import org.w3c.css.properties.css.CssMarkerMid;
import org.w3c.css.properties.css.CssMarkerStart;

public class SVGStyle extends SVGBasicStyle {

	ColorProfileSrc cpSrc;
	ColorProfileName cpName;
	CssRenderIntent cssRenderIntent;

	public CssMarkerStart cssMarkerStart;
	public CssMarkerMid cssMarkerMid;
	public CssMarkerEnd cssMarkerEnd;
	public CssMarker cssMarker;

	public CssMarkerStart getMarkerStart() {
		if (cssMarkerStart == null) {
			cssMarkerStart = (CssMarkerStart) style.CascadingOrder(new CssMarkerStart(),
					style, selector);
		}
		return cssMarkerStart;
	}

	public CssMarkerMid getMarkerMid() {
		if (cssMarkerMid == null) {
			cssMarkerMid = (CssMarkerMid) style.CascadingOrder(new CssMarkerMid(),
					style, selector);
		}
		return cssMarkerMid;
	}

	public CssMarkerEnd getMarkerEnd() {
		if (cssMarkerEnd == null) {
			cssMarkerEnd = (CssMarkerEnd) style.CascadingOrder(new CssMarkerEnd(),
					style, selector);
		}
		return cssMarkerEnd;
	}

	public CssMarker getMarker() {
		if (cssMarker == null) {
			cssMarker = (CssMarker) style.CascadingOrder(new CssMarker(),
					style, selector);
		}
		return cssMarker;
	}


	public ColorProfileSrc getColorProfileSrc() {
		if (cpSrc == null) {
			cpSrc =
					(ColorProfileSrc) style.CascadingOrder(
							new ColorProfileSrc(), style, selector);
		}
		return cpSrc;
	}

	public ColorProfileName getColorProfileName() {
		if (cpName == null) {
			cpName =
					(ColorProfileName) style.CascadingOrder(
							new ColorProfileName(), style, selector);
		}
		return cpName;
	}

	public CssRenderIntent getRenderIntent() {
		if (cssRenderIntent == null) {
			cssRenderIntent =
					(CssRenderIntent) style.CascadingOrder(
							new CssRenderIntent(), style, selector);
		}
		return cssRenderIntent;
	}

}
