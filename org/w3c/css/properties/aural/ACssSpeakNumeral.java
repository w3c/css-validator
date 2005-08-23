//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.3  2005/08/08 13:18:03  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.2  2002/04/08 21:16:56  plehegar
 * New
 *
 */

package org.w3c.css.properties.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 * <H3> &nbsp;&nbsp 'speak-numeral'</H3>
 * <P>
 * <EM>Value: </EM> digits | continous | none<BR>
 * <EM>Initial:</EM> none<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> yes<BR>
 * <EM>Percentage values:</EM> NA
 *
 *
 * @version $Revision$
 */
public class ACssSpeakNumeral extends ACssProperty {
    
    CssValue value;
    
    private static CssIdent none = new CssIdent("none");
    private static CssIdent digits = new CssIdent("digits");
    private static CssIdent continuous = new CssIdent("continuous");

    /**
     * Create a new ACssSpeakNumeral
     */  
    public ACssSpeakNumeral() {
	value = none;
    }
    
    /**
     * Creates a new ACssSpeakNumeral
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */  
    public ACssSpeakNumeral(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	
	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	} else if (val.equals(none)) {
	    value = none;
	    expression.next();
	    return;
	} else if (val.equals(continuous)) {
	    value = continuous;
	    expression.next();
	    return;
	} else if (val.equals(digits)) {
	    value = digits;
	    expression.next();
	    return;
	}
	
	throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
    }
    
    public ACssSpeakNumeral(ApplContext ac, CssExpression expression)
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
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "speak-numeral";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((ACssStyle) style).acssSpeakNumeral != null)
	    ((ACssStyle) style).addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssSpeakNumeral = this;
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof ACssSpeakNumeral && 
		value.equals(((ACssSpeakNumeral) property).value));
    }
    
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getSpeakNumeral();
	} else {
	    return ((ACssStyle) style).acssSpeakNumeral;
	}
    }
    
}
