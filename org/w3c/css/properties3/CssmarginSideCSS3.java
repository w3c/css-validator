//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.1  2002/07/19 20:30:12  sijtsche
 * files representing CSS3 properties
 *
 * Revision 1.1  2002/05/08 09:30:52  dejong
 * CSS version 3 specific properties as in March 2002, all modules
 *
 * Revision 3.1  1997/08/29 13:13:55  plehegar
 * Freeze
 *
 * Revision 1.4  1997/08/20 11:41:26  plehegar
 * Freeze
 *
 * Revision 1.3  1997/08/06 17:30:11  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:10  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/24 00:08:17  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties.CssMarginSide;
import org.w3c.css.properties.CssProperty;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'margin-inside'
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
 *   H1 { margin-inside: 2em }
 * </PRE>
 *   <P>
 *   A negative value is allowed, but there may be implementation-specific limits.
 * @version $Revision$
 */
public class CssMarginInside extends CssMarginSide {

  /**
   * Create a new CssMarginInside
   */
  public CssMarginInside() {
    super();
  }
  
  /**
   * Create a new CssMarginInside with an another CssMarginSide
   *
   * @param another The another side.
   */
  public CssMarginInside(CssMarginSide another) {
    super(another);
  }
  
  /**
   * Create a new CssMarginInside
   *
   * @param expression The expression foir this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssMarginInside(ApplContext ac, CssExpression expression) throws InvalidParamException {
    super(ac, expression);
  }
  
  /**
   * Returns the name of this property
   */  
  public String getPropertyName() {
    return "margin-inside";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    Css3Style style0 = (Css3Style) style;
    if (style0.cssMarginInside != null)
      style0.addRedefinitionWarning(ac, this);
    style0.cssMarginInside = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css3Style) style).getMarginInside();
    } else {
      return ((Css3Style) style).cssMarginInside;
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
      //   return (property instanceof CssMarginInside && 
      //    value.equals(((CssMarginInside) property).value));
      return false;
  }

}
