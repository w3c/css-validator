//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssNumber;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;angle&gt; || inherit<BR>
 *  <EM>Initial:</EM>0<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  glyph-orientation-horizontal' controls glyph orientation when the primary 
 *  text advance direction is horizontal.
 */

public class CssGlyphHor extends CssProperty {

    CssValue hor;
    ApplContext ac;

    /** 
     * Create a new CssGlyphHor
     */
    public CssGlyphHor() {
	CssNumber cssnum = new CssNumber(1);
	hor = cssnum;
	this.ac = ac;
    }

    /** 
     * Create a new CssGlyphOrHor
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssGlyphHor(ApplContext ac, CssExpression expression) throws InvalidParamException {
	
	this.ac = ac;
	setByUser();
	CssValue val = expression.getValue();
	
	if (val.equals(inherit)) {
	    hor = inherit;
	    expression.next();
	}
	else if (val instanceof CssNumber) {
	    Integer intval = new Integer(val.toString());
	    int n = intval.intValue();
	    if (((n % 90) == 0) && (n < 360) && (n > -360)) {
		hor = val;
		expression.next();
	    }
	    else {
		throw new InvalidParamException("anglevalue", val.toString(),
						getPropertyName(), ac);
	    }
	}
	else {
	    throw new InvalidParamException("value", val.toString(),
					    getPropertyName(), ac);
	}
    }
		

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssGlyphHor != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssGlyphHor = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getGlyphHor();
	}
	else {
	    return ((Css3Style) style).cssGlyphHor;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssGlyphHor &&
		hor.equals(((CssGlyphHor) property).hor));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "glyph-orientation-horizontal";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return hor;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return hor.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	    return hor.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	CssNumber cssnum = new CssNumber(ac, 1);
	return hor == cssnum;
    }

}
