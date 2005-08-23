//
// $Id$
// From Sijtsche Smeman (sijtsche@wisdom.nl)
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
 * This property sets the preferred media for this stylesheet
 */

public class ColorProfileSrc extends CssProperty implements CssOperator {
    
    CssValue cpSrc;
    Vector cpSrcs = new Vector();
    
    CssIdent sRGB = new CssIdent("sRGB");
    
    /**
     * Create a new ColorProfileSrc
     */
    public ColorProfileSrc() {
	cpSrc = sRGB;
    }
    
    /**
     * Create a new ColorProfileSrc
     */
    public ColorProfileSrc(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	//setByUser();
	char op = expression.getOperator();
	CssValue val = expression.getValue();
	
	if ((val.toString().toLowerCase()).equals("srgb")) {
	    cpSrc = sRGB;
	    expression.next();
	} else if (val instanceof CssFunction) {
	    CssFunction fun = (CssFunction) val;
	    CssExpression params = fun.getParameters();
	    if (fun.getName().equals("local")) {
		if ((params.getCount() == 1)) {
		    cpSrc = val;
		    expression.next();
		} else {
		    throw new InvalidParamException("value",
			    params.getValue(), getPropertyName(), ac);
		}
	    } else {
		throw new InvalidParamException("value",
			val, getPropertyName(), ac);
	    }
	    
	    val = expression.getValue();
	    
	    if (val != null) {
		
		if (op != SPACE) {
		    throw new InvalidParamException("operator",
			    new Character(op), getPropertyName(), ac);
		}
		
		if (val instanceof CssURL) {
		    cpSrcs.addElement(cpSrc);
		    cpSrcs.addElement(val);
		    cpSrc = null;
		    expression.next();
		}
	    }
	    
	} else if (val instanceof CssURL) {
	    cpSrc = val;
	    expression.next();
	} else {
	    throw new InvalidParamException("cpSrc",expression.getValue(),
		    getPropertyName(), ac);
	}
	
    }
    
    public ColorProfileSrc(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((SVGStyle) style).cpSrc != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGStyle) style).cpSrc = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGStyle) style).getColorProfileSrc();
	} else {
	    return ((SVGStyle) style).cpSrc;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param cpSrc The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof ColorProfileSrc &&
		cpSrc.equals( ((ColorProfileSrc) property).cpSrc));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "src";
    }
    
    /**
     * Returns the cpSrc of this property
     */
    public Object get() {
	if (cpSrc != null)
	    return cpSrc;
	else
	    return cpSrcs;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return cpSrc.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	if (cpSrc != null) {
	    return cpSrc.toString();
	} else {
	    String ret = "";
	    for (int i = 0; i < cpSrcs.size(); i++) {
		ret += " " + cpSrcs.elementAt(i).toString();
	    }
	    return ret;
	}
    }
    
    /**
     * Is the cpSrc of this property a default cpSrc
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return cpSrc == sRGB;
    }
    
}
