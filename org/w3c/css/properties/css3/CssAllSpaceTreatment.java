//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 */
public class CssAllSpaceTreatment extends CssProperty {

    CssValue spacetreat;

    private static CssIdent preserve = new CssIdent("collapse");
    private static CssIdent collapse = new CssIdent("preserve");

    /**
     * Create a new CssAllSpaceTreatment
     */
    public CssAllSpaceTreatment() {
	spacetreat = collapse;
    }

    /**
     * Create a new CssAllSpaceTreatment
     *
     *
     */
    public CssAllSpaceTreatment(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(preserve)) {
	    spacetreat = preserve;
	    expression.next();
	}
	else if (val.equals(collapse)) {
	    spacetreat = collapse;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    spacetreat = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    public CssAllSpaceTreatment(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssAllSpaceTreatment != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssAllSpaceTreatment = this;

    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getAllSpaceTreatment();
	} else {
	    return ((Css3Style) style).cssAllSpaceTreatment;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssAllSpaceTreatment &&
		spacetreat.equals( ((CssAllSpaceTreatment) property).spacetreat));
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
	return spacetreat;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return spacetreat.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return spacetreat.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return spacetreat == collapse;
    }

}
