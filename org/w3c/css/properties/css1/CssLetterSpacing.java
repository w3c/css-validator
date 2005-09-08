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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'letter-spacing'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> normal | &lt;length&gt; <BR>
 *   <EM>Initial:</EM> normal<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P> The length unit indicates an addition to the default space between
 *   characters.  Values can be negative, but there may be
 *   implementation-specific limits.  The UA is free to select the exact spacing
 *   algorithm. The letter spacing may also be influenced by justification
 *   (which is a value of the 'align' property).
 *   <PRE>
 *   BLOCKQUOTE { letter-spacing: 0.1em }
 * </PRE>
 *   <P>
 *   Here, the letter-spacing between each character in 'BLOCKQUOTE' elements
 *   would be increased by '0.1em'.
 *   <P>
 *   With a value of 'normal', the UAs may change the space between letters to
 *   justify text. This will not happen if 'letter-spacing' is explicitly set
 *   to a &lt;length&gt; value:
 *   <PRE>
 *   BLOCKQUOTE { letter-spacing: 0 }
 *   BLOCKQUOTE { letter-spacing: 0cm }
 * </PRE>
 *   <P>
 *   When the resultant space between two letters is not the same as the default
 *   space, UAs should not use ligatures.
 *
 * @version $Revision$ 
 */
public class CssLetterSpacing extends CssProperty {
    
    CssValue length;
    static CssIdent normal = new CssIdent("normal");

    /**
     * Create a new CssLetterSpacing
     */
    public CssLetterSpacing() {
	length = normal;
    }  
    
    /**
     * Create a new CssLetterSpacing
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssLetterSpacing(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	setByUser();
	
	CssValue val = expression.getValue();
	if (val instanceof CssLength) {
	    length = (CssLength) val;
	    expression.next();
	} else if (val instanceof CssNumber) {
	    length = ((CssNumber) val).getLength();
	    expression.next();
	} else if (val.equals(inherit)) {
	    length = inherit;
	    expression.next();
	} else if (val.equals(normal)) {
	    length = normal;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", 
					    expression.getValue(), 
					    getPropertyName(), ac);
	}
    }
    
    public CssLetterSpacing(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return length;
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "letter-spacing";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return length == inherit;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return length.toString();
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssLetterSpacing != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssLetterSpacing = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getLetterSpacing();
	} else {
	    return ((Css1Style) style).cssLetterSpacing;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssLetterSpacing && 
		length.equals(((CssLetterSpacing) property).length));
    }
    
}
