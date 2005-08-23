//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
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
 *
 */
public class CssScript extends CssProperty {
    
    CssValue script;
    
    private static CssIdent auto = new CssIdent("auto");
    private static CssIdent none = new CssIdent("none");
    
    /**
     * Create a new CssScript
     */
    public CssScript() {
	script = auto;
    }
    
    /**
     * Create a new CssScript
     *
     *
     */
    public CssScript(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(auto)) {
	    script = auto;
	    expression.next();
	}
	else if (val.equals(none)) {
	    script = none;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    script = inherit;
	    expression.next();
	}
	else if (val instanceof CssIdent) {
	    script = val;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }
    
    public CssScript(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssScript != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssScript = this;
	
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getScript();
	} else {
	    return ((Css3Style) style).cssScript;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssScript &&
		script.equals( ((CssScript) property).script));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-script";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return script;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return script.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return script.toString();
    }
    
    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return script == auto;
    }
    
}
