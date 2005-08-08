//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.1  2002/07/24 14:42:28  sijtsche
 * ATSC TV profile files
 *
 * Revision 1.1  2002/05/31 09:00:17  dejong
 * ATSC TV profile objects
 *
 * Revision 3.3  1997/09/09 13:03:38  plehegar
 * Added getColor()
 *
 * Revision 3.2  1997/09/08 14:03:51  plehegar
 * Suppressed a conflict
 *
 * Revision 3.1  1997/08/29 13:13:43  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:21  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:14  plehegar
 * Nothing
 *
 * Revision 1.6  1997/08/06 17:29:57  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.5  1997/07/31 15:44:29  plehegar
 * CssColors --> CssColor
 *
 * Revision 1.4  1997/07/30 13:19:53  plehegar
 * Updated package
 *
 * Revision 1.3  1997/07/22 17:53:08  plehegar
 * Bug fix in documentation
 *
 * Revision 1.2  1997/07/22 11:21:01  plehegar
 * Updated documentation
 *
 * Revision 1.1  1997/07/21 22:07:26  plehegar
 * Initial revision
 *
 */
package org.w3c.css.atsc;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'color'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;color&gt;<BR>
 *   <EM>Initial:</EM> UA specific<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property describes the text color of an element (often referred to as
 *   the <EM>foreground</EM> color). There are different ways to specify red:
 *   <PRE>
 *   EM { color: red }              /* natural language * /
 *   EM { color: rgb(255,0,0) }     /* RGB range 0-255   * /
 * </PRE>
 * @version $Revision$
 */
public class ATSCColor extends CssProperty {
    
    CssValue color;
    
    /**
     * Create a new ATSCColor
     */
    public ATSCColor() {
	color = inherit;
    }  
    
    /**
     * Set the value of the property
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public ATSCColor(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {
	CssValue val = expression.getValue();
	setByUser();
	
	if (val.equals(inherit)) {
	    color = inherit;
	    expression.next();
	} else if (val instanceof org.w3c.css.values.ATSCColor) {
	    color = val;
	    expression.next();
	} else if (val instanceof CssIdent) {
	    color = new org.w3c.css.values.ATSCColor(ac, (String) val.get());
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(), 
					    getPropertyName(), ac);
	}
    }
    
    public ATSCColor(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return color;
    }
    
    /**
     * Returns the color
     */
    public org.w3c.css.values.ATSCColor getColor() {
	if (color.equals(inherit)) {
	    /*
	    System.err.println("[ERROR] org.w3c.css.properties.CssColor");
	    System.err.println("[ERROR] value is inherited");
	    */
	    return null;
	} else {
	    return (org.w3c.css.values.ATSCColor) color;
	}
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return color.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return color.toString();
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	ATSCStyle style0 = (ATSCStyle) style;
	if (style0.ATSCcolor != null) {
	    style0.addRedefinitionWarning(ac, this);
	}
	style0.ATSCcolor = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ATSCStyle) style).getColorATSC();
	} else {
	    return ((ATSCStyle) style).ATSCcolor;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof ATSCColor && 
		color.equals(((ATSCColor) property).color));
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "color";
    }
    
}
