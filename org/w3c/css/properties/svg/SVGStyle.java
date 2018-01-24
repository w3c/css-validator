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
import org.w3c.css.properties.css.CssMaskClip;
import org.w3c.css.properties.css.CssMaskComposite;
import org.w3c.css.properties.css.CssMaskImage;
import org.w3c.css.properties.css.CssMaskOrigin;
import org.w3c.css.properties.css.colorprofile.CssName;
import org.w3c.css.properties.css.colorprofile.CssRenderingIntent;
import org.w3c.css.properties.css.colorprofile.CssSrc;

public class SVGStyle extends SVGBasicStyle {

    public CssMarkerStart cssMarkerStart;
    public CssMarkerMid cssMarkerMid;
    public CssMarkerEnd cssMarkerEnd;
    public CssMarker cssMarker;

    // @color-profile
    public CssRenderingIntent colorProfileCssRenderingIntent;
    public CssName colorProfileCssName;
    public CssSrc colorProfileCssSrc;

    public CssMaskClip cssMaskClip;
    public CssMaskComposite cssMaskComposite;
    public CssMaskImage cssMaskImage;
    public CssMaskOrigin cssMaskOrigin;


    public CssMaskOrigin getMaskOrigin() {
        if (cssMaskOrigin == null) {
            cssMaskOrigin = (CssMaskOrigin) style.CascadingOrder(new CssMaskOrigin(),
                    style, selector);
        }
        return cssMaskOrigin;
    }

    public CssMaskImage getMaskImage() {
        if (cssMaskImage == null) {
            cssMaskImage = (CssMaskImage) style.CascadingOrder(new CssMaskImage(),
                    style, selector);
        }
        return cssMaskImage;
    }

    public CssMaskComposite getMaskComposite() {
        if (cssMaskComposite == null) {
            cssMaskComposite = (CssMaskComposite) style.CascadingOrder(new CssMaskComposite(),
                    style, selector);
        }
        return cssMaskComposite;
    }

    public CssMaskClip getMaskClip() {
        if (cssMaskClip == null) {
            cssMaskClip = (CssMaskClip) style.CascadingOrder(new CssMaskClip(),
                    style, selector);
        }
        return cssMaskClip;
    }

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

    // @color-profile

    public CssRenderingIntent getColorProfileRenderingIntent() {
        if (colorProfileCssRenderingIntent == null) {
            colorProfileCssRenderingIntent = (CssRenderingIntent) style.CascadingOrder(new CssRenderingIntent(),
                    style, selector);
        }
        return colorProfileCssRenderingIntent;
    }

    public CssName getColorProfileName() {
        if (colorProfileCssName == null) {
            colorProfileCssName = (CssName) style.CascadingOrder(new CssName(),
                    style, selector);
        }
        return colorProfileCssName;
    }

    public CssSrc getColorProfileSrc() {
        if (colorProfileCssSrc == null) {
            colorProfileCssSrc = (CssSrc) style.CascadingOrder(new CssSrc(),
                    style, selector);
        }
        return colorProfileCssSrc;
    }
}
