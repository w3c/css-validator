//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */

package org.w3c.css.table;

import org.w3c.css.properties.CssProperty;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;


/**
 */
public class BorderCollapse extends TableProperty {
    
    CssValue value;
    
    private static CssIdent collapse = new CssIdent("collapse");
    private static CssIdent separate = new CssIdent("separate");

    /**
     * Create a new BorderCollapse
     */  
    public BorderCollapse() {
	value = collapse;
    }
    
    /**
     * Creates a new CssBorderCollapse
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */  
    public BorderCollapse(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = expression.getValue();
	setByUser();

	if (val.equals(inherit)) {
	    value = inherit;
	} else if (val.equals(collapse)) {
	    value = collapse;
	} else if (val.equals(separate)) {
	    value = separate;
	} else {
	    throw new InvalidParamException("value", 
					    val.toString(), 
					    getPropertyName(), ac);
	}

	expression.next();
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
	return "border-collapse";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.borderCollapse != null) {
	    style.addRedefinitionWarning(ac, this);
	}
	style0.borderCollapse = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getBorderCollapse();
	} else {
	    return ((Css2Style) style).borderCollapse;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	if (value == null) {
	    return (property instanceof BorderCollapse && 
		    ((BorderCollapse) property).value == value);
	} else {
	    return (property instanceof BorderCollapse && 
		    ((BorderCollapse) property).value.equals(value));
	}
    }
    
}
