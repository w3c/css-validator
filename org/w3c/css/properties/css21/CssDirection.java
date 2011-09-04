//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css21;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * See
 * http://www.w3.org/TR/2011/REC-CSS2-20110607/visuren.html#propdef-direction
 *
 * 'direction'
    Value:  	ltr | rtl | inherit
    Initial:  	ltr
    Applies to:  	all elements, but see prose
    Inherited:  	yes
    Percentages:  	N/A
    Media:  	visual
    Computed value:  	as specified
 */
public class CssDirection extends org.w3c.css.properties.css.CssDirection {

    /**
     * Create a new CssDirection
     */
    public CssDirection() {
        value = ltr;
    }

    /**
     * Create a new CssDirection
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          The expression is incorrect
     */
    public CssDirection(ApplContext ac, CssExpression expression,
                        boolean check) throws InvalidParamException {

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        CssValue val = expression.getValue();

        setByUser();
        if (val.equals(inherit)) {
            value = inherit;
            expression.next();
        } else if (val.equals(ltr)) {
            value = ltr;
            expression.next();
        } else if (val.equals(rtl)) {
            value = rtl;
            expression.next();
        } else {
            throw new InvalidParamException("value", expression.getValue(),
                    getPropertyName(), ac);
        }

    }

    public CssDirection(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return value == ltr;
    }

}
