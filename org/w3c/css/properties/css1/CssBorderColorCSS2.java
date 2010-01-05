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
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *      &nbsp;&nbsp; 'border-color'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;color&gt;{1,4}<BR>
 *   <EM>Initial:</EM> the value of the 'color' property<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   The 'border-color' property sets the color of the four borders. 'border-color'
 *   can have from one to four values, and the values are set on the different
 *   sides as for 'border-width' above.
 *   <P>
 *   If no color value is specified, the value of the 'color' property of the
 *   element itself will take its place:
 *   <PRE>
 *   P {
 *     color: black;
 *     background: white;
 *     border: solid;
 *   }
 * </PRE>
 *   <P>
 *   In the above example, the border will be a solid black line.
 *
 * @version $Revision$
 */
public class CssBorderColorCSS2 extends CssProperty implements CssOperator {

    CssBorderTopColorCSS2 top;
    CssBorderBottomColorCSS2 bottom;
    CssBorderRightColorCSS2 right;
    CssBorderLeftColorCSS2 left;

    public CssBorderColorCSS2() {

    }

    /**
     * Create a new CssBorderColorCSS2 with all four sides
     */
    public CssBorderColorCSS2(CssBorderTopColorCSS2 top,
			  CssBorderBottomColorCSS2 bottom,
			  CssBorderRightColorCSS2 right,
			  CssBorderLeftColorCSS2 left) {
	this.top = top;
	this.bottom = bottom;
	this.left = left;
	this.right = right;
    }

    /**
     * Create a new CssBorderCSS2
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderColorCSS2(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();

	CssValue val;

	switch (expression.getCount()) {
	case 1:
	    setTop(new CssBorderTopColorCSS2(ac, expression));
	   /* val = expression.getValue();
	    if (val.equals(transparent)) {
		top = new CssBorderTopColorCSS2();
		top.face.face = transparent;
		expression.next();
	    } else if (val.equals(inherit)) {
		top = new CssBorderTopColorCSS2();
		top.face.face = inherit;
		expression.next();
	    } else{
		top = new CssBorderTopColorCSS2(ac, expression);
//		bottom = new CssBorderBottomColorCSS2((CssBorderFaceColorCSS2) top.get());
//		right = new CssBorderRightColorCSS2((CssBorderFaceColorCSS2) top.get());
//		left = new CssBorderLeftColorCSS2((CssBorderFaceColorCSS2) top.get());
	    }*/
	    break;
	case 2:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    val = expression.getValue();
	    if(val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopColorCSS2(ac, expression);
	    val = expression.getValue();
	    if(val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightColorCSS2(ac, expression);
//	    bottom = new CssBorderBottomColorCSS2((CssBorderFaceColorCSS2) top.get());
//	    left = new CssBorderLeftColorCSS2((CssBorderFaceColorCSS2) right.get());
	    break;
	case 3:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    val = expression.getValue();
	    if(val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopColorCSS2(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()), ac);
	    val = expression.getValue();
	    if(val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightColorCSS2(ac, expression);
	    val = expression.getValue();
	    if(val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomColorCSS2(ac, expression);
//	    left = new CssBorderLeftColorCSS2((CssBorderFaceColorCSS2) right.get());
	    break;
	case 4:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    val = expression.getValue();
	    if(val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopColorCSS2(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    val = expression.getValue();
	    if(val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightColorCSS2(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    val = expression.getValue();
	    if(val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomColorCSS2(ac, expression);
	    val = expression.getValue();
	    if(val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    left = new CssBorderLeftColorCSS2(ac, expression);
	    break;
	default:
	    if(check) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	}
    }

    public CssBorderColorCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * @return Returns the bottom.
     */
    public CssBorderBottomColorCSS2 getBottom() {
        return bottom;
    }

    /**
     * @param bottom The bottom to set.
     */
    public void setBottom(CssBorderBottomColorCSS2 bottom) {
        this.bottom = bottom;
    }

    /**
     * @return Returns the left.
     */
    public CssBorderLeftColorCSS2 getLeft() {
        return left;
    }

    /**
     * @param left The left to set.
     */
    public void setLeft(CssBorderLeftColorCSS2 left) {
        this.left = left;
    }

    /**
     * @return Returns the right.
     */
    public CssBorderRightColorCSS2 getRight() {
        return right;
    }

    /**
     * @param right The right to set.
     */
    public void setRight(CssBorderRightColorCSS2 right) {
        this.right = right;
    }

    /**
     * @return Returns the top.
     */
    public CssBorderTopColorCSS2 getTop() {
        return top;
    }

    /**
     * @param top The top to set.
     */
    public void setTop(CssBorderTopColorCSS2 top) {
        this.top = top;
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return top;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border-color";
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	String ret = "";
	if(top != null) {
	    ret += top;
	}
	if(right != null) {
	    ret += " " + right;
	}
	if(bottom != null) {
	    ret += " " + bottom;
	}
	if(left != null) {
	    ret += " " + left;
	}
	return ret;
	/*
	if (right.face.equals(left.face)) {
	    if (top.face.equals(bottom.face)) {
		if (top.face.equals(right.face)) {
		    return top.toString();
		} else {
		    return top + " " + right;
		}
	    } else {
		return top + " " + right + " " + bottom;
	    }
	} else {
	    return top + " " + right + " " + bottom + " " + left;
	}
	*/
    }

    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */
    public void setImportant() {
	if(top != null) {
	    top.important = true;
	}
	if(right != null) {
	    right.important = true;
	}
	if(left != null) {
	    left.important = true;
	}
	if(bottom != null) {
	    bottom.important = true;
	}
    }

    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return ((top == null || top.important) &&
		(right == null || right.important) &&
		(left == null || left.important) &&
		(bottom == null || bottom.important));
    }

    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */
    public void print(CssPrinterStyle printer) {
	if ((top != null && right != null &&
	     left != null && bottom != null) &&
	    (!top.face.isDefault() && !right.face.isDefault() &&
	     !left.face.isDefault() && !bottom.face.isDefault()) &&
	    (getImportant() ||
	     (!top.important &&
	      !right.important &&
	      !left.important &&
	      !bottom.important))) {
	    printer.print(this);
	} else {
	    if (top != null)
		top.print(printer);
	    if (right != null)
		right.print(printer);
	    if (left != null)
		left.print(printer);
	    if (bottom != null)
		bottom.print(printer);
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
	throw new IllegalStateException("Can't invoke this method on the property " +
					getPropertyName());
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

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return false; // FIXME
    }

}
