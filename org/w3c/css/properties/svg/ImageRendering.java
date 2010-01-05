//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> auto || optimizeSpeed || optimizeQuality || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>images<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class ImageRendering extends CssProperty {

    CssValue rendering;
    ApplContext ac;

    CssIdent auto = new CssIdent("auto");
    CssIdent optimizeSpeed = new CssIdent("optimizeSpeed");
    CssIdent optimizeQuality = new CssIdent("optimizeQuality");

    /**
     * Create a new Rendering
     */
    public ImageRendering() {
	//nothing to do
    }

    /**
     * Create a new ImageRendering
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public ImageRendering(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this.ac = ac;
	setByUser(); // tell this property is set by the user
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    rendering = inherit;
	    expression.next();
	} else if (val.equals(optimizeSpeed)) {
	    rendering = val;
	    expression.next();
	} else if (val.equals(optimizeQuality)) {
	    rendering = val;
	    expression.next();
	} else if (val.equals(auto)) {
	    rendering = auto;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    public ImageRendering(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((SVGBasicStyle) style).imageRendering != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGBasicStyle) style).imageRendering = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGBasicStyle) style).getImageRendering();
	} else {
	    return ((SVGBasicStyle) style).imageRendering;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof ImageRendering &&
		rendering.equals( ((ImageRendering) property).rendering));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "image-rendering";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return rendering;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return rendering.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return rendering.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (rendering == auto);
    }

}
