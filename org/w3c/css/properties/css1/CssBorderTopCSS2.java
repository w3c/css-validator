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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'border-top'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;border-top-width&gt; || &lt;border-style&gt; ||
 *   &lt;color&gt;<BR>
 *   <EM>Initial:</EM> not defined for shorthand properties<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This is a shorthand property for setting the width, style and color of an
 *   element's top border.
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
public class CssBorderTopCSS2 extends CssProperty implements CssOperator {

    CssBorderTopWidthCSS2 width = null;
    CssBorderTopStyleCSS2 style = null;
    CssBorderTopColorCSS2 color = null;
    String output = null;
    
    /**
     * Create a new CssBorderFaceCSS2
     */
    public CssBorderTopCSS2() {
    }

    /**
     * Create a new CssBorderFaceCSS2
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssBorderTopCSS2(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	CssValue val = null;
	char op = SPACE;
	boolean find = true;

	if(check && expression.getCount() > 3) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	boolean manyValues = (expression.getCount() > 1);

	setByUser();

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
						((new Character(op)).toString()),
						ac);
	    if (width == null) {
		try {
		    width = new CssBorderTopWidthCSS2(ac, expression);
		    find = true;
		} catch(InvalidParamException e){
		    //nothing to do, style will test this value
		}
	    }
	    if (!find && style == null) {
		try {
		    style = new CssBorderTopStyleCSS2(ac, expression);
		    find = true;
		} catch(InvalidParamException e){
		    //nothing to do, color will test this value
		}
	    }
	    if (!find && color == null) {
		// throws an exception if the value is not valid
		color = new CssBorderTopColorCSS2(ac, expression);
		find = true;
	    }
	}

	//if some values have not been set, we set them with their default ones
	/*
	if (width == null) {
	    width = new CssBorderTopWidthCSS2();
	}
	if (style == null) {
	    style = new CssBorderTopStyleCSS2();
	}
	if (color == null) {
	    color = new CssBorderTopColorCSS2();
	}*/
    }

    public CssBorderTopCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression,false);
    }

    /**
     * @return Returns the color.
     */
    public CssBorderTopColorCSS2 getColor2() {
        return color;
    }

    /**
     * @return Returns the style.
     */
    public CssBorderTopStyleCSS2 getStyle2() {
        return style;
    }

    /**
     * @return Returns the width.
     */
    public CssBorderTopWidthCSS2 getWidth2() {
        return width;
    }

    /**
     * @param color The color to set.
     */
    public void setColor(CssBorderTopColorCSS2 color) {
        this.color = color;
	output = null;
    }

    /**
     * @param style The style to set.
     */
    public void setStyle(CssBorderTopStyleCSS2 style) {
        this.style = style;
	output = null;
    }

    /**
     * @param width The width to set.
     */
    public void setWidth(CssBorderTopWidthCSS2 width) {
        this.width = width;
	output = null;
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
	}
	return null;
    }

    /**
     * Returns the width property
     */
    public CssValue getWidth() {
	if (width != null) {
	    return width.getValue();
	}
	return null;
    }

    /**
     * Returns the style property
     */
    public String getStyle() {
	if (style != null) {
	    return style.getStyle();
	}
	return null;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (output != null) {
	    return output;
	}
	StringBuilder sb = new StringBuilder();
	boolean first = true;
	if(width != null) {
	    sb.append(width.toString());
	    first = false;
	}
	if(style != null) {
	    if (!first) {
		sb.append(' ');
	    } 
	    first = false;
	    sb.append(style.toString());
	}
	if(color != null) {
	    if (!first) {
		sb.append(' ');
	    } 	    
	    sb.append(color);
	}
	output = sb.toString();
	return output;
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border-top";
    }

    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */
    public void setImportant() {
	if(width != null) {
	    width.important = true;
	}
	if(style != null) {
	    style.important = true;
	}
	if(color != null) {
	    color.important = true;
	}
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
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if(width != null) {
	    width.addToStyle(ac, style);
	}
	if(this.style != null) {
	    this.style.addToStyle(ac, style);
	}
	if(color != null) {
	    color.addToStyle(ac, style);
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
	    return ((Css1Style) style).getBorderTopCSS2();
	} else {
	    return ((Css1Style) style).cssBorderCSS2.getTop();
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
	if(width != null) {
	    width.setInfo(line, source);
	}
	if(style != null) {
	    style.setInfo(line, source);
	}
	if(color != null) {
	    color.setInfo(line, source);
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
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	if (property instanceof CssBorderTopCSS2) {
	    CssBorderTopCSS2 top = (CssBorderTopCSS2) property;
	    return (width != null && width.equals(top.width)
		    && style != null && style.equals(top.style)
		    && color != null && color.equals(top.color));
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
