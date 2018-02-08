//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// rewritten 2018 by Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2018.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec https://www.w3.org/TR/2014/WD-css-ruby-1-20140805/#propdef-ruby-position
 */

public class CssRubyPosition extends org.w3c.css.properties.css.CssRubyPosition {
    public static final CssIdent[] allowed_values;

    // Here we use the 9 November 2017 draft values
    // https://github.com/w3c/csswg-drafts/blob/85d366602c156b1735da30d99b68f3fe0908ed60/css-ruby-1/Overview.bs

    static {
        String[] _allowed_values = {"over", "under", "inter-character"};
        allowed_values = new CssIdent[_allowed_values.length];
        int i = 0;
        for (String s : _allowed_values) {
            allowed_values[i++] = CssIdent.getIdent(s);
        }
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
     * Create new CssRubyPosition
     */
    public CssRubyPosition() {
        value = initial;
    }

    /**
     * Create new CssRubyPosition
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorrect
     */
    public CssRubyPosition(ApplContext ac, CssExpression expression,
                           boolean check) throws InvalidParamException {
        setByUser();
        CssValue val = expression.getValue();

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        if (val.getType() != CssTypes.CSS_IDENT) {
            throw new InvalidParamException("value",
                    expression.getValue(),
                    getPropertyName(), ac);
        }
        if (inherit.equals(val)) {
            value = inherit;
        } else {
            value = getAllowedIdent((CssIdent) val);
            if (value == null) {
                throw new InvalidParamException("value",
                        expression.getValue(),
                        getPropertyName(), ac);
            }
        }
        expression.next();
    }


    public CssRubyPosition(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}
