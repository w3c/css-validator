//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssMarginSide;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'margin-outside'
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
 *   H1 { margin-outside: 12.3% }
 * </PRE>
 *   <P> A negative value is allowed, but there may be implementation-specific
 *   limits.
 *
 * @version $Revision$ */
public class CssMarginOutside extends CssMarginSide {

  /**
   * Create a new CssMarginOutside
   */
  public CssMarginOutside() {
    super();
  }
  
  /**
   * Create a new CssMarginOutside with an another CssMarginSide
   * @param another The another side.
   */
  public CssMarginOutside(CssMarginSide another) {
    super(another);
  }
  
  /**
   * Create a new CssMarginOutside
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssMarginOutside(ApplContext ac, CssExpression expression,
	  boolean check) throws InvalidParamException {
    super(ac, expression, check);
  }
  
  public CssMarginOutside(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
    this(ac, expression, false);
  }  
  
  /**
   * Returns the name of this property
   */  
  public String getPropertyName() {
    return "margin-outside";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    Css3Style style0 = (Css3Style) style;
    if (style0.cssMarginOutside != null)
      style0.addRedefinitionWarning(ac, this);
    style0.cssMarginOutside = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css3Style) style).getMarginOutside();
    } else {
      return ((Css3Style) style).cssMarginOutside;
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
      //    return (property instanceof CssMarginOutside && 
      //    value.equals(((CssMarginOutside) property).value));
      return false;
  }

}
