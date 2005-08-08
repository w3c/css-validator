//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.1  2002/07/24 14:42:28  sijtsche
 * ATSC TV profile files
 *
 * Revision 1.1  2002/05/31 09:00:16  dejong
 * ATSC TV profile objects
 *
 * Revision 3.2  1997/09/09 08:52:16  plehegar
 * Added getStyle()
 *
 * Revision 3.1  1997/08/29 13:13:42  plehegar
 * Freeze
 *
 * Revision 1.1  1997/08/20 11:41:20  plehegar
 * Initial revision
 *
 */
package org.w3c.css.atsc;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision$
 */
public class CssBorderTopStyleATSC extends CssProperty {
    
    CssBorderFaceStyleATSC face;
    
    /**
     * Create a new CssBorderTopStyleATSC
     */
    public CssBorderTopStyleATSC() {
	face = new CssBorderFaceStyleATSC();
    }
    
    /**
     * Create a new CssBorderTopStyleATSC with an another CssBorderFaceStyleATSC
     *
     * @param another The another side.
     */
    public CssBorderTopStyleATSC(CssBorderFaceStyleATSC another) {
	setByUser();
	
	face = another;
    }
    
    /**
     * Create a new CssBorderTopStyleATSC
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderTopStyleATSC(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	setByUser();
	face = new CssBorderFaceStyleATSC(ac, expression);
    }
    
    public CssBorderTopStyleATSC(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
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
	return face.getStyle();
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
	return "border-top-style";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderTopATSC top = ((ATSCStyle) style).cssBorderATSC.getTop();
	if (top.getStyle() != null)
	    style.addRedefinitionWarning(ac, this);
	top.style = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ATSCStyle) style).getBorderTopStyleATSC();
	} else {
	    return ((ATSCStyle) style).cssBorderATSC.getTop().style;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderTopStyleATSC && 
		face.equals(((CssBorderTopStyleATSC) property).face));
    }
    
}
