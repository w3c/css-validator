// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
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
 * @spec http://www.w3.org/TR/2012/WD-css3-text-20120814/#text-decoration0
 */
public class CssTextDecoration extends org.w3c.css.properties.css.CssTextDecoration {

    public static final CssIdent blink;

    static {
        blink = CssIdent.getIdent("blink");
    }

    CssTextDecorationLine lineValue = null;
    CssTextDecorationColor colorValue = null;
    CssTextDecorationStyle styleValue = null;

    /**
     * Create a new CssTextDecoration
     */
    public CssTextDecoration() {
        value = initial;
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
        if (check && expression.getCount() > 6) {
            throw new InvalidParamException("unrecognize", ac);
        }
        setByUser();

        CssValue val;
        char op;

        CssIdent styValue = null;
        CssIdent bliValue = null;
        CssValue colValue = null;
        CssExpression linExp = null;

        int state = 0;

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();

            if (val.getType() != CssTypes.CSS_IDENT) {
                if (colValue != null) {
                    throw new InvalidParamException("value",
                            val.toString(),
                            getPropertyName(), ac);
                }
                CssColor c = new CssColor(ac, expression, false);
                colValue = c.getColor();
                state *= 2;
                // constructor is providing expression.next()
            } else {
                // so we have an ident...
                CssIdent ident = (CssIdent) val;
                if (inherit.equals(ident)) {
                    value = inherit;
                    if (check && expression.getCount() != 1) {
                        throw new InvalidParamException("value",
                                val.toString(),
                                getPropertyName(), ac);
                    }
                    expression.next();
                } else {
                    boolean match = false;
                    CssIdent id;
                    // check for style (single value)
                    if (styValue == null) {
                        id = CssTextDecorationStyle.getMatchingIdent(ident);
                        if (id != null) {
                            styValue = id;
                            match = true;
                            state *= 2;
                            expression.next();
                        }
                    }
                    // blink (single value)
                    if (!match && bliValue == null) {
                        if (blink.equals(ident)) {
                            bliValue = blink;
                            match = true;
                            state *= 2;
                            expression.next();
                        }
                    }
                    // line... up to 3 values
                    // state should be 0 (nothing yet) or 1 (parsing line)
                    // if 2 or more, we got something else in between!
                    if (!match && state <= 1) {
                        id = CssTextDecorationLine.getAllowedValue(ident);
                        if (id != null) {
                            state = 1;
                            match = true;
                            if (linExp == null) {
                                linExp = new CssExpression();
                            }
                            linExp.addValue(val);
                            expression.next();
                        }
                    }
                    // if it fails, it must be a color, then...
                    if (!match && colValue == null) {
                        try {
                            CssColor c = new CssColor(ac, expression, false);
                            colValue = c.getColor();
                            state *= 2;
                        } catch (InvalidParamException iex) {
                            // as it is a catchall, we do not know if the intent was a color
                            // or something else, so let's use a generic message
                            throw new InvalidParamException("value", val.toString(),
                                    getPropertyName(), ac);
                        }
                        // no need to check match, as if it is not a color
                        // an exception would be fired
                    }
                }
            }

            if (op != CssOperator.SPACE) {
                throw new InvalidParamException("operator",
                        Character.toString(op), ac);
            }
        }
        int got = 0;
        // now check the value
        if (linExp != null) {
            lineValue = new CssTextDecorationLine(ac, linExp, check);
            got++;
            value = lineValue.value;
        }
        // fill the individual values if needed
        if (colValue != null) {
            colorValue = new CssTextDecorationColor();
            colorValue.value = colValue;
            value = colValue;
            got++;
        }
        if (styValue != null) {
            styleValue = new CssTextDecorationStyle();
            styleValue.value = styValue;
            value = styValue;
            got++;
        }
        if (bliValue != null) {
            value = bliValue;
            got++;
        }
        // and generate the value;
        if (value != inherit && got > 1) {
            ArrayList<CssValue> v = new ArrayList<CssValue>(4);
            if (lineValue != null) {
                v.add(lineValue.value);
            }
            if (styValue != null) {
                v.add(styValue);
            }
            if (colValue != null) {
                v.add(colValue);
            }
            if (bliValue != null) {
                v.add(bliValue);
            }
            value = new CssValueList(v);
        }
    }

    public CssTextDecoration(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        super.addToStyle(ac, style);
        // and the individual...
        if (colorValue != null) {
            colorValue.addToStyle(ac, style);
        }
        if (styleValue != null) {
            styleValue.addToStyle(ac, style);
        }
        if (lineValue != null) {
            lineValue.addToStyle(ac, style);
        }
    }
}

