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
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;
import static org.w3c.css.values.CssOperator.SLASH;
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

    // TODO get rid of those or reformat them
    public CssBackgroundColor color;

    public CssBackgroundImage image;
    public CssBackgroundRepeat repeat;
    public CssBackgroundAttachment attachment;
    public CssBackgroundPosition position;
    public CssBackgroundSize size;

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
        CssExpression single_layer = null;
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
            if (single_layer == null) {
                single_layer = new CssExpression();
            }
            // we will check later
            single_layer.addValue(val);
            single_layer.setOperator(op);
            expression.next();

            if (!expression.end()) {
                // incomplete value followed by a comma... it's complete!
                if (op == COMMA) {
                    single_layer.setOperator(SPACE);
                    b_val = check(ac, single_layer, check, false);
                    values.add(b_val);
                    single_layer = null;
                } else if ((op != SPACE) && (op != SLASH)) {
                    throw new InvalidParamException("operator",
                            ((new Character(op)).toString()), ac);
                }
            }
        }
        // if we reach the end in a value that can come in pair
        if (single_layer != null) {
            b_val = check(ac, single_layer, check, true);
            values.add(b_val);
        }
        if (values.size() == 1) {
            value = values.get(0);
        } else {
            value = values;
        }
    }

    private Object getCssBackgroundRepeatValue(ApplContext ac,
                                               CssExpression expression,
                                               boolean check)
            throws InvalidParamException {
        char op = expression.getOperator();
        CssExpression exp = new CssExpression();

        exp.addValue(expression.getValue());
        CssBackgroundRepeat bg_size;
        repeat = new CssBackgroundRepeat(ac, exp, check);
        // now check if we can add a second value ;)
        if ((op == SPACE) && !expression.end()) {
            expression.next();
            if (!expression.end()) {
                CssValue val = expression.getValue();
                if ((val.getType() == CssTypes.CSS_IDENT) &&
                        (CssBackgroundRepeat.isMatchingIdent((CssIdent) val))) {
                    exp.addValue(expression.getValue());
                    exp.starts();
                    try {
                        repeat = new CssBackgroundRepeat(ac, exp, check);
                    } catch (InvalidParamException ipe) {
                        expression.precedent();
                    }
                } else {
                    expression.precedent();
                }
            }
        }
        return repeat.get();
    }

    private Object getCssBackgroundSizeValue(ApplContext ac,
                                             CssExpression expression,
                                             boolean check)
            throws InvalidParamException {
        char op = expression.getOperator();
        CssExpression exp = new CssExpression();

        exp.addValue(expression.getValue());
        CssBackgroundSize bg_size;
        bg_size = new CssBackgroundSize(ac, exp, check);
        // now check if we can add a second value ;)
        // TODO really dirty.. must check the use of 'check'
        // here, and possibly adjust the parsing model in
        // other classes :(
        if ((op == SPACE) && !expression.end()) {
            expression.next();
            if (!expression.end()) {
                exp.addValue(expression.getValue());
                exp.starts();
                try {
                    bg_size = new CssBackgroundSize(ac, exp, check);
                } catch (InvalidParamException ipe) {
                    // roll back
                    expression.precedent();
                }
            }
        }
        return bg_size.get();
    }


    private Object getCssBackgroundPositionValue(ApplContext ac,
                                                 CssExpression expression,
                                                 boolean check)
            throws InvalidParamException {
        CssExpression exp = new CssExpression();
        char op = expression.getOperator();
        exp.addValue(expression.getValue());
        int last_val = -1;

        CssBackgroundPosition bg_pos;
        bg_pos = new CssBackgroundPosition(ac, exp, check);
        // good we have a valid value, try something better..
        expression.mark();
        // we MUST try all the cases, as we can have something
        // invalid using 3 values (incompatible definitions)
        // but valid using 4 values...
        // example top 12% is invalid, top 12% center is valid...
        for (int i = 0; i < 3; i++) {
            if ((op == SPACE) && !expression.end()) {
                expression.next();
                if (expression.end()) {
                    break;
                }
                exp.addValue(expression.getValue());
                exp.starts();
                try {
                    bg_pos = new CssBackgroundPosition(ac, exp, check);
                    last_val = i;
                } catch (InvalidParamException ipe) {
                }

            }
        }
        expression.reset();
        while (last_val >= 0) {
            expression.next();
            last_val--;
        }
        return bg_pos.get();
    }


    public CssBackgroundValue check(ApplContext ac, CssExpression expression,
                                    boolean check, boolean is_final)
            throws InvalidParamException {
        // <bg-layer> = <bg-image> || <bg-position> || / <bg-size> || <repeat-style> ||
        //              <attachment> || <bg-origin>
        // bg_image is CSS_URL | IDENT
        // bg-position is IDENT | NUMBER | LENGTH | PERCENTAGE
        // bg-size is IDENT | NUMBER | LENGTH | PERCENTAGE
        // repeat-style is IDENT
        // attachment is IDENT
        // bg-origin is IDENT
        // + color as CSS_COLOR or IDENT on final-layer

        CssValue val;
        char op;
        CssExpression exp;
        CssBackgroundValue v = new CssBackgroundValue();
        boolean next_is_size, got_size;
        Object res;

        next_is_size = false;
        got_size = false;
        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();

            switch (val.getType()) {
                case CssTypes.CSS_COLOR:
                    // we already got one, fail...
                    if (v.color != null || next_is_size || !is_final) {
                        throw new InvalidParamException("value", val,
                                getPropertyName(), ac);
                    }
                    exp = new CssExpression();
                    exp.addValue(val);

                    CssBackgroundColor bg_color;
                    bg_color = new CssBackgroundColor(ac, exp, check);
                    v.color = (CssValue) bg_color.get();
                    break;

                case CssTypes.CSS_URL:
                    // we already got one, fail...
                    if (v.bg_image != null || next_is_size) {
                        throw new InvalidParamException("value", val,
                                getPropertyName(), ac);
                    }
                    exp = new CssExpression();
                    exp.addValue(val);

                    CssBackgroundImage bg_image;
                    bg_image = new CssBackgroundImage(ac, exp, check);
                    res = bg_image.get();
                    // we only have one vale so it should always be the case
                    if (res instanceof CssValue) {
                        v.bg_image = (CssValue) res;
                    } else {
                        throw new InvalidParamException("value", val,
                                getPropertyName(), ac);
                    }
                    break;
                case CssTypes.CSS_NUMBER:
                case CssTypes.CSS_LENGTH:
                case CssTypes.CSS_PERCENTAGE:
                    // ok, so now we have a background position or size.
                    // and...
                    // in <bg_layer>: where '<bg-position>' must occur before
                    //  '/ <bg-size>' if both are present.
                    if (next_is_size) {
                        // size, we have up to two values
                        if (v.bg_size != null) {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        res = getCssBackgroundSizeValue(ac, expression, check);
                        op = expression.getOperator();
                        // we only have one vale so it should always be the case
                        if (res instanceof CssValue) {
                            v.bg_size = (CssValue) res;
                        } else {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        got_size = true;
                        next_is_size = false;
                    } else {
                        // position with it's up to 4 values...
                        if (got_size) {
                            throw new InvalidParamException("bg_order", val,
                                    getPropertyName(), ac);
                        }
                        if (v.bg_position != null) {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        res = getCssBackgroundPositionValue(ac, expression, check);
                        op = expression.getOperator();
                        // we only have one vale so it should always be the case
                        if (res instanceof CssValue) {
                            v.bg_position = (CssValue) res;
                        } else {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }

                    }
                    break;
                case CssTypes.CSS_IDENT:
                    // inherit is already taken care of...
                    CssIdent ident_val = (CssIdent) val;
                    if (CssBackgroundAttachment.isMatchingIdent(ident_val)) {
                        if (v.attachment != null || next_is_size) {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        exp = new CssExpression();
                        exp.addValue(val);

                        CssBackgroundAttachment attachment;
                        attachment = new CssBackgroundAttachment(ac, exp, check);
                        res = attachment.get();
                        // we only have one vale so it should always be the case
                        if (res instanceof CssValue) {
                            v.attachment = (CssValue) res;
                        } else {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        break;
                    }
                    if (CssBackgroundImage.isMatchingIdent(ident_val)) {
                        if (v.bg_image != null || next_is_size) {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        // a bit of an overkill, as we know it can be only
                        // 'none'.. but it is more flexible if ever it changes
                        exp = new CssExpression();
                        exp.addValue(val);

                        bg_image = new CssBackgroundImage(ac, exp, check);
                        res = bg_image.get();
                        // we only have one vale so it should always be the case
                        if (res instanceof CssValue) {
                            v.bg_image = (CssValue) res;
                        } else {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        break;
                    }
                    if (CssBackgroundOrigin.isMatchingIdent(ident_val)) {
                        if (v.origin != null || next_is_size) {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        exp = new CssExpression();
                        exp.addValue(val);

                        CssBackgroundOrigin origin;
                        origin = new CssBackgroundOrigin(ac, exp, check);
                        res = origin.get();
                        // we only have one vale so it should always be the case
                        if (res instanceof CssValue) {
                            v.origin = (CssValue) res;
                        } else {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        break;
                    }
                    if (CssBackgroundRepeat.isMatchingIdent(ident_val)) {
                        if (v.repeat_style != null || next_is_size) {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        res = getCssBackgroundRepeatValue(ac, expression, check);
                        op = expression.getOperator();

                        // we only have one vale so it should always be the case
                        if (res instanceof CssValue) {
                            v.repeat_style = (CssValue) res;
                        } else {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        break;
                    }
                    if (next_is_size) {
                        if (CssBackgroundSize.isMatchingIdent(ident_val)) {
                            // size, we have up to two values
                            if (v.bg_size != null) {
                                throw new InvalidParamException("value", val,
                                        getPropertyName(), ac);
                            }
                            res = getCssBackgroundSizeValue(ac, expression, check);
                            op = expression.getOperator();
                            // we only have one vale so it should always be the case
                            if (res instanceof CssValue) {
                                v.bg_size = (CssValue) res;
                            } else {
                                throw new InvalidParamException("value", val,
                                        getPropertyName(), ac);
                            }
                            got_size = true;
                            next_is_size = false;
                            break;
                        }
                    } else {
                        if (CssBackgroundPosition.isMatchingIdent(ident_val)) {
                            // position with it's up to 4 values...
                            if (got_size) {
                                throw new InvalidParamException("bg_order",
                                        val, getPropertyName(), ac);
                            }
                            if (v.bg_position != null) {
                                throw new InvalidParamException("value", val,
                                        getPropertyName(), ac);
                            }
                            res = getCssBackgroundPositionValue(ac, expression, check);
                            op = expression.getOperator();
                            // we only have one vale so it should always be the case
                            if (res instanceof CssValue) {
                                v.bg_position = (CssValue) res;
                            } else {
                                throw new InvalidParamException("value", val,
                                        getPropertyName(), ac);
                            }
                            break;
                        }
                    }
                    // last one remaining... color!
                    // or else, it will fail :)
                    if (is_final) {
                        if (v.color != null || next_is_size) {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        exp = new CssExpression();
                        exp.addValue(val);
                        bg_color = new CssBackgroundColor(ac, exp, check);
                        v.color = (CssValue) bg_color.get();
                        break;
                    }
                    // unrecognized or unwanted ident
                    // let it fail now
                default:
                    throw new InvalidParamException("value", val,
                            getPropertyName(), ac);
            }

            if (op == SLASH) {
                next_is_size = true;
            } else if (op != SPACE) {
                throw new InvalidParamException("operator", val,
                        getPropertyName(), ac);
            }
            expression.next();
        }
        align_bg_values(v);
        return v;
    }

    private void align_bg_values(CssBackgroundValue v) {
        // <bg-layer> = <bg-image> || <bg-position> || / <bg-size> || <repeat-style> ||
        //              <attachment> || <bg-origin>
        Object value;
        if (v.bg_image == null) {
            value = (new CssBackgroundImage()).get();
            if (value instanceof CssValue) {
                v.bg_image_value = (CssValue) value;
            }
        } else {
            v.bg_image_value = v.bg_image;
        }

        if (v.bg_position == null) {
            value = (new CssBackgroundPosition()).get();
            if (value instanceof CssValue) {
                v.bg_position_value = (CssValue) value;
            }
        } else {
            v.bg_position_value = v.bg_position;
        }

        if (v.bg_size == null) {
            value = (new CssBackgroundSize()).get();
            if (value instanceof CssValue) {
                v.bg_size_value = (CssValue) value;
            }
        } else {
            v.bg_size_value = v.bg_size;
        }

        if (v.repeat_style == null) {
            value = (new CssBackgroundRepeat()).get();
            if (value instanceof CssValue) {
                v.repeat_style_value = (CssValue) value;
            }
        } else {
            v.repeat_style_value = v.repeat_style;
        }

        if (v.attachment == null) {
            value = (new CssBackgroundAttachment()).get();
            if (value instanceof CssValue) {
                v.attachment_value = (CssValue) value;
            }
        } else {
            v.attachment_value = v.attachment;
        }

        if (v.origin == null) {
            value = (new CssBackgroundOrigin()).get();
            if (value instanceof CssValue) {
                CssValue css_val = (CssValue) value;
                v.origin_value = (CssValue) value;
                // If 'background-origin' is present and its value matches a
                // possible value for 'background-clip' then it also sets
                //  'background-clip' to that value.
                if ((css_val.getType() == CssTypes.CSS_IDENT) &&
                        CssBackgroundClip.isMatchingIdent((CssIdent) css_val)) {
                    v.clip_value = v.origin_value;
                }
            }
        } else {
            v.origin_value = v.origin;
        }

        if (v.color == null) {
            v.color_value = (new CssBackgroundColor()).getColor();
        } else {
            v.color_value = v.color;
        }
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

        CssValue bg_image_value = null;
        CssValue bg_position_value = null;
        CssValue bg_size_value = null;
        CssValue repeat_style_value = null;
        CssValue attachment_value = null;
        CssValue origin_value = null;
        // If 'background-origin' is present and its value matches a possible
        // value for 'background-clip' then it also sets 'background-clip' to
        // that value.
        CssValue clip_value = null;
        CssValue color_value = null;

        public boolean equals(CssBackgroundValue v) {
            if (bg_image_value == null) {
                if (v.bg_image_value != null) {
                    return false;
                }
            } else if (!bg_image_value.equals(v.bg_image_value)) {
                return false;
            }
            if (bg_position_value == null) {
                if (v.bg_position_value != null) {
                    return false;
                }
            } else if (!bg_position_value.equals(v.bg_position_value)) {
                return false;
            }
            if (bg_size_value == null) {
                if (v.bg_size_value != null) {
                    return false;
                }
            } else if (!bg_size_value.equals(v.bg_size_value)) {
                return false;
            }
            if (repeat_style_value == null) {
                if (v.repeat_style_value != null) {
                    return false;
                }
            } else if (!repeat_style_value.equals(v.repeat_style_value)) {
                return false;
            }
            if (attachment_value == null) {
                if (v.attachment_value != null) {
                    return false;
                }
            } else if (!attachment_value.equals(v.attachment_value)) {
                return false;
            }
            if (origin_value == null) {
                if (v.origin_value != null) {
                    return false;
                }
            } else if (!origin_value.equals(v.origin_value)) {
                return false;
            }
            if (clip_value == null) {
                if (v.clip_value != null) {
                    return false;
                }
            } else if (!clip_value.equals(v.clip_value)) {
                return false;
            }
            if (color_value == null) {
                if (v.color_value != null) {
                    return false;
                }
            } else if (!color_value.equals(v.color_value)) {
                return false;
            }
            // at last!
            return true;
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
