//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 */
public class CssRight extends CssBoxOffsetFace {

  /**
   * Create a new CssRight
   */
  public CssRight() {
    super();
  }
  
  /**
   * Create a new CssRight
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssRight(ApplContext ac, CssExpression expression) throws InvalidParamException {
    super(ac, expression);
  }
  
  /**
   * Returns the name of this property
   */  
  public String getPropertyName() {
    return "right";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    Css1Style style0 = (Css1Style) style;
    if (style0.cssRight != null)
      style0.addRedefinitionWarning(ac, this);
    style0.cssRight = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css1Style) style).getRight();
    } else {
      return ((Css1Style) style).getRight();
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof CssRight && 
	    value.equals(((CssRight) property).value));
  }

}
