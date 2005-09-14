// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssTextAlign;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssString;

/**
 * CssTextAlignCSS21<br />
 * Created: Aug 31, 2005 11:39:33 AM<br />
 */
public class CssTextAlignCSS21 extends CssTextAlign {
    /**
     * Create a new CssTextAlignCSS21
     */
    public CssTextAlignCSS21() {
	// depends on user agent and writing direction
    }

    /**
     * Create a new CssTextAlign
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssTextAlignCSS21(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {

	super(ac, expression, check);

	Object val = get();

	if(val instanceof CssString) {
	    throw new InvalidParamException("value", val.toString(),
		    getPropertyName(), ac);
	}
    }

    public CssTextAlignCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
}
