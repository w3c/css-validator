// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Rewritten by Yves Lafon <ylafon@w3.org>

// (c) COPYRIGHT MIT, Keio and ERCIM, 1997-2010.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.Css1Style;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;
import java.util.HashMap;

import static org.w3c.css.values.CssOperator.COMMA;
import static org.w3c.css.values.CssOperator.SPACE;

/**
 * http://www.w3.org/TR/2009/CR-css3-background-20091217/#background-position
 * <p/>
 * Name: 	background-position
 * Value: 	&lt;bg-position&gt; [ , &lt;bg-position&gt; ]*
 * Initial: 	0% 0%
 * Applies to: 	all elements
 * Inherited: 	no
 * Percentages: 	refer to size of background positioning area minus size of
 * background image; see text
 * Media: 	visual
 * Computed value: 	If one or two values are specified, for a &lt;length&gt;
 * the absolute value, otherwise a percentage. If three or
 * four values are specified, two pairs of a keyword plus a
 * length or percentage.
 * <p/>
 * <p/>
 * If background images have been specified, this property specifies their
 * initial position (after any resizing) within their corresponding
 * background positioning area.
 * <p/>
 * Where
 * <p/>
 * &lt;bg-position&gt; = [
 * [ [ &lt;percentage&gt; | &lt;length&gt; | left | center | right ] ]
 * [ [ &lt;percentage&gt; | &lt;length&gt; | top | center | bottom ] ]?
 * |
 * [ center | [ left | right ] [ &lt;percentage&gt; | &lt;length&gt; ]? ] ||
 * [ center | [ top | bottom ] [ &lt;percentage&gt; | &lt;length&gt; ]? ]
 * ]
 */
public class CssBackgroundPosition extends CssProperty {

    private static final String propertyName = "background-position";

    private static HashMap<String, CssIdent> allowed_values;
    private static final CssIdent center, top, bottom, left, right;
    private static final CssPercentage defaultPercent0, defaultPercent50;
    private static final CssPercentage defaultPercent100;

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

    public static boolean isMatchingIdent(CssIdent ident) {
        return allowed_values.containsKey(ident.toString());
    }

    Object value;

    /**
     * Create a new CssBackgroundPosition
     */
    public CssBackgroundPosition() {
        value = new CssBackgroundPositionValue();
    }

