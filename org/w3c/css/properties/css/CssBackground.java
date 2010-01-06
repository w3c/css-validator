// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Rewritten 2010 Yves Lafon <ylafon@w3.org>

// (c) COPYRIGHT MIT, ERCIM and Keio, 1997-2010.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.Css1Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssColor;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;
import static org.w3c.css.values.CssOperator.SPACE;

/**
 * http://www.w3.org/TR/2009/CR-css3-background-20091217/#the-background
 * <p/>
 * Name: 	background
 * Value: 	[ &lt;bg-layer&gt; , ]* &lt;final-bg-layer&gt;
 * Initial: 	see individual properties
 * Applies to: 	all elements
 * Inherited: 	no
 * Percentages: 	see individual properties
 * Media: 	visual
 * Computed value: 	see individual properties
 * <p/>
 * Where
 * <p/>
 * &lt;bg-layer&gt; = &lt;bg-image&gt; || &lt;bg-position&gt; ||
 * / &lt;bg-size&gt; || &lt;repeat-style&gt; || &lt;attachment&gt; ||
 * &lt;bg-origin&gt;
 * <p/>
 * where ‘&lt;bg-position&gt;’ must occur before ‘/ &lt;bg-size&gt;’ if both
 * are present.
 * <p/>
 * &lt;final-bg-layer&gt; = &lt;bg-image&gt; || &lt;bg-position&gt; ||
 * / &lt;bg-size&gt; || &lt;repeat-style&gt; || &lt;attachment&gt; ||
 * &lt;bg-origin&gt; || &lt;'background-color'&gt;
 * <p/>
 * where ‘&lt;bg-position&gt;’ must not occur before ‘/ &lt;bg-size&gt;’ if
 * both are present.
 * <p/>
 * Note that a color is permitted in &lt;final-bg-layer&gt;, but
 * not in &lt;bg-layer&gt;.
 *
 * @see CssBackgroundColor
 * @see CssBackgroundImage
 * @see CssBackgroundRepeat
 * @see CssBackgroundAttachment
 * @see CssBackgroundPosition
 * @see CssBackgroundSize
 */
public class CssBackground extends CssProperty {

    private static final String propertyName = "background";

    Object value;

    public CssColor _color;

    // TODO get rid of those
    public CssBackgroundColor color;

    public CssBackgroundImage image;
    public CssBackgroundRepeat repeat;
    public CssBackgroundAttachment attachment;
    public CssBackgroundPosition position;
    public CssBackgroundSize size;
    boolean sizedefined;

    boolean same;

    /**
     * Create a new CssBackground
     */
    public CssBackground() {
    }

    /**
     * Set the value of the property<br/>
     * Does not check the number of values
     *
     * @param expression The expression for this property
     * @throws InvalidParamException The expression is incorrect
     */
    public CssBackground(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Set the value of the property
     *
     * @param expression The expression for this property
     * @param check      set it to true to check the number of values
     * @throws InvalidParamException The expression is incorrect
     */
    public CssBackground(ApplContext ac, CssExpression expression,
                         boolean check) throws InvalidParamException {

                setByUser();
        CssValue val;
        ArrayList<CssBackgroundValue> values;
        CssBackgroundValue b_val = null;
        char op;

        values = new ArrayList<CssBackgroundValue>();
        // we just accumulate values and check at validation
        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();

            if (inherit.equals(val)) {
                if (expression.getCount() > 1) {
                    throw new InvalidParamException("value", val,
                            getPropertyName(), ac);
                }
                value = inherit;
                expression.next();
                return;
            }
            if (b_val == null) {
                b_val = new CssBackgroundValue();
            }
            // we will check later
            b_val.add(val);
            expression.next();

            if (!expression.end()) {
                // incomplete value followed by a comma... it's complete!
                if (op == COMMA) {
                    check(b_val, ac);
                    values.add(b_val);
                    b_val = null;
                } else if (op != SPACE) {
                    throw new InvalidParamException("operator",
                            ((new Character(op)).toString()), ac);
                }
            }
        }
        // if we reach the end in a value that can come in pair
        if (b_val != null) {
            check(b_val, ac);
            values.add(b_val);
        }
        if (values.size() == 1) {
            value = values.get(0);
        } else {
            value = values;
        }
    }

    public void check(CssBackgroundValue v, ApplContext ac)
            throws InvalidParamException
    {
        // TODO have fun here...
    }

    /**
     * @return Returns the image.
     */
    public CssBackgroundImage getImage() {
        return image;
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return color;
    }

