//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssBorderFaceStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;


public class CssBorderBreak extends CssProperty {

    CssBorderFaceStyle bfsvalue = null;
    CssValue value = null;

    CssIdent none = new CssIdent("none");

    /**
     * Create a new CssBorderBreak
     */
    public CssBorderBreak() {
	value = none;
    }

    /**
     * Create a new CssBorderBreak
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssBorderBreak(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();

	bfsvalue = new CssBorderFaceStyle(ac, expression);
    }

    public CssBorderBreak(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssBorderBreak != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBorderBreak = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getBorderBreak();
	}
	else {
	    return ((Css3Style) style).cssBorderBreak;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderBreak &&
		(value.equals(((CssBorderBreak) property).value) ||
			bfsvalue.equals(((CssBorderBreak) property).bfsvalue)));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border-break";
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
	if (value != null) {
	    return value.toString();
	} else {
	    return bfsvalue.toString();
	}
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value.equals(new CssBorderFaceStyle());
    }

}
