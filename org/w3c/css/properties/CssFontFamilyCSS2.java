//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 3.1  1997/08/29 13:13:46  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:22  plehegar
 * Freeze
 *
 * Revision 1.1  1997/07/08 13:46:31  plehegar
 * Initial revision
 *
 */

package org.w3c.css.properties;

import java.util.Vector;
import java.util.Enumeration;
import org.w3c.css.util.Util;
import org.w3c.css.values.CssOperator;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssString;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/** 
 *   <H4>
 *     &nbsp;&nbsp; 'font-family'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> [[&lt;family-name&gt; | &lt;generic-family&gt;],]*
 *   [&lt;family-name&gt; | &lt;generic-family&gt;]<BR>
 *   <EM>Initial:</EM> UA specific<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   The value is a prioritized list of font family names and/or generic family
 *   names. Unlike most other CSS1 properties, values are separated by a comma
 *   to indicate that they are alternatives:
 *   <PRE>
 *   BODY { font-family: gill, helvetica, sans-serif }
 * </PRE>
 *   <P>
 *   There are two types of list values:
 *   <DL>
 *     <DT>
 *       <STRONG>&lt;family-name&gt;</STRONG>
 *     <DD> The name of a font family of choice. In the last example, "gill" and
 *     "helvetica" are font families.
 *     <DT>
 *       <STRONG>&lt;generic-family&gt;</STRONG>
 *     <DD> In the example above, the last value is a generic family name. The
 *     following generic families are defined:
 *       <UL>
 * 	<LI>
 * 	  'serif' (e.g. Times)
 * 	<LI>
 * 	  'sans-serif' (e.g. Helvetica)
 * 	<LI>
 * 	  'cursive' (e.g. Zapf-Chancery)
 * 	<LI>
 * 	  'fantasy' (e.g. Western)
 * 	<LI>
 * 	  'monospace' (e.g. Courier)
 *       </UL>
 *       <P> Style sheet designers are encouraged to offer a generic font family
 *       as a last alternative.
 *   </DL>
 *   <P>
 *   Font names containing whitespace should be quoted:
 *   <PRE>
 *   BODY { font-family: "new century schoolbook", serif }
 *
 *   &lt;BODY STYLE="font-family: 'My own font', fantasy"&gt;
 *  </PRE>
 *   <P>
 *   If quoting is omitted, any whitespace characters before and after the font
 *   name are ignored and any sequence of whitespace characters inside the font
 *   name is converted to a single space.
 * 
 * @see CssFont
 * @version $Revision$ 
 */
public class CssFontFamilyCSS2 extends CssProperty implements CssOperator {
    
    Vector family_name = new Vector();

    boolean inheritedValue;

    static String[] genericFamily = { "serif", "sans-serif", "cursive",
				      "fantasy", "monospace" };
    
    static int[] genericFamilyHash;
    
    boolean withSpace = false;

    /**
     * Create a new CssFontFamilyCSS2
     */
    public CssFontFamilyCSS2() {
    }  
    
    /**
     * Create a new CssFontFamilyCSS2
     *
     * @param expression the font name
     * @exception InvalidParamException The expression is incorrect
     */
    public CssFontFamilyCSS2(ApplContext ac, CssExpression expression) 
	    throws InvalidParamException {
	boolean family = true;
	CssValue val = expression.getValue();
	char op;

	setByUser();
	//@@ and if name is already in the vector ?
	

	if (val.equals(inherit)) {
	    inheritedValue = true;
	    expression.next();
	    return;
	}

	while (family) {
	    val = expression.getValue();
	    op = expression.getOperator();
	    
	    if ((op != COMMA) && (op != SPACE)) {
		throw new InvalidParamException("operator", 
						((new Character(op)).toString()),
						ac);
	    }
	    
	    if (val instanceof CssString) {
		String familyName = null;
		if (op == COMMA) { // "helvetica", "roman"
		    familyName = trimToOneSpace(val.toString());
		    expression.next();
		} else { // "helvetica" CssValue
		    familyName = trimToOneSpace(val.toString());
		    family = false;
		    expression.next();
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
		    family_name.addElement(convertString(val.toString()));
		    expression.next();
		} else {
		    CssValue next = expression.getNextValue();
		    
		    if (next instanceof CssIdent) {
			CssIdent New = new CssIdent(val.get() + " " 
						    + next.get());
			withSpace = true;
			expression.remove();
			op = expression.getOperator();
			expression.remove();
			expression.insert(New);
			expression.setCurrentOperator(op);
		    } else {
			family_name.addElement(convertString(val.toString()));
			expression.next();
			family = false;
		    }
		}
	    } else
		throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
	}

    }
    
    /**
     * Returns all fonts name
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
     * Returns the font (null if no font)
     */  
    public Object get() {
	if (family_name.size() == 0) {
	    return null;
	}
	return family_name.firstElement();
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return inheritedValue;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {  
	if (inheritedValue) {
	    return inherit.toString();
	} else {
	    String r = "";
	    for (Enumeration e = elements(); e.hasMoreElements();)
		//		r += ", " + convertString(e.nextElement().toString());
		r += ", " + e.nextElement().toString();
	    if (r.length() < 3) {
		return null;
	    }
	    return r.substring(2);
	}
    }
    
    String convertString (String value) {
	if (value.indexOf('"') != -1) {
	    return '\'' + value + '\'';
	} else if (value.indexOf('\'') != -1) {
	    return '"' + value + '"';	    
	} else {
	    return value;
	}
    }

    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "font-family";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssFontCSS2 cssFont = ((Css1Style) style).cssFontCSS2;

	if (cssFont.fontFamily != null)
	    style.addRedefinitionWarning(ac, this);
	cssFont.fontFamily = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getFontFamilyCSS2();
	} else {
	    return ((Css1Style) style).cssFontCSS2.fontFamily;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return false; //@@ FIXME
    }
    
    private static String trimToOneSpace(String name) {
	int count = name.length();
	char[] dst = new char[count];
	char[] src = new char[count];
	int index = -1;
	
	name.getChars(0, count, src, 0);
	for(int i=0; i < count; i++)
	    if ( i == 0 || ! Util.isWhiteSpace(src[i]) || 
		 ( Util.isWhiteSpace(src[i]) && 
		   !Util.isWhiteSpace(dst[index]) ) )
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
