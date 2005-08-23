//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYLeft 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyLeft statement at
// http://www.w3.org/Consortium/Legal/copyLeft-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

public class CssOutlineOffset extends CssProperty {

    CssValue outlineOffset;
    ApplContext ac;

    static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssOutlineOffset
     */
    public CssOutlineOffset() {
	// nothing to do
    }

    /**
     * Create a new CssOutlineOffset
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssOutlineOffset(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	this.ac = ac;
	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    outlineOffset = val;
	    expression.next();
	} else if (val instanceof CssLength) {
	    outlineOffset = val;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssOutlineOffset(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssOutlineOffset != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssOutlineOffset = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getOutlineOffset();
	}
	else {
	    return ((Css3Style) style).cssOutlineOffset;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssOutlineOffset &&
		outlineOffset.equals(((CssOutlineOffset) property).outlineOffset));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "outline-offset";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return outlineOffset;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return outlineOffset.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return outlineOffset.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {

		CssNumber cssnum = new CssNumber(ac, (float) 0.0);
        return outlineOffset.toString() == cssnum.toString();
    }

}
