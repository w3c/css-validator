//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */

package org.w3c.css.properties.css2.table;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;


/**
 */
public class BorderSpacingATSC extends TableProperty implements CssOperator {

    CssValue value;

    CssLength second;

    /**
     * Create a new BorderSpacingATSC
     */
    public BorderSpacingATSC() {
    }

    /**
     * Creates a new CssBorderSpacingATSC
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */
    public BorderSpacingATSC(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 2) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	CssLength le = getLength(val);
	setByUser();

	ac.getFrame().addWarning("atsc", val.toString());

	if (val.equals(inherit)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    value = inherit;
	} else if (le != null) {
	    value = le;
	    if (expression.getOperator() == SPACE) {
		expression.next();
		if(expression.getValue().equals(inherit)) {
		    throw new InvalidParamException("unrecognize", ac);
		}
		le = getLength(expression.getValue());
		if (le != null) {
		    second = le;
		} else {
		    return;
		}
	    }

	} else {
	    throw new InvalidParamException("value",
					    val.toString(),
					    getPropertyName(), ac);
	}

	expression.next();
    }

    public BorderSpacingATSC(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the current value
     */
    public Object get() {
	return value;
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
	if (second != null) {
	    return value + " " + second;
	} else {
	    return value.toString();
	}
    }


    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border-spacing";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.borderSpacingATSC != null) {
	    style.addRedefinitionWarning(ac, this);
	}
	style0.borderSpacingATSC = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getBorderSpacingATSC();
	} else {
	    return ((Css2Style) style).borderSpacingATSC;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	if (value == null) {
	    return (property instanceof BorderSpacingATSC &&
		    ((BorderSpacingATSC) property).value == value);
	} else {
	    return (property instanceof BorderSpacingATSC &&
		    ((BorderSpacingATSC) property).value.equals(value));
	}
    }

    CssLength getLength(CssValue val) throws InvalidParamException {
	if (val instanceof CssLength) {
	    return (CssLength) val;
	} else if (val instanceof CssNumber) {
	    return ((CssNumber) val).getLength();
	} else {
	    return null;
	}
    }

}
