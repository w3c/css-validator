// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssListStyleTypeCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * CssListStyleTypeCSS21<br />
 * Created: Aug 31, 2005 10:54:09 AM<br />
 */
public class CssListStyleTypeCSS21 extends CssListStyleTypeCSS2 {
    
    
    
    /**
     * Create a new CssListStyleTypeCSS21
     */
    public CssListStyleTypeCSS21() {
	// nothing to do
    }  
    
    /**
     * Create a new CssListStyleTypeCSS21
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssListStyleTypeCSS21(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	super(ac, expression, check);
	
	// fast but ugly
	if(getValue() == 12 || getValue() == 15 || getValue() == 16 ||
		getValue() == 17 || getValue() == 18 || getValue() == 19) {	    
	    throw new InvalidParamException("value", get().toString(),
		    getPropertyName(), ac);
	}
    }
    
    public CssListStyleTypeCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

}
