//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties;

import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * @version $Revision$
 */
public abstract class CssBoxOffsetFace extends CssProperty {
    
    CssValue value;
    
    private static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssBoxOffsetFace
     */
    public CssBoxOffsetFace() {
	value = auto;
    }
    
    /**
     * Create a new CssBoxOffsetFace
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssBoxOffsetFace(ApplContext ac, CssExpression expression) 
	    throws InvalidParamException {
	CssValue val = expression.getValue();
	
	setByUser();
	
	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else if (val.equals(auto)) {
	    value = auto;
	    expression.next();
	} else if (val instanceof CssLength || val instanceof CssPercentage) {
	    value = val;
	    expression.next();
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getLength();
	    expression.next();
	} else if (val.equals(auto)) {
	    value = auto;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", val.toString(), 
					    getPropertyName(), ac);
	}
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }
    
    /**
     * Returns the internal CssValue value.
     */
    public CssValue getValue() {
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
     * Compares two sides for equality.
     *
     * @param side The other side.
     */  
    public boolean equals(CssBoxOffsetFace side) {
	return value.equals(side.value);
    }
    
    /**
     * Is this property contains a default value.
     */  
    public boolean isDefault() {
	if (value != auto)
	    return ((Float) value.get()).floatValue() == 0;
	else
	    return false;
    }
    
}
