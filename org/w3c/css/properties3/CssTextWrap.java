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
 *  <EM>Value:</EM> normal || none || inherit<BR>
 *  <EM>Initial:</EM>normal<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property controls whether or not text wraps when it reaches the flow 
 *  edge of its containing block box
 */

public class CssTextWrap extends CssProperty {
 
    CssValue textwrap;
    ApplContext ac;

    static CssIdent normal = new CssIdent("normal");
    static CssIdent none = new CssIdent("none");

    /**
     * Create a new CssTextWrap
     */
    public CssTextWrap() {
	textwrap = normal;
    }

    /**
     * Create a new CssTextWrap
     * 
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextWrap(ApplContext ac, CssExpression expression) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(normal)) {
	    textwrap = normal;
	    expression.next();
	}
	else if (val.equals(none)) {
	    textwrap = none;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    textwrap = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextWrap != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextWrap = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextWrap();
	}
	else {
	    return ((Css3Style) style).cssTextWrap;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextWrap &&
		textwrap.equals(((CssTextWrap) property).textwrap));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-wrap";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return textwrap;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return textwrap.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return textwrap.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return textwrap == normal;
    }

}
