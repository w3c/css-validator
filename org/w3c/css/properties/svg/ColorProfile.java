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
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;

/**
 *  <PRE>
 *  Value:      auto | sRGB | (<uri> | <local-profile>)+ | inherit
 *  Initial:    auto
 *  Applies to: 'image' elements that refer to raster images
 *  Inherited:  yes
 *  Percentages: N/A
 *  Media:      visual
 * </PRE>
 */

public class ColorProfile extends CssProperty implements CssOperator {

    CssValue value;
    Vector values = new Vector();

    CssIdent auto = new CssIdent("auto");
    CssIdent sRGB = new CssIdent("sRGB");
    CssIdent none = new CssIdent("none");

    /**
     * Create a new ColorProfile
     */
    public ColorProfile() {
	// nothing to do
    }

    /** 
     * Create a new ColorProfile
     */
    public ColorProfile(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	//setByUser();
	char op = SPACE;
	CssValue val = expression.getValue();
	int counter = 0;
	boolean correct = true;
	String errorval = new String();

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else if (val.equals(auto)) {
	    value = none;
	    expression.next();
	} else if (val.equals(sRGB)) {
	    value = sRGB;
	    expression.next();
	} else {
	    while ((op == SPACE)
		    && (counter < expression.getCount() && correct == true)) {
		
		if (val instanceof CssURL) {
		    values.addElement(val);
		}
		else if (val instanceof CssFunction) {
		    if (((CssFunction) val).getName().equals("local")) {
			if ((((CssFunction) val).getParameters().getCount() == 1) &&
				(((CssFunction) val).getParameters().getValue() instanceof CssURL)) {
			    values.addElement(val);
			} else {
			    correct = false;
			    errorval = val.toString();
			}
		    } else {
			correct = false;
			errorval = val.toString();
		    }
		} else {
		    correct = false;
		    errorval = val.toString();
		}

		expression.next();
		counter++;
		val = expression.getValue();
		op = expression.getOperator();
	    }
	    
	    if (!correct) {
		throw new InvalidParamException("value", val.toString(), 
			getPropertyName(), ac);
	    }
	}
    }
    
    public ColorProfile(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((SVGStyle) style).colorProfile != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGStyle) style).colorProfile = this;

    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGStyle) style).getColorProfileSVG();
	} else {
	    return ((SVGStyle) style).colorProfile;
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
	return "color-profile";
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
	return value == auto;
    }
    
}
