//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM>  auto || &lt;color&gt; || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property specifies the color for the line-through.
 */

public class CssTextLTColor extends CssProperty {

    CssValue ltcolor;

    static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssTextLTColor
     */
    public CssTextLTColor() {
	ltcolor = auto;
    }

    /**
     * Create a new CssTextLTColor
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextLTColor(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val instanceof org.w3c.css.values.CssColor) {
	    ltcolor = val;
	    expression.next();
	}
        else if (val instanceof CssIdent) {
	    if (val.equals(inherit)) {
		ltcolor = inherit;
		expression.next();
	    }
	    else if (val.equals(auto)) {
		ltcolor = auto;
		expression.next();
	    }
	    else {
		ltcolor = new org.w3c.css.values.CssColor(ac, (String) val.get());
		expression.next();
	    }
	}
	else {
	    throw new InvalidParamException("value",
					    expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssTextLTColor(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextLTColor != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextLTColor = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextLTColor();
	}
	else {
	    return ((Css3Style) style).cssTextLTColor;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextLTColor &&
		ltcolor.equals(((CssTextLTColor) property).ltcolor));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-line-through-color";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return ltcolor;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return ltcolor.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return ltcolor.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return ltcolor == auto;
    }

}

