//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.svgproperties;

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
 * <P>
 * <EM>Value:</EM> &lt;marker-end&gt; || &lt;marker-mid&gt; ||
 *  &lt;marker-start&gt; || inherit<BR>
 * <EM>Initial:</EM>see individual properties<BR>
 * <EM>Applies to:</EM>path, line, polyline and polygon elements<BR>
 * <EM>Inherited</EM>see Inheritance of Painting Properties<BR>
 * <EM>Percentages:</EM>no<BR>
 * <EM>Media:</EM>visual
 */

public class Marker extends CssProperty
implements CssOperator {

    CssValue value;
    MarkerEnd markerEnd;
    MarkerMid markerMid;
    MarkerStart markerStart;

    /**
     * Creates a new Marker
     */
    public Marker() {
	// nothing to do
    }

    /**
     * Creates a new Marker
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public Marker(ApplContext ac, CssExpression expression) throws InvalidParamException {

	CssValue val = expression.getValue();
	int maxvalues = 3;
	boolean correct = true;
	char op = SPACE;

	while (correct && (val != null) && (maxvalues-- > 0)) {

	    correct = false;

	    if (markerStart == null) {
			try {
			    markerStart = new MarkerStart(ac, expression);
			    correct = true;
			} catch (InvalidParamException e) { }
	    } else if (markerEnd == null) {
			try {
			    markerEnd = new MarkerEnd(ac, expression);
			    correct = true;
			} catch (InvalidParamException e) { }
	    } else if (markerMid == null) {
			try {
			    markerMid = new MarkerMid(ac, expression);
			    correct = true;
			} catch (InvalidParamException e) { }
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
	if (((SVGStyle) style).marker != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGStyle) style).marker = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGStyle) style).getMarker();
	}
	else {
	    return ((SVGStyle) style).marker;
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
		return "marker";
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
    //	return value.equals(inherit);
    //}

    /**
     * Returns a string representation of the object
     */
    public String toString() {
		String ret = "";

		if (markerStart != null) {
			if (markerStart.isByUser()) {
			    ret += " " + markerStart;
			}
		}

		if (markerEnd != null) {
			if (markerEnd.isByUser()) {
			    ret += " " + markerEnd;
			}
		}

		if (markerMid != null) {
			if (markerMid.isByUser()) {
			    ret += " " + markerMid;
			}
		}

		return ret.substring(1);

    }

}
