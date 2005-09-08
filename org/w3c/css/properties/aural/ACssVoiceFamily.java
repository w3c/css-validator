//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.aural;

import java.util.Enumeration;
import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssString;
import org.w3c.css.values.CssValue;

/** 
 * <H3>5.2 &nbsp;&nbsp;   'voice-family'</H3>
 * <P>
 * <EM>Value:</EM>  [[&lt;specific-voice&gt; | &lt;generic-voice&gt;],]* 
 * [&lt;specific-voice&gt; | &lt;generic-voice&gt;]<BR>
 * <EM>Initial:</EM> UA <BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> yes<BR>
 * <EM>Percentage values:</EM> NA
 *
 * <P>The value is a prioritized list of voice family names (compare 
 * with '<a
 * href="/pub/WWW/TR/REC-CSS1##font-family">font-family</a>'. Suggested
 * genric families: male, female, child.
 *
 * <P>Examples of specific voice families are: comedian, trinoids, carlos, lisa
 *
 * <p>Examples
 *
 * <pre>
 *   H1 { voice-family: announcer, male }
 *   P.part.romeo {  voice-family: romeo, male }
 *   P.part.juliet { voice-family: juliet, female }
 * </pre>
 *
 * <p class=comment>Should the properties of these family names be
 * described, using an @-rule, to allow better client-side matching (like
 * fonts). If so, what are the values that describe these voice families
 * in a way that is independent of speech synthesizer?
 *
 * @version $Revision$ 
 */
public class ACssVoiceFamily extends ACssProperty implements CssOperator {
    
    Vector family_name = new Vector();
    boolean inheritValue;
    
    static String[] genericFamily = { "male", "female", "child" };
    
    static int[] genericFamilyHash;

    boolean withSpace = false;
    
    /**
     * Create a new ACssVoiceFamily
     */
    public ACssVoiceFamily() {
	// depends on user agent
    }  
    
    /**
     * Create a new ACssVoiceFamily
     * @param value the voice name
     * @exception InvalidParamException The expression is incorrect
     */
    public ACssVoiceFamily(ApplContext ac, CssExpression value, boolean check)
    	throws InvalidParamException {
	boolean family = true;
	CssValue val = value.getValue();
	char op;
	//@@ and if name is already in the vector ?
	
	setByUser();
	if (val.equals(inherit)) {
	    if(value.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    inheritValue = true;
	    return;
	}
	
	while (family) {
	    val = value.getValue();
	    op = value.getOperator();
	    
	    if ((op != COMMA) && (op != SPACE)) {
		throw new InvalidParamException("operator", 
						(new Character(op)).toString(), ac);
	    }
	    
	    if(val != null && val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    
	    if (val instanceof CssString) {
		String familyName = null;
		if (op == COMMA) { // "helvetica", "roman"
		    familyName = trimToOneSpace(val.toString());
		    value.next();
		} else { // "helvetica" CssValue
		    familyName = trimToOneSpace(val.toString());
		    family = false;
		    value.next();
		}
		if (familyName.length() > 2) {
		    String tmp = familyName.substring(1, familyName.length()-1);
		    for (int i = 0; i < genericFamily.length; i++) {
			if (genericFamily[i].equals(tmp)) {
			    throw new InvalidParamException("generic-family.quote",
				    genericFamily[i],
				    getPropertyName(),
				    ac);
			}
		    }
		}
		family_name.addElement(familyName);
	    } else if (val instanceof CssIdent) {
		if (op == COMMA) {
		    family_name.addElement(val.toString());
		    value.next();
		} else {
		    CssValue next = value.getNextValue();
		    
		    if (next != null && next instanceof CssIdent) { 
			// @@ null and instanceof
			CssIdent New = new CssIdent(val.get() 
						    + " " + next.get());
			withSpace = true;
			value.remove();
			op = value.getOperator();
			value.remove();
			value.insert(New);
			value.setCurrentOperator(op);
		    } else {
			family_name.addElement(val.toString());
			value.next();
			family = false;
		    }
		}
	    } else {
		throw new InvalidParamException("value", val.toString(), 
						getPropertyName(), ac);
	    }
	}
	
    }    
    
    public ACssVoiceFamily(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns all voices name
     */  
    public Enumeration elements() {
	return family_name.elements();
    }
    
    /**
     * Returns the size
     */
    public int size() {
	return family_name.size();
    }
    
    /**
     * Returns the voice (null if no voice)
     */  
    public Object get() {
	if (family_name.size() == 0) {
	    return null;
	}
	return family_name.firstElement();
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
	return inheritValue;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {  
	if (inheritValue) {
	    return inherit.toString();
	} else {
	    
	    String r = "";
	    for (Enumeration e = elements(); e.hasMoreElements();)
		//		r += ", " + e.nextElement().toString();
		r += ", " + e.nextElement().toString();
	    if (r.length() < 3) {
		return null;
	    }
	    return r.substring(2);
	}
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "voice-family";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((ACssStyle) style).acssVoiceFamily != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssVoiceFamily = this;
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return false; //@@ FIXME
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getVoiceFamily();
	} else {
	    return ((ACssStyle) style).acssVoiceFamily;
	}
    }
    
    private static String trimToOneSpace(String name) {
	int count = name.length();
	char[] dst = new char[count];
	char[] src = new char[count];
	int index = -1;
	
	name.getChars(0, count, src, 0);
	for(int i=0; i < count; i++)
	    if ( i == 0 || ! Util.isWhiteSpace(src[i]) || 
		 ( Util.isWhiteSpace(src[i]) && !Util.isWhiteSpace(dst[index]) ) )
		dst[++index] = src[i];
	
	return new String(dst, 0, index+1);
    }
    
    /**
     * Returns true if this property contains a generic family name
     */  
    public boolean containsGenericFamily() {
	if (family_name.size() == 0) {
	    return true;
	} else {
	    for (Enumeration e = family_name.elements(); 
		 e.hasMoreElements();) {
		int hash = ((String) e.nextElement()).toLowerCase().hashCode();
		for (int i = 0; i < genericFamilyHash.length; i++) {
		    if (hash == genericFamilyHash[i])
			return true;
		}
	    }
	    return false;
	}
    }
    
    
    static {
	genericFamilyHash = new int[genericFamily.length];
	for (int i = 0; i < genericFamily.length; i++) {
	    genericFamilyHash[i] = genericFamily[i].hashCode();
	}
    }
}
