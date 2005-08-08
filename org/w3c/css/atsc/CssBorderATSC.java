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
 * Revision 3.2  1997/09/09 08:34:26  plehegar
 * Added get*
 *
 * Revision 3.1  1997/08/29 13:13:32  plehegar
 * Freeze
 *
 * Revision 2.3  1997/08/26 14:05:15  plehegar
 * Added setSelectors()
 *
 * Revision 2.2  1997/08/20 11:41:15  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:05  plehegar
 * Nothing
 *
 *
 */
package org.w3c.css.atsc;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class CssBorderATSC extends CssProperty {
    
    CssBorderTopATSC top;
    CssBorderRightATSC right;
    CssBorderBottomATSC bottom;
    CssBorderLeftATSC left;
    
    /**
     * Create a new CssBorderFaceATSC
     */
    public CssBorderATSC() {
	top = new CssBorderTopATSC();
	right = new CssBorderRightATSC();
	bottom = new CssBorderBottomATSC();
	left = new CssBorderLeftATSC();
    }  
    
    /**
     * Create a new CssBorderFaceATSC
     *
     * @param value The value for this property
     * @exception InvalidParamException The value is incorrect
     */  
    public CssBorderATSC(ApplContext ac, CssExpression value, boolean check)
    	throws InvalidParamException {
	CssValue val = value.getValue();
	
	setByUser();	
	
	top = new CssBorderTopATSC(ac, value);

	if (val == value.getValue()) {
	    throw new InvalidParamException("value", 
					    value.getValue(), 
					    getPropertyName(), ac);
	}
	
	right = new CssBorderRightATSC();
	bottom = new CssBorderBottomATSC();
	left = new CssBorderLeftATSC();

	right.width  = 
	    new CssBorderRightWidthATSC((CssBorderFaceWidthATSC) top.width.get());
	left.width = 
	    new CssBorderLeftWidthATSC((CssBorderFaceWidthATSC) top.width.get());
	bottom.width = 
	    new CssBorderBottomWidthATSC((CssBorderFaceWidthATSC) top.width.get());
	
	right.style = 
	    new CssBorderRightStyleATSC((CssBorderFaceStyleATSC) top.style.get());
	left.style = 
	    new CssBorderLeftStyleATSC((CssBorderFaceStyleATSC) top.style.get());
	bottom.style = 
	    new CssBorderBottomStyleATSC((CssBorderFaceStyleATSC) top.style.get());
	
	right.color = 
	    new CssBorderRightColorATSC((CssBorderFaceColorATSC) top.color.get());
	left.color = 
	    new CssBorderLeftColorATSC((CssBorderFaceColorATSC) top.color.get());
	bottom.color = 
	    new CssBorderBottomColorATSC((CssBorderFaceColorATSC) top.color.get());	
    }
    
    public CssBorderATSC(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return top.get();
    }
    
    /**
     * Returns the top property
     */
    public CssBorderTopATSC getTop() {
	return top;
    }
    
    /**
     * Returns the right property
     */
    public CssBorderRightATSC getRight() {
	return right;
    }
    
    /**
     * Returns the bottom property
     */
    public CssBorderBottomATSC getBottom() {
	return bottom;
    }
    
    /**
     * Returns the left property
     */
    public CssBorderLeftATSC getLeft() {
	return left;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return top.toString();
    }
    
    public boolean equals(CssProperty property) {
	return false; // FIXME
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "border";
    }
    
    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */  
    public void setImportant() {
	top.setImportant();
	right.setImportant();
	left.setImportant();
	bottom.setImportant();
    }
    
    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return (top.getImportant() &&
		right.getImportant() &&
		left.getImportant() &&
		bottom.getImportant());
    }
    
    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */  
    public void print(CssPrinterStyle printer) {
	int printMacro = 0;

	if ((top.width != null && bottom.width != null &&
	     right.width != null && left.width != null) &&
	    ((top.width.getImportant() && bottom.width.getImportant() && 
	      right.width.getImportant() && left.width.getImportant()) ||
	     (!top.width.getImportant() && !bottom.width.getImportant() && 
	      !right.width.getImportant() && !left.width.getImportant()))) {
	    CssBorderWidthATSC width = new CssBorderWidthATSC(top.width, bottom.width, 
						      right.width, left.width);
	    if (top.getImportant()) {
		width.setImportant();
	    }
	    printMacro = 1;
	    width.print(printer);
	}
	if ((top.style != null && bottom.style != null &&
	     right.style != null && left.style != null) &&
	    ((top.style.getImportant() && bottom.style.getImportant() && 
	      right.style.getImportant() && left.style.getImportant()) ||
	     (!top.style.getImportant() && !bottom.style.getImportant() && 
	      !right.style.getImportant() && !left.style.getImportant()))) {
	    CssBorderStyleATSC style = new CssBorderStyleATSC(top.style, bottom.style, 
						      right.style, left.style);
	    if (top.getImportant()) {
		style.setImportant();
	    }
	    printMacro |= 2;
	    style.print(printer);
	}
	if ((top.color != null && bottom.color != null &&
	     right.color != null && left.color != null) &&
	    ((top.color.getImportant() && bottom.color.getImportant() && 
	      right.color.getImportant() && left.color.getImportant()) ||
	     (!top.color.getImportant() && !bottom.color.getImportant() && 
	      !right.color.getImportant() && !left.color.getImportant()))) {
	    CssBorderColorATSC color = new CssBorderColorATSC(top.color, bottom.color, 
						      right.color, left.color);
	    if (top.getImportant()) {
		color.setImportant();
	    }
	    printMacro |= 4;
	    color.print(printer);
	}
	
	if (printMacro == 0) {
	    top.print(printer);
	    right.print(printer);
	    bottom.print(printer);
	    left.print(printer);
	} else {
	    if ((printMacro & 1) == 0) {
		if (top.width != null) top.width.print(printer);
		if (right.width != null) right.width.print(printer);
		if (bottom.width != null) bottom.width.print(printer);
		if (left.width != null) left.width.print(printer);
	    } 
	    if ((printMacro & 2) == 0) {
		if (top.style != null) top.style.print(printer);
		if (right.style != null) right.style.print(printer);
		if (bottom.style != null) bottom.style.print(printer);
		if (left.style != null) left.style.print(printer);
	    }
	    if ((printMacro & 4) == 0) {
		if (top.color != null) top.color.print(printer);
		if (right.color != null) right.color.print(printer);
		if (bottom.color != null) bottom.color.print(printer);
		if (left.color != null) left.color.print(printer);
	    }
	}
	
    }
    
    /**
     * Set the context.
     * Overrides this method for a macro
     *
     * @see org.w3c.css.css.CssCascadingOrder#order
     * @see org.w3c.css.css.StyleSheetParser#handleRule
     */
    public void setSelectors(CssSelectors selector) {
	super.setSelectors(selector);
	if (top != null) {
	    top.setSelectors(selector);
	}
	if (right != null) {
	    right.setSelectors(selector);
	}
	if (bottom != null) {
	    bottom.setSelectors(selector);
	}
	if (left != null) {
	    left.setSelectors(selector);
	}
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	top.addToStyle(ac, style);
	right.addToStyle(ac, style);
	left.addToStyle(ac, style);
	bottom.addToStyle(ac, style);
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ATSCStyle) style).getBorderATSC();
	} else {
	    return ((ATSCStyle) style).cssBorderATSC;
	}
    }
    
    /**
     * Update the source file and the line.
     * Overrides this method for a macro
     *
     * @param line The line number where this property is defined
     * @param source The source file where this property is defined
     */  
    public void setInfo(int line, String source) {
	super.setInfo(line, source);
	top.setInfo(line, source);
	right.setInfo(line, source);
	left.setInfo(line, source);
	bottom.setInfo(line, source);
    }
    
    void check() {
	top.check();
	bottom.check();
	right.check();
	left.check();
    }
}
