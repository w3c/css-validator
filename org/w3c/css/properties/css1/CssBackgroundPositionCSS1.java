//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssBackgroundPosition;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.HashMap;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * <H4>
 * &nbsp;&nbsp; 'background-position'
 * </H4>
 * <p/>
 * <EM>Value:</EM> [&lt;percentage&gt; | &lt;length&gt;]{1,2} | [top | center
 * | bottom] || [left | center | right]<BR>
 * <EM>Initial:</EM> 0% 0%<BR>
 * <EM>Applies to:</EM> block-level and replaced elements<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentage values:</EM> refer to the size of the element itself<BR>
 * <P> If a background image has been specified, the value of
 * 'background-position' specifies its initial position.
 * <P> With a value pair of '0% 0%', the upper left corner of the image is
 * placed in the upper left corner of the box that surrounds the content of
 * the element (i.e., not the box that surrounds the padding, border or
 * margin). A value pair of '100% 100%' places the lower right corner of the
 * image in the lower right corner of the element. With a value pair of '14%
 * 84%', the point 14% across and 84% down the image is to be placed at the
 * point 14% across and 84% down the element.
 * <P> With a value pair of '2cm 2cm', the upper left corner of the image is
 * placed 2cm to the right and 2cm below the upper left corner of the element.
 * <P> If only one percentage or length value is given, it sets the horizontal
 * position only, the vertical position will be 50%. If two values are given,
 * the horizontal position comes first. Combinations of length and percentage
 * values are allowed, e.g. '50% 2cm'. Negative positions are allowed.
 * <P> One can also use keyword values to indicate the position of the
 * background image. Keywords cannot be combined with percentage values, or
 * length values.  The possible combinations of keywords and their
 * interpretations are as follows:
 * <p/>
 * <UL>
 * <LI>
 * 'top left' and 'left top' both mean the same as '0% 0%'.
 * <LI>
 * 'top', 'top center' and 'center top' mean the same as '50% 0%'.
 * <LI>
 * 'right top' and 'top right' mean the same as '100% 0%'.
 * <LI>
 * 'left', 'left center' and 'center left' mean the same as '0% 50%'.
 * <LI>
 * 'center' and 'center center' mean the same as '50% 50%'.
 * <LI>
 * 'right', 'right center' and 'center right' mean the same as '100% 50%'.
 * <LI>
 * 'bottom left' and 'left bottom' mean the same as '0% 100%'.
 * <LI>
 * 'bottom', 'bottom center' and 'center bottom' mean the same as '50% 100%'.
 * <LI>
 * 'bottom right' and 'right bottom' mean the same as '100% 100%'.
 * </UL>
 * <p/>
 * examples:
 * <PRE>
 * BODY { background: url(banner.jpeg) right top }    / * 100%   0% * /
 * BODY { background: url(banner.jpeg) top center }   / *  50%   0% * /
 * BODY { background: url(banner.jpeg) center }       / *  50%  50% * /
 * BODY { background: url(banner.jpeg) bottom }       / *  50% 100% * /
 * </PRE>
 * <p/>
 * If the background image is fixed with regard to the canvas (see the
 * 'background-attachment' property above), the image is placed relative to
 * the canvas instead of the element. E.g.:
 * <PRE>
 * BODY {
 * background-image: url(logo.png);
 * background-attachment: fixed;
 * background-position: 100% 100%;
 * }
 * </PRE>
 * <p/>
 * In the example above, the image is placed in the lower right corner of the
 * canvas.
 *
 * @version $Revision$
 * @see org.w3c.css.properties.css.CssBackgroundAttachment
 */
public class CssBackgroundPositionCSS1 extends CssBackgroundPosition {

    public static boolean checkMatchingIdent(CssIdent ident) {
        return allowed_values.containsValue(ident);
    }

    private static final String propertyName = "background-position";

    public static HashMap<String, CssIdent> allowed_values;
    public static CssIdent center, top, bottom, left, right;
    private static CssPercentage defaultPercent0, defaultPercent50;
    private static CssPercentage defaultPercent100;

    static {
        top = CssIdent.getIdent("top");
        bottom = CssIdent.getIdent("bottom");
        left = CssIdent.getIdent("left");
        right = CssIdent.getIdent("right");
        center = CssIdent.getIdent("center");
        allowed_values = new HashMap<String, CssIdent>();
        allowed_values.put("top", top);
        allowed_values.put("bottom", bottom);
        allowed_values.put("left", left);
        allowed_values.put("right", right);
        allowed_values.put("center", center);

        defaultPercent0 = new CssPercentage(0);
        defaultPercent50 = new CssPercentage(50);
        defaultPercent100 = new CssPercentage(100);
    }

    public CssValue value;

    /**
     * Create a new CssBackgroundPositionCSS1
     */
    public CssBackgroundPositionCSS1() {

    }

    /**
     * Creates a new CssBackgroundPositionCSS1
     *
     * @param expression The expression for this property
     * @throws InvalidParamException Values are incorrect
     */
    public CssBackgroundPositionCSS1(ApplContext ac, CssExpression expression,
                                     boolean check) throws InvalidParamException {
        int nb_val = expression.getCount();

        if (check && nb_val > 2) {
            throw new InvalidParamException("unrecognize", ac);
        }

        setByUser();
        setByUser();
        CssValue val;
        CssBackgroundPositionValue b_val = null;
        char op;

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
                b_val = new CssBackgroundPositionValue();
            }
            // we will check later
            b_val.add(val);
            expression.next();

