//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'margin-right'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;length&gt; | &lt;percentage&gt; | auto<BR>
 *   <EM>Initial:</EM> 0<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to parent element's width<BR>
 *   <P>
 *   This property sets the right margin of an element:
 *   <PRE>
 *   H1 { margin-right: 12.3% }
 * </PRE>
 *   <P> A negative value is allowed, but there may be implementation-specific
 *   limits.
 *
 * @version $Revision$ */
public class CssMarginRight extends CssMarginSide {

  /**
   * Create a new CssMarginRight
   */
  public CssMarginRight() {
    super();
  }

  /**
   * Create a new CssMarginRight with an another CssMarginSide
   * @param another The another side.
   */
  public CssMarginRight(CssMarginSide another) {
    super(another);
  }

  /**
   * Create a new CssMarginRight
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssMarginRight(ApplContext ac, CssExpression expression)
  	throws InvalidParamException {
      super(ac, expression);
  }

  public CssMarginRight(ApplContext ac, CssExpression expression, boolean check)
	throws InvalidParamException {
      super(ac, expression, check);
  }

  /**
   * Returns the name of this property
   */
  public String getPropertyName() {
    return "margin-right";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    Css1Style style0 = (Css1Style) style;
    if (style0.cssMargin.right != null)
      style0.addRedefinitionWarning(ac, this);
    style0.cssMargin.right = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css1Style) style).getMarginRight();
    } else {
      return ((Css1Style) style).cssMargin.getRight();
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */
  public boolean equals(CssProperty property) {
    return (property instanceof CssMarginRight &&
	    value.equals(((CssMarginRight) property).value));
  }

}
