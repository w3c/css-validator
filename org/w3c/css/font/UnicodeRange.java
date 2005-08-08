//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.font;

import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssUnicodeRange;
import org.w3c.css.values.CssValue;

/**
 */
public class UnicodeRange extends CssProperty {
    
    Vector values = new Vector();
    
    /**
     * Create a new UnicodeRange
     */
    public UnicodeRange() {
	// nothing to do
    }
    
    /**
     * Creates a new UnicodeRange
     *
     * @param expression the unicode range
     * @exception InvalidParamException values are incorrect
     */  
    public UnicodeRange(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	char op = expression.getOperator();
	CssValue val = expression.getValue();
	setByUser();

	do {
	    if (val instanceof CssUnicodeRange) {
		// nothing
	    } else {
		throw new InvalidParamException("value", expression.getValue(), 
						getPropertyName(), ac);
	    }
	    values.addElement(val);
	    op = expression.getOperator();
	    expression.next();
	} while (op == CssOperator.COMMA);
	
    }
    
    public UnicodeRange(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	return values.elementAt(0);
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {  
	String ret ="";
	int i = 0;

	while (i < values.size()) {
	    ret += ", " + values.elementAt(i);
	    i++;
	}

	return ret.substring(2);
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "unicode-range";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.unicodeRange != null) {
	    style0.addRedefinitionWarning(ac, this);
	}
	style0.unicodeRange = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getFaceUnicodeRange();
	} else {
	    return ((Css2Style) style).unicodeRange;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	// @@TODO
	return false;
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return false;
    }
    
}
