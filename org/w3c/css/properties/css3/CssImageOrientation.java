//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio University, Beihang, 2012-2019
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssAngle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

/**
 * @spec https://www.w3.org/TR/2019/CR-css-images-3-20191010/#propdef-image-orientation
 * @deprecated
 */
public class CssImageOrientation extends org.w3c.css.properties.css.CssImageOrientation {

    public static final CssIdent[] allowed_values;
    public static final CssIdent flip;

    static {
        String[] _allowed_values = {"from-image", "none", "flip"};
        allowed_values = new CssIdent[_allowed_values.length];
        int i = 0;
        for (String s : _allowed_values) {
            allowed_values[i++] = CssIdent.getIdent(s);
        }
        flip = CssIdent.getIdent("flip");
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
     * Create a new CssImageOrientation
     */
    public CssImageOrientation() {
        value = initial;
    }

    /**
     * Creates a new CssImageOrientation
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssImageOrientation(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        if (check && expression.getCount() > 2) {
            throw new InvalidParamException("unrecognize", ac);
        }
        setByUser();

        CssValue val;
        char op;

        val = expression.getValue();
        op = expression.getOperator();

        ac.getFrame().addWarning("deprecatedproperty", getPropertyName());

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                val.getCheckableValue().checkEqualsZero(ac, this);
            case CssTypes.CSS_ANGLE:
                value = val;
                // check for optional 'flip'
                if (op == CssOperator.SPACE && expression.getRemainingCount() > 1) {
                    expression.next();
                    val = expression.getValue();
                    op = expression.getOperator();
                    if (val.getType() != CssTypes.CSS_IDENT) {
                        throw new InvalidParamException("value",
                                val.toString(),
                                getPropertyName(), ac);
                    }
                    if (!flip.equals((CssIdent) val)) {
                        throw new InvalidParamException("value",
                                val.toString(),
                                getPropertyName(), ac);
                    }
                    ArrayList<CssValue> v = new ArrayList<>(2);
                    v.add(value);
                    v.add(flip);
                    value = new CssValueList(v);
                }
                break;
            case CssTypes.CSS_IDENT:
                if (check && expression.getCount() > 1) {
                    throw new InvalidParamException("unrecognize", ac);
                }
                if (inherit.equals(val)) {
                    value = inherit;
                    break;
                }
                value = getAllowedIdent((CssIdent) val);
                if (value != null) {
                    // single 'flip' will be pretty-printed as 0deg flip
                    if (flip == value) {
                        ArrayList<CssValue> v = new ArrayList<>(2);
                        v.add(new CssAngle());
                        v.add(flip);
                        value = new CssValueList(v);
                    }
                    break;
                }
                // null value, bail out
            default:
                throw new InvalidParamException("value",
                        val.toString(),
                        getPropertyName(), ac);
        }
        expression.next();
    }

    public CssImageOrientation(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}

