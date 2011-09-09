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
public class CssBorderColor extends CssProperty implements CssOperator {

    CssBorderTopColor top;
    CssBorderBottomColor bottom;
    CssBorderRightColor right;
    CssBorderLeftColor left;

    //private static CssIdent transparent = new CssIdent("transparent"); // obsolete, not in CSS3 anymore

    /**
     * Create a new CssBorderColor with all four sides
     */
    public CssBorderColor(CssBorderTopColor top,
			  CssBorderBottomColor bottom,
			  CssBorderRightColor right,
			  CssBorderLeftColor left) {
	this.top = top;
	this.bottom = bottom;
	this.left = left;
	this.right = right;
    }

    /**
     * Create a new CssBorder
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderColor(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();

	switch (expression.getCount()) {
	case 1:
	    //CssValue val = expression.getValue();
	    //if (val.equals(transparent)) { // obsolete, transparent is a color value now
		//top = new CssBorderTopColor();
		//top.face.face = transparent;
		//expression.next();
	    //} else
	    /*if (val.equals(inherit)) {
		top = new CssBorderTopColor();
		top.face.face = inherit;
		expression.next();
	    } else*/
	    top = new CssBorderTopColor(ac, expression);
	    /*bottom = new CssBorderBottomColor((CssBorderFaceColor) top.get());
	    right = new CssBorderRightColor((CssBorderFaceColor) top.get());
	    left = new CssBorderLeftColor((CssBorderFaceColor) top.get());*/
	    break;
	case 2:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopColor(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightColor(ac, expression);
	    /*bottom = new CssBorderBottomColor((CssBorderFaceColor) top.get());
	    left = new CssBorderLeftColor((CssBorderFaceColor) right.get());*/
	    break;
	case 3:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopColor(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()), ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightColor(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomColor(ac, expression);
	    //left = new CssBorderLeftColor((CssBorderFaceColor) right.get());
	    break;
	case 4:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopColor(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightColor(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomColor(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    left = new CssBorderLeftColor(ac, expression);
	    break;
	default:
	    if(check) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    break;
	}
    }

    public CssBorderColor(ApplContext ac, CssExpression expression)
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
	return "border-color";
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        String result = "";
        // top should never be null
        if(top != null) result += top;
        if(right != null) result += " " + right;
        if(bottom != null) result += " " + bottom;
        if(left != null) result += " " + left;
        return result;
	/*if (right.face.equals(left.face)) {
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
	}*/

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
