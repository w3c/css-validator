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
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssNumber;

/**
 *  <P>
 *  <EM>Value:</EM> content-box || border-box || inherit<BR>
 *  <EM>Initial:</EM>content-box<BR>
 *  <EM>Applies to:</EM>all elements that accept width or height<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class CssBoxSizing extends CssProperty {

    CssValue boxsizing;
    ApplContext ac;

    CssIdent contentbox = new CssIdent("content-box");
    CssIdent borderbox = new CssIdent("border-box");
    CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssBoxSizing
     */
    public CssBoxSizing() {
	boxsizing = contentbox;
    }

    public CssBoxSizing(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(contentbox)) {
	    boxsizing = contentbox;
	    expression.next();
	}
	else if (val.equals(borderbox)) {
	    boxsizing = borderbox;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    boxsizing = inherit;
	    expression.next();
	}
	else if (val.equals(initial)) {
	    boxsizing = initial;
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
	if (((Css3Style) style).cssBoxSizing != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBoxSizing = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getBoxSizing();
	} else {
	    return ((Css3Style) style).cssBoxSizing;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBoxSizing &&
                boxsizing.equals( ((CssBoxSizing) property).boxsizing));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "box-sizing";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return boxsizing;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return boxsizing.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return boxsizing.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return boxsizing == contentbox;
    }

}
