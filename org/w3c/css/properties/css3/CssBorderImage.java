//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;



public class CssBorderImage extends CssProperty {

    String value = "";
    ApplContext ac;
    CssIdent none = new CssIdent("none");

    /**
     * Create new CssBorderImage
     */
    public CssBorderImage() {
	value = "none";
    }

    /**
     * Create new CssBorderImage
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderImage(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = null;

	if (!(expression.getCount() <= 4)) {
	    throw new InvalidParamException("value", expression.getValue(),
		    getPropertyName(), ac);
	}

	for (int i=0; i < expression.getCount(); i++) {

	    val = expression.getValue();

	    if (val != null) {

		if (val.equals(none)) {
		    value += "none" + " ";
		} else if (val instanceof CssURL) {
		    value += val.toString() + " ";
		} else {
		    throw new InvalidParamException("value", expression.getValue(),
			    getPropertyName(), ac);
		}

	    } else {
		break;
	    }

	    expression.next();
	}

	value = value.trim();
    }

    public CssBorderImage(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssBorderImage != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBorderImage = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getBorderImage();
	} else {
	    return ((Css3Style) style).cssBorderImage;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderImage &&
		value.equals( ((CssBorderImage) property).value));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border-image";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return value.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return value;
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	CssNumber cssnum = new CssNumber(ac, (float) 1.0);
	return value == cssnum.toString();
    }

}
