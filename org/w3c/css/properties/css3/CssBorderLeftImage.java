//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;



public class CssBorderLeftImage extends CssProperty {
    
    String value;
    ApplContext ac;
    CssIdent none = new CssIdent("none");
    
    /**
     * Create new CssBorderLeftImage
     */
    public CssBorderLeftImage() {
	value = "none";
    }
    
    /**
     * Create new CssBorderLeftImage
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderLeftImage(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	
	if (val instanceof CssIdent) {
	    if (val.equals(none)) {
		value = "none";
		expression.next();
	    } else if (val.equals(inherit)) {
		value = "inherit";
		expression.next();
	    }
	} else if (val instanceof CssURL) {
	    
	    value = val.toString();
	    expression.next();
	    
	    val = expression.getValue();
	    
	    if (val != null) {
		if (val instanceof CssURL) {
		    value += " " + val.toString();
		    expression.next();
		} else {
		    throw new InvalidParamException("value", expression.getValue(),
			    getPropertyName(), ac);
		}
		
		expression.next();
		val = expression.getValue();
		
		if (val != null) {
		    if (val instanceof CssURL) {
			value += " " + val.toString();
			expression.next();
		    } else {
			throw new InvalidParamException("value", expression.getValue(),
				getPropertyName(), ac);
		    }
		}
	    }
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
		    getPropertyName(), ac);
	}
    }
    
    public CssBorderLeftImage(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssBorderLeftImage != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBorderLeftImage = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getBorderLeftImage();
	} else {
	    return ((Css3Style) style).cssBorderLeftImage;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderLeftImage &&
		value.equals( ((CssBorderLeftImage) property).value));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border-left-image";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return value.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return value;
    }
    
    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	CssNumber cssnum = new CssNumber(ac, (float) 1.0);
	return value == cssnum.toString();
    }
    
}
