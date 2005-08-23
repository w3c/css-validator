//
// $Id$
// From Sijtsche Smeman (sijtsche@wisdom.nl)
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
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;


/**
 */
public class CssBackgroundSpacing extends CssProperty implements CssOperator {
    
    CssValue value;
    CssIdent initial = new CssIdent("initial");
    CssValue second;
    
    /**
     * Create a new CssBackgroundSpacing
     */
    public CssBackgroundSpacing() {
    }
    
    /**
     * Creates a new CssBackgroundSpacing
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */
    public CssBackgroundSpacing(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = expression.getValue();
	setByUser();
	
	if (val.equals(inherit)) {
	    value = inherit;
	} else if (val.equals(initial)) {
	    value = initial;
	} else if (val instanceof CssLength || val instanceof CssNumber) {
	    if (val instanceof CssNumber) {
		val = ((CssNumber) val).getLength();
	    }
	    value = val;
	    expression.next();
	    val = expression.getValue();
	    
	    if (val != null) {
		if (expression.getOperator() == SPACE) {
		    if (val instanceof CssNumber) {
			val = ((CssNumber) val).getLength();
		    }
		    second = val;
		    expression.next();
		} else {
		    // invalid operator
		}
	    }
	} else {
	    throw new InvalidParamException("value",
		    val.toString(),
		    getPropertyName(), ac);
	}
	
	expression.next();
    }
    
    public CssBackgroundSpacing(ApplContext ac, CssExpression expression)
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
	return "background-spacing";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssBackgroundSpacing != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBackgroundSpacing = this;
    }
    
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getCssBackgroundSpacing();
	}
	else {
	    return ((Css3Style) style).cssBackgroundSpacing;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBackgroundSpacing &&
		value.equals(((CssBackgroundSpacing) property).value));
    }
    
    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == new CssNumber(0);
    }
    
}
