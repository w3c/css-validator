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
 *  <EM>Value:</EM> baseline || auto-script || before-edge ||
 *  text-before-edge || after-edge || text-after-edge || middle ||
 *  ideographic || lower || hanging || mathematical || inherit<BR>
 *  <EM>Initial:</EM>baseline<BR>
 *  <EM>Applies to:</EM>inline-level elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property specifies how an inline-level element is aligned with
 *   respect to its parent.
 */

public class CssDropInitialAfterAlign extends CssProperty {

    CssValue dropvalue;

    static CssIdent baseline = new CssIdent("baseline");

    private static String[] values = {
	"baseline", "use-script", "before-edge",
	"text-before-edge", "after-edge", "text-after-edge", "central", "middle",
	"ideographic", "alphabetic", "hanging", "mathematical", "inherit", "initial"
    };

    /**
     * Create a new CssDropInitialAfterAlign
     */
    public CssDropInitialAfterAlign() {
		dropvalue = baseline;
    }

    /**
     * Create a new CssDropInitialAfterAlign
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssDropInitialAfterAlign(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	int i = 0;
	for (; i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		dropvalue = val;
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
	if (((Css3Style) style).cssDropInitialAfterAlign != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssDropInitialAfterAlign = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getDropInitialAfterAlign();
	}
	else {
	    return ((Css3Style) style).cssDropInitialAfterAlign;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssDropInitialAfterAlign &&
		dropvalue.equals(((CssDropInitialAfterAlign) property).dropvalue));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "drop-initial-after-align";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return dropvalue;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return dropvalue.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return dropvalue.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (dropvalue == baseline);
    }

}
