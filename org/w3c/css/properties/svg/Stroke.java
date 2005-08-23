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
import org.w3c.css.properties.css1.CssColor;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;paint&gt; | inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */
public class Stroke extends CssProperty implements CssOperator {
    
    CssValue stroke;
    ApplContext ac;
    Vector values = new Vector();
    
    CssIdent currentColor = new CssIdent("currentColor");
    CssIdent none = new CssIdent("none");
    
    /**
     * Create a new Stroke
     */
    public Stroke() {
	//nothing to do
    }
    
    /**
     * Create a new Stroke
     *
     * @param expression The expression for this property     
     * @exception InvalidParamException Values are incorrect
     */
    public Stroke(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this.ac = ac;
	setByUser(); // tell this property is set by the user
	CssValue val = expression.getValue();
	boolean correct = true;
	String errorval = "";
	char op = expression.getOperator();
	
	if (val.equals(inherit)) {
	    stroke = inherit;
	    expression.next();
	} else if (val.equals(currentColor)) {
	    stroke = currentColor;
	    expression.next();
	} else if (val.equals(none)) {
	    stroke = none;
	    expression.next();
	} else {
	    try {
		CssColor color = new CssColor(ac, expression);
		values.addElement(val);
		//expression.next();
		
		op = expression.getOperator();
		val = expression.getValue();
		
		
		if (val instanceof CssFunction) { // icc-color(<name>[,<icccolorvalue>]*)]
		    CssValue function = val;
		    if (!((CssFunction) val).getName().equals("icc-color")) {
			correct = false;
			errorval = val.toString();
		    } else {
			CssExpression params = ((CssFunction) val).getParameters();
			
			op = params.getOperator();
			val = params.getValue();
			
			if (!(val instanceof CssIdent)) {
			    correct = false;
			    errorval = val.toString();
			}
			
			params.next();
			op = params.getOperator();
			val = params.getValue();
			
			if (!params.end()) { // there are more parameters left
			    int counter = 0;
			    
			    while ((op == COMMA || op == SPACE)
				    && (counter < (params.getCount() - 1) && correct == true)) {
				
				if ((!(val instanceof CssNumber)) || (((CssNumber) val).getValue() < 0)) {
				    correct = false;
				    errorval = val.toString();
				}
				
				params.next();
				counter++;
				val = params.getValue();
				op = params.getOperator();
			    }
			}
			
			if (correct) {
			    params.starts();
			    values.addElement(function);
			}
			else {
			    correct = false;
			    errorval = val.toString();
			}
		    }
		}
	    } catch (InvalidParamException e) {
		correct = false;
		errorval = val.toString();
	    }
	    
	    if (val instanceof CssURL) {
		values.addElement(val);
		correct = true;
		errorval = null;
		
		expression.next();
		op = expression.getOperator();
		val = expression.getValue();
		
		if (val.equals(none) || val.equals(currentColor)) {
		    values.addElement(val);
		    expression.next();
		} else {
		    //-------------		   
		    
		    try {
			CssColor color = new CssColor(ac, expression);
			values.addElement(val);
			//expression.next();
			
			op = expression.getOperator();
			val = expression.getValue();
			
			
			if (val instanceof CssFunction) { // icc-color(<name>[,<icccolorvalue>]*)]
			    CssValue function = val;
			    if (!((CssFunction) val).getName().equals("icc-color")) {
				correct = false;
				errorval = val.toString();
			    } else {
				CssExpression params = ((CssFunction) val).getParameters();
				
				op = params.getOperator();
				val = params.getValue();
				
				if (!(val instanceof CssIdent)) {
				    correct = false;
				    errorval = val.toString();
				}
				
				params.next();
				op = params.getOperator();
				val = params.getValue();
				
				if (!params.end()) { // there are more parameters left
				    int counter = 0;
				    
				    while ((op == COMMA || op == SPACE)
					    && (counter < (params.getCount() - 1) && correct == true)) {
					
					if ((!(val instanceof CssNumber)) || (((CssNumber) val).getValue() < 0)) {
					    correct = false;
					    errorval = val.toString();
					}
					
					params.next();
					counter++;
					val = params.getValue();
					op = params.getOperator();
				    }
				}
				
				if (correct) {
				    params.starts();
				    values.addElement(function);
				}
				else {
				    correct = false;
				    errorval = val.toString();
				}
			    }
			}
		    } catch (InvalidParamException e) {
			correct = false;
			errorval = val.toString();
		    }
		    
		    //-----------------
		}
	    } else {
		correct = false;
		errorval = val.toString();
	    }
	    
	}
	
	expression.next();
	
	if (!correct) {
	    throw new InvalidParamException("value", errorval, getPropertyName(), ac);
	}
    }
    
    public Stroke(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((SVGStyle) style).stroke != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGStyle) style).stroke = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGStyle) style).getStroke();
	} else {
	    return ((SVGStyle) style).stroke;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof Stroke && 
		stroke.equals( ((Stroke) property).stroke));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "stroke";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	if (stroke != null) 
	    return stroke;
	else 
	    return values;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return stroke.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	if (stroke != null) {
	    return stroke.toString();
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
	return stroke == none;
    }
    
}
