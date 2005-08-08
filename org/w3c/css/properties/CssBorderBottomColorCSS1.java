//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:17:42  plehegar
 * New
 *
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

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision$
 */
public class CssBorderBottomColorCSS1 extends CssProperty {

  CssBorderFaceColorCSS1 face;
  
  /**
   * Create a new CssBorderBottomColorCSS1
   */
  public CssBorderBottomColorCSS1() {
    face = new CssBorderFaceColorCSS1();
  }
  
  /**
   * Create a new CssBorderBottomColor with an another CssBorderFaceColor
   *
   * @param another An another face.
   */
  public CssBorderBottomColorCSS1(CssBorderFaceColorCSS1 another) {

      setByUser();

    face = another;
  }
  
  /**
   * Create a new CssBorderBottomColor
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssBorderBottomColorCSS1(ApplContext ac, CssExpression expression,
	  boolean check) throws InvalidParamException {
      
      if(check && expression.getCount() > 1) {
	  throw new InvalidParamException("unrecognize", ac);
      }

      setByUser();
      face = new CssBorderFaceColorCSS1(ac, expression);
  }
  
  public CssBorderBottomColorCSS1(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	this(ac, expression, false);
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
      if(face != null) {
	  return face.getColor();
      }
      return null;
  }

  /**
   * Returns a string representation of the object.
   */
  public String toString() {
      if(face != null) {
	  return face.toString();
      }
      return "";
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
    CssBorderBottomCSS1 bottom = ((Css1Style) style).cssBorderCSS1.bottom;
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
      return ((Css1Style) style).getBorderBottomColorCSS1();
    } else {
      return ((Css1Style) style).cssBorderCSS1.getBottom().color;
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof CssBorderBottomColorCSS1 && face.equals(((CssBorderBottomColorCSS1) property).face));
  }

  /**
   * Print this property.
   *
   * @param printer The printer.
   * @see #toString()
   * @see #getPropertyName()
   */  
  public void print(CssPrinterStyle printer) {
    if (face != null && !face.isDefault())
      printer.print(this);
  }
}
