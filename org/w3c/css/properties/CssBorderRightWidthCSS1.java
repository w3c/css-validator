//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 3.2  1997/09/09 10:56:24  plehegar
 * Added getValue()
 *
 * Revision 3.1  1997/08/29 13:13:39  plehegar
 * Freeze
 *
 * Revision 1.4  1997/08/20 11:41:19  plehegar
 * Freeze
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *   <H4>
 *      &nbsp;&nbsp; 'border-right-width'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> thin | medium | thick | &lt;length&gt;<BR>
 *   <EM>Initial:</EM> 'medium'<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P> This property sets the width of an element's right border. The width of
 *   the keyword values are UA dependent, but the following holds: 'thin' &lt;=
 *   'medium' &lt;= 'thick'.
 *   <P>
 *   The keyword widths are constant throughout a document:
 *   <PRE>
 *   H1 { border: solid thick red }
 *   P  { border: solid thick blue }
 * </PRE>
 *   <P>
 *   In the example above, 'H1' and 'P' elements will have the same border width
 *   regardless of font size. To achieve relative widths, the 'em' unit can be
 *   used:
 *   <PRE>
 *   H1 { border: solid 0.5em }
 * </PRE>
 *   <P>
 *   Border widths cannot be negative.
 *
 * @version $Revision$ 
 */
public class CssBorderRightWidthCSS1 extends CssProperty {

  CssBorderFaceWidthCSS1 face;
  
  /**
   * Create a new CssBorderRightWidth
   */
  public CssBorderRightWidthCSS1() {
    face = new CssBorderFaceWidthCSS1();
  }
  
  /**
   * Create a new CssBorderRightWidthCSS1 with an another CssBorderFaceWidthCSS1
   *
   * @param another The another side.
   */
  public CssBorderRightWidthCSS1(CssBorderFaceWidthCSS1 another) {
	setByUser();

    face = another;
  }
  
  /**
   * Create a new CssBorderRightWidthCSS1
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssBorderRightWidthCSS1(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
    
	setByUser();

    face = new CssBorderFaceWidthCSS1(ac, expression);
  }
  
  /**
   * Returns the value of this property
   */
  public Object get() {
    return face;
  }

  /**
   * Return the value of this property
   */
  public CssValue getValue() {
    return face.getValue();
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
    return "border-right-width";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    CssBorderRightCSS1 right = ((Css1Style) style).cssBorderCSS1.right;
    if (right.width != null)
      style.addRedefinitionWarning(ac, this);
    right.width = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css1Style) style).getBorderRightWidthCSS1();
    } else {
      return ((Css1Style) style).cssBorderCSS1.getRight().width;
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof CssBorderRightWidthCSS1 && 
	    face.equals(((CssBorderRightWidthCSS1) property).face));
  }

}
