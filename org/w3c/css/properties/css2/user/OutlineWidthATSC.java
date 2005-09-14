//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 *
 */
package org.w3c.css.properties.css2.user;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class OutlineWidthATSC extends UserProperty {

    CssValue value;

    private static CssIdent thin = new CssIdent("thin");
    private static CssIdent medium = new CssIdent("medium");
    private static CssIdent thick = new CssIdent("thick");

    /**
     * Create a new OutlineWidthATSC
     */
    public OutlineWidthATSC() {
	value = medium;
    }

    /**
     * Create a new OutlineWidthATSC from an another OutlineWidthATSC
     *
     * @param another The another side.
     */
    public OutlineWidthATSC(OutlineWidthATSC another) {
	value = another.value;
    }

    /**
     * Create a new OutlineWidthATSC
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public OutlineWidthATSC(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	setByUser();

	ac.getFrame().addWarning("atsc", val.toString());

	if (val instanceof CssLength) {
	    float f = ((Float) val.get()).floatValue();
	    if (f >= 0) {
		this.value = val;
	    } else {
		throw new InvalidParamException("negative-value",
						val.toString(), ac);
	    }
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getLength();
	} else if (val.equals(thin)) {
	    value = thin;
	} else if (val.equals(medium)) {
	    value = medium;
	} else if (val.equals(thick)) {
	    value = thick;
	} else if (val.equals(inherit)) {
	    value = CssProperty.inherit;
	} else {
	    throw new InvalidParamException("value", val.toString(),
					    "width", ac);
	}

	expression.next();
    }

    public OutlineWidthATSC(ApplContext ac, CssExpression expression)
    	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the internal value
     */
    public Object get() {
	return value;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value.equals(inherit);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value.toString();
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	OutlineATSC outline = ((Css2Style) style).outlineATSC;
	if (outline.width != null) {
	    style.addRedefinitionWarning(ac, this);
	}
	outline.width = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getOutlineWidthATSC();
	} else {
	    return ((Css2Style) style).outlineATSC.width;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof OutlineWidthATSC &&
		value.equals(((OutlineWidthATSC) property).value));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "outline-width";
    }

}



