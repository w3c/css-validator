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

/**
 *  <P>
 *  <EM>Value:</EM> auto || left || center || right || distribute-letter ||
 *  distribute-space || line-edge || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>all elementes<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property can be used on any element to control the text alignment of 
 *  the ruby text and ruby base contents relative to each other. It applies to
 *  all the ruby's in the element.
 *  For simple ruby, the alignment is applied to the ruby child element whose 
 *  content is shorter: either the rb element or the rt element [RUBY]. For 
 *  group ruby, the alignment is also applied to the ruby child elements 
 *  whose content is shorter: either the rb element and/or one or two rt 
 *  elements for each related ruby text and ruby base element within the
 *  rtc and rbc element. 
*/

public class CssRubyAlign extends CssProperty {

    CssValue rubyalign;
    ApplContext ac;

    CssIdent auto = new CssIdent("auto");
    CssIdent left = new CssIdent("left");
    CssIdent center = new CssIdent("center");
    CssIdent right = new CssIdent("right");
    CssIdent distributeletter = new CssIdent("distribute-letter");
    CssIdent distributespace = new CssIdent("distribute-space");
    CssIdent lineedge = new CssIdent("line-edge");

    /**
     * Create a new CssRubyAlign
     */
    public CssRubyAlign() {
	rubyalign = auto;
    }

    /**
     * Create a new CssRubyAlign
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssRubyAlign(ApplContext ac, CssExpression expression) throws InvalidParamException {
	
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(auto)) {
	    rubyalign = auto;
	    expression.next();
	}
	else if (val.equals(left)) {
	    rubyalign = left;
	    expression.next();
	}
	else if (val.equals(center)) {
	    rubyalign = center;
	    expression.next();
	}
	else if (val.equals(right)) {
	    rubyalign = right;
	    expression.next();
	}
	else if (val.equals(distributeletter)) {
	    rubyalign = distributeletter;
	    expression.next();
	}
	else if (val.equals(distributespace)) {
	    rubyalign = distributespace;
	    expression.next();
	}
	else if (val.equals(lineedge)) {
	    rubyalign = lineedge;
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
	if (((Css3Style) style).cssRubyAlign != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssRubyAlign = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getRubyAlign();
	} else {
	    return ((Css3Style) style).cssRubyAlign;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssRubyAlign && 
                rubyalign.equals( ((CssRubyAlign) property).rubyalign));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "ruby-align";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return rubyalign;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return rubyalign.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return rubyalign.toString();
    }
    
    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {	
	return rubyalign == auto;
    }
    
}	
