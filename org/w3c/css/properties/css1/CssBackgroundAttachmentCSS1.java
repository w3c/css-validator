//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssBackgroundAttachment;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.HashMap;

/**
 * <H4>
 * &nbsp;&nbsp; 'background-attachment'
 * </H4>
 * <p/>
 * <EM>Value:</EM> scroll | fixed<BR>
 * <EM>Initial:</EM> scroll<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentage values:</EM> N/A<BR>
 * <p/>
 * If a background image is specified, the value of 'background-attachment'
 * determines if it is fixed with regard to the canvas or if it scrolls along
 * with the content.
 * <PRE>
 * BODY {
 * background: red url(pendant.gif);
 * background-repeat: repeat-y;
 * background-attachment: fixed;
 * }
 * </PRE>
 *
 * @version $Revision$
 */
public class CssBackgroundAttachmentCSS1 extends CssBackgroundAttachment {

    private static HashMap<String, CssIdent> allowed_values;
    private static CssIdent scroll;

    static {
        allowed_values = new HashMap<String, CssIdent>();
        scroll = CssIdent.getIdent("scroll");
        allowed_values.put("scroll", scroll);
        allowed_values.put("fixed", CssIdent.getIdent("fixed"));
    }

    CssIdent value;

    /**
     * Create a new CssBackgroundAttachmentCSS1
     */
    public CssBackgroundAttachmentCSS1() {
        value = scroll;
    }

    /**
     * Creates a new CssBackgroundAttachmentCSS1
     *
     * @param expression The expression for this property
     * @throws InvalidParamException Values are incorrect
     */
    public CssBackgroundAttachmentCSS1(ApplContext ac, CssExpression expression,
                                       boolean check) throws InvalidParamException {

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        setByUser();

        CssValue val = expression.getValue();

        if (val.getType() == CssTypes.CSS_IDENT) {
            CssIdent new_val = allowed_values.get(val.toString());
            if (new_val != null) {
                value = new_val;
                expression.next();
                return;
            }
        }
        throw new InvalidParamException("value", expression.getValue(),
                getPropertyName(), ac);
    }

    public CssBackgroundAttachmentCSS1(ApplContext ac, CssExpression expression)
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
        return false;
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
        CssBackgroundCSS1 cssBackground = ((Css1Style) style).cssBackgroundCSS1;
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
            return ((Css1Style) style).getBackgroundAttachmentCSS1();
        } else {
            return ((Css1Style) style).cssBackgroundCSS1.attachment;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssBackgroundAttachmentCSS1 &&
                value == ((CssBackgroundAttachmentCSS1) property).value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (scroll == value);
    }
}
