//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssBackground;
import org.w3c.css.properties.css.CssBackgroundConstants;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 * <H4>
 * <A NAME="background">5.3.7 &nbsp;&nbsp; 'background'</A>
 * </H4>
 * <p/>
 * <EM>Value:</EM> &lt;background-color&gt; || &lt;background-image&gt; ||
 * &lt;background-repeat&gt; || &lt;background-attachment&gt; ||
 * &lt;background-position&gt;<BR>
 * <EM>Initial:</EM> not defined for shorthand properties<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentage values:</EM> allowed on &lt;background-position&gt;<BR>
 * <p/>
 * The 'background' property is a shorthand property for setting the individual
 * background properties (i.e., 'background-color', 'background-image',
 * 'background-repeat', 'background-attachment' and 'background-position') at
 * the same place in the style sheet.
 * <p/>
 * Possible values on the 'background' properties are the set of all possible
 * values on the individual properties.
 * <PRE>
 * BODY { background: red }
 * P { background: url(chess.png) gray 50% repeat fixed }
 * </PRE>
 * <P> The 'background' property always sets all the individual background
 * properties.  In the first rule of the above example, only a value for
 * 'background-color' has been given and the other individual properties are
 * set to their initial value. In the second rule, all individual properties
 * have been specified.
 *
 * @version $Revision$
 * @see org.w3c.css.properties.css.CssBackgroundColor
 * @see org.w3c.css.properties.css.CssBackgroundImage
 * @see org.w3c.css.properties.css.CssBackgroundRepeat
 * @see org.w3c.css.properties.css.CssBackgroundAttachment
 * @see org.w3c.css.properties.css.CssBackgroundPosition
 */
public class CssBackgroundCSS1 extends CssBackground
        implements CssOperator, CssBackgroundConstants {

    CssBackgroundColorCSS1 color;
    CssBackgroundImageCSS1 image;
    CssBackgroundRepeatCSS1 repeat;
    CssBackgroundAttachmentCSS1 attachment;
    CssBackgroundPositionCSS1 position;

    boolean same;

    /**
     * Create a new CssBackgroundCSS1
     */
    public CssBackgroundCSS1() {
    }

    /**
     * Set the value of the property
     *
     * @param expression The expression for this property
     * @throws InvalidParamException The expression is incorrect
     */
    public CssBackgroundCSS1(ApplContext ac, CssExpression expression,
                             boolean check) throws InvalidParamException {

        CssValue val;
        char op;
        boolean find = true;

        // too many values
        if (check && expression.getCount() > 6) {
            throw new InvalidParamException("unrecognize", ac);
        }

        setByUser();

        while (find) {
            find = false;
            val = expression.getValue();
            op = expression.getOperator();

            if (val == null) {
                break;
            }

            if (color == null) {
                try {
                    color = new CssBackgroundColorCSS1(ac, expression);
                    find = true;
                } catch (InvalidParamException e) {
                }
            }
            if (!find && image == null) {
                try {
                    image = new CssBackgroundImageCSS1(ac, expression);
                    find = true;
                } catch (InvalidParamException e) {
                    // nothing to do, repeat will test this value
                }
            }
            if (!find && repeat == null) {
                try {
                    repeat = new CssBackgroundRepeatCSS1(ac, expression);
                    find = true;
                } catch (InvalidParamException e) {
                    // nothing to do, attachment will test this value
                }
            }
            if (!find && attachment == null) {
                try {
                    attachment = new CssBackgroundAttachmentCSS1(ac, expression);
                    find = true;
                } catch (InvalidParamException e) {
                    // nothing to do, position will test this value
                }
            }
            if (!find && position == null) {
                try {
                    position = new CssBackgroundPositionCSS1(ac, expression);
                    find = true;
                } catch (InvalidParamException e) {
                    // nothing to do
                }
            }
            if (op != SPACE) {
                throw new InvalidParamException("operator",
                        ((new Character(op)).toString()),
                        ac);
            }
            if (check && !find && val != null) {
                throw new InvalidParamException("unrecognize", ac);
            }
        }
        /*
      if (color == null)
          color = new CssBackgroundColorCSS1();
      if (image == null)
          image = new CssBackgroundImageCSS1();
      if (repeat == null)
          repeat = new CssBackgroundRepeatCSS1();
      if (attachment == null)
          attachment = new CssBackgroundAttachmentCSS1();
      if (position == null)
          position = new CssBackgroundPositionCSS1();
      */
    }

    public CssBackgroundCSS1(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
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
    public final CssValue getColor() {
        if (color == null) {
            return null;
        } else {
            return color.getColor();
        }
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        if (color != null) {
            sb.append(color);
            first = false;
        }
        if (image != null) {
            if (!first) {
                sb.append(' ');
            }
            sb.append(image);
            first = false;
        }
        if (repeat != null) {
            if (!first) {
                sb.append(' ');
            }
            first = false;
            sb.append(repeat);
        }
        if (attachment != null) {
            if (!first) {
                sb.append(' ');
            }
            first = false;
            sb.append(attachment);
        }
        if (position != null) {
            if (!first) {
                sb.append(' ');
            }
            first = false;
            sb.append(position);
        }
        return sb.toString();
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
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
        return important && ((color == null || color.important) &&
                (image == null || image.important) &&
                (repeat == null || repeat.important) &&
                (attachment == null || attachment.important) &&
                (position == null || position.important));
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
}
