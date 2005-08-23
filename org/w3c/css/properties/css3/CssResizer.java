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
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> auto || both || horizontal || vertical || none || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:interactive
 */

public class CssResizer extends CssProperty {

    CssValue resz;
    ApplContext ac;

    CssIdent auto = new CssIdent("auto");
    CssIdent both = new CssIdent("both");
    CssIdent horizontal = new CssIdent("horizontal");
    CssIdent vertical = new CssIdent("vertical");
    CssIdent none = new CssIdent("none");
    
    /**
     * Create a new CssResizer
     */
    public CssResizer() {
	resz = auto;
    }

    /** 
     * Create a new CssResizer
     */
    public CssResizer(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	setByUser();
	CssValue val = expression.getValue();
	
	if (val.equals(auto)) {
	    resz = auto;
	    expression.next();
	}
	else if (val.equals(both)) {
	    resz = both;
	    expression.next();
	}
	else if (val.equals(horizontal)) {
	    resz = horizontal;
	    expression.next();
	}
	else if (val.equals(vertical)) {
	    resz = vertical;
	    expression.next();
	}
	else if (val.equals(none)) {
	    resz = none;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    resz = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssResizer(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssResizer != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssResizer = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getResizer();
	} else {
	    return ((Css3Style) style).cssResizer;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssResizer && 
                resz.equals( ((CssResizer) property).resz));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "resizer";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return resz;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return resz.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return resz.toString();
    }
    
    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {	
	return resz == auto;
    }
    
}	
