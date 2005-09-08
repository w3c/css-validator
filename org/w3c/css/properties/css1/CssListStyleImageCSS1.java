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
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *      &nbsp;&nbsp; 'list-style-image'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;url&gt; | none<BR>
 *   <EM>Initial:</EM> none<BR>
 *   <EM>Applies to:</EM> elements with 'display' value 'list-item'<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property sets the image that will be used as the list-item marker. When
 *   the image is available it will replace the marker set with the 'list-style-type'
 *   marker.
 *   <PRE>
 *   UL { list-style-image: url(http://png.com/ellipse.png) }
 * </PRE>
 * @version $Revision$
 */
public class CssListStyleImageCSS1 extends CssProperty {
    
    CssValue value;
    
    private static CssIdent none = new CssIdent("none");
    
    /**
     * Create a new CssListStyleImageCSS1
     */
    public CssListStyleImageCSS1() {
	value = none;
    }  
    
    /**
     * Create a new CssListStyleImageCSS1
     *
     * @param value The value for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssListStyleImageCSS1(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	
	setByUser();
	
	if (val instanceof CssURL) {
	    value = val;
	    expression.next();
 	} else if (val.equals(none)) {
	    value = none;
	    expression.next();
 	} else if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", val, getPropertyName(), ac);
	}
    }
    
    public CssListStyleImageCSS1(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "list-style-image";
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value.toString();
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssListStyleCSS1 cssListStyle = ((Css1Style) style).cssListStyleCSS1;
	if (cssListStyle.listStyleImage != null)
	    style.addRedefinitionWarning(ac, this);
	cssListStyle.listStyleImage = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getListStyleImageCSS1();
	} else {
	    return ((Css1Style) style).cssListStyleCSS1.listStyleImage;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssListStyleImageCSS1 && 
		value.equals(((CssListStyleImageCSS1) property).value));
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == none;
    }
    
}
