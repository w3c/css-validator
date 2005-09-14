//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> clip || ellipsis || ellipsis-word || ellipsis-path || fade || inherit<BR>
 *  <EM>Initial:</EM>clip<BR>
 *  <EM>Applies to:</EM>all block-level elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property controls whether or not text wraps when it reaches the flow
 *  edge of its containing block box.
 */

public class CssTextAfterOverflow extends CssProperty {

    CssValue overflow;
    ApplContext ac;

    static CssIdent clip = new CssIdent("clip");
    static CssIdent ellipsis = new CssIdent("ellipsis");
    static CssIdent ellipsisword = new CssIdent("ellipsis-word");
    static CssIdent ellipsispath = new CssIdent("ellipsis-path");
    static CssIdent fade = new CssIdent("fade");

    /**
     * Create a new CssTextAfterOverflow
     */
    public CssTextAfterOverflow() {
	overflow = clip;
    }

    /**
     * Create a new CssTextAfterOverflow
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextAfterOverflow(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(clip)) {
	    overflow = clip;
	    expression.next();
	}
	else if (val.equals(ellipsis)) {
	    overflow = ellipsis;
	    expression.next();
	}
	else if (val.equals(ellipsisword)) {
	    overflow = ellipsisword;
	    expression.next();
	}
	else if (val.equals(ellipsispath)) {
	    overflow = ellipsispath;
	    expression.next();
	}
        else if (val.equals(fade)) {
            overflow = fade;
            expression.next();
        }
	else if (val.equals(inherit)) {
	    overflow = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    public CssTextAfterOverflow(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextAfterOverflow != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextAfterOverflow = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextAfterOverflow();
	}
	else {
	    return ((Css3Style) style).cssTextAfterOverflow;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextAfterOverflow &&
		overflow.equals(((CssTextAfterOverflow) property).overflow));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-after-overflow";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return overflow;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return overflow.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return overflow.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return overflow == clip;
    }

}
