//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio and Beihang University, 2018.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec https://www.w3.org/TR/2018/CR-css-writing-modes-3-20180524/#propdef-text-combine-upright
 */
public class CssTextCombineUpright extends org.w3c.css.properties.css.CssTextCombineUpright {

    public static final CssIdent[] allowed_values;

    static {
        String[] _allowed_values = {"all", "none"};
        allowed_values = new CssIdent[_allowed_values.length];
        int i = 0;
        for (String s : _allowed_values) {
            allowed_values[i++] = CssIdent.getIdent(s);
        }
    }

    static CssIdent all;

    static {
        all = CssIdent.getIdent("all");
    }

    public static CssIdent getAllowedIdent(CssIdent ident) {
        for (CssIdent id : allowed_values) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
    }

    /**
     * Create a new CssTextCombineUpright
     */
    public CssTextCombineUpright() {
        value = initial;
    }

    /**
     * Creates a new CssTextCombineUpright
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssTextCombineUpright(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        CssValue val = expression.getValue();

        setByUser();
        if (val.getType() != CssTypes.CSS_IDENT) {
            throw new InvalidParamException("value", expression.getValue(),
                    getPropertyName(), ac);
        }
        if (val.equals(inherit)) {
            value = inherit;
        } else {
            value = getAllowedIdent((CssIdent) val);
            if (value == null) {
                throw new InvalidParamException("value", expression.getValue(),
                        getPropertyName(), ac);
            }
        }
        expression.next();
    }

    public CssTextCombineUpright(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}

