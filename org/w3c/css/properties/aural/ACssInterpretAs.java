//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssDate;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTime;
import org.w3c.css.values.CssValue;

/**

 *
 * @version $Revision$
 */
public class ACssInterpretAs extends ACssProperty {

    CssValue value;

    private static CssTime defaultValue;

	private static String[] interpretas = { "currency", "measure", "telephone", "address", "name", "net" };

    /**
     * Create a new ACssInterpretAs
     */
    public ACssInterpretAs() {
	// Initial is User Agent Specific
	if (defaultValue == null) {
	    defaultValue = new CssTime(ACssProperties.getValue(this, "default"));
	}
	value = defaultValue;
    }

    /**
     * Creates a new ACssInterpretAs
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */
    public ACssInterpretAs(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();

	setByUser();

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	} else if (val instanceof CssTime) {
	    float num = ((Float) val.get()).floatValue();
	    if (num < 0) {
		throw new InvalidParamException("negative-value",
			val.toString(), ac);
	    }
	    value = val;
	    expression.next();
	    return;
	} else if (val instanceof CssDate) {
	    value = val;
	    expression.next();
	    return;
	} else if (val instanceof CssIdent) {
	    for (int i=0; i < interpretas.length; i++) {
		if (val.toString().equals(interpretas[i])) {
		    value = val;
		    expression.next();
		    return;
		}
	    }
	}

	throw new InvalidParamException("value", val.toString(),
		getPropertyName(), ac);
    }

    public ACssInterpretAs(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the current value
     */
    public Object get() {
	return value;
    }

    /**
     * Returns some usable value of this property...
     */
    public int getValue() { // vm
	return ((Float) value.get()).intValue();
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (value != null)
	    return value.toString();
	else
	    return null;
    }


    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "interpret-as";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((ACssStyle) style).acssInterpretAs != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssInterpretAs = this;
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	if (value != null) {
	    return (property instanceof ACssInterpretAs &&
		    value.equals(((ACssInterpretAs) property).value));
	} else {
	    return false;
	}
    }


    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getInterpretAs();
	} else {
	    return ((ACssStyle) style).acssInterpretAs;
	}
    }

}
