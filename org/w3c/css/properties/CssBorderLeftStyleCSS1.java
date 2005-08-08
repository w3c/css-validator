//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:17:43  plehegar
 * New
 *
 * Revision 3.2  1997/09/09 11:01:31  plehegar
 * Added getStyle()
 *
 * Revision 3.1  1997/08/29 13:13:37  plehegar
 * Freeze
 *
 * Revision 1.1  1997/08/20 11:41:18  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision$
 */
public class CssBorderLeftStyleCSS1 extends CssProperty {
    
    CssBorderFaceStyleCSS1 face;
    
    /**
     * Create a new CssBorderLeftStyleCSS1
     */
    public CssBorderLeftStyleCSS1() {
	face = new CssBorderFaceStyleCSS1();
    }
    
    /**
     * Create a new CssBorderLeftStyleCSS1 with an another CssBorderFaceStyleCSS1
     *
     * @param another The another side.
     */
    public CssBorderLeftStyleCSS1(CssBorderFaceStyleCSS1 another) {
	setByUser();
	
	face = another;
    }
    
    /**
     * Create a new CssBorderLeftStyleCSS1
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderLeftStyleCSS1(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	setByUser();
	face = new CssBorderFaceStyleCSS1(ac, expression);
    }
    
    public CssBorderLeftStyleCSS1(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression,false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return face;
    }
    
    /**
     * Returns the value
     */
    public String getStyle() {
	if(face != null) {
	    return face.getStyle();
	}
	return null;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if(face != null) {
	    return face.toString();
	}
	return "";
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "border-left-style";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderLeftCSS1 left = ((Css1Style) style).cssBorderCSS1.left;
	if (left.style != null)
	    style.addRedefinitionWarning(ac, this);
	left.style = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBorderLeftStyleCSS1();
	} else {
	    return ((Css1Style) style).cssBorderCSS1.getLeft().style;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderLeftStyleCSS1 && face.equals(((CssBorderLeftStyleCSS1) property).face));
    }
    
}
