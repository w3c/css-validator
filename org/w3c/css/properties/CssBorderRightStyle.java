//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 3.2  1997/09/09 11:00:52  plehegar
 * Added getStyle()
 *
 * Revision 3.1  1997/08/29 13:13:39  plehegar
 * Freeze
 *
 * Revision 1.1  1997/08/20 11:41:19  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision$
 */
public class CssBorderRightStyle extends CssProperty {

  CssBorderFaceStyle face;
  
  /**
   * Create a new CssBorderRightStyle
   */
  public CssBorderRightStyle() {
	setByUser();

    face = new CssBorderFaceStyle();
  }
  
  /**
   * Create a new CssBorderRightStyle with an another CssBorderFaceStyle
   *
   * @param another The another side.
   */
  public CssBorderRightStyle(CssBorderFaceStyle another) {
	setByUser();

    face = another;
  }
  
  /**
   * Create a new CssBorderRightStyle.
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssBorderRightStyle(ApplContext ac, CssExpression expression) 
    throws InvalidParamException {

      setByUser();

    face = new CssBorderFaceStyle(ac, expression);
  }
  
  /**
   * Returns the value of this property
   */
  public Object get() {
    return face;
  }

  /**
   * Returns the value
   */
  public String getStyle() {
    return face.getStyle();
  }

  /**
   * Returns a string representation of the object.
   */
  public String toString() {
    return face.toString();
  }

  /**
   * Returns the name of this property
   */  
  public String getPropertyName() {
    return "border-right-style";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    CssBorderRight right = ((Css1Style) style).cssBorder.right;
    if (right.style != null)
      style.addRedefinitionWarning(ac, this);
    right.style = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css1Style) style).getBorderRightStyle();
    } else {
      return ((Css1Style) style).cssBorder.getRight().style;
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof CssBorderRightStyle && 
	    face.equals(((CssBorderRightStyle) property).face));
  }

}
