//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2015.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec https://www.w3.org/TR/2017/PR-css-ui-3-20171214/#propdef-resize
 */
public class CssResize extends org.w3c.css.properties.css.CssResize {

    private static CssIdent[] allowed_values;

    static {
        String id_values[] = {"none", "both", "horizontal", "vertical"};
        allowed_values = new CssIdent[id_values.length];
        int i = 0;
        for (String s : id_values) {
            allowed_values[i++] = CssIdent.getIdent(s);
        }
    }

    public static CssIdent getMatchingIdent(CssIdent ident) {
        for (CssIdent id : allowed_values) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
    }

    /**
     * Create a new CssResize
     */
    public CssResize() {
        value = initial;
    }

    /**
     * Creates a new CssResize
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssResize(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
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
        } else {
            val = getMatchingIdent((CssIdent) val);
            if (val == null) {
                throw new InvalidParamException("value",
                        expression.getValue(),
                        getPropertyName(), ac);
            }
            value = val;
        }
        expression.next();
    }

    public CssResize(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}

