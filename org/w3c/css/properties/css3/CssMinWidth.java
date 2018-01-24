//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2016.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssCheckableValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2007/WD-css3-box-20070809/#min-width
 * @spec https://www.w3.org/TR/2016/CR-css-flexbox-1-20160526/#min-size-auto
 * @spec https://www.w3.org/TR/2016/WD-css-sizing-3-20160512/#width-height-keywords
 */
public class CssMinWidth extends org.w3c.css.properties.css.CssMinWidth {

    public static final CssIdent[] allowed_values;

    static {
        // auto from flexbox, fill to fit-content from css-sizing
        String[] _allowed_values = {"auto", "fill", "max-content", "min-content", "fit-content"};

        allowed_values = new CssIdent[_allowed_values.length];
        int i = 0;
        for (String s : _allowed_values) {
            allowed_values[i++] = CssIdent.getIdent(s);
        }
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
     * Create a new CssMinWidth
     */
    public CssMinWidth() {
        value = initial;
    }

    /**
     * Creates a new CssMinWidth
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssMinWidth(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        CssValue val = expression.getValue();

        setByUser();

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                val.getCheckableValue().checkEqualsZero(ac, this);
                value = val;
                break;
            case CssTypes.CSS_LENGTH:
            case CssTypes.CSS_PERCENTAGE:
                CssCheckableValue l = val.getCheckableValue();
                l.checkPositiveness(ac, this);
                value = val;
                break;
            case CssTypes.CSS_IDENT:
                if (inherit.equals(val)) {
                    value = inherit;
                } else {
                    CssIdent id = getAllowedIdent((CssIdent) val);
                    if (id != null) {
                        value = id;
                    } else {
                        throw new InvalidParamException("unrecognize", ac);
                    }
                }
                break;
            default:
                throw new InvalidParamException("value", expression.getValue(),
                        getPropertyName(), ac);
        }
        expression.next();
    }

    public CssMinWidth(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}

