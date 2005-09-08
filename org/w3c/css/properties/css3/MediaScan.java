//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class MediaScan extends CssProperty {

    CssValue value;
    CssIdent progressive = new CssIdent("progressive");
    CssIdent interlace = new CssIdent("interlace");

    /**
     * Create a new MediaScan
     */
    public MediaScan() {
		//empty
    }

    /**
     * Create a new MediaScan.
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public MediaScan(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {
	CssValue val = null;
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	if (expression != null) {
	    val = expression.getValue();
	}
	
	setByUser();
	
	if (val != null) {
	    if (val instanceof CssIdent) {
		if (val.equals(progressive) || val.equals(interlace)) {
		    value = val;
		} else {
		    throw new InvalidParamException("value", expression.getValue(),
			    getPropertyName(), ac);
		}
		
	    } else {
		throw new InvalidParamException("value", expression.getValue(),
			getPropertyName(), ac);
	    }
	    
	    expression.next();
	}
    }

    public MediaScan(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property.
     */
    public Object get() {
	return value;
    }

    /**
     * Returns the name of this property.
     */
    public String getPropertyName() {
	return "scan";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
		if (value != null) {
			return value.toString();
		} else {
			return null;
		}
    }


    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css3Style style0 = (Css3Style) style;
	if (style0.mediaScan != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.mediaScan = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getMediaScan();
	} else {
	    return ((Css3Style) style).mediaScan;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof MediaScan && value.equals(((MediaScan) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return false;
    }

}
