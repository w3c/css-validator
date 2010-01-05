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
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;
import java.util.HashMap;

import static org.w3c.css.values.CssOperator.COMMA;
import static org.w3c.css.values.CssOperator.SPACE;

/**
 * http://www.w3.org/TR/2009/CR-css3-background-20091217/#the-background-repeat
 *
 * Name: 	background-repeat
 * Value: 	&lt;repeat-style&gt; [ , &lt;repeat-style&gt; ]*
 * Initial: 	repeat
 * Applies to: 	all elements
 * Inherited: 	no
 * Percentages: 	N/A
 * Media: 	visual
 * Computed value: 	as specified
 *
 * Specifies how background images are tiled after they have been sized and
 * positioned. Where
 *
 * &lt;repeat-style&gt; = repeat-x | repeat-y | [repeat | space |
 *                        round | no-repeat]{1,2}
 *
 */
public class CssBackgroundRepeat extends CssProperty {

    private static final String propertyName = "background-repeat";
    public static CssIdent repeat;
    public static HashMap<String, CssIdent> allowed_simple_values;
    public static HashMap<String, CssIdent> allowed_double_values;

    static {
        allowed_simple_values = new HashMap<String, CssIdent>();
        allowed_double_values = new HashMap<String, CssIdent>();
        String[] REPEAT = {"repeat", "space", "round", "no-repeat"};

        allowed_simple_values.put("repeat-x", CssIdent.getIdent("repeat-x"));
        allowed_simple_values.put("repeat-y", CssIdent.getIdent("repeat-y"));

        for (String aREPEAT : REPEAT) {
            allowed_double_values.put(aREPEAT, CssIdent.getIdent(aREPEAT));
        }
        repeat = CssIdent.getIdent("repeat");
    }

    public Object value;

    /**
     * Create a new CssBackgroundRepeat
     */
    public CssBackgroundRepeat() {
        value = repeat;
    }

    /**
     * Set the value of the property
     *
     * @param ac the context
     * @param expression The expression for this property
     * @param check is length checking needed
     * @throws InvalidParamException The expression is incorrect
     */
    public CssBackgroundRepeat(ApplContext ac, CssExpression expression,
                               boolean check) throws InvalidParamException {

        ArrayList<CssValue> values = new ArrayList<CssValue>();
        boolean is_complete = true;
        CssValue val;
        CssValueList vl = null;
        char op;

        setByUser();

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();

            // not an ident? fail
            if (val.getType() != CssTypes.CSS_IDENT) {
                throw new InvalidParamException("value", expression.getValue(),
                        getPropertyName(), ac);
            }

            CssIdent id_val = (CssIdent) val;
            if (inherit.equals(id_val)) {
                // if we got inherit after other values, fail
                // if we got more than one value... fail
                if ((values.size() > 0) || (expression.getCount() > 1)) {
                    throw new InvalidParamException("value", val,
                            getPropertyName(), ac);
                }
                values.add(inherit);
            } else {
                String id_value = id_val.toString();
                CssIdent new_val;
                // check values that must be alone
                new_val = allowed_simple_values.get(id_value);
                if (new_val != null) {
                    // if we already have a double value... it's an error
                    if (!is_complete) {
                        throw new InvalidParamException("value",
                                val, getPropertyName(), ac);
                    }
                    values.add(new_val);
                    is_complete = true;
                } else {
                    // the the one that may come in pairs
                    new_val = allowed_double_values.get(id_value);
                    // not an allowed value !
                    if (new_val == null) {
                        throw new InvalidParamException("value",
                                val, getPropertyName(), ac);
                    }
                    if (is_complete) {
                        vl = new CssValueList();
                        vl.add(new_val);
                    } else {
                        vl.add(new_val);
                        values.add(vl);
                    }
                    is_complete = !is_complete;
                }
            }

            expression.next();
            if (!expression.end()) {
                // incomplete value followed by a comma... it's complete!
                if (!is_complete && (op == COMMA)) {
                    values.add(vl);
                    is_complete = true;
                }
                // complete values are separated by a comma, otherwise space
                if ((is_complete && (op != COMMA)) ||
                        (!is_complete && (op != SPACE))) {
                    throw new InvalidParamException("operator",
                            ((new Character(op)).toString()), ac);
                }
            }
        }
        // if we reach the end in a value that can come in pair
        if (!is_complete) {
            values.add(vl);
        }
        if (values.size() == 1) {
            value = values.get(0);
        } else {
            value = values;
        }
    }


    public CssBackgroundRepeat(ApplContext ac, CssExpression expression)
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
        if (cssBackground.repeat != null)
            style.addRedefinitionWarning(ac, this);
        cssBackground.repeat = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css1Style) style).getBackgroundRepeat();
        } else {
            return ((Css1Style) style).cssBackground.repeat;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssBackgroundRepeat &&
                value == ((CssBackgroundRepeat) property).value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (repeat == value);
    }

}



