//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> nonzero || evenodd || inherit<BR>
 *  <EM>Initial:</EM>nonzero<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class ClipRule extends CssProperty {

    CssValue cliprule;
    ApplContext ac;

    CssIdent nonzero = new CssIdent("nonzero");
    CssIdent evenodd = new CssIdent("evenodd");

    /**
     * Create a new Cliprule
     */
    public ClipRule() {
	//nothing to do
    }

    /**
     * Create a new Cliprule
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public ClipRule(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this.ac = ac;
	setByUser(); // tell this property is set by the user
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    cliprule = inherit;
	    expression.next();
	} else if (val.equals(nonzero)) {
	    cliprule = nonzero;
	    expression.next();
	} else if (val.equals(evenodd)) {
	    cliprule = evenodd;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    public ClipRule(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((SVGStyle) style).clipRule != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGStyle) style).clipRule = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGStyle) style).getClipRule();
	} else {
	    return ((SVGStyle) style).clipRule;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof ClipRule &&
		cliprule.equals( ((ClipRule) property).cliprule));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "clip-rule";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return cliprule;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return cliprule.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return cliprule.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (cliprule == nonzero);
    }

}
