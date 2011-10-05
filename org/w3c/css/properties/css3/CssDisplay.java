// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 14th 2000 Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT, ERCIM and Keio, 1997-2010.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.HashMap;

/**
 * Validity: 2007/08/09
 * From http://www.w3.org/TR/2007/WD-css3-box-20070809/#the-lsquo
 * <p/>
 * 4.1. The ?display? property
 * Name: 	display
 * Value: 	inline | block | inline-block | list-item | run-in | compact | table |
 * inline-table | table-row-group | table-header-group |
 * table-footer-group | table-row | table-column-group | table-column |
 * table-cell | table-caption | ruby | ruby-base | ruby-text |
 * ruby-base-group | ruby-text-group | <template>| none
 * Initial: 	inline
 * Applies to: 	all elements
 * Inherited: 	no
 * Percentages: 	N/A
 * Media: 	visual (?none? applies to all media)
 * Computed value: 	specified value, except for floats, root elements and positioned elements; see text
 *
 *
 * TODO do &lt;template&gt; from http://www.w3.org/TR/2010/WD-css3-layout-20100429/#declaring-templates-the-display-property
 *
 */
public class CssDisplay extends org.w3c.css.properties.css.CssDisplay {

    public CssIdent value;
    private static HashMap<String, CssIdent> allowed_values;

    static {
        allowed_values = new HashMap<String, CssIdent>();

        String[] DISPLAY = {
                "inline", "block", "inline-block", "list-item", "run-in",
                "compact", "table", "inline-table", "table-row-group",
                "table-header-group", "table-footer-group", "table-row",
                "table-column-group", "table-column", "table-cell",
                "table-caption", "ruby", "ruby-base", "ruby-text",
                "ruby-base-group", "ruby-text-group", "none"
        };

        for (String aDISPLAY : DISPLAY) {
            allowed_values.put(aDISPLAY, CssIdent.getIdent(aDISPLAY));
        }
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
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorect
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
            if (value == null) {
                // do templates...
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
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (value == inline);
    }

}
