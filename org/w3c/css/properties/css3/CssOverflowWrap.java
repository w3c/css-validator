//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

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
 * @spec hhttps://www.w3.org/TR/2017/WD-css-text-3-20170822/#overflow-wrap-property
 * <p/>
 * Note that word-wrap is also an alias for this.
 */
public class CssOverflowWrap extends org.w3c.css.properties.css.CssOverflowWrap {

    public static final CssIdent normal, break_word, break_spaces;

    static {
        normal = CssIdent.getIdent("normal");
        break_word = CssIdent.getIdent("break-word");
        break_spaces = CssIdent.getIdent("break-spaces");
    }

    /**
     * Create a new CssOverflowWrap
     */
    public CssOverflowWrap() {
        value = initial;
    }

    /**
     * Creates a new CssOverflowWrap
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssOverflowWrap(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        if (check && expression.getCount() > 2) {
            throw new InvalidParamException("unrecognize", ac);
        }
        setByUser();

        CssValue val;
        char op;

        ArrayList<CssValue> values = new ArrayList<>();
        boolean gotNormal = false;
        boolean gotBw = false;
        boolean gotBs = false;

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();

            if (val.getType() == CssTypes.CSS_IDENT) {
                CssIdent ident = (CssIdent) val;
                if (inherit.equals(ident)) {
                    if (expression.getCount() > 1) {
                        throw new InvalidParamException("unrecognize", ac);
                    }
                    value = inherit;
                } else if (normal.equals(ident)) {
                    if (gotNormal || gotBw || gotBs) {
                        throw new InvalidParamException("unrecognize", ac);
                    }
                    value = normal;
                    gotNormal = true;
                } else if (break_word.equals(ident)) {
                    if (gotNormal || gotBw) {
                        throw new InvalidParamException("unrecognize", ac);
                    }
                    values.add(break_word);
                    gotBw = true;
                } else if (break_spaces.equals(ident)) {
                    if (gotNormal || gotBs) {
                        throw new InvalidParamException("unrecognize", ac);
                    }
                    values.add(break_spaces);
                    gotBs = true;
                } else {
                    throw new InvalidParamException("value",
                            val.toString(),
                            getPropertyName(), ac);
                }
            } else {
                throw new InvalidParamException("value",
                        val.toString(),
                        getPropertyName(), ac);
            }
            if (op != SPACE) {
                throw new InvalidParamException("operator",
                        ((new Character(op)).toString()),
                        ac);
            }
            expression.next();
        }
        if (values.size() > 0) {
            value = values.size() == 1 ? values.get(0) : new CssValueList(values);
        }
    }

    public CssOverflowWrap(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}

