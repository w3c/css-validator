//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 3.2  1997/09/09 11:01:13  plehegar
 * Added getStyle()
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
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision$
 */
public class CssBorderBottomStyleCSS2 extends CssProperty {

  CssBorderFaceStyleCSS2 face;
  
  /**
   * Create a new CssBorderBottomStyleCSS2
   */
  public CssBorderBottomStyleCSS2() {
    face = new CssBorderFaceStyleCSS2();
  }
  
  /**
   * Create a new CssBorderBottomStyleCSS2 with an another CssBorderFaceStyleCSS2
   * @param another The another side.
   */
  public CssBorderBottomStyleCSS2(CssBorderFaceStyleCSS2 another) {
	setByUser();

    face = another;
  }
  
  /**
   * Create a new CssBorderBottomStyle eith an expression
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssBorderBottomStyleCSS2(ApplContext ac, CssExpression expression) 
    throws InvalidParamException {

      setByUser();
    face = new CssBorderFaceStyleCSS2(ac, expression);
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
    return "border-bottom-style";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    CssBorderBottomCSS2 bottom = ((Css1Style) style).cssBorderCSS2.bottom;
    if (bottom.style != null)
      style.addRedefinitionWarning(ac, this);
    bottom.style = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css1Style) style).getBorderBottomStyleCSS2();
    } else {
      return ((Css1Style) style).cssBorderCSS2.getBottom().style;
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof CssBorderBottomStyleCSS2 && face.equals(((CssBorderBottomStyleCSS2) property).face));
  }

}
