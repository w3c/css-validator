//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 3.2  1997/09/09 10:52:45  plehegar
 * Added getColor()
 *
 * Revision 3.1  1997/08/29 13:13:33  plehegar
 * Freeze
 *
 * Revision 1.1  1997/08/20 11:41:16  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision$
 */
public class CssBorderBottomColor extends CssProperty {

  CssBorderFaceColor face;
  
  /**
   * Create a new CssBorderBottomColor
   */
  public CssBorderBottomColor() {
    face = new CssBorderFaceColor();
  }
  
  /**
   * Create a new CssBorderBottomColor with an another CssBorderFaceColor
   *
   * @param another An another face.
   */
  public CssBorderBottomColor(CssBorderFaceColor another) {

      setByUser();

    face = another;
  }
  
  /**
   * Create a new CssBorderBottomColor
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssBorderBottomColor(ApplContext ac, CssExpression expression) 
    throws InvalidParamException {

      setByUser();

    face = new CssBorderFaceColor(ac, expression);
  }
  
  /**
   * Returns the value of this property
   */
  public Object get() {
    return face;
  }

  /**
   * Returns the color of this property
   */
  public CssValue getColor() {
    return face.getColor();
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
    return "border-bottom-color";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    CssBorderBottom bottom = ((Css1Style) style).cssBorder.bottom;
    if (bottom.color != null)
      style.addRedefinitionWarning(ac, this);
    bottom.color = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css1Style) style).getBorderBottomColor();
    } else {
      return ((Css1Style) style).cssBorder.getBottom().color;
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof CssBorderBottomColor && face.equals(((CssBorderBottomColor) property).face));
  }

  /**
   * Print this property.
   *
   * @param printer The printer.
   * @see #toString()
   * @see #getPropertyName()
   */  
  public void print(CssPrinterStyle printer) {
    if (!face.isDefault())
      printer.print(this);
  }
}
