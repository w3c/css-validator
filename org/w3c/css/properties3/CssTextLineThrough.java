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
 *  <EM>Value:</EM> &lt;style&gt; || &lt;color&gt; || &lt;mode&gt; || 
 *  &lt;position&gt; || inherit<BR>
 *  <EM>Initial:</EM>not defined for shorthand properties<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property is the shorthand for 'text-line-through-style', 
 *  'text-line-through-color' and 'text-line-through-mode'.
 */

public class CssTextLineThrough extends CssProperty 
implements CssOperator {
 
    CssValue linethrough;
    CssTextLTStyle tls;
    CssTextLTColor tlc;
    CssTextLTMode tlm;

    /**
     * Create a new CssTextLineThrough
     */
    public CssTextLineThrough() {
    }

    /**
     * Create a new CssTextLineThrough
     * 
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextLineThrough(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	CssValue val = expression.getValue();
	int maxvalues = 3;
	boolean correct = true;
	char op = SPACE;

	while (correct && (val != null) && (maxvalues -- > 0)) {

	    correct = false;

	    if (tls == null) {
		try {
		    tls = new CssTextLTStyle(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && tlc == null) {
		try {
		    tlc = new CssTextLTColor(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && tlm == null) {
		try {
		    tlm = new CssTextLTMode(ac, expression);
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

	if (tls == null) {
	    tls = new CssTextLTStyle();
	}
	if (tlc == null) {
	    tlc = new CssTextLTColor();
	}
	if (tlm == null) {
	    tlm = new CssTextLTMode();
	}

    }

    public CssTextLineThrough(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextLineThrough != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextLineThrough = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextLineThrough();
	}
	else {
	    return ((Css3Style) style).cssTextLineThrough;
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
	return "text-line-through";
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

	if (tls.isByUser()) {
	    ret += " " + tls;
	}
	if (tlc.isByUser()) {
	    ret += " " + tlc;
	}
	if (tlm.isByUser()) {
	    ret += " " + tlc;
	}
	return ret.substring(1);

    }
}
