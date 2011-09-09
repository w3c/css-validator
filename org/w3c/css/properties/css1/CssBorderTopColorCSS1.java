//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision$
 */
public class CssBorderTopColorCSS1 extends CssProperty {

    CssBorderFaceColorCSS1 face;

    /**
     * Create a new CssBorderTopColorCSS1
     */
    public CssBorderTopColorCSS1() {
	face = new CssBorderFaceColorCSS1();
    }

    /**
     * Create a new CssBorderTopColorCSS1 with an another CssBorderFaceColorCSS1
     * @param another The another side.
     */
    public CssBorderTopColorCSS1(CssBorderFaceColorCSS1 another) {
	setByUser();

	face = another;
    }

    /**
     * Create a new CssBorderTopColorCSS1
     *
     * @param expression The expression fir this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderTopColorCSS1(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	face = new CssBorderFaceColorCSS1(ac, expression);
    }

    public CssBorderTopColorCSS1(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression,false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return face;
    }

    /**
     * Returns the color
     */
    public CssValue getColor() {
	if (face != null) {
	    return face.getColor();
	} else {
	    return null;
	}
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
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border-top-color";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderTopCSS1 top = ((Css1Style) style).cssBorderCSS1.top;
	if (top.color != null)
	    style.addRedefinitionWarning(ac, this);
	top.color = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBorderTopColorCSS1();
	} else {
	    return ((Css1Style) style).cssBorderCSS1.getTop().color;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderTopColorCSS1 &&
		face.equals(((CssBorderTopColorCSS1) property).face));
    }

}
