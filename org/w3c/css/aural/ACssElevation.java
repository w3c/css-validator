//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 */
package org.w3c.css.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssAngle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;


/**
 *
 * @version $Revision$
 */
public class ACssElevation extends ACssProperty {
    
    CssValue value;
    
    private static int[] hash_values;
    
    private static String[] ELEVATION = { "below", "level", "above",
					  "highter", "lower" };
    

    private static CssValue defaultValue = new CssIdent(ELEVATION[1]);
    /**
     * Create a new ACssElevation
     */
    public ACssElevation() {
	value = defaultValue;
    }  
    
    /**
     * Creates a new ACssElevation
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public ACssElevation(ApplContext ac, CssExpression expression) throws InvalidParamException {
	this();
	CssValue val = expression.getValue();
	int index;
	setByUser();

	if (val.equals(inherit)) {	    
	    value = inherit;
	    expression.next();
	    return;
	} else if (val instanceof CssIdent) {
	    value = checkIdent(ac, (CssIdent) val);
	    expression.next();
	    return;
	} else if (val instanceof CssAngle) {
	    float v = ((CssAngle) val).getDegree();
	    String unit = ((CssAngle) val).getUnit();
	    if (!unit.equals("deg")) {
		throw new InvalidParamException("degree", null, ac);
	    }
	    if (v > 90 || v < -90) {
		throw new InvalidParamException("elevation.range", null, ac);
	    }
	    value = val;
	    expression.next();
	    return;
	}

	throw new InvalidParamException("value", 
					expression.getValue().toString(), 
					getPropertyName(), ac);
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
	return "elevation";
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
	return value.equals(inherit);
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
	if (((ACssStyle) style).acssElevation != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssElevation = this;
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof ACssElevation && 
		value.equals(((ACssElevation) property).value));
    }
    
    private CssIdent checkIdent(ApplContext ac, CssIdent ident)
	throws InvalidParamException {
	int hash = ident.hashCode();
	for (int i = 0; i < ELEVATION.length; i++) {
	    if (hash_values[i] == hash) {
		return ident;
	    }
	}
	
	throw new InvalidParamException("value", ident.toString(), 
					getPropertyName(), ac);
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getElevation();
	} else {
	    return ((ACssStyle) style).acssElevation;
	}
    }
    
    static {
	hash_values = new int[ELEVATION.length];
	for (int i = 0; i < ELEVATION.length; i++)
	    hash_values[i] = ELEVATION[i].hashCode();
    }
}

