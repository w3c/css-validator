//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.svgproperties;

import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;


/**
 * This is the stroke-dasharray property for SVG
 */

public class StrokeDashArray extends CssProperty implements CssOperator {

    CssValue value;
    Vector values = new Vector();

    CssIdent none = new CssIdent("none");

    /**
     * Create a new StrokeDashArray
     */
    public StrokeDashArray() {
	// nothing to do
    }

    /** 
     * Create a new StrokeDashArray
     */
    public StrokeDashArray(ApplContext ac, CssExpression expression) throws InvalidParamException {

	//setByUser();
	char op = COMMA;
	CssValue val = expression.getValue();
	int counter = 0;
	boolean correct = true;

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else if (val.equals(none)) {
	    value = none;
	    expression.next();
	} else if (val instanceof CssNumber) {
	    while ((op == COMMA || op == SPACE)
		   && (counter < expression.getCount() && correct == true)) {
		
		if ((!(val instanceof CssNumber)) || (((CssNumber) val).getValue() < 0))
		    correct = false;
		
		values.addElement(val);
		expression.next();
		counter++;
		val = expression.getValue();
		op = expression.getOperator();
	    }
	} else {
	    correct = false;
	}

	if (!correct) {
	    throw new InvalidParamException("value", val.toString(), 
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((SVGStyle) style).strokeDashArray != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGStyle) style).strokeDashArray = this;

    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGStyle) style).getStrokeDashArray();
	} else {
	    return ((SVGStyle) style).strokeDashArray;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return false;
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "stroke-dasharray";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	if (value != null) {
	    return value;
	}
	else {
	    return values;
	}
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * This property can't be inherited, it's only for @preference
     */
    public boolean isSoftlyInherited() {
	return value == inherit; 
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	if (value != null) {
	    return value.toString();
	}
	else {
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
	return value == none;
    }
    
}
