//
// $Id$
// @author Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 2010  World Wide Web Consortium (MIT, ERCIM, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLayerList;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;

/**
 * @spec http://www.w3.org/TR/2009/CR-css3-background-20091217/#the-background-origin
 */

public class CssBackgroundOrigin extends org.w3c.css.properties.css.CssBackgroundOrigin {

    private static CssIdent border_box;
    private static CssIdent padding_box;
    private static CssIdent content_box;

    static {
        border_box = CssIdent.getIdent("border-box");
        padding_box = CssIdent.getIdent("padding-box");
        content_box = CssIdent.getIdent("content-box");
    }

    public static boolean isMatchingIdent(CssIdent ident) {
        return (border_box.equals(ident) ||
                padding_box.equals(ident) ||
                content_box.equals(ident));
    }

    /**
     * Create a new CssBackgroundClip
     */
    public CssBackgroundOrigin() {
        value = padding_box;
    }

    /**
     * Create a new CssBackgroundClip
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Incorrect value
     */
    public CssBackgroundOrigin(ApplContext ac, CssExpression expression,
                               boolean check) throws InvalidParamException {

        ArrayList<CssValue> values = new ArrayList<CssValue>();

        CssValue val = expression.getValue();
        char op;

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();
            switch (val.getType()) {
                case CssTypes.CSS_IDENT:
                    if (inherit.equals(val)) {
                        // if we got inherit after other values, fail
                        // if we got more than one value... fail
                        if ((values.size() > 0) || (expression.getCount() > 1)) {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        values.add(inherit);
                        break;
                    } else if (border_box.equals(val)) {
                        values.add(border_box);
                        break;
                    } else if (content_box.equals(val)) {
                        values.add(content_box);
                        break;
                    } else if (padding_box.equals(val)) {
                        values.add(padding_box);
                        break;
                    }
                default:
                    throw new InvalidParamException("value", val,
                            getPropertyName(), ac);
            }
            expression.next();
            if (!expression.end() && (op != COMMA)) {
                throw new InvalidParamException("operator",
                        ((new Character(op)).toString()), ac);
            }
        }
        if (values.size() == 1) {
            value = values.get(0);
        } else {
            value = new CssLayerList(values);
        }
    }

    public CssBackgroundOrigin(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (padding_box == value);
    }

}