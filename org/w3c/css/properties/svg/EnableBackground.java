//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.properties.css1.CssWidth;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> accumulate || new [ &lt;x&gt; &lt;y&gt; 
 *  &lt;width&gt; &lt;height&gt; || inherit<BR>
 *  <EM>Initial:</EM>accumulate<BR>
 *  <EM>Applies to:</EM>container elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class EnableBackground extends CssProperty implements CssOperator {
    
    CssValue value;
    Vector values = new Vector();
    ApplContext ac;
    
    CssIdent accumulate = new CssIdent("accumulate");
    
    /**
     * Create a new Value
     */
    public EnableBackground() {
	//nothing to do
    }
    
    /**
     * Create a new Value
     *
     * @param expression The expression for this property     
     * @exception InvalidParamException Values are incorrect
     */
    public EnableBackground(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	this.ac = ac;
	setByUser(); // tell this property is set by the user
	CssValue val = expression.getValue();
	char op = SPACE;
	
	if (val instanceof CssIdent) {
	    if (val.equals(inherit)) {
		value = inherit;
		expression.next();
	    } else if (val.equals(accumulate)) {
		value = accumulate;
		expression.next();
	    } else {
		throw new InvalidParamException("value", val.toString(), 
			getPropertyName(), ac);
	    }
	}
	else if (val instanceof CssNumber) {
	    // first value
	    values.addElement(val);
	    expression.next();
	    val = expression.getValue();
	    op = expression.getOperator();
	    
	    // second value
	    if (val instanceof CssNumber) {
		values.addElement(val);
		expression.next();
	    } else {
		throw new InvalidParamException("value", val.toString(), 
			getPropertyName(), ac);
	    }
	    val = expression.getValue();
	    op = expression.getOperator();
	    
	    // third value
	    try {
		CssWidth width = new CssWidth(ac, expression);
		values.addElement(width);
		//expression.next();
	    } catch (InvalidParamException e) {
		throw new InvalidParamException("value", val.toString(), 
			getPropertyName(), ac);
	    }
	    
	    val = expression.getValue(); 
	    op = expression.getOperator();
	    
	    // fourth value
	    try {
		CssWidth width = new CssWidth(ac, expression);
		values.addElement(width);
		expression.next();
	    } catch (InvalidParamException e) {
		throw new InvalidParamException("value", val.toString(), 
			getPropertyName(), ac);
	    }
	    
	}
	else {
	    throw new InvalidParamException("value", val.toString(), 
		    getPropertyName(), ac);
	}
    }
    
    public EnableBackground(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((SVGStyle) style).enableBackground != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGStyle) style).enableBackground = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGStyle) style).getEnableBackground();
	} else {
	    return ((SVGStyle) style).enableBackground;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof EnableBackground && 
		value.equals( ((EnableBackground) property).value));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "enable-background";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	if (value != null) {
	    return value;
	} else {
	    return values;
	}
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	if (value != null) {
	    return value.equals(inherit);
	} else {
	    return false;
	}
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	if (value != null) {
	    return value.toString();
	} else {
	    String ret = "";
	    for (int i = 0; i < values.size(); i++) {
		ret += " " + values.elementAt(i).toString();
	    }
	    return ret;
	}
	
    }
    
    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {	
	return (value == accumulate);
    }
    
}
