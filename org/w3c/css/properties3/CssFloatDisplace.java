//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *  <P>
 *  <EM>Value:</EM> none | block | line | list-item | marker | run-in | compact |
 *  table-row | table-cell | table-row-group | table-header-group | table-footer-group |
 *  table-column | table-column-group | table-caption | ruby-text | ruby-base |
 *  ruby-base-group | ruby-text-group | inherited <BR>
 *  <EM>Initial:</EM>line<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class CssFloatDisplace extends CssProperty {

    CssValue fd;

    static CssIdent line = new CssIdent("line");

    private static String[] values = {
		"line", "indent", "block", "block-within-page", "initial", "inherit"
    };

    /**
     * Create a new CssFloatDisplace
     */
    public CssFloatDisplace() {
		fd = line;
    }

    /**
     * Create a new CssFloatDisplace
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssFloatDisplace(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	int i = 0;
	for (; i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		fd = val;
		expression.next();
		break;
	    }
	}
	if (i == values.length) {
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
	if (((Css3Style) style).cssFloatDisplace != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssFloatDisplace = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getFloatDisplace();
	}
	else {
	    return ((Css3Style) style).cssFloatDisplace;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssFloatDisplace &&
		fd.equals(((CssFloatDisplace) property).fd));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
		return "float-displace";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
		return fd;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
		return fd.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
		return fd.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
		return (fd == line);
    }

}
