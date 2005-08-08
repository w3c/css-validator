//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:16:56  plehegar
 * New
 *
 */

package org.w3c.css.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 * <H3>&nbsp;&nbsp 'speak-date'</H3>
 * <P>
 * <EM>Value: </EM> myd | dmy | ymd <BR>
 * <EM>Initial:</EM> (Browser-specific)<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> yes<BR>
 * <EM>Percentage values:</EM> NA
 *
 * <p>This is a request about how any
 * dates should be spoken. month-day-year is common in the USA, while
 * day-month-year is common in Europe and year-month-day is also used.

 * <p class=comment>This would be most useful when combined with a new
 * HTML tag used to identify dates, such as this theoretical example:
 *
 * <pre>
 *    &lt;p&gt;The campaign started on &lt;date value="1874-oct-21"&gt;
 *    the twenty-first of that month&lt;/date&gt; and finished 
 *    &lt;date value="1874-oct-28"&gt;a week later&lt;/date&gt;
 * </pre>
 *
 *
 * @version $Revision$
 */
public class ACssSpeakDate extends ACssProperty {
    
    CssValue value;
    
    /**
     * Create a new ACssSpeakDate
     */  
    public ACssSpeakDate() {
	value = myd; // browser specific
    }
    
    /**
     * Creates a new ACssSpeakDate
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */  
    public ACssSpeakDate(ApplContext ac, CssExpression expression, boolean check) 
    throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	
	if (val.equals(dmy)) {
	    value = dmy;
	    expression.next();
	    return;
	} else if (val.equals(ymd)) {
	    value = ymd;
	    expression.next();
	    return;
	} else if (val.equals(myd)) {
	    value = myd;
	    expression.next();
	    return;
	}
	
	throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
    }
    
    public ACssSpeakDate(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	return value;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value.toString();
    }
    
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "speak-date";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((ACssStyle) style).acssSpeakDate != null)
	    ((ACssStyle) style).addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssSpeakDate = this;
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof ACssSpeakDate && 
		value.equals(((ACssSpeakDate) property).value));
    }
    
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getSpeakDate();
	} else {
	    return ((ACssStyle) style).acssSpeakDate;
	}
    }
    
    private static CssIdent myd = new CssIdent("myd");
    private static CssIdent dmy = new CssIdent("dmy");
    private static CssIdent ymd = new CssIdent("ymd");
}
