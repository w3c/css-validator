//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;

/**
 * <P>
 * <EM>Value:</EM> auto || sRGB || &lt;uri&gt; || inherit<BR>
 * <EM>Initial:</EM>auto<BR>
 * <EM>Applies to:</EM>all elements or replaced elements??<BR>
 * <EM>Inherited</EM>yes<BR>
 * <EM>Percentages:</EM>no<BR>
 * <EM>Media:</EM>visual
 * <P>
 * This property permits the specification of a source color profile other
 *  than the default.
 */

    public class CssColorProfile extends CssProperty {

	CssValue colorprofile;

	static CssIdent auto = new CssIdent("auto");

	/**
	 * Create a new CssColorProfile
	 */
	public CssColorProfile() {
	    colorprofile = auto;
	}

	/**
	 * Create a new CssColorProfile
	 *
	 * @param expression The expression for this property
	 * @exception InvalidParamException Values are incorrect
	 */
	public CssColorProfile(ApplContext ac, CssExpression expression,
		boolean check) throws InvalidParamException {
	    setByUser();
	    CssValue val = expression.getValue();
	    if (val instanceof CssIdent) {
		if (val.equals(inherit)) {
		    colorprofile = inherit;
		    expression.next();
		}
		else if (val.equals(auto)) {
		    colorprofile = auto;
		    expression.next();
		}
		else {
		    colorprofile = new org.w3c.css.values.CssColor(ac,
			    (String) val.get());
		    expression.next();
		}
	    }
	    else if (val instanceof CssURL) {
		colorprofile = val;
		expression.next();
	    }
	    else if (val instanceof org.w3c.css.values.CssColor) {
		colorprofile = val;
		expression.next();
	    }
	    else {
		throw new InvalidParamException("value", expression.getValue(),
			getPropertyName(), ac);
	    }
        }

	public CssColorProfile(ApplContext ac, CssExpression expression)
		throws InvalidParamException {
	    this(ac, expression, false);
	}

	/**
	 * Add this property to the CssStyle.
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
	    if (((Css3Style) style).cssColorProfile != null)
		style.addRedefinitionWarning(ac, this);
	    ((Css3Style) style).cssColorProfile = this;
	}

	/**
	 * Get this property in the style.
	 *
	 * @param style The style where the property is
	 * @param resolve if true, resolve the style to find this property
	 */
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((Css3Style) style).getColorProfile();
	    } else {
		return ((Css3Style) style).cssColorProfile;
	    }
	}

       /**
        * Compares two properties for equality.
        *
        * @param value The other property.
        */
       public boolean equals(CssProperty property) {
	   return (property instanceof CssColorProfile &&
                colorprofile.equals( ((CssColorProfile) property).colorprofile));
       }

	/**
	 * Returns the name of this property
	 */
	public String getPropertyName() {
	    return "color-profile";
	}

	/**
	 * Returns the value of this property
	 */
	public Object get() {
	    return colorprofile;
	}

	/**
	 * Returns true if this property is "softly" inherited
	 */
	public boolean isSoftlyInherited() {
	    return colorprofile.equals(inherit);
	}

	/**
	 * Returns a string representation of the object
	 */
	public String toString() {
	    return colorprofile.toString();
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
	    return colorprofile == auto;
	}

    }
