//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 3.2  1997/09/09 10:56:46  plehegar
 * Added getValue()
 *
 * Revision 3.1  1997/08/29 13:13:34  plehegar
 * Freeze
 *
 * Revision 1.4  1997/08/20 11:41:16  plehegar
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
 *      &nbsp;&nbsp; 'border-bottom-width'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> thin | medium | thick | &lt;length&gt;<BR>
 *   <EM>Initial:</EM> 'medium'<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P> This property sets the width of an element's bottom border. The width
 *   of the keyword values are UA dependent, but the following holds: 'thin'
 *   &lt;= 'medium' &lt;= 'thick'.
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
 * @version $Revision$ */
public class CssBorderBottomWidthCSS1 extends CssProperty {

  CssBorderFaceWidthCSS1 face;
  
  /**
   * Create a new CssBorderBottomWidth
   */
  public CssBorderBottomWidthCSS1() {
    face = new CssBorderFaceWidthCSS1();
  }
  
  /**
   * Create a new CssBorderBottomWidth with an another CssBorderFaceWidth
   *
   * @param another An another face.
   */
  public CssBorderBottomWidthCSS1(CssBorderFaceWidthCSS1 another) {
	setByUser();

    face = another;
  }
  
  /**
   * Create a new CssBorderBottomWidth
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssBorderBottomWidthCSS1(ApplContext ac, CssExpression expression) 
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
    return "border-bottom-width";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    CssBorderBottomCSS1 bottom = ((Css1Style) style).cssBorderCSS1.bottom;
    if (bottom.width != null)
      style.addRedefinitionWarning(ac, this);
    bottom.width = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css1Style) style).getBorderBottomWidthCSS1();
    } else {
      return ((Css1Style) style).cssBorderCSS1.getBottom().width;
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof CssBorderBottomWidthCSS1 && 
	    face.equals(((CssBorderBottomWidthCSS1) property).face));
  }

}
