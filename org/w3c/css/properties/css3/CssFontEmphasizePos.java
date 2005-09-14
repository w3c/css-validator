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
 *  <EM>Value:</EM> before || after || inherit<BR>
 *  <EM>Initial:</EM>before<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property sets the position of the emphasis symbols. They can appear
 *  either 'before' or 'after' the emphasized run of horizontal text.
 *  The values 'before' and 'after' should be understood as relative to the
 *  line baseline. In an horizontal layout flow, 'before' means above the text.
 *  In a vertical layout flow, if the position is set to 'before' then the
 *  emphasis marks should appear on the right side of the vertical text column.
 *  If the position is set to 'after', then the emphasis should appear the the
 *  left side of the column.
 *  <PRE>
 *  H1 { font-emphasize-position: before}
 *  </PRE>
 */

public class CssFontEmphasizePos extends CssProperty {

    CssValue emppos;
    ApplContext ac;

    static CssIdent before = new CssIdent("before");
    static CssIdent after = new CssIdent("after");

    /**
     * Create new CssFontEmphasizePos
     */
    public CssFontEmphasizePos() {
	emppos = before;
    }

    /**
     * Create new CssFontEmphasizePos
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssFontEmphasizePos(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(before)) {
	    emppos = before;
	    expression.next();
	}
	else if (val.equals(after)) {
	    emppos = before;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					getPropertyName(), ac);
	}
    }

    public CssFontEmphasizePos(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssFontEmphasizePos != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssFontEmphasizePos = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getFontEmphasizePos();
	} else {
	    return ((Css3Style) style).cssFontEmphasizePos;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssFontEmphasizePos &&
                emppos.equals( ((CssFontEmphasizePos) property).emppos));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "font-emphasize-position";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return emppos;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return emppos.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return emppos.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return emppos == before;
    }

}
