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
 * Revision 2.3  1997/09/08 09:15:48  plehegar
 * Bug. Padding values cannot be negative
 *
 * Revision 2.2  1997/08/20 11:41:27  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:32  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:30:16  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:16  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/24 01:28:20  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.css1;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public abstract class CssPaddingSide extends CssProperty {
    
    CssValue value;
    
    /**
     * Create a new CssPaddingSide.
     */
    public CssPaddingSide() {
	value = new CssLength();
    }
    
    /**
     * Create a new CssPaddingSide with an another CssPaddingSide
     *
     * @param another An another side.
     */
    public CssPaddingSide(CssPaddingSide another) {
	value = another.value;
    }
    
    /**
     * Create a new CssPaddingSide
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssPaddingSide(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	
	setByUser();
	
	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else if (val instanceof CssLength || val instanceof CssPercentage) {
	    value = val;
	    float v = ((Float) val.get()).floatValue();
	    if (v < 0)
		throw new InvalidParamException("negative-value", 
						Float.toString(v), ac);
	    expression.next();
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getLength();
	    expression.next();
	} else {
	    throw new InvalidParamException("value", val.toString(), 
					    getPropertyName(), ac);
	}
    }
    
    public CssPaddingSide(ApplContext ac, CssExpression expression)
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
     * Returns the internal value
     */
    public CssValue getValue() {
	return value;
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
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return ((Float) value.get()).floatValue() == 0;
    }
}
