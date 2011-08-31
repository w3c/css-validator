//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css.CssDisplay;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.HashMap;
/**
 *
 * http://www.w3.org/TR/2011/REC-CSS2-20110607/visuren.html#display-prop
 *
9.2.4 The 'display' property

'display'
    Value:  	inline | block | list-item | inline-block | table |
                inline-table | table-row-group | table-header-group |
                table-footer-group | table-row | table-column-group |
                table-column | table-cell | table-caption | none | inherit
    Initial:  	inline
    Applies to:  	all elements
    Inherited:  	no
    Percentages:  	N/A
    Media:  	all
    Computed value:  	see text

 * @version $Revision$
 */

public class CssDisplayCSS21 extends CssDisplay {

    public static CssIdent inline;
    public static HashMap<String, CssIdent> allowed_values;

    static {
        allowed_values = new HashMap<String, CssIdent>();
        String[] DISPLAY = {
                "inline", "block", "list-item", "inline-block",
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
    public CssDisplayCSS21() {
        // nothing to do
    }

    /**
     * Create a new CssDisplay
     *
     * @param ac         The context
     * @param expression The expression for this property
     * @param check      boolean, if check has to be enforced
     * @throws org.w3c.css.util.InvalidParamException Values are incorect
     */
    public CssDisplayCSS21(ApplContext ac, CssExpression expression,
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

    public CssDisplayCSS21(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }


    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (value == inline);
    }

}