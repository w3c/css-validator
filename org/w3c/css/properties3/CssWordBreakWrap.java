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
 *  <EM>Value:</EM> normal || emergency || inherit<BR>
 *  <EM>Initial:</EM>normal<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property controls the wrapping behavior for words. It allows a 
 *  word to be split arbitrarily at the end of a line if the word cannot 
 *  fit in a single line.
 */

public class CssWordBreakWrap extends CssProperty {

    CssValue wordwrap;

    CssIdent normal = new CssIdent("normal");
    CssIdent emergency = new CssIdent("emergency");

    /** 
     * Create a new CssWordBreakWrap
     */
    public CssWordBreakWrap() {
	wordwrap = normal;
    }

    /**
     * Create a new CssWordBreakWrap
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssWordBreakWrap(ApplContext ac, CssExpression expression) throws InvalidParamException{
	
	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    wordwrap = inherit;
	    expression.next();
	}
	else if (val.equals(normal)) {
	    wordwrap = normal;
	    expression.next();
	}
	else if (val.equals(emergency)) {
	    wordwrap = emergency;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssWordBreakWrap != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssWordBreakWrap = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getWordBreakWrap();
	}
	else {
	    return ((Css3Style) style).cssWordBreakWrap;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssWordBreakWrap &&
		wordwrap.equals(((CssWordBreakWrap) property).wordwrap));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "word-break-wrap";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return wordwrap;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return wordwrap.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return wordwrap.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return wordwrap == normal;
    }

}

