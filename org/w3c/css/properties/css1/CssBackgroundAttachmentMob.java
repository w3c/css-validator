//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'background-attachment'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> scroll | fixed<BR>
 *   <EM>Initial:</EM> scroll<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   If a background image is specified, the value of 'background-attachment'
 *   determines if it is fixed with regard to the canvas or if it scrolls along
 *   with the content.
 *   <PRE>
 *   BODY { 
 *     background: red url(pendant.gif);
 *     background-repeat: repeat-y;
 *     background-attachment: fixed;
 *   }
 * </PRE>
 * @version $Revision$
 */
public class CssBackgroundAttachmentMob extends CssProperty 
    implements CssBackgroundConstants {
    
    int attachment;
    
    private static int[] hash_values;
    
    /**
     * Create a new CssBackgroundAttachmentMob
     */
    public CssBackgroundAttachmentMob() {
	// nothing to do
    }  
    
    /**
     * Creates a new CssBackgroundAttachmentMob
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssBackgroundAttachmentMob(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	setByUser();

	CssValue val = expression.getValue();
	
	if (val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i =0; i < ATTACHMENT.length; i++)
		if (hash_values[i] == hash) {
		    attachment = i;
		    expression.next();
		    return;
		}
	}
	
	throw new InvalidParamException("value", expression.getValue(), 
					getPropertyName(), ac);
    }
    
    public CssBackgroundAttachmentMob(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return ATTACHMENT[attachment];
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return attachment == 2;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return ATTACHMENT[attachment];
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "background-attachment";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBackgroundMob cssBackground = ((Css1Style) style).cssBackgroundMob;
	if (cssBackground.attachment != null)
	    style.addRedefinitionWarning(ac, this);
	cssBackground.attachment = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBackgroundAttachmentMob();
	} else {
	    return ((Css1Style) style).cssBackgroundMob.attachment;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssBackgroundAttachmentMob && 
		attachment == ((CssBackgroundAttachmentMob) property).attachment);
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return attachment == 0;
    }
    
    static {
	hash_values = new int[ATTACHMENT.length];
	for (int i = 0; i < ATTACHMENT.length; i++)
	    hash_values[i] = ATTACHMENT[i].hashCode();
    }
}
