//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:17:43  plehegar
 * New
 *
 * Revision 3.2  1997/09/09 10:54:11  plehegar
 * Added getColor, getStyle and getWidth
 *
 * Revision 3.1  1997/08/29 13:13:38  plehegar
 * Freeze
 *
 * Revision 2.3  1997/08/26 14:05:46  plehegar
 * Added setSelectors()
 *
 * Revision 2.2  1997/08/20 11:41:18  plehegar
 * Freeze
 *
 * Revision 1.2  1997/08/06 17:29:55  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.1  1997/07/28 21:37:28  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssURL;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'border-right'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;border-right-width&gt; || &lt;border-style&gt; ||
 *   &lt;color&gt;<BR>
 *   <EM>Initial:</EM> not defined for shorthand properties<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This is a shorthand property for setting the width, style and color of an
 *   element's right border.
 *   <PRE>
 *   H1 { border-bottom: thick solid red }
 * </PRE>
 *   <P>
 *   The above rule will set the width, style and color of the border below the
 *   H1 element. Omitted values will be set to their initial values:
 *   <PRE>
 *   H1 { border-bottom: thick solid }
 * </PRE>
 *   <P>
 *   Since the color value is omitted in the example above, the border color will
 *   be the same as the 'color' value of the element itself.
 *   <P>
 *   Note that while the 'border-style' property accepts up to four values, this
 *   property only accepts one style value.
 *
 * @version $Revision$
 */
public class CssBorderRight extends CssProperty implements CssOperator {

    CssBorderRightWidth width;
    CssBorderRightStyle style;
    CssBorderRightColor color;
    CssValue uri = null;

    /**
     * Create a new CssBorderFace
     */
    public CssBorderRight() {
    }

    /**
     * Create a new CssBorderFace
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssBorderRight(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = null;
	char op = SPACE;
	boolean find = true;

	setByUser();
	while (find) {
	    find = false;
	    val = expression.getValue();
	    op = expression.getOperator();

	    if (val == null)
		break;

	    if (op != SPACE)
		throw new InvalidParamException("operator",
						((new Character(op)).toString()),
						ac);

	    if (width == null) {
			try {
			    width = new CssBorderRightWidth(ac, expression);
			    find = true;
			} catch (InvalidParamException e) {}
	    }

	    if (!find && style == null) {
			try {
			    style = new CssBorderRightStyle(ac, expression);
			    find = true;
			}
			catch (InvalidParamException e) {}
	    }

	    if (!find && color == null) {
			try {
			    color = new CssBorderRightColor(ac, expression);
			    find = true;
			}
			catch (InvalidParamException e) {}
	    }

	    if (!find && uri == null) {
			if (val instanceof CssURL) {
				uri = val;
				find = true;
			}
		}
	}

	if (width == null)
	    width = new CssBorderRightWidth();
	if (style == null)
	    style = new CssBorderRightStyle();
	if (color == null)
	    color = new CssBorderRightColor();
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return width;
    }

    /**
     * Returns the color property
     */
    public CssValue getColor() {
	if (color != null) {
	    return color.getColor();
	} else {
	    return null;
	}
    }

    /**
     * Returns the width property
     */
    public CssValue getWidth() {
	if (width != null) {
	    return width.getValue();
	} else {
	    return null;
	}
    }

    /**
     * Returns the style property
     */
    public String getStyle() {
	if (style != null) {
	    return style.getStyle();
	} else {
	    return null;
	}
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	String ret = width + " " + style;
	if (!color.face.isDefault())
	    ret += " " + color;
	if (uri != null) {
		ret += " " + uri.toString();
	}
	return ret;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border-right";
    }

    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */
    public void setImportant() {
	width.important = true;
	style.important = true;
	color.important = true;
    }

    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return ((width == null || width.important) &&
		(style == null || style.important) &&
		(color == null || color.important));
    }

    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */
    public void print(CssPrinterStyle printer) {
	if ((width != null && style != null &&
	     color != null) &&
	    (getImportant() ||
	     (!width.important &&
	      !style.important &&
	      !color.important))) {
	    printer.print(this);
	} else {
	    if (width != null)
		width.print(printer);
	    if (style != null)
		style.print(printer);
	    if (color != null)
		color.print(printer);
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
	if (width != null) {
	    width.setSelectors(selector);
	}
	if (style != null) {
	    style.setSelectors(selector);
	}
	if (color != null) {
	    color.setSelectors(selector);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	width.addToStyle(ac, style);
	this.style.addToStyle(ac, style);
	color.addToStyle(ac, style);
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBorderRight();
	} else {
	    return ((Css1Style) style).cssBorder.getRight();
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
	width.setInfo(line, source);
	style.setInfo(line, source);
	color.setInfo(line, source);
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	if (property instanceof CssBorderRight) {
	    CssBorderRight right = (CssBorderRight) property;
	    return (width.equals(right.width) &&
		    style.equals(right.style) && color.equals(right.color));
	} else {
	    return false;
	}
    }

    void check() {
	if ((style != null)
	    && (style.face.value == 0)) {
	    if (width != null) {
		width.face.value = new CssLength();
	    }
	}
    }
}
