//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties.CssBorderStyle;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;border-style&gt; || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class CssColumnRuleStyle extends CssProperty {

    CssBorderStyle value;

    /**
     * Create a new CssColumnRuleStyle
     */
    public CssColumnRuleStyle() {
	// nothing to do
    }

    /**
     * Create a new CssColumnRuleStyle
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssColumnRuleStyle(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	try {
	    value = new CssBorderStyle(ac, expression);
	    expression.next();
	}
	catch (InvalidParamException e) {
	    throw new InvalidParamException("value",
					    expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssColumnRuleStyle != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssColumnRuleStyle = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getColumnRuleStyle();
	}
	else {
	    return ((Css3Style) style).cssColumnRuleStyle;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssColumnRuleStyle &&
		value.equals(((CssColumnRuleStyle) property).value));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "column-border-style";
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
	return value.equals(inherit);
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
	return false;
    }

}
