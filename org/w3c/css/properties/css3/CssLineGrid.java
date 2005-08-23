//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
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
 *
 */

public class CssLineGrid extends CssProperty
implements CssOperator {

    CssValue linegrid;
    CssLineGridMode lgm;
    CssLineGridProgression lgp;

    /**
     * Creates a new CssLineGrid
     */
    public CssLineGrid() {
    }

    /**
     * Creates a new CssLineGrid
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssLineGrid(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	CssValue val = expression.getValue();
	int maxvalues = 2;
	boolean correct = true;
	char op = SPACE;

	while (correct && (val != null) && (maxvalues-- > 0)) {

	    correct = false;

	    if (lgm == null) {
		try {
		    lgm = new CssLineGridMode(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && lgp == null) {
		try {
		    lgp = new CssLineGridProgression(ac, expression);
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

    public CssLineGrid(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssLineGrid != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssLineGrid = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getLineGrid();
	}
	else {
	    return ((Css3Style) style).cssLineGrid;
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
	return "line-grid";
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
	if (lgp != null) {
	    ret += " " + lgp.toString();
	}
	if (lgm != null) {
	    ret += " " + lgm.toString();
	}
	return ret.substring(1);
    }

}
