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

/**
 *   <H4>
 *     <A NAME="padding-left">5.5.9 &nbsp;&nbsp; 'padding-left'</A>
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;length&gt; | &lt;percentage&gt;<BR>
 *   <EM>Initial:</EM> 0<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to parent element's width<BR>
 *   <P>
 *   This property sets the left padding of an element.
 *   <PRE>
 *   BLOCKQUOTE { padding-left: 20% }
 * </PRE>
 *   <P>
 *   Padding values cannot be negative.
 * @version $Revision$
 */
public class CssPaddingLeftCSS3 extends CssPaddingSideCSS3 {

    /**
     * Create a new CssPaddingLeftCSS3
     */
    public CssPaddingLeftCSS3() {
	super();
    }

    /**
     * Create a new CssPaddingLeftCSS3 with an another CssPaddingSideCSS3
     * @param another The another side.
     */
    public CssPaddingLeftCSS3(CssPaddingSideCSS3 another) {
	super(another);
    }

    /**
     * Create a new CssPaddingLeftCSS3
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException Values are incorrect
     */
    public CssPaddingLeftCSS3(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	super(ac, expression, check);
    }

    public CssPaddingLeftCSS3(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "padding-left";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssPaddingLeftCSS3 != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssPaddingLeftCSS3 = this;

    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getPaddingLeftCSS3();
	} else {
	    return ((Css3Style) style).cssPaddingCSS3.getLeft();
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssPaddingLeftCSS3 &&
		value.equals(((CssPaddingLeftCSS3) property).value));
    }

}
