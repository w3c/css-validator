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
 *  <EM>Value:</EM> none || letters || lines || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property controls the creation of composite characters
 *  (a.k.a. "kumimoji") or lines (a.k.a. "warichu").
 */

public class CssTextCombine extends CssProperty {

    CssValue combine;

    static CssIdent none = new CssIdent("none");
    static CssIdent letters = new CssIdent("letters");
    static CssIdent lines = new CssIdent("lines");

    /**
     * Create a new CssCombine
     */
    public CssTextCombine() {
	combine = none;
    }

    /**
     * Create a new CssTextCombine
     *
     * @param expression The expression for this parameter
     * @exception InvalidParamException Incorrect value
     */
    public CssTextCombine(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(none)) {
	    combine = none;
	    expression.next();
	}
	else if (val.equals(letters)) {
	    combine = letters;
	    expression.next();
	}
	else if (val.equals(lines)) {
	    combine = lines;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    combine = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssTextCombine(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextCombine != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextCombine = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextCombine();
	}
	else {
	    return ((Css3Style) style).cssTextCombine;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextCombine &&
		combine.equals(((CssTextCombine) property).combine));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-combine";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return combine;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return combine.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return combine.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return combine == none;
    }

}
