//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 */
public class CssMaxHeightCSS3 extends CssProperty {

    CssValue value;

    private static CssIdent none = new CssIdent("none");
    private static CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssMaxHeightCSS3
     */
    public CssMaxHeightCSS3() {
	value = none;
    }

    /**
     * Create a new CssMaxHeightCSS3.
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssMaxHeightCSS3(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = expression.getValue();

	setByUser();

	if (val.equals(inherit)) {
	    value = inherit;
	} else if (val instanceof CssLength || val instanceof CssPercentage) {
	    float f = ((Float) val.get()).floatValue();
	    if (f < 0) {
		throw new InvalidParamException("negative-value",
						val.toString(), ac);
	    }
	    value = val;
	} else if (val.equals(none)) {
	    value = none;
	} else if (val.equals(initial)) {
	    value = initial;
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getLength();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}

	expression.next();
    }

    public CssMaxHeightCSS3(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property.
     */
    public Object get() {
	return value;
    }

    /**
     * Returns the name of this property.
     */
    public String getPropertyName() {
	return "max-height";
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
	Css3Style style0 = (Css3Style) style;
	if (style0.cssMaxHeightCSS3 != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssMaxHeightCSS3 = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getMaxHeightCSS3();
	} else {
	    return ((Css3Style) style).cssMaxHeightCSS3;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssMaxHeightCSS3
		&& value.equals(((CssMaxHeightCSS3) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == none;
    }

}
