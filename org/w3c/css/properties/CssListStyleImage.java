//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:13:51  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:25  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:23  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:30:08  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:07  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/28 21:37:28  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

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
public class CssListStyleImage extends CssProperty {
    
    CssValue value;
    
    private static CssIdent none = new CssIdent("none");
    
    /**
     * Create a new CssListStyleImage
     */
    public CssListStyleImage() {
	value = none;
    }  
    
    /**
     * Create a new CssListStyleImage
     *
     * @param value The value for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssListStyleImage(ApplContext ac, CssExpression expression,
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
    
    public CssListStyleImage(ApplContext ac, CssExpression expression)
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
	CssListStyle cssListStyle = ((Css1Style) style).cssListStyle;
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
	    return ((Css1Style) style).getListStyleImage();
	} else {
	    return ((Css1Style) style).cssListStyle.listStyleImage;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssListStyleImage && 
		value.equals(((CssListStyleImage) property).value));
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == none;
    }
    
}
