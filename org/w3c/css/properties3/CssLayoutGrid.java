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
 *  <EM>Value:</EM> &lt;mode&gt; || &lt;type&gt; || &lt;line&gt; || 
 *  &lt;char&gt; || inherit<BR>
 *  <EM>Initial:</EM>not defined for shorthand properties<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  The 'layout-grid' property is a shorthand property for setting 
 *  'layout-grid-mode', 'layout-grid-type', 'layout-grid-line' and 
 *  'layout-grid-char' at the same time in the style sheet. Using the value 
 *  'none' on the shorthand property sets the 'layout-grid-mode' to 'none'. 
 *  Using the value "none none" sets both the 'layout-grid-mode' and 
 *  'layout-grid-line' to 'none', and using the value "none none none" sets 
 *  the previous properties as well as 'layout-grid-char' to 'none'. 
 */

public class CssLayoutGrid extends CssProperty 
implements CssOperator {
 
    CssValue grid;
    CssLayoutGridType lgt;
    CssLayoutGridMode lgm;
    CssLayoutGridLine lgl;
    CssLayoutGridChar lgc;

    /**
     * Create a new CssLayoutGrid
     */
    public CssLayoutGrid() {
    }

    /**
     * Create a new CssLayoutGrid
     * 
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssLayoutGrid(ApplContext ac, CssExpression expression) throws InvalidParamException {

	CssValue val = expression.getValue();
	int maxvalues = 4;
	boolean correct = true;
	char op = SPACE;

	while (correct && (val != null) && (maxvalues-- > 0)) {

	    correct = false;

	    if (lgt == null) {
		try {
		    lgt = new CssLayoutGridType(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && lgl == null) {
		try {
		    lgl = new CssLayoutGridLine(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && lgm == null) {
		try {
		    lgm = new CssLayoutGridMode(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct && lgc == null) {
		try {
		    lgc = new CssLayoutGridChar(ac, expression);
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

	if (lgt == null) {
	    lgt = new CssLayoutGridType();
	}
	if (lgm == null) {
	    lgm = new CssLayoutGridMode();
	}
	if (lgl == null) {
	    lgl = new CssLayoutGridLine();
	}
	if (lgc == null) {
	    lgc = new CssLayoutGridChar();
	}

    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssLayoutGrid != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssLayoutGrid = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getLayoutGrid();
	}
	else {
	    return ((Css3Style) style).cssLayoutGrid;
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
	return "layout-grid";
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
	
	if (lgt.isByUser()) {
	    ret += " " + lgt;
	}
	if (lgm.isByUser()) {
	    ret += " " + lgm;
	}
	if (lgl.isByUser()) {
	    ret += " " + lgl;
	}
	if (lgc.isByUser()) {
	    ret += " " + lgc;
	}
	return ret.substring(1);
    }
}
