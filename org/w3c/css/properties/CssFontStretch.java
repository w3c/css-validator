//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *
 * @see CssFont
 * @version $Revision$ 
 */
public class CssFontStretch extends CssProperty implements CssFontConstant {
    
    int value;
    
    private static int[] hash_values;
    
    /**
     * Create a new CssFontStretch
     */
    public CssFontStretch() {
	// nothing to do
    }
    
    /**
     * Creates a new CssFontStretch
     *
     * @param expression the font stretch
     * @exception InvalidParamException Values are incorrect
     */  
    public CssFontStretch(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	setByUser();
	if (expression.getValue() instanceof CssIdent) {
	    int hash = expression.getValue().hashCode();
	    for (int i=0; i<hash_values.length; i++)
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	}
	
	throw new InvalidParamException("value", expression.getValue(), 
					getPropertyName(), ac);
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	return FONTSTRETCH[value];
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == FONTSTRETCH.length - 1;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return FONTSTRETCH[value];
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "font-stretch";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssFontStretch != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssFontStretch = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getFontStretch();
	} else {
	    return ((Css1Style) style).cssFontStretch;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssFontStretch && 
		((CssFontStretch) property).value == value);
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == 0;
    }
    
    static {
	hash_values = new int[FONTSTRETCH.length];
	for (int i=0;i<FONTSTRETCH.length;i++)
	    hash_values[i] = FONTSTRETCH[i].hashCode();
    }
}
