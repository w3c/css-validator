//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class CssBorderCSS1 extends CssProperty {

    CssBorderTopCSS1 top;
    CssBorderRightCSS1 right;
    CssBorderBottomCSS1 bottom;
    CssBorderLeftCSS1 left;

    /**
     * Create a new CssBorderFaceCSS1
     */
    public CssBorderCSS1() {
	top = new CssBorderTopCSS1();
	right = new CssBorderRightCSS1();
	bottom = new CssBorderBottomCSS1();
	left = new CssBorderLeftCSS1();
    }

    /**
     * Create a new CssBorderFaceCSS1
     *
     * @param value The value for this property
     * @exception InvalidParamException The value is incorrect
     */
    public CssBorderCSS1(ApplContext ac, CssExpression value,
	    boolean check) throws InvalidParamException {
	CssValue val = value.getValue();

	// too many values
	if(check && value.getCount() > 3) {
	     throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();


	top = new CssBorderTopCSS1(ac, value);

	if (val == value.getValue()) {
	    throw new InvalidParamException("value",
					    value.getValue(),
					    getPropertyName(), ac);
	}

	right = new CssBorderRightCSS1();
	bottom = new CssBorderBottomCSS1();
	left = new CssBorderLeftCSS1();

	CssBorderTopWidthCSS1 w = top.width;
	CssBorderTopStyleCSS1 s = top.style;
	CssBorderTopColorCSS1 c = top.color;

	if(w != null) {
	    right.width  =
		new CssBorderRightWidthCSS1((CssBorderFaceWidthCSS1) w.get());
	    left.width =
		new CssBorderLeftWidthCSS1((CssBorderFaceWidthCSS1) w.get());
	    bottom.width =
		new CssBorderBottomWidthCSS1((CssBorderFaceWidthCSS1) w.get());
	}
	if(s != null) {
	    right.style =
		new CssBorderRightStyleCSS1((CssBorderFaceStyleCSS1) s.get());
	    left.style =
		new CssBorderLeftStyleCSS1((CssBorderFaceStyleCSS1) s.get());
	    bottom.style =
		new CssBorderBottomStyleCSS1((CssBorderFaceStyleCSS1) s.get());
	}
	if(c != null) {
	    right.color =
		new CssBorderRightColorCSS1((CssBorderFaceColorCSS1) c.get());
	    left.color =
		new CssBorderLeftColorCSS1((CssBorderFaceColorCSS1) c.get());
	    bottom.color =
		new CssBorderBottomColorCSS1((CssBorderFaceColorCSS1) c.get());
	}
    }

    public CssBorderCSS1(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	if(top != null) {
	    return top.get();
	}
	return null;
    }

    /**
     * Returns the top property
     */
    public CssBorderTopCSS1 getTop() {
	return top;
    }

    /**
     * Returns the right property
     */
    public CssBorderRightCSS1 getRight() {
	return right;
    }

    /**
     * Returns the bottom property
     */
    public CssBorderBottomCSS1 getBottom() {
	return bottom;
    }

    /**
     * Returns the left property
     */
    public CssBorderLeftCSS1 getLeft() {
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
	    ((top.width.important && bottom.width.important &&
	      right.width.important && left.width.important) ||
	     (!top.width.important && !bottom.width.important &&
	      !right.width.important && !left.width.important))) {
	    CssBorderWidthCSS1 width = new CssBorderWidthCSS1(top.width, bottom.width,
						      right.width, left.width);
	    if (top.important) {
		width.setImportant();
	    }
	    printMacro = 1;
	    width.print(printer);
	}
	if ((top.style != null && bottom.style != null &&
	     right.style != null && left.style != null) &&
	    ((top.style.important && bottom.style.important &&
	      right.style.important && left.style.important) ||
	     (!top.style.important && !bottom.style.important &&
	      !right.style.important && !left.style.important))) {
	    CssBorderStyleCSS1 style = new CssBorderStyleCSS1(top.style, bottom.style,
						      right.style, left.style);
	    if (top.important) {
		style.setImportant();
	    }
	    printMacro |= 2;
	    style.print(printer);
	}
	if ((top.color != null && bottom.color != null &&
	     right.color != null && left.color != null) &&
	    ((top.color.important && bottom.color.important &&
	      right.color.important && left.color.important) ||
	     (!top.color.important && !bottom.color.important &&
	      !right.color.important && !left.color.important))) {
	    CssBorderColorCSS1 color = new CssBorderColorCSS1(top.color, bottom.color,
						      right.color, left.color);
	    if (top.important) {
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
	    return ((Css1Style) style).getBorderCSS1();
	} else {
	    return ((Css1Style) style).cssBorderCSS1;
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
