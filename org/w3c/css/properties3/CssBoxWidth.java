//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.Util;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssPercentage;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;length&gt; || &lt;percentage&gt; || auto || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>same as 'width' and 'height'<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class CssBoxWidth extends CssProperty {

    CssValue boxwidth;
    ApplContext ac;

    CssIdent auto = new CssIdent("auto");
    CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssBoxWidth
     */
    public CssBoxWidth() {
	boxwidth = auto;
    }

    /**
     * Create a new CssBoxWidth
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBoxWidth(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(auto)) {
	    boxwidth = auto;
	    expression.next();
	}
	else if (val.equals(initial)) {
		boxwidth = initial;
		expression.next();
	}
	else if (val.equals(inherit)) {
	    boxwidth = inherit;
	    expression.next();
	}
	else if (val instanceof CssLength) {
	    boxwidth = val;
	    expression.next();
	} else if (val instanceof CssPercentage) {
	    boxwidth = val;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssBoxWidth != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBoxWidth = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getBoxWidth();
	} else {
	    return ((Css3Style) style).cssBoxWidth;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBoxWidth &&
                boxwidth.equals( ((CssBoxWidth) property).boxwidth));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "box-width";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return boxwidth;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return boxwidth.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return boxwidth.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return boxwidth == auto;
    }

}
