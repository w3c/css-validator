//
// $Id$
// From Sijtsche Smeman (sijtsche@wisdom.nl)
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
 *  <EM>Value:</EM> border || padding || content<BR>
 *  <EM>Initial:</EM>border<BR>
 *  <EM>Applies to:</EM>block-level and replaced elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  Determines whether the background extents into the border area or not. If the value is
 *  'padding', the background is clipped to the padding edge and the background of the border
 *  is transparent.
 */

public class CssBackgroundOrigin extends CssProperty {

    CssValue bgorigin;
    
    CssIdent border = new CssIdent("border");
    CssIdent padding = new CssIdent("padding");
    CssIdent content = new CssIdent("content");
    
    /**
     * Create a new CssBackgroundOrigin
     */
    public CssBackgroundOrigin() {
	bgorigin = padding;
    }
    
    /**
     * Create a new CssBackgroundOrigin
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssBackgroundOrigin(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	setByUser();
	CssValue val = expression.getValue();
	
	if (val.equals(border)) {
	    bgorigin = border;
	    expression.next();
	} else if (val.equals(padding)) {
	    bgorigin = padding;
	    expression.next();
	} else if (val.equals(content)) {
	    bgorigin = content;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
		    getPropertyName(), ac);
	}
    }
    
    public CssBackgroundOrigin(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }    
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssBackgroundOrigin != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBackgroundOrigin = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getCssBackgroundOrigin();
	}
	else {
	    return ((Css3Style) style).cssBackgroundOrigin;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBackgroundOrigin &&
		bgorigin.equals(((CssBackgroundOrigin) property).bgorigin));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "background-origin";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return bgorigin;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return bgorigin.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return bgorigin.toString();
    }
    
    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return bgorigin == padding;
    }
    
}
