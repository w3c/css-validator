//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class CssBorderFaceWidth {
    
    CssValue value;
    
    /**
     * Create a new CssBorderFaceWidth
     */
    public CssBorderFaceWidth() {
	//value = medium;
    }
    
    /**
     * Create a new CssBorderFaceWidth from an another CssBorderFaceWidth
     *
     * @param another The another side.
     */
    public CssBorderFaceWidth(CssBorderFaceWidth another) {
	value = another.value;
    }
    
    /**
     * Create a new CssBorderFaceWidth
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderFaceWidth(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	CssIdent initial = new CssIdent("initial");
	
	if (val instanceof CssLength) {
	    float f = ((Float) val.get()).floatValue();
	    if (f >= 0)
		this.value = val;
	    else
		throw new InvalidParamException("negative-value", val.toString(), ac);
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getLength();
	} else if (val instanceof CssPercentage) {
	    value = val;
	} else if (val.equals(thin)) {
	    value = thin;
	} else if (val.equals(medium)) {
	    value = medium;
	} else if (val.equals(thick)) {
	    value = thick;
	} else if (val.equals(initial)) {
	    value = initial;
	} else if (val.equals(CssProperty.inherit)) {
	    value = CssProperty.inherit;
	} else {
	    throw new InvalidParamException("value", val.toString(), "width", ac);
	}
	
	expression.next();
    }
    
    public CssBorderFaceWidth(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the internal value
     */
    public CssValue getValue() {
	return value;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	
	if (value != null) {
	    return value.toString();
	} else {
	    return "";
	}
    }
    
    /**
     * Compares two sides for equality.
     *
     * @param value The another side.
     */
    public boolean equals(CssBorderFaceWidth another) {
	return value.equals(another.value); // FIXME
    }
    
    private static CssIdent thin = new CssIdent("thin");
    private static CssIdent medium = new CssIdent("medium");
    private static CssIdent thick = new CssIdent("thick");
    
}



