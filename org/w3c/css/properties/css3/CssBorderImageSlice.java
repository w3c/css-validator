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
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec http://www.w3.org/TR/2012/CR-css3-background-20120417/#border-image-slice
 */
public class CssBorderImageSlice extends org.w3c.css.properties.css.CssBorderImageSlice {

    public static final CssIdent fill;

    static {
        fill = CssIdent.getIdent("fill");
    }

    public final static CssIdent getMatchingIdent(CssIdent ident) {
        return (fill.equals(ident)) ? fill : null;
    }

    /**
     * Create a new CssBorderImageSlice
     */
    public CssBorderImageSlice() {
        value = initial;
    }

    /**
     * Creates a new CssBorderImageSlice
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssBorderImageSlice(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {

        CssValueList valueList = new CssValueList();
        if (check && expression.getCount() > 5) {
            throw new InvalidParamException("unrecognize", ac);
        }
        CssValue val;
        char op;
        boolean gotFill = false;

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();

            switch (val.getType()) {
                case CssTypes.CSS_NUMBER:
                    CssNumber num = (CssNumber)val;
                    if (!num.isPositive()) {
                        throw new InvalidParamException("negative-value", expression.getValue(),
                                getPropertyName(), ac);
                    }
                    valueList.add(val);
                    break;
                case CssTypes.CSS_PERCENTAGE:
                    CssPercentage percent = (CssPercentage) val;
                    if (!percent.isPositive()) {
                        throw new InvalidParamException("negative-value", expression.getValue(),
                                getPropertyName(), ac);
                    }
                    if (percent.floatValue() > 100f) {
                        ac.getFrame().addWarning("out-of-range", percent.toString());
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
                    if (fill.equals(val)) {
                        // fill is first or last and can't appear twice
                        if (gotFill || (valueList.size() != 0 && expression.getRemainingCount() > 1)) {
                            throw new InvalidParamException("value", val.toString(),
                                    getPropertyName(), ac);
                        }
                        gotFill = true;
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
        // we add fill last to normalize
        if (gotFill) {
            valueList.add(fill);
        }
        value = (valueList.size() == 1) ? valueList.get(0) : valueList;
    }

    public CssBorderImageSlice(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}

