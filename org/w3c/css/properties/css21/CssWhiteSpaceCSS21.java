// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssWhiteSpace;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 * CssWhiteSpaceCSS21<br />
 * Created: Aug 30, 2005 5:02:07 PM<br />
 */
public class CssWhiteSpaceCSS21 extends CssWhiteSpace {
    private static String[] WHITESPACE = {
	"normal", "pre", "nowrap", "pre-wrap", "pre-line", "inherit" };
    private static int[] hash_values;
    
    /**
     * Create a new CssWhiteSpace
     */
    public CssWhiteSpaceCSS21() {
	// nothing to do
    }
    
    /**
     * Create a new CssWhiteSpace
     *
     * @param expression The expression for this property
     * @exception InvalidParamException values are incorrect
     */  
    public CssWhiteSpaceCSS21(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	
	setByUser();
	
	if ( val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < WHITESPACE.length; i++)
		if (hash_values[i] == hash) {
		    setValue(i);		    
		    expression.next();
		    return;
		}
	}
	
	throw new InvalidParamException("value", expression.getValue(), 
					getPropertyName(), ac);
    }
    
    public CssWhiteSpaceCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return WHITESPACE[getValue()];
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return getValue() == (WHITESPACE.length - 1);
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return WHITESPACE[getValue()];
    }

    static {
	hash_values = new int[WHITESPACE.length];
	for (int i = 0; i < WHITESPACE.length; i++)
	    hash_values[i] = WHITESPACE[i].hashCode();
    }
}
