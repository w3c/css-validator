//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.1  2002/05/08 09:30:52  dejong
 * CSS version 3 specific properties as in March 2002, all modules
 *
 * Revision 3.1  1997/08/29 13:13:43  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:20  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:13  plehegar
 * Nothing
 *
 */
package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties.CssProperty;

/**
 *   <H4>
 *     <A NAME="clear">5.5.26 &nbsp;&nbsp; 'clear'</A>
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> [block || inline || font || text || replaced || inline-box ] 
 *   | none | inherit <BR>
 *   <EM>Initial:</EM> block inline replaced<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>This property enumerates the aspects of an element that contribute to the 
 *   height of a line line box.</P>
 *
 * @version $Revision$ */
public class CssLineBoxContain extends CssProperty {
    
    CssValue value;

    private static String[] values = {
	"block", "inline", "font", "text", "replaced", "inline-box",
	"none", "inherit"
    };
    
    /**
     * Create a new CssLineBoxContain
     */
    public CssLineBoxContain() {
	// nothing to do
    }  
    
    /**
     * Create a new CssLineBoxContain
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssLineBoxContain(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = expression.getValue();
	
	setByUser();
	
	int i = 0;
	for (; i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		value = val;
		expression.next();
		break;
	    }
	}
	if (i == values.length) {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}

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
	return "line-box-contain";
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
	if (style0.cssLineBoxContain != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssLineBoxContain = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getLineBoxContain();
	} else {
	    return ((Css3Style) style).cssLineBoxContain;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssLineBoxContain && 
		value == ((CssLineBoxContain) property).value);
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return false;
    }
    
}
