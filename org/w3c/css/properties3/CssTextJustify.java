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
 *  <EM>Value:</EM> auto || inter-word || inter-ideograph || distribute || 
 *  newspaper || inter-cluster || kashida || inherit<BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property selects the type of justify alignment. It affects the text 
 *  layout only if 'text-align' is set to 'justify'. That way, UA's that do 
 *  not support this property will still render the text as fully
 *  justified, which most of the time is at least partially correct. Typically
 *  the text-justify property does not affect the last line, unless the last 
 *  line itself is justified. Most of the text-justify values affects
 *  writing systems in very specific ways.
 */

public class CssTextJustify extends CssProperty {

    CssValue textjustify;
    private CssIdent auto = new CssIdent("auto");

    private static String[] values = {
	"auto", "inter-word", "inter-ideograph", "distribute", 
	"newspaper", "inter-cluster", "kashida", "inherit" 
    };

    /**
     * Create a new CssTextJustify
     */
    public CssTextJustify() {
	textjustify = auto;
    }

    /**
     * Create a new CssTextJustify
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssTextJustify(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();
	
	int i = 0;
	for (; i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		textjustify = val;
		expression.next();
		break;
	    }
	}
	if (i == values.length) {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextJustify != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextJustify = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextJustify();
	}
	else {
	    return ((Css3Style) style).cssTextJustify;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextJustify &&
		textjustify.equals(((CssTextJustify) property).textjustify));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-justify";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return textjustify;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return textjustify.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return textjustify.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (textjustify == auto);
    }

}
