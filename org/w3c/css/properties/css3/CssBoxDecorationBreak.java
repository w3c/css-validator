// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten 2012 by Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 1995-2012  World Wide Web Consortium (MIT, ERCIM, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec https://www.w3.org/TR/2017/CR-css-break-3-20170209/#propdef-box-decoration-break
 */

public class CssBoxDecorationBreak extends org.w3c.css.properties.css.CssBoxDecorationBreak {

    public static CssIdent slice;
    public static CssIdent clone;

    static {
        slice = CssIdent.getIdent("slice");
        clone = CssIdent.getIdent("clone");
    }

    /**
     * Create new CssBoxDecorationBreak
     */
    public CssBoxDecorationBreak() {
        value = slice;
    }

    /**
     * Create new CssBoxDecorationBreak
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorrect
     */
    public CssBoxDecorationBreak(ApplContext ac, CssExpression expression,
                                 boolean check) throws InvalidParamException {
        setByUser();
        CssValue val = expression.getValue();

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        if (val.getType() != CssTypes.CSS_IDENT) {
            throw new InvalidParamException("value",
                    expression.getValue(),
                    getPropertyName(), ac);
        }
        // ident, so inherit, or allowed value
        if (inherit.equals(val)) {
            value = inherit;
        } else if (slice.equals(val)) {
            value = slice;
        } else if (clone.equals(val)) {
            value = clone;
        } else {
            throw new InvalidParamException("value",
                    expression.getValue(),
                    getPropertyName(), ac);
        }
        expression.next();
    }


    public CssBoxDecorationBreak(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return slice == value;
    }
}
