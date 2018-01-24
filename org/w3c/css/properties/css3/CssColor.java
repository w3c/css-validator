// $Id$
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css3;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2011/REC-css3-color-20110607/#color0
 */
public class CssColor extends org.w3c.css.properties.css.CssColor {

    CssValue color;
    String attrvalue = null;

    /**
     * Create a new CssColor
     */
    public CssColor() {
        color = inherit;
    }

    /**
     * Set the value of the property
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorrect
     */
    public CssColor(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        org.w3c.css.values.CssColor tempcolor = new org.w3c.css.values.CssColor();

        CssValue val = expression.getValue();
        setByUser();

        switch (val.getType()) {
            case CssTypes.CSS_HASH_IDENT:
                org.w3c.css.values.CssColor c = new org.w3c.css.values.CssColor();
                c.setShortRGBColor(val.toString(), ac);
                color = c;
                break;
            case CssTypes.CSS_IDENT:
                if (inherit.equals(val)) {
                    color = inherit;
                } else if (currentColor.equals(val)) {
                    color = currentColor;
                } else {
                    color = new org.w3c.css.values.CssColor(ac, (String) val.get());
                }
                break;
            case CssTypes.CSS_COLOR:
                color = val;
                break;
            case CssTypes.CSS_FUNCTION:
                CssFunction attr = (CssFunction) val;
                CssExpression params = attr.getParameters();
                String fname = attr.getName();

                if (fname.equals("attr")) {
                    CssValue v1 = params.getValue();
                    params.next();
                    CssValue v2 = params.getValue();
                    if ((params.getCount() != 2)) {
                        throw new InvalidParamException("value",
                                params.getValue(),
                                getPropertyName(), ac);
                    } else if (v1.getType() != CssTypes.CSS_IDENT) {
                        throw new InvalidParamException("value",
                                params.getValue(),
                                getPropertyName(), ac);

                    } else if (!(v2.toString().equals("color"))) {
                        throw new InvalidParamException("value",
                                params.getValue(),
                                getPropertyName(), ac);
                    } else {
                        attrvalue = "attr(" + v1 + ", " + v2 + ')';
                    }
                } else {
                    throw new InvalidParamException("value",
                            params.getValue(),
                            getPropertyName(), ac);
                }
                break;
            default:
                throw new InvalidParamException("value", expression.getValue(),
                        getPropertyName(), ac);
        }
        expression.next();
    }

    public CssColor(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return color;
    }

    /**
     * Returns the color
     */
    public org.w3c.css.values.CssColor getColor() {
        if (inherit.equals(color) || currentColor.equals(color)) {
            /*
			 System.err.println("[ERROR] org.w3c.css.properties.CssColor");
             System.err.println("[ERROR] value is inherited");
           */
            return null;
        } else {
            return (org.w3c.css.values.CssColor) color;
        }
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
        return inherit.equals(color) || currentColor.equals(color);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (attrvalue != null) {
            return attrvalue;
        } else {
            return color.toString();
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssColor &&
                color.equals(((CssColor) property).color));
    }


}
