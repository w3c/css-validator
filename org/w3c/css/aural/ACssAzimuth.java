//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:16:56  plehegar
 * New
 *
 *
 */
package org.w3c.css.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssAngle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 *
 * @version $Revision$
 */
public class ACssAzimuth extends ACssProperty {
    
    CssValue value;

    boolean isBehind;
    
    private static int[] hash_values;
    
    private static String[] AZIMUTH = { "left-side", "far-left", "left",
					"center-left", "center", "center-right",
					"right", "far-right", "right-side" };
    
    private static CssIdent defaultIdentValue = new CssIdent(AZIMUTH[4]);
    private static CssIdent behind = new CssIdent("behind");
    private static CssIdent leftwards = new CssIdent("leftwards");
    private static CssIdent rightwards = new CssIdent("rightwards");
    
    /**
     * Create a new ACssAzimuth
     */
    public ACssAzimuth() {
	value = defaultIdentValue;
    }  
    
    /**
     * Creates a new ACssAzimuth
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Expressions are incorrect
     */  
    public ACssAzimuth(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {
			
	this();
	
	if(check && expression.getCount() > 2) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	int index;
	
	setByUser();
	
	if (val.equals(leftwards)) {
	    if(check && expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    value = leftwards;
	    expression.next();
	    return;
	} if (val.equals(inherit)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    value = inherit;
	    expression.next();
	    return;
	} else if (val.equals(rightwards)) {
	    if(check && expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    value = rightwards;
	    expression.next();
	    return;
	} else if (val.equals(behind)) {	    
	    isBehind = true;
	    expression.next();
	    CssValue valnext = expression.getValue();
	    if (valnext == null) {
		// behind == behind center
		value = null;
		return;
	    } else if (valnext instanceof CssIdent) {
		value = checkIdent(ac, (CssIdent) valnext);
		expression.next();
		return;
	    }
	} else if (val instanceof CssIdent) {
	    expression.next();
	    CssValue valnext = expression.getValue();
	    if (valnext == null) {
		// left
		value = checkIdent(ac, (CssIdent) val);
		return;
	    } else if (valnext.equals(behind)) {
		// left behind
		value = checkIdent(ac, (CssIdent) val);
		isBehind = true;
		expression.next();
		return;
	    }
	} else if (val instanceof CssAngle) {
	    if(check && expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    CssAngle angle = (CssAngle) val;
	    if (!angle.isDegree()) {
		throw new InvalidParamException("degree", null, ac);
	    }
	    value = val;
	    expression.next();
	    return;
	} else if (val instanceof CssNumber) {
	    if(check && expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    value = ((CssNumber) val).getAngle();
	    expression.next();
	    return;
	}
	
	throw new InvalidParamException("value", 
					expression.getValue().toString(), 
					getPropertyName(), ac);
    }
    
    public ACssAzimuth(ApplContext ac, CssExpression expression)
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
	return "azimuth";
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
	if (isBehind) {
	    if (value != null) {
		return behind.toString() + " " + value.toString();
	    } else {
		return behind.toString();
	    }
	} else {
	    return value.toString();
	}
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((ACssStyle) style).acssAzimuth != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssAzimuth = this;
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof ACssAzimuth && 
		value.equals(((ACssAzimuth) property).value));
    }
    
    private CssIdent checkIdent(ApplContext ac, CssIdent ident) 
	throws InvalidParamException {
	
	int hash = ident.hashCode();
	
	for (int i = 0; i < AZIMUTH.length; i++) {
	    if (hash_values[i] == hash) {
		return ident;
	    }
	}	
	throw new InvalidParamException("value", 
					ident.toString(), 
					getPropertyName(), ac);
    }
    
    /** @deprecated */
    private Float ValueOfIdent(ApplContext ac, CssIdent ident, boolean b) 
	throws InvalidParamException {
	
	int hash = ident.hashCode();
	
	for (int i = 0; i < AZIMUTH.length; i++) {
	    if (hash_values[i] == hash) {
		if (b) {
		    return ACssProperties.getValue(this, 
						   behind.toString() + "." + AZIMUTH[i]);
		} else {
		    return ACssProperties.getValue(this, AZIMUTH[i]);
		}
	    }
	}
	
	throw new InvalidParamException("value", 
					ident.toString(), 
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
	    return ((ACssStyle) style).getAzimuth();
	} else {
	    return ((ACssStyle) style).acssAzimuth;
	}
    }
    
    static {
	hash_values = new int[AZIMUTH.length];
	for (int i = 0; i < AZIMUTH.length; i++)
	    hash_values[i] = AZIMUTH[i].hashCode();
    }
}

