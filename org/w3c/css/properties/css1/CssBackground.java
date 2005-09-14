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
import org.w3c.css.properties.css3.CssBackgroundSize;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     <A NAME="background">5.3.7 &nbsp;&nbsp; 'background'</A>
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;background-color&gt; || &lt;background-image&gt; ||
 *   &lt;background-repeat&gt; || &lt;background-attachment&gt; ||
 *   &lt;background-position&gt;<BR>
 *   <EM>Initial:</EM> not defined for shorthand properties<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> allowed on &lt;background-position&gt;<BR>
 *   <P>
 *   The 'background' property is a shorthand property for setting the individual
 *   background properties (i.e., 'background-color', 'background-image',
 *   'background-repeat', 'background-attachment' and 'background-position') at
 *   the same place in the style sheet.
 *   <P>
 *   Possible values on the 'background' properties are the set of all possible
 *   values on the individual properties.
 *   <PRE>
 *   BODY { background: red }
 *   P { background: url(chess.png) gray 50% repeat fixed }
 * </PRE>
 *   <P> The 'background' property always sets all the individual background
 *   properties.  In the first rule of the above example, only a value for
 *   'background-color' has been given and the other individual properties are
 *   set to their initial value. In the second rule, all individual properties
 *   have been specified.
 *
 * @version $Revision$
 * @see CssBackgroundColor
 * @see CssBackgroundImage
 * @see CssBackgroundRepeat
 * @see CssBackgroundAttachment
 * @see CssBackgroundPosition
 */