            if (op != SPACE) {
                throw new InvalidParamException("operator",
                        ((new Character(op)).toString()), ac);
            }

        }
        // if we reach the end in a value that can come in pair
        if (b_val != null) {
            check(b_val, ac);
            value = b_val;
        }
    }

    // check the value

    public void check(CssBackgroundPositionValue v, ApplContext ac)
            throws InvalidParamException {
        int nb_keyword = 0;
        int nb_percentage = 0;
        int nb_length = 0;
        int nb_values = v.value.size();

        if (nb_values > 2) {
            throw new InvalidParamException("unrecognize", ac);
        }
        // basic check
        for (CssValue aValue : v.value) {
            switch (aValue.getType()) {
                case CssTypes.CSS_NUMBER:
                    aValue = ((CssNumber) aValue).getLength();
                case CssTypes.CSS_LENGTH:
                    nb_length++;
                    break;
                case CssTypes.CSS_PERCENTAGE:
                    nb_percentage++;
                    break;
                case CssTypes.CSS_IDENT:
                    nb_keyword++;
                    break;
                default:
                    throw new InvalidParamException("unrecognize", aValue,
                            ac);
            }
        }

        // this is unnecessary complex, blame it on the CSS3 spec.
        switch (nb_keyword) {
            case 0:
                // no keyword, so it's easy, it depends on the number
                // of values :)
                switch (nb_values) {
                    case 1:
                        // If only one value is specified, the second value
                        // is assumed to be 'center'.
                        v.horizontal = v.value.get(0);
                        if (v.horizontal.getType() == CssTypes.CSS_NUMBER) {
                            v.horizontal = defaultPercent0;
                        }
                        v.val_horizontal = v.horizontal;
                        v.val_vertical = defaultPercent50;
                        break;
                    case 2:
                        v.horizontal = v.value.get(0);
                        if (v.horizontal.getType() == CssTypes.CSS_NUMBER) {
                            v.horizontal = defaultPercent0;
                        }
                        v.val_horizontal = v.horizontal;
                        v.vertical = v.value.get(1);
                        if (v.vertical.getType() == CssTypes.CSS_NUMBER) {
                            v.vertical = defaultPercent0;
                        }
                        v.val_vertical = v.vertical;
                        break;
                    default:
                        // should never happen
                        throw new InvalidParamException("unrecognize",
                                ac);

                }
                break;
            // we got one keyword... let's have fun...
            case 1:
                switch (nb_values) {
                    case 1:
                        CssIdent ident = (CssIdent) v.value.get(0);
                        // ugly as we need to set values for equality tests
                        v.val_vertical = defaultPercent50;
                        v.val_horizontal = defaultPercent50;
                        ident = allowed_values.get(ident.toString());
                        if (ident != null) {
                            if (isVertical(ident)) {
                                v.vertical = ident;
                                v.val_vertical = identToPercent(ident);
                            } else {
                                // horizontal || center
                                v.horizontal = ident;
                                v.val_horizontal = identToPercent(ident);
                            }
                            break;
                        }
                        throw new InvalidParamException("unrecognize",
                                ident, propertyName, ac);
                    case 2:
                        // one ident, two values... first MUST be horizontal
                        // and second vertical
                        CssValue val0 = v.value.get(0);
                        if (val0.getType() == CssTypes.CSS_IDENT) {

                            ident = allowed_values.get(val0.toString());
                            if (ident == null) {
                                throw new InvalidParamException("unrecognize",
                                        ident, propertyName, ac);
                            }
                            if (isVertical(ident)) {
                                throw new InvalidParamException("incompatible",
                                        ident, v.value.get(1), ac);
                            }
                            v.horizontal = ident;
                            v.val_horizontal = identToPercent(ident);
                            // and the vertical value...
                            v.vertical = v.value.get(1);
                            if (v.vertical.getType() == CssTypes.CSS_NUMBER) {
                                v.vertical = defaultPercent0;
                            }
                            v.val_vertical = v.vertical;
                        } else {
                            ident = allowed_values.get(v.value.get(1).toString());
                            if (ident == null) {
                                throw new InvalidParamException("unrecognize",
                                        ident, propertyName, ac);
                            }
                            if (isHorizontal(ident)) {
                                throw new InvalidParamException("incompatible",
                                        val0, v.value.get(1), ac);
                            }
                            v.vertical = ident;
                            v.val_vertical = identToPercent(ident);
                            // and the first value
                            v.horizontal = val0;
                            if (v.horizontal.getType() == CssTypes.CSS_NUMBER) {
                                v.horizontal = defaultPercent0;
                            }
                            v.val_horizontal = v.horizontal;
                        }
                        break;
                    default:
                        // one ident, 3 or 4 values is not allowed
                        throw new InvalidParamException("unrecognize",
                                ac);
                }
                break;
            default:
                // we got two keywords for two values, yeah!
                CssIdent id1 = (CssIdent) v.value.get(0);
                CssIdent id2 = (CssIdent) v.value.get(1);


                if (((isVertical(id1) && isVertical(id2))) ||
                        (isHorizontal(id1) && isHorizontal(id2))) {
                    throw new InvalidParamException("incompatible",
                            id1, id2, ac);
                }
                if (isVertical(id1) || isHorizontal(id2)) {
                    v.horizontal = id2;
                    v.val_horizontal = identToPercent(id2);
                    v.vertical = id1;
                    v.val_vertical = identToPercent(id1);
                } else {
                    v.horizontal = id1;
                    v.val_horizontal = identToPercent(id1);
                    v.vertical = id2;
                    v.val_vertical = identToPercent(id2);
                }
        }
    }

    public CssBackgroundPositionCSS1(ApplContext ac, CssExpression expression)
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
        return (inherit == value);
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
        if (cssBackground.position != null)
            style.addRedefinitionWarning(ac, this);
        cssBackground.position = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css1Style) style).getBackgroundPositionCSS1();
        } else {
            return ((Css1Style) style).cssBackgroundCSS1.position;
        }
    }
}
