//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'stroke-miterlimit'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> inherit | &lt;miterlimit&gt; <BR>
 *   <EM>Initial:</EM>4<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 */
public class StrokeMiterLimit extends CssProperty {
    
    CssValue value;

    /**
     * Create a new StrokeMiterLimit
     */
    public StrokeMiterLimit() {
	//nothing to do
    }  
    
    /**
     * Create a new StrokeMiterLimit
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public StrokeMiterLimit(ApplContext ac, CssExpression expression,
	    boolean check) 
	throws InvalidParamException {
	
	setByUser();
	CssValue val = expression.getValue();

	if (val instanceof CssNumber) {
	    if (((CssNumber) val).getValue() >= 1) {
		value = val;
		expression.next();
	    } else {
		throw new InvalidParamException("out-of-range", expression.getValue(),
						getPropertyName(), ac);
	    }
	} else if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", 
					    expression.getValue(), 
					    getPropertyName(), ac);
	}
    }
    
    public StrokeMiterLimit(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "stroke-miterlimit";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value.toString();
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	SVGStyle style0 = (SVGStyle) style;
	if (style0.strokeMiterLimit != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.strokeMiterLimit = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGStyle) style).getStrokeMiterLimit();
	} else {
	    return ((SVGStyle) style).strokeMiterLimit;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof StrokeMiterLimit && 
		value.equals(((StrokeMiterLimit) property).value));
    }
    
}
