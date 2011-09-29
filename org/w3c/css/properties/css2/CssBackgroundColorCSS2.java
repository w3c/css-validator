//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css2;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.properties.css1.Css1Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
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
public class CssBackgroundColorCSS2 extends CssProperty {

    public CssValue color;

    public static CssIdent transparent = CssIdent.getIdent("transparent");

    /**
     * Create a new CssBackgroundColorCSS2
     */
    public CssBackgroundColorCSS2() {
        color = transparent;
    }

    /**
     * Create a new CssBackgroundColorCSS2
     *
     * @param expression The expression for this property
     * @throws InvalidParamException Values are incorrect
     */
    public CssBackgroundColorCSS2(ApplContext ac, CssExpression expression,
                                  boolean check) throws InvalidParamException {

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        setByUser();
        CssValue val = expression.getValue();

        switch (val.getType()) {
            case CssTypes.CSS_COLOR:
                setColor(val);
                break;
            case CssTypes.CSS_IDENT:
                if (transparent.equals(val)) {
                    setColor(transparent);
                    break;
                }
                if (inherit.equals(val)) {
                    setColor(inherit);
                    break;
                }
                setColor(new org.w3c.css.values.CssColor(ac,
                        (String) val.get()));
                break;
            default:
                throw new InvalidParamException("value", val.toString(),
                        getPropertyName(), ac);
        }
        expression.next();
    }

    public CssBackgroundColorCSS2(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * @param color The color to set.
     */
    public void setColor(CssValue color) {
        this.color = color;
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
    public final CssValue getColor() {
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
        if (color != null) {
            return color.toString();
        }
        return "";
    }


    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        CssBackgroundCSS2 cssBackground = ((Css1Style) style).cssBackgroundCSS2;
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
            return ((Css1Style) style).getBackgroundColorCSS2();
        } else {
            return ((Css1Style) style).cssBackgroundCSS2.color;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssBackgroundColorCSS2 &&
                color.equals(((CssBackgroundColorCSS2) property).color));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
        return "background-color";
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return color == transparent;
    }

}
