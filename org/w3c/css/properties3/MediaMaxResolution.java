//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.1  2002/12/24 13:18:36  sijtsche
 * new version for CSS3: value initial added
 *
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:14:07  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:30  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:39  plehegar
 * Nothing
 *
 * Revision 1.4  1997/08/06 17:30:25  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.3  1997/07/30 13:20:23  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/25 15:44:54  plehegar
 * bug fix in documentation
 *
 * Revision 1.1  1997/07/25 15:42:02  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssResolution;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties.CssProperty;

/**
 * @version $Revision$
 */
public class MediaMaxResolution extends CssProperty {

    CssValue value;

    /**
     * Create a new MediaMaxResolution
     */
    public MediaMaxResolution() {
		//empty
    }

    /**
     * Create a new MediaMaxResolution.
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public MediaMaxResolution(ApplContext ac, CssExpression expression) throws InvalidParamException {
		CssValue val = null;

		if (expression != null) {
			val = expression.getValue();
		}

		setByUser();

		if (val != null) {
			if (val instanceof CssResolution) {
				value = val;
			} else {
			    throw new InvalidParamException("value", expression.getValue(),
							    getPropertyName(), ac);
			}

			expression.next();
		}
    }

    /**
     * Returns the value of this property.
     */
    public Object get() {
	return value;
    }

    /**
     * Returns the name of this property.
     */
    public String getPropertyName() {
	return "max-resolution";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
		if (value != null) {
			return value.toString();
		} else {
			return null;
		}
    }


    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css3Style style0 = (Css3Style) style;
	if (style0.mediaMaxResolution != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.mediaMaxResolution = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getMediaMaxResolution();
	} else {
	    return ((Css3Style) style).mediaMaxResolution;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof MediaMaxResolution && value.equals(((MediaMaxResolution) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return false;
    }

}
