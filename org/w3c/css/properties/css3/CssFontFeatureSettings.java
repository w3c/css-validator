//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssCheckableValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLayerList;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;
import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec https://www.w3.org/TR/2018/REC-css-fonts-3-20180920/#propdef-font-feature-settings
 */
public class CssFontFeatureSettings extends org.w3c.css.properties.css.CssFontFeatureSettings {

    public static final CssIdent on, off, normal;

    static {
        on = CssIdent.getIdent("on");
        off = CssIdent.getIdent("off");
        normal = CssIdent.getIdent("normal");
    }

    /**
     * Create a new CssFontFeatureSettings
     */
    public CssFontFeatureSettings() {
        value = initial;
    }

    /**
     * Creates a new CssFontFeatureSettings
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssFontFeatureSettings(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {

        setByUser();
        CssValue val;
        ArrayList<CssValue> values;
        CssExpression singleExpr = null;
        CssValue b_val = null;
        char op;

        values = new ArrayList<CssValue>();
        // we just accumulate values and check at validation
        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();

            if (inherit.equals(val)) {
                if (expression.getCount() > 1) {
                    throw new InvalidParamException("value", val,
                            getPropertyName(), ac);
                }
                value = inherit;
                expression.next();
                return;
            }
            if (singleExpr == null) {
                singleExpr = new CssExpression();
            }
            // we will check later
            singleExpr.addValue(val);
            singleExpr.setOperator(op);
            expression.next();

            if (!expression.end()) {
                // incomplete value followed by a comma... it's complete!
                if (op == COMMA) {
                    singleExpr.setOperator(SPACE);
                    b_val = check(ac, singleExpr);
                    values.add(b_val);
                    singleExpr = null;
                } else if ((op != SPACE)) {
                    throw new InvalidParamException("operator",
                            Character.toString(op), ac);
                }
            }
        }
        // if we reach the end in a value that can come in pair
        if (singleExpr != null) {
            b_val = check(ac, singleExpr);
            values.add(b_val);
        }
        if (values.size() == 1) {
            value = values.get(0);
        } else {
            value = new CssLayerList(values);
        }
    }

    public CssFontFeatureSettings(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    public CssValue check(ApplContext ac, CssExpression exp)
            throws InvalidParamException {
        CssValue val;
        if (exp.getCount() > 2) {
            throw new InvalidParamException("unrecognize", ac);
        }
        val = exp.getValue();
        switch (val.getType()) {

            case CssTypes.CSS_STRING:
                String s = val.toString();
                // limit of 4 characters + two surrounding quotes
                if (s.length() != 6) {
                    throw new InvalidParamException("value",
                            s, getPropertyName(), ac);
                }
                break;
            case CssTypes.CSS_IDENT:
                if (normal.equals(val) && exp.getCount() == 1) {
                    return normal;
                }
                //else we throw
            default:
                throw new InvalidParamException("value",
                        val.toString(),
                        getPropertyName(), ac);
        }
        if (exp.getCount() == 1) {
            return val;
        }
        ArrayList<CssValue> v = new ArrayList<CssValue>(2);
        v.add(val);
        // now check the second value
        exp.next();
        val = exp.getValue();
        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                CssCheckableValue n = val.getCheckableValue();
                n.checkPositiveness(ac, this);
                v.add(val);
                break;
            case CssTypes.CSS_IDENT:
                if (on.equals(val)) {
                    v.add(on);
                    break;
                }
                if (off.equals(val)) {
                    v.add(off);
                    break;
                }
                // let it fail
            default:
                throw new InvalidParamException("value",
                        val.toString(),
                        getPropertyName(), ac);
        }
        return (v.size() == 1) ? v.get(0) : new CssValueList(v);
    }

}

