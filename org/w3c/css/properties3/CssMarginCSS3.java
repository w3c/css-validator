//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:13:53  plehegar
 * Freeze
 *
 * Revision 2.3  1997/08/26 13:56:28  plehegar
 * Added setSelectors()
 *
 * Revision 2.2  1997/08/20 11:41:25  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:25  plehegar
 * Nothing
 *
 * Revision 1.4  1997/08/06 17:30:10  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.3  1997/07/30 13:20:09  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/24 01:36:58  plehegar
 * bug fix
 *
 * Revision 1.1  1997/07/24 01:07:08  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties.CssProperty;

/**
 * @version $Revision$
 */
public class CssMarginCSS3 extends CssProperty implements CssOperator {

    CssMarginTopCSS3 top;
    CssMarginBottomCSS3 bottom;
    CssMarginRightCSS3 right;
    CssMarginLeftCSS3 left;

    boolean inheritedValue;

    /**
     * Create a new CssMargin
     */
    public CssMarginCSS3() {
    }

    /**
     * Create a new CssMargin
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssMarginCSS3(ApplContext ac, CssExpression expression)  throws InvalidParamException {
	CssValue val = expression.getValue();
	setByUser();

	if (val.equals(inherit)) {
	    inheritedValue = true;
	    top = new CssMarginTopCSS3();
	    top.value = inherit;
	    bottom = new CssMarginBottomCSS3();
	    bottom.value = inherit;
	    right = new CssMarginRightCSS3();
	    right.value = inherit;
	    left = new CssMarginLeftCSS3();
	    left.value = inherit;
	}

	switch (expression.getCount()) {
	case 1:
	    top = new CssMarginTopCSS3(ac, expression);
	    bottom = new CssMarginBottomCSS3(top);
	    right = new CssMarginRightCSS3(top);
	    left = new CssMarginLeftCSS3(top);
	    break;
	case 2:
	    if (expression.getOperator() != SPACE)
		return;
	    top = new CssMarginTopCSS3(ac, expression);
	    right = new CssMarginRightCSS3(ac, expression);
	    bottom = new CssMarginBottomCSS3(top);
	    left = new CssMarginLeftCSS3(right);
	    break;
	case 3:
	    if (expression.getOperator() != SPACE)
		return;
	    top = new CssMarginTopCSS3(ac, expression);
	    if (expression.getOperator() != SPACE)
		return;
	    right = new CssMarginRightCSS3(ac, expression);
	    bottom = new CssMarginBottomCSS3(ac, expression);
	    left = new CssMarginLeftCSS3(right);
	    break;
	case 4:
	    if (expression.getOperator() != SPACE)
		return;
	    top = new CssMarginTopCSS3(ac, expression);
	    if (expression.getOperator() != SPACE)
		return;
	    right = new CssMarginRightCSS3(ac, expression);
	    if (expression.getOperator() != SPACE)
		return;
	    bottom = new CssMarginBottomCSS3(ac, expression);
	    left = new CssMarginLeftCSS3(ac, expression);
	    break;
	default:
	}
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
    public CssMarginTopCSS3 getTop() {
	return top;
    }

    /**
     * Returns the right property
     */
    public CssMarginRightCSS3 getRight() {
	return right;
    }

    /**
     * Returns the bottom property
     */
    public CssMarginBottomCSS3 getBottom() {
	return bottom;
    }

    /**
     * Returns the left property
     */
    public CssMarginLeftCSS3 getLeft() {
	return left;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "margin";
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
		    return ((Css3Style) style).getMarginCSS3();
		} else {
		    return ((Css3Style) style).cssMarginCSS3;
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
	return false;
    }

}
