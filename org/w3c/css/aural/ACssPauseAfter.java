//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 2.1  1997/08/29 13:11:50  plehegar
 * Updated
 *
 * Revision 1.7  1997/08/25 13:53:03  plehegar
 * Added getValue()
 *
 * Revision 1.6  1997/08/22 15:00:09  plehegar
 * Bugs
 *
 * Revision 1.5  1997/08/22 14:57:32  plehegar
 * Added getPropertyInStyle()
 *
 * Revision 1.4  1997/08/21 21:13:25  plehegar
 * Added time
 *
 * Revision 1.3  1997/08/21 14:34:56  vmallet
 * Minor modifications so we could compile it.
 *
 * Revision 1.2  1997/08/14 13:19:05  plehegar
 * Added ACssPauseAfter(ACssPauseBefore)
 *
 * Revision 1.1  1997/08/14 12:58:44  plehegar
 * Initial revision
 *
 */

package org.w3c.css.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssTime;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * &nbsp;&nbsp;  'pause-after'
 *
 * <P>
 * <EM>Value: </EM>&lt;time&gt;  | &lt;percentage&gt;<BR>
 * <EM>Initial:</EM> UA specific<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentage values:</EM> NA<BR>
 *
 * <P>This property specifies the pause after an element is spoken. Values are
 * specified the same way as 'pause-before'.
 *
 * @version $Revision$
 */
public class ACssPauseAfter extends ACssProperty {
    
    CssValue value;
    
    /**
     * Create a new ACssPauseAfter
     */  
    public ACssPauseAfter() {
	// Initial is User Agent Specific
	if (defaultValue == null) {
	    defaultValue = new CssTime(ACssProperties.getValue(this, "default"));
	}
	value = defaultValue;
    }
    
    /**
     * Create a new ACssPauseAfter
     */  
    public ACssPauseAfter(ACssPauseBefore pauseBefore) {
	value = pauseBefore.value;
    }
    
    /**
     * Creates a new ACssPauseAfter
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */  
    public ACssPauseAfter(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = expression.getValue();
	
	setByUser();
	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	} else if (val instanceof CssPercentage) {
	    float num = ((Float) val.get()).floatValue();
	    if (num < 0)
		throw new InvalidParamException("negative-value", val.toString(),
						ac);
	    value = val;
	    expression.next();
	    return;
	} else if (val instanceof CssTime) {
	    float num = ((Float) val.get()).floatValue();
	    if (num < 0) {
		throw new InvalidParamException("negative-value", val.toString(),
						ac);
	    }
	    value = val;
	    expression.next();
	    return;
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getTime();
	    expression.next();
	}
	
	throw new InvalidParamException("value", val.toString(), getPropertyName(),
					ac);
    }
    
    /**
     * Returns the current value
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
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (value != null)
	    return value.toString();
	else
	    return null;
    }
    
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "pause-after";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	ACssPause acssPause = ((ACssStyle) style).acssPause;
	if (acssPause.pauseAfter != null)
	    style.addRedefinitionWarning(ac, this);
	acssPause.pauseAfter = this;
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	if (value != null)
	    return (property instanceof ACssPauseAfter && 
		    value.equals(((ACssPauseAfter) property).value));
	else
	    return false;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getPauseAfter();
	} else {
	    return ((ACssStyle) style).acssPause.pauseAfter;
	}
    }
    
    private static CssTime defaultValue;
}
