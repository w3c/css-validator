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
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

public class CssBlockProgression extends CssProperty implements CssOperator {

    CssValue blockprog;
    ApplContext ac;
    CssIdent tb = new CssIdent("tb");
    CssIdent rl = new CssIdent("rl");
    CssIdent lr = new CssIdent("lr");

    /**
     * Create a new CssBlockProgression
     */
    public CssBlockProgression() {
	blockprog = tb;
    }

    /**
     * Create a new CssBlockProgression
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBlockProgression(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this.ac = ac;
	setByUser(); // tell this property is set by the user
	CssValue val = expression.getValue();

	if (val.equals(tb)) {
	    blockprog = val;
	    expression.next();
	}
	else if (val.equals(rl)) {
	    blockprog = val;
	    expression.next();
	}
	else if (val.equals(lr)) {
	    blockprog = val;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    blockprog = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    public CssBlockProgression(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssBlockProgression != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBlockProgression = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getBlockProgression();
	} else {
	    return ((Css3Style) style).cssBlockProgression;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBlockProgression &&
		blockprog.equals( ((CssBlockProgression) property).blockprog));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "block-progression";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return blockprog;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return blockprog.equals("inherit");
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return blockprog.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return blockprog.equals(new CssIdent("tb"));
    }

}

