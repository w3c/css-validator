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
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;percentage&gt; || inherit<BR>
 *  <EM>Initial:</EM>0%<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>as described<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  Kashida is a typographic effect used in Arabic writing systems that allows
 *  character elongation at some carefully chosen points in Arabic. Each
 *  elongation can be accomplished using a number of kashida glyphs, a single
 *  graphic or character elongation on each side of the kashida point. (The UA
 *  may use either mechanism based on font or system capability). The
 *  text-kashida-space property expresses the ratio of the kashida expansion
 *  size to the white space expansion size, 0% means no kashida expansion,
 *  100% means kashida expansion only . This property can be used with any
 *  justification style where kashida expansion is used (currently
 *  text-justify: auto, kashida, distribute and newspaper).
 */

public class CssTextKashidaSpace extends CssProperty {

    CssValue kashspace;

    /**
     * Create a new CssTextKashidaSpace
     */
    public CssTextKashidaSpace() {
	CssPercentage perc = new CssPercentage(0);
	kashspace = perc;
    }

    /**
     * Create a new CssTextKashidaSpace
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssTextKashidaSpace(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    kashspace = inherit;
	    expression.next();
	}
	else if (val instanceof CssPercentage) {
	    kashspace = val;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("percentage", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssTextKashidaSpace(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextKashidaSpace != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextKashidaSpace = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextKashidaSpace();
	}
	else {
	    return ((Css3Style) style).cssTextKashidaSpace;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextKashidaSpace &&
		kashspace.equals(((CssTextKashidaSpace) property).kashspace));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-kashida-space";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return kashspace;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return kashspace.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return kashspace.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	CssPercentage perc = new CssPercentage(0);
	return kashspace == perc;
    }

}
