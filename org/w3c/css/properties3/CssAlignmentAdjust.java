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
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssLength;

/**
 *  <P>
 *  <EM>Value:</EM> auto || &lt;percentage&gt; || &lt;length&gt; || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>inline-level elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>refers to the 'line-height' of the element<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  The 'alignment-adjust' property allows more precise alignment of
 *  elements, such as graphics, that do not have a baseline-table 
 *  or lack the desired baseline in their baseline-table. With the
 *  'alignment-adjust' property, the position of the baseline
 *  identified by the 'alignment-baseline' can be explicitly determined.
 */

public class CssAlignmentAdjust extends CssProperty {

    CssValue alignadjust;
    
    CssIdent auto = new CssIdent("auto");
    
    /**
     * Create a new CssAlignmentAdjust
     */
    public CssAlignmentAdjust() {
	alignadjust = auto;
    }

    /** 
     * Create a new CssAlignmentAdjust
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssAlignmentAdjust(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    alignadjust = inherit;
	    expression.next();
	}
	else if (val.equals(auto)) {
	    alignadjust = auto;
	    expression.next();
	}
	else if (val instanceof CssPercentage) {
	    alignadjust = val;
	    expression.next();
	}
	else if (val instanceof CssLength) {
	    alignadjust = val;
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
	if (((Css3Style) style).cssAlignmentAdjust != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssAlignmentAdjust = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getAlignmentAdjust();
	}
	else {
	    return ((Css3Style) style).cssAlignmentAdjust;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssAlignmentAdjust &&
		alignadjust.equals(((CssAlignmentAdjust) property).alignadjust));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "alignment-adjust";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return alignadjust;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return alignadjust.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return alignadjust.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return alignadjust == auto;
    }

}
