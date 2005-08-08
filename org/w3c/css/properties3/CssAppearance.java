//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> auto || &lt;percentage&gt; || &lt;length&gt; || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>inline-level elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>refers to the 'line-height' of the element<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  The 'alignment-adjust' property allows more precise alignment of
 *  elements, such as graphics, that do not have a baseline-table
 *  or lack the desired baseline in their baseline-table. With the
 *  'alignment-adjust' property, the position of the baseline
 *  identified by the 'alignment-baseline' can be explicitly determined.
 */

public class CssAppearance extends CssProperty {
    
    CssValue appearance;
    
    CssIdent normal = new CssIdent("normal");
    
    private static String[] values = {
	"normal", "icon", "window", "document", "workspace", 
	"desktop", "tooltip", "dialog", "button", "default-button",
	"hyperlink", "menu", "pull-down-menu", "pop-up-menu", "list-menu",
	"field", "inherit"
    };
    
    /**
     * Create a new CssAppearance
     */
    public CssAppearance() {
	appearance = normal;
    }
    
    /**
     * Create a new CssAppearance
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssAppearance(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	setByUser();
	CssValue val = expression.getValue();
	
	if (val instanceof CssIdent) {
	    int i = 0;
	    for (; i < values.length; i++) {
		if (val.toString().equals(values[i])) {
		    appearance = val;
		    expression.next();
		    break;
		}
	    }
	    if (i == values.length) {
		throw new InvalidParamException("value", expression.getValue(),
			getPropertyName(), ac);
	    }
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
		    getPropertyName(), ac);
	}
    }
    
    public CssAppearance(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }  
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssAppearance != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssAppearance = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getAppearance();
	}
	else {
	    return ((Css3Style) style).cssAppearance;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssAppearance &&
		appearance.equals(((CssAppearance) property).appearance));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "appearance";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return appearance;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return appearance.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return appearance.toString();
    }
    
    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return appearance == normal;
    }
    
}
