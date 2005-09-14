// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssBorderFaceColorCSS2;
import org.w3c.css.properties.css1.CssBorderLeftColorCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * CssBorderLeftColorCSS21<br />
 * Created: Aug 31, 2005 2:10:19 PM<br />
 */
public class CssBorderLeftColorCSS21 extends CssBorderLeftColorCSS2 {

    /**
     * Create a new CssBorderLeftColorCSS21 with an another CssBorderFaceColorCSS2
     *
     * @param another An another face.
     */
    public CssBorderLeftColorCSS21(CssBorderFaceColorCSS2 another) {
	super(another);
    }

    /**
     * Create a new CssBorderLeftColor
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderLeftColorCSS21(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	setFace(new CssBorderFaceColorCSS21(ac, expression));
    }

    public CssBorderLeftColorCSS21(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }
}
