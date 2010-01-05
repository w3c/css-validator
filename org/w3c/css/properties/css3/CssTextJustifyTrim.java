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
 *  <P>
 *  <EM>Value:</EM> none || punctuation || punctuation-and-kana || inherit<BR>
 *  <EM>Initial:</EM>punctuation<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This sets the individual font blank space compression permissions for
 *  the text justification algorithm, when 'text-justify' is anything other
 *  than 'inter-word'. This special type of space compression occurs on the
 *  font level, i.e. the blank space within the character area itself may be
 *  reduced without affecting the appearance of the glyph. This applies to
 *  wide-cell glyphs only.
 */

public class CssTextJustifyTrim extends CssProperty {

    CssValue trim;

    static CssIdent none = new CssIdent("none");
    static CssIdent punctuation = new CssIdent("punctuation");
    static CssIdent punctkana = new CssIdent("punctuation-and-kana");

    /**
     * Create a new CssTextJustifyTrim
     */
    public CssTextJustifyTrim() {
	trim = punctuation;
    }

    /**
     * Create a new CssTextJustifyTrim
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssTextJustifyTrim(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(none)) {
	    trim = none;
	    expression.next();
	}
	else if (val.equals(punctuation)) {
	    trim = punctuation;
	    expression.next();
	}
	else if (val.equals(punctkana)) {
	    trim = punctkana;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    trim = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssTextJustifyTrim(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextJustifyTrim != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextJustifyTrim = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextJustifyTrim();
	}
	else {
	    return ((Css3Style) style).cssTextJustifyTrim;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextJustifyTrim &&
		trim.equals(((CssTextJustifyTrim) property).trim));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-justify-trim";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return trim;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return trim.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return trim.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return trim == punctuation;
    }

}


