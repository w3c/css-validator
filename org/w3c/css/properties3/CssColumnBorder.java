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
import org.w3c.css.properties.CssBorderTopWidth;
import org.w3c.css.properties.CssBorderBottomWidth;
import org.w3c.css.properties.CssBorderLeftWidth;
import org.w3c.css.properties.CssBorderRightWidth;
import org.w3c.css.properties.CssBorderTopStyle;
import org.w3c.css.properties.CssBorderBottomStyle;
import org.w3c.css.properties.CssBorderLeftStyle;
import org.w3c.css.properties.CssBorderRightStyle;
import org.w3c.css.properties.CssColor;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;border-style&gt; || &lt;color&gt; || &lt;border-width&gt; ||
 *  inherit<BR>
 *  <EM>Initial:</EM>the value of the color property<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class CssColumnBorder extends CssProperty
implements CssOperator {

    CssValue value;
    /* I should use border-width and border-style here, but I don't see how to implement a
       shorthand property for shorthand properties ... So I splitted it up*/
    CssBorderTopWidth btw;
    CssBorderRightWidth brw;
    CssBorderLeftWidth blw;
    CssBorderBottomWidth bbw;
    CssBorderTopStyle bts;
    CssBorderRightStyle brs;
    CssBorderLeftStyle bls;
    CssBorderBottomStyle bbs;
    CssColor color;


    /**
     * Create a new CssColumnBorder
     */
    public CssColumnBorder() {
    }

    /**
     * Create a new CssColumnBorder
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssColumnBorder(ApplContext ac, CssExpression expression) throws InvalidParamException {

	CssValue val = expression.getValue();
	int maxvalues = 9;
	boolean correct = true;
	char op = SPACE;

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else {

	    while (correct && (val != null) && (maxvalues -- > 0)) {

		correct = false;

		if (btw == null) {
		    try {
			btw = new CssBorderTopWidth(ac, expression);
			correct = true;
		    }
		    catch (InvalidParamException e) {
		    }
		}
		if (!correct && bbw == null) {
		    try {
			bbw = new CssBorderBottomWidth(ac, expression);
			correct = true;
		    }
		    catch (InvalidParamException e) {
		    }
		}
		if (!correct && blw == null) {
		    try {
			blw = new CssBorderLeftWidth(ac, expression);
			correct = true;
		    }
		    catch (InvalidParamException e) {
		    }
		}
		if (!correct && brw == null) {
		    try {
			brw = new CssBorderRightWidth(ac, expression);
			correct = true;
		    }
		    catch (InvalidParamException e) {
		    }
		}
		if (!correct && color == null) {
		    try {
			color = new CssColor(ac, expression);
			correct = true;
		    }
		    catch (InvalidParamException e) {
		    }
		}

		if (!correct && bts == null) {
		    try {
			bts = new CssBorderTopStyle(ac, expression);
			correct = true;
		    }
		    catch (InvalidParamException e) {
		    }
		}
		if (!correct && bbw == null) {
		    try {
			bbs = new CssBorderBottomStyle(ac, expression);
			correct = true;
		    }
		    catch (InvalidParamException e) {
		    }
		}
		if (!correct && bls == null) {
		    try {
			bls = new CssBorderLeftStyle(ac, expression);
			correct = true;
		    }
		    catch (InvalidParamException e) {
		    }
		}
		if (!correct && brs == null) {
		    try {
			brs = new CssBorderRightStyle(ac, expression);
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
	}
    }


    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssColumnBorder != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssColumnBorder = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getColumnBorder();
	}
	else {
	    return ((Css3Style) style).cssColumnBorder;
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
	return "column-border";
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

	if (btw != null) {
		ret += " " + btw.toString();
	}
	if (brw != null) {
		ret += " " + brw.toString();
	}
	if (blw != null) {
		ret += " " + blw.toString();
    }
	if (bbw != null) {
		ret += " " + bbw.toString();
    }
	if (bts != null) {
		ret += " " + bts.toString();
    }
	if (brs != null) {
		ret += " " + brs.toString();
    }
	if (bls != null) {
		ret += " " + bls.toString();
    }
    if (bbs != null) {
		ret += " " + bbs.toString();
    }
    if (color != null) {
		ret += " " + color.toString();
    }

	return ret.substring(1);

    }
}
