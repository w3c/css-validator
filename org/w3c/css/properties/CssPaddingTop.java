//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 3.1  1997/08/29 13:14:01  plehegar
 * Freeze
 *
 * Revision 1.4  1997/08/20 11:41:27  plehegar
 * Freeze
 *
 * Revision 1.3  1997/08/06 17:30:17  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:17  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/24 01:32:19  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'padding-top'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;length&gt; | &lt;percentage&gt;<BR>
 *   <EM>Initial:</EM> 0<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to parent element's width<BR>
 *   <P>
 *   This property sets the top padding of an element.
 *   <PRE>
 *   BLOCKQUOTE { padding-top: 0.3em }
 * </PRE>
 *   <P>
 *   Padding values cannot be negative.
 * @version $Revision$
 */
public class CssPaddingTop extends CssPaddingSide {

  /**
   * Create a new CssPaddingTop
   */
  public CssPaddingTop() {
    super();
  }
  
  /**
   * Create a new CssPaddingTop with an another CssPaddingSide.
   *
   * @param another The another side.
   */
  public CssPaddingTop(CssPaddingSide another) {
    super(another);
  }
  
  /**
   * Create a new CssPaddingTop
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssPaddingTop(ApplContext ac, CssExpression expression) throws InvalidParamException {
    super(ac, expression);
  }
  
  /**
   * Returns the name of this property
   */  
  public String getPropertyName() {
    return "padding-top";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    Css1Style style0 = (Css1Style) style;
    if (style0.cssPadding.top != null)
      style0.addRedefinitionWarning(ac, this);
    style0.cssPadding.top = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css1Style) style).getPaddingTop();
    } else {
      return ((Css1Style) style).cssPadding.getTop();
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof CssPaddingTop && value.equals(((CssPaddingTop) property).value));
  }

}
