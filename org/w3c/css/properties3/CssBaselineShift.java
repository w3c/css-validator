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
 *  <EM>Value:</EM> baseline || sub || super || &lt;percentage&gt; || &lt;length&gt; || inherit<BR>
 *  <EM>Initial:</EM>baseline<BR>
 *  <EM>Applies to:</EM>inline-level elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>refers to the 'line-height' of the element<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  The 'baseline-shift' property allows repositioning of the dominant-baseline
 *  relative to the dominant-baseline. The shifted object might be a sub- or 
 *  superscript. Within the shifted object, the whole baseline table is offset;
 *  not just a single baseline. For sub- and superscript, the amount of offset
 *  is determined from the nominal font of the parent.
 */

public class CssBaselineShift extends CssProperty {

    CssValue baselineshift;
    
    static CssIdent baseline = new CssIdent("baseline");
    static CssIdent sub = new CssIdent("sub");
    static CssIdent sup = new CssIdent("super");

    /**
     * Create a new CssBaselineShift
     */
    public CssBaselineShift() {
	baselineshift = baseline;
    }

    /**
     * Create a new CssBaselineShift
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssBaselineShift(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    baselineshift = inherit;
	    expression.next();
	}
	else if (val.equals(baseline)) {
	    baselineshift = baseline;
	    expression.next();
	}
	else if (val.equals(sub)) {
	    baselineshift = sub;
	    expression.next();
	}
	else if (val.equals(sup)) {
	    baselineshift = sup;
	    expression.next();
	}
	else if (val instanceof CssPercentage) {
	    baselineshift = val;
	    expression.next();
	}
	else if (val instanceof CssLength) {
	    baselineshift = val;
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
	if (((Css3Style) style).cssBaselineShift != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBaselineShift = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getBaselineShift();
	}
	else {
	    return ((Css3Style) style).cssBaselineShift;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBaselineShift &&
		baselineshift.equals(((CssBaselineShift) property).baselineshift));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "baseline-shift";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return baselineshift;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return baselineshift.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return baselineshift.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return baselineshift == baseline;
    }

}
