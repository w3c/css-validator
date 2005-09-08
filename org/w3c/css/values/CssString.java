//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

/**
 * A CSS string.
 *
 * @version $Revision$
 */
public class CssString extends CssValue {

    String value;
    
    /**
     * Create a new CssString
     */
    public CssString() {
    }
    
    /**
     * Create a new CssString
     */
    public CssString(String s) {
	value = s;
    }
    
    /**
     * Set the value of this string.
     *
     * @param s     the string representation of the string.
     * @param frame For errors and warnings reports.
     * @exception InvalidParamException The unit is incorrect
     */  
    public void set(String s, ApplContext ac) throws InvalidParamException {
	if (s.indexOf('\'') == -1 &&
	    s.indexOf('"') == -1) {
	    throw new InvalidParamException("string", s, ac);
	}
	value = s;
    }
    
    /**
     * Returns the string
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
     * Get the hash code of the internal string.
     */  
    public int hashCode() {
	return value.hashCode();
    }
    
    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */  
    public boolean equals(Object value) {
	return (value instanceof CssString && 
		this.value.equals(((CssString) value).value));
    }
    
}
