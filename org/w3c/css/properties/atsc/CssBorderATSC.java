//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.atsc;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
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

	if(check && value.getCount() > 3) {
	     throw new InvalidParamException("unrecognize", ac);
	}

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

	CssBorderTopWidthATSC w = top.width;
	CssBorderTopStyleATSC s = top.style;
	CssBorderTopColorATSC c = top.color;

	if(w != null) {
	    right.width  =
		new CssBorderRightWidthATSC((CssBorderFaceWidthATSC) w.get());
	    left.width =
		new CssBorderLeftWidthATSC((CssBorderFaceWidthATSC) w.get());
	    bottom.width =
		new CssBorderBottomWidthATSC((CssBorderFaceWidthATSC) w.get());
	}
	if(s != null) {
	    right.style =
		new CssBorderRightStyleATSC((CssBorderFaceStyleATSC) s.get());
	    left.style =
		new CssBorderLeftStyleATSC((CssBorderFaceStyleATSC) s.get());
	    bottom.style =
		new CssBorderBottomStyleATSC((CssBorderFaceStyleATSC) s.get());
	}
	if(c != null) {
	    right.color =
		new CssBorderRightColorATSC((CssBorderFaceColorATSC) c.get());
	    left.color =
		new CssBorderLeftColorATSC((CssBorderFaceColorATSC) c.get());
	    bottom.color =
		new CssBorderBottomColorATSC((CssBorderFaceColorATSC) c.get());
	}
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
	if(top != null) {
	    return top.toString();
	}
	return "";
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
	if(top != null) {
	    top.setImportant();
	}
	if(right != null) {
	    right.setImportant();
	}
	if(left != null) {
	    left.setImportant();
	}
	if(bottom != null) {
	    bottom.setImportant();
	}
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
	if(top != null) {
	    top.addToStyle(ac, style);
	}
	if(right != null) {
	    right.addToStyle(ac, style);
	}
	if(left != null) {
	    left.addToStyle(ac, style);
	}
	if(bottom != null) {
	    bottom.addToStyle(ac, style);
	}
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
	if(top != null) {
	    top.setInfo(line, source);
	}
	if(right != null) {
	    right.setInfo(line, source);
	}
	if(left != null) {
	    left.setInfo(line, source);
	}
	if(bottom != null) {
	    bottom.setInfo(line, source);
	}
    }

    void check() {
	if(top != null) {
	    top.check();
	}
	if(bottom != null) {
	    bottom.check();
	}
	if(right != null) {
	    right.check();
	}
	if(left != null) {
	    left.check();
	}
    }
}
