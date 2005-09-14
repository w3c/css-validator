//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css1;

import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssString;
import org.w3c.css.values.CssValue;

/**
 */
public class CssQuotesATSC extends CssProperty {

    Vector values = new Vector();

    private static CssIdent none = new CssIdent("none");


    /**
     * Create a new CssQuotesATSC
     */
    public CssQuotesATSC() {
    }

    /**
     * Create a new CssQuotesATSC
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssQuotesATSC(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {

	CssValue val = expression.getValue();
	int counter = 0;
	char op = expression.getOperator();

	ac.getFrame().addWarning("atsc", val.toString());

	setByUser();
	if (val.equals(inherit)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    values.addElement(inherit);
	    expression.next();
	    return;
	} else if (val.equals(none)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    values.addElement(none);
	    expression.next();
	    return;
	}


	while ((op == CssOperator.SPACE)
	       && (counter + 1 < expression.getCount())) {
	    if (val instanceof CssString) {
		values.addElement(val);
		expression.next();
		counter++;
		val = expression.getValue();
		op = expression.getOperator();
		if ((op == CssOperator.SPACE)
		    && (val instanceof CssString)) {
		    // nothing
		} else {
		    throw new InvalidParamException("value",
						    expression.getValue(),
						    getPropertyName(), ac);
		}
	    } else {
		throw new InvalidParamException("value",
						expression.getValue(),
						getPropertyName(), ac);
	    }
	    values.addElement(val);
	    expression.next();
	    counter++;
	    val = expression.getValue();
	    op = expression.getOperator();
	}

    }

    public CssQuotesATSC(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return values;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "quotes";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return values.elementAt(0) == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	int i = 0;
	String ret = "";
	while (i < values.size()) {
	    ret += " " + values.elementAt(i);
	    i++;
	}
	return ret.substring(1);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssQuotesATSC != null) {
	    style0.addRedefinitionWarning(ac, this);
	}
	style0.cssQuotesATSC = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getQuotesATSC();
	} else {
	    return ((Css1Style) style).cssQuotesATSC;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	// @@TODO
	return false;
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return false;
    }

}
