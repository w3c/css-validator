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

/**
 *  <P>
 *  <EM>Value:</EM> &lt;integer&gt; || none || all || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class CssColumnSpan extends CssProperty {

   CssValue value;
    ApplContext ac;

    CssIdent none = new CssIdent("none");
    CssIdent all = new CssIdent("all");

    /**
     * Create a new CssColumnSpan
     */
   public CssColumnSpan() {
       //nothing to do
   }
   
   /**
    * Create a new CssColumnSpan
    *
    * @param expression The expression for this property     
    * @exception InvalidParamException Values are incorrect
    */
   public CssColumnSpan(ApplContext ac, CssExpression expression) throws InvalidParamException {
       this.ac = ac;
       setByUser(); // tell this property is set by the user
       CssValue val = expression.getValue();

       if (val instanceof CssNumber) {
	    if (((CssNumber) val).isInteger()) {
		value = val;
		expression.next();
		return;
	    } else {
		throw new InvalidParamException("integer", 
						val.toString(), 
						getPropertyName(), ac);
	    }
       }
       else if (val instanceof CssIdent) {
           if (val.equals(inherit)) {
	       value = inherit;
	       expression.next();
           } else if (val.equals(none)) {
	       value = none;
	       expression.next();
	   } else if (val.equals(all)) {
	       value = all;
	       expression.next();
	   }
       }
       else {
	   throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
       }
   }
   
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
     public void addToStyle(ApplContext ac, CssStyle style) {
	 if (((Css3Style) style).cssColumnSpan != null)
	     style.addRedefinitionWarning(ac, this);
	 ((Css3Style) style).cssColumnSpan = this;
     }
    
      /**
       * Get this property in the style.
       *
       * @param style The style where the property is
       * @param resolve if true, resolve the style to find this property
       */  
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((Css3Style) style).getColumnSpan();
	    } else {
		return ((Css3Style) style).cssColumnSpan;
	    }
	}
    
       /**
        * Compares two properties for equality.
        *
        * @param value The other property.
        */  
       public boolean equals(CssProperty property) {
	   return (property instanceof CssColumnSpan && 
                value.equals( ((CssColumnSpan) property).value));
       }

    /**
     * Returns the name of this property
     */
   public String getPropertyName() {
       return "column-span";
   }
   
    /**
     * Returns the value of this property
     */
   public Object get() {
       return value;
   }
   
    /**
     * Returns true if this property is "softly" inherited
     */
   public boolean isSoftlyInherited() {
       return value.equals(inherit);
   }

   /**
    * Returns a string representation of the object
    */
   public String toString() {
       return value.toString();
   }
 
    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
   public boolean isDefault() {	
       return value == none;
   }

}
