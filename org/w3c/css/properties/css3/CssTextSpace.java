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
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> normal || honor || inherit<BR>
 *  <EM>Initial:</EM>normal<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property controls whether or not white space in text stream is
 *  collapsed, or kept as is.
 */

public class CssTextSpace extends CssProperty {

    CssValue textspace;
    ApplContext ac;

    static CssIdent normal = new CssIdent("normal");
    static CssIdent honor = new CssIdent("honor");

    /**
     * Create a new CssTextSpace
     */
    public CssTextSpace() {
	textspace = normal;
    }

    /**
     * Create a new CssTextSpace
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextSpace(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(normal)) {
	    textspace = normal;
	    expression.next();
	}
	else if (val.equals(honor)) {
	    textspace = honor;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    textspace = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(),
		    getPropertyName(), ac);
	}
    }

    public CssTextSpace(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextSpace != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextSpace = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextSpace();
	}
	else {
	    return ((Css3Style) style).cssTextSpace;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextSpace &&
		textspace.equals(((CssTextSpace) property).textspace));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-space";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return textspace;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return textspace.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return textspace.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return textspace == normal;
    }

}
