//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.HashMap;

/**
 * http://www.w3.org/TR/2008/REC-CSS1-20080411/#display
 * <p/>
 * <H4>
 * &nbsp;&nbsp; 'display'
 * </H4>
 * <p/>
 * <EM>Value:</EM> block | inline | list-item | none<BR>
 * <EM>Initial:</EM> block<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentage values:</EM> N/A<BR>
 * <p/>
 * This property describes how/if an element is displayed on the canvas (which
 * may be on a printed page, a computer display etc.).
 * <P> An element with a 'display' value of 'block' opens a new box. The box
 * is positioned relative to adjacent boxes according to the CSS <A
 * HREF="#formatting-model">formatting model</A>. Typically, elements like
 * 'H1' and 'P' are of type 'block'. A value of 'list-item' is similar to
 * 'block' except that a list-item marker is added. In HTML, 'LI' will
 * typically have this value.
 * <p/>
 * An element with a 'display' value of 'inline' results in a new inline box
 * on the same line as the previous content. The box is dimensioned according
 * to the formatted size of the content. If the content is text, it may span
 * several lines, and there will be a box on each line. The margin, border and
 * padding properties apply to 'inline' elements, but will not have any effect
 * at the line breaks.
 * <p/>
 * A value of 'none' turns off the display of the element, including children
 * elements and the surrounding box.
 * <PRE>
 * P { display: block }
 * EM { display: inline }
 * LI { display: list-item }
 * IMG { display: none }
 * </PRE>
 * <p/>
 * The last rule turns off the display of images.
 * <P> The initial value of 'display' is 'block', but a UA will typically have
 * default values for all HTML elements according to the suggested rendering
 * of elements in the HTML specification.
 *
 * @version $Revision$
 */
public class CssDisplay extends org.w3c.css.properties.css.CssDisplay {

    CssIdent value;
    public static CssIdent block;
    public static HashMap<String, CssIdent> allowed_values;

    static {
        allowed_values = new HashMap<String, CssIdent>();
        String[] DISPLAY = {
                "block", "inline", "list-item", "none"};
        for (String aDISPLAY : DISPLAY) {
            allowed_values.put(aDISPLAY, CssIdent.getIdent(aDISPLAY));
        }
        block = CssIdent.getIdent("block");
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

            value = allowed_values.get(id_value);
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
        return (value == block);
    }

}
