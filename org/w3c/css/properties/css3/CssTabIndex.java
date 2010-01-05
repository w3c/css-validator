//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> auto || <number> || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>all enabled elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:interactive
 *  <P>
 *  This property specifies the position of the current element in the tabbing
 *  order for the current document.
 */

public class CssTabIndex extends CssProperty {

    CssValue tabidx;

    static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssTabIndex
     */
    public CssTabIndex() {
	tabidx = auto;
    }

    /**
     * Create a new CssTabIndex
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssTabIndex(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(auto)) {
	    tabidx = auto;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    tabidx = inherit;
	    expression.next();
	}
	else if (val instanceof CssNumber) {
	    tabidx = val;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssTabIndex(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTabIndex != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTabIndex = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTabIndex();
	}
	else {
	    return ((Css3Style) style).cssTabIndex;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTabIndex &&
		tabidx.equals(((CssTabIndex) property).tabidx));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "tab-index";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return tabidx;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return tabidx.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return tabidx.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return tabidx == auto;
    }

}
