//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css2;

import org.w3c.css.properties.aural.ACssStyle;
import org.w3c.css.properties.css.CssTextShadow;

/**
 * @version $Revision$
 */
public class Css2Style extends ACssStyle {

    /**
     * aural properties
     */
    public org.w3c.css.properties.css.CssAzimuth cssAzimuth;
    public org.w3c.css.properties.css.CssElevation cssElevation;

    /**
     * font properties
     */
	public org.w3c.css.properties.css.CssFontStretch cssFontStretch;
	public org.w3c.css.properties.css.CssFontSizeAdjust cssFontSizeAdjust;

	/**
	 * text properties
	 */
	public CssTextShadow cssTextShadow;

	/**
     * Get the azimuth
     */
    public org.w3c.css.properties.css.CssAzimuth getAzimuth() {
        if (cssAzimuth == null) {
            cssAzimuth = (org.w3c.css.properties.css.CssAzimuth) style.CascadingOrder(new org.w3c.css.properties.css.CssAzimuth(),
                    style, selector);
        }
        return cssAzimuth;
    }

    /**
     * Get the elevation
     */
    public org.w3c.css.properties.css.CssElevation getElevation() {
        if (cssElevation == null) {
            cssElevation = (org.w3c.css.properties.css.CssElevation) style.CascadingOrder(new org.w3c.css.properties.css.CssElevation(),
                    style, selector);
        }
        return cssElevation;
    }

    /**
     * Get the border-top-style property
     */
    public final org.w3c.css.properties.css.CssBorderTopStyle getBorderTopStyle() {
        if (cssBorder.borderStyle.top == null) {
            cssBorder.borderStyle.top =
                    (org.w3c.css.properties.css.CssBorderTopStyle) style.CascadingOrder(new org.w3c.css.properties.css.CssBorderTopStyle(),
                            style, selector);
        }
        return cssBorder.borderStyle.top;
    }

    /**
     * Get the border-right-style property
     */
    public final org.w3c.css.properties.css.CssBorderRightStyle getBorderRightStyle() {
        if (cssBorder.borderStyle.right == null) {
            cssBorder.borderStyle.right =
                    (org.w3c.css.properties.css.CssBorderRightStyle) style.CascadingOrder(new org.w3c.css.properties.css.CssBorderRightStyle(),
                            style, selector);
        }
        return cssBorder.borderStyle.right;
    }

    /**
     * Get the border-bottom-style property
     */
    public final org.w3c.css.properties.css.CssBorderBottomStyle getBorderBottomStyle() {
        if (cssBorder.borderStyle.bottom == null) {
            cssBorder.borderStyle.bottom =
                    (org.w3c.css.properties.css.CssBorderBottomStyle) style.CascadingOrder(new org.w3c.css.properties.css.CssBorderBottomStyle(),
                            style, selector);
        }
        return cssBorder.borderStyle.bottom;
    }

    /**
     * Get the border-left-style property
     */
    public final org.w3c.css.properties.css.CssBorderLeftStyle getBorderLeftStyle() {
        if (cssBorder.borderStyle.left == null) {
            cssBorder.borderStyle.left =
                    (org.w3c.css.properties.css.CssBorderLeftStyle) style.CascadingOrder(new org.w3c.css.properties.css.CssBorderLeftStyle(),
                            style, selector);
        }
        return cssBorder.borderStyle.left;
    }

	/**
	 * get the font-stretch property
	 * @return a CssFontStretch instance
	 */
	public org.w3c.css.properties.css.CssFontStretch getFontStretch() {
		if (cssFontStretch == null) {
			cssFontStretch = (org.w3c.css.properties.css.CssFontStretch) style.CascadingOrder(new org.w3c.css.properties.css.CssFontStretch(),
					style, selector);
		}
		return cssFontStretch;
	}

	/**
	 * get the font-size-adjust property
	 * @return a CssFontSizeAdjust instance
	 */
	public org.w3c.css.properties.css.CssFontSizeAdjust getFontSizeAdjust() {
		if (cssFontSizeAdjust == null) {
			cssFontSizeAdjust = (org.w3c.css.properties.css.CssFontSizeAdjust) style.CascadingOrder(new org.w3c.css.properties.css.CssFontSizeAdjust(),
					style, selector);
		}
		return cssFontSizeAdjust;
	}

	/**
	 * Get the text-shadow property
	 * @return a CssTextShadow instance
	 */
	public final org.w3c.css.properties.css.CssTextShadow getTextShadow() {
		if (cssTextShadow == null) {
			cssTextShadow =
					(org.w3c.css.properties.css.CssTextShadow) style.CascadingOrder(new org.w3c.css.properties.css.CssTextShadow(),
							style, selector);
		}
		return cssTextShadow;
	}

}
