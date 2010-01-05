//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.properties.css1.Css1Style;
import org.w3c.css.properties.css1.CssColor;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 * <H4>
 * &nbsp;&nbsp; 'background-color'
 * </H4>
 * <p/>
 * <EM>Value:</EM> &lt;color&gt; | transparent<BR>
 * <EM>Initial:</EM> transparent<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentage values:</EM> N/A<BR>
 * <p/>
 * This property sets the background color of an element.
 * <PRE>
 * H1 { background-color: #F00 }
 * </PRE>
 *
 * @version $Revision$
 */
public class CssBackgroundColor extends CssProperty {

    CssValue color;

    private static final String propertyName = "background-color";

    /**
     * Create a new CssBackgroundColor
     */
    public CssBackgroundColor() {
        color = transparent;
    }

    /**
     * Create a new CssBackgroundColor
     *
     * @param expression The expression for this property
     * @throws InvalidParamException Values are incorrect
     */
    public CssBackgroundColor(ApplContext ac, CssExpression expression,
                              boolean check) throws InvalidParamException {

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        setByUser();
        CssColor tempcolor = new CssColor(ac, expression, check);
        color = (CssValue) tempcolor.get();
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
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        CssBackground cssBackground = ((Css1Style) style).cssBackground;
        if (cssBackground.color != null)
            style.addRedefinitionWarning(ac, this);
        cssBackground.color = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css1Style) style).getBackgroundColor();
        } else {
            return ((Css1Style) style).cssBackground.color;
        }
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
     * Returns the name of this property
     */
    public final String getPropertyName() {
        return propertyName;
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return color == transparent;
    }

}
