// $Id$
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css2;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.HashMap;

/**
 * @version $Revision$
 * @spec http://www.w3.org/TR/2008/REC-CSS2-20080411/text.html#white-space-prop
 */
public class CssWhiteSpace extends org.w3c.css.properties.css.CssWhiteSpace {

    CssValue value;

    public static HashMap<String, CssIdent> allowed_values;

    static {
        allowed_values = new HashMap<String, CssIdent>();
        String[] WHITESPACE = {
                "normal", "pre", "nowrap"
        };

        for (String aWS : WHITESPACE) {
            allowed_values.put(aWS, CssIdent.getIdent(aWS));
        }
    }

    /**
     * Create a new CssWhiteSpace
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          values are incorrect
     */
    public CssWhiteSpace(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        CssValue val = expression.getValue();
        setByUser();

        if (val.getType() == CssTypes.CSS_IDENT) {
            if (inherit.equals(val)) {
                value = inherit;
            } else {
                value = allowed_values.get(val.toString());
            }
            if (value != null) {
                expression.next();
                return;
            }
        }
        throw new InvalidParamException("value", expression.getValue(),
                getPropertyName(), ac);
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
        return (inherit == value);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        return value.toString();
    }

    public CssWhiteSpace(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

}
