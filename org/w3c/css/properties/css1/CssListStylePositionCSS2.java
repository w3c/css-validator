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
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:13:52  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:25  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:24  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:30:09  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:07  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/25 14:40:15  plehegar
 * Initial revision
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
 *      &nbsp;&nbsp; 'list-style-position'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> inside | outside<BR>
 *   <EM>Initial:</EM> outside<BR>
 *   <EM>Applies to:</EM> elements with 'display' value 'list-item'<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   The value of 'list-style-position' determines how the list-item marker is
 *   drawn with regard to the content. For a formatting example see
 * @version $Revision$
 */
public class CssListStylePositionCSS2 extends CssProperty 
    implements CssListStyleConstants {
    
    int value;
    
    /**
     * Create a new CssListStylePositionCSS2
     */
    public CssListStylePositionCSS2() {
	// nothing to do
    }  
    
    /**
     * Create a new CssListStylePositionCSS2
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssListStylePositionCSS2(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	
	setByUser();
	
	if ( val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < LISTSTYLEPOSITION.length; i++) {
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	    }
	}
	throw new InvalidParamException("value", val, getPropertyName(), ac);
    }
    
    public CssListStylePositionCSS2(ApplContext ac, CssExpression expression)
    	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return LISTSTYLEPOSITION[value];
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "list-style-position";
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == (LISTSTYLEPOSITION.length - 1);
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return LISTSTYLEPOSITION[value];
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssListStyleCSS2 cssListStyle = ((Css1Style) style).cssListStyleCSS2;
	if (cssListStyle.listStylePosition != null)
	    style.addRedefinitionWarning(ac, this);
	cssListStyle.listStylePosition = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getListStylePositionCSS2();
	} else {
	    return ((Css1Style) style).cssListStyleCSS2.listStylePosition;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssListStylePositionCSS2 && 
		((CssListStylePositionCSS2) property).value == value);
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == 0;
    }
    
    private static int[] hash_values;
    
    static {
	hash_values = new int[LISTSTYLEPOSITION.length];
	for (int i = 0; i < LISTSTYLEPOSITION.length; i++)
	    hash_values[i] = LISTSTYLEPOSITION[i].hashCode();
    }
}
