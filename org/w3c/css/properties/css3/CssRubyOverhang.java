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
 *  <EM>Value:</EM> auto || start || end || none || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>the parent of elements with display: ruby-text<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class CssRubyOverhang extends CssProperty {

    CssValue rubyov;
    ApplContext ac;

    CssIdent auto = new CssIdent("auto");
    CssIdent start = new CssIdent("start");
    CssIdent end = new CssIdent("end");
    CssIdent none = new CssIdent("none");

    /**
     * Create a new CssRubyOverhang
     */
    public CssRubyOverhang() {
	rubyov = auto;
    }

    /**
     * Create a new CssRubyOverhang
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssRubyOverhang(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(auto)) {
	    rubyov = auto;
	    expression.next();
	}
	else if (val.equals(start)) {
	    rubyov = start;
	    expression.next();
	}
	else if (val.equals(end)) {
	    rubyov = end;
	    expression.next();
	}
	else if (val.equals(none)) {
	    rubyov = end;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    rubyov = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssRubyOverhang(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssRubyOverhang != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssRubyOverhang = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getRubyOverhang();
	} else {
	    return ((Css3Style) style).cssRubyOverhang;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssRubyOverhang &&
                rubyov.equals( ((CssRubyOverhang) property).rubyov));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "ruby-overhang";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return rubyov;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return rubyov.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return rubyov.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return rubyov == auto;
    }

}
