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
 * @spec https://www.w3.org/TR/2018/WD-css-align-3-20180423/#justify-items-property
 */
public class CssJustifyItems extends org.w3c.css.properties.css.CssJustifyItems {

    public static final CssIdent[] self_position_extras, legacy_qualifier,
            single_justify_items_values;
    public static final CssIdent legacy;

    static {
        legacy = CssIdent.getIdent("legacy");
        String[] _single_values = {"normal", "stretch"};
        single_justify_items_values = new CssIdent[_single_values.length];
        int i = 0;
        for (String s : _single_values) {
            single_justify_items_values[i++] = CssIdent.getIdent(s);
        }
        String[] _self_position_extra_values = {"left", "right"};
        self_position_extras = new CssIdent[_self_position_extra_values.length];
        i = 0;
        for (String s : _self_position_extra_values) {
            self_position_extras[i++] = CssIdent.getIdent(s);
        }
        String[] _legacy_qualifier_values = {"left", "right", "center"};
        legacy_qualifier = new CssIdent[_legacy_qualifier_values.length];
        i = 0;
        for (String s : _legacy_qualifier_values) {
            legacy_qualifier[i++] = CssIdent.getIdent(s);
        }
    }

    public static CssIdent getSelfPositionAddExtras(CssIdent ident) {
        for (CssIdent id : self_position_extras) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return CssAlignSelf.getSelfPosition(ident);
    }

    public static CssIdent getLegacyQualifier(CssIdent ident) {
        for (CssIdent id : legacy_qualifier) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
    }

    public static CssIdent getSingleJustifyItemsValue(CssIdent ident) {
        for (CssIdent id : single_justify_items_values) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
    }

    /**
     * Create a new CssAlignSelf
     */
    public CssJustifyItems() {
        value = initial;
    }

    /**
     * Creates a new CssAlignSelf
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssJustifyItems(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        if (check && expression.getCount() > 2) {
            throw new InvalidParamException("unrecognize", ac);
        }
        setByUser();

        value = parseJustifyItems(ac, expression, this);
        if (!expression.end()) {
            throw new InvalidParamException("unrecognize", ac);
        }
    }

    public static CssValue parseJustifyItems(ApplContext ac, CssExpression expression,
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
            value = getSingleJustifyItemsValue(ident);
            if (value != null) {
                expression.next();
                return value;
            }
            // now try the two-values position, starting first with only one.
            if (CssAlignContent.baseline.equals(ident)) {
                expression.next();
                return CssAlignContent.baseline;
            }
            // we must check the extras first
            value = getLegacyQualifier(ident);
            // legacy qualifier are part of self-position, so we may have nothing
            if (value != null) {
                expression.next();
                if (expression.end()) {
                    return value;
                }
                val = expression.getValue();
                if (val.getType() != CssTypes.CSS_IDENT || !legacy.equals(val)) {
                    return value;
                }
                // ok, we got a leagacy, operator check and return
                if (op != SPACE) {
                    throw new InvalidParamException("operator",
                            ((new Character(op)).toString()), ac);
                }
                values.add(value);
                values.add(legacy);
                expression.next();
                return new CssValueList(values);
            }

            value = getSelfPositionAddExtras(ident);
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
                value = getSelfPositionAddExtras((CssIdent) val);
                if (value == null) {
                    throw new InvalidParamException("value", val.toString(),
                            caller.getPropertyName(), ac);
                }
                values.add(value);
                expression.next();
                return new CssValueList(values);
            }
            // now we need to do guess work and possibly backtrack.
            if (legacy.equals(ident)) {
                // we can have nothing or a qualifier here.
                expression.next();
                if (expression.end()) {
                    return legacy;
                }
                val = expression.getValue();
                if (val.getType() != CssTypes.CSS_IDENT) {
                    return legacy; // let the caller check and fail if necessary
                }
                value = getLegacyQualifier((CssIdent) val);
                if (value == null) {
                    return legacy;
                }
                // so we got something, check the operator
                if (op != SPACE) {
                    throw new InvalidParamException("operator",
                            ((new Character(op)).toString()), ac);
                }
                values.add(legacy);
                values.add(value);
                expression.next();
                return new CssValueList(values);
            }

        }
        throw new InvalidParamException("value",
                val.toString(),
                caller.getPropertyName(), ac);
    }


    public CssJustifyItems(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}

