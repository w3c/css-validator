// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssBackgroundColorCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 * CssBackgroundColorCSS21<br />
 * Created: Aug 31, 2005 2:04:23 PM<br />
 */
public class CssBackgroundColorCSS21 extends CssBackgroundColorCSS2 {

    /**
     * Create a new CssBackgroundColorCSS2
     */
    public CssBackgroundColorCSS21() {
	super();
    }

    /**
     * Create a new CssBackgroundColorCSS2
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBackgroundColorCSS21(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	CssValue val = expression.getValue();

	if (val instanceof org.w3c.css.values.CssColor) {
	    setColor(val);
	    expression.next();
	} else if (val instanceof CssIdent) {
	    if (val.equals(transparent)) {
		setColor(transparent);
		expression.next();
	    } else if (val.equals(inherit)) {
		setColor(inherit);
		expression.next();
	    } else {
		setColor(new org.w3c.css.values.CssColorCSS21(ac, (String) val.get()));
		expression.next();
	    }
	} else {
	    throw new InvalidParamException("value", val.toString(),
					    getPropertyName(), ac);
	}
    }

    public CssBackgroundColorCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
}
