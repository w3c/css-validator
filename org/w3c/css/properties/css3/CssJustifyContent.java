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
 * original
 * @spec https://www.w3.org/TR/2018/CR-css-flexbox-1-20181119/#propdef-justify-content
 * replaced by
 * @spec https://www.w3.org/TR/2018/WD-css-align-3-20180423/#propdef-justify-content
 */
public class CssJustifyContent extends org.w3c.css.properties.css.CssJustifyContent {

    public static final CssIdent[] content_position_extras;
    public static final CssIdent normal;

    static {
        normal = CssIdent.getIdent("normal");
        String[] _content_position_extras_values = {"left", "right"};
        content_position_extras = new CssIdent[_content_position_extras_values.length];
        int i = 0;
        for (String s : _content_position_extras_values) {
            content_position_extras[i++] = CssIdent.getIdent(s);
        }
    }

    public static CssIdent getContentPositionAndExtras(CssIdent ident) {
        for (CssIdent id : content_position_extras) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return CssAlignContent.getContentPosition(ident);
    }

    /**
     * Create a new CssJustifyContent
     */
    public CssJustifyContent() {
        value = initial;
    }

    /**
     * Creates a new CssJustifyContent
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssJustifyContent(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        if (check && expression.getCount() > 2) {
            throw new InvalidParamException("unrecognize", ac);
        }
        setByUser();

        value = parseJustifyContent(ac, expression, this);
        if (!expression.end()) {
            throw new InvalidParamException("unrecognize", ac);
        }
    }

    public static CssValue parseJustifyContent(ApplContext ac, CssExpression expression,
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
            if (normal.equals(ident)) {
                expression.next();
                return normal;
            }
            value = CssAlignContent.getContentDistribution(ident);
            if (value != null) {
                expression.next();
                return value;
            }
            // now try the two-values position, starting first with only one.
            value = getContentPositionAndExtras(ident);
            if (value != null) {
                expression.next();
                return value;
            }
            // ok, at that point we need two values.
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
                value = getContentPositionAndExtras((CssIdent) val);
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


    public CssJustifyContent(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}