    /**
     * Returns the color
     */
    public CssValue getColor() {
        if (color == null) {
            return null;
        } else {
            return color.getColor();
        }
    }

    /**
     * Returns the name of this property
     */
    public final String getPropertyName() {
        return propertyName;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (value instanceof ArrayList) {
            ArrayList v_list;
            v_list = (ArrayList) value;
            StringBuilder sb = new StringBuilder();
            for (Object val : v_list) {
                sb.append(val.toString()).append(", ");
            }
            sb.setLength(sb.length() - 2);
            return sb.toString();
        }
        return value.toString();
    }

    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */
    public void setImportant() {
        super.setImportant();
        if (color != null) {
            color.important = true;
        }
        if (image != null) {
            image.important = true;
        }
        if (repeat != null) {
            repeat.important = true;
        }
        if (attachment != null) {
            attachment.important = true;
        }
        if (position != null) {
            position.important = true;
        }
    }

    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */
    public void print(CssPrinterStyle printer) {
        if ((color != null && image != null &&
                repeat != null && attachment != null &&
                position != null) &&
                (getImportant() ||
                        (!image.important &&
                                !color.important &&
                                !repeat.important &&
                                !attachment.important &&
                                !position.important))) {
            if (color.byUser || image.byUser || repeat.byUser
                    || attachment.byUser || position.byUser) {
                printer.print(this);
            }
        } else {
            if (color != null)
                color.print(printer);
            if (image != null)
                image.print(printer);
            if (repeat != null)
                repeat.print(printer);
            if (attachment != null)
                attachment.print(printer);
            if (position != null)
                position.print(printer);
        }
    }

    /**
     * Set the context.
     * Overrides this method for a macro
     *
     * @see org.w3c.css.css.CssCascadingOrder#order
     * @see org.w3c.css.css.StyleSheetParser#handleRule
     */
    public void setSelectors(CssSelectors selector) {
        super.setSelectors(selector);
        if (color != null) {
            color.setSelectors(selector);
        }
        if (image != null) {
            image.setSelectors(selector);
        }
        if (repeat != null) {
            repeat.setSelectors(selector);
        }
        if (attachment != null) {
            attachment.setSelectors(selector);
        }
        if (position != null) {
            position.setSelectors(selector);
        }
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        ((Css1Style) style).cssBackground.same = same;
        ((Css1Style) style).cssBackground.byUser = byUser;

        if (color != null) {
            color.addToStyle(ac, style);
        }
        if (image != null) {
            image.addToStyle(ac, style);
        }
        if (repeat != null) {
            repeat.addToStyle(ac, style);
        }
        if (attachment != null) {
            attachment.addToStyle(ac, style);
        }
        if (position != null) {
            position.addToStyle(ac, style);
        }
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css1Style) style).getBackground();
        } else {
            return ((Css1Style) style).cssBackground;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return false; // FIXME
    }

    /**
     * Update the source file and the line.
     * Overrides this method for a macro
     *
     * @param line   The line number where this property is defined
     * @param source The source file where this property is defined
     */
    public void setInfo(int line, String source) {
        super.setInfo(line, source);
        if (color != null) {
            color.setInfo(line, source);
        }
        if (image != null) {
            image.setInfo(line, source);
        }
        if (repeat != null) {
            repeat.setInfo(line, source);
        }
        if (attachment != null) {
            attachment.setInfo(line, source);
        }
        if (position != null) {
            position.setInfo(line, source);
        }
    }

    // placeholder for the different values

    public class CssBackgroundValue extends CssValueList {

        CssValue bg_image = null;
        CssValue bg_position = null;
        CssValue bg_size = null;
        CssValue repeat_style = null;
        CssValue attachment = null;
        CssValue origin = null;
        CssValue color = null;

        public boolean equals(CssBackgroundValue v) {
            return false;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (bg_image != null) {
                sb.append(bg_image).append(' ');
            }
            if (bg_position != null) {
                sb.append(bg_position).append(' ');
            }
            if (bg_size != null) {
                sb.append('/').append(bg_size).append(' ');
            }
            if (repeat_style != null) {
                sb.append(repeat_style).append(' ');
            }
            if (attachment != null) {
                sb.append(attachment).append(' ');
            }
            if (origin != null) {
                sb.append(origin).append(' ');
            }
            if (color != null) {
                sb.append(color);
            } else {
                int sb_length = sb.length();
                if (sb_length > 0) {
                    sb.setLength(sb_length - 1);
                }
            }
            return sb.toString();
        }
    }
}
