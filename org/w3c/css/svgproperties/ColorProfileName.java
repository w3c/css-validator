//
// $Id$
// From Sijtsche Smeman (sijtsche@wisdom.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.svgproperties;

import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssURL;

/**
 * This property sets the preferred media for this stylesheet
 */

public class ColorProfileName extends CssProperty implements CssOperator {

    CssValue cpName;

    /**
     * Create a new ColorProfileName
     */
    public ColorProfileName() {
		// no initial value
    }

    /**
     * Create a new ColorProfileName
     */
    public ColorProfileName(ApplContext ac, CssExpression expression) throws InvalidParamException {

		CssValue val = expression.getValue();

		if (val instanceof CssIdent) {
			cpName = val;
			expression.next();
		} else {
			throw new InvalidParamException("value",
				val, getPropertyName(), ac);
		}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
    */
    public void addToStyle(ApplContext ac, CssStyle style) {
		if (((SVGStyle) style).cpName != null)
	 	    style.addRedefinitionWarning(ac, this);
	 	((SVGStyle) style).cpName = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
     public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	 	if (resolve) {
			return ((SVGStyle) style).getColorProfileName();
	   	} else {
			return ((SVGStyle) style).cpName;
	   	}
	 }

     /**
      * Compares two properties for equality.
      *
      * @param cpName The other property.
      */
     public boolean equals(CssProperty property) {
  		return (property instanceof ColorProfileName &&
  	      cpName.equals( ((ColorProfileName) property).cpName));
     }

    /**
     * Returns the name of this property
     */
   public String getPropertyName() {
       return "name";
   }

    /**
     * Returns the cpName of this property
     */
   public Object get() {
  		return cpName;
   }

    /**
     * Returns true if this property is "softly" inherited
     */
   public boolean isSoftlyInherited() {
       return false;
   }

   /**
    * Returns a string representation of the object
    */
   public String toString() {
   		return cpName.toString();
   }

    /**
     * Is the cpName of this property a default cpName
     * It is used by all macro for the function <code>print</code>
     */
   public boolean isDefault() {
       return false;
   }

}
