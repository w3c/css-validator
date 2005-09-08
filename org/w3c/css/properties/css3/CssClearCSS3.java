//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     <A NAME="clear">5.5.26 &nbsp;&nbsp; 'clear'</A>
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> none | left | right | both<BR>
 *   <EM>Initial:</EM> none<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P> This property specifies if an element allows floating elements on its
 *   sides.  More specifically, the value of this property lists the sides where
 *   floating elements are not accepted. With 'clear' set to 'left', an element
 *   will be moved below any floating element on the left side. With 'clear' set
 *   to 'none', floating elements are allowed on all sides. Example:
 *   <PRE>
 *   H1 { clear: left }
 *  </PRE>
 *
 * @version $Revision$ */
public class CssClearCSS3 extends CssProperty {

    int value;

    private static String[] CLEAR = {
	"none", "left", "right", "both", "inherit",
	"top", "bottom", "inside", "outside", "start", "end", "initial", "inherit"
    };

    private static int[] hash_values;

    /**
     * Create a new CssClearCSS3
     */
    public CssClearCSS3() {
	// nothing to do
    }

    /**
     * Create a new CssClearCSS3
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssClearCSS3(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = expression.getValue();

	setByUser();
	if ( val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < CLEAR.length; i++)
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	}
	throw new InvalidParamException("value", expression.getValue(),
					getPropertyName(), ac);
    }

    public CssClearCSS3(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return CLEAR[value];
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "clear";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == CLEAR.length - 1;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return CLEAR[value];
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css3Style style0 = (Css3Style) style;
	if (style0.cssClearCSS3 != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssClearCSS3 = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getClearCSS3();
	} else {
	    return ((Css3Style) style).cssClearCSS3;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssClearCSS3 &&
		value == ((CssClearCSS3) property).value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == 0;
    }

    static {
	hash_values = new int[CLEAR.length];
	for (int i = 0; i < CLEAR.length; i++)
	    hash_values[i] = CLEAR[i].hashCode();
    }
}
