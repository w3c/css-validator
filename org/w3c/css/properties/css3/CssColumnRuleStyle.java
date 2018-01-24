// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten Yves lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 1995-2010  World Wide Web Consortium
// (MIT, ERCIM, Keio University)
//
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2011/CR-css3-multicol-20110412/#column-rule-style
 */

public class CssColumnRuleStyle extends org.w3c.css.properties.css.CssColumnRuleStyle {

    CssValue value;

    /**
     * Create a new CssColumnRuleStyle
     */
    public CssColumnRuleStyle() {
        value = initial;
    }

    /**
     * Create a new CssColumnRuleStyle
     *
     * @param ac         the context
     * @param expression The expression for this property
     * @param check      if check on length is required
     * @throws org.w3c.css.util.InvalidParamException
     *          Incorrect value
     */
    public CssColumnRuleStyle(ApplContext ac, CssExpression expression,
                              boolean check) throws InvalidParamException {

        setByUser();
        value = CssBorderStyle.checkBorderSideStyle(ac, this, expression, check);
    }

    public CssColumnRuleStyle(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssColumnRuleStyle &&
                value.equals(((CssColumnRuleStyle) property).value));
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return value;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
        return (inherit == value);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
        return value.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return value == initial;
    }
}
