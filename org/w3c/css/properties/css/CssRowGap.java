//
// Author: Yves Lafon <ylafon@w3.org>
//
// COPYRIGHT (c) 2018 World Wide Web Consortium, (MIT, ERCIM, Keio, Beihang)
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css3.Css3Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * @since CSS3
 */

public class CssRowGap extends CssProperty {

    private static final String propertyName = "row-gap";

    public CssValue value;

    /**
     * Create a new CssRowGap
     */
    public CssRowGap() {
    }

    /**
     * Create a new CssRowGap
     */
    public CssRowGap(ApplContext ac, CssExpression expression,
                     boolean check) throws InvalidParamException {

        throw new InvalidParamException("unrecognize", ac);
    }

    public CssRowGap(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        if (((Css3Style) style).cssRowGap != null)
            style.addRedefinitionWarning(ac, this);
        ((Css3Style) style).cssRowGap = this;

    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css3Style) style).getRowGap();
        } else {
            return ((Css3Style) style).cssRowGap;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssRowGap &&
                value.equals(((CssRowGap) property).value));
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
        return false;
    }

}