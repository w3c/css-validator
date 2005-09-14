//
// $Id$
// From Sijtsche Smeman (sijtsche@wisdom.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'background-size'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> [&lt;length&gt; | &lt;percentage&gt | auto]{1.2};<BR>
 *   <EM>Initial:</EM> auto<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> see text<BR>
 *   <P>
 *   In case there is a 'background-image', the background-size can be used to stretch or shrink it.
 *   If the property has only one value, it applies both horizontally and vertically. If there are
 *   two, the first one refers to the width, the second to the height of the background image.
 */
public class CssBackgroundSize extends CssProperty  {

    public CssValue value1 = null;
    CssValue value2 = null;
    CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssBackgroundSize
     */
    public CssBackgroundSize() {
    }

    /**
     * Create a new CssBackgroundSize
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBackgroundSize(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	for (int i = 0; i < 2; i++) {

	    CssValue val = expression.getValue();

	    setByUser();

	    if ((i == 0) || (i == 1 && val != null)) {

		if (val.equals(auto)) {
		    if (i == 0) {
			value1 = auto;
		    } else {
			value2 = auto;
		    }
		} else if (val instanceof CssLength || val instanceof CssPercentage) {
		    if (i == 0) {
			value1 = val;
		    } else {
			value2 = val;
		    }
		} else if (val instanceof CssNumber) {
		    if (i == 0) {
			value1 = ((CssNumber) val).getLength();
		    } else {
			value2 = ((CssNumber) val).getLength();
		    }
		} else {
		    throw new InvalidParamException("value", val.toString(),
			    getPropertyName(), ac);
		}

		expression.next();
	    }
	}
    }

    public CssBackgroundSize(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssBackgroundSize != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBackgroundSize = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getCssBackgroundSize();
	}
	else {
	    return ((Css3Style) style).cssBackgroundSize;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	//return (property instanceof CssBackgroundSize &&
	//	value.equals(((CssBackgroundSize) property).cssBackgroundSize));
    	return false;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "background-size";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return value1;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return false;
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	if (value2 == null) {
	    return value1.toString();
	} else {
	    return value1.toString() + " " + value2.toString();
	}
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value1 == auto;
    }

}
