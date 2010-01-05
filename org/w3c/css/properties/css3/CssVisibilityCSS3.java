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
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 */
public class CssVisibilityCSS3 extends CssProperty {

    CssValue value;

    private static CssIdent visible = new CssIdent("visible");
    private static CssIdent hidden = new CssIdent("hidden");
    private static CssIdent collapse = new CssIdent("collapse");
    private static CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssVisibilityCSS3
     */
    public CssVisibilityCSS3() {
	value = visible;
    }

    /**
     * Create a new CssVisibilityCSS3
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssVisibilityCSS3(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = expression.getValue();

	setByUser();
	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else if (val.equals(visible)) {
	    value = visible;
	    expression.next();
	} else if (val.equals(hidden)) {
	    value = hidden;
	    expression.next();
	} else if (val.equals(collapse)) {
	    value = collapse;
	    expression.next();
	} else if (val.equals(initial)) {
		value = initial;
		expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}

    }

    public CssVisibilityCSS3(ApplContext ac, CssExpression expression)
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
	return "visibility";
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
	if (style0.cssVisibilityCSS3 != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssVisibilityCSS3 = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getVisibilityCSS3();
	} else {
	    return ((Css3Style) style).cssVisibilityCSS3;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssVisibilityCSS3 &&
		value.equals(((CssVisibilityCSS3) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == visible;
    }

}
