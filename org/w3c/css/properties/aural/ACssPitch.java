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
 * Revision 2.1  1997/08/29 13:11:50  plehegar
 * Updated
 *
 * Revision 1.1  1997/08/22 18:11:54  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFrequency;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  &nbsp;&nbsp; 'pitch' <span>(or 'average pitch'
 * ?, what is the relationship with voice-family?)</span>
 *
 * <P>
 * <EM>Value: </EM>&lt;hertz&gt; | x-low | low | medium | high | x-high<BR>
 * <EM>Initial:</EM> medium<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> yes<BR>
 * <EM>Percentage values:</EM> NA
 *
 * <p>Specifies the average pitch of the speaking voice in hertz (Hz).
 *
 * @version $Revision$
 */
public class ACssPitch extends ACssProperty {
    
    CssValue value;
    

    private static int[] hash_values;
    
    private static String[] PITCH = { "x-low", "low", "medium", 
				      "high", "x-high" };
    
    private static CssIdent defaultValue = new CssIdent(PITCH[2]);

    /**
     * Create a new ACssPitch
     */
    public ACssPitch() {
	value = defaultValue;
    }  
    
    /**
     * Creates a new ACssPitch
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public ACssPitch(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this();
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	
	setByUser();

	if (val.equals(inherit)) {
	    val = inherit;
	} else if (val instanceof CssIdent) {
	    value = checkIdent(ac,(CssIdent) val);
	} else if (val instanceof CssFrequency) {
	    value = val;
	} else {
	    throw new InvalidParamException("value", 
					    expression.getValue().toString(), 
					    getPropertyName(), ac);
	}

	expression.next();
    }
    
    public ACssPitch(ApplContext ac, CssExpression expression)
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
     * Returns some usable value of this property...
     */
    public int getValue() { // vm
	return ((Float) value.get()).intValue();
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "pitch";
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
	if (((ACssStyle) style).acssPitch != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssPitch = this;
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof ACssPitch && 
		value.equals(((ACssPitch) property).value));
    }
    
    private CssIdent checkIdent(ApplContext ac, CssIdent ident) throws InvalidParamException {
	int hash = ident.hashCode();
	for (int i = 0; i < PITCH.length; i++) {
	    if (hash_values[i] == hash) {
		return ident;
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
	    return ((ACssStyle) style).getPitch();
	} else {
	    return ((ACssStyle) style).acssPitch;
	}
    }
    
    static {
	hash_values = new int[PITCH.length];
	for (int i = 0; i < PITCH.length; i++)
	    hash_values[i] = PITCH[i].hashCode();
    }
}

