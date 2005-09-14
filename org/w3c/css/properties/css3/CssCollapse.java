//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssContent;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;


/**
 *  <P>
 *  <EM>Value:</EM> none | &lt;content&gt; | inherit <BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  There is one kind of implicit link that CSS can exploit and present as a hyperlink
 *  and that is the relation between an element and its children.
 */

public class CssCollapse extends CssProperty {

    CssValue collapse;

    static CssIdent none = new CssIdent("none");

    /**
     * Create a new CssCollapse
     */
    public CssCollapse() {
	collapse = none;
    }

    /**
     * Create a new CssCollapse
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssCollapse(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(none)) {
	    collapse = none;
	    expression.next();
	} else if (val.equals(inherit)) {
	    collapse = inherit;
	    expression.next();
	} else {
	    try {
		CssContent content = new CssContent(ac, expression);
		expression.next();
		return;
	    } catch (InvalidParamException ie) {
		throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
	    }
	}
    }

    public CssCollapse(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssCollapse != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssCollapse = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getCollapse();
	}
	else {
	    return ((Css3Style) style).cssCollapse;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssCollapse &&
		collapse.equals(((CssCollapse) property).collapse));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "collapse";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return collapse;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return collapse.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return collapse.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (collapse == none);
    }

}
