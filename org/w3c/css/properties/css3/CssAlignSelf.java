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
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec https://www.w3.org/TR/2016/CR-css-flexbox-1-20160526/#propdef-align-self
 * replaced by
 * @spec https://www.w3.org/TR/2018/WD-css-align-3-20180423/#align-self-property
 */
public class CssAlignSelf extends org.w3c.css.properties.css.CssAlignSelf {

    public static final CssIdent[] single_align_self_values;
    public static final CssIdent[] self_position;

    static {
        String[] _single_values = {"auto", "normal", "stretch"};
        single_align_self_values = new CssIdent[_single_values.length];
        int i = 0;
        for (String s : _single_values) {
            single_align_self_values[i++] = CssIdent.getIdent(s);
        }
        String[] _self_position_values = {"center", "start", "end", "self-start",
                "self-end", "flex-start", "flex-end"};
        self_position = new CssIdent[_self_position_values.length];
        i = 0;
        for (String s : _self_position_values) {
            self_position[i++] = CssIdent.getIdent(s);
        }
    }

    public static CssIdent getSingleAlignSelfValue(CssIdent ident) {
        for (CssIdent id : single_align_self_values) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
    }

    public static CssIdent getSelfPosition(CssIdent ident) {
        for (CssIdent id : self_position) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
    }

    /**
     * Create a new CssAlignSelf
     */
    public CssAlignSelf() {
        value = initial;
    }

    /**
     * Creates a new CssAlignSelf
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssAlignSelf(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        if (check && expression.getCount() > 2) {
            throw new InvalidParamException("unrecognize", ac);
        }
        setByUser();

        value = parseAlignSelf(ac, expression, this);
        if (!expression.end()) {
            throw new InvalidParamException("unrecognize", ac);
        }
    }

    public static CssValue parseAlignSelf(ApplContext ac, CssExpression expression,
                                          CssProperty caller)
            throws InvalidParamException {
        CssValue val, value;
        ArrayList<CssValue> values = new ArrayList<>();
        char op;

        val = expression.getValue();
        op = expression.getOperator();

        if (val.getType() == CssTypes.CSS_IDENT) {
            CssIdent ident = (CssIdent) val;
            if (inherit.equals(ident)) {
                if (expression.getCount() > 1) {
                    throw new InvalidParamException("value", val.toString(),
                            caller.getPropertyName(), ac);
                }
                expression.next();
                return inherit;
            }
            value = getSingleAlignSelfValue(ident);
            if (value != null) {
                expression.next();
                return value;
            }
            // now try the two-values position, starting first with only one.
            if (CssAlignContent.baseline.equals(ident)) {
                expression.next();
                return CssAlignContent.baseline;
            }
            value = getSelfPosition(ident);
            if (value != null) {
                expression.next();
                return value;
            }
            // ok, at that point we need two values.
            value = CssAlignContent.getBaselineQualifier(ident);
            if (value != null) {
                values.add(value);
                if (op != SPACE) {
                    throw new InvalidParamException("operator",
                            ((new Character(op)).toString()), ac);
                }
                expression.next();
                if (expression.end()) {
                    throw new InvalidParamException("unrecognize", ac);
                }
                val = expression.getValue();
                if (val.getType() != CssTypes.CSS_IDENT || !CssAlignContent.baseline.equals(val)) {
                    throw new InvalidParamException("value", val.toString(),
                            caller.getPropertyName(), ac);
                }
                values.add(CssAlignContent.baseline);
                expression.next();
                return new CssValueList(values);
            }
            value = CssAlignContent.getOverflowPosition(ident);
            if (value != null) {
                values.add(value);
                if (op != SPACE) {
                    throw new InvalidParamException("operator",
                            ((new Character(op)).toString()), ac);
                }
                expression.next();
                if (expression.end()) {
                    throw new InvalidParamException("unrecognize", ac);
                }
                val = expression.getValue();
                if (val.getType() != CssTypes.CSS_IDENT) {
                    throw new InvalidParamException("value", val.toString(),
                            caller.getPropertyName(), ac);
                }
                value = getSelfPosition((CssIdent) val);
                if (value == null) {
                    throw new InvalidParamException("value", val.toString(),
                            caller.getPropertyName(), ac);
                }
                values.add(value);
                expression.next();
                return new CssValueList(values);
            }

        }
        throw new InvalidParamException("value",
                val.toString(),
                caller.getPropertyName(), ac);
    }


    public CssAlignSelf(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}

