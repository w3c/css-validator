//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *  <P>
 *  <EM>Value:</EM> fill | hidden | meet | slice <BR>
 *  <EM>Initial:</EM>hidden<BR>
 *  <EM>Applies to:</EM>replaced elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property gives a hint for how to scale a replaced element if neither its
 *  'width' nor its 'height' property is 'auto'. Not all replaced objects can be
 *  scaled, but images typically can.
 */

public class CssFit extends CssProperty {

    CssValue fit;

    static CssIdent hidden = new CssIdent("hidden");

    private static String[] values = {
	"fill", "hidden", "meet", "slice", "initial", "inherit"
    };

    /**
     * Create a new CssFit
     */
    public CssFit() {
	fit = hidden;
    }

    /**
     * Create a new CssFit
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssFit(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	int i = 0;
	for (; i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		fit = val;
		expression.next();
		break;
	    }
	}
	if (i == values.length) {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssFit != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssFit = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getFit();
	}
	else {
	    return ((Css3Style) style).cssFit;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssFit &&
		fit.equals(((CssFit) property).fit));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "fit";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return fit;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return fit.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return fit.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (fit == hidden);
    }

}
