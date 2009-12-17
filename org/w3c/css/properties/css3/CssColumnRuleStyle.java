//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2009  World Wide Web Consortium 
// (MIT, ERCIM, Keio University)
//
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssBorderStyleCSS2;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 *  http://www.w3.org/TR/css3-multicol
 *  <P>
 *  <EM>Value:</EM> &lt;border-style&gt;<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>multicol elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>N/A<BR>
 *  <EM>Media:</EM>:visual
 */

public class CssColumnRuleStyle extends CssProperty {

    CssIdent value;

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
    public CssColumnRuleStyle(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();
	// too many values
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	// we only use Css Ident part of the CssBorderStyle acceptable values
	if ((val.getType() != CssTypes.CSS_IDENT) ||
	    !(((CssIdent)val).equals(inherit) || 
	      CssBorderStyleCSS2.acceptable_values.contains((CssIdent)val))) {
	    throw new InvalidParamException("value",
					    expression.getValue(),
					    getPropertyName(), ac);
	}
	value = (CssIdent) val;
	expression.next();
    }
    
    public CssColumnRuleStyle(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
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
	return "column-rule-style";
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
