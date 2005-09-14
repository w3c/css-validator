//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 */
public class CssClip extends CssProperty {

    CssValue value;

    private static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssClip
     */
    public CssClip() {
	value = auto;
    }

    /**
     * Create a new CssClip
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssClip(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();

	setByUser();
	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else if (val.equals(auto)) {
	    value = auto;
	    expression.next();
	} else if (val instanceof CssFunction) {
	    CssFunction shape = (CssFunction) val;
	    if (!shape.getName().equals("rect")) {
		throw new InvalidParamException("shape", expression.getValue(),
						getPropertyName(), ac);
	    }
	    CssExpression params = shape.getParameters();
	    int i = 0;
	    params.starts();
	    if (params.getCount() != 4) {
		throw new InvalidParamException("shape", params.getValue(),
						getPropertyName(), ac);
	    }

	    while (i < 4) {
		CssValue v = params.getValue();
		char op = params.getOperator();
		if (i != 3) {
		    if (op != CssOperator.COMMA) {
			throw new InvalidParamException("shape-separator",
							params.getValue(),
							getPropertyName(), ac);
		    }
		}
		if (v != null) {
		    isValidParameter(v, ac);
		    params.next();
		} else {
		    throw new InvalidParamException("shape",
						    params.getValue(),
						    getPropertyName(), ac);
		}
		i++;
	    }
	    params.starts();
	    value = val;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}

    }

    public CssClip(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "clip";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value.toString();
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssClip != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssClip = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getClip();
	} else {
	    return ((Css1Style) style).cssClip;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssClip &&
		value.equals(((CssClip) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == auto;
    }

    private void isValidParameter(CssValue val, ApplContext ac)
	    throws InvalidParamException {
	if (val instanceof CssNumber) {
	    //CssLength le = ((CssNumber) val).getLength();
	    return;
	} else if (auto.equals(val)
		   || (val instanceof CssLength)) {
	    return;
	}
	throw new InvalidParamException("shape",
					val,
					getPropertyName(), ac);
    }
}
