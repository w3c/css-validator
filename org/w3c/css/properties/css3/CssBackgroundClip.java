//
// $Id$
// From Sijtsche Smeman (sijtsche@wisdom.nl)
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
 *  <EM>Value:</EM> border || padding<BR>
 *  <EM>Initial:</EM>border<BR>
 *  <EM>Applies to:</EM>block-level and replaced elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  Determines whether the background extents into the border area or not. If the value is
 *  'padding', the background is clipped to the padding edge and the background of the border
 *  is transparent.
 */

public class CssBackgroundClip extends CssProperty {
    
    CssValue bgclip;
    
    CssIdent border = new CssIdent("border");
    CssIdent padding = new CssIdent("padding");
    
    /**
     * Create a new CssBackgroundClip
     */
    public CssBackgroundClip() {
	bgclip = border;
    }
    
    /**
     * Create a new CssBackgroundClip
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssBackgroundClip(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	setByUser();
	CssValue val = expression.getValue();
	
	if (val.equals(border)) {
	    bgclip = border;
	    expression.next();
	} else if (val.equals(padding)) {
	    bgclip = padding;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
		    getPropertyName(), ac);
	}
    }
    
    public CssBackgroundClip(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssBackgroundClip != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBackgroundClip = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getCssBackgroundClip();
	}
	else {
	    return ((Css3Style) style).cssBackgroundClip;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBackgroundClip &&
		bgclip.equals(((CssBackgroundClip) property).bgclip));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "background-clip";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return bgclip;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return bgclip.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return bgclip.toString();
    }
    
    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return bgclip == border;
    }
    
}
