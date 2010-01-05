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
 *  <EM>Value:</EM> auto || normal || select-all || select-before ||
 *  select-after || select-same || select-menu || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:interactive
 *  <P>
 *  The purpose of this property is to determine what happens to an element
 *  when the user focusses it either by "tabbing" into it or clicking on it
 *  with a pointing device. This property serves as both a shortcut property
 *  that sets the values of the individual user-focus-key and
 *  user-focus-pointer properties, and also serves to determine what
 *  happens for any other input methods not covered by user-focus-key
 *  and user-focus-pointer.
 */

public class CssUserFocus extends CssProperty {

    CssValue userfocus;

    static CssIdent auto = new CssIdent("auto");
    static CssIdent normal = new CssIdent("normal");
    static CssIdent selectall = new CssIdent("select-all");
    static CssIdent selectbefore = new CssIdent("select-before");
    static CssIdent selectafter = new CssIdent("select-after");
    static CssIdent selectsame = new CssIdent("select-same");
    static CssIdent selectmenu = new CssIdent("select-menu");

    /**
     * Create a new CssUserFocus
     */
    public CssUserFocus() {
	userfocus = auto;
    }

    /**
     * Create a new CssUserFocus
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssUserFocus(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(auto)) {
	    userfocus = auto;
	    expression.next();
	}
	else if (val.equals(normal)) {
	    userfocus = normal;
	    expression.next();
	}
	else if (val.equals(selectall)) {
	    userfocus = selectall;
	    expression.next();
	}
	else if (val.equals(selectbefore)) {
	    userfocus = selectbefore;
	    expression.next();
	}
	else if (val.equals(selectafter)) {
	    userfocus = selectafter;
	    expression.next();
	}
	else if (val.equals(selectsame)) {
	    userfocus = selectsame;
	    expression.next();
	}
	else if (val.equals(selectmenu)) {
	    userfocus = selectmenu;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    userfocus = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssUserFocus(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssUserFocus != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssUserFocus = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getUserFocus();
	}
	else {
	    return ((Css3Style) style).cssUserFocus;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssUserFocus &&
		userfocus.equals(((CssUserFocus) property).userfocus));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "user-focus";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return userfocus;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return userfocus.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return userfocus.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return userfocus == auto;
    }

}

