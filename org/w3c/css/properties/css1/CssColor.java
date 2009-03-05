//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css1;

import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'color'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;color&gt;<BR>
 *   <EM>Initial:</EM> UA specific<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property describes the text color of an element (often referred to as
 *   the <EM>foreground</EM> color). There are different ways to specify red:
 *   <PRE>
 *   EM { color: red }              /* natural language * /
 *   EM { color: rgb(255,0,0) }     /* RGB range 0-255   * /
 * </PRE>
 * @version $Revision$
 */
public class CssColor extends CssProperty implements CssOperator {

    CssValue color;
    org.w3c.css.values.CssColor tempcolor = new org.w3c.css.values.CssColor();
    String attrvalue = null;

    /**
     * Create a new CssColor
     */
    public CssColor() {
	color = inherit;
    }

    /**
     * Set the value of the property
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssColor(ApplContext ac, CssExpression expression, boolean check)
	throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	setByUser();

	if (val.equals(inherit)) {
	    color = inherit;
	    expression.next();
	}
	else if (val instanceof org.w3c.css.values.CssColor) {
	    color = val;
	    expression.next();
	} else if (val instanceof CssFunction) {
	    CssFunction attr = (CssFunction) val;
	    CssExpression params = attr.getParameters();

	    if (attr.getName().equals("attr")) {

		CssValue v1 = params.getValue();
		params.next();
		CssValue v2 = params.getValue();

		if ((params.getCount() != 2)) {
		    throw new InvalidParamException("value",
						    params.getValue(),
						    getPropertyName(), ac);
		} else if (!(v1 instanceof CssIdent)) {
		    throw new InvalidParamException("value",
						    params.getValue(),
						    getPropertyName(), ac);

		} else if (!(v2.toString().equals("color"))) {
		    throw new InvalidParamException("value",
						    params.getValue(),
						    getPropertyName(), ac);
		} else {
		    attrvalue = "attr(" + v1 + ", " + v2 + ")";
		    expression.next();
		}
	    } else if (attr.getName().equals("rgba")) {
		tempcolor.setRGBAColor(params, ac);
		color = tempcolor;
		expression.next();
	    } else if (attr.getName().equals("hsl")) {
		Vector hslValues = new Vector();
		char op;

		CssValue v1 = params.getValue();
		op = params.getOperator();
		if (v1 == null || op != COMMA) {
		    throw new InvalidParamException("invalid-color", ac);
		}
		hslValues.addElement(v1);
		params.next();

		CssValue v2 = params.getValue();
		op = params.getOperator();
		if (v2 == null || op != COMMA) {
		    throw new InvalidParamException("invalid-color", ac);
		}
		hslValues.addElement(v2);
		params.next();

		CssValue v3 = params.getValue();
		if (v3 == null) {
		    throw new InvalidParamException("invalid-color", ac);
		}
		hslValues.addElement(v3);

		params.starts(); // set position back to the first value

		tempcolor.setHSLColor(hslValues, ac);
		params.ends();
		color = tempcolor;
		expression.next();

	    } else if (attr.getName().equals("hsla")) {

		Vector hslaValues = new Vector();

		char op;

		CssValue v1 = params.getValue();
		op = params.getOperator();
		if (v1 == null || op != COMMA) {
		    throw new InvalidParamException("invalid-color", ac);
		}
		hslaValues.addElement(v1);
		params.next();

		CssValue v2 = params.getValue();
		op = params.getOperator();
		if (v2 == null || op != COMMA) {
		    throw new InvalidParamException("invalid-color", ac);
		}
		hslaValues.addElement(v2);
		params.next();

		CssValue v3 = params.getValue();
		op = params.getOperator();
		if (v3 == null || op != COMMA) {
		    throw new InvalidParamException("invalid-color", ac);
		}
		hslaValues.addElement(v3);
		params.next();

		CssValue v4 = params.getValue();
		if (v4 == null) {
		    throw new InvalidParamException("invalid-color", ac);
		}
		hslaValues.addElement(v4);

		params.starts();
		tempcolor.setHSLAColor(hslaValues, ac);
		params.ends();
		color = tempcolor;
		expression.next();

	    } else {
		throw new InvalidParamException("value",
						params.getValue(),
						getPropertyName(), ac);
	    }
	} else if (val instanceof CssIdent) {
	    if ("css1".equals(ac.getCssVersion())) {
		color = new org.w3c.css.values.CssColorCSS1(ac, (String) val.get());
	    } else if ("css2".equals(ac.getCssVersion())) {
		color = new org.w3c.css.values.CssColorCSS2(ac, (String) val.get());
	    } else if ("css3".equals(ac.getCssVersion())){
		color = new org.w3c.css.values.CssColor(ac, (String) val.get());
	    } else {
		color = new org.w3c.css.values.CssColorCSS2(ac, (String) val.get()); // SVG profiles
	    }
	    //	    color = new org.w3c.css.values.CssColor();
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    public CssColor(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return color;
    }

    /**
     * Returns the color
     */
    public org.w3c.css.values.CssColor getColor() {
	if (color.equals(inherit)) {
	    /*
	      System.err.println("[ERROR] org.w3c.css.properties.CssColor");
	      System.err.println("[ERROR] value is inherited");
	    */
	    return null;
	} else {
	    return (org.w3c.css.values.CssColor) color;
	}
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return color.equals(inherit);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (attrvalue != null) {
	    return attrvalue;
	} else {
	    return color.toString();
	}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssColor != null) {
	    style0.addRedefinitionWarning(ac, this);
	}
	style0.cssColor = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getColor();
	} else {
	    return ((Css1Style) style).cssColor;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssColor &&
		color.equals(((CssColor) property).color));
}

    /**
     * Returns the name of this property
     */
public String getPropertyName() {
    return "color";
}

}
