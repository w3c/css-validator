//
// $Id$
// From Sijtsche de Jong
//
// (c) COPYRIGHT 1995-2002  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> &lt; alphavalue&gt; || inherit<BR>
 *  <EM>Initial:</EM>1<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property sets the opacity of an element.
 *  <PRE>
 *  H1 { opacity: 0}
 *  </PRE>
 */

public class SolidOpacity extends CssProperty {
    
    CssValue opaclevel;
    ApplContext ac;
    
    /**
     * Create a new SolidOpacity
     */
    public SolidOpacity() {
	CssNumber cssnum =  new CssNumber((float) 1.0);
	opaclevel = cssnum;
    }
    
    /**
     * Create a new SolidOpacity
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public SolidOpacity(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this.ac = ac;
	setByUser(); // tell this property is set by the user
	CssValue val = expression.getValue();
	if (val instanceof CssNumber) {
	    
	    CssNumber cssnum = new CssNumber(clampedValue(ac, 
		    ((CssNumber) val).getValue()));
	    opaclevel = cssnum;
	    expression.next();
	}
	else if (val instanceof CssIdent) {
	    if (val.equals(inherit)) {
		opaclevel = inherit;
		expression.next();
	    }
	}
	else {
	    throw new InvalidParamException("value", val.toString(),
		    getPropertyName(), ac);
	}
    }
    
    public SolidOpacity(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Brings all values back between 0 and 1
     *
     * @param opac The value to be modified if necessary
     */
    private float clampedValue(ApplContext ac, float opac) {
	if (opac < 0 || opac > 1) {
	    ac.getFrame().addWarning("out-of-range", Util.displayFloat(opac));
	    return ((opac<0)?0:1);
	}
	else return(opac);
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((SVGStyle) style).solidOpacity != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGStyle) style).solidOpacity = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGStyle) style).getSolidOpacity();
	} else {
	    return ((SVGStyle) style).solidOpacity;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof SolidOpacity &&
		opaclevel.equals( ((SolidOpacity) property).opaclevel));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "solid-opacity";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return opaclevel;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return opaclevel.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return opaclevel.toString();
    }
    
    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	CssNumber cssnum = new CssNumber(ac, (float) 1.0);
	return opaclevel == cssnum;
    }
    
}

