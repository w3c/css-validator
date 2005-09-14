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

/**
 *   <H4>
 *     &nbsp;&nbsp; 'border-width'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> [thin | medium | thick | &lt;length&gt;]{1,4}<BR>
 *   <EM>Initial:</EM> not defined for shorthand properties<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property is a shorthand property for setting 'border-width-top',
 *   'border-width-right', 'border-width-bottom' and 'border-width-left' at the
 *   same place in the style sheet.
 *   <P>
 *   There can be from one to four values, with the following interpretation:
 *   <UL>
 *     <LI>
 *       one value: all four border widths are set to that value
 *     <LI>
 *       two values: top and bottom border widths are set to the first value, right
 *       and left are set to the second
 *     <LI>
 *       three values: top is set to the first, right and left are set to the second,
 *       bottom is set to the third
 *     <LI>
 *       four values: top, right, bottom and left, respectively
 *   </UL>
 *   <P>
 *   In the examples below, the comments indicate the resulting widths of the
 *   top, right, bottom and left borders:
 *   <PRE>
 *   H1 { border-width: thin }                   / * thin thin thin thin * /
 *   H1 { border-width: thin thick }             / * thin thick thin thick * /
 *   H1 { border-width: thin thick medium }      / * thin thick medium thin * /
 *   H1 { border-width: thin thick medium 12cm } / * thin thick medium 12cm * /
 * </PRE>
 *   <P>
 *   Border widths cannot be negative.
 * @version $Revision$
 */
public class CssBorderWidthCSS1 extends CssProperty implements CssOperator {

    CssBorderTopWidthCSS1 top;
    CssBorderBottomWidthCSS1 bottom;
    CssBorderRightWidthCSS1 right;
    CssBorderLeftWidthCSS1 left;

    /**
     * Create a new CssBorderWidthCSS1
     */
    public CssBorderWidthCSS1(CssBorderTopWidthCSS1 top,
			  CssBorderBottomWidthCSS1 bottom,
			  CssBorderRightWidthCSS1 right,
			  CssBorderLeftWidthCSS1 left) {
	this.top = top;
	this.bottom = bottom;
	this.left = left;
	this.right = right;
    }

    /**
     * Create a new CssBorderCSS1
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderWidthCSS1(ApplContext ac, CssExpression expression,
	    boolean check)  throws InvalidParamException {

	setByUser();
	switch (expression.getCount()) {
	case 1:
	    top = new CssBorderTopWidthCSS1(ac, expression);
	    bottom = new CssBorderBottomWidthCSS1((CssBorderFaceWidthCSS1) top.get());
	    right = new CssBorderRightWidthCSS1((CssBorderFaceWidthCSS1) top.get());
	    left = new CssBorderLeftWidthCSS1((CssBorderFaceWidthCSS1) top.get());
	    break;
	case 2:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopWidthCSS1(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightWidthCSS1(ac, expression);
	    bottom = new CssBorderBottomWidthCSS1((CssBorderFaceWidthCSS1) top.get());
	    left = new CssBorderLeftWidthCSS1((CssBorderFaceWidthCSS1) right.get());
	    break;
	case 3:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopWidthCSS1(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightWidthCSS1(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomWidthCSS1(ac, expression);
	    left = new CssBorderLeftWidthCSS1((CssBorderFaceWidthCSS1) right.get());
	    break;
	case 4:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopWidthCSS1(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightWidthCSS1(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomWidthCSS1(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    left = new CssBorderLeftWidthCSS1(ac, expression);
	    break;
	default:
	    if(check) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	}
    }

    CssBorderWidthCSS1(ApplContext ac, CssExpression expression)
    	throws InvalidParamException {
	this(ac, expression, false);
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
	return "border-width";
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
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
