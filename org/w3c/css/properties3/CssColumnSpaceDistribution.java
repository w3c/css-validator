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
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> right || left || start || end || top || bottom || inner ||
 *   outer || between || outside || inherit<BR>
 *  <EM>Initial:</EM>end<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property describes how to interpret 'column-width'. The 'flexible'
 *  value indicates that the width of columns can be increased to fill all
 *  the available space. The 'strict' value indicates that 'column-width' is
 *  to be honored.
 */

public class CssColumnSpaceDistribution extends CssProperty implements CssOperator {
    
    String distribution = "";
    
    static CssIdent end = new CssIdent("end");
    
    private static String[] values = {
	"start", "end", "inner", "outer", "between", "inherit"
    };
    
    /**
     * Create a new CssColumnSpaceDistribution
     */
    public CssColumnSpaceDistribution() {
	// nothing to do
    }
    
    /**
     * Create a new CssColumnSpaceDistribution
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssColumnSpaceDistribution(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	setByUser();
	
	CssValue val = expression.getValue();
	int maxvalues = 2;
	boolean correct = true;
	char op = SPACE;
	
	while (correct && (val != null) && (maxvalues-- > 0)) {
	    
	    correct = false;
	    
	    for (int i = 0; i < values.length; i++) {
		if (val.toString().equals(values[i])) {
		    distribution += " " + val.toString();
		    expression.next();
		    correct = true;
		    break;
		}
	    }
	    
	    if (!correct) {
		throw new InvalidParamException("value", expression.getValue(),
			getPropertyName(), ac);
	    }
	    
	    val = expression.getValue();
	    op = expression.getOperator();
	    
	}
	
    }
    
    public CssColumnSpaceDistribution(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssColumnSpaceDistribution != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssColumnSpaceDistribution = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getColumnSpaceDistribution();
	}
	else {
	    return ((Css3Style) style).cssColumnSpaceDistribution;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssColumnSpaceDistribution &&
		distribution.equals(((CssColumnSpaceDistribution) property).distribution));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "column-space-distribution";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return distribution;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return distribution.equals("inherit");
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return distribution;
    }
    
    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (distribution.equals("end"));
    }
    
}
