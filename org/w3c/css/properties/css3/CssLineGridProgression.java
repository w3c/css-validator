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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssValue;

/**
 *
 */

    public class CssLineGridProgression extends CssProperty {

	CssValue linegridprog;

	private static CssIdent normal = new CssIdent("normal");
	private static CssIdent gridheight = new CssIdent("grid-height");

	/**
	 * Create a new CssLineGridProgression
	 */
	public CssLineGridProgression() {
	    linegridprog = normal;
	}

	/**
	 * Create a new CssLineGridProgression
	 *
	 *
	 */
	public CssLineGridProgression(ApplContext ac, CssExpression expression,
		boolean check) throws InvalidParamException {
	    setByUser();
	    CssValue val = expression.getValue();
	    if (val.equals(normal)) {
			linegridprog = normal;
			expression.next();
	    }
	    else if (val.equals(gridheight)) {
			linegridprog = gridheight;
			expression.next();
	    }
	    else if (val instanceof CssLength) {
			linegridprog = val;
			expression.next();
	    }
	    else if (val.equals(inherit)) {
			linegridprog = inherit;
			expression.next();
	    }

	    else {
		throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	    }
	}

	public CssLineGridProgression(ApplContext ac, CssExpression expression)
		throws InvalidParamException {
	    this(ac, expression, false);
	}
	
	/**
	 * Add this property to the CssStyle.
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
	    if (((Css3Style) style).cssLineGridProgression != null)
		style.addRedefinitionWarning(ac, this);
	    ((Css3Style) style).cssLineGridProgression = this;

	}

	/**
	 * Get this property in the style.
	 *
	 * @param style The style where the property is
	 * @param resolve if true, resolve the style to find this property
	 */
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((Css3Style) style).getLineGridProgression();
	    } else {
		return ((Css3Style) style).cssLineGridProgression;
	    }
	}

	/**
	 * Compares two properties for equality.
	 *
	 * @param value The other property.
	 */
	public boolean equals(CssProperty property) {
	    return (property instanceof CssLineGridProgression &&
		    linegridprog.equals( ((CssLineGridProgression) property).linegridprog));
	}

	/**
	 * Returns the name of this property
	 */
	public String getPropertyName() {
	    return "line-grid-progression";
	}

	/**
	 * Returns the value of this property
	 */
	public Object get() {
	    return linegridprog;
	}

	/**
	 * Returns true if this property is "softly" inherited
	 */
	public boolean isSoftlyInherited() {
	    return linegridprog.equals(inherit);
	}

	/**
	 * Returns a string representation of the object
	 */
	public String toString() {
	    return linegridprog.toString();
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
	    return linegridprog == normal;
	}

    }
