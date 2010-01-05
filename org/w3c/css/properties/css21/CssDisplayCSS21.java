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
 * http://www.w3.org/TR/2009/CR-CSS2-20090908/visuren.html#display-prop
 *
 * 9.2.4 The 'display' property

'display'
    Value:  	inline | block | list-item | run-in | inline-block | table |
                inline-table | table-row-group | table-header-group |
                table-footer-group | table-row | table-column-group |
                table-column | table-cell | table-caption | none | inherit
    Initial:  	inline
    Applies to:  	all elements
    Inherited:  	no
    Percentages:  	N/A
    Media:  	all
    Computed value:  	see text

The values of this property have the following meanings:

block
    This value causes an element to generate a block box.
inline-block
    This value causes an element to generate a block box, which itself is
    flowed as a single inline box, similar to a replaced element. The inside
    of an inline-block is formatted as a block box, and the element itself is
    formatted as an inline replaced element.
inline
    This value causes an element to generate one or more inline boxes.
list-item
    This value causes an element (e.g., LI in HTML) to generate a principal
    block box and a list-item inline box. For information about lists and
    examples of list formatting, please consult the section on lists.
none
    This value causes an element to not appear in the formatting structure
    (i.e., in visual media the element generates no boxes and has no effect
    on layout). Descendant elements do not generate any boxes either;
    the element and its content are removed from the formatting structure
    entirely. This behavior cannot be overridden by setting the 'display'
    property on the descendants.

    Please note that a display of 'none' does not create an invisible box;
    it creates no box at all. CSS includes mechanisms that enable an element
    to generate boxes in the formatting structure that affect formatting but
    are not visible themselves. Please consult the section on visibility for
    details.
run-in
    This value creates either block or inline boxes, depending on context.
    Properties apply to run-in boxes based on their final status (inline-level
    or block-level).
table, inline-table, table-row-group, table-column, table-column-group,
table-header-group, table-footer-group, table-row, table-cell,
and table-caption
    These values cause an element to behave like a table element
    (subject to restrictions described in the chapter on tables).

The computed value is the same as the specified value, except for positioned
and floating elements (see Relationships between 'display', 'position', and '
float') and for the root element. For the root element, the computed value is
changed as described in the section on the relationships between 'display',
'position', and 'float'.

Note that although the initial value of 'display' is 'inline', rules in the
user agent's default style sheet may override this value. See the sample
style sheet for HTML 4 in the appendix.

Example(s):

Here are some examples of the 'display' property:

p   { display: block }
em  { display: inline }
li  { display: list-item }
img { display: none }

 * @version $Revision$
 */

public class CssDisplayCSS21 extends CssDisplay {

    public static CssIdent inline;
    public static HashMap<String, CssIdent> allowed_values;

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