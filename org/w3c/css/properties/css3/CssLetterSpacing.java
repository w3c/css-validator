// $Id$
//
// (c) COPYRIGHT MIT, INRIA and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2011/WD-css3-text-20110901/#letter-spacing0
 */
public class CssLetterSpacing extends org.w3c.css.properties.css.CssLetterSpacing {

    private CssValue value[] = new CssValue[3];
    int nb_values;
    private static CssIdent normal = CssIdent.getIdent("normal");

    /**
     * Create a new CssLetterSpacing.
     */
    public CssLetterSpacing() {
        value[0] = normal;
    }

    /**
     * Create a new CssLetterSpacing with an expression
     *
     * @param expression The expression
     * @throws org.w3c.css.util.InvalidParamException
     *          The expression is incorrect
     */
    public CssLetterSpacing(ApplContext ac, CssExpression expression,
                            boolean check) throws InvalidParamException {
        nb_values = expression.getCount();

        if (check && nb_values > 3) {
            throw new InvalidParamException("unrecognize", ac);
        }

        for (int i = 0; i < nb_values; i++) {
            CssValue val = expression.getValue();

            setByUser();

            switch (val.getType()) {
                case CssTypes.CSS_NUMBER:
                    val = ((CssNumber) val).getLength();
                case CssTypes.CSS_LENGTH:
                    value[i] = val;
                    break;
                case CssTypes.CSS_PERCENTAGE:
                    value[i] = val;
                    break;
                case CssTypes.CSS_IDENT:
                    if (inherit.equals(val)) {
                        // inherit can only be alone
                        if (nb_values > 1) {
                            throw new InvalidParamException("value", expression.getValue(),
                                    getPropertyName(), ac);
                        }
                        value[i] = inherit;
                        break;
                    } else if (normal.equals(val)) {
                        value[i] = normal;
                        break;
                    }
                default:
                    throw new InvalidParamException("value", expression.getValue(),
                            getPropertyName(), ac);
            }
            if (i+1 < nb_values && expression.getOperator() != CssOperator.SPACE) {
               throw new InvalidParamException("operator", CssOperator.SPACE, ac);
            }
            expression.next();
        }
    }

    public CssLetterSpacing(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return value;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
        return value[0] == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (nb_values == 1) {
            return value[0].toString();
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < nb_values; i++) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(value[i]);
        }
        return sb.toString();
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssLetterSpacing &&
                value.equals(((CssLetterSpacing) property).value));
    }
}
