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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

public class CssBorderBottomRightRadius extends CssProperty {

    String value;
    ApplContext ac;
    
    /**
     * Create new CssBorderBottomRightRadius
     */
    public CssBorderBottomRightRadius() {
	CssNumber cssnum =  new CssNumber((float) 1.0);
	value = cssnum.toString();
    }
    
    /**
     * Create new CssBorderBottomRightRadius
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderBottomRightRadius(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	
	if (val instanceof CssLength) {
	    value = val.toString();
	    expression.next();
	    
	    val = expression.getValue();
	    if (val != null) {
		
		if (val instanceof CssLength) {
		    value += " " + val.toString();
		    expression.next();
		} else {
		    throw new InvalidParamException("value", 
			    expression.getValue(), getPropertyName(), ac);
		}
	    }
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
		    getPropertyName(), ac);
	}
    }
    
    public CssBorderBottomRightRadius(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssBorderBottomRightRadius != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBorderBottomRightRadius = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getBorderBottomRightRadius();
	} else {
	    return ((Css3Style) style).cssBorderBottomRightRadius;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderBottomRightRadius &&
		value.equals( ((CssBorderBottomRightRadius) property).value));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border-bottom-right-radius";
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
	return value == cssnum.toString();
    }
    
}
