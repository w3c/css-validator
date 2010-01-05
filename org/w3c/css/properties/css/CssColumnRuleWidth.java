//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.properties.css1.CssBorderFaceWidthCSS2;
import org.w3c.css.properties.css3.Css3Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * http://www.w3.org/TR/css3-multicol/
 *  <P>
 *  <EM>Value:</EM> &lt;border-width&gt;<BR>
 *  <EM>Initial:</EM>medium<BR>
 *  <EM>Applies to:</EM>multicol elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>N/A<BR>
 *  <EM>Media:</EM>:visual
 */

public class CssColumnRuleWidth extends CssProperty {

    CssBorderFaceWidthCSS2 value;

    /**
     * Create a new CssColumnRuleWidth
     */
    public CssColumnRuleWidth() {
	// nothing to do
    }

    /**
     * Create a new CssColumnRuleWidth
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssColumnRuleWidth(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	try {
	    value = new CssBorderFaceWidthCSS2(ac, expression);
	} catch (InvalidParamException e) {
	    throw new InvalidParamException("value",
					    expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssColumnRuleWidth(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssColumnRuleWidth != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssColumnRuleWidth = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getColumnRuleWidth();
	}
	else {
	    return ((Css3Style) style).cssColumnRuleWidth;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssColumnRuleWidth &&
		value.equals(((CssColumnRuleWidth) property).value));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "column-rule-width";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return value.getValue();
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return inherit.equals(value.getValue());
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
