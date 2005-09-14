//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

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

public class CssDropInitialBeforeAlign extends CssProperty {

    CssValue dropvalue;

    static CssIdent capsheight = new CssIdent("caps-height");

    private static String[] values = {
	"caps-height", "baseline", "use-script", "before-edge",
	"text-before-edge", "after-edge", "text-after-edge", "central", "middle",
	"ideographic", "alphabetic", "hanging", "mathematical", "inherit", "initial"
    };

    /**
     * Create a new CssDropInitialBeforeAlign
     */
    public CssDropInitialBeforeAlign() {
		dropvalue = capsheight;
    }

    /**
     * Create a new CssDropInitialBeforeAlign
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssDropInitialBeforeAlign(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

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

    public CssDropInitialBeforeAlign(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssDropInitialBeforeAlign != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssDropInitialBeforeAlign = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getDropInitialBeforeAlign();
	}
	else {
	    return ((Css3Style) style).cssDropInitialBeforeAlign;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssDropInitialBeforeAlign &&
		dropvalue.equals(((CssDropInitialBeforeAlign) property).dropvalue));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "drop-initial-before-align";
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
	return (dropvalue == capsheight);
    }

}
