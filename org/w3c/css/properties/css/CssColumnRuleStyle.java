// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten Yves lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 1995-2010  World Wide Web Consortium
// (MIT, ERCIM, Keio University)
//
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssBorderStyleCSS2;
import org.w3c.css.properties.css3.Css3Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * http://www.w3.org/TR/2009/CR-css3-multicol-20091217/#column-rule-style
 * 
 * Name:  	column-rule-style
 * Value: 	&lt;Ôborder-styleÕ&gt;
 * Initial: 	none
 * Applies to: 	multicol elements
 * Inherited: 	no
 * Percentages: 	N/A
 * Media: 	visual
 * Computed value: 	specified value
 *
 * The Ôcolumn-rule-styleÕ property sets the style of the rule between columns
 * of an element. The &lt;border-style&gt; values are defined in [CSS21].
 */

public class CssColumnRuleStyle extends CssProperty {

    private static final String propertyName = "column-rule-style";

    CssIdent value;

    /**
     * Create a new CssColumnRuleStyle
     */
    public CssColumnRuleStyle() {
        value = none;
    }

    /**
     * Create a new CssColumnRuleStyle
     *
     * @param ac the context
     * @param expression The expression for this property
     * @param check if check on length is required
     * @throws InvalidParamException Incorrect value
     */
    public CssColumnRuleStyle(ApplContext ac, CssExpression expression,
                              boolean check) throws InvalidParamException {

        setByUser();
        CssValue val = expression.getValue();
        // too many values
        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }
        // we only use Css Ident part of the CssBorderStyle acceptable values
        if (val.getType() != CssTypes.CSS_IDENT) {
            throw new InvalidParamException("value",
                    expression.getValue(),
                    getPropertyName(), ac);
        }
        CssIdent ident = (CssIdent) val;
        if (inherit.equals(ident)) {
            value = inherit;
        } else if (CssBorderStyleCSS2.acceptable_values.contains(ident)) {
            value = ident;
        } else {
            throw new InvalidParamException("value",
                    expression.getValue(),
                    getPropertyName(), ac);
        }
        expression.next();
    }

    public CssColumnRuleStyle(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        if (((Css3Style) style).cssColumnRuleStyle != null)
            style.addRedefinitionWarning(ac, this);
        ((Css3Style) style).cssColumnRuleStyle = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css3Style) style).getColumnRuleStyle();
        } else {
            return ((Css3Style) style).cssColumnRuleStyle;
        }
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
     * Returns the name of this property
     */
    public final String getPropertyName() {
        return propertyName;
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
        return none.equals(value);
    }
}
