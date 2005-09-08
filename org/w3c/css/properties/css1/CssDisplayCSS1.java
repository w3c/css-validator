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
 *      &nbsp;&nbsp; 'display'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> block | inline | list-item | none<BR>
 *   <EM>Initial:</EM> block<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property describes how/if an element is displayed on the canvas (which
 *   may be on a printed page, a computer display etc.).
 *   <P> An element with a 'display' value of 'block' opens a new box. The box
 *   is positioned relative to adjacent boxes according to the CSS <A
 *   HREF="#formatting-model">formatting model</A>. Typically, elements like
 *   'H1' and 'P' are of type 'block'. A value of 'list-item' is similar to
 *   'block' except that a list-item marker is added. In HTML, 'LI' will
 *   typically have this value.
 *   <P>
 *   An element with a 'display' value of 'inline' results in a new inline box
 *   on the same line as the previous content. The box is dimensioned according
 *   to the formatted size of the content. If the content is text, it may span
 *   several lines, and there will be a box on each line. The margin, border and
 *   padding properties apply to 'inline' elements, but will not have any effect
 *   at the line breaks.
 *   <P>
 *   A value of 'none' turns off the display of the element, including children
 *   elements and the surrounding box.
 *   <PRE>
 *   P { display: block }
 *   EM { display: inline }
 *   LI { display: list-item }
 *   IMG { display: none }
 * </PRE>
 *   <P>
 *   The last rule turns off the display of images.
 *   <P> The initial value of 'display' is 'block', but a UA will typically have
 *   default values for all HTML elements according to the suggested rendering
 *   of elements in the HTML specification.
 *
 * @version $Revision$ 
 */
public class CssDisplayCSS1 extends CssProperty {
    
    int value;
    
    private static String[] DISPLAY = {
	"inline", "block", "list-item", "none", };

    private static int[] hash_values;
    
    /**
     * Create a new CssDisplayCSS1
     */
    public CssDisplayCSS1() {
	// nothing to do
    }  
    
    /**
     * Create a new CssDisplayCSS1
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorect
     */  
    public CssDisplayCSS1(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	
	setByUser();

	if ( val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < DISPLAY.length; i++) {
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	    }
	}
	
	throw new InvalidParamException("value", expression.getValue(), 
					getPropertyName(), ac);
    }
    
    public CssDisplayCSS1(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return DISPLAY[value];
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "display";
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == DISPLAY.length - 1;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return DISPLAY[value];
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssDisplayCSS1 != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssDisplayCSS1 = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getDisplayCSS1();
	} else {
	    return ((Css1Style) style).cssDisplayCSS1;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssDisplayCSS1 && 
		value == ((CssDisplay) property).value);
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == 0;
    }
    
    static {
	hash_values = new int[DISPLAY.length];
	for (int i = 0; i < DISPLAY.length; i++)
	    hash_values[i] = DISPLAY[i].hashCode();
    }
}
