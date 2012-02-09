//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec  http://www.w3.org/TR/2011/REC-CSS2-20110607/text.html#propdef-letter-spacing
 * @version $Revision$
 */
public class CssLetterSpacing extends org.w3c.css.properties.css.CssLetterSpacing {

    private CssValue value;
    private static CssIdent normal = CssIdent.getIdent("normal");

    /**
     * Create a new CssLetterSpacing.
     */
    public CssLetterSpacing() {
        value = normal;
    }

    /**
     * Create a new CssLetterSpacing with an expression
     *
     * @param expression The expression
     * @throws org.w3c.css.util.InvalidParamException The expression is incorrect
     */
    public CssLetterSpacing(ApplContext ac, CssExpression expression,
                            boolean check) throws InvalidParamException {

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        CssValue val = expression.getValue();

        setByUser();

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                val = ((CssNumber) val).getLength();
            case CssTypes.CSS_LENGTH:
                value = val;
                break;
            case CssTypes.CSS_IDENT:
                if (inherit.equals(val) || normal.equals(val)) {
                    value = val;
                    break;
                }
            default:
                throw new InvalidParamException("value", expression.getValue(),
                        getPropertyName(), ac);
        }
        expression.next();
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
        return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        return value.toString();
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
