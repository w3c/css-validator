//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2016.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec https://www.w3.org/TR/2017/CR-css-grid-1-20170209/#propdef-grid-row-gap
 */
public class CssGridRowGap extends org.w3c.css.properties.css.CssGridRowGap {

    /**
     * Create a new CssGridRowGap
     */
    public CssGridRowGap() {
        value = initial;
    }

    /**
     * Creates a new CssGridRowGap
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssGridRowGap(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        value = checkSyntax(ac, expression, check, this);
    }

    // as it is ued in other places, use a static checker function.
    public static CssValue checkSyntax(ApplContext ac, CssExpression expression, boolean check,
                                       CssProperty caller)
            throws InvalidParamException {
        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }
        caller.setByUser();

        CssValue val, value;
        char op;

        val = expression.getValue();
        op = expression.getOperator();

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                val.getCheckableValue().checkEqualsZero(ac, caller);
                value = val;
                break;
            case CssTypes.CSS_LENGTH:
            case CssTypes.CSS_PERCENTAGE:
                value = val;
                break;
            case CssTypes.CSS_IDENT:
                if (inherit.equals(val)) {
                    value = inherit;
                    break;
                }
            default:
                throw new InvalidParamException("value",
                        val.toString(),
                        caller.getPropertyName(), ac);

        }
        expression.next();
        return value;
    }

    public CssGridRowGap(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}

