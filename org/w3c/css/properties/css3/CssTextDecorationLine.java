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
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-text-20120814/#text-decoration-line0
 */
public class CssTextDecorationLine extends org.w3c.css.properties.css.CssTextDecorationLine {

    public static final CssIdent underline, overline, line_through;

    static {
        underline = CssIdent.getIdent("underline");
        overline = CssIdent.getIdent("overline");
        line_through = CssIdent.getIdent("line-through");
    }

    public static final CssIdent getAllowedValue(CssIdent ident) {
        if (none.equals(ident)) {
            return none;
        }
        if (underline.equals(ident)) {
            return underline;
        }
        if (overline.equals(ident)) {
            return overline;
        }
        if (line_through.equals(ident)) {
            return line_through;
        }
        return null;
    }

    /**
     * Create a new CssTextDecorationLine
     */
    public CssTextDecorationLine() {
        value = initial;
    }

    /**
     * Creates a new CssTextDecorationLine
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssTextDecorationLine(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        if (check && expression.getCount() > 3) {
            throw new InvalidParamException("unrecognize", ac);
        }
        setByUser();

        CssValue val;
        char op;

        CssIdent underlineValue = null;
        CssIdent overlineValue = null;
        CssIdent lineThroughValue = null;

        val = expression.getValue();
        op = expression.getOperator();

        if (val.getType() != CssTypes.CSS_IDENT) {
            throw new InvalidParamException("value",
                    val.toString(),
                    getPropertyName(), ac);
        }

        CssIdent ident = (CssIdent) val;
        if (inherit.equals(ident)) {
            value = inherit;
            if (check && expression.getCount() != 1) {
                throw new InvalidParamException("value",
                        val.toString(),
                        getPropertyName(), ac);
            }
        } else if (none.equals(ident)) {
            value = none;
            if (check && expression.getCount() != 1) {
                throw new InvalidParamException("value",
                        val.toString(),
                        getPropertyName(), ac);
            }
        } else {
            int nbgot = 0;
            do {
                if (underlineValue == null && underline.equals(ident)) {
                    underlineValue = underline;
                } else if (overlineValue == null && overline.equals(ident)) {
                    overlineValue = overline;
                } else if (lineThroughValue == null && line_through.equals(ident)) {
                    lineThroughValue = line_through;
                } else {
                    throw new InvalidParamException("value",
                            val.toString(),
                            getPropertyName(), ac);
                }
                nbgot++;
                if (expression.getRemainingCount() == 1 || (!check && nbgot == 3)) {
                    // if we have both, exit
                    // (needed only if check == false...
                    break;
                }
                if (op != CssOperator.SPACE) {
                    throw new InvalidParamException("operator",
                            Character.toString(op), ac);
                }
                expression.next();
                val = expression.getValue();
                op = expression.getOperator();
                if (val.getType() != CssTypes.CSS_IDENT) {
                    throw new InvalidParamException("value",
                            val.toString(),
                            getPropertyName(), ac);
                }
                ident = (CssIdent) val;
            } while (!expression.end());
            // now construct the value
            ArrayList<CssValue> v = new ArrayList<CssValue>(nbgot);
            if (underlineValue != null) {
                v.add(underlineValue);
            }
            if (overlineValue != null) {
                v.add(overlineValue);
            }
            if (lineThroughValue != null) {
                v.add(lineThroughValue);
            }
            value = (nbgot > 1) ? new CssValueList(v) : v.get(0);
        }
        expression.next();
    }

    public CssTextDecorationLine(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}

