//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision$
 */
public class CssBorderTopColorCSS2 extends CssProperty {

    CssBorderFaceColorCSS2 face;

    /**
     * Create a new CssBorderTopColorCSS2
     */
    public CssBorderTopColorCSS2() {
	face = new CssBorderFaceColorCSS2();
    }

    /**
     * Create a new CssBorderTopColorCSS2 with an another CssBorderFaceColorCSS2
     * @param another The another side.
     */
    public CssBorderTopColorCSS2(CssBorderFaceColorCSS2 another) {
	setByUser();

	face = another;
    }

    /**
     * Create a new CssBorderTopColorCSS2
     *
     * @param expression The expression fir this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderTopColorCSS2(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	face = new CssBorderFaceColorCSS2(ac, expression);
    }

    public CssBorderTopColorCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression,false);
    }

    /**
     * @return Returns the face.
     */
    public CssBorderFaceColorCSS2 getFace() {
        return face;
    }

    /**
     * @param face The face to set.
     */
    public void setFace(CssBorderFaceColorCSS2 face) {
        this.face = face;
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
	CssBorderTopCSS2 top = ((Css1Style) style).cssBorderCSS2.top;
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
	    return ((Css1Style) style).getBorderTopColorCSS2();
	} else {
	    return ((Css1Style) style).cssBorderCSS2.getTop().color;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderTopColorCSS2 &&
		face.equals(((CssBorderTopColorCSS2) property).face));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     *
     * @see #print(CssPrinterStyle)
     */
    public void print(CssPrinterStyle printer) {
	if (face != null && !face.isDefault())
	    printer.print(this);
    }

}
