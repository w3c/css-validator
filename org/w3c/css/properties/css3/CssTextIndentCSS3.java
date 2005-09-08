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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'text-indent'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;length&gt; | &lt;percentage&gt;<BR>
 *   <EM>Initial:</EM> 0<BR>
 *   <EM>Applies to:</EM> block-level elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> refer to parent element's width<BR>
 *   <P>
 *   The property specifies the indentation that appears before the first formatted
 *   line. The value of 'text-indent' may be negative, but there may be
 *   implementation-specific limits. An indentation is not inserted in the middle
 *   of an element that was broken by another (such as 'BR' in HTML).
 *   <P>
 *   Example:
 *   <PRE>
 *   P { text-indent: 3em }
 * </PRE>
 * @version $Revision$
 */
public class CssTextIndentCSS3 extends CssProperty  {

    CssValue value = new CssLength();
    CssIdent hanging = new CssIdent("hanging");

    /**
     * Create a new CssTextIndentCSS3
     */
    public CssTextIndentCSS3() {
    }

    /**
     * Create a new CssTextIndentCSS3
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssTextIndentCSS3(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = expression.getValue();
	
	setByUser();
	
	if (val.equals(inherit)) {
	    value = inherit;
	} else if (val instanceof CssLength || val instanceof CssPercentage) {
	    value = val;
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getLength();
	} else if (val.equals(hanging)) {
	    value = hanging;
	} else {
	    throw new InvalidParamException("value", val.toString(),
		    getPropertyName(), ac);
	}
	
	expression.next();
    }
    
    public CssTextIndentCSS3(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-indent";
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
	if (style0.cssTextIndentCSS3 != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssTextIndentCSS3 = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextIndent();
	} else {
	    return ((Css3Style) style).cssTextIndentCSS3;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextIndentCSS3 &&
		value.equals(((CssTextIndentCSS3) property).value));
    }
}
