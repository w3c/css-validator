//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.3  2005/08/08 13:18:12  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.2  2002/04/08 21:17:43  plehegar
 * New
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
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
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
public class CssClear extends CssProperty {
    
    int value;
    
    private static String[] CLEAR = { 
	"none", "left", "right", "both", "inherit" };
    private static int[] hash_values;
    
    /**
     * Create a new CssClear
     */
    public CssClear() {
	// nothing to do
    }  
    
    /**
     * Create a new CssClear
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssClear(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	
	setByUser();
	if ( val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < CLEAR.length; i++)
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	}
	throw new InvalidParamException("value", expression.getValue(), 
					getPropertyName(), ac);
    }
    
    public CssClear(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return CLEAR[value];
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "clear";
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == CLEAR.length - 1;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return CLEAR[value];
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssClear != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssClear = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getClear();
	} else {
	    return ((Css1Style) style).cssClear;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssClear && 
		value == ((CssClear) property).value);
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == 0;
    }
    
    static {
	hash_values = new int[CLEAR.length];
	for (int i = 0; i < CLEAR.length; i++)
	    hash_values[i] = CLEAR[i].hashCode();
    }
}
