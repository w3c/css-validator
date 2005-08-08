//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYLeft 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyLeft statement at
// http://www.w3.org/Consortium/Legal/copyLeft-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

public class CssOverflowY extends CssProperty {
    
    CssValue overflowY;
    
    static CssIdent auto = new CssIdent("auto");
    static CssIdent visible = new CssIdent("visible");
    static CssIdent hidden = new CssIdent("hidden");
    static CssIdent scroll = new CssIdent("scroll");
    static CssIdent initial = new CssIdent("initial");
    
    /**
     * Create a new CssOverflowY
     */
    public CssOverflowY() {
	// nothing to do
    }
    
    /**
     * Create a new CssOverflowY
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssOverflowY(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	setByUser();
	CssValue val = expression.getValue();
	
	if (val.equals(inherit)) {
	    overflowY = val;
	    expression.next();
	} else if (val.equals(visible)) {
	    overflowY = val;
	    expression.next();
	} else if (val.equals(hidden)) {
	    overflowY = val;
	    expression.next();
	} else if (val.equals(scroll)) {
	    overflowY = val;
	    expression.next();
	} else if (val.equals(auto)) {
	    overflowY = val;
	    expression.next();
	} else if (val.equals(initial)) {
	    overflowY = val;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
		    getPropertyName(), ac);
	}
    }
    
    public CssOverflowY(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssOverflowY != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssOverflowY = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getOverflowY();
	}
	else {
	    return ((Css3Style) style).cssOverflowY;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssOverflowY &&
		overflowY.equals(((CssOverflowY) property).overflowY));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "overflow-y";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return overflowY;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return overflowY.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return overflowY.toString();
    }
    
    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	
	return overflowY == visible;
    }
    
}
