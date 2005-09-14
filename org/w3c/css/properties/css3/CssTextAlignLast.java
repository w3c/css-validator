//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> auto || start || end || center || justify ||
 *  size || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property describes how the last line of the inline content of a block
 *  is aligned. This also applies to the only line of a block if it contains a
 *  single line, the line preceding a BR element and to last lines of
 *  anonymous blocks. Typically the last line is aligned like the other lines
 *  of the block element, this is set by the 'text-align' property. However,
 *  in some situations like when the 'text-align' property is set to
 *  'justify', the last line may be aligned differently.
 */

public class CssTextAlignLast extends CssProperty {

    CssValue alignlast;

    static CssIdent start = new CssIdent("start");
    static CssIdent end = new CssIdent("end");
    static CssIdent center = new CssIdent ("center");
    static CssIdent justify = new CssIdent("justify");
    static CssIdent size = new CssIdent("size");
	static CssIdent left = new CssIdent("left");
	static CssIdent right = new CssIdent("right");

    /**
     * Create a new CssTextAlignLast
     */
    public CssTextAlignLast() {
		alignlast = start;
    }

    /**
     * Create a new CssTextAlignLast
     *
     * @param expression The expression for this parameter
     * @exception InvalidParamException Incorrect value
     */
    public CssTextAlignLast(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(start)) {
	    alignlast = start;
	    expression.next();
	}
	else if (val.equals(end)) {
	    alignlast = end;
	    expression.next();
	}
	else if (val.equals(center)) {
	    alignlast = center;
	    expression.next();
	}
	else if (val.equals(justify)) {
	    alignlast = justify;
	    expression.next();
	}
	else if (val.equals(size)) {
	    alignlast = size;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    alignlast = inherit;
	    expression.next();
	}
	else if (val.equals(left)) {
		alignlast = left;
	}
	else if (val.equals(right)) {
		alignlast = right;
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssTextAlignLast(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextAlignLast != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextAlignLast = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextAlignLast();
	}
	else {
	    return ((Css3Style) style).cssTextAlignLast;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextAlignLast &&
		alignlast.equals(((CssTextAlignLast) property).alignlast));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-align-last";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return alignlast;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return alignlast.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return alignlast.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
		return alignlast == start;
    }

}



