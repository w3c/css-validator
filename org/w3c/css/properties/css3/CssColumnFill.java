//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import java.util.HashSet;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * http://www.w3.org/TR/css3-multicol/
 *  <P>
 *  <EM>Value:</EM> auto | balance <BR>
 *  <EM>Initial:</EM>balance<BR>
 *  <EM>Applies to:</EM>multicol elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>N/A<BR>
 *  <EM>Media:</EM>:paged
 *  <P>
 */

public class CssColumnFill extends CssProperty {

    CssValue value;

    static CssIdent default_value;
    public static HashSet<CssIdent> acceptable_values;
    static {
	default_value = CssIdent.getIdent("balance");
	acceptable_values = new HashSet<CssIdent>();
	acceptable_values.add(default_value);
	acceptable_values.add(CssIdent.getIdent("auto"));
    }

    /**
     * Create a new CssColumnWidth
     */
    public CssColumnFill() {
	value = default_value;
    }

    /**
     * Create a new CssColumnFill
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssColumnFill(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	if ((val.getType() != CssTypes.CSS_IDENT) ||
	    !(((CssIdent)val).equals(inherit) || 
	      acceptable_values.contains((CssIdent)val))) {
	    throw new InvalidParamException("value",
					    expression.getValue(),
					    getPropertyName(), ac);
	}
	value = CssIdent.getIdent(((CssIdent)val).toString());
	expression.next();
    }

    public CssColumnFill(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssColumnFill != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssColumnFill = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getColumnFill();
	}
	else {
	    return ((Css3Style) style).cssColumnFill;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssColumnFill &&
		value.equals(((CssColumnFill) property).value));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "column-fill";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return inherit.equals(value);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return value.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (value == default_value);
    }

}
