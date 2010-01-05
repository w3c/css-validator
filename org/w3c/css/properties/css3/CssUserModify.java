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
 *  <EM>Value:</EM> read-only || read-write || write-only || inherit<BR>
 *  <EM>Initial:</EM>read-only<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:interactive
 *  <P>
 *  The purpose of this property is to allow finer control over which user
 *  interface elements are user modifiable. Input elements can be pre-filled
 *  in, with the user-modify property set to read-only so that the user cannot
 *  change them. This is useful for templates, or for update forms. The user
 *  can still activate a read-only element and copy content out of
 *  it, if that is appropriate for the content model of the element.
 *  This is different from making the element "user-input:disabled" because
 *  that would prevent the user from activating the element.
*/

public class CssUserModify extends CssProperty {

    CssValue um;
    static CssIdent readonly = new CssIdent("read-only");
    static CssIdent readwrite = new CssIdent("read-write");
    static CssIdent writeonly = new CssIdent("write-only");

    /**
     * Create a new CssUserModify
     */
    public CssUserModify() {
	um = readonly;
    }

    /**
     * Create a new CssUserModify
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssUserModify(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(readonly)) {
	    um = readonly;
	    expression.next();
	}
	else if (val.equals(readwrite)) {
	    um = readwrite;
	    expression.next();
	}
	else if (val.equals(writeonly)) {
	    um = writeonly;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    um = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssUserModify(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssUserModify != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssUserModify = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getUserModify();
	}
	else {
	    return ((Css3Style) style).cssUserModify;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssUserModify &&
		um.equals(((CssUserModify) property).um));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "user-modify";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return um;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return um.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return um.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return um == readonly;
    }

}

