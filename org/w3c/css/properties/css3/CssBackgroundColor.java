// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Rewritten 2010 Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio, 1997-2010.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css3;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2009/CR-css3-background-20091217/#the-background-color
 */
public class CssBackgroundColor extends org.w3c.css.properties.css.CssBackgroundColor {

    CssValue color;

    /**
     * Create a new CssBackgroundColor
     */
    public CssBackgroundColor() {
        color = initial;
    }

    /**
     * Create a new CssBackgroundColor
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorrect
     */
    public CssBackgroundColor(ApplContext ac, CssExpression expression,
                              boolean check) throws InvalidParamException {

        setByUser();
        CssValue val = expression.getValue();

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        if (inherit.equals(val)) {
            color = inherit;
            expression.next();
        } else {
            try {
                // we use the latest version of CssColor, aka CSS3
                // instead of using CSS21 colors + transparent per spec.
                CssColor tcolor = new CssColor(ac, expression, check);
                // instead of using getColor, we get the value directly
                // as we can have idents
                color = tcolor.color;
            } catch (InvalidParamException e) {
                throw new InvalidParamException("value",
                        expression.getValue(),
                        getPropertyName(), ac);
            }
        }
    }

    public CssBackgroundColor(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return color;
    }


    public void set(CssValue col) {
        color = col;
    }

    /**
     * Returns the color
     */
    public CssValue getColor() {
        return color;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
        return color.equals(inherit);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        return color.toString();
    }


    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssBackgroundColor &&
                color.equals(((CssBackgroundColor) property).color));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (color == transparent);
    }

}
