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
import org.w3c.css.values.CssLength;

/**
 *  <P>
 *  <EM>Value:</EM> auto || &lt;length&gt; || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>inline elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>relative to line-width<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property controls the amount of space a run of text is to fill or 
 *  fit into. 
 */

public class CssTextFit extends CssProperty {

    CssValue fit;

    static CssIdent auto = new CssIdent("auto");    ;

    /**
     * Create a new CssTextFit
     */
    public CssTextFit() {
	fit = auto;
    }

    /**
     * Create a new CssTextFit
     *
     * @param expression The expression for this parameter
     * @exception InvalidParamException Incorrect value
     */
    public CssTextFit(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(auto)) {
	    fit = auto;
	    expression.next();
	}
	else if (val instanceof CssLength) {
	    fit = val;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    fit = inherit;
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
	if (((Css3Style) style).cssTextFit != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextFit = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextFit();
	}
	else {
	    return ((Css3Style) style).cssTextFit;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextFit &&
		fit.equals(((CssTextFit) property).fit));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-fit";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return fit;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return fit.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return fit.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return fit == auto;
    }

}
