//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *  <P>
 *  <EM>Value:</EM> none || text || toggle || element || elements || all ||
 *  inherit<BR>
 *  <EM>Initial:</EM>read-only<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:interactive
 *  <P>
 *  This property controls the selection model and granularity of an element. 
 *  Additional selection models may be added in the future.
 */

public class CssUserSelect extends CssProperty {

    CssValue userselect;

    static CssIdent none = new CssIdent("none");
    static CssIdent text = new CssIdent("text");
    static CssIdent toggle = new CssIdent("toggle");
    static CssIdent element = new CssIdent("element");
    static CssIdent elements = new CssIdent("elements");
    static CssIdent all = new CssIdent("all");

    /** 
     * Create a new CssUserSelect
     */
    public CssUserSelect() {
	userselect = text;
    }

    /**
     * Create a new CssUserSelect
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssUserSelect(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();
	
	if (val.equals(none)) {
	    userselect = none;
	    expression.next();
	}
	else if (val.equals(toggle)) {
	    userselect = toggle;
	    expression.next();
	}
	else if (val.equals(element)) {
	    userselect = element;
	    expression.next();
	}
	else if (val.equals(elements)) {
	    userselect = element;
	    expression.next();
	}
	else if (val.equals(all)) {
	    userselect = all;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    userselect = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssUserSelect != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssUserSelect = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getUserSelect();
	}
	else {
	    return ((Css3Style) style).cssUserSelect;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssUserSelect &&
		userselect.equals(((CssUserSelect) property).userselect));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "user-select";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return userselect;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return userselect.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return userselect.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return userselect == text;
    }

}
