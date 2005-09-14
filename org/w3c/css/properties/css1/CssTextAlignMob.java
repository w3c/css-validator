//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     <A NAME="text-align">5.4.6 &nbsp;&nbsp; 'text-align'</A>
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> left | right | center | justify<BR>
 *   <EM>Initial:</EM> UA specific<BR>
 *   <EM>Applies to:</EM> block-level elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property describes how text is aligned within the element. The actual
 *   justification algorithm used is UA and human language dependent.
 *   <P>
 *   Example:
 *   <PRE>
 *   DIV.center { text-align: center }
 * </PRE>
 *   <P>
 *   Since 'text-align' inherits, all block-level elements inside the 'DIV' element
 *   with 'CLASS=center' will be centered. Note that alignments are relative to
 *   the width of the element, not the canvas. If 'justify' is not supported,
 *   the UA will supply a replacement. Typically, this will be 'left' for western
 *   languages.
 * @version $Revision$
 */
public class CssTextAlignMob extends CssProperty
        implements CssTextPropertiesConstants {

    int value;
    CssValue valueString;

    private static int[] hash_values;

    /**
     * Create a new CssTextAlignMob
     */
    public CssTextAlignMob() {
	// depends on user agent and writing direction
    }

    /**
     * Create a new CssTextAlignMob
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssTextAlignMob(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	int hash = val.hashCode();

	setByUser();

	if (val.equals(inherit)) {
	    valueString = inherit;
	    expression.next();
	    return;
	} else if (val instanceof CssIdent) {
	    for (int i = 0; i < TEXTALIGN.length; i++) {
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	    }
	}

	throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
    }

    public CssTextAlignMob(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	if (valueString != null) {
	    return valueString;
	} else {
	    return TEXTALIGN[value];
	}
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-align";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return valueString == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (valueString != null) {
	    return valueString.toString();
	} else {
	    return TEXTALIGN[value];
	}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssTextAlignMob != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssTextAlignMob = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getTextAlignMob();
	} else {
	    return ((Css1Style) style).cssTextAlignMob;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	if (valueString != null) {
	    return (property instanceof CssTextAlignMob
  	    && valueString.equals(((CssTextAlignMob) property).valueString));
	} else {
	    return (property instanceof CssTextAlignMob
		    && value == ((CssTextAlignMob) property).value);
	}
    }

    static {
	hash_values = new int[TEXTALIGN.length];
	for (int i=0; i<TEXTALIGN.length; i++)
	    hash_values[i] = TEXTALIGN[i].hashCode();
    }
}
