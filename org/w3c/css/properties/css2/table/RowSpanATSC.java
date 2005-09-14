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
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;


/**
 */
public class RowSpanATSC extends TableProperty {

    CssValue value;

    /**
     * Create a new RowSpanATSC
     */
    public RowSpanATSC() {
	value = new CssNumber(null, 1);
    }

    /**
     * Creates a new CssRowSpanATSC
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */
    public RowSpanATSC(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	setByUser();

	ac.getFrame().addWarning("atsc", val.toString());

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	} else if (val instanceof CssNumber) {
	    if (((CssNumber) val).isInteger()) {
		value = val;
		expression.next();
		return;
	    } else {
		throw new InvalidParamException("integer",
						val.toString(),
						getPropertyName(), ac);
	    }
	}

	throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
    }

    public RowSpanATSC(ApplContext ac, CssExpression expression)
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
	return value.toString();
    }


    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "row-span";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.rowSpanATSC != null) {
	    style.addRedefinitionWarning(ac, this);
	}
	style0.rowSpanATSC = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getRowSpanATSC();
	} else {
	    return ((Css2Style) style).rowSpanATSC;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	if (value == null) {
	    return (property instanceof RowSpanATSC &&
		    ((RowSpanATSC) property).value == value);
	} else {
	    return (property instanceof RowSpanATSC &&
		    ((RowSpanATSC) property).value.equals(value));
	}
    }

}
