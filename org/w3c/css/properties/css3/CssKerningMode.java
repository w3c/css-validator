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
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> none || pair || contextual || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property controls all kerning effects.
 */

public class CssKerningMode extends CssProperty {

    CssValue mode;

    static CssIdent none = new CssIdent("none");
    static CssIdent pair = new CssIdent("pair");
    static CssIdent contextual = new CssIdent("contextual");

    /**
     * Create a new CssKerningMode
     */
    public CssKerningMode() {
	mode = none;
    }

    /**
     * Create a new CssKerningMode
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssKerningMode(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(none)) {
	    mode = none;
	    expression.next();
	}
	else if (val.equals(pair)) {
	    mode = pair;
	    expression.next();
	}
	else if (val.equals(contextual)) {
	    mode = contextual;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    mode = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    public CssKerningMode(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssKerningMode != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssKerningMode = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getKerningMode();
	}
	else {
	    return ((Css3Style) style).cssKerningMode;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssKerningMode &&
		mode.equals(((CssKerningMode) property).mode));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "kerning-mode";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return mode;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return mode.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return mode.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return mode == none;
    }

}
