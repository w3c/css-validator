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
 * Revision 3.1  1997/08/29 13:14:00  plehegar
 * Freeze
 *
 * Revision 1.4  1997/08/20 11:41:27  plehegar
 * Freeze
 *
 * Revision 1.3  1997/08/06 17:30:16  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:16  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/24 01:29:32  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties.CssProperty;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'padding-right'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;length&gt; | &lt;percentage&gt;<BR>
 *   <EM>Initial:</EM> 0<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to parent element's width<BR>
 *   <P>
 *   This property sets the right padding of an element.
 *   <PRE>
 *   BLOCKQUOTE { padding-right: 10px }
 * </PRE>
 *   <P>
 *   Padding values cannot be negative.
 *
 * @version $Revision$
 */
public class CssPaddingRightCSS3 extends CssPaddingSideCSS3 {

  /**
   * Create a new CssPaddingRightCSS3
   */
  public CssPaddingRightCSS3() {
    super();
  }

  /**
   * Create a new CssPaddingRightCSS3 with an another CssPaddingSideCSS3
   *
   * @param another The another side
   */
  public CssPaddingRightCSS3(CssPaddingSideCSS3 another) {
    super(another);
  }

  /**
   * Create a new CssPaddingRightCSS3
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssPaddingRightCSS3(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
    super(ac, expression);
  }

  /**
   * Returns the name of this property
   */
  public String getPropertyName() {
    return "padding-right";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    if (((Css3Style) style).cssPaddingRightCSS3 != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssPaddingRightCSS3 = this;

  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css3Style) style).getPaddingRightCSS3();
    } else {
      return ((Css3Style) style).cssPaddingCSS3.getRight();
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */
  public boolean equals(CssProperty property) {
    return (property instanceof CssPaddingRightCSS3 &&
	    value.equals(((CssPaddingRightCSS3) property).value));
  }

}
