//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
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
public class CssBorderWidth extends CssProperty implements CssOperator {

    CssBorderTopWidth top;
    CssBorderBottomWidth bottom;
    CssBorderRightWidth right;
    CssBorderLeftWidth left;

    /**
     * Create a new CssBorderWidth
     */
    public CssBorderWidth(CssBorderTopWidth top,
			  CssBorderBottomWidth bottom,
			  CssBorderRightWidth right,
			  CssBorderLeftWidth left) {
	this.top = top;
	this.bottom = bottom;
	this.left = left;
	this.right = right;
    }

    /**
     * Create a new CssBorder
     *
     * @param expression The expression for this property
     * @param check true will test the number of values
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderWidth(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {

	setByUser();
	switch (expression.getCount()) {
	case 1:
	    top = new CssBorderTopWidth(ac, expression);
	    bottom = new CssBorderBottomWidth((CssBorderFaceWidth) top.get());
	    right = new CssBorderRightWidth((CssBorderFaceWidth) top.get());
	    left = new CssBorderLeftWidth((CssBorderFaceWidth) top.get());
	    break;
	case 2:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopWidth(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightWidth(ac, expression);
	    bottom = new CssBorderBottomWidth((CssBorderFaceWidth) top.get());
	    left = new CssBorderLeftWidth((CssBorderFaceWidth) right.get());
	    break;
	case 3:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopWidth(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightWidth(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomWidth(ac, expression);
	    left = new CssBorderLeftWidth((CssBorderFaceWidth) right.get());
	    break;
	default:
	    // checks the number of parameters
	    if(check && expression.getCount() > 4) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopWidth(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightWidth(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomWidth(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    left = new CssBorderLeftWidth(ac, expression);
	    break;
	}
    }

    public CssBorderWidth(ApplContext ac, CssExpression expression)
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
	top.important = true;
	right.important = true;
	left.important = true;
	bottom.important = true;
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
	return false; //FIXME
    }

}
