//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 2.1  1997/08/08 15:53:05  plehegar
 * Nothing
 *
 * Revision 1.3  1997/07/30 13:19:34  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/23 21:01:29  plehegar
 * Added getLength()
 *
 * Revision 1.1  1997/07/16 13:58:16  plehegar
 * Initial revision
 *
 */
package org.w3c.css.values;

import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;
import org.w3c.css.util.ApplContext;

/**
 * A CSS float number.
 *
 * @version $Revision$
 */
public class CssNumber extends CssValue implements CssValueFloat {
    
    ApplContext ac;
    Float value;

    /**
     * Create a new CssNumber
     */
    public CssNumber() {
    }
    
    /**
     * Create a new CssNumber
     */
    public CssNumber(ApplContext ac, float value) {
	this.ac = ac;
	this.value = new Float(value);
    }    
    
    public CssNumber(float value) {
	this.value = new Float(value);
    }

    /**
     * Set the value of this frequency.
     *
     * @param s     the string representation of the frequency.
     * @param frame For errors and warnings reports.
     */  
    public void set(String s, ApplContext ac) {
	value = new Float(s);
	this.ac = ac;
    }
    
    /**
     * Returns the value
     */  
    public Object get() {
	return value;
    }

    /**
     * Return the float value
     */
    public float getValue() {
	return value.floatValue();
    }

    public boolean isInteger() {
	float f = (float) value.intValue();
	return (f == value.floatValue());
    }
    
    /**
     * Returns a length.
     * Only zero can be a length.
     *
     * @exception InvalidParamException The value is not zero
     */  
    public CssLength getLength() throws InvalidParamException {
	float num = value.floatValue();
	if (num == 0) {
	    return new CssLength();
	} else {
	    throw new InvalidParamException("zero", "length", ac);
	}
    }
    
    /**
     * Returns a percentage.
     * Only zero can be a length.
     *
     * @exception InvalidParamException The value is not zero
     */  
    public CssPercentage getPercentage() throws InvalidParamException {
	float num = value.floatValue();
	if (num == 0)
	    return new CssPercentage();
	else {
	    throw new InvalidParamException("zero", 
					    value.toString(), 
					    "percentage", ac);
	}
    }
    
    /**
     * Returns a time.
     * Only zero can be a length.
     *
     * @exception InvalidParamException The value is not zero
     */  
    public CssTime getTime() throws InvalidParamException {
	float num = value.floatValue();
	if (num == 0)
	    return new CssTime();
	else
	    throw new InvalidParamException("zero", value.toString(), 
					    "time", ac);
    }
    
    /**
     * Returns a angle.
     * Only zero can be a length.
     *
     * @exception InvalidParamException The value is not zero
     */  
    public CssAngle getAngle() throws InvalidParamException {
	float num = value.floatValue();
	if (num == 0)
	    return new CssAngle();
	else
	    throw new InvalidParamException("zero", value.toString(), 
					    "angle", ac);
    }
    
    /**
     * Returns a frequency.
     * Only zero can be a length.
     *
     * @exception InvalidParamException The value is not zero
     */  
    public CssFrequency getFrequency() throws InvalidParamException {
	float num = value.floatValue();
	if (num == 0) {
	    return new CssFrequency();
	} else {
	    throw new InvalidParamException("zero", 
					    value.toString(), "frequency", ac);
	}
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {  
	return Util.displayFloat(value);
    }
    
    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */  
    public boolean equals(Object value) {
	return (value instanceof CssNumber && 
		this.value.equals(((CssNumber) value).value));
    }
    
}
