//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssOperator;

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

public class CssMarquee extends CssProperty
implements CssOperator  {

    CssValue underline;
    CssMarqueeDirection mdir;
    CssMarqueeRepetition mrep;
    CssMarqueeSpeed mspeed;
    CssMarqueeStyle mstyle;

    /**
     * Create a new CssMarquee
     */
    public CssMarquee() {
    }

    /**
     * Create a new CssMarquee
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssMarquee(ApplContext ac, CssExpression expression) throws InvalidParamException {

	CssValue val = expression.getValue();
	int maxvalues = 4;
	boolean correct = true;
	char op = SPACE;

	while (correct && (val != null) && (maxvalues-- > 0)) {

	    correct = false;

	    if (mstyle == null) {
		try {
		    mstyle = new CssMarqueeStyle(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && mdir == null) {
		try {
		    mdir = new CssMarqueeDirection(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && mrep == null) {
		try {
		    mrep = new CssMarqueeRepetition(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && mspeed == null) {
		try {
		    mspeed = new CssMarqueeSpeed(ac, expression);
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

	if (mdir == null) {
	    mdir = new CssMarqueeDirection();
	}
	if (mrep == null) {
	    mrep = new CssMarqueeRepetition();
	}
	if (mspeed == null) {
	    mspeed = new CssMarqueeSpeed();
	}
	if (mstyle == null) {
	    mstyle = new CssMarqueeStyle();
	}

    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssMarquee != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssMarquee = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getMarquee();
	}
	else {
	    return ((Css3Style) style).cssMarquee;
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
	return "marquee";
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
	if (mstyle.isByUser()) {
	    ret += " " + mstyle;
	}
	if (mdir.isByUser()) {
	    ret += " " + mdir;
	}
	if (mrep.isByUser()) {
	    ret += " " + mrep;
	}
	if (mspeed.isByUser()) {
	    ret += " " + mspeed;
	}
	return ret.substring(1);
    }
}
