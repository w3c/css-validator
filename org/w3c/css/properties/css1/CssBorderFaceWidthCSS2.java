//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import java.util.HashSet;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class CssBorderFaceWidthCSS2 {

    public static HashSet<CssIdent> acceptable_values;
    static {
	acceptable_values = new HashSet<CssIdent>();
	acceptable_values.add(CssIdent.getIdent("thin"));
	acceptable_values.add(CssIdent.getIdent("medium"));
	acceptable_values.add(CssIdent.getIdent("thick"));
    }

    CssValue value;
    
    /**
     * Create a new CssBorderFaceWidthCSS2
     */
    public CssBorderFaceWidthCSS2() {
	value = CssIdent.getIdent("medium");
    }

    /**
     * Create a new CssBorderFaceWidthCSS2 from an another CssBorderFaceWidthCSS2
     *
     * @param another The another side.
     */
    public CssBorderFaceWidthCSS2(CssBorderFaceWidthCSS2 another) {
	value = another.value;
    }

    /**
     * Create a new CssBorderFaceWidth
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderFaceWidthCSS2(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();

	switch (val.getType()) {
	case CssTypes.CSS_NUMBER:
	    val = ((CssNumber) val).getLength();
	case CssTypes.CSS_LENGTH:
	    float f = ((Float) val.get()).floatValue();
	    if (f >= 0) {
		this.value = val;
	    } else {
		throw new InvalidParamException("negative-value", val.toString(), ac);
	    }
	    break;
	case CssTypes.CSS_IDENT:
	    CssIdent ci = (CssIdent) val;
	    if (CssProperty.inherit.equals(ci)) {
		value = CssProperty.inherit;
		break;
	    } 
	    if (acceptable_values.contains(ci)) {
		// use the cached version
		value = CssIdent.getIdent(ci.toString());
		break;
	    }
	default:
	    throw new InvalidParamException("value", val.toString(), "width", ac);
	}
	expression.next();
    }

    public CssBorderFaceWidthCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the internal value
     */
    public CssValue getValue() {
	return value;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if(value != null) {
	    return value.toString();
	}
	return "";
    }

    /**
     * Compares two sides for equality.
     *
     * @param value The another side.
     */
    public boolean equals(CssBorderFaceWidthCSS2 another) {
	return value.equals(another.value); // FIXME
    }



}