public class CssBackground extends CssProperty
        implements CssOperator, CssBackgroundConstants {

    CssBackgroundColor color;
    CssBackgroundImage image;
    CssBackgroundRepeat repeat;
    CssBackgroundAttachment attachment;
    CssBackgroundPosition position;
    CssBackgroundSize size;
    boolean sizedefined;

    boolean same;

    /**
     * Create a new CssBackground
     */
    public CssBackground() {
    }

    /**
     * Set the value of the property<br/>
     * Does not check the number of values
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssBackground(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Set the value of the property
     *
     * @param expression The expression for this property
     * @param check set it to true to check the number of values
     * @exception InvalidParamException The expression is incorrect
     */
    public CssBackground(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	CssValue val = expression.getValue();
	char op = SPACE;
	boolean find = true;
	setByUser();

	// if there are too many values -> error
	if(check && expression.getCount() > 6) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	boolean manyValues = (expression.getCount() > 1);

	while (find) {
	    find = false;
	    val = expression.getValue();
	    op = expression.getOperator();

	    if (val == null) {
		break;
	    }

	    // if there are many values, we can't have inherit as one of them
	    if(manyValues && val != null && val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", null, null, ac);
	    }

	    if (color == null) {
		try {
		    color = new CssBackgroundColor(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		    // nothing to do, image will test this value
		}
	    }
	    if (!find && image == null) {
		try {
		    image = new CssBackgroundImage(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		    // nothing to do, repeat will test this value
		}
	    }
	    if (!find && repeat == null) {
		try {
		    repeat = new CssBackgroundRepeat(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		    // nothing to do, attachment will test this value
		}
	    }
	    if (!find && attachment == null) {
		try {
		    attachment = new CssBackgroundAttachment(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		    // nothing to do, position will test this value
		}
	    }
	    if (!find && position == null) {
		try {
		    position = new CssBackgroundPosition(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		    // nothing to do
		}
	    }
	    if (op != SPACE) {
		if (op != SLASH) {
		    throw new InvalidParamException("operator",
			    ((new Character(op)).toString()),
			    ac);
		} else {
		    //try {
		    size = new CssBackgroundSize(ac, expression);
		    sizedefined = true;
		    break;
		    //} catch (InvalidParamException e) {
		    // error!
		    //}
		}
	    }
	    if(check && !find && val != null) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	}
    }


    /**
     * Returns the value of this property
     */
    public Object get() {
	return color;
    }

    /**
     * Returns the color
     */
    public final CssValue getColor() {
	if (color == null) {
	    return null;
	} else {
	    return color.getColor();
	}
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "background";
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	String ret = "";
	if(color != null) {
	    ret += color;
	}
	if(image != null) {
	    if(ret != null) {
		ret += " ";
	    }
	    ret += image;
	}
	if(repeat != null) {
	    if(ret != null) {
		ret += " ";
	    }
	    ret += repeat;
	}
	if(attachment != null) {
	    if(ret != null) {
		ret += " ";
	    }
	    ret += attachment;
	}
	if(position != null) {
	    if(ret != null) {
		ret += " ";
	    }
	    ret += position;
	}
	if(sizedefined) {
	    ret += "/";
	    ret += size;
	}
	return ret;
	/*if (same) {
	    return inherit.toString();
	} else {
	    String ret = "";
	    if (color.byUser)
		ret += " " + color.toString();
	    if (image.byUser)
		ret += " " + image.toString();
	    if (image.byUser)
		ret += " " + repeat.toString();
	    if (attachment.byUser)
		ret += " " + attachment.toString();
	    if (position.byUser)
			ret += " " + position.toString();

	    if (sizedefined)
			ret += "/" + size.toString();
		return ret.trim();
	}*/
    }

    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */
    public void setImportant() {
	if(color != null) {
	    color.important = true;
	}
	if(image != null) {
	    image.important = true;
	}
	if(repeat != null) {
	    repeat.important = true;
	}
	if(attachment != null) {
	    attachment.important = true;
	}
	if(position != null) {
	    position.important = true;
	}
    }

    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return ((color == null || color.important) &&
		(image == null || image.important) &&
		(repeat == null || repeat.important) &&
		(attachment == null || attachment.important) &&
		(position == null || position.important));
    }

    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */
    public void print(CssPrinterStyle printer) {
	if ((color != null && image != null &&
	     repeat != null && attachment !=null &&
	     position != null) &&
	    (getImportant() ||
	     (!image.important &&
	      !color.important &&
	      !repeat.important &&
	      !attachment.important &&
	      !position.important))) {
	    if (color.byUser || image.byUser || repeat.byUser
		|| attachment.byUser || position.byUser) {
		printer.print(this);
	    }
	} else {
	    if (color != null)
		color.print(printer);
	    if (image != null)
		image.print(printer);
	    if (repeat != null)
		repeat.print(printer);
	    if (attachment != null)
		attachment.print(printer);
	    if (position != null)
		position.print(printer);
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
	if (color != null) {
	    color.setSelectors(selector);
	}
	if (image != null) {
	    image.setSelectors(selector);
	}
	if (repeat != null) {
	    repeat.setSelectors(selector);
	}
	if (attachment != null) {
	    attachment.setSelectors(selector);
	}
	if (position != null) {
	    position.setSelectors(selector);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	((Css1Style) style).cssBackground.same = same;
	((Css1Style) style).cssBackground.byUser = byUser;

	if(color != null) {
	    color.addToStyle(ac, style);
	}
	if(image != null) {
	    image.addToStyle(ac, style);
	}
	if(repeat != null) {
	    repeat.addToStyle(ac, style);
	}
	if(attachment != null) {
	    attachment.addToStyle(ac, style);
	}
	if(position != null) {
	    position.addToStyle(ac, style);
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
	    return ((Css1Style) style).getBackground();
	} else {
	    return ((Css1Style) style).cssBackground;
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

    /**
     * Update the source file and the line.
     * Overrides this method for a macro
     *
     * @param line The line number where this property is defined
     * @param source The source file where this property is defined
     */
    public void setInfo(int line, String source) {
	super.setInfo(line, source);
	if(color != null) {
	    color.setInfo(line, source);
	}
	if(image != null) {
	    image.setInfo(line, source);
	}
	if(repeat != null) {
	    repeat.setInfo(line, source);
	}
	if(attachment != null) {
	    attachment.setInfo(line, source);
	}
	if(position != null) {
	    position.setInfo(line, source);
	}
    }

}
