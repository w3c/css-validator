//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 2.2  1997/08/20 11:41:17  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:08  plehegar
 * Nothing
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * @version $Revision$
 */
public class CssBorderFaceColorCSS2 {
    
    CssValue face;
    
    /**
     * Create a new CssBorderFaceColor
     */
    public CssBorderFaceColorCSS2() {
	face = new org.w3c.css.values.CssColorCSS2();
    }  
    
    /**
     * Create a new CssBorderFaceColor with a color property.
     *
     * @param color A color property
     */
    public CssBorderFaceColorCSS2(org.w3c.css.properties.CssColorCSS2 color) {
	face = color.color;
    }  
    
    /**
     * Create a new CssBorderFaceColor with an another CssBorderFaceColor
     *
     * @param another An another face.
     */
    public CssBorderFaceColorCSS2(CssBorderFaceColorCSS2 another) {
	face = another.face;
    }  
    
    /**
     * Create a new CssBorderFaceColor with an expression
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException color is not a color
     */
    public CssBorderFaceColorCSS2(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	
	CssValue val = expression.getValue();
	
	if (val instanceof org.w3c.css.values.CssColorCSS2) {
	    face = val;
	} else if (val.equals(CssProperty.inherit)) {
	    face = CssProperty.inherit;
	} else if (val instanceof CssIdent) {
	    face = new org.w3c.css.values.CssColorCSS2(ac, (String) val.get());
	} else {
	    throw new InvalidParamException("value", val.toString(), 
					    "border-color", ac);
	}
	expression.next();
    }
    
    /**
     * Returns the internal color
     */  
    public CssValue getColor() {
	return face;
    }
    
    /**
     * Is the value of this face is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return false; // @@ FIXME face.isDefault();
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return face.toString();
    }
    
    /**
     * Compares two faces for equality.
     *
     * @param value The another faces.
     */  
    public boolean equals(CssBorderFaceColorCSS2 color) {
	return this.face.equals(color.face);
    }
}
