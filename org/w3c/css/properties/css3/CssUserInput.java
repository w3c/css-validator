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
 *  <EM>Value:</EM> none || enabled || disabled || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:all
 *  <P>
 *  The purpose of this property is to allow the author to specify whether or
 *  not a user interface element will currently accept user input. Typically
 *  this will be used to enable or disable specific input elements in a form.
 *  UAs may interpret none as disabled for user interface related elements.
 */

public class CssUserInput extends CssProperty {

    CssValue userinput;
    static CssIdent none = new CssIdent("none");
    static CssIdent enabled = new CssIdent("enabled");
    static CssIdent disabled = new CssIdent("disabled");

    /**
     * Create a new CssUserInput
     */
    public CssUserInput() {
	userinput = none;
    }

    /**
     * Create a new CssUserInput
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssUserInput(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(none)) {
	    userinput = none;
	    expression.next();
	}
	else if (val.equals(enabled)) {
	    userinput = enabled;
	    expression.next();
	}
	else if (val.equals(disabled)) {
	    userinput = disabled;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    userinput = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssUserInput(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssUserInput != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssUserInput = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getUserInput();
	}
	else {
	    return ((Css3Style) style).cssUserInput;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssUserInput &&
		userinput.equals(((CssUserInput) property).userinput));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "user-input";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return userinput;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return userinput.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return userinput.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return userinput == none;
    }

}
