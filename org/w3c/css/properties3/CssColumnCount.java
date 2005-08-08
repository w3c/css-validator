//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;integer&gt; || auto || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  The 'column-count' property determines the number of columns into which the content 
 *  of the element will be flowed. 
 */

public class CssColumnCount extends CssProperty {

    CssValue count;

    static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssColumnCount
     */
    public CssColumnCount() {
	// nothing to do
    }

    /**
     * Create a new CssColumnCount
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssColumnCount(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    count = val;
	    expression.next();
	} else if (val.equals(auto)) {
	    count = val;
	    expression.next();
	} else if (val instanceof CssNumber) {
	    count = val;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssColumnCount(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssColumnCount != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssColumnCount = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getColumnCount();
	}
	else {
	    return ((Css3Style) style).cssColumnCount;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssColumnCount &&
		count.equals(((CssColumnCount) property).count));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "column-count";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return count;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return count.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return count.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (count == auto);
    }

}
