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
 * @spec https://www.w3.org/TR/2018/CR-css-flexbox-1-20181119/#propdef-align-items
 * replaced by
 * https://www.w3.org/TR/2018/WD-css-align-3-20180423/#propdef-align-items
 */
public class CssAlignItems extends org.w3c.css.properties.css.CssAlignItems {

    public static final CssIdent[] single_align_items_values;

    static {
        String[] _single_values = {"normal", "stretch"};
        single_align_items_values = new CssIdent[_single_values.length];
        int i = 0;
        for (String s : _single_values) {
            single_align_items_values[i++] = CssIdent.getIdent(s);
        }
    }

    public static CssIdent getSingleAlignItemsValue(CssIdent ident) {
        for (CssIdent id : single_align_items_values) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
    }

    /**
     * Create a new CssAlignItems
     */
    public CssAlignItems() {
        value = initial;
    }

    /**
     * Creates a new CssAlignItems
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssAlignItems(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        if (check && expression.getCount() > 2) {
            throw new InvalidParamException("unrecognize", ac);
        }
        setByUser();

        value = parseAlignItems(ac, expression, this);
        if (!expression.end()) {
            throw new InvalidParamException("unrecognize", ac);
        }

    }

    public static CssValue parseAlignItems(ApplContext ac, CssExpression expression,
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
            value = getSingleAlignItemsValue(ident);
            if (value != null) {
                expression.next();
                return value;
            }
            // now try the two-values position, starting first with only one.
            if (CssAlignContent.baseline.equals(ident)) {
                expression.next();
                return CssAlignContent.baseline;
            }
            value = CssAlignSelf.getSelfPosition(ident);
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
                value = CssAlignSelf.getSelfPosition((CssIdent) val);
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

    public CssAlignItems(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}

