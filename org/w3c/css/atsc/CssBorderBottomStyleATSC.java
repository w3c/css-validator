//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.1  2002/05/31 09:00:16  dejong
 * ATSC TV profile objects
 *
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
package org.w3c.css.atsc;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties.CssProperty;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision$
 */
public class CssBorderBottomStyleATSC extends CssProperty {

  CssBorderFaceStyleATSC face;
  
  /**
   * Create a new CssBorderBottomStyleATSC
   */
  public CssBorderBottomStyleATSC() {
    face = new CssBorderFaceStyleATSC();
  }
  
  /**
   * Create a new CssBorderBottomStyleATSC with an another CssBorderFaceStyleATSC
   * @param another The another side.
   */
  public CssBorderBottomStyleATSC(CssBorderFaceStyleATSC another) {
	setByUser();

    face = another;
  }
  
  /**
   * Create a new CssBorderBottomStyle eith an expression
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssBorderBottomStyleATSC(ApplContext ac, CssExpression expression) 
    throws InvalidParamException {

      setByUser();
    face = new CssBorderFaceStyleATSC(ac, expression);
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
    CssBorderBottomATSC bottom = ((ATSCStyle) style).cssBorderATSC.bottom;
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
      return ((ATSCStyle) style).getBorderBottomStyleATSC();
    } else {
      return ((ATSCStyle) style).cssBorderATSC.getBottom().style;
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof CssBorderBottomStyleATSC && face.equals(((CssBorderBottomStyleATSC) property).face));
  }

}
