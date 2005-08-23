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
 * Revision 3.1  1997/08/29 13:13:50  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:23  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:20  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:30:06  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:03  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/23 21:35:29  plehegar
 * Initial revision
 *
 */
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
