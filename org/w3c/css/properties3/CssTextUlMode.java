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

/**
 *  <P>
 *  <EM>Value:</EM>  continuous || words || inherit<BR>
 *  <EM>Initial:</EM>continuous<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property specifies the mode for the underline, that is whether the 
 *  underline is continuous or whether it appears only under words and not 
 *  whitespace.
 */

public class CssTextUlMode extends CssProperty {
 
    CssValue ulmode;

    static CssIdent continuous = new CssIdent("continuous");
    static CssIdent words = new CssIdent("words");

    /**
     * Create a new CssTextUlMode
     */
    public CssTextUlMode() {
	ulmode = continuous;
    }

    /**
     * Create a new CssTextUlStyle
     * 
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextUlMode(ApplContext ac, CssExpression expression) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(continuous)) {
	    ulmode = continuous;
	    expression.next();
	}
	else if (val.equals(words)) {
	    ulmode = words;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    ulmode = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", 
					    expression.getValue(), 
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextUlMode != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextUlMode = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextUlMode();
	}
	else {
	    return ((Css3Style) style).cssTextUlMode;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextUlMode &&
		ulmode.equals(((CssTextUlMode) property).ulmode));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-underline-mode";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return ulmode;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return ulmode.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return ulmode.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return ulmode == continuous;
    }

}
