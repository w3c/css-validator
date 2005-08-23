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
 *  This property specifies the color for the overline.
 */

public class CssTextOLColor extends CssProperty {
 
    CssValue olcolor;

    static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssTextOLColor
     */
    public CssTextOLColor() {
	olcolor = auto;
    }

    /**
     * Create a new CssTextOLColor
     * 
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextOLColor(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val instanceof org.w3c.css.values.CssColor) {
	    olcolor = val;
	    expression.next();
	}
        else if (val instanceof CssIdent) {
	    if (val.equals(inherit)) {
		olcolor = inherit;
		expression.next();
	    }
	    else if (val.equals(auto)) {
		olcolor = auto;
		expression.next();
	    }
	    else {
		olcolor = new org.w3c.css.values.CssColor(ac, (String) val.get());
		expression.next();
	    }
	}
	else {
	    throw new InvalidParamException("value", 
					    expression.getValue(), 
					    getPropertyName(), ac);
	}
    }

    public CssTextOLColor(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextOLColor != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextOLColor = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextOLColor();
	}
	else {
	    return ((Css3Style) style).cssTextOLColor;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextOLColor &&
		olcolor.equals(((CssTextOLColor) property).olcolor));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-overline-color";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return olcolor;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return olcolor.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return olcolor.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return olcolor == auto;
    }

}
