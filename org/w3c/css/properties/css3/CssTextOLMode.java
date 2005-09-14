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
 *  <EM>Value:</EM>  continuous || skipwhitespace || inherit<BR>
 *  <EM>Initial:</EM>continuous<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property specifies the mode for the overline, that is whether the
 *  overline is continuous or whether it appears only under skipwhitespace and not
 *  whitespace.
 */

public class CssTextOLMode extends CssProperty {

    CssValue olmode;

    static CssIdent continuous = new CssIdent("continuous");
    static CssIdent skipwhitespace = new CssIdent("skip-white-space");

    /**
     * Create a new CssTextOLMode
     */
    public CssTextOLMode() {
	olmode = continuous;
    }

    /**
     * Create a new CssTextOLMode
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextOLMode(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(continuous)) {
	    olmode = continuous;
	    expression.next();
	}
	else if (val.equals(skipwhitespace)) {
	    olmode = skipwhitespace;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    olmode = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value",
					    expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssTextOLMode(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextOLMode != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextOLMode = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextOLMode();
	}
	else {
	    return ((Css3Style) style).cssTextOLMode;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextOLMode &&
		olmode.equals(((CssTextOLMode) property).olmode));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-overline-mode";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return olmode;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return olmode.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return olmode.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return olmode == continuous;
    }

}
