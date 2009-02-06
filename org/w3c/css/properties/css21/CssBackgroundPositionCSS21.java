// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssBackgroundPositionCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 * CssBackgroundPositionCSS21<br />
 * Created: Aug 31, 2005 5:45:30 PM<br />
 */
public class CssBackgroundPositionCSS21 extends CssBackgroundPositionCSS2 {

    /**
     * Create a new CssBackgroundPositionCSS2
     */
    public CssBackgroundPositionCSS21() {
	super();
    }

    /**
     * Creates a new CssBackgroundPositionCSS2
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBackgroundPositionCSS21(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	super(ac, expression, check);
    }

    public CssBackgroundPositionCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

}
