//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2009 
// World Wide Web Consortium (MIT, ERCIM, Keio University)
//
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * From http://www.w3.org/TR/css3-multicol
 *  <P>
 *  <EM>Value:</EM> 1 ||  all <BR>
 *  <EM>Initial:</EM>1<BR>
 *  <EM>Applies to:</EM>static, non-floating elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>N/A<BR>
 *  <EM>Media:</EM>:visual
 */

public class CssColumnSpan extends CssProperty {

    CssValue value;
    ApplContext ac;

    static CssIdent all;
    static {
	all = new CssIdent("all");
    }

    /**
     * Create a new CssColumnSpan
     */
    public CssColumnSpan() {
	//nothing to do
    }

    /**
     * Create a new CssColumnSpan
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssColumnSpan(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this.ac = ac;
	setByUser(); // tell this property is set by the user
	CssValue val = expression.getValue();

	switch (val.getType()) {
	case CssTypes.CSS_NUMBER:
	    int ival = ((CssNumber) val).getInt();
	    if (ival != 1) {
		throw new InvalidParamException("value", val.toString(),
						getPropertyName(), ac);
	    }
	    value = val;
	    break;
	case CssTypes.CSS_IDENT:
	    if (all.equals(val)) {
		value = all;
		break;
	    }
	    if (inherit.equals(val)) {
		value = inherit;
		break;
	    }
	default:
	    throw new InvalidParamException("value", val.toString(),
					    getPropertyName(), ac);
	}
    }

    public CssColumnSpan(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssColumnSpan != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssColumnSpan = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getColumnSpan();
	} else {
	    return ((Css3Style) style).cssColumnSpan;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssColumnSpan &&
		value.equals( ((CssColumnSpan) property).value));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "column-span";
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
	return (value == inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return value.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	// we only have 3 values
	return ((value != all) && (value != inherit));
    }

}
