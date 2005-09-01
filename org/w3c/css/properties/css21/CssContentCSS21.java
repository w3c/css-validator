// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssContentCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 * CssContentCSS21<br />
 * Created: Aug 30, 2005 4:18:11 PM<br />
 */
public class CssContentCSS21 extends CssContentCSS2{

    static final CssIdent normal = new CssIdent("normal");
    static final CssIdent none = new CssIdent("none");
    
    /**
     * Create a new CssContent
     */
    public CssContentCSS21() {
    }  
    
    /**
     * Create a new CssContent
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */  
    public CssContentCSS21(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	CssValue val = expression.getValue();

	setByUser();
	
	if (val.equals(inherit)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    getValues().addElement(inherit);
	    expression.next();
	    return;
	}
	if (val.equals(normal)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    getValues().addElement(normal);
	    expression.next();
	    return;
	}
	if (val.equals(none)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    getValues().addElement(none);
	    expression.next();
	    return;
	}
	
	addContent(ac, expression);
	
    }
    
    public CssContentCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
}
