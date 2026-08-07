package org.w3c.css.properties.css3.fontface;

import static org.w3c.css.properties.css3.CssFontWidth.getAllowedValue;
import static org.w3c.css.values.CssOperator.SPACE;

import java.util.ArrayList;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

/**
 * @spec http://www.w3.org/TR/2008/REC-CSS2-20080411/fonts.html#propdef-font-stretch
 */
public class CSSFontStretch extends org.w3c.css.properties.css.fontface.CSSFontStretch {

    public static final CssIdent auto = CssIdent.getIdent("auto");

    /**
     * Create a new CSSFontStretch
     */
    public CSSFontStretch() {
        value = initial;
    }

    /**
     * Creates a new CSSFontStretch
     *
     * @param expression The expression for this property
     * @throws InvalidParamException Expressions are incorrect
     */
    public CSSFontStretch(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        if (check && expression.getCount() > 2) {
            throw new InvalidParamException("unrecognize", ac);
        }

        setByUser();

        char op;
        CssValue val;
        ArrayList<CssValue> values = new ArrayList<>();

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();

            switch (val.getType()) {
                case CssTypes.CSS_IDENT:
                    CssIdent id = val.getIdent();
                    if (auto.equals(id)) {
                        if (expression.getCount() > 1) {
                            throw new InvalidParamException("unrecognize", ac);
                        }
                        values.add(val);
                        break;
                    }
                    if (getAllowedValue(id) != null) {
                        values.add(val);
                        break;
                    }
                default:
                    throw new InvalidParamException("value",
                            val.toString(),
                            getPropertyName(), ac);
            }
            if (op != SPACE) {
                throw new InvalidParamException("operator",
                        Character.toString(op), ac);
            }
            expression.next();
        }
        if (values.isEmpty()) {
            throw new InvalidParamException("few-value", getPropertyName(), ac);
        }
        value = (values.size() == 1) ? values.get(0) : new CssValueList(values);
    }

    public CSSFontStretch(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}