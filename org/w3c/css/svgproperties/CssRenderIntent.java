//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.svgproperties;

import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.properties.CssProperty;

/**
 * <P>
 * <EM>Value:</EM> auto || perceptual || relative-colorimetric ||
 * saturation || absolute-colorimetric || inherit<BR>
 * <EM>Initial:</EM>auto<BR>
 * <EM>Applies to:</EM>all elements<BR>
 * <EM>Inherited</EM>yes<BR>
 * <EM>Percentages:</EM>no<BR>
 * <EM>Media:</EM>visual
 * <P>
 * This property permits the specification of a color profile rendering intent other than the default.
 * The behavior of values other than auto and inherent are defined by the International Color Consortium standard.
 */

    public class CssRenderIntent extends CssProperty {

	CssValue renderintent;

	static CssIdent auto = new CssIdent("auto");
	static CssIdent abscolorimetric = new CssIdent("absolute-colorimetric");
	static CssIdent relcolorimetric = new CssIdent("relative-colorimetric");
	static CssIdent saturation = new CssIdent("saturation");
	static CssIdent perceptual = new CssIdent("perceptual");

	/**
	 * Create a new CssRenderIntent
	 */
	public CssRenderIntent() {
	    renderintent = auto;
	}

	/**
	 * Create a new CssRenderIntent
	 *
	 *
	 */
	public CssRenderIntent(ApplContext ac, CssExpression expression) throws InvalidParamException {
	    setByUser();
	    CssValue val = expression.getValue();
	    if (val.equals(auto)) {
		renderintent = auto;
		expression.next();
	    }
	    else if (val.equals(perceptual)) {
		renderintent = perceptual;
		expression.next();
	    }
	    else if (val.equals(relcolorimetric)) {
		renderintent = relcolorimetric;
		expression.next();
	    }
	    else if (val.equals(saturation)) {
		renderintent = saturation;
		expression.next();
	    }
	    else if (val.equals(abscolorimetric)) {
		renderintent = abscolorimetric;
		expression.next();
	    }
	    else {
		throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	    }
	}

	/**
	 * Add this property to the CssStyle.
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
	    if (((SVGStyle) style).cssRenderIntent != null)
		style.addRedefinitionWarning(ac, this);
	    ((SVGStyle) style).cssRenderIntent = this;

	}

	/**
	 * Get this property in the style.
	 *
	 * @param style The style where the property is
	 * @param resolve if true, resolve the style to find this property
	 */
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((SVGStyle) style).getRenderIntent();
	    } else {
		return ((SVGStyle) style).cssRenderIntent;
	    }
	}

	/**
	 * Compares two properties for equality.
	 *
	 * @param value The other property.
	 */
	public boolean equals(CssProperty property) {
	    return (property instanceof CssRenderIntent &&
		    renderintent.equals( ((CssRenderIntent) property).renderintent));
	}

	/**
	 * Returns the name of this property
	 */
	public String getPropertyName() {
	    return "rendering-intent";
	}

	/**
	 * Returns the value of this property
	 */
	public Object get() {
	    return renderintent;
	}

	/**
	 * Returns true if this property is "softly" inherited
	 */
	public boolean isSoftlyInherited() {
	    return renderintent.equals(inherit);
	}

	/**
	 * Returns a string representation of the object
	 */
	public String toString() {
	    return renderintent.toString();
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
	    return renderintent == auto;
	}

    }
