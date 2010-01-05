//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.properties.css2.font.FontSize;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;font-size&gt; || auto || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>element's computed 'font-size'<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  If 'text-align-last' is 'size', the fonts of the last line of an element are not allowed to become larger than the larger of 'font-size' and 'max-font-size'. 'auto' means that there is no limit.
*/

public class CssMaxFontSize extends CssProperty {

    CssValue max;

    static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssMaxFontSize
     */
    public CssMaxFontSize() {
	max = auto;
    }

    /**
     * Create a new CssMaxFontSize
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssMaxFontSize(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(auto)) {
	    max = auto;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    max = inherit;
	    expression.next();
	}
	else {
	    try {
		FontSize fontsize = new FontSize(ac, expression);
		max = val;
		expression.next();
	    }
	    catch (InvalidParamException e) {
		throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
	    }
	}
    }

    public CssMaxFontSize(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssMaxFontSize != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssMaxFontSize = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getMaxFontSize();
	} else {
	    return ((Css3Style) style).cssMaxFontSize;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssMaxFontSize &&
                max.equals( ((CssMaxFontSize) property).max));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "max-font-size";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return max;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return max.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return max.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return max == auto;
    }

}
