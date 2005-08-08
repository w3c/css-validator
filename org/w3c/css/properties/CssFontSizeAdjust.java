//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:17:43  plehegar
 * New
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 *
 * @see CssFont
 * @version $Revision$ 
 */
public class CssFontSizeAdjust extends CssProperty implements CssFontConstant {
    
    CssValue value;
    
    private static CssIdent none = new CssIdent("none");

    /**
     * Create a new CssFontSize-Adjust
     */
    public CssFontSizeAdjust() {
	value = none;
    }
    
    /**
     * Creates a new CssFontSize-Adjust
     *
     * @param expression the font size-adjust
     * @exception InvalidParamException Values are incorrect
     */  
    public CssFontSizeAdjust(ApplContext ac, CssExpression expression,
	    boolean check) 
	throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	setByUser();

	if (val.equals(none)) {
	    value = none;
	} else if (val.equals(inherit)) {
	    value = inherit;
	} else if (val instanceof CssNumber) {
	    value = val;
	} else {
	    throw new InvalidParamException("value", expression.getValue(), 
					    getPropertyName(), ac);
	}
	
	expression.next();
    }
    
    public CssFontSizeAdjust(ApplContext ac, CssExpression expression)
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
	return "font-size-adjust";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssFontSizeAdjust != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssFontSizeAdjust = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getFontSizeAdjust();
	} else {
	    return ((Css1Style) style).cssFontSizeAdjust;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssFontSizeAdjust && 
		((CssFontSizeAdjust) property).value.equals(value));
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == none;
    }
}
