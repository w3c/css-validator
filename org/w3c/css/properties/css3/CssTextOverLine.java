//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;style&gt; || &lt;color&gt; || &lt;mode&gt; || inherit<BR>
 *  <EM>Initial:</EM>not defined for shorthand properties<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property is the shorthand for 'text-overline-style',
 *  'text-overline-color' and 'text-overline-mode'.
 */

public class CssTextOverLine extends CssProperty
implements CssOperator {

    CssValue overline;
    CssTextOLStyle tos;
    CssTextOLColor toc;
    CssTextOLMode tom;

    /**
     * Create a new CssTextOverLine
     */
    public CssTextOverLine() {
    }

    /**
     * Create a new CssTextOverLine
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextOverLine(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	CssValue val = expression.getValue();
	int maxvalues = 3;
	boolean correct = true;
	char op = SPACE;

	while (correct && (val != null) && (maxvalues-- > 0)) {

	    correct = false;

	    if (tos == null) {
		try {
		    tos = new CssTextOLStyle(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && toc == null) {
		try {
		    toc = new CssTextOLColor(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && tom == null) {
		try {
		    tom = new CssTextOLMode(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct) {
		throw new InvalidParamException("value",
						expression.getValue(),
						getPropertyName(), ac);
	    }

	    val = expression.getValue();
	    op = expression.getOperator();
	}

	if (tos == null) {
	    tos = new CssTextOLStyle();
	}
	if (toc == null) {
	    toc = new CssTextOLColor();
	}
	if (tom == null) {
	    tom = new CssTextOLMode();
	}
    }

    public CssTextOverLine(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextOverLine != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextOverLine = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextOverLine();
	}
	else {
	    return ((Css3Style) style).cssTextOverLine;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return false;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-overline";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return null;
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {

	String ret = "";

	if (tos.isByUser()) {
	    ret += " " + tos;
	}
	if (toc.isByUser()) {
	    ret += " " + toc;
	}
	if (tom.isByUser()) {
	    ret += " " + tom;
	}
	return ret.substring(1);

    }
}
