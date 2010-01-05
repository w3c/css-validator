//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.properties.css3.CssOpacity;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;opacityvalue&gt; || inherit<BR>
 *  <EM>Initial:</EM>1<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class StrokeOpacity extends CssProperty {

    CssValue value;
    ApplContext ac;

    /**
     * Create a new Value
     */
    public StrokeOpacity() {
	//nothing to do
    }

    /**
     * Create a new Value
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public StrokeOpacity(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this.ac = ac;
	setByUser(); // tell this property is set by the user
	CssValue val = expression.getValue();
	boolean correct = false;

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    correct = true;
	} else {
	    try {
		CssOpacity opac = new CssOpacity(ac, expression);
		value = val;
		expression.next();
		correct = true;
	    } catch (InvalidParamException e) {
		correct = false;
	    }
	}
	if (!correct) {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    public StrokeOpacity(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((SVGBasicStyle) style).strokeOpacity != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGBasicStyle) style).strokeOpacity = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGBasicStyle) style).getStrokeOpacity();
	} else {
	    return ((SVGBasicStyle) style).strokeOpacity;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof StrokeOpacity &&
		value.equals( ((StrokeOpacity) property).value));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "stroke-opacity";
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
	return value.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	CssNumber cssnum = new CssNumber(ac, (float) 1.0);
	return value == cssnum;
    }
}
