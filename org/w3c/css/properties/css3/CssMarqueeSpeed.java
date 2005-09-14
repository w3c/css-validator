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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssTime;
import org.w3c.css.values.CssValue;

public class CssMarqueeSpeed extends CssProperty {

    String mspeed;

    static CssIdent slow = new CssIdent("slow");
    static CssIdent normal = new CssIdent("normal");
    static CssIdent fast = new CssIdent("fast");
    static CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssMarqueeSpeed
     */
    public CssMarqueeSpeed() {
		mspeed = "normal";
    }

    /**
     * Create a new CssMarqueeSpeed
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssMarqueeSpeed(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(normal)) {
	    mspeed = "normal";
	    expression.next();
	}
	else if (val.equals(slow)) {
	    mspeed = "slow";
	    expression.next();
	}
	else if (val.equals(initial)) {
		mspeed = "initial";
		expression.next();
	}
	else if (val.equals(fast)) {
	    mspeed = "fast";
	    expression.next();
	}
	else if (val instanceof CssLength) {
	    mspeed = val.toString();
	    expression.next();

	    val = expression.getValue();
	    if (val != null) {
		if (val instanceof CssTime) {
		    mspeed = mspeed += " " + val.toString();
		    expression.next();
		} else {
		    throw new InvalidParamException("value", expression.getValue(), getPropertyName(), ac);
		}
	    }
	}
	else if (val.equals(inherit)) {
	    mspeed = "inherit";
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value",
		    expression.getValue(),
		    getPropertyName(), ac);
	}

    }

    public CssMarqueeSpeed(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssMarqueeSpeed != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssMarqueeSpeed = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getMarqueeSpeed();
	}
	else {
	    return ((Css3Style) style).cssMarqueeSpeed;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssMarqueeSpeed &&
		mspeed.equals(((CssMarqueeSpeed) property).mspeed));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "marquee-speed";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return mspeed;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return mspeed.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return mspeed.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return mspeed.equals("normal");
    }

}
