// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten 2010 Yves lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 1995-2010  World Wide Web Consortium (MIT, ERCIM and Keio)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2011/CR-css3-multicol-20110412/#column-rule-color
 */

public class CssColumnRuleColor extends org.w3c.css.properties.css.CssColumnRuleColor {

    CssValue color;

    /**
     * Create a new CssColumnRuleColor
     */
    public CssColumnRuleColor() {
        color = initial;
    }

    /**
     * Create a new CssColumnRuleColor
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Incorrect value
     */
    public CssColumnRuleColor(ApplContext ac, CssExpression expression,
                              boolean check) throws InvalidParamException {

        setByUser();
        CssValue val = expression.getValue();

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }
        if (inherit.equals(val)) {
            if (expression.getCount() > 1) {
                throw new InvalidParamException("value",
                        val, getPropertyName(), ac);
            }
            color = inherit;
            expression.next();
        } else if (currentColor.equals(val)) {
            color = currentColor;
            expression.next();
        } else {
            try {
                // we use the latest version of CssColor, aka CSS3
                // instead of using CSS21 colors + transparent per spec
                CssColor tcolor = new CssColor(ac, expression, check);
                color = tcolor.getColor();
            } catch (InvalidParamException e) {
                throw new InvalidParamException("value",
                        expression.getValue(),
                        getPropertyName(), ac);
            }
        }
    }

    public CssColumnRuleColor(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssColumnRuleColor &&
                color.equals(((CssColumnRuleColor) property).color));
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return color;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
        return (inherit.equals(color) || currentColor.equals(color));
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
        return color.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (initial == color);
    }
}
