//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *
 */

    public class CssTextOverflowMode extends CssProperty {

	CssValue overflowmode;

	private static CssIdent clip = new CssIdent("clip");
	private static CssIdent ellipsis = new CssIdent("ellipsis");
	private static CssIdent ellipsisword = new CssIdent("ellipsis-word");

	/**
	 * Create a new CssTextOverflowMode
	 */
	public CssTextOverflowMode() {
	    overflowmode = clip;
	}

	/**
	 * Create a new CssTextOverflowMode
	 *
	 *
	 */
	public CssTextOverflowMode(ApplContext ac, CssExpression expression,
		boolean check) throws InvalidParamException {
	    setByUser();
	    CssValue val = expression.getValue();
	    if (val.equals(clip)) {
			overflowmode = clip;
			expression.next();
	    }
	    else if (val.equals(ellipsis)) {
			overflowmode = ellipsis;
			expression.next();
	    }
	    else if (val.equals(ellipsisword)) {
			overflowmode = ellipsisword;
			expression.next();
	    }
	    else if (val.equals(inherit)) {
			overflowmode = inherit;
			expression.next();
	    }

	    else {
		throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	    }
	}

	public CssTextOverflowMode(ApplContext ac, CssExpression expression)
		throws InvalidParamException {
	    this(ac, expression, false);
	}
	
	/**
	 * Add this property to the CssStyle.
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
	    if (((Css3Style) style).cssTextOverflowMode != null)
		style.addRedefinitionWarning(ac, this);
	    ((Css3Style) style).cssTextOverflowMode = this;

	}

	/**
	 * Get this property in the style.
	 *
	 * @param style The style where the property is
	 * @param resolve if true, resolve the style to find this property
	 */
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((Css3Style) style).getTextOverflowMode();
	    } else {
		return ((Css3Style) style).cssTextOverflowMode;
	    }
	}

	/**
	 * Compares two properties for equality.
	 *
	 * @param value The other property.
	 */
	public boolean equals(CssProperty property) {
	    return (property instanceof CssTextOverflowMode &&
		    overflowmode.equals( ((CssTextOverflowMode) property).overflowmode));
	}

	/**
	 * Returns the name of this property
	 */
	public String getPropertyName() {
	    return "text-overflow-mode";
	}

	/**
	 * Returns the value of this property
	 */
	public Object get() {
	    return overflowmode;
	}

	/**
	 * Returns true if this property is "softly" inherited
	 */
	public boolean isSoftlyInherited() {
	    return overflowmode.equals(inherit);
	}

	/**
	 * Returns a string representation of the object
	 */
	public String toString() {
	    return overflowmode.toString();
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
	    return overflowmode == clip;
	}

    }
