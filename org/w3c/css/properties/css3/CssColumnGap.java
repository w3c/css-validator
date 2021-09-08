//
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten 2010 Yves Lafon <ylafon@w3.org>
//
// COPYRIGHT (c) 1995-2018 World Wide Web Consortium, (MIT, ERCIM, Keio, Beihang)
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
 * @spec https://www.w3.org/TR/2021/WD-css-multicol-1-20210212/#cg
 * @spec https://www.w3.org/TR/2020/WD-css-align-3-20200421/#propdef-column-gap
 */

public class CssColumnGap extends org.w3c.css.properties.css.CssColumnGap {

    static CssIdent normal;

    static {
        normal = CssIdent.getIdent("normal");
    }

    /**
     * Create a new CssColumnGap
     */
    public CssColumnGap() {
        value = initial;
    }

    /**
     * Create a new CssColumnGap
     */
    public CssColumnGap(ApplContext ac, CssExpression expression,
                        boolean check) throws InvalidParamException {
        setByUser();
        CssValue val = expression.getValue();

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                val.getCheckableValue().checkEqualsZero(ac, this);
                value = val;
                break;
            case CssTypes.CSS_PERCENTAGE:
            case CssTypes.CSS_LENGTH:
                CssCheckableValue l = val.getCheckableValue();
                l.checkPositiveness(ac, this);
                value = val;
                break;
            case CssTypes.CSS_IDENT:
                CssIdent ident = val.getIdent();
                if (normal.equals(ident)) {
                    value = val;
                    break;
                }
                if (CssIdent.isCssWide(ident)) {
                    value = val;
                    break;
                }
            default:
                throw new InvalidParamException("value", expression.getValue(),
                        getPropertyName(), ac);
        }
        expression.next();
    }

    public CssColumnGap(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (value == initial);
    }

}
