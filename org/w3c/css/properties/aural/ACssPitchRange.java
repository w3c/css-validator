//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;


/**
 * <H3> &nbsp;&nbsp; 'pitch-range' <span class="notes">(could be
 * combined with 'pitch' ?) or 'inflection'</span></H3>
 *
 * <P>
 * <EM>Value: </EM>&lt;number&gt;|inherit<BR>
 * <em>Initial:</EM>50%<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> yes<BR>
 * <EM>Percentage values:</EM> relative to..
 *
 * <p>Specifies variation in average pitch. A pitch range of 0% produces
 * a flat, monotonic voice. A pitch range of 50% produces normal
 * inflection.  Pitch ranges greater than 50% produce animated voices.
 *
 *
 * @version $Revision$
 */
public class ACssPitchRange extends ACssProperty {

    CssValue value;

    static CssValue DefaultValue = new CssNumber(null, 50);

    /**
     * Create a new ACssPitchRange
     */
    public ACssPitchRange() {
	value = DefaultValue;
    }

    /**
     * Creates a new ACssPitchRange
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public ACssPitchRange(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this();

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	int index;

	setByUser();
	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	} else if (val instanceof CssNumber) {
	    float f = ((CssNumber) val).getValue();
	    if ((f < 0) || (f > 100)) {
		throw new InvalidParamException("range", null, ac);
	    }
	    value = val;
	    expression.next();
	    return;
	}

	throw new InvalidParamException("value",
					expression.getValue().toString(),
					getPropertyName(), ac);
    }

    public ACssPitchRange(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }


    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "pitch-range";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
	return value.equals(inherit);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value.toString();
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((ACssStyle) style).acssPitchRange != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssPitchRange = this;
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof ACssPitchRange &&
		value.equals(((ACssPitchRange) property).value));
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getPitchRange();
	} else {
	    return ((ACssStyle) style).acssPitchRange;
	}
    }

}

