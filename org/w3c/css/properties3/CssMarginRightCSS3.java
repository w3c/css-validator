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
 * Revision 3.1  1997/08/29 13:13:56  plehegar
 * Freeze
 *
 * Revision 1.4  1997/08/20 11:41:26  plehegar
 * Freeze
 *
 * Revision 1.3  1997/08/06 17:30:12  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:11  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/24 00:08:48  plehegar
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
public class CssMarginRightCSS3 extends CssMarginSideCSS3 {

  /**
   * Create a new CssMarginRightCSS3
   */
  public CssMarginRightCSS3() {
    super();
  }

  /**
   * Create a new CssMarginRightCSS3 with an another CssMarginSideCSS3
   * @param another The another side.
   */
  public CssMarginRightCSS3(CssMarginSideCSS3 another) {
    super(another);
  }

  /**
   * Create a new CssMarginRightCSS3
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssMarginRightCSS3(ApplContext ac, CssExpression expression) throws InvalidParamException {
    super(ac, expression);
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
    if (((Css3Style) style).cssMarginRightCSS3 != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssMarginRightCSS3 = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css3Style) style).getMarginRightCSS3();
    } else {
      return ((Css3Style) style).cssMarginCSS3.getRight();
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */
  public boolean equals(CssProperty property) {
    return (property instanceof CssMarginRightCSS3 &&
	    value.equals(((CssMarginRightCSS3) property).value));
  }

}
