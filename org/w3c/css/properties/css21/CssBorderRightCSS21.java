// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssBorderRightCSS2;
import org.w3c.css.properties.css1.CssBorderRightStyleCSS2;
import org.w3c.css.properties.css1.CssBorderRightWidthCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * CssBorderRightCSS21<br />
 * Created: Aug 31, 2005 2:08:24 PM<br />
 */
public class CssBorderRightCSS21 extends CssBorderRightCSS2 {
    
    /**
     * Create a new CssBorderRightCSS21
     */
    public CssBorderRightCSS21() {
	super();
    }  
    
    /**
     * Create a new CssBorderRightCSS21
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */  
    public CssBorderRightCSS21(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = null;
	char op = SPACE;
	boolean find = true;
	
	if(check && expression.getCount() > 3) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	boolean manyValues = (expression.getCount() > 1);
	
	setByUser();
	
	while (find) {
	    find = false;
	    val = expression.getValue();	    
	    op = expression.getOperator();	    
	    if (val == null)
		break;
	    
	    // if there are many values, we can't have inherit as one of them
	    if(manyValues && val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", null, null, ac);
	    }
	    
	    if (op != SPACE)
		throw new InvalidParamException("operator", 
						((new Character(op)).toString()),
						ac);   
	    if (getWidth() == null) {
		try {
		    setWidth(new CssBorderRightWidthCSS2(ac, expression));		
		    find = true;
		} catch(InvalidParamException e){
		    // nothing to do, style will test this value		    
		}
	    }
	    if (!find && getStyle() == null) {
		try {
		    setStyle(new CssBorderRightStyleCSS2(ac, expression));		    
		    find = true;
		} catch(InvalidParamException e){
		    // nothing to do, color will test this value
		}
	    }
	    if (!find && getColor() == null) {
		// throws an exception if the value is not valid
		setColor(new CssBorderRightColorCSS21(ac, expression));
		find = true;		
	    }
	}
    }
    
    public CssBorderRightCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression,false);
    }
    
}
