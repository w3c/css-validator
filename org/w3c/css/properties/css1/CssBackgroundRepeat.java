//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.*;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.HashMap;

/**
 * <H4>
 * <A NAME="background-repeat">5.3.4 &nbsp;&nbsp; 'background-repeat'</A>
 * </H4>
 * <p/>
 * <EM>Value:</EM> repeat | repeat-x | repeat-y | no-repeat<BR>
 * <EM>Initial:</EM> repeat<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentage values:</EM> N/A<BR>
 * <p/>
 * If a background image is specified, the value of 'background-repeat' determines
 * how/if the image is repeated.
 * <p/>
 * A value of 'repeat' means that the image is repeated both horizontally and
 * vertically. The 'repeat-x' ('repeat-y') value makes the image repeat horizontally
 * (vertically), to create a single band of images from one side to the other.
 * With a value of 'no-repeat', the image is not repeated.
 * <PRE>
 * BODY {
 * background: red url(pendant.gif);
 * background-repeat: repeat-y;
 * }
 * </PRE>
 * <p/>
 * In the example above, the image will only be repeated vertically.
 *
 * @version $Revision$
 */
public class CssBackgroundRepeat extends org.w3c.css.properties.css.CssBackgroundRepeat {

    private static HashMap<String, CssIdent> allowed_values;

    static {
        allowed_values = new HashMap<String, CssIdent>();
        String[] REPEAT = {"repeat", "repeat-x", "repeat-y", "no-repeat"};

        for (String aREPEAT : REPEAT) {
            allowed_values.put(aREPEAT, CssIdent.getIdent(aREPEAT));
        }
    }

	protected static boolean checkMatchingIdent(CssIdent ident) {
		for (CssIdent id : allowed_values.values()) {
			if (id.equals(ident)) {
				return true;
			}
		}
		return false;
	}

    public CssValue value;


    /**
     * Create a new CssBackgroundRepeat
     */
    public CssBackgroundRepeat() {
        value = repeat;
    }

    /**
     * Set the value of the property
     *
     * @param expression The expression for this property
     * @throws InvalidParamException The expression is incorrect
     */
    public CssBackgroundRepeat(ApplContext ac, CssExpression expression,
                               boolean check) throws InvalidParamException {
        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        CssValue val = expression.getValue();
        setByUser();

        if (val.getType() != CssTypes.CSS_IDENT) {
            throw new InvalidParamException("value", expression.getValue(),
                    getPropertyName(), ac);
        }
        value = allowed_values.get(val.toString());
        if (value == null) {
            throw new InvalidParamException("value", expression.getValue(),
                    getPropertyName(), ac);
        }
        expression.next();
    }

    public CssBackgroundRepeat(ApplContext ac, CssExpression expression)
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
        org.w3c.css.properties.css.CssBackground cssBackground = ((Css1Style) style).cssBackground;
        if (cssBackground.repeat != null)
            style.addRedefinitionWarning(ac, this);
        cssBackground.repeat = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css1Style) style).getBackgroundRepeat();
        } else {
            return ((Css1Style) style).cssBackground.repeat;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssBackgroundRepeat &&
                value == ((CssBackgroundRepeat) property).value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (repeat == value);
    }
}



