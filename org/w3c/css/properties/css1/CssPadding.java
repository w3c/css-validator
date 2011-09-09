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
 *     &nbsp;&nbsp; 'padding'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> [ &lt;length&gt; | &lt;percentage&gt; ]{1,4} <BR>
 *   <EM>Initial:</EM> 0<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to parent element's width<BR>
 *   <P>
 *   The 'padding' property is a shorthand property for setting 'padding-top',
 *   'padding-right' 'padding-bottom' and 'padding-left' at the same place in
 *   the style sheet.
 *   <P>
 *   If four values are specified they apply to top, right, bottom and left
 *   respectively. If there is only one value, it applies to all sides, if there
 *   are two or three, the missing values are taken from the opposite side.
 *   <P>
 *   The surface of the padding area is set with the 'background' property:
 *   <PRE>
 *   H1 {
 *     background: white;
 *     padding: 1em 2em;
 *   }
 * </PRE>
 *   <P>
 *   The example above sets a '1em' padding vertically ('padding-top' and
 *   'padding-bottom') and a '2em' padding horizontally ('padding-right' and
 *   'padding-left'). The 'em' unit is relative to the element's font size: '1em'
 *   is equal to the size of the font in use.
 *   <P>
 *   Padding values cannot be negative.
 * @version $Revision$
 */
public class CssPadding extends CssProperty implements CssOperator {

    CssPaddingTop    top    = null;
    CssPaddingBottom bottom = null;
    CssPaddingRight  right  = null;
    CssPaddingLeft   left   = null;

    boolean inheritedValue;

    /**
     * Create a new CssPadding
     */
    public CssPadding() {
    }

    /**
     * Create a new CssPadding
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssPadding(ApplContext ac, CssExpression expression, boolean check)
	throws InvalidParamException {
	//CssValue val = expression.getValue();
	setByUser();

	/*if (val.equals(inherit)) {
	    inheritedValue = true;
	    top = new CssPaddingTop();
	    top.value = inherit;
	    bottom = new CssPaddingBottom();
	    bottom.value = inherit;
	    right = new CssPaddingRight();
	    right.value = inherit;
	    left = new CssPaddingLeft();
	    left.value = inherit;
	}*/

	int count = expression.getCount();

	switch (count) {
	case 1:
	    top = new CssPaddingTop(ac, expression);
	    /*bottom = new CssPaddingBottom(top);
	    right = new CssPaddingRight(top);
	    left = new CssPaddingLeft(top);*/
	    break;
	case 2:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssPaddingTop(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssPaddingRight(ac, expression);
	    /*bottom = new CssPaddingBottom(top);
	    left = new CssPaddingLeft(right);*/
	    break;
	case 3:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssPaddingTop(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssPaddingRight(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssPaddingBottom(ac, expression);
	    //left = new CssPaddingLeft(right);
	    break;
	case 4:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssPaddingTop(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssPaddingRight(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssPaddingBottom(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    left = new CssPaddingLeft(ac, expression);
	    break;
	default:
	    if(check) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	}
    }

    public CssPadding(ApplContext ac, CssExpression expression)
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
     * Returns the top property
     */
    public CssPaddingTop getTop() {
	return top;
    }

    /**
     * Returns the right property
     */
    public CssPaddingRight getRight() {
	return right;
    }

    /**
     * Returns the bottom property
     */
    public CssPaddingBottom getBottom() {
	return bottom;
    }

    /**
     * Returns the left property
     */
    public CssPaddingLeft getLeft() {
	return left;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "padding";
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (inheritedValue) {
	    return inherit.toString();
	}
        String result = "";
        // top should never be null
        if(top != null) result += top;
        if(right != null) result += " " + right;
        if(bottom != null) result += " " + bottom;
        if(left != null) result += " " + left;
        return result;
	/*if (right.value.equals(left.value)) {
	    if (top.value.equals(bottom.value)) {
		if (top.value.equals(right.value)) {
		    return top.toString();
		} else {
		    return top + " " + right;
		}
	    } else {
		return top + " " + right + " " + bottom;
	    }
	} else {
	    return top + " " + right + " " + bottom + " " + left;
	}*/
    }

    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */
    public void setImportant() {
	if(top != null) top.important = true;
	if(right != null) right.important = true;
	if(bottom != null) bottom.important = true;
	if(left != null) left.important = true;
    }

    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return ((top == null || top.important) &&
		(right == null || right.important) &&
		(bottom == null || bottom.important) &&
		(left == null || left.important));
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
	((Css1Style) style).cssPadding.inheritedValue = inheritedValue;
	if(top != null) top.addToStyle(ac, style);
	if(right != null) right.addToStyle(ac, style);
	if(bottom != null) bottom.addToStyle(ac, style);
	if(left != null) left.addToStyle(ac, style);
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getPadding();
	} else {
	    return ((Css1Style) style).cssPadding;
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
	if(top != null) top.setInfo(line, source);
	if(right != null) right.setInfo(line, source);
	if(bottom != null) bottom.setInfo(line, source);
	if(left != null) left.setInfo(line, source);
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	// @FIXME
	return false;
    }

}
