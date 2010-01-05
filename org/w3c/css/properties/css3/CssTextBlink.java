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
import org.w3c.css.values.CssValue;

public class CssTextBlink extends CssProperty {

    CssValue blink;

    static CssIdent none = new CssIdent("none");
    static CssIdent blinkval = new CssIdent("blink");


    /**
     * Create a new CssBlink
     */
    public CssTextBlink() {
	blink = none;
    }

    /**
     * Create a new CssTextBlink
     *
     * @param expression The expression for this parameter
     * @exception InvalidParamException Incorrect value
     */
    public CssTextBlink(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(none)) {
	    blink = none;
	    expression.next();
	}
	else if (val.equals(blinkval)) {
	    blink = blinkval;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    blink = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssTextBlink(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextBlink != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextBlink = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextBlink();
	}
	else {
	    return ((Css3Style) style).cssTextBlink;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextBlink &&
		blink.equals(((CssTextBlink) property).blink));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-blink";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return blink;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return blink.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return blink.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return blink == none;
    }

}
