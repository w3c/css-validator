//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> normal || &lt;word-break-CJK&gt; || &lt;word-break-wrap&gt; ||
 *  word-break-inside || inherit<BR>
 *  <EM>Initial:</EM>normal<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  The 'word-break' property is a shorthand property for setting
 *  'word-break-CJK', 'word-break-wrap' and 'word-break-inside', at the same
 *  place in the style sheet.
 */

public class CssWordBreak extends CssProperty
implements CssOperator {

    CssWordBreakCJK wbc;
    CssWordBreakInside wbi;
    CssValue wordbreak;

    /**
     * Create a new CssWordBreak
     */
    public CssWordBreak() {
    }

    /**
     * Create a new CssWordBreak
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssWordBreak(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	CssValue val = expression.getValue();
	int maxvalues = 3;
	boolean correct = true;
	char op = SPACE;

	while (correct && (val != null) && (maxvalues-- > 0)) {

	    correct = false;

	    if (wbc == null) {
		try {
		    wbc = new CssWordBreakCJK(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && wbi == null) {
		try {
		    wbi = new CssWordBreakInside(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct) {
		throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
	    }

	    val = expression.getValue();
	    op = expression.getOperator();

	}

	if (wbc == null) {
	    wbc = new CssWordBreakCJK();
	}
	if (wbi == null) {
	    wbi = new CssWordBreakInside();
	}

    }

    public CssWordBreak(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssWordBreak != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssWordBreak = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getWordBreak();
	}
	else {
	    return ((Css3Style) style).cssWordBreak;
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
	return "word-break";
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
	if (wbc.isByUser()) {
	    ret += " " + wbc;
	}
	if (wbi.isByUser()) {
	    ret += " " + wbi;
	}
	return ret.substring(1);

    }

    //    /**
    // * Is the value of this property a default value
    //* It is used by alle macro for the function <code>print</code>
    //*/
    //public boolean isDefault() {
    //return wordbreak == normal;
    //}

}
