//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
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
 *  when the user focusses it specifically by "tabbing" into it.
 */

public class CssUserFocusPointer extends CssProperty {

    CssValue userfocuspointer;

    static CssIdent auto = new CssIdent("auto");
    static CssIdent normal = new CssIdent("normal");
    static CssIdent selectall = new CssIdent("select-all");
    static CssIdent selectbefore = new CssIdent("select-before");
    static CssIdent selectafter = new CssIdent("select-after");
    static CssIdent selectsame = new CssIdent("select-same");
    static CssIdent selectmenu = new CssIdent("select-menu");

    /**
     * Create a new CssUserFocusPointer
     */
    public CssUserFocusPointer() {
	userfocuspointer = auto;
    }

    /**
     * Create a new CssUserFocusPointer
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssUserFocusPointer(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(auto)) {
	    userfocuspointer = auto;
	    expression.next();
	}
	else if (val.equals(normal)) {
	    userfocuspointer = normal;
	    expression.next();
	}
	else if (val.equals(selectall)) {
	    userfocuspointer = selectall;
	    expression.next();
	}
	else if (val.equals(selectbefore)) {
	    userfocuspointer = selectbefore;
	    expression.next();
	}
	else if (val.equals(selectafter)) {
	    userfocuspointer = selectafter;
	    expression.next();
	}
	else if (val.equals(selectsame)) {
	    userfocuspointer = selectsame;
	    expression.next();
	}
	else if (val.equals(selectmenu)) {
	    userfocuspointer = selectmenu;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    userfocuspointer = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssUserFocusPointer(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssUserFocusPointer != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssUserFocusPointer = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getUserFocusPointer();
	}
	else {
	    return ((Css3Style) style).cssUserFocusPointer;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssUserFocusPointer &&
		userfocuspointer.equals(((CssUserFocusPointer) property).userfocuspointer));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "user-focus-pointer";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return userfocuspointer;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return userfocuspointer.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return userfocuspointer.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return userfocuspointer == auto;
    }

}


