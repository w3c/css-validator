//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */

package org.w3c.css.paged;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;


/**
 */
public class WidowsATSC extends PagedProperty {
    
    CssValue value;
    
    /**
     * Create a new WidowsATSC
     */  
    public WidowsATSC() {
	value = new CssNumber(null, 2);
    }
    
    /**
     * Creates a new CssWidowsATSC
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */  
    public WidowsATSC(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	setByUser();

	ac.getFrame().addWarning("atsc", val.toString());

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	} else if (val instanceof CssNumber) {
	    if (((CssNumber) val).isInteger()) {
		value = val;
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
    
    public WidowsATSC(ApplContext ac, CssExpression expression) 
    	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	return value;
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
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "widows";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.widowsATSC != null) {
	    style.addRedefinitionWarning(ac, this);
	}
	style0.widowsATSC = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getWidowsATSC();
	} else {
	    return ((Css2Style) style).widowsATSC;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	if (value == null) {
	    return (property instanceof WidowsATSC && 
		    ((WidowsATSC) property).value == value);
	} else {
	    return (property instanceof WidowsATSC && 
		    ((WidowsATSC) property).value.equals(value));
	}
    }
    
}
