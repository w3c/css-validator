// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21.table;

import org.w3c.css.properties.css2.table.CaptionSide;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * CssCaptionSide21<br />
 * Created: Aug 30, 2005 5:49:51 PM<br />
 */
public class CssCaptionSideCSS21 extends CaptionSide {    

    /**
     * Create a new CaptionSide
     */  
    public CssCaptionSideCSS21() {
	super();
    }
    
    /**
     * Creates a new CssCaptionSide
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */  
    public CssCaptionSideCSS21(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {
	
	super(ac, expression, check);
	
	// 'left' and 'right' are forbidden in CSS 2.1
	if(getValue() == left || getValue() == right) {
	    throw new InvalidParamException("value", 
		    getValue().toString(), 
		    getPropertyName(), ac);
	}
    }
    
    public CssCaptionSideCSS21(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	this(ac, expression, false);
    }
}
