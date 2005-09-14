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
 *  <EM>Value:</EM> auto-pos || before || after || inherit<BR>
 *  <EM>Initial:</EM>auto-pos<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property sets the position of the underline when set through the
 *  text-decoration property.
 */

public class CssTextUlPos extends CssProperty {

    CssValue ulpos;

    static CssIdent autopos = new CssIdent("auto-pos");
    static CssIdent beforeedge = new CssIdent("before-edge");
    static CssIdent afterbaseline = new CssIdent("after-baseline");
    static CssIdent afteredge = new CssIdent("after-edge");

    /**
     * Create a new CssTextUlPos
     */
    public CssTextUlPos() {
	ulpos = autopos;
    }

    /**
     * Create a new CssTextUlPos
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextUlPos(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(autopos)) {
	    ulpos = autopos;
	    expression.next();
	}
	else if (val.equals(beforeedge)) {
	    ulpos = beforeedge;
	    expression.next();
	}
	else if (val.equals(afterbaseline)) {
	    ulpos = afterbaseline;
	    expression.next();
	}
	else if (val.equals(afteredge)) {
	    ulpos = afteredge;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    ulpos = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value",
					    expression.getValue(),
					    getPropertyName(), ac);
	}

    }

    public CssTextUlPos(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextUlPos != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextUlPos = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextUlPos();
	}
	else {
	    return ((Css3Style) style).cssTextUlPos;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextUlPos &&
		ulpos.equals(((CssTextUlPos) property).ulpos));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-underline-position";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return ulpos;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return ulpos.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return ulpos.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return ulpos == autopos;
    }

}
