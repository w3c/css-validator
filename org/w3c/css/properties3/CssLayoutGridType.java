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
 *  <EM>Value:</EM> loose || strict || fixed || inherit<BR>
 *  <EM>Initial:</EM>loose<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  Specifies the type of grid to use. Each grid type entails a different set 
 *  of rules for rendering contents when a grid is enabled and specified. 
 */

public class CssLayoutGridType extends CssProperty {
 
    CssValue type;

    static CssIdent loose = new CssIdent("loose");
    static CssIdent strict = new CssIdent("strict");
    static CssIdent fixed = new CssIdent("fixed");

    /**
     * Create a new CssLayoutGridType
     */
    public CssLayoutGridType() {
	type = loose;
    }

    /**
     * Create a new CssLayoutGridType
     * 
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssLayoutGridType(ApplContext ac, CssExpression expression) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(loose)) {
	    type = loose;
	    expression.next();
	}
	else if (val.equals(strict)) {
	    type = strict;
	    expression.next();
	}
	else if (val.equals(fixed)) {
	    type = fixed;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    type = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssLayoutGridType != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssLayoutGridType = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getLayoutGridType();
	}
	else {
	    return ((Css3Style) style).cssLayoutGridType;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssLayoutGridType &&
		type.equals(((CssLayoutGridType) property).type));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "layout-grid-type";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return type;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return type.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return type.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return type == loose;
    }

}
