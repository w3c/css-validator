//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 */
public class CssBottom extends CssBoxOffsetFace {

    /**
     * Create a new CssBottom
     */
    public CssBottom() {
	super();
    }

    /**
     * Create a new CssBottom
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException Values are incorrect
     */
    public CssBottom(ApplContext ac, CssExpression expression)
    	throws InvalidParamException {
	super(ac, expression);
    }

    public CssBottom(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {
	super(ac, expression, check);
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "bottom";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssBottom != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssBottom = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBottom();
	} else {
	    return ((Css1Style) style).getBottom();
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBottom &&
		value.equals(((CssBottom) property).value));
    }

}
