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

public class CssLineStackingRuby extends CssProperty {

    CssValue linestackingruby;

    private static CssIdent initial = new CssIdent("initial");
    private static CssIdent excluderuby = new CssIdent("exclude-ruby");
    private static CssIdent includeruby = new CssIdent("include-ruby");

    /**
     * Create a new CssLineStackingRuby
     */
    public CssLineStackingRuby() {
		linestackingruby = excluderuby;
    }

    /**
     * Create a new CssLineStackingRuby
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssLineStackingRuby(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(initial)) {
		linestackingruby = initial;
		expression.next();
	}
	else if (val.equals(excluderuby)) {
		linestackingruby = excluderuby;
		expression.next();
	}
	else if (val.equals(includeruby)) {
		linestackingruby = includeruby;
		expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssLineStackingRuby(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssLineStackingRuby != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssLineStackingRuby = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getLineStackingRuby();
	}
	else {
	    return ((Css3Style) style).cssLineStackingRuby;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssLineStackingRuby &&
		linestackingruby.equals(((CssLineStackingRuby) property).linestackingruby));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "line-stacking-ruby";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return linestackingruby;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return linestackingruby.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return linestackingruby.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return linestackingruby == excluderuby;
    }

}
