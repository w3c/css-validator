//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *
 */

public class CssLineStackingShift extends CssProperty {

    CssValue linestackingshift;

    private static CssIdent initial = new CssIdent("initial");
    private static CssIdent considershifts = new CssIdent("consider-shifts");
    private static CssIdent disregardshifts = new CssIdent("disregard-shifts");

    /**
     * Create a new CssLineStackingShift
     */
    public CssLineStackingShift() {
		linestackingshift = considershifts;
    }

    /**
     * Create a new CssLineStackingShift
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssLineStackingShift(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(initial)) {
		linestackingshift = initial;
		expression.next();
	}
	else if (val.equals(considershifts)) {
		linestackingshift = considershifts;
		expression.next();
	}
	else if (val.equals(disregardshifts)) {
		linestackingshift = disregardshifts;
		expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssLineStackingShift(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssLineStackingShift != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssLineStackingShift = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getLineStackingShift();
	}
	else {
	    return ((Css3Style) style).cssLineStackingShift;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssLineStackingShift &&
		linestackingshift.equals(((CssLineStackingShift) property).linestackingshift));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "line-stacking-shift";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return linestackingshift;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return linestackingshift.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return linestackingshift.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return linestackingshift == considershifts;
    }

}
