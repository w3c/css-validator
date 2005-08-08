//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.font.FontSize;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;font-size&gt; || inherit<BR>
 *  <EM>Initial:</EM>0<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>element's computed 'font-size'<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  If 'text-align-last' is 'size', the fonts of the last line of an element 
 *  are not allowed to become smaller than the smaller of 'font-size' and 
 *  'min-font-size'. 
 */

public class CssMinFontSize extends CssProperty {

    CssValue min;
    ApplContext ac;

    /** 
     * Create a new CssMinFontSize
     */
    public CssMinFontSize() {
	CssNumber cssnum = new CssNumber(0);
	min = cssnum;
    }

    /**
     * Create a new CssMinFontSize
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssMinFontSize(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    min = inherit;
	    expression.next();
	}
	else {
	    try {
		FontSize fontsize = new FontSize(ac, expression); 
		min = val;
		expression.next();
	    }
	    catch (InvalidParamException e) {
		throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
	    }
	}
    }

    public CssMinFontSize(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssMinFontSize != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssMinFontSize = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getMinFontSize();
	} else {
	    return ((Css3Style) style).cssMinFontSize;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssMinFontSize && 
                min.equals( ((CssMinFontSize) property).min));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "min-font-size";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return min;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return min.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return min.toString();
    }
    
    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {	
	CssNumber cssnum = new CssNumber(ac, 0);
	return min == cssnum;
    }
    
}
    
