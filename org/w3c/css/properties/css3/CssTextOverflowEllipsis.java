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
 *
 */

    public class CssTextOverflowEllipsis extends CssProperty {

	String overflowellipsis;

	/**
	 * Create a new CssTextOverflowEllipsis
	 */
	public CssTextOverflowEllipsis() {
	    overflowellipsis = "...";
	}
	
	/**
	 * Create a new CssTextOverflowEllipsis
	 *
	 *
	 */
	public CssTextOverflowEllipsis(ApplContext ac, CssExpression expression,
		boolean check) throws InvalidParamException {
	    setByUser();
	    CssValue val = expression.getValue();
	    CssValue val2 = null;
	    
	    if (val instanceof CssIdent) {
		overflowellipsis = val.toString();
		expression.next();
	    }
	    else if (val instanceof CssURL) {
		overflowellipsis = val.toString();
		expression.next();
	    }
	    else {
		throw new InvalidParamException("value", val.toString(),
			getPropertyName(), ac);
	    }
	    
	    val2 = expression.getValue();
	    
	    if (val2 != null) {
		
		if (val2 instanceof CssIdent) {
		    overflowellipsis += " " + val2.toString();
		    expression.next();
		}
		else if (val2 instanceof CssURL) {
		    overflowellipsis += " " + val2.toString();
		    expression.next();
		}
		else {
		    throw new InvalidParamException("value",
			    val2.toString(), getPropertyName(), ac);
		}
	    }
	}

	public CssTextOverflowEllipsis(ApplContext ac, CssExpression expression)
		throws InvalidParamException {
	    this(ac, expression, false);
	}
	
	/**
	 * Add this property to the CssStyle.
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
	    if (((Css3Style) style).cssTextOverflowEllipsis != null)
		style.addRedefinitionWarning(ac, this);
	    ((Css3Style) style).cssTextOverflowEllipsis = this;

	}

	/**
	 * Get this property in the style.
	 *
	 * @param style The style where the property is
	 * @param resolve if true, resolve the style to find this property
	 */
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((Css3Style) style).getTextOverflowEllipsis();
	    } else {
		return ((Css3Style) style).cssTextOverflowEllipsis;
	    }
	}

	/**
	 * Compares two properties for equality.
	 *
	 * @param value The other property.
	 */
	public boolean equals(CssProperty property) {
	    return (property instanceof CssTextOverflowEllipsis &&
		    overflowellipsis.equals( ((CssTextOverflowEllipsis) property).overflowellipsis));
	}

	/**
	 * Returns the name of this property
	 */
	public String getPropertyName() {
	    return "text-overflow-ellipsis";
	}

	/**
	 * Returns the value of this property
	 */
	public Object get() {
	    return overflowellipsis;
	}

	/**
	 * Returns true if this property is "softly" inherited
	 */
	public boolean isSoftlyInherited() {
	    return overflowellipsis.equals(inherit);
	}

	/**
	 * Returns a string representation of the object
	 */
	public String toString() {
	    return overflowellipsis.toString();
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
	    return overflowellipsis.equals("...");
	}

    }
