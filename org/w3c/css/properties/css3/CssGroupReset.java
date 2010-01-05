//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> <identifier>+ || none || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:all
 *  <P>
 *  The groups defined by the group-reset property are used as values
 *  for the toggle-group property.
 */
public class CssGroupReset extends CssProperty {

    CssValue groupreset;
    Vector values = new Vector();

    static CssIdent none = new CssIdent("none");

    /**
     * Create a new CssGroupReset
     */
    public CssGroupReset() {
	groupreset = none;
    }

    /**
     * Create a new CssGroupReset
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssGroupReset(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();
	CssToggleGroup tg = new CssToggleGroup();
	char op = expression.getOperator();
	int counter = 0;

	if (val.equals(none)) {
	    groupreset = none;
	    expression.next();
	    return;
	}
	if (val.equals(inherit)) {
	    groupreset = inherit;
	    expression.next();
	    return;
	}

	while ((op == CssOperator.SPACE)
	       && (counter < expression.getCount())) {

	    if (tg.groups.containsKey(val.toString())) {
		values.addElement(val.toString());
		expression.next();
		val = expression.getValue();
		op = expression.getOperator();
	    }
	    else {
		throw new InvalidParamException("nogroup", expression.getValue(),
						getPropertyName(), ac);
	    }

	    counter++;
	}
    }

    public CssGroupReset(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssGroupReset != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssGroupReset = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getGroupReset();
	} else {
	    return ((Css3Style) style).cssGroupReset;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssGroupReset &&
                groupreset.equals( ((CssGroupReset) property).groupreset));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "group-reset";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return groupreset;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return groupreset.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	if (groupreset != null)
	    return groupreset.toString();
	else
	    return values.firstElement().toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return groupreset == none;
    }

}

