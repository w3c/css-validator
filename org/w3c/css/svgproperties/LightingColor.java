//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
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
import org.w3c.css.util.Util;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties.CssColor;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssNumber;

/**
 *  <P>
 *  <EM>Value:</EM> currentColor |
                    <color> [icc-color(<name>[,<icccolorvalue>]*)] | inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class LightingColor extends CssProperty implements CssOperator {

    CssValue lightingColor;
    ApplContext ac;
    Vector values = new Vector();

    CssIdent currentColor = new CssIdent("currentColor");

    /**
     * Create a new LightingColor
     */
   public LightingColor() {
       //nothing to do
   }

   /**
    * Create a new LightingColor
    *
    * @param expression The expression for this property
    * @exception InvalidParamException Values are incorrect
    */
   public LightingColor(ApplContext ac, CssExpression expression) throws InvalidParamException {
       this.ac = ac;
       setByUser(); // tell this property is set by the user
       CssValue val = expression.getValue();

       boolean correct = true;
       String errorval = "";
       char op = expression.getOperator();
       CssColor color;

       if (val.equals(inherit)) {
	   		lightingColor = inherit;
	   		expression.next();
       } else if (val.equals(currentColor)) {
	   		lightingColor = currentColor;
	   		expression.next();
       } else {
	   	try {
	   	    color = new CssColor(ac, expression);
	   	    values.addElement(val);
	   	    //expression.next();
	   	} catch (InvalidParamException e) {
	   	    correct = false;
	   	    errorval = val.toString();
	   	}

	   op = expression.getOperator();
	   val = expression.getValue();

	   if (val != null) {
	   	if (val instanceof CssFunction) { // icc-color(<name>[,<icccolorvalue>]*)]
	   	    CssValue function = val;
	   	    if (!((CssFunction) val).getName().equals("icc-color")) {
			   correct = false;
			   errorval = val.toString();
	   	    } else {
			   CssExpression params = ((CssFunction) val).getParameters();

			   op = params.getOperator();
			   val = params.getValue();

			   if (!(val instanceof CssIdent)) {
			       correct = false;
			       errorval = val.toString();
			   }

			   params.next();
			   op = params.getOperator();
			   val = params.getValue();

			   if (!params.end()) { // there are more parameters left
			       int counter = 0;

			       while ((op == COMMA || op == SPACE)
				      && (counter < (params.getCount() - 1) && correct == true)) {

				   if ((!(val instanceof CssNumber)) || (((CssNumber) val).getValue() < 0)) {
				       correct = false;
				       errorval = val.toString();
				   }

				   params.next();
				   counter++;
				   val = params.getValue();
				   op = params.getOperator();
			       }
			   }

			   if (correct) {
			       params.starts();
			       values.addElement(function);
			   }
	   	    }
	   	} else {
	   	    correct = false;
	   	    errorval = val.toString();
	   	}
	   } else {
		   correct = false;
		   errorval = new String("");
	   }

	   expression.next();

       }

       if (!correct) {
	   throw new InvalidParamException("value", errorval, getPropertyName(), ac);
       }
   }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
     public void addToStyle(ApplContext ac, CssStyle style) {
	 if (((SVGStyle) style).lightingColor != null)
	     style.addRedefinitionWarning(ac, this);
	 ((SVGStyle) style).lightingColor = this;
     }

      /**
       * Get this property in the style.
       *
       * @param style The style where the property is
       * @param resolve if true, resolve the style to find this property
       */
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((SVGStyle) style).getLightingColor();
	    } else {
		return ((SVGStyle) style).lightingColor;
	    }
	}

       /**
        * Compares two properties for equality.
        *
        * @param value The other property.
        */
       public boolean equals(CssProperty property) {
	   return (property instanceof LightingColor &&
                lightingColor.equals( ((LightingColor) property).lightingColor));
       }

    /**
     * Returns the name of this property
     */
   public String getPropertyName() {
       return "lighting-color";
   }

    /**
     * Returns the value of this property
     */
   public Object get() {
       if (lightingColor != null)
	   return lightingColor;
       else
	   return values;
   }

    /**
     * Returns true if this property is "softly" inherited
     */
   public boolean isSoftlyInherited() {
       return lightingColor.equals(inherit);
   }

   /**
    * Returns a string representation of the object
    */
   public String toString() {
       if (lightingColor != null) {
	   	return lightingColor.toString();
       } else {
	   String ret = "";
	   for (int i = 0; i < values.size(); i++) {
	       ret += " " + values.elementAt(i).toString();
	   }
	   return ret;
       }
   }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
   public boolean isDefault() {
       return false;
   }

}
