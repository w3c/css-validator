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

/**
 *   <H4>
 *     &nbsp;&nbsp; 'font-variant'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> normal | small-caps<BR>
 *   <EM>Initial:</EM> normal<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P> Another type of variation within a font family is the small-caps. In a
 *   small-caps font the lower case letters look similar to the uppercase ones,
 *   but in a smaller size and with slightly different proportions. The
 *   'font-variant' property selects that font.
 *   <P> A value of 'normal' selects a font that is not a small-caps font,
 *   'small-caps' selects a small-caps font. It is acceptable (but not required)
 *   in CSS1 if the small-caps font is a created by taking a normal font and
 *   replacing the lower case letters by scaled uppercase characters. As a last
 *   resort, uppercase letters will be used as replacement for a small-caps
 *   font.
 *   <P> The following example results in an 'H3' element in small-caps, with
 *   emphasized words in oblique small-caps:
 *   <PRE>
 *   H3 { font-variant: small-caps }
 *   EM { font-style: oblique }
 *   </PRE>
 *   <P> There may be other variants in the font family as well, such as fonts
 *   with old-style numerals, small-caps numerals, condensed or expanded
 *   letters, etc.  CSS1 has no properties that select those.
 *   <P> <EM>CSS1 core:</EM> insofar as this property causes text to be
 *   transformed to uppercase, the same considerations as for 'text-transform'
 *   apply
 *
 * @see CssFont
 * @see TextTransform
 * @version $Revision$
 */
public class CssFontVariantCSS1 extends CssProperty implements CssFontConstantCSS1 {

    int value;

    /**
     * Create a new CssFontVariantCSS1
     */
    public CssFontVariantCSS1() {
	// nothing to do
    }

    /**
     * Creates a new CssFontVariantCSS1
     *
     * @param expression the font variant
     * @exception InvalidParamException Values are incorrect
     */
    public CssFontVariantCSS1(ApplContext ac, CssExpression expression,
	    boolean check)
	    throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	if (expression.getValue() instanceof CssIdent) {
	    int hash = expression.getValue().hashCode();
	    for (int i=0; i<hash_values.length; i++)
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	}

	throw new InvalidParamException("value", expression.getValue(),
					getPropertyName(), ac);
    }

    public CssFontVariantCSS1(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the current value
     */
    public Object get() {
	return FONTVARIANT[value];
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == FONTVARIANT.length - 1;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return FONTVARIANT[value];
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "font-variant";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssFontCSS1 cssFont = ((Css1Style) style).cssFontCSS1;
	if (cssFont.fontVariant != null)
	    style.addRedefinitionWarning(ac, this);
	cssFont.fontVariant = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getFontVariantCSS1();
	} else {
	    return ((Css1Style) style).cssFontCSS1.fontVariant;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssFontVariantCSS1 &&
		((CssFontVariantCSS1) property).value == value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == 0;
    }

    private static int[] hash_values;

    static {
	hash_values = new int[FONTVARIANT.length];
	for (int i=0;i<FONTVARIANT.length;i++)
	    hash_values[i] = FONTVARIANT[i].hashCode();
    }
}
