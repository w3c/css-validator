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
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssAngle;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;angle&gt; || auto || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  glyph-orientation-vertical' controls glyph orientation when the primary
 *  text advance direction is vertical.
 */

public class CssGlyphOrVert extends CssProperty {

    CssValue vert;

    static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssGlyphOrVert
     */
    public CssGlyphOrVert() {
	vert = auto;
    }

    /**
     * Create a new CssGlyphOrVert
     *
     * @param expression The expression for this property
     8 @exception InvalidParamException Incorrect value
     */
    public CssGlyphOrVert(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    vert = inherit;
	    expression.next();
	}
	else if (val.equals(auto)) {
	    vert = auto;
	    expression.next();
	} else if (val instanceof CssAngle) {
		vert = val;
		expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(),
					    getPropertyName(), ac);
	}
    }


    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssGlyphOrVert != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssGlyphOrVert = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getGlyphOrVert();
	}
	else {
	    return ((Css3Style) style).cssGlyphOrVert;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssGlyphOrVert &&
		vert.equals(((CssGlyphOrVert) property).vert));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "glyph-orientation-vertical";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return vert;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return vert.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return vert.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return vert == auto;
    }

}
