//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css2;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.HashMap;

/**
 *
 * http://www.w3.org/TR/2008/REC-CSS2-20080411/visuren.html#display-prop
 * 9.2.5 The 'display' property

'display'
    Value:  	inline | block | list-item | run-in | compact | marker |
                table | inline-table | table-row-group | table-header-group |
                table-footer-group | table-row | table-column-group |
                table-column | table-cell | table-caption | none | inherit
    Initial:  	inline
    Applies to:  	all elements
    Inherited:  	no
    Percentages:  	N/A
    Media:  	all

The values of this property have the following meanings:

block
    This value causes an element to generate a principal block box.
inline
    This value causes an element to generate one or more inline boxes.
list-item
    This value causes an element (e.g., LI in HTML) to generate a principal
    block box and a list-item inline box. For information about lists and
    examples of list formatting, please consult the section on lists.
marker
    This value declares generated content before or after a box to be a marker.
    This value should only be used with :before and :after pseudo-elements
    attached to block-level elements. In other cases, this value is interpreted
    as 'inline'. Please consult the section on markers for more information.
none
    This value causes an element to generate no boxes in the formatting
    structure (i.e., the element has no effect on layout). Descendant elements
    do not generate any boxes either; this behavior cannot be overridden by
    setting the 'display' property on the descendants.

    Please note that a display of 'none' does not create an invisible box; it
    creates no box at all. CSS includes mechanisms that enable an element to
    generate boxes in the formatting structure that affect formatting but are
    not visible themselves. Please consult the section on visibility for
    details.
run-in and compact
    These values create either block or inline boxes, depending on context.
    Properties apply to run-in and compact boxes based on their final status
    (inline-level or block-level). For example, the 'white-space' property only
    applies if the box becomes a block box.
table, inline-table, table-row-group, table-column, table-column-group,
table-header-group, table-footer-group, table-row, table-cell, and table-caption
    These values cause an element to behave like a table element (subject to
    restrictions described in the chapter on tables).

Note that although the initial value of 'display' is 'inline', rules in the
user agent's default style sheet may override this value. See the sample style
sheet for HTML 4.0 in the appendix.

Example(s):

Here are some examples of the 'display' property:

P   { display: block }
EM  { display: inline }
LI  { display: list-item }
IMG { display: none }

Conforming HTML user agents may ignore the 'display' property.
 * @version $Revision$
 */

public class CssDisplay extends org.w3c.css.properties.css.CssDisplay {

    public static HashMap<String, CssIdent> allowed_values;
    static {
        allowed_values = new HashMap<String, CssIdent>();
        String[] DISPLAY = {
                "inline", "block", "list-item", "run-in", "compact", "marker",
                "table", "inline-table", "table-row-group",
                "table-header-group", "table-footer-group",
                "table-row", "table-column-group", "table-column",
                "table-cell", "table-caption", "none"};

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
     * @param check      boolean, if check has to be enforced
     * @throws org.w3c.css.util.InvalidParamException Values are incorect
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
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (value == inline);
    }

}