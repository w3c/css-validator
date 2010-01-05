// $Id$
// @author Yves Lafon <ylafon@w3.org>

// (c) COPYRIGHT MIT, ERCIM and Keio, 2010.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.Css1Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.ArrayList;
import java.util.HashMap;

import static org.w3c.css.values.CssOperator.COMMA;

/**
 * http://www.w3.org/TR/2009/CR-css3-background-20091217/#the-background-attachment
 * Name: 	background-attachment
 * Value: 	&lt;attachment&gt; [ , &lt;attachment&gt; ]*
 * Initial: 	scroll
 * Applies to: 	all elements
 * Inherited: 	no
 * Percentages: 	N/A
 * Media: 	visual
 * Computed value: 	as specified
 *
 * If background images are specified, this property specifies whether they
 * are fixed with regard to the viewport ('fixed') or scroll along with the
 * element ('scroll') or its contents ('local'). The property's value is given
 *  as a comma-separated list of &lt;attachment&gt; keywords where
 *
 * &lt;attachment&gt; = scroll | fixed | local
 */
public class CssBackgroundAttachment extends CssProperty {

    private static final String propertyName = "background-attachment";

    private static HashMap<String, CssIdent> allowed_values;
    public static CssIdent scroll;

    static {
        allowed_values = new HashMap<String, CssIdent>();
        scroll = CssIdent.getIdent("scroll");
        allowed_values.put("scroll", scroll);
        allowed_values.put("fixed", CssIdent.getIdent("fixed"));
        allowed_values.put("local", CssIdent.getIdent("local"));
    }

    Object value;

    /**
     * Create a new CssBackgroundAttachment
     */
    public CssBackgroundAttachment() {
        value = scroll;
    }

    /**
     * Creates a new CssBackgroundAttachment
     *
     * @param ac the context
     * @param expression The expression for this property
     * @param check if some length checking is required
     * @throws InvalidParamException Values are incorrect
     */
    public CssBackgroundAttachment(ApplContext ac, CssExpression expression,
                                   boolean check) throws InvalidParamException {

        ArrayList<CssValue> values = new ArrayList<CssValue>();
        char op;
        CssValue val;

        setByUser();

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();
            if (val.getType() != CssTypes.CSS_IDENT) {
                throw new InvalidParamException("value", val,
                        getPropertyName(), ac);
            }
            if (inherit.equals(val)) {
                // if we got inherit after other values, fail
                // if we got more than one value... fail
                if ((values.size() > 0) || (expression.getCount() > 1)) {
                    throw new InvalidParamException("value", val,
                            getPropertyName(), ac);
                }
                values.add(inherit);
            } else {
                // check that it's in the allowed values
                CssValue new_val = allowed_values.get(val.toString());
                if (new_val == null) {
                    throw new InvalidParamException("value", val,
                            getPropertyName(), ac);
                }
                values.add(new_val);
            }
            expression.next();
            // and check that values are separated by commas
            if (!expression.end() && (op != COMMA)) {
                throw new InvalidParamException("operator",
                        ((new Character(op)).toString()), ac);
            }
        }

        if (values.size() == 1) {
            value = values.get(0);
        } else {
            value = values;
        }
    }

    public CssBackgroundAttachment(ApplContext ac, CssExpression expression)
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
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
        return (inherit == value);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (value instanceof ArrayList) {
            ArrayList values = (ArrayList) value;
            StringBuilder sb = new StringBuilder();
            for (Object aValue : values) {
                sb.append(aValue.toString()).append(", ");
            }
            sb.setLength(sb.length() - 2);
            return sb.toString();
        }
        return value.toString();
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
        if (cssBackground.attachment != null)
            style.addRedefinitionWarning(ac, this);
        cssBackground.attachment = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css1Style) style).getBackgroundAttachment();
        } else {
            return ((Css1Style) style).cssBackground.attachment;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssBackgroundAttachment &&
                value.equals(((CssBackgroundAttachment) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (scroll == value);
    }
}
