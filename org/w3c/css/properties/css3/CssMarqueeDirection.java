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
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;


public class CssMarqueeDirection extends CssProperty {

    CssValue mdir;

    static CssIdent auto = new CssIdent("auto");

    private static String[] values = {
		"forwards", "backwards", "ahead", "reverse",
		"left", "right", "up", "down", "auto", "initial", "inherit"
    };

    /**
     * Create a new CssMarqueeDirection
     */
    public CssMarqueeDirection() {
		mdir = auto;
    }

    /**
     * Create a new CssMarqueeDirection
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssMarqueeDirection(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	int i = 0;

	for (;i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		mdir = val;
		expression.next();
		break;
	    }
	}

	if (i == values.length) {
	    throw new InvalidParamException("value",
		    expression.getValue(),
		    getPropertyName(), ac);
	}

    }

    public CssMarqueeDirection(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssMarqueeDirection != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssMarqueeDirection = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getMarqueeDirection();
	}
	else {
	    return ((Css3Style) style).cssMarqueeDirection;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssMarqueeDirection &&
		mdir.equals(((CssMarqueeDirection) property).mdir));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "marquee-direction";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return mdir;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return mdir.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return mdir.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return mdir == auto;
    }

}
