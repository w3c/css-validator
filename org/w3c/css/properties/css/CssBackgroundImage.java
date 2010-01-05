// $Id$
// @author Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio, 2010.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.Css1Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;

/**
 * http://www.w3.org/TR/2009/CR-css3-background-20091217/#the-background-image
 *
 * Name: 	background-image
 * Value: 	&lt;bg-image&gt; [ , &lt;bg-image&gt; ]*
 * Initial: 	none
 * Applies to: 	all elements
 * Inherited: 	no
 * Percentages: 	N/A
 * Media: 	visual
 * Computed value: 	as specified, but with URIs made absolute
 *
 * This property sets the background image(s) of an element. Images are drawn
 * with the first specified one on top (closest to the user) and each
 * subsequent image behind the previous one. Where
 *
 * &lt;bg-image&gt; = &lt;image&gt; | none
 */
public class CssBackgroundImage extends CssProperty {

    private static final String propertyName = "background-image";

    Object url = null;

    /**
     * Create a new CssBackgroundImage
     */
    public CssBackgroundImage() {
        url = none;
    }

    /**
     * Creates a new CssBackgroundImage
     *
     * @param ac the context
     * @param expression The expression for this property
     * @param check boolean
     * @throws InvalidParamException Values are incorrect
     */
    public CssBackgroundImage(ApplContext ac, CssExpression expression,
                              boolean check) throws InvalidParamException {

        ArrayList<CssValue> values = new ArrayList <CssValue>();
        setByUser();

        CssValue val;
        char op;

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();
            switch (val.getType()) {
                case CssTypes.CSS_URL:
                    values.add(val);
                    break;
                case CssTypes.CSS_IDENT:
                    if (inherit.equals(val)) {
                        // if we got inherit after other values, fail
                        // if we got more than one value... fail
                        if ((values.size()>0) || (expression.getCount()>1 )) {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        values.add(inherit);
                        break;
                    } else if (none.equals(val)) {
                        values.add(none);
                        break;
                    }
                default:
                    throw new InvalidParamException("value", val,
                            getPropertyName(), ac);
            }
            expression.next();
            if (!expression.end() && (op != COMMA)) {
                throw new InvalidParamException("operator",
                        ((new Character(op)).toString()), ac);
            }
        }
        if (values.size() == 1) {
            url = values.get(0);
        } else {
            url = values;
        }
    }

    public CssBackgroundImage(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */

    public Object get() {
        return url;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
        return (inherit == url);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (url instanceof ArrayList) {
            ArrayList values = (ArrayList) url;
            StringBuilder sb = new StringBuilder();
            for (Object aValue : values) {
                sb.append(aValue.toString()).append(", ");
            }
            sb.setLength(sb.length() - 2);
            return sb.toString();
        }
        return url.toString();
    }

    /**
     * Returns the name of this property
     */
    public final String getPropertyName() {
        return propertyName;
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        CssBackground cssBackground = ((Css1Style) style).cssBackground;
        if (cssBackground.image != null)
            style.addRedefinitionWarning(ac, this);
        cssBackground.image = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css1Style) style).getBackgroundImage();
        } else {
            return ((Css1Style) style).cssBackground.image;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssBackgroundImage && url != null &&
                url.equals(((CssBackgroundImage) property).url));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (url == none);
    }

}
