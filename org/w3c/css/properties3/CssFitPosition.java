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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.Util;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;


public class CssFitPosition extends CssProperty implements CssOperator {

   String fitpos = new String();
    ApplContext ac;
    CssIdent auto = new CssIdent("auto");
    CssIdent initial = new CssIdent("initial");
    CssIdent top = new CssIdent("top");
    CssIdent center = new CssIdent("center");
    CssIdent bottom = new CssIdent("bottom");

    CssIdent left = new CssIdent("left");
    CssIdent right = new CssIdent("right");

    /**
     * Create a new CssFitPosition
     */
   public CssFitPosition() {
       fitpos = "0% 0%";
   }

   /**
    * Create a new CssFitPosition
    *
    * @param expression The expression for this property
    * @exception InvalidParamException Values are incorrect
    */
   public CssFitPosition(ApplContext ac, CssExpression expression) throws InvalidParamException {

       this.ac = ac;
       setByUser(); // tell this property is set by the user
       CssValue val = expression.getValue();
       char op = expression.getOperator();

       if (op == SPACE) {

		   val = expression.getValue();

		   if (val != null) {

		   		if (val instanceof CssIdent) {
		   			if (val.equals(top)) {
						fitpos += " " + val.toString();
					} else if (val.equals(center)) {
						fitpos += " " + val.toString();
					} else if (val.equals(bottom)) {
						fitpos += " " + val.toString();
					} else if (val.equals(initial)) {
						fitpos += " " + val.toString();
					} else {
						throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
					}

					expression.next();
					op = expression.getOperator();
					val = expression.getValue();

					if (val != null && val instanceof CssIdent) {
			   			if (val.equals(left)) {
							fitpos += " " + val.toString();
						} else if (val.equals(center)) {
							fitpos += " " + val.toString();
						} else if (val.equals(right)) {
							fitpos += " " + val.toString();
						} else {
							throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
						}
					}

					expression.next();

	   	   		} else if (val instanceof CssLength) {
					fitpos += " " + val.toString();

					expression.next();
					op = expression.getOperator();
					val = expression.getValue();

					if (val != null && val instanceof CssLength) {
						fitpos += " " + val.toString();
						expression.next();
					} else if (val instanceof CssPercentage) {
						fitpos += " " + val.toString();
						expression.next();
					}

		   		} else if (val instanceof CssPercentage) {
					fitpos += " " + val.toString();

					expression.next();
					op = expression.getOperator();
					val = expression.getValue();

					if (val != null && val instanceof CssLength) {
						fitpos += " " + val.toString();
						expression.next();
					} else if (val instanceof CssPercentage) {
						fitpos += " " + val.toString();
						expression.next();
					}

				} else {
					throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
				}
			}

	   }
       else if (val instanceof CssIdent) {
           if (val.equals(inherit)) {
	       		fitpos = "inherit";
	       		expression.next();
           } else if (val.equals(auto)) {
			    fitpos = "auto";
			    expression.next();
		   } else if (val.equals(initial)) {
				fitpos = "initial";
			    expression.next();
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
		 if (((Css3Style) style).cssFitPosition != null)
		     style.addRedefinitionWarning(ac, this);
		 ((Css3Style) style).cssFitPosition = this;
     }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
     public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
			return ((Css3Style) style).getFitPosition();
	    } else {
			return ((Css3Style) style).cssFitPosition;
	    }
	 }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */
   public boolean equals(CssProperty property) {
	   return (property instanceof CssFitPosition &&
               fitpos.equals( ((CssFitPosition) property).fitpos));
   }

    /**
     * Returns the name of this property
     */
   public String getPropertyName() {
       return "fit-position";
   }

    /**
     * Returns the value of this property
     */
   public Object get() {
       return fitpos;
   }

    /**
     * Returns true if this property is "softly" inherited
     */
   public boolean isSoftlyInherited() {
       return fitpos.equals("inherit");
   }

   /**
    * Returns a string representation of the object
    */
   public String toString() {
       return fitpos;
   }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
   public boolean isDefault() {
       return fitpos.equals("0% 0%");
   }

}

