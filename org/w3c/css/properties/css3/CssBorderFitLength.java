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
import org.w3c.css.values.CssValue;


public class CssBorderFitLength extends CssProperty {

    String value = "";

    CssIdent repeat = new CssIdent("repeat");

    private static String[] values = {
	"clip", "repeat", "scale", "stretch", "overwrite", "overflow", "space"
    };

    /**
     * Create a new CssBorderFitLength
     */
    public CssBorderFitLength() {
	value = "repeat";
    }

    /**
     * Create a new CssBorderFitLength
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssBorderFitLength(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();
	int i = 0;

	if (expression.getCount() <= 4) {

	    val = expression.getValue();

	    if (val != null) {

		for (; i < expression.getCount(); i++) {

		    int j = 0;
		    for (; j < values.length; j++) {
			if (val.toString().equals(values[j])) {
			    value += val.toString() + " ";
			    expression.next();
			    break;
			}
		    }

		    if (j == values.length) {
			throw new InvalidParamException("value", expression.getValue(),
				getPropertyName(), ac);
		    }
		}
	    }

	} else {
	    throw new InvalidParamException("value", expression.getValue(),
		    getPropertyName(), ac);
	}

    }

    public CssBorderFitLength(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssBorderFitLength != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBorderFitLength = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getBorderFitLength();
	}
	else {
	    return ((Css3Style) style).cssBorderFitLength;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderFitLength &&
		value.equals(((CssBorderFitLength) property).value));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border-fit-length";
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
	return value.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value.equals("repeat");
    }

}
