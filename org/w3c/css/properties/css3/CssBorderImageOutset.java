// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec http://www.w3.org/TR/2012/CR-css3-background-20120417/#border-image-outset
 */
public class CssBorderImageOutset extends org.w3c.css.properties.css.CssBorderImageOutset {

    /**
     * Create a new CssBorderImageOutset
     */
    public CssBorderImageOutset() {
        value = initial;
    }

    /**
     * Creates a new CssBorderImageOutset
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssBorderImageOutset(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {

        CssValueList valueList = new CssValueList();
        if (check && expression.getCount() > 4) {
            throw new InvalidParamException("unrecognize", ac);
        }
        CssValue val;
        char op;

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();

            switch (val.getType()) {
                case CssTypes.CSS_NUMBER:
                    CssNumber num = (CssNumber)val;
                    if (!num.isPositive()) {
                        throw new InvalidParamException("negative-value", num,
                                getPropertyName(), ac);
                    }
                    valueList.add(val);
                    break;
                case CssTypes.CSS_LENGTH:
                    CssLength length = (CssLength)val;
                    if (!length.isPositive()) {
                        throw new InvalidParamException("negative-value", length,
                                getPropertyName(), ac);
                    }
                    valueList.add(val);
                    break;
                case CssTypes.CSS_IDENT:
                    if (inherit.equals(val)) {
                        if (expression.getCount() > 1) {
                            throw new InvalidParamException("unrecognize", ac);
                        }
                        valueList.add(inherit);
                        break;
                    }
                    // unrecognized ident, let it fail
                default:
                    throw new InvalidParamException("value", val.toString(),
                            getPropertyName(), ac);
            }
            expression.next();
            if (op != SPACE) {
                throw new InvalidParamException("operator",
                        Character.toString(op),
                        ac);
            }
        }
        value = (valueList.size() == 1) ? valueList.get(0) : valueList;
    }

    public CssBorderImageOutset(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}

