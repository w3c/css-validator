//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 14th 2000 Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 3.1  1997/08/29 13:13:44  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:21  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:15  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:30:00  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:19:55  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/25 14:31:21  plehegar
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
 *      &nbsp;&nbsp; 'display'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> block | inline | list-item | none | inline-block<BR>
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
public class CssDisplay extends CssProperty {
    
    int value;
    
    private static String[] DISPLAY = {
	"inline", "block", "inline-block", "list-item", "run-in", "compact", 
	"marker", "inline-svg", "svg", "math", "display-math",
	"table", "inline-table", "table-row-group", "table-header-group", 
	"table-footer-group", "table-row", "table-column-group", "table-column", 
	"table-cell", "table-caption", "ruby", "ruby-base", "ruby-text",
	"ruby-base-group", "ruby-text-group", "none", "inherit"
    };

    private static int[] hash_values;
    
    /**
     * Create a new CssDisplay
     */
    public CssDisplay() {
	// nothing to do
    }  
    
    /**
     * Create a new CssDisplay
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorect
     */  
    public CssDisplay(ApplContext ac, CssExpression expression) throws InvalidParamException {
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
	if (style0.cssDisplay != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssDisplay = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getDisplay();
	} else {
	    return ((Css1Style) style).cssDisplay;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssDisplay && 
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
