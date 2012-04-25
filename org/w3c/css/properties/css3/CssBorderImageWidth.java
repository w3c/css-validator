// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @version $Revision$
 * @spec http://www.w3.org/TR/2012/CR-css3-background-20120417/#the-border-image-width
 */
public class CssBorderImageWidth extends org.w3c.css.properties.css.CssBorderImageWidth {

    public static final CssIdent auto;

    static {
        auto = CssIdent.getIdent("auto");
    }

    public final static CssIdent getMatchingIdent(CssIdent ident) {
        return (auto.equals(ident)) ? auto : null;
    }

    /**
     * Create a new CssBorderImageWidth
     */
    public CssBorderImageWidth() {
        value = initial;
    }

    /**
     * Creates a new CssBorderImageWidth
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssBorderImageWidth(ApplContext ac, CssExpression expression, boolean check)
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
                case CssTypes.CSS_PERCENTAGE:
                    CssPercentage percent = (CssPercentage) val;
                    if (!percent.isPositive()) {
                        throw new InvalidParamException("negative-value", percent,
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
                    if (auto.equals(val)) {
                        // fill is first or last and can't appear twice
                        valueList.add(auto);
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

    public CssBorderImageWidth(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}

