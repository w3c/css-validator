//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *
 */

    public class CssLinefeedTreatment extends CssProperty {

	CssValue linefeedtreat;

	private static CssIdent auto = new CssIdent("auto");
	private static CssIdent ignore = new CssIdent("ignore");
	private static CssIdent preserve = new CssIdent("preserve");
	private static CssIdent treatasspace = new CssIdent("treat-as-space");
	private static CssIdent treataszerowidthspace = new CssIdent("treat-as-zero-width-space");
	private static CssIdent ignoreifafterlinefeed = new CssIdent("ignore-if-after-linefeed");


	/**
	 * Create a new CssLinefeedTreatment
	 */
	public CssLinefeedTreatment() {
	    linefeedtreat = treatasspace;
	}

	/**
	 * Create a new CssLinefeedTreatment
	 *
	 *
	 */
	public CssLinefeedTreatment(ApplContext ac, CssExpression expression,
		boolean check) throws InvalidParamException {
	    setByUser();
	    CssValue val = expression.getValue();
	    if (val.equals(auto)) {
		linefeedtreat = auto;
		expression.next();
	    }
	    else if (val.equals(ignore)) {
		linefeedtreat = ignore;
		expression.next();
	    }
	    else if (val.equals(treatasspace)) {
		linefeedtreat = treatasspace;
		expression.next();
	    }
	    else if (val.equals(preserve)) {
		linefeedtreat = preserve;
		expression.next();
	    }
	    else if (val.equals(treataszerowidthspace)) {
		linefeedtreat = treataszerowidthspace;
		expression.next();
	    }
	    else if (val.equals(ignoreifafterlinefeed)) {
		linefeedtreat = ignoreifafterlinefeed;
		expression.next();
	    }
	    else if (val.equals(inherit)) {
		linefeedtreat = inherit;
		expression.next();
	    }
	    
	    else {
		throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	    }
	}
	
	public CssLinefeedTreatment(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	    this(ac, expression, false);
	}
	
	/**
	 * Add this property to the CssStyle.
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
	    if (((Css3Style) style).cssLinefeedTreatment != null)
		style.addRedefinitionWarning(ac, this);
	    ((Css3Style) style).cssLinefeedTreatment = this;

	}

	/**
	 * Get this property in the style.
	 *
	 * @param style The style where the property is
	 * @param resolve if true, resolve the style to find this property
	 */
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((Css3Style) style).getLinefeedTreatment();
	    } else {
		return ((Css3Style) style).cssLinefeedTreatment;
	    }
	}

	/**
	 * Compares two properties for equality.
	 *
	 * @param value The other property.
	 */
	public boolean equals(CssProperty property) {
	    return (property instanceof CssLinefeedTreatment &&
		    linefeedtreat.equals( ((CssLinefeedTreatment) property).linefeedtreat));
	}

	/**
	 * Returns the name of this property
	 */
	public String getPropertyName() {
	    return "linefeed-treatment";
	}

	/**
	 * Returns the value of this property
	 */
	public Object get() {
	    return linefeedtreat;
	}

	/**
	 * Returns true if this property is "softly" inherited
	 */
	public boolean isSoftlyInherited() {
	    return linefeedtreat.equals(inherit);
	}

	/**
	 * Returns a string representation of the object
	 */
	public String toString() {
	    return linefeedtreat.toString();
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
	    return linefeedtreat == treatasspace;
	}

    }
