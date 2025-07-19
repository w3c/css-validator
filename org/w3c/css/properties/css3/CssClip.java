// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import static org.w3c.css.values.CssOperator.COMMA;
import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec https://www.w3.org/TR/2021/CRD-css-masking-1-20210805/#propdef-clip
 * @deprecated
 */
@Deprecated
public class CssClip extends org.w3c.css.properties.css.CssClip {

    public static final CssIdent auto = CssIdent.getIdent("auto");

    /**
     * Create a new CssClip
     */
    public CssClip() {
        value = initial;
    }

    /**
     * Creates a new CssClip
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssClip(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }
        setByUser();

        CssValue val = expression.getValue();
        ac.getFrame().addWarning("deprecatedproperty", getPropertyName());

        switch (val.getType()) {
            case CssTypes.CSS_FUNCTION:
                CssFunction func = val.getFunction();
                String funcname = func.getName().toLowerCase();
                if (!funcname.equals("rect")) {
                    throw new InvalidParamException("value", val,
                            getPropertyName(), ac);
                }
                checkShape(ac, func.getParameters(), this);
                value = val;
                break;
            case CssTypes.CSS_IDENT:
                CssIdent ident = val.getIdent();
                if (CssIdent.isCssWide(ident)) {
                    value = val;
                    break;
                } else if (auto.equals(ident)) {
                    value = val;
                    break;
                }
                // let if fail.
            default:
                throw new InvalidParamException("value", val,
                        getPropertyName(), ac);
        }
        expression.next();
    }

    public CssClip(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    static void checkShape(ApplContext ac, CssExpression expression,
                           CssProperty caller) throws InvalidParamException {
        if (expression.getCount() < 4) {
            throw new InvalidParamException("few-value", caller.getPropertyName(), ac);
        }
        if (expression.getCount() > 4) {
            throw new InvalidParamException("unrecognize", ac);
        }
        CssValue val;
        char op, firstop;
        firstop = expression.getOperator();
        if (firstop == SPACE) {
            // we add a warning for SPACE
            ac.getFrame().addWarning("shape-separator");
        }
        for (int i = 0; i < 4; i++) {
            val = expression.getValue();
            op = expression.getOperator();
            switch (val.getType()) {
                case CssTypes.CSS_NUMBER:
                    val.getCheckableValue().checkEqualsZero(ac, caller);
                case CssTypes.CSS_LENGTH:
                    break;
                case CssTypes.CSS_IDENT:
                    if (auto.equals(val.getIdent())) {
                        break;
                    }
                default:
                    throw new InvalidParamException("value",
                            val.toString(),
                            caller.getPropertyName(), ac);
            }
            expression.next();
            // as the spec was unclear, we allow comma or space
            // but no mix of the two.
            // special case at the end as default separator is SPACE
            if (((op != firstop) || (op != COMMA && op != SPACE)) && !expression.end()) {
                throw new InvalidParamException("shape-separator",
                        Character.toString(op), ac);
            }
        }
    }
}

