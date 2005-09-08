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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class MediaMinHeight extends CssProperty {

    CssValue value;

    /**
     * Create a new MediaMinHeight
     */
    public MediaMinHeight() {
		//empty
    }

    /**
     * Create a new MediaMinHeight.
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public MediaMinHeight(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = null;
	
	if (expression != null) {
	    val = expression.getValue();
	}
	
	setByUser();
	
	if (val != null) {
	    if (val instanceof CssLength || val instanceof CssPercentage) {
		float f = ((Float) val.get()).floatValue();
		if (f < 0) {
		    throw new InvalidParamException("negative-value",
			    val.toString(), ac);
		}
		value = val;
	    } else if (val instanceof CssNumber) {
		value = ((CssNumber) val).getLength();
	    } else {
		throw new InvalidParamException("value", expression.getValue(),
			getPropertyName(), ac);
	    }
	    
	    expression.next();
	}
    }
    
    public MediaMinHeight(ApplContext ac, CssExpression expression)
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
	return "min-height";
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
	if (style0.mediaMinHeight != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.mediaMinHeight = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getMediaMinHeight();
	} else {
	    return ((Css3Style) style).mediaMinHeight;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof MediaMinHeight && value.equals(((MediaMinHeight) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return false;
    }

}
