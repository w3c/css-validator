//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.svgproperties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.Util;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssURL;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;uri&gt; || none || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class MarkerMid extends CssProperty {

   CssValue markerMid;
    ApplContext ac;

    CssIdent none = new CssIdent("none");

    /**
     * Create a new MarkerMid
     */
   public MarkerMid() {
       //nothing to do
   }
   
   /**
    * Create a new MarkerMid
    *
    * @param expression The expression for this property     
    * @exception InvalidParamException Values are incorrect
    */
   public MarkerMid(ApplContext ac, CssExpression expression) throws InvalidParamException {
       this.ac = ac;
       setByUser(); // tell this property is set by the user
       CssValue val = expression.getValue();
       if (val instanceof CssURL) {
           markerMid = val;
	   expression.next();
       }
       else if (val instanceof CssIdent) {
           if (val.equals(inherit)) {
	       markerMid = inherit;
	       expression.next();
           } else if (val.equals(none)) {
	       markerMid = none;
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
	 if (((SVGStyle) style).markerMid != null)
	     style.addRedefinitionWarning(ac, this);
	 ((SVGStyle) style).markerMid = this;
     }
    
      /**
       * Get this property in the style.
       *
       * @param style The style where the property is
       * @param resolve if true, resolve the style to find this property
       */  
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((SVGStyle) style).getMarkerMid();
	    } else {
		return ((SVGStyle) style).markerMid;
	    }
	}
    
       /**
        * Compares two properties for equality.
        *
        * @param value The other property.
        */  
       public boolean equals(CssProperty property) {
	   return (property instanceof MarkerMid && 
                markerMid.equals( ((MarkerMid) property).markerMid));
       }

    /**
     * Returns the name of this property
     */
   public String getPropertyName() {
       return "marker-mid";
   }
   
    /**
     * Returns the value of this property
     */
   public Object get() {
       return markerMid;
   }
   
    /**
     * Returns true if this property is "softly" inherited
     */
   public boolean isSoftlyInherited() {
       return markerMid.equals(inherit);
   }

   /**
    * Returns a string representation of the object
    */
   public String toString() {
       return markerMid.toString();
   }
 
    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
   public boolean isDefault() {	
       return (markerMid == none);
   }

}
