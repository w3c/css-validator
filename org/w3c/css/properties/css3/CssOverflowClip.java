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
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> &lt; alphavalue&gt; || inherit<BR>
 *  <EM>Initial:</EM>1<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property sets the OverflowClip of an element.
 *  <PRE>
 *  H1 { OverflowClip: 0}
 *  </PRE>
 */

public class CssOverflowClip extends CssProperty implements CssOperator {

    String overflowclip = new String();
    ApplContext ac;
    CssIdent auto = new CssIdent("auto");
    CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssOverflowClip
     */
    public CssOverflowClip() {

    }

    /**
     * Create a new CssOverflowClip
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssOverflowClip(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this.ac = ac;
	setByUser(); // tell this property is set by the user
	CssValue val = expression.getValue();

	if (val instanceof CssFunction) {
	    CssFunction fun = (CssFunction) val;
	    CssExpression params = fun.getParameters();

	    CssValue v;

	    if (fun.getName().equals("rect")) {
		if (params.getCount() == 4) {

		    overflowclip = "rect(";

		    for (int i =0; i < 4; i++) {
			v = params.getValue();
			if (!(v instanceof CssLength || v instanceof CssPercentage) ) {
			    throw new InvalidParamException("value", params.getValue(),
				    getPropertyName(), ac);
			} else {
			    if (i == 0)
				overflowclip += v.toString();
			    else
				overflowclip += ", " + v.toString();
			}
		    }

		} else {
		    throw new InvalidParamException("value", params.getValue(),
			    getPropertyName(), ac);
		}
	    } else if (fun.getName().equals("inset-rect")) {

		if (params.getCount() == 4) {

		    overflowclip = "inset-rect(";

		    for (int i =0; i < 4; i++) {
			v = params.getValue();
			if (!(v instanceof CssLength || v instanceof CssPercentage) ) {
			    throw new InvalidParamException("value", params.getValue(),
				    getPropertyName(), ac);
			} else {
			    if (i == 0)
				overflowclip += v.toString();
			    else
				overflowclip += ", " + v.toString();
			}
		    }
		} else {
		    throw new InvalidParamException("value", params.getValue(),
			    getPropertyName(), ac);
		}

	    }
	    else throw new InvalidParamException("value", expression.getValue(),
		    getPropertyName(), ac);

	    overflowclip += ")";
	    expression.next();
	    return;
	}
	else if (val instanceof CssIdent) {
	    if (val.equals(inherit)) {
		overflowclip = "inherit";
		expression.next();
	    } else if (val.equals(auto)) {
		overflowclip = "auto";
		expression.next();
	    } else if (val.equals(initial)) {
		overflowclip = "initial";
		expression.next();
	    } else {
		throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	    }
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    public CssOverflowClip(ApplContext ac, CssExpression expression)
    	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssOverflowClip != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssOverflowClip = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getOverflowClip();
	} else {
	    return ((Css3Style) style).cssOverflowClip;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssOverflowClip &&
		overflowclip.equals( ((CssOverflowClip) property).overflowclip));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "overflow-clip";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return overflowclip;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return overflowclip.equals("inherit");
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return overflowclip;
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	CssNumber cssnum = new CssNumber(ac, (float) 1.0);
	return overflowclip == cssnum.toString();
    }

}

