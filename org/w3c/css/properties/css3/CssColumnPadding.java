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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;length&gt; || &lt;percentage&gt; || inherit<BR>
 *  <EM>Initial:</EM>0<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>relative to 'column-width'<BR>
 *  <EM>Media:</EM>:visual
 */

public class CssColumnPadding extends CssProperty {

    CssValue padding;

    /**
     * Create a new CssColumnPadding
     */
    public CssColumnPadding() {
	// nothing to do
    }

    /**
     * Create a new CssColumnPadding
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssColumnPadding(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    padding = val;
	    expression.next();
	} else if (val instanceof CssLength) {
	    padding = val;
	    expression.next();
	} else if (val instanceof CssPercentage) {
	    padding = val;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssColumnPadding(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssColumnPadding != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssColumnPadding = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getColumnPadding();
	}
	else {
	    return ((Css3Style) style).cssColumnPadding;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssColumnPadding &&
		padding.equals(((CssColumnPadding) property).padding));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "column-padding";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return padding;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return padding.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return padding.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return false;
    }

}
