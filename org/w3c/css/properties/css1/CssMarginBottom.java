//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.3  2005/08/08 13:18:12  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:13:55  plehegar
 * Freeze
 *
 * Revision 1.4  1997/08/20 11:41:26  plehegar
 * Freeze
 *
 * Revision 1.3  1997/08/06 17:30:10  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:10  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/24 00:07:47  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'margin-bottom'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;length&gt; | &lt;percentage&gt; | auto<BR>
 *   <EM>Initial:</EM> 0<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to parent element's width<BR>
 *   <P>
 *   This property sets the bottom margin of an element:
 *   <PRE>
 *   H1 { margin-bottom: 3px }
 * </PRE>
 *   <P>
 *   A negative value is allowed, but there may be implementation-specific limits.
 * @version $Revision$
 */
public class CssMarginBottom extends CssMarginSide {

  /**
   * Create a new CssMarginBottom
   */
  public CssMarginBottom() {
    super();
  }
  
  /**
   * Create a new CssMarginBottom with an another CssMarginSide
   *
   * @param another The another side.
   */
  public CssMarginBottom(CssMarginSide another) {
    super(another);
  }
  
  /**
   * Create a new CssMarginBottom
   *
   * @param expression The expression for this porperty.
   * @exception InvalidParamException Values are incorrect
   */
  public CssMarginBottom(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
      super(ac, expression);
  }
  
  public CssMarginBottom(ApplContext ac, CssExpression expression, boolean check)
	throws InvalidParamException {
      super(ac, expression, check);
  }
  
  /**
   * Returns the name of this property
   */  
  public String getPropertyName() {
    return "margin-bottom";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    Css1Style style0 = (Css1Style) style;
    if (style0.cssMargin.bottom != null)
      style0.addRedefinitionWarning(ac, this);
    style0.cssMargin.bottom = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css1Style) style).getMarginBottom();
    } else {
      return ((Css1Style) style).cssMargin.getBottom();
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof CssMarginBottom && 
	    value.equals(((CssMarginBottom) property).value));
  }

}
