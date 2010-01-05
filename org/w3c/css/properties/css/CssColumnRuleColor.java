// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten 2010 Yves lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 1995-2010  World Wide Web Consortium (MIT, ERCIM and Keio)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssColor;
import org.w3c.css.properties.css3.Css3Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * http://www.w3.org/TR/2009/CR-css3-multicol-20091217/#column-rule-color
 *
 * Name:  	column-rule-color
 * Value: 	&lt;color&gt;
 * Initial: 	same as for ÔcolorÕ property [CSS21]
 * Applies to: 	multicol elements
 * Inherited: 	no
 * Percentages: 	N/A
 * Media: 	visual
 * Computed value: 	the same as the computed value for the ÔcolorÕ
 * property [CSS21]
 *
 * This property sets the color of the column rule. The &lt;color&gt; values are
 * defined in [CSS21].
 */

public class CssColumnRuleColor extends CssProperty {

    private static final String propertyName = "column-rule-color";

    CssColor color;

    /**
     * Create a new CssColumnRuleColor
     */
    public CssColumnRuleColor() {
        // nothing to do
    }

    /**
     * Create a new CssColumnRuleColor
     *
     * @param expression The expression for this property
     * @throws InvalidParamException Incorrect value
     */
    public CssColumnRuleColor(ApplContext ac, CssExpression expression,
                              boolean check) throws InvalidParamException {

        setByUser();
        CssValue val = expression.getValue();

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        try {
            // we use the latest version of CssColor, aka CSS3
            // instead of using CSS21 colors + transparent per spec.
            color = new CssColor(ac, expression);
        } catch (InvalidParamException e) {
            throw new InvalidParamException("value",
                    expression.getValue(),
                    getPropertyName(), ac);
        }
    }

    public CssColumnRuleColor(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        if (((Css3Style) style).cssColumnRuleColor != null)
            style.addRedefinitionWarning(ac, this);
        ((Css3Style) style).cssColumnRuleColor = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css3Style) style).getColumnRuleColor();
        } else {
            return ((Css3Style) style).cssColumnRuleColor;
        }
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
     * Returns the name of this property
     */
    public final String getPropertyName() {
        return propertyName;
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
        return inherit.equals(color);
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
        return false;
    }
}
