// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssColorCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 * CssColorCSS21<br />
 * Created: Aug 30, 2005 2:33:04 PM<br />
 */
public class CssColorCSS21 extends CssColorCSS2 {

    /**
     * Set the value of the property
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssColorCSS21(ApplContext ac, CssExpression expression, boolean check)
	throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	setByUser();
	if (val.equals(inherit)) {
	    setColor(inherit);
	    expression.next();
	} else if (val instanceof org.w3c.css.values.CssColor) {	    
	    setColor(val);
	    expression.next();
	} else if (val instanceof CssIdent) {	    
	    setColor(new org.w3c.css.values.CssColorCSS21(ac,
							(String) val.get()));
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssColorCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
}
