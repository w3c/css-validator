//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYDown 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyDown statement at
// http://www.w3.org/Consortium/Legal/copyDown-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssURL;

public class CssNavDown extends CssProperty {

    CssValue navDown;

    static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssNavDown
     */
    public CssNavDown() {
	// nothing to do
    }

    /**
     * Create a new CssNavDown
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssNavDown(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    navDown = val;
	    expression.next();
	} else if (val.equals(auto)) {
	    navDown = val;
	    expression.next();
	} else if (val instanceof CssURL) {
	    navDown = val;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssNavDown != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssNavDown = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getNavDownCSS3();
	}
	else {
	    return ((Css3Style) style).cssNavDown;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssNavDown &&
		navDown.equals(((CssNavDown) property).navDown));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "nav-down";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return navDown;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return navDown.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return navDown.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (navDown == auto);
    }

}
