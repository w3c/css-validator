// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT, ERCIM and Keio, 1997-2010.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.Css1Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 */
public class CssZIndex extends CssProperty {

    private static final String propertyName = "z-index";

    CssValue value;

    private static CssIdent auto = CssIdent.getIdent("auto");

    /**
     * Create a new CssZIndex
     */
    public CssZIndex() {
        value = auto;
    }

    /**
     * Create a new CssZIndex
     *
     * @param ac    The context
     * @param expression The expression for this property
     * @param check      true will test the number of parameters
     * @throws InvalidParamException The expression is incorrect
     */
    public CssZIndex(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        CssValue val = expression.getValue();

        setByUser();
        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                if (((CssNumber) val).isInteger()) {
                    value = val;
                    break;
                }
                throw new InvalidParamException("integer",
                    val.toString(),
                    getPropertyName(), ac);
            case CssTypes.CSS_IDENT:
                CssIdent ide = (CssIdent) val;
                if (inherit.equals(ide)) {
                    value = inherit;
                    break;
                } else if (auto.equals(ide)) {
                    value = auto;
                    break;
                }
            default:
                throw new InvalidParamException("value", expression.getValue(),
                        getPropertyName(), ac);
        }
        expression.next();
    }

    /**
     * Create a new CssZIndex
     *
     * @param ac, the Context
     * @param expression The expression for this property
     * @throws InvalidParamException The expression is incorrect
     */
    public CssZIndex(ApplContext ac, CssExpression expression)
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
     * Returns the name of this property
     */
    public final String getPropertyName() {
        return propertyName;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
        return (value == inherit);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        return value.toString();
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        Css1Style style0 = (Css1Style) style;
        if (style0.cssZIndex != null) {
            style0.addRedefinitionWarning(ac, this);
        }
        style0.cssZIndex = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css1Style) style).getZIndex();
        } else {
            return ((Css1Style) style).cssZIndex;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     * @return boolean
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssZIndex &&
                value.equals(((CssZIndex) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (value == auto);
    }

}
