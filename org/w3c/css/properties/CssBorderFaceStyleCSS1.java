//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 2.3  1997/09/09 08:50:28  plehegar
 * Added getStyle()
 *
 * Revision 2.2  1997/08/20 11:41:17  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:08  plehegar
 * Nothing
 *
 * Revision 1.2  1997/07/30 13:19:52  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/25 12:34:00  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * @version $Revision$
 */
public class CssBorderFaceStyleCSS1 {
    
    int value;
    
    private static String[] BORDERSTYLE = {
	"none", "hidden", "dotted", "dashed", "solid", "double", "groove", 
	"ridge", "inset", "outset", "inherit" };
    
    private static int[] hash_values;
    
    /**
     * Create a new CssBorderFaceStyleCSS1
     */
    public CssBorderFaceStyleCSS1() {
	// nothing to do
    }  
    
    /**
     * Create a new CssBorderFaceStyleCSS1 with an another CssBorderFaceStyle
     *
     * @param another An another side.
     */
    public CssBorderFaceStyleCSS1(CssBorderFaceStyleCSS1 another) {
	value = another.value;
    }
    
    /**
     * Create a new CssBorderFaceStyleCSS1
     *
     * @param expression The expression for this face
     * @exception InvalidParamException The expression is incorrect
     */  
    public CssBorderFaceStyleCSS1(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	
	CssValue val = expression.getValue();
	
	if (val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < BORDERSTYLE.length; i++)
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	}
	
	throw new InvalidParamException("value", val.toString(), "style", ac);
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == (BORDERSTYLE.length - 1);
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return BORDERSTYLE[value];
    }
    
    /**
     * Returns the value
     */
    public String getStyle() {
	return BORDERSTYLE[value];
    }
    
    /**
     * Compares two side for equality.
     *
     * @param value The another side.
     */  
    public boolean equals(CssBorderFaceStyleCSS1 style) {
	return value == style.value;
    }
    
    static {
	hash_values = new int[BORDERSTYLE.length];
	for (int i=0; i<BORDERSTYLE.length; i++)
	    hash_values[i] = BORDERSTYLE[i].hashCode();
    }
    
}
