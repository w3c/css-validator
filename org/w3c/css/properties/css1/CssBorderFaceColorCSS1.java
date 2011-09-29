//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class CssBorderFaceColorCSS1 {

    CssValue face;

    /**
     * Create a new CssBorderFaceColor
     */
    public CssBorderFaceColorCSS1() {
	face = new org.w3c.css.values.CssColor();
    }

    /**
     * Create a new CssBorderFaceColor with a color property.
     *
     * @param color A color property
     */
    public CssBorderFaceColorCSS1(org.w3c.css.properties.css1.CssColorCSS1 color) {
	face = color.color;
    }

    /**
     * Create a new CssBorderFaceColor with an another CssBorderFaceColor
     *
     * @param another An another face.
     */
    public CssBorderFaceColorCSS1(CssBorderFaceColorCSS1 another) {
	face = another.face;
    }

    /**
     * Create a new CssBorderFaceColor with an expression
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException color is not a color
     */
    public CssBorderFaceColorCSS1(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();

	if (val instanceof org.w3c.css.values.CssColor) {
	    face = val;
	} else if (val instanceof CssIdent) {
	    face = new org.w3c.css.values.CssColor(ac, (String) val.get());
	} else {
	    throw new InvalidParamException("value", val.toString(),
					    "border-color", ac);
	}
	expression.next();
    }

    public CssBorderFaceColorCSS1(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the internal color
     */
    public CssValue getColor() {
	return face;
    }

    /**
     * Is the value of this face is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return false; // @@ FIXME face.isDefault();
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if(face != null) {
	    return face.toString();
	}
	return "";
    }

    /**
     * Compares two faces for equality.
     *
     * @param value The another faces.
     */
    public boolean equals(CssBorderFaceColorCSS1 color) {
	return this.face.equals(color.face); // FIXME
    }
}
