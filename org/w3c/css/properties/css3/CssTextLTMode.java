//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM>  continuous || skip-white-space || inherit<BR>
 *  <EM>Initial:</EM>continuous<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property specifies the mode for the line-through, that is whether the
 *  line-through is continuous or whether it appears only under words and not
 *  whitespace.
 */

public class CssTextLTMode extends CssProperty {

    CssValue ltmode;

    static CssIdent continuous = new CssIdent("continuous");
    static CssIdent skipwhitespace = new CssIdent("skip-white-space");

    /**
     * Create a new CssTextLTMode
     */
    public CssTextLTMode() {
	ltmode = continuous;
    }

    /**
     * Create a new CssTextLTMode
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextLTMode(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(continuous)) {
	    ltmode = continuous;
	    expression.next();
	}
	else if (val.equals(skipwhitespace)) {
	    ltmode = skipwhitespace;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    ltmode = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value",
					    expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssTextLTMode(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextLTMode != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextLTMode = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextLTMode();
	}
	else {
	    return ((Css3Style) style).cssTextLTMode;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextLTMode &&
		ltmode.equals(((CssTextLTMode) property).ltmode));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-line-through-mode";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return ltmode;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return ltmode.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return ltmode.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return ltmode == continuous;
    }

}
