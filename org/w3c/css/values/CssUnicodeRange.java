//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;

/**
 */
public class CssUnicodeRange extends CssValue {
    
    String value;

    /**
     * Create a new CssUnicodeRange
     */
    public CssUnicodeRange() {
    }
    
    /**
     * Create a new CssUnicodeRange
     */
    public CssUnicodeRange(String value) {
	this.value = value;
    }    
    
    /**
     * Set the value of this frequency.
     *
     * @param s     the string representation of the frequency.
     * @param frame For errors and warnings reports.
     */  
    public void set(String s, ApplContext ac) {
	value = s;
    }
    
    /**
     * Returns the value
     */  
    public Object get() {
	return value;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {  
	return value;
    }
    
    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */  
    public boolean equals(Object value) {
	return ((value instanceof CssUnicodeRange)
		&& (value != null)
		&& this.value.equals(((CssUnicodeRange) value).value));
    }
    
}
