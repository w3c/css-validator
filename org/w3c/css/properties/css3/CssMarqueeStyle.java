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

public class CssMarqueeStyle extends CssProperty {

    CssValue mstyle;

    static CssIdent alternate = new CssIdent("alternate");

    private static String[] values = {
		"none", "slide", "scroll", "alternate", "initial", "inherit"
    };

    /**
     * Create a new CssMarqueeStyle
     */
    public CssMarqueeStyle() {
		mstyle = alternate;
    }

    /**
     * Create a new CssMarqueeStyle
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssMarqueeStyle(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	int i = 0;
	for (;i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		mstyle = val;
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

    public CssMarqueeStyle(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssMarqueeStyle != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssMarqueeStyle = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getMarqueeStyle();
	}
	else {
	    return ((Css3Style) style).cssMarqueeStyle;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssMarqueeStyle &&
		mstyle.equals(((CssMarqueeStyle) property).mstyle));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "marquee-style";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return mstyle;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return mstyle.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return mstyle.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return mstyle == alternate;
    }

}
