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
 *  <EM>Value:</EM> none || ideograph-numeric || ideograph-alpha || 
 *  ideograph-space || ideograph-parenthesis || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  When a run of non-ideographic or numeric characters appears inside of 
 *  ideographic text, a certain amount of space is often preferred on both 
 *  sides of the non-ideographic text to separate it from the surrounding
 *  ideographic glyphs. This property controls the creation of that space when 
 *  rendering the text. That added width does not correspond to the insertion 
 *  of additional space characters, but instead to the width increment of
 *  existing glyphs. 
 */

public class CssTextAutoSpace extends CssProperty {
 
    CssValue textautospace;
    ApplContext ac;

    static CssIdent none = new CssIdent("none");

    private static String[] values = {
	"none", "ideograph-numeric", "ideograph-alpha", 
	"ideograph-space", "ideograph-parenthesis", "inherit"
    };

    /**
     * Create a new CssTextAutoSpace
     */
    public CssTextAutoSpace() {
	textautospace = none;
    }

    /**
     * Create a new CssTextAutoSpace
     * 
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextAutoSpace(ApplContext ac, CssExpression expression) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	int i = 0;
	for (;i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		textautospace = val;
		expression.next();
		break;
	    }
	}
	if (i == values.length) {
	    throw new InvalidParamException("value", 
					    expression.getValue(), 
					    getPropertyName(), ac);
	}

    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextAutoSpace != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextAutoSpace = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextAutoSpace();
	}
	else {
	    return ((Css3Style) style).cssTextAutoSpace;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextAutoSpace &&
		textautospace.equals(((CssTextAutoSpace) property).textautospace));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-autospace";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return textautospace;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return textautospace.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return textautospace.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return textautospace == none;
    }

}