    /**
     * Creates a new CssBackgroundPosition
     *
     * @param expression The expression for this property
     * @throws InvalidParamException Values are incorrect
     */
    public CssBackgroundPosition(ApplContext ac, CssExpression expression,
                                 boolean check) throws InvalidParamException {
        setByUser();
        CssValue val;
        ArrayList<CssBackgroundPositionValue> values;
        CssBackgroundPositionValue b_val = null;
        char op;

        values = new ArrayList<CssBackgroundPositionValue>();
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

    public CssBackgroundPosition(ApplContext ac, CssExpression expression)
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
     * Returns the name of this property
     */
    public final String getPropertyName() {
        return propertyName;
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
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        CssBackground cssBackground = ((Css1Style) style).cssBackground;
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
            return ((Css1Style) style).getBackgroundPosition();
        } else {
            return ((Css1Style) style).cssBackground.position;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return ((property != null) &&
                (property instanceof CssBackgroundPosition) &&
                (value.equals(((CssBackgroundPosition) property).value)));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        if (!(value instanceof CssBackgroundPositionValue)) {
            return false;
        }
        CssBackgroundPositionValue v = (CssBackgroundPositionValue) value;
        return ((v.val_vertical == defaultPercent0) &&
                (v.val_horizontal == defaultPercent0) &&
                (v.vertical_offset == null) &&
                (v.horizontal_offset == null));
    }

    public void check(CssBackgroundPositionValue v, ApplContext ac)
            throws InvalidParamException {
        int nb_keyword = 0;
        int nb_percentage = 0;
        int nb_length = 0;
        int nb_values = v.value.size();

        if (nb_values > 4) {
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
        if ((nb_keyword > 2) || (nb_length > 2) || (nb_percentage > 2)) {
            throw new InvalidParamException("unrecognize", ac);
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
                        // If three or four values are given, then each
                        // <percentage> or<length> represents an offset and
                        // must be preceded by a keyword
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
                // ok so we have two keywords, with possible offsets
                // we must check that every possible offset is right
                // after a keyword and also that the two keywords are
                // not incompatible
                boolean got_ident = false;
                CssIdent id1 = null;
                CssIdent id2 = null;
                CssValue off1 = null;
                CssValue off2 = null;
                for (CssValue aValue : v.value) {
                    switch (aValue.getType()) {
                        case CssTypes.CSS_IDENT:
                            aValue = allowed_values.get(aValue.toString());
                            if (aValue == null) {
                                throw new InvalidParamException("unrecognize",
                                        aValue, propertyName, ac);
                            }
                            got_ident = true;
                            if (id1 == null) {
                                id1 = (CssIdent) aValue;
                            } else {
                                id2 = (CssIdent) aValue;
                                // we got both, let's check.
                                if (((isVertical(id1) && isVertical(id2))) ||
                                        (isHorizontal(id1) && isHorizontal(id2))) {
                                    throw new InvalidParamException("incompatible",
                                            id1, id2, ac);
                                }
                            }
                            break;
                        case CssTypes.CSS_NUMBER:
                            aValue = ((CssNumber) aValue).getPercentage();
                        case CssTypes.CSS_PERCENTAGE:
                        case CssTypes.CSS_LENGTH:
                            if (!got_ident) {
                                throw new InvalidParamException("unrecognize",
                                        aValue, propertyName, ac);
                            }
                            if (id2 == null) {
                                off1 = aValue;
                            } else {
                                off2 = aValue;
                            }
                            got_ident = false;
                            break;
                        default:
                            // should never happen
                    }
                }

                if (isVertical(id1) || isHorizontal(id2)) {
                    // if an offset is present and value is 'center'
                    if (((off1 != null) && !isVertical(id1)) ||
                            ((off2 != null) && !isHorizontal(id2))) {
                        throw new InvalidParamException("incompatible",
                                id1, id2, ac);
                    }
                    v.horizontal = id2;
                    v.val_horizontal = identToPercent(id2);
                    v.horizontal_offset = off2;
                    v.vertical = id1;
                    v.val_vertical = identToPercent(id1);
                    v.vertical_offset = off1;
                } else {
                    if (((off2 != null) && !isVertical(id2)) ||
                            ((off1 != null) && !isHorizontal(id1))) {
                        throw new InvalidParamException("incompatible",
                                id1, id2, ac);
                    }
                    v.horizontal = id1;
                    v.val_horizontal = identToPercent(id1);
                    v.horizontal_offset = off1;
                    v.vertical = id2;
                    v.val_vertical = identToPercent(id2);
                    v.vertical_offset = off2;
                }
        }
    }

    public static CssPercentage identToPercent(CssIdent ident) {
        if (center.equals(ident)) {
            return defaultPercent50;
        } else if (top.equals(ident) || left.equals(ident)) {
            return defaultPercent0;
        } else if (bottom.equals(ident) || right.equals(ident)) {
            return defaultPercent100;
        }
        return defaultPercent0; // FIXME throw an exception ?
    }

    public static boolean isHorizontal(CssIdent ident) {
        return (left.equals(ident) || right.equals(ident));
    }

    public static boolean isVertical(CssIdent ident) {
        return (top.equals(ident) || bottom.equals(ident));
    }

    // placeholder for the different values

    public class CssBackgroundPositionValue extends CssValueList {
        public CssValue vertical = null;
        public CssValue horizontal = null;
        public CssValue vertical_offset = null;
        public CssValue horizontal_offset = null;

        public CssValue val_vertical = defaultPercent0;
        public CssValue val_horizontal = defaultPercent0;

        public boolean equals(CssBackgroundPositionValue v) {
            // check vertical compatibility (with optional values)
            if (!val_vertical.equals(v.val_vertical)) {
                return false;
            }
            if (vertical_offset != null) {
                if (!vertical_offset.equals(v.vertical_offset)) {
                    return false;
                }
            } else if (v.vertical_offset != null) {
                return false;
            }

            if (!val_horizontal.equals(v.val_horizontal)) {
                return false;
            }
            if (horizontal_offset != null) {
                if (!horizontal_offset.equals(v.horizontal_offset)) {
                    return false;
                }
            } else if (v.horizontal_offset != null) {
                return false;
            }
            // yeah!    
            return true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (horizontal != null) {
                sb.append(horizontal);
                if (horizontal_offset != null) {
                    sb.append(' ').append(horizontal_offset);
                }
                if (vertical != null) {
                    sb.append(' ');
                }
            }
            if (vertical != null) {
                sb.append(vertical);
                if (vertical_offset != null) {
                    sb.append(' ').append(vertical_offset);
                }
            }
            return sb.toString();
        }

    }

}
