//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css1;

import java.util.HashSet;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;

/**
 *   <H4>
 *      &nbsp;&nbsp; 'border-style'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> none | dotted | dashed | solid | double | groove | ridge
 *   | inset | outset<BR>
 *   <EM>Initial:</EM> none<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   The 'border-style' property sets the style of the four borders. It can have
 *   from one to four values, and the values are set on the different sides as
 *   for 'border-width' above.
 *   <PRE>
 *   #xy34 { border-style: solid dotted }
 * </PRE>
 *   <P>
 *   In the above example, the horizontal borders will be 'solid' and the vertical
 *   borders will be 'dotted'.
 *   <P>
 *   Since the initial value of the border styles is 'none', no borders will be
 *   visible unless the border style is set.
 *   <P>
 *   The border styles mean:
 *   <DL>
 *     <DT>
 *       none
 *     <DD>
 *       no border is drawn (regardless of the 'border-width' value)
 *     <DT>
 *       dotted
 *     <DD>
 *       the border is a dotted line drawn on top of the background of the element
 *     <DT>
 *       dashed
 *     <DD>
 *       the border is a dashed line drawn on top of the background of the element
 *     <DT>
 *       solid
 *     <DD>
 *       the border is a solid line
 *     <DT>
 *       double
 *     <DD> the border is a double line drawn on top of the background of the
 *     element.  The sum of the two single lines and the space between equals
 *     the &lt;border-width&gt; value.
 *     <DT>
 *       groove
 *     <DD>
 *       a 3D groove is drawn in colors based on the &lt;color&gt; value.
 *     <DT>
 *       ridge
 *     <DD>
 *       a 3D ridge is drawn in colors based on the &lt;color&gt; value.
 *     <DT>
 *       inset
 *     <DD>
 *       a 3D inset is drawn in colors based on the &lt;color&gt; value.
 *     <DT>
 *       outset
 *     <DD>
 *       a 3D outset is drawn in colors based on the &lt;color&gt; value.
 *   </DL>
 *   <P>
 *   <EM>CSS1 core:</EM> UAs may interpret all of 'dotted', 'dashed', 'double',
 *   'groove', 'ridge', 'inset' and 'outset' as 'solid'.
 *
 * @version $Revision$
 */
public class CssBorderStyle extends CssProperty implements CssOperator {

    public static HashSet<CssIdent> acceptable_values;
    static {
	acceptable_values = new HashSet<CssIdent>();
	acceptable_values.add(CssIdent.getIdent("none"));
	acceptable_values.add(CssIdent.getIdent("hidden"));
	acceptable_values.add(CssIdent.getIdent("dotted"));
	acceptable_values.add(CssIdent.getIdent("dashed"));
	acceptable_values.add(CssIdent.getIdent("solid"));
	acceptable_values.add(CssIdent.getIdent("double"));
	acceptable_values.add(CssIdent.getIdent("dot-dash"));
	acceptable_values.add(CssIdent.getIdent("dot-dot-dash"));
	acceptable_values.add(CssIdent.getIdent("wave"));
	acceptable_values.add(CssIdent.getIdent("groove"));
	acceptable_values.add(CssIdent.getIdent("ridge"));
	acceptable_values.add(CssIdent.getIdent("inset"));
	acceptable_values.add(CssIdent.getIdent("outset"));
    }
    CssBorderTopStyle top;
    CssBorderBottomStyle bottom;
    CssBorderRightStyle right;
    CssBorderLeftStyle left;

    /**
     * Create a new CssBorderStyle
     */
    public CssBorderStyle(CssBorderTopStyle top,
	    CssBorderBottomStyle bottom,
	    CssBorderRightStyle right,
	    CssBorderLeftStyle left) {
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
    public CssBorderStyle(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();

	switch (expression.getCount()) {
	case 1:
	    top = new CssBorderTopStyle(ac, expression);
	    /*bottom = new CssBorderBottomStyle((CssBorderFaceStyle) top.get());
	    right = new CssBorderRightStyle((CssBorderFaceStyle) top.get());
	    left = new CssBorderLeftStyle((CssBorderFaceStyle) top.get());*/
	    break;
	case 2:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopStyle(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightStyle(ac, expression);
	    /*bottom = new CssBorderBottomStyle((CssBorderFaceStyle) top.get());
	    left = new CssBorderLeftStyle((CssBorderFaceStyle) right.get());*/
	    break;
	case 3:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopStyle(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightStyle(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomStyle(ac, expression);
	    //left = new CssBorderLeftStyle((CssBorderFaceStyle) right.get());
	    break;
	case 4:
	    if (expression.getOperator() != SPACE) {
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    }
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopStyle(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
		    ((new Character(expression.getOperator())).toString()),
		    ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightStyle(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
		    ((new Character(expression.getOperator())).toString()),
		    ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomStyle(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    left = new CssBorderLeftStyle(ac, expression);
	    break;
	    default:
		if(check) {
		    throw new InvalidParamException("unrecognize", ac);
		}
	    break;
	}
    }

    public CssBorderStyle(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression,false);
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
	return "border-style";
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
	    top.addToStyle(ac , style);
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
