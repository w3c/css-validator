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
import org.w3c.css.values.CssFunction;

/**
 *  <P>
 *  <EM>Value:</EM> none | <url> | attr(X) | down-attr(X) | up-attr(X) | inherit <BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:all
 *  <P>
 *  Specifies whether an element is the source of a link and where to find the URL 
 *  of the link target.
 */

public class CssLink extends CssProperty {

    CssValue link;

    static CssIdent none = new CssIdent("none");

    /**
     * Create a new CssLink
     */
    public CssLink() {
	link = none;
    }

    /**
     * Create a new CssLink
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssLink(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(none)) {
	    link = none;
	    expression.next();
	} else if (val.equals(inherit)) {
	    link = inherit;
	    expression.next();
	} else if (val instanceof CssFunction) {
	    CssFunction attr = (CssFunction) val;
	    CssExpression params = attr.getParameters();
	    CssValue v = params.getValue();
	    
	    if (attr.getName().equals("attr")) {		
		if ((params.getCount() != 1)
		    || !(v instanceof CssIdent)) {
		    throw new InvalidParamException("attr", 
						    params.getValue(), 
						    getPropertyName(), ac);
		}
	    } else if (attr.getName().equals("down-attr")) {
		if ((params.getCount() != 1)
		    || !(v instanceof CssIdent)) {
		    throw new InvalidParamException("function", 
						    params.getValue(), 
						    getPropertyName(), ac);
		}
	    } else if (attr.getName().equals("up-attr")) {
		if ((params.getCount() != 1)
		    || !(v instanceof CssIdent)) {
		    throw new InvalidParamException("function", 
						    params.getValue(), 
						    getPropertyName(), ac);
		}
	    }
	    else{
		throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
	    }
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssLink != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssLink = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getLink();
	}
	else {
	    return ((Css3Style) style).cssLink;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssLink &&
		link.equals(((CssLink) property).link));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "link";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return link;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return link.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return link.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (link == none);
    }

}
