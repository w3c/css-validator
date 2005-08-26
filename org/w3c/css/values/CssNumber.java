//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.3  2005/08/08 13:19:47  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.2  2002/04/08 21:19:46  plehegar
 * New
 *
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

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;

/**
 * A CSS float number.
 *
 * @version $Revision$
 */
public class CssNumber extends CssValue implements CssValueFloat {
    
    ApplContext ac;
    Float value;
    boolean isInt = false;

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
	try {
	    new Integer(s);	    
	    isInt = true;
	}
	catch(NumberFormatException e) {
	    isInt = false;
	}
	finally {	 
	    value = new Float(s);
	}
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

    public int getInt() throws InvalidParamException {
	if(isInt) {
	    return value.intValue();
	}
	else {
	    throw new InvalidParamException("invalid-color", ac);
	}
    }
    
    public boolean isInteger() {
	return isInt;
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
