//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;uri&gt; || none || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class MarkerEnd extends CssProperty {

    CssValue markerEnd;
    ApplContext ac;

    CssIdent none = new CssIdent("none");

    /**
     * Create a new MarkerEnd
     */
    public MarkerEnd() {
	//nothing to do
    }

    /**
     * Create a new MarkerEnd
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public MarkerEnd(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this.ac = ac;
	setByUser(); // tell this property is set by the user
	CssValue val = expression.getValue();
	if (val instanceof CssURL) {
	    markerEnd = val;
	    expression.next();
	}
	else if (val instanceof CssIdent) {
	    if (val.equals(inherit)) {
		markerEnd = inherit;
		expression.next();
	    } else if (val.equals(none)) {
		markerEnd = none;
		expression.next();
	    }
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    public MarkerEnd(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((SVGStyle) style).markerEnd != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGStyle) style).markerEnd = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGStyle) style).getMarkerEnd();
	} else {
	    return ((SVGStyle) style).markerEnd;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof MarkerEnd &&
		markerEnd.equals( ((MarkerEnd) property).markerEnd));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "marker-end";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return markerEnd;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return markerEnd.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return markerEnd.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (markerEnd == none);
    }

}
