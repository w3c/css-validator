//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 3.2  1997/09/09 08:37:17  plehegar
 * Added getColor()
 *
 * Revision 3.1  1997/08/29 13:13:41  plehegar
 * Freeze
 *
 * Revision 1.1  1997/08/20 11:41:20  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision$
 */
public class CssBorderTopColor extends CssProperty {
    
    CssBorderFaceColor face;
    
    /**
     * Create a new CssBorderTopColor
     */
    public CssBorderTopColor() {
	face = new CssBorderFaceColor();
    }
    
    /**
     * Create a new CssBorderTopColor with an another CssBorderFaceColor
     * @param another The another side.
     */
    public CssBorderTopColor(CssBorderFaceColor another) {
	setByUser();
	
	face = another;
    }
    
    /**
     * Create a new CssBorderTopColor
     *
     * @param expression The expression fir this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderTopColor(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	setByUser();
	face = new CssBorderFaceColor(ac, expression);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return face;
    }
    
    /**
     * Returns the color
     */
    public CssValue getColor() {
	if (face != null) {
	    return face.getColor();
	} else {
	    return null;
	}
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return face.toString();
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "border-top-color";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderTop top = ((Css1Style) style).cssBorder.top;
	if (top.color != null)
	    style.addRedefinitionWarning(ac, this);
	top.color = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBorderTopColor();
	} else {
	    return ((Css1Style) style).cssBorder.getTop().color;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderTopColor && 
		face.equals(((CssBorderTopColor) property).face));
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     *
     * @see #print(CssPrinterStyle)
     */  
    public void print(CssPrinterStyle printer) {
	if (!face.isDefault())
	    printer.print(this);
    }
    
}
