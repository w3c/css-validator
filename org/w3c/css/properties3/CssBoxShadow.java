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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssColor;
import org.w3c.css.values.CssOperator;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;



public class CssBoxShadow extends CssProperty implements CssOperator {

    String value = "";
    ApplContext ac;
	CssIdent none = new CssIdent("none");

    /**
     * Create new CssBoxShadow
     */
    public CssBoxShadow() {
		value = "none";
    }

    /**
     * Create new CssBoxShadow
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBoxShadow(ApplContext ac, CssExpression expression) throws InvalidParamException {
		setByUser();
		CssValue val = expression.getValue();
		char op = COMMA;

		if (val instanceof CssIdent) {
			if (val.equals(none)) {
				value = "none";
				expression.next();
			} else if (val.equals(inherit)) {
				value = "inherit";
				expression.next();
			}
		} else {

			// <length> <length> <length>? || <color> [, <length> <length> <length>? || <color>]+

			int lengthcounter = 0;
			int runs = 1;

			for (int i = 0; i < expression.getCount(); i++) {

				if (op != COMMA) {
					throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
				} else if (runs != 1) {
					value += ", ";
				}

				val = expression.getValue();

				if (val != null) {

					while (val instanceof CssLength && lengthcounter < 3) {
						value += val.toString() + " ";
						expression.next();
						i++;
						val = expression.getValue();
						lengthcounter++;
					}

					if (lengthcounter == 2 || lengthcounter == 3) {
						if (val instanceof CssColor) {
							value += val.toString() + " ";
						} else {
							value += (new org.w3c.css.values.CssColor(ac, (String) val.get())).toString() + " ";
						}
					} else {
					    throw new InvalidParamException("value", expression.getValue(),
							getPropertyName(), ac);
					}
				} else {

					if (runs < 2) {
						throw new InvalidParamException("value", expression.getValue(),
							getPropertyName(), ac);
					}

					value = value.trim();
					return;
				}

				op = expression.getOperator();
				lengthcounter = 0;
				expression.next();
				runs++;
			}
		}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
		if (((Css3Style) style).cssBoxShadow != null)
		    style.addRedefinitionWarning(ac, this);
		((Css3Style) style).cssBoxShadow = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
		if (resolve) {
		    return ((Css3Style) style).getBoxShadow();
		} else {
		    return ((Css3Style) style).cssBoxShadow;
		}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
		return (property instanceof CssBoxShadow &&
                value.equals( ((CssBoxShadow) property).value));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
		return "box-shadow";
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
		return value.equals(none);
    }

}
