//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> normal || break-all || keep-all || inherit<BR>
 *  <EM>Initial:</EM>normal<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property controls line-breaking behavior inside of words from a CJK 
 *  point of view. 
 */

public class CssWordBreakCJK extends CssProperty {
    
    CssValue wordbreak;

    CssIdent normal = new CssIdent("normal");
    CssIdent breakall = new CssIdent("break-all");
    CssIdent keepall = new CssIdent("keep-all");
    
    /**
     * Create a new CssWordBreakCJK
     */
    public CssWordBreakCJK() {
	wordbreak = normal;
    }
    
    /**
     * Create a new CssWordBreakCJK
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssWordBreakCJK(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(normal)) {
	    wordbreak = normal;
	    expression.next();
	}
	else if (val.equals(keepall)) {
	    wordbreak = keepall;	
    	    expression.next();
	}
	else if (val.equals(breakall)) {
	    wordbreak = breakall;	
    	    expression.next();
	}
	else if (val.equals(inherit)) {
	    wordbreak = inherit;
     	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssWordBreakCJK(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssWordBreakCJK != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssWordBreakCJK = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getWordBreakCJK();
	}
	else {
	    return ((Css3Style) style).cssWordBreakCJK;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssWordBreakCJK &&
		wordbreak.equals(((CssWordBreakCJK) property).wordbreak));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "word-break-CJK";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return wordbreak;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return wordbreak.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return wordbreak.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return wordbreak == normal;
    }

}
	    
