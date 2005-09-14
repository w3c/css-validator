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
 *  <EM>Value:</EM> none || accent || dot || circle || disc || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property sets the style for the emphasis formatting applied to text.
 *  East Asian documents use the following symbols on top of each glyph to
 *  emphasize a run of text: an 'accent' symbol, a 'dot', a hollow 'circle', or *  a solid 'disc'.
 *  <PRE>
 *  H1 { font-emphasize: accent}
 *  </PRE>
 */

public class CssFontEmphasizeStyle extends CssProperty {

    CssValue emphstyle;
    ApplContext ac;

    static CssIdent none = new CssIdent("none");
    static CssIdent accent = new CssIdent("accent");
    static CssIdent dot = new CssIdent("dot");
    static CssIdent circle = new CssIdent("circle");
    static CssIdent disc = new CssIdent("disc");

    /**
     * Create a new CssFontEmphasizeStyle
     */
    public CssFontEmphasizeStyle() {
	emphstyle = none;
    }

    /**
     * Create a new CssFontEmphasizeStyle
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssFontEmphasizeStyle(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(none)) {
	    emphstyle = none;
	    expression.next();
	}
	else if (val.equals(accent)) {
	    emphstyle = accent;
	    expression.next();
	}
	else if (val.equals(dot)) {
	    emphstyle = dot;
	    expression.next();
	}
	else if (val.equals(circle)) {
	    emphstyle = circle;
	    expression.next();
	}
	else if (val.equals(disc)) {
	    emphstyle = disc;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    emphstyle = inherit;
	    expression.next();
	}
	else throw new InvalidParamException("value", expression.getValue(),
					     getPropertyName(), ac);
    }

    public CssFontEmphasizeStyle(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssFontEmphasizeStyle != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssFontEmphasizeStyle = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getFontEmphasizeStyle();
	} else {
	    return ((Css3Style) style).cssFontEmphasizeStyle;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssFontEmphasizeStyle &&
                emphstyle.equals( ((CssFontEmphasizeStyle) property).emphstyle));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "font-emphasize-style";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return emphstyle;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return emphstyle.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return emphstyle.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return emphstyle == none;
    }

}
