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
 * Revision 1.2  2002/04/08 21:17:42  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:13:31  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:14  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:05  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:29:50  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:19:47  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/22 12:36:48  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     <A NAME="background-repeat">5.3.4 &nbsp;&nbsp; 'background-repeat'</A>
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> repeat | repeat-x | repeat-y | no-repeat<BR>
 *   <EM>Initial:</EM> repeat<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   If a background image is specified, the value of 'background-repeat' determines
 *   how/if the image is repeated.
 *   <P>
 *   A value of 'repeat' means that the image is repeated both horizontally and
 *   vertically. The 'repeat-x' ('repeat-y') value makes the image repeat horizontally
 *   (vertically), to create a single band of images from one side to the other.
 *   With a value of 'no-repeat', the image is not repeated.
 *   <PRE>
 *   BODY { 
 *     background: red url(pendant.gif);
 *     background-repeat: repeat-y;
 *   }
 *  </PRE>
 *   <P>
 *   In the example above, the image will only be repeated vertically.
 * @version $Revision$
 */
public class CssBackgroundRepeatCSS1 extends CssProperty
	implements CssBackgroundConstants {
    
    int repeat;
    
    private static int[] hash_values;
    
    /**
     * Create a new CssBackgroundRepeatCSS1
     */
    public CssBackgroundRepeatCSS1() {
	repeat = 0;
    }  
    
    /**
     * Set the value of the property
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */  
    public CssBackgroundRepeatCSS1(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	setByUser();
	
	if (val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i =0; i < REPEAT.length; i++) {
		if (hash_values[i] == hash) {
		    repeat = i;
		    expression.next();
		    return;
		}
	    }
	}
	
	throw new InvalidParamException("value", expression.getValue(), 
					getPropertyName(), ac);
    }
    
    public CssBackgroundRepeatCSS1(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return REPEAT[repeat];
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return repeat == 4;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return REPEAT[repeat];
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "background-repeat";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBackgroundCSS1 cssBackground = ((Css1Style) style).cssBackgroundCSS1;
	if (cssBackground.repeat != null)
	    style.addRedefinitionWarning(ac, this);
	cssBackground.repeat = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBackgroundRepeatCSS1();
	} else {
	    return ((Css1Style) style).cssBackgroundCSS1.repeat;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssBackgroundRepeatCSS1 && 
		repeat == ((CssBackgroundRepeatCSS1) property).repeat);
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return repeat == 0;
    }
    
    static {
	hash_values = new int[REPEAT.length];
	for (int i = 0; i < REPEAT.length; i++)
	    hash_values[i] = REPEAT[i].hashCode();
    }
}



