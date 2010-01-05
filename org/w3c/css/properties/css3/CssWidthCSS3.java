//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *      &nbsp;&nbsp; 'width'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;length&gt; | &lt;percentage&gt; | auto <BR>
 *   <EM>Initial:</EM> auto<BR>
 *   <EM>Applies to:</EM> block-level and replaced elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to parent element's width<BR>
 *   <P>
 *   This property can be applied to text elements, but it is most useful with
 *   replaced elements such as images. The width is to be enforced by scaling
 *   the image if necessary. When scaling, the aspect ratio of the image is preserved
 *   if the 'height' property is 'auto'.
 *   <P>
 *   Example:
 *   <PRE>
 *   IMG.icon { width: 100px }
 * </PRE>
 *   <P>
 *   If the 'width' and 'height' of a replaced element are both 'auto', these
 *   properties will be set to the intrinsic dimensions of the element.
 *   <P>
 *   Negative values are not allowed.
 * @version $Revision$
 */
public class CssWidthCSS3 extends CssProperty {

    CssValue value;

    private static CssIdent auto = new CssIdent("auto");
    private static CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssWidthCSS3
     */
    public CssWidthCSS3() {
	value = auto;
    }

    /**
     * Create a new CssWidthCSS3.
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssWidthCSS3(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = expression.getValue();

	setByUser();

	if (val.equals(inherit)) {
	    value = inherit;
	} else if (val.equals(initial)) {
		value = initial;
	} else if (val instanceof CssLength || val instanceof CssPercentage) {
	    float f = ((Float) val.get()).floatValue();
	    if (f < 0) {
		throw new InvalidParamException("negative-value",
						val.toString(), ac);
	    }
	    value = val;
	} else if (val.equals(auto)) {
	    value = auto;
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getLength();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}

	expression.next();
    }

    public CssWidthCSS3(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property.
     */
    public Object get() {
	return value;
    }

    /**
     * Returns the name of this property.
     */
    public String getPropertyName() {
	return "width";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value.toString();
    }


    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css3Style style0 = (Css3Style) style;
	if (style0.cssWidthCSS3 != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssWidthCSS3 = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getWidthCSS3();
	} else {
	    return ((Css3Style) style).cssWidthCSS3;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssWidthCSS3 && value.equals(((CssWidthCSS3) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == auto;
    }

}
