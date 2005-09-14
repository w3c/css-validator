//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

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
public class CssPaddingCSS3 extends CssProperty implements CssOperator {

    CssPaddingTopCSS3 top;
    CssPaddingBottomCSS3 bottom;
    CssPaddingRightCSS3 right;
    CssPaddingLeftCSS3 left;

    boolean inheritedValue;

    /**
     * Create a new CssPadding
     */
    public CssPaddingCSS3() {
    }

    /**
     * Create a new CssPadding
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssPaddingCSS3(ApplContext ac, CssExpression expression,
	    boolean check)  throws InvalidParamException {
	CssValue val = expression.getValue();
	setByUser();

	if (val.equals(inherit)) {
	    inheritedValue = true;
	    top = new CssPaddingTopCSS3();
	    top.value = inherit;
	    bottom = new CssPaddingBottomCSS3();
	    bottom.value = inherit;
	    right = new CssPaddingRightCSS3();
	    right.value = inherit;
	    left = new CssPaddingLeftCSS3();
	    left.value = inherit;
	}

	switch (expression.getCount()) {
	case 1:
	    top = new CssPaddingTopCSS3(ac, expression, check);
	    bottom = new CssPaddingBottomCSS3(top);
	    right = new CssPaddingRightCSS3(top);
	    left = new CssPaddingLeftCSS3(top);
	    break;
	case 2:
	    if (expression.getOperator() != SPACE)
		return;
	    top = new CssPaddingTopCSS3(ac, expression, check);
	    right = new CssPaddingRightCSS3(ac, expression, check);
	    bottom = new CssPaddingBottomCSS3(top);
	    left = new CssPaddingLeftCSS3(right);
	    break;
	case 3:
	    if (expression.getOperator() != SPACE)
		return;
	    top = new CssPaddingTopCSS3(ac, expression, check);
	    if (expression.getOperator() != SPACE)
		return;
	    right = new CssPaddingRightCSS3(ac, expression, check);
	    bottom = new CssPaddingBottomCSS3(ac, expression, check);
	    left = new CssPaddingLeftCSS3(right);
	    break;
	case 4:
	    if (expression.getOperator() != SPACE)
		return;
	    top = new CssPaddingTopCSS3(ac, expression, check);
	    if (expression.getOperator() != SPACE)
		return;
	    right = new CssPaddingRightCSS3(ac, expression, check);
	    if (expression.getOperator() != SPACE)
		return;
	    bottom = new CssPaddingBottomCSS3(ac, expression, check);
	    left = new CssPaddingLeftCSS3(ac, expression, check);
	    break;
	default:
	}
    }

    public CssPaddingCSS3(ApplContext ac, CssExpression expression)
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
    public CssPaddingTopCSS3 getTop() {
	return top;
    }

    /**
     * Returns the right property
     */
    public CssPaddingRightCSS3 getRight() {
	return right;
    }

    /**
     * Returns the bottom property
     */
    public CssPaddingBottomCSS3 getBottom() {
	return bottom;
    }

    /**
     * Returns the left property
     */
    public CssPaddingLeftCSS3 getLeft() {
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
	if (right.value.equals(left.value)) {
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
	bottom.addToStyle(ac, style);
	left.addToStyle(ac, style);
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getPadding();
	} else {
	    return ((Css3Style) style).cssPaddingCSS3;
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
	bottom.setInfo(line, source);
	left.setInfo(line, source);
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
