//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.Util;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;

/**
 *  <P>
 *  <EM>Value:</EM> &lt; alphavalue&gt; || inherit<BR>
 *  <EM>Initial:</EM>1<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property sets the opacity of an element.
 *  <PRE>
 *  H1 { opacity: 0}
 *  </PRE>
 */

public class CssOpacity extends CssProperty implements CssOperator {

   String opaclevel;
   ApplContext ac;


    /**
     * Create a new CssOpacity
     */
   public CssOpacity() {
       CssNumber cssnum =  new CssNumber((float) 1.0);
       opaclevel = cssnum.toString();
   }

   /**
    * Create a new CssOpacity
    *
    * @param expression The expression for this property
    * @exception InvalidParamException Values are incorrect
    */
   public CssOpacity(ApplContext ac, CssExpression expression) throws InvalidParamException {
       this.ac = ac;
       setByUser(); // tell this property is set by the user
       CssValue val = expression.getValue();

       if (val instanceof CssNumber) {

		   CssNumber cssnum = new CssNumber(clampedValue(ac, ((CssNumber) val).getValue()));
		   opaclevel = cssnum.toString();
		   expression.next();
       }
       else if (val instanceof CssIdent) {
           if (val.equals(inherit)) {
	       	  	opaclevel = "inherit";
	       	 	expression.next();
           } else {
				throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
		   }
       }
       else {
	   		throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
       }
   }
    /**
     * Brings all values back between 0 and 1
     *
     * @param opac The value to be modified if necessary
     */
   private float clampedValue(ApplContext ac, float opac) {
       if (opac < 0 || opac > 1) {
          ac.getFrame().addWarning("out-of-range", Util.displayFloat(opac));
          return ((opac<0)?0:1);
       }
       else return(opac);
   }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
     public void addToStyle(ApplContext ac, CssStyle style) {
	 if (((Css3Style) style).cssOpacity != null)
	     style.addRedefinitionWarning(ac, this);
	 ((Css3Style) style).cssOpacity = this;
     }

      /**
       * Get this property in the style.
       *
       * @param style The style where the property is
       * @param resolve if true, resolve the style to find this property
       */
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((Css3Style) style).getOpacity();
	    } else {
		return ((Css3Style) style).cssOpacity;
	    }
	}

       /**
        * Compares two properties for equality.
        *
        * @param value The other property.
        */
       public boolean equals(CssProperty property) {
	   return (property instanceof CssOpacity &&
                opaclevel.equals( ((CssOpacity) property).opaclevel));
       }

    /**
     * Returns the name of this property
     */
   public String getPropertyName() {
       return "opacity";
   }

    /**
     * Returns the value of this property
     */
   public Object get() {
       return opaclevel;
   }

    /**
     * Returns true if this property is "softly" inherited
     */
   public boolean isSoftlyInherited() {
       return opaclevel.equals("inherit");
   }

   /**
    * Returns a string representation of the object
    */
   public String toString() {
       return opaclevel;
   }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
   public boolean isDefault() {
       CssNumber cssnum = new CssNumber(ac, (float) 1.0);
       return opaclevel == cssnum.toString();
   }

}

