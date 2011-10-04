// $Id$
// @author Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 2010  World Wide Web Consortium (MIT, ERCIM, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;

/**
 * http://www.w3.org/TR/2009/CR-css3-background-20091217/#the-background-clip
 * Name: 	background-clip
 * Value: 	[border-box | padding-box ] [ , [border-box | padding-box ] ]*
 * Initial: 	border-box
 * Applies to: 	all elements
 * Inherited: 	no
 * Percentages: 	N/A
 * Media: 	visual
 * Computed value: 	same as specified value
 * <p/>
 * Determines the background painting area.
 */

public class CssBackgroundClip extends org.w3c.css.properties.css.CssBackgroundClip {

    public final static CssIdent border_box;
    public final static CssIdent padding_box;

    Object value;

    static {
        border_box = CssIdent.getIdent("border-box");
        padding_box = CssIdent.getIdent("padding-box");
    }

    public static boolean isMatchingIdent(CssIdent ident) {
        return (border_box.equals(ident) ||
                padding_box.equals(ident));
    }

    /**
     * Create a new CssBackgroundClip
     */
    public CssBackgroundClip() {
    }

    /**
     * Create a new CssBackgroundClip
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException Incorrect value
     */
    public CssBackgroundClip(ApplContext ac, CssExpression expression,
                             boolean check) throws InvalidParamException {

        ArrayList<CssValue> values = new ArrayList<CssValue>();

        CssValue val;
        char op;

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();
            switch (val.getType()) {
                case CssTypes.CSS_IDENT:
                    if (inherit.equals(val)) {
                        // if we got inherit after other values, fail
                        // if we got more than one value... fail
                        if ((values.size() > 0) || (expression.getCount() > 1)) {
                            throw new InvalidParamException("value", val,
                                    getPropertyName(), ac);
                        }
                        values.add(inherit);
                        break;
                    } else if (border_box.equals(val)) {
                        values.add(border_box);
                        break;
                    } else if (padding_box.equals(val)) {
                        values.add(padding_box);
                        break;
                    }
                default:
                    throw new InvalidParamException("value", val,
                            getPropertyName(), ac);
            }
            expression.next();
            if (!expression.end() && (op != COMMA)) {
                throw new InvalidParamException("operator",
                        ((new Character(op)).toString()), ac);
            }
        }
        if (values.size() == 1) {
            value = values.get(0);
        } else {
            value = values;
        }
    }

    public CssBackgroundClip(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    public void set(Object val) {
        value = val;
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        // TODO FIXME -> in CssStyle
        if (((Css3Style) style).cssBackgroundClip != null)
            style.addRedefinitionWarning(ac, this);
        ((Css3Style) style).cssBackgroundClip = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css3Style) style).getCssBackgroundClip();
        } else {
            return ((Css3Style) style).cssBackgroundClip;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssBackgroundClip &&
                value.equals(((CssBackgroundClip) property).value));
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
        if (value instanceof ArrayList) {
            ArrayList values = (ArrayList) value;
            StringBuilder sb = new StringBuilder();
            for (Object aValue : values) {
                sb.append(aValue.toString()).append(", ");
            }
            sb.setLength(sb.length() - 2);
            return sb.toString();
        }
        return value.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (border_box == value);
    }

}
