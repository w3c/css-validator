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
 *  <EM>Value:</EM> normal || strict || inherit<BR>
 *  <EM>Initial:</EM>normal<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property selects the set of line breaking rules to be used for text. The values described below are especially useful to CJK authors, but the property itself is open to other, not yet specified settings for non-CJK
authors as well.
 */

public class CssLineBreak extends CssProperty {

    CssValue linebreak;

    CssIdent normal = new CssIdent("normal");
    CssIdent strict = new CssIdent("strict");

    /**
     * Create a new CssLineBreak
     */
    public CssLineBreak() {
	linebreak = normal;
    }

    /**
     * Create a new CssLineBreak
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssLineBreak(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(normal)) {
	    linebreak = normal;
	    expression.next();
	}
	else if (val.equals(strict)) {
	    linebreak = strict;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    linebreak = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssLineBreak(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssLineBreak != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssLineBreak = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getLineBreak();
	}
	else {
	    return ((Css3Style) style).cssLineBreak;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssLineBreak &&
		linebreak.equals(((CssLineBreak) property).linebreak));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "line-break";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return linebreak;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return linebreak.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return linebreak.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return linebreak == normal;
    }

}


