//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/**
 *
 */
package org.w3c.css.user;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * @version $Revision$
 */
public class OutlineStyleATSC extends UserProperty {
    
    int value = -1;
    
    static String[] BORDERSTYLE = {
	"none", "dotted", "dashed", "solid", "double", "groove", 
	"ridge", "inset", "outset", "inherit" };
    
    private static int[] hash_values;
    
    /**
     * Create a new OutlineStyleATSC
     */
    public OutlineStyleATSC() {
	// nothing to do
    }  
    
    /**
     * Create a new OutlineStyleATSC
     *
     * @param expression The expression for this face
     * @exception InvalidParamException The expression is incorrect
     */  
    public OutlineStyleATSC(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	
	CssValue val = expression.getValue();
	setByUser();

	ac.getFrame().addWarning("atsc", val.toString());

	if (val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < BORDERSTYLE.length; i++) {
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	    }
	}
	
	throw new InvalidParamException("value", val.toString(), 
					getPropertyName(), ac);
    }
    
    /**
     * Returns the internal value
     */  
    public Object get() {
	return BORDERSTYLE[value];
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
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	OutlineATSC outline = ((Css2Style) style).outlineATSC;
	if (outline.style != null) {
	    style.addRedefinitionWarning(ac, this);
	}
	outline.style = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getOutlineStyleATSC();
	} else {
	    return ((Css2Style) style).outlineATSC.style;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return ((property instanceof OutlineStyleATSC) 
		&& (value == ((OutlineStyleATSC) property).value));
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "outline-style";
    }
    
    static {
	hash_values = new int[BORDERSTYLE.length];
	for (int i=0; i<BORDERSTYLE.length; i++)
	    hash_values[i] = BORDERSTYLE[i].hashCode();
    }
    
}
