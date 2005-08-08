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
 *  <EM>Value:</EM> before || after || right || inline || inherit<BR>
 *  <EM>Initial:</EM>before<BR>
 *  <EM>Applies to:</EM>the parent of elements with display: ruby-text<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property is used by the parent of elements with display: ruby-text to
 *  control the position of the ruby text with respect to its base. Such 
 *  parents are typically either the ruby element itself (simple ruby) or 
 *  the rtc element (group ruby).
 */

public class CssRubyPosition extends CssProperty {

    CssValue pos;
    ApplContext ac;

    CssIdent before = new CssIdent("before");
    CssIdent after = new CssIdent("after");
    CssIdent right = new CssIdent("right");
    CssIdent inline = new CssIdent("inline");

    /**
     * Create a new CssRubyPosition
     */
    public CssRubyPosition() {
	pos = before;
    }

    /**
     * Create a new CssRubyPosition
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssRubyPosition(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(before)) {
	    pos = before;
	    expression.next();
	}
	else if (val.equals(after)) {
	    pos = after;
	    expression.next();
	}
	else if (val.equals(right)) {
	    pos = right;
	    expression.next();
	}
	else if (val.equals(inline)) {
	    pos = inline;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    pos = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }
    
    public CssRubyPosition(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
	
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssRubyPosition != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssRubyPosition = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getRubyPosition();
	} else {
	    return ((Css3Style) style).cssRubyPosition;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssRubyPosition && 
                pos.equals( ((CssRubyPosition) property).pos));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "ruby-position";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return pos;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return pos.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return pos.toString();
    }
    
    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {	
	return pos == before;
    }
    
}	
