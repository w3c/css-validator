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
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class CssBorder extends CssProperty implements CssOperator {

//    CssBorderTopWidth width;
//    CssBorderTopStyle style;
//    CssBorderTopColor color;
//    CssValue uri = null;
//    CssBorderImageTransform imagetransform = null;

    CssBorderTop top = new CssBorderTop();
    CssBorderRight right = new CssBorderRight();
    CssBorderBottom bottom = new CssBorderBottom();
    CssBorderLeft left = new CssBorderLeft();

    /**
     * Create a new CssBorder
     */
    public CssBorder() {
	top = new CssBorderTop();
	right = new CssBorderRight();
	bottom = new CssBorderBottom();
	left = new CssBorderLeft();
    }

    /**
     * Create a new CssBorder
     *
     * @param value The value for this property
     * @exception InvalidParamException The value is incorrect
     */
    public CssBorder(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = expression.getValue();

	if(check && expression.getCount() > 4) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();


	top = new CssBorderTop(ac, expression);

	if (val == expression.getValue()) {
	    throw new InvalidParamException("value",
					    expression.getValue(),
					    getPropertyName(), ac);
	}
	right = new CssBorderRight();
	bottom = new CssBorderBottom();
	left = new CssBorderLeft();

	CssBorderTopWidth w = top.width;
	CssBorderTopStyle s = top.style;
	CssBorderTopColor c = top.color;

	if(w != null) {
	    right.width  =
		new CssBorderRightWidth((CssBorderFaceWidth) w.get());
	    left.width =
		new CssBorderLeftWidth((CssBorderFaceWidth) w.get());
	    bottom.width =
		new CssBorderBottomWidth((CssBorderFaceWidth) w.get());
	}
	if(s != null) {
	    right.style =
		new CssBorderRightStyle((CssBorderFaceStyle) s.get());
	    left.style =
		new CssBorderLeftStyle((CssBorderFaceStyle) s.get());
	    bottom.style =
		new CssBorderBottomStyle((CssBorderFaceStyle) s.get());
	}
	if(c != null) {
	    right.color =
		new CssBorderRightColor((CssBorderFaceColor) c.get());
	    left.color =
		new CssBorderLeftColor((CssBorderFaceColor) c.get());
	    bottom.color =
		new CssBorderBottomColor((CssBorderFaceColor) c.get());
	}
	/*
	CssValue val = expression.getValue();
	char op = SPACE;
	boolean find = true;

	setByUser();

	boolean manyValues = (expression.getCount() > 1);

	while (find) {
	    find = false;
	    val = expression.getValue();
	    op = expression.getOperator();

	    if (val == null)
		break;

	    // if there are many values, we can't have inherit as one of them
	    if(manyValues && val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", null, null, ac);
	    }

	    if (op != SPACE)
		throw new InvalidParamException("operator",
			((new Character(op)).toString()), ac);

	    if (width == null) {
		try {
		    width = new CssBorderTopWidth(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		    // nothing to do, style will test the value
		}
	    }

	    if (!find && style == null) {
		try {
		    style = new CssBorderTopStyle(ac, expression);
		    find = true;
		}
		catch (InvalidParamException e) {
		    // nothing to do, color will test the value
		}
	    }

	    if (!find && color == null) {
		try {
		    color = new CssBorderTopColor(ac, expression);
		    find = true;
		}
		catch (InvalidParamException e) {
		    // nothing to do, uri will test the value
		}
	    }

	    if (!find && uri == null) {
		if (val instanceof CssURL) {
		    uri = val;
		    find = true;
		}
	    }

	    if (!find && imagetransform == null) {
		imagetransform = new CssBorderImageTransform(ac, expression);
		find = true;
	    }
	}*/
	/*
	if (width == null) {
	    width = new CssBorderTopWidth();
	}
	if (style == null) {
	    style = new CssBorderTopStyle();
	}

	if (color == null) {
	    color = new CssBorderTopColor();
	}
	*/
	//


//	top = new CssBorderTop(ac, expression);
/*
	right = new CssBorderRight();
	bottom = new CssBorderBottom();
	left = new CssBorderLeft();*/
/*
	right.width  =  new CssBorderRightWidth((CssBorderFaceWidth) top.width.get());
	left.width   =  new CssBorderLeftWidth((CssBorderFaceWidth) top.width.get());
	bottom.width =  new CssBorderBottomWidth((CssBorderFaceWidth) top.width.get());

	right.style  =  new CssBorderRightStyle((CssBorderFaceStyle) top.style.get());
	left.style   =  new CssBorderLeftStyle((CssBorderFaceStyle) top.style.get());
	bottom.style =  new CssBorderBottomStyle((CssBorderFaceStyle) top.style.get());

	right.color  = 	new CssBorderRightColor((CssBorderFaceColor) top.color.get());
	left.color   =  new CssBorderLeftColor((CssBorderFaceColor) top.color.get());
	bottom.color =  new CssBorderBottomColor((CssBorderFaceColor) top.color.get());
*/
    }

    public CssBorder(ApplContext ac, CssExpression expression)
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
    public CssBorderTop getTop() {
	return top;
    }

    /**
     * Returns the right property
     */
    public CssBorderRight getRight() {
	return right;
    }

    /**
     * Returns the bottom property
     */
    public CssBorderBottom getBottom() {
	return bottom;
    }

    /**
     * Returns the left property
     */
    public CssBorderLeft getLeft() {
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
	    CssBorderWidth width = new CssBorderWidth(top.width, bottom.width,
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
	    CssBorderStyle style = new CssBorderStyle(top.style, bottom.style,
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
	    CssBorderColor color = new CssBorderColor(top.color, bottom.color,
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
	    return ((Css1Style) style).getBorder();
	} else {
	    return ((Css1Style) style).cssBorder;
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
	//super.setInfo(line, source);
	//top.setInfo(line, source);
	//right.setInfo(line, source);
	//left.setInfo(line, source);
	//bottom.setInfo(line, source);
    }

    void check() {
	//top.check();
	//bottom.check();
	//right.check();
	//left.check();
    }
}
