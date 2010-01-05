//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *
 */

public class CssLineStackingStrategy extends CssProperty {

    CssValue strategy;

    static CssIdent inlinelineheight = new CssIdent("inline-line-height");

    private static String[] values = {
	"inline-line-height", "block-line-height", "max-height", "grid-height",
	"inherit", "initial"
    };

    /**
     * Create a new CssLineStackingStrategy
     */
    public CssLineStackingStrategy() {
		strategy = inlinelineheight;
    }

    /**
     * Create a new CssLineStackingStrategy
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssLineStackingStrategy(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	int i = 0;
	for (; i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		strategy = val;
		expression.next();
		break;
	    }
	}
	if (i == values.length) {
	    throw new InvalidParamException("value", val,
					    getPropertyName(), ac);
	}
    }

    public CssLineStackingStrategy(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssLineStackingStrategy != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssLineStackingStrategy = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getLineStackingStrategy();
	}
	else {
	    return ((Css3Style) style).cssLineStackingStrategy;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssLineStackingStrategy &&
		strategy.equals(((CssLineStackingStrategy) property).strategy));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "line-stacking-strategy";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return strategy;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return strategy.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return strategy.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (strategy == inlinelineheight);
    }

}
