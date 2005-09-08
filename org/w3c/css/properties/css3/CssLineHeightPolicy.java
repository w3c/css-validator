//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     <A NAME="clear">5.5.26 &nbsp;&nbsp; 'clear'</A>
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> none | left | right | both<BR>
 *   <EM>Initial:</EM> none<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P> This property specifies if an element allows floating elements on its
 *   sides.  More specifically, the value of this property lists the sides where
 *   floating elements are not accepted. With 'clear' set to 'left', an element
 *   will be moved below any floating element on the left side. With 'clear' set
 *   to 'none', floating elements are allowed on all sides. Example:
 *   <PRE>
 *   H1 { clear: left }
 *  </PRE>
 *
 * @version $Revision$ */
public class CssLineHeightPolicy extends CssProperty {
    
    CssValue value;

    private CssIdent normal = new CssIdent("normal");
    private CssIdent stack = new CssIdent("stack");
    private CssIdent enforce = new CssIdent("enforce");
    private CssIdent snap = new CssIdent("snap");
    
    /**
     * Create a new CssLineHeightPolicy
     */
    public CssLineHeightPolicy() {
	// nothing to do
    }  
    
    /**
     * Create a new CssLineHeightPolicy
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssLineHeightPolicy(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = expression.getValue();
	
	setByUser();

	if (val.equals(normal)) {
	    value = val;
	    expression.next();
	} else if (val.equals(stack)) {
	    value = val;
	    expression.next();
	} else if (val.equals(enforce)) {
	    value = val;
	    expression.next();
	} else if (val.equals(snap)) {
	    value = val;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(), 
					getPropertyName(), ac);
	}
    }
    
    public CssLineHeightPolicy(ApplContext ac, CssExpression expression)
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
	return "line-height-policy";
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
	if (style0.cssLineHeightPolicy != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssLineHeightPolicy = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getLineHeightPolicy();
	} else {
	    return ((Css3Style) style).cssLineHeightPolicy;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssLineHeightPolicy && 
		value == ((CssLineHeightPolicy) property).value);
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == normal;
    }
    
}
