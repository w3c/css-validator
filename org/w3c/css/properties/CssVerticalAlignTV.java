//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:14:06  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:30  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:38  plehegar
 * Nothing
 *
 * Revision 1.4  1997/08/06 17:30:23  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.3  1997/07/30 13:20:22  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/23 22:36:39  plehegar
 * Updated documentation
 *
 * Revision 1.1  1997/07/23 22:34:49  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'vertical-align'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> baseline | sub | super | top | text-top | middle | bottom
 *   | text-bottom | &lt;percentage&gt; <BR>
 *   <EM>Initial:</EM> baseline<BR>
 *   <EM>Applies to:</EM> inline elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to the 'line-height' of the element
 *   itself<BR>
 *   <P>
 *   The property affects the vertical positioning of the element. One set of
 *   keywords is relative to the parent element:
 *   <DL>
 *     <DT>
 *       'baseline'
 *     <DD>
 *       align the baseline of the element (or the bottom, if the element doesn't
 *       have a baseline) with the baseline of the parent
 *     <DT>
 *       'middle'
 *     <DD>
 *       align the vertical midpoint of the element (typically an image) with the
 *       baseline plus half the x-height of the parent
 *     <DT>
 *       'sub'
 *     <DD>
 *       subscript the element
 *     <DT>
 *       'super'
 *     <DD>
 *       superscript the element
 *     <DT>
 *       'text-top'
 *     <DD>
 *       align the top of the element with the top of the parent element's font
 *     <DT>
 *       'text-bottom'
 *     <DD>
 *       align the bottom of the element with the bottom of the parent element's font
 *   </DL>
 *   <P>
 *   Another set of properties are relative to the formatted line that the element
 *   is a part of:
 *   <DL>
 *     <DT>
 *       'top'
 *     <DD>
 *       align the top of the element with the tallest element on the line
 *     <DT>
 *       'bottom'
 *     <DD>
 *       align the bottom of the element with the lowest element on the line
 *   </DL>
 *   <P>
 *   Using the 'top' and 'bottom' alignment, unsolvable situations can occur where
 *   element dependencies form a loop.
 *   <P>
 *   Percentage values refer to the value of the 'line-height' property of the
 *   element itself. They raise the baseline of the element (or the bottom, if
 *   it has no baseline) the specified amount above the baseline of the parent.
 *   Negative values are possible. E.g., a value of '-100%' will lower the element
 *   so that the baseline of the element ends up where the baseline of the next
 *   line should have been. This allows precise control over the vertical position
 *   of elements (such as images that are used in place of letters) that don't
 *   have a baseline.
 *   <P>
 *   It is expected that a future version of CSS will allow &lt;length&amp;t;
 *   as a value on this property.
 *
 * @version $Revision$
 */
public class CssVerticalAlignTV extends CssProperty
    implements CssTextPropertiesConstants {

    Object value;

    private static int[] hash_values;

    /**
     * Create a new CssVerticalAlignTV
     */
    public CssVerticalAlignTV() {
	value = VERTICALALIGNTV[0];
    }

    /**
     * Create a new CssVerticalAlignTV
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssVerticalAlignTV(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = expression.getValue();
	int hash = val.hashCode();

	setByUser();

	if (val instanceof CssIdent) {
	    for (int i = 0; i < VERTICALALIGNTV.length; i++)
		if (hash_values[i] == hash) {
		    value = VERTICALALIGNTV[i];
		    expression.next();
		    return;
		}
	    throw new InvalidParamException("value",
					    val.toString(), getPropertyName(), ac);
	} else if (val instanceof CssPercentage) {
	    value = val;
	    expression.next();
	} else {
	    throw new InvalidParamException("value",
					    val.toString(), getPropertyName(), ac);
	}
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
	return "vertical-align";
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
	Css1Style style0 = (Css1Style) style;
	if (style0.cssVerticalAlignTV != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssVerticalAlignTV = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getVerticalAlignTV();
	} else {
	    return ((Css1Style) style).cssVerticalAlignTV;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssVerticalAlignTV && value.equals(((CssVerticalAlignTV) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value.equals(VERTICALALIGNTV[0]);
    }

    static {
	hash_values = new int[VERTICALALIGNTV.length];
	for (int i=0; i<VERTICALALIGNTV.length; i++)
	    hash_values[i] = VERTICALALIGNTV[i].hashCode();
    }
}
