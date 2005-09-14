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
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;style&gt; || &lt;color&gt; || &lt;mode&gt; ||
 *  &lt;position&gt; || inherit<BR>
 *  <EM>Initial:</EM>not defined for shorthand properties<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property is the shorthand for 'text-underline-style',
 *  'text-underline-color', 'text-underline-mode' and 'text-underline-position'.
 */

public class CssTextUnderLine extends CssProperty
implements CssOperator  {

    CssValue underline;
    CssTextUlColor tuc;
    CssTextUlMode tum;
    CssTextUlPos tup;
    CssTextUlStyle tus;

    /**
     * Create a new CssTextUnderLine
     */
    public CssTextUnderLine() {
    }

    /**
     * Create a new CssTextUnderLine
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextUnderLine(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	CssValue val = expression.getValue();
	int maxvalues = 4;
	boolean correct = true;
	char op = SPACE;

	while (correct && (val != null) && (maxvalues-- > 0)) {

	    correct = false;

	    if (tus == null) {
		try {
		    tus = new CssTextUlStyle(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && tuc == null) {
		try {
		    tuc = new CssTextUlColor(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && tum == null) {
		try {
		    tum = new CssTextUlMode(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && tup == null) {
		try {
		    tup = new CssTextUlPos(ac, expression);
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

	if (tuc == null) {
	    tuc = new CssTextUlColor();
	}
	if (tum == null) {
	    tum = new CssTextUlMode();
	}
	if (tup == null) {
	    tup = new CssTextUlPos();
	}
	if (tus == null) {
	    tus = new CssTextUlStyle();
	}

    }

    public CssTextUnderLine(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextUnderLine != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextUnderLine = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextUnderLine();
	}
	else {
	    return ((Css3Style) style).cssTextUnderLine;
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
	return "text-underline";
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
	if (tus.isByUser()) {
	    ret += " " + tus;
	}
	if (tuc.isByUser()) {
	    ret += " " + tuc;
	}
	if (tum.isByUser()) {
	    ret += " " + tum;
	}
	if (tup.isByUser()) {
	    ret += " " + tup;
	}
	return ret.substring(1);
    }
}
