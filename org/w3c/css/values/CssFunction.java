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
import org.w3c.css.util.ApplContext;

/**
 * A CSS float number.
 *
 * @version $Revision$
 */
public class CssFunction extends CssValue {

    String name;
    CssExpression parameters;
    
    /**
     * Set the value of this function
     *
     * @param s     the string representation of the frequency.
     * @param frame For errors and warnings reports.
     */  
    public void set(String s, ApplContext ac) {
	// @@TODO
    }
    
    public void set(String name, CssExpression parameters) {
	this.name = name;
	this.parameters = parameters;
	
    }
    
    /**
     * Returns the value
     */  
    public Object get() {
	// @@TODO
	return null;
    }

    /**
     * Returns the name of the function
     */
    public String getName() {
	return name;
    }
    
    /**
     * Returns the parameters expression
     */
    public CssExpression getParameters() {
	return parameters;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {  
	return name + "(" + parameters + ")";
    }
    
    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */  
    public boolean equals(Object value) {
	// @@FIXME
	return (value instanceof CssFunction && 
		this.name.equals(((CssFunction) value).name));
    }
}
