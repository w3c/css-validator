//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import java.util.Hashtable;

import org.w3c.css.values.CssColor;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssOperator;

/**
 *
 */

public class CssTextOverflow extends CssProperty
implements CssOperator {

    CssValue textoverflow;
    CssTextOverflowMode tom;
    CssTextOverflowEllipsis toe;

    /**
     * Creates a new CssTextOverflow
     */
    public CssTextOverflow() {
    }

    /**
     * Creates a new CssTextOverflow
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssTextOverflow(ApplContext ac, CssExpression expression) throws InvalidParamException {

	CssValue val = expression.getValue();
	int maxvalues = 2;
	boolean correct = true;
	char op = SPACE;

	while (correct && (val != null) && (maxvalues-- > 0)) {

	    correct = false;

	    if (tom == null) {
		try {
		    tom = new CssTextOverflowMode(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && toe == null) {
		try {
		    toe = new CssTextOverflowEllipsis(ac, expression);
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

    }


    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextOverflow != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextOverflow = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextOverflow();
	}
	else {
	    return ((Css3Style) style).cssTextOverflow;
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
	return "text-overflow";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
		return null;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    //    public boolean isSoftlyInherited() {
    //	return fontemph.equals(inherit);
    //}

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	String ret = "";
	if (tom != null) {
	    ret += " " + tom.toString();
	}
	if (toe != null) {
	    ret += " " + toe.toString();
	}
	return ret.substring(1);
    }

}
