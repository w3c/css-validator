//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.values.CssURL;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssNumber;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;



public class CssBorderCornerImage extends CssProperty {

    String value = "";
    ApplContext ac;
	CssIdent none = new CssIdent("none");
	CssIdent cont = new CssIdent("continue");

    /**
     * Create new CssBorderCornerImage
     */
    public CssBorderCornerImage() {
		value = "none";
    }

    /**
     * Create new CssBorderCornerImage
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderCornerImage(ApplContext ac, CssExpression expression) throws InvalidParamException {
		setByUser();
		CssValue val = null;

		if (!(expression.getCount() <= 4)) {
		    throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
		}

		if (expression.getCount() == 1) {
			if (val.equals(cont)) {
				value = "continue";
			} else if (val.equals(none)) {
				value = "none";
			} else if (val instanceof CssURL) {
				value = val.toString();
			} else {
				throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
			}
		} else {

			for (int i=0; i < expression.getCount(); i++) {

				val = expression.getValue();

				if (val != null) {
					if (val.equals(cont)) {
						value += "continue ";
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

		}

		value = value.trim();


    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
		if (((Css3Style) style).cssBorderCornerImage != null)
		    style.addRedefinitionWarning(ac, this);
		((Css3Style) style).cssBorderCornerImage = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
		if (resolve) {
		    return ((Css3Style) style).getBorderCornerImage();
		} else {
		    return ((Css3Style) style).cssBorderCornerImage;
		}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
		return (property instanceof CssBorderCornerImage &&
                value.equals( ((CssBorderCornerImage) property).value));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
		return "border-corner-image";
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
