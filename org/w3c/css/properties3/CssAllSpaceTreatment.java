//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

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

    public class CssHangingPunctuation extends CssProperty {

	CssValue hangpunct;

	private static CssIdent none = new CssIdent("none");
	private static CssIdent start = new CssIdent("start");
	private static CssIdent end = new CssIdent("end");
	private static CssIdent both = new CssIdent("both");

	/**
	 * Create a new CssHangingPunctuation
	 */
	public CssHangingPunctuation() {
	    hangpunct = collapse;
	}

	/**
	 * Create a new CssHangingPunctuation
	 *
	 *
	 */
	public CssHangingPunctuation(ApplContext ac, CssExpression expression) throws InvalidParamException {
	    setByUser();
	    CssValue val = expression.getValue();
	    if (val.equals(preserve)) {
		hangpunct = preserve;
		expression.next();
	    }
	    else if (val.equals(collapse)) {
		hangpunct = collapse;
		expression.next();
	    }
	    else if (val.equals(inherit)) {
		hangpunct = inherit;
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
	    if (((Css3Style) style).cssHangingPunctuation != null)
		style.addRedefinitionWarning(ac, this);
	    ((Css3Style) style).cssHangingPunctuation = this;

	}

	/**
	 * Get this property in the style.
	 *
	 * @param style The style where the property is
	 * @param resolve if true, resolve the style to find this property
	 */
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((Css3Style) style).getHangingPunctuation();
	    } else {
		return ((Css3Style) style).cssHangingPunctuation;
	    }
	}

	/**
	 * Compares two properties for equality.
	 *
	 * @param value The other property.
	 */
	public boolean equals(CssProperty property) {
	    return (property instanceof CssHangingPunctuation &&
		    hangpunct.equals( ((CssHangingPunctuation) property).hangpunct));
	}

	/**
	 * Returns the name of this property
	 */
	public String getPropertyName() {
	    return "all-space-treatment";
	}

	/**
	 * Returns the value of this property
	 */
	public Object get() {
	    return hangpunct;
	}

	/**
	 * Returns true if this property is "softly" inherited
	 */
	public boolean isSoftlyInherited() {
	    return hangpunct.equals(inherit);
	}

	/**
	 * Returns a string representation of the object
	 */
	public String toString() {
	    return hangpunct.toString();
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
	    return hangpunct == collapse;
	}

    }
