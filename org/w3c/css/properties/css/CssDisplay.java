// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 14th 2000 Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT, ERCIM and Keio, 1997-2010.
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

import java.util.HashMap;

/**
 * The 'display' property
 * using CSS21 values for now
 */
public class CssDisplay extends CssProperty {

    private static final String propertyName = "display";

    public CssIdent value;
    public static CssIdent inline;
    private static HashMap<String, CssIdent> allowed_values;

    static {
        allowed_values = new HashMap<String, CssIdent>();

        String[] DISPLAY = {
                "inline", "block", "list-item", "run-in", "inline-block",
                "table", "inline-table", "table-row-group",
                "table-header-group", "table-footer-group",
                "table-row", "table-column-group", "table-column",
                "table-cell", "table-caption", "none"};

        for (String aDISPLAY : DISPLAY) {
            allowed_values.put(aDISPLAY, CssIdent.getIdent(aDISPLAY));
        }
        inline = CssIdent.getIdent("inline");
    }

    /**
     * Create a new CssDisplay
     */
    public CssDisplay() {
        // nothing to do
    }

    /**
     * Create a new CssDisplay
     *
     * @param ac         The context
     * @param expression The expression for this property
     * @param check      true if explicit check is needed
     * @throws InvalidParamException Values are incorect
     */
    public CssDisplay(ApplContext ac, CssExpression expression,
                      boolean check) throws InvalidParamException {

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        CssValue val = expression.getValue();

        setByUser();

        if (val.getType() == CssTypes.CSS_IDENT) {
            CssIdent id_val = (CssIdent) val;
            String id_value = id_val.toString();
            if (inherit.equals(id_val)) {
                value = inherit;
            } else {
                value = allowed_values.get(id_value);
            }
            if (value != null) {
                expression.next();
                return;
            }
        }

        throw new InvalidParamException("value", expression.getValue(),
                getPropertyName(), ac);
    }

    public CssDisplay(ApplContext ac, CssExpression expression)
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
        if (style0.cssDisplay != null)
            style0.addRedefinitionWarning(ac, this);
        style0.cssDisplay = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css1Style) style).getDisplay();
        } else {
            return ((Css1Style) style).cssDisplay;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssDisplay &&
                value == ((CssDisplay) property).value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (value == inline);
    }

}
