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

    public class CssWhiteSpaceTreatment extends CssProperty {

	CssValue wstreat;

	private static CssIdent ignore = new CssIdent("ignore");
	private static CssIdent preserve = new CssIdent("preserve");
	private static CssIdent ignoreifbeforelf = new CssIdent("ignore-if-before-linefeed");
	private static CssIdent ignoreifafterlf = new CssIdent("ignore-if-after-linefeed");
	private static CssIdent ignoreifsurroundinglf = new CssIdent("ignore-if-surrounding-linefeed");

	/**
	 * Create a new CssWhiteSpaceTreatment
	 */
	public CssWhiteSpaceTreatment() {
	    wstreat = ignoreifsurroundinglf;
	}

	/**
	 * Create a new CssWhiteSpaceTreatment
	 *
	 *
	 */
	public CssWhiteSpaceTreatment(ApplContext ac, CssExpression expression,
		boolean check) throws InvalidParamException {
	    setByUser();
	    CssValue val = expression.getValue();
	    if (val.equals(ignore)) {
			wstreat = ignore;
			expression.next();
	    }
	    else if (val.equals(preserve)) {
			wstreat = preserve;
			expression.next();
	    }
	    else if (val.equals(ignoreifbeforelf)) {
			wstreat = ignoreifbeforelf;
			expression.next();
	    }
	    else if (val.equals(ignoreifafterlf)) {
			wstreat = ignoreifafterlf;
			expression.next();
	    }
	    else if (val.equals(ignoreifsurroundinglf)) {
			wstreat = ignoreifsurroundinglf;
			expression.next();
	    }
	    else if (val.equals(inherit)) {
			wstreat = inherit;
			expression.next();
	    }

	    else {
		throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	    }
	}

	public CssWhiteSpaceTreatment(ApplContext ac, CssExpression expression)
		throws InvalidParamException {
	    this(ac, expression, false);
	}
	
	/**
	 * Add this property to the CssStyle.
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
	    if (((Css3Style) style).cssWhiteSpaceTreatment != null)
		style.addRedefinitionWarning(ac, this);
	    ((Css3Style) style).cssWhiteSpaceTreatment = this;

	}

	/**
	 * Get this property in the style.
	 *
	 * @param style The style where the property is
	 * @param resolve if true, resolve the style to find this property
	 */
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((Css3Style) style).getWhiteSpaceTreatment();
	    } else {
		return ((Css3Style) style).cssWhiteSpaceTreatment;
	    }
	}

	/**
	 * Compares two properties for equality.
	 *
	 * @param value The other property.
	 */
	public boolean equals(CssProperty property) {
	    return (property instanceof CssWhiteSpaceTreatment &&
		    wstreat.equals( ((CssWhiteSpaceTreatment) property).wstreat));
	}

	/**
	 * Returns the name of this property
	 */
	public String getPropertyName() {
	    return "white-space-treatment";
	}

	/**
	 * Returns the value of this property
	 */
	public Object get() {
	    return wstreat;
	}

	/**
	 * Returns true if this property is "softly" inherited
	 */
	public boolean isSoftlyInherited() {
	    return wstreat.equals(inherit);
	}

	/**
	 * Returns a string representation of the object
	 */
	public String toString() {
	    return wstreat.toString();
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
	    return wstreat == ignoreifsurroundinglf;
	}

    }
