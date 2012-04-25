// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * @see
 */
public class CssBorderLeftColor extends org.w3c.css.properties.css.CssBorderLeftColor {

    /**
     * Create a new CssBorderLeftColor
     */
    public CssBorderLeftColor() {
        value = initial;
    }

    /**
     * Creates a new CssBorderLeftColor
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssBorderLeftColor(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        value = CssBorderColor.checkBorderSideColor(ac, this, expression, check);
    }

    public CssBorderLeftColor(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}

