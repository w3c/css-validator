//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 3.1  1997/08/29 13:14:06  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:30  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:37  plehegar
 * Nothing
 *
 * Revision 1.5  1997/08/06 17:30:23  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.4  1997/07/30 13:20:21  plehegar
 * Updated package
 *
 * Revision 1.3  1997/07/23 23:43:31  plehegar
 * bug fix none
 *
 * Revision 1.2  1997/07/23 22:51:20  plehegar
 * bug fix in getPropertyName()
 *
 * Revision 1.1  1997/07/23 22:49:33  plehegar
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
 *   <H4>
 *     <A NAME="text-transform">5.4.5 &nbsp;&nbsp; 'text-transform'</A>
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> capitalize | uppercase | lowercase | none<BR>
 *   <EM>Initial:</EM> none<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <DL>
 *     <DT>
 *       'capitalize'
 *     <DD>
 *       uppercases the first character of each word
 *     <DT>
 *       'uppercase'
 *     <DD>
 *       uppercases all letters of the element
 *     <DT>
 *       'lowercase'
 *     <DD>
 *       lowercases all letters of the element
 *     <DT>
 *       'none'
 *     <DD>
 *       neutralizes inherited value.
 *   </DL>
 *   <P>
 *   The actual transformation in each case is human language dependent. See
 *   <A href="#ref4">[4]</A> for ways to find the language of an element.
 *   <PRE>
 *   H1 { text-transform: uppercase }
 * </PRE>
 *   <P>
 *   The example above would put 'H1' elements in uppercase text.
 *
 * <P>
 * <A NAME="ref4">[4]</A> F Yergeau, G Nicol, G Adams, M D&uuml;rst:
 * "<A HREF="ftp://ietf.org/internet-drafts/draft-ietf-html-i18n-05.txt">Internationalization
 * of the Hypertext Markup Language</A>"
 * (ftp://ietf.org/internet-drafts/draft-ietf-html-i18n-05.txt).
 *
 * @version $Revision$
 */
public class CssTextTransform extends CssProperty 
        implements CssTextPropertiesConstants {
    
    /**
     * Create a new CssTextTransform
     */
    public CssTextTransform() {
    }  
    
    /**
     * Create a new CssTextTransform
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The value is incorrect
     */  
    public CssTextTransform(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	
	CssValue val = expression.getValue();
	int hash = val.hashCode();
	
	if (val instanceof CssIdent) {
	    for (int i = 0; i < TEXTTRANSFORM.length; i++) {
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	    }
	}
	throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return TEXTTRANSFORM[value];
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "text-transform";
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == (TEXTTRANSFORM.length - 1);
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return TEXTTRANSFORM[value];
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssTextTransform != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssTextTransform = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getTextTransform();
	} else {
	    return ((Css1Style) style).cssTextTransform;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextTransform && 
		value == ((CssTextTransform) property).value);
    }
    
    private int value;
    
    private static int[] hash_values;
    
    static {
	hash_values = new int[TEXTTRANSFORM.length];
	for (int i=0; i<TEXTTRANSFORM.length; i++)
	    hash_values[i] = TEXTTRANSFORM[i].hashCode();
    }
}
