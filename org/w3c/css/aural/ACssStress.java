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
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssIdent;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;


/**
 * <H3> &nbsp;&nbsp; 'stress'</H3>
 *
 * <P>
 * <EM>Value: </EM>&lt;number&gt;|inherit<BR>
 * <em>Initial:</EM> 50%<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> yes<BR>
 * <EM>Percentage values:</EM> Relative to...
 *
 *
 * <p>Specifies the level of stress (assertiveness or emphasis) of the
 * speaking voice.  English is a <strong>stressed</strong> language, and
 * different parts of a sentence are assigned primary, secondary or
 * tertiary stress. The value of property 'stress' controls the amount of
 * inflection that results from these stress markers.  
 *
 * <P>Increasing the value of this property results in the speech being
 * more strongly inflected.  It is in a sense dual to property
 * <em>:pitch-range</em> and is provided to allow developers to exploit
 * higher-end auditory displays.
 *
 * <p class=comment>Combine 'pitch-range' and 'stress' into one property
 * 'inflection'?</p>
 *
 *
 * @version $Revision$
 */
public class ACssStress extends ACssProperty {
    
    CssValue value;
    
    static CssValue DefaultValue = new CssNumber(null, 50);

    /**
     * Create a new ACssStress
     */
    public ACssStress() {
	value = DefaultValue;
    }  
    
    /**
     * Creates a new ACssStress
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public ACssStress(ApplContext ac, CssExpression expression) throws InvalidParamException {
	this();
	CssValue val = expression.getValue();
	int index;
	setByUser();

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	} else if (val instanceof CssNumber) {
	    float f = ((CssNumber) val).getValue();
	    if ((f < 0) || (f > 100)) {		
		throw new InvalidParamException("range", null, ac);
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
	return "stress";
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
	if (((ACssStyle) style).acssStress != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssStress = this;
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof ACssStress && 
		value.equals(((ACssStress) property).value));
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getStress();
	} else {
	    return ((ACssStyle) style).acssStress;
	}
    }
    
}

