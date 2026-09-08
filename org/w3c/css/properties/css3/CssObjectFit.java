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
import org.w3c.css.values.CssImage;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec https://www.w3.org/TR/2025/WD-css-images-4-20250930/#the-object-fit
 */
public class CssObjectFit extends org.w3c.css.properties.css.CssObjectFit {

    public static final CssIdent[] allowed_values, allowed_unique_values;
    public static final CssValue scale_down, contain, cover;

    static {
        String[] _allowed_values = {"fill", "contain", "cover", "none", "scale-down"};
        String[] _allowed_unique_values = {"fill", "none"};
        allowed_values = new CssIdent[_allowed_values.length];
        allowed_unique_values = new CssIdent[_allowed_unique_values.length];
        int i = 0;
        for (String s : _allowed_values) {
            allowed_values[i++] = CssIdent.getIdent(s);
        }
        i = 0;
        for (String s : _allowed_unique_values) {
            allowed_unique_values[i++] = CssIdent.getIdent(s);
        }
        scale_down = CssIdent.getIdent("scale-down");
        contain = CssIdent.getIdent("contain");
        cover = CssIdent.getIdent("cover");
    }

    public static CssIdent getAllowedUniqueIdent(CssIdent ident) {
        for (CssIdent id : allowed_unique_values) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
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
     * Create a new CssObjectFit
     */
    public CssObjectFit() {
        value = initial;
    }

    /**
     * Creates a new CssObjectFit
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException Expressions are incorrect
     */
    public CssObjectFit(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        if (check && expression.getCount() > 2) {
            throw new InvalidParamException("unrecognize", ac);
        }
        setByUser();

        ArrayList<CssValue> values = new ArrayList<CssValue>();
        CssValue val;
        char op;
        boolean got_scale_down = false;
        boolean got_contain = false;

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();

            if (val.getType() != CssTypes.CSS_IDENT) {
                throw new InvalidParamException("value",
                        val.toString(),
                        getPropertyName(), ac);
            }
            CssIdent ident = val.getIdent();
            if (CssImage.isVerticalIdent(ident) || getAllowedUniqueIdent(ident) != null) {
                if (expression.getCount() > 1) {
                    throw new InvalidParamException("value", val.toString(),
                            getPropertyName(), ac);
                }
                values.add(val);
                break;
            }
            if (!CssIdent.isCssWide(ident) && getAllowedIdent(ident) == null) {
                throw new InvalidParamException("value",
                        val.toString(),
                        getPropertyName(), ac);
            }
            value = val;
            if (!got_scale_down && scale_down.equals(ident)) {
                got_scale_down = true;
                values.add(val);
            } else if (!got_contain && (contain.equals(ident) || cover.equals(ident))) {
                got_contain = true;
                values.add(val);
            } else {
                throw new InvalidParamException("value",
                        val.toString(),
                        getPropertyName(), ac);
            }
            if (op != SPACE) {
                throw new InvalidParamException("operator", op,
                        getPropertyName(), ac);
            }
            expression.next();
        }
        value = (values.size() == 1) ? values.get(0) : new CssValueList(values);
        expression.next();
    }

    public CssObjectFit(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}

