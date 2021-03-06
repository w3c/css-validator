// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.tv;

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
 * @spec http://www.w3.org/TR/2011/REC-CSS2-20110607/text.html#propdef-text-decoration
 * @spec http://www.w3.org/TR/2003/CR-css-tv-20030514#section-properties
 */
public class CssTextDecoration extends org.w3c.css.properties.css.CssTextDecoration {

    public static final CssIdent underline, overline, line_through;

    static {
        underline = CssIdent.getIdent("underline");
        overline = CssIdent.getIdent("overline");
        line_through = CssIdent.getIdent("line-through");
    }

    /**
     * Create a new CssTextDecoration
     */
    public CssTextDecoration() {
    }

    /**
     * Creates a new CssTextDecoration
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssTextDecoration(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        if (check && expression.getCount() > 3) {
            throw new InvalidParamException("unrecognize", ac);
        }
        setByUser();

        CssValue val;
        char op;

        CssIdent undValue = null;
        CssIdent oveValue = null;
        CssIdent linValue = null;

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
                if (undValue == null && underline.equals(ident)) {
                    undValue = underline;
                } else if (oveValue == null && overline.equals(ident)) {
                    oveValue = overline;
                } else if (linValue == null && line_through.equals(ident)) {
                    linValue = line_through;
                } else {
                    throw new InvalidParamException("value",
                            val.toString(),
                            getPropertyName(), ac);
                }
                nbgot++;
                if (expression.getRemainingCount() == 1 || (!check && nbgot == 4)) {
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
            if (undValue != null) {
                v.add(undValue);
            }
            if (oveValue != null) {
                v.add(oveValue);
            }
            if (linValue != null) {
                v.add(linValue);
            }
            value = (nbgot > 1) ? new CssValueList(v) : v.get(0);
        }
        expression.next();
    }

    public CssTextDecoration(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}

