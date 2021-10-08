// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, Beihang, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec https://www.w3.org/TR/2021/CRD-css-text-3-20210422/#propdef-white-space
 */
public class CssWhiteSpace extends org.w3c.css.properties.css.CssWhiteSpace {


    public static CssIdent[] allowed_values;

    static {
        String[] WHITESPACE = {
                "normal", "pre", "nowrap", "pre-wrap", "break-spaces", "pre-line"
        };
        allowed_values = new CssIdent[WHITESPACE.length];
        int i = 0;
        for (String aWS : WHITESPACE) {
            allowed_values[i++] = CssIdent.getIdent(aWS);
        }
    }

    public static final CssIdent getMatchingIdent(CssIdent ident) {
        for (CssIdent id : allowed_values) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
    }

    /*
     * Create a new CssWhiteSpace
     */
    public CssWhiteSpace() {
        value = initial;
    }

    /**
     * Create a new CssWhiteSpace
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException values are incorrect
     */
    public CssWhiteSpace(ApplContext ac, CssExpression expression, boolean check)
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
        if (CssIdent.isCssWide(val.getIdent())) {
            value = val;
        } else if (getMatchingIdent(val.getIdent()) != null) {
            value = val;
        } else {
            throw new InvalidParamException("value", expression.getValue(),
                    getPropertyName(), ac);
        }
        expression.next();

    }

    public CssWhiteSpace(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}
