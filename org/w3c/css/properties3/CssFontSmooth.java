//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import java.util.Hashtable;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> auto || never || always || &lt;font-size&gt; || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property allows author control over applying anti-aliasing fonts when rendered.
 *  <PRE>
 *  H1 { font-smooth: never}
 *  </PRE>
 */

public class CssFontSmooth extends CssProperty {

    CssValue fontsmooth;
    ApplContext ac;

    static CssIdent auto = new CssIdent("auto");
    static CssIdent never = new CssIdent("never");
    static CssIdent always = new CssIdent("always");
    static CssIdent xxsmall = new CssIdent("xx-small");
    static CssIdent xsmall = new CssIdent("x-small");
    static CssIdent small = new CssIdent("small");
    static CssIdent medium = new CssIdent("medium");
    static CssIdent large = new CssIdent("large");
    static CssIdent xlarge = new CssIdent("x-large");
    static CssIdent xxlarge = new CssIdent("xx-large");

    Hashtable fsizes = new Hashtable();

    /**
     * Create a new CssFontSmooth
     */
    public CssFontSmooth() {
	fontsmooth = auto;
    }

    /**
     * Create a new CssFontSmooth
     *
     * @param  expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssFontSmooth(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	fsizes.put(xxsmall, "nothing");
	fsizes.put(xsmall, "nothing");
	fsizes.put(small, "nothing");
	fsizes.put(medium, "nothing");
	fsizes.put(large, "nothing");
	fsizes.put(xlarge, "nothing");
	fsizes.put(xxlarge, "nothing");
	CssValue val = expression.getValue();

	if (fsizes.containsKey(val)) {
	    fontsmooth = val;
	    expression.next();
	}
	else if (val instanceof CssLength) { // may not be negative!
	    float f = ((Float) val.get()).floatValue();
	    if (f >= 0) {
		fontsmooth = val;
		expression.next();
		return;
	    }
	    else {
		throw new InvalidParamException("negative-value",
						val.toString(), ac);
	    }
	}
	else if (val instanceof CssIdent) {
	    if (val.equals(auto)) {
		fontsmooth = auto;
		expression.next();
	    }
	    else if (val.equals(never)) {
		fontsmooth = never;
		expression.next();
	    }
	    else if (val.equals(always)) {
		fontsmooth = always;
		expression.next();
	    }
	    else if (val.equals(inherit)) {
		fontsmooth = inherit;
		expression.next();
	    }
	} else if (val instanceof CssLength) {
		fontsmooth = val;
		expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }
    
    public CssFontSmooth(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }    
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssFontSmooth != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssFontSmooth = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getFontSmooth();
	} else {
	    return ((Css3Style) style).cssFontSmooth;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssFontSmooth &&
		fontsmooth.equals( ((CssFontSmooth) property).fontsmooth));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "font-smooth";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return fontsmooth;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return fontsmooth.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return fontsmooth.toString();
    }
    
    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return fontsmooth == auto;
    }
    
}


