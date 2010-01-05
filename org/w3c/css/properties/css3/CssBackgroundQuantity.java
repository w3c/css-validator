//
// $Id$
// From Sijtsche Smeman (sijtsche@wisdom.nl)
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
 *  <EM>Value:</EM> &lt;integer&gt; || inherit || infinite || initial<BR>
 *  <EM>Initial:</EM>infinite<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  If a 'background-image' is specified, the value of, the value of 'background-quantity'
 *  determines how many times the image will repeat.
 */

public class CssBackgroundQuantity extends CssProperty {

    CssValue bgquantity;

    CssIdent initial = new CssIdent("initial");
    CssIdent infinite = new CssIdent("infinite");

    /**
     * Create a new CssBackgroundQuantity
     */
    public CssBackgroundQuantity() {
	bgquantity = infinite;
    }

    /**
     * Create a new CssBackgroundQuantity
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssBackgroundQuantity(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	CssValue val = expression.getValue();
	setByUser();

	if (val.equals(inherit)) {
	    bgquantity = inherit;
	    expression.next();
	    return;
	} else if (val.equals(infinite)) {
	    bgquantity = infinite;
	    expression.next();
	    return;
	} else if (val.equals(initial)) {
	    bgquantity = initial;
	    expression.next();
	    return;
	} else if (val instanceof CssNumber) {
	    if (((CssNumber) val).isInteger()) {
		bgquantity = val;
		expression.next();
		return;
	    } else {
		throw new InvalidParamException("integer",
			val.toString(),
			getPropertyName(), ac);
	    }
	}

	throw new InvalidParamException("value",
		val.toString(),
		getPropertyName(), ac);
    }

    public CssBackgroundQuantity(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssBackgroundQuantity != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBackgroundQuantity = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getCssBackgroundQuantity();
	}
	else {
	    return ((Css3Style) style).cssBackgroundQuantity;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBackgroundQuantity &&
		bgquantity.equals(((CssBackgroundQuantity) property).bgquantity));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "background-quantity";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return bgquantity;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return bgquantity.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return bgquantity.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return bgquantity == infinite;
    }

}
