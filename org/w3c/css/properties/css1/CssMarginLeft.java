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

/**
 *   <H4>
 *     &nbsp;&nbsp; 'margin-left'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;length&gt; | &lt;percentage&gt; | auto<BR>
 *   <EM>Initial:</EM> 0<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to parent element's width<BR>
 *   <P>
 *   This property sets the left margin of an element:
 *   <PRE>
 *   H1 { margin-left: 2em }
 * </PRE>
 *   <P>
 *   A negative value is allowed, but there may be implementation-specific limits.
 * @version $Revision$
 */
public class CssMarginLeft extends CssMarginSide {

  /**
   * Create a new CssMarginLeft
   */
  public CssMarginLeft() {
    super();
  }
  
  /**
   * Create a new CssMarginLeft with an another CssMarginSide
   *
   * @param another The another side.
   */
  public CssMarginLeft(CssMarginSide another) {
    super(another);
  }
  
  /**
   * Create a new CssMarginLeft
   *
   * @param expression The expression foir this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssMarginLeft(ApplContext ac, CssExpression expression) 
  	throws InvalidParamException {
      super(ac, expression);
  }
  
  public CssMarginLeft(ApplContext ac, CssExpression expression, boolean check)
	throws InvalidParamException {
      super(ac, expression, check);
  }
  
  /**
   * Returns the name of this property
   */  
  public String getPropertyName() {
    return "margin-left";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    Css1Style style0 = (Css1Style) style;
    if (style0.cssMargin.left != null)
      style0.addRedefinitionWarning(ac, this);
    style0.cssMargin.left = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css1Style) style).getMarginLeft();
    } else {
      return ((Css1Style) style).cssMargin.getLeft();
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof CssMarginLeft && 
	    value.equals(((CssMarginLeft) property).value));
  }

}
