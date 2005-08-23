//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import java.util.Hashtable;
import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> <identifier> || none || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:all
 *  <P>
 *  The 'toggle-group' property accepts the name of a group (identifier). 
 *  The property indicates which toggle group that the element is a member 
 *  of. Only one element belonging to any given toggle group can be :checked. 
 */

public class CssToggleGroup extends CssProperty {

    CssValue toggle;
    public static Hashtable groups = new Hashtable();
    public static String checkedel = new String(); 
    String selector = new String();

    CssIdent none = new CssIdent("none");

    /**
     * Create a new CssToggleGroup
     */
    public CssToggleGroup() {
	toggle = none;
    }

    /**
     * Create a new CssToggleGroup
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssToggleGroup(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(none)) {
	    toggle = none;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    toggle = inherit;
	    expression.next();
	}
	else if (val instanceof CssIdent) {
	    if (!groups.containsKey(val.toString())) { // add group + element
		Vector elements = new Vector();
		elements.addElement(selector);
		groups.put(val.toString(), elements);
		toggle = val;
		expression.next();
	    }
	    else { // group already exists, only add element
		Vector elements = new Vector();
	        elements = (Vector) groups.get(val.toString());
		if (!elements.contains(selector)) {
		    groups.remove(val.toString());
		    elements.addElement(selector);
		    groups.put(val.toString(), elements);
		    toggle = val;
		    expression.next();
		}
		toggle = val;
		expression.next();
	    }
	}
	else {
	    throw new InvalidParamException("groupname", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssToggleGroup(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssToggleGroup != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssToggleGroup = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	selector = ((Css3Style) style).getSelector();
	if (resolve) {
	    return ((Css3Style) style).getToggleGroup();
	} else {
	    return ((Css3Style) style).cssToggleGroup;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssToggleGroup && 
                toggle.equals( ((CssToggleGroup) property).toggle));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "toggle-group";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return toggle;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return toggle.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return toggle.toString();
    }
    
    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {	
	return toggle == none;
    }
    
}	
