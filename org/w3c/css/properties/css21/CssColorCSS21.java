// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssColorCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssTypes;
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
    public CssColorCSS21(ApplContext ac, CssExpression expression, 
			 boolean check)
	throws InvalidParamException 
    {
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	setByUser();

	switch (val.getType()) {
	case CssTypes.CSS_COLOR:
	    setColor(val);
	    break;
	case CssTypes.CSS_IDENT:
	    if (inherit.equals(val)) {
		setColor(inherit);
		break;
	    }
	    setColor(new org.w3c.css.values.CssColor(ac,
							  (String) val.get()));
	    break;
	default:
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
	expression.next();
    }

    public CssColorCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
}
