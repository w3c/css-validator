//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 14th Sijtsche de Jong (sy.de.jong@.let.rug.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties;

import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssString;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 */
public class CssContent extends CssProperty {

    CssValue value;

    CssIdent normal = new CssIdent("normal");

    private static String CONTENT[] = {
		"normal", "disc", "circle", "square", "box", "check", "diamond"
    };

    private static int[] hash_values;


    /**
     * Create a new CssContent
     */
    public CssContent() {
		value = normal;
    }

    /**
     * Create a new CssContent
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssContent(ApplContext ac, CssExpression expression) throws InvalidParamException {

		CssValue val = expression.getValue();

		setByUser();

		if (val.equals(inherit)) {
		    value = val;
		    expression.next();
		    return;
		}

	    if (val instanceof CssIdent) {
			int hash = val.hashCode();
			int i = 0;

			for (;i < CONTENT.length; i++) {
			    if (hash_values[i] == hash) {
					break;
			    }
			}
			if (i == CONTENT.length) {
			    throw new InvalidParamException("value",
							    expression.getValue(),
							    getPropertyName(), ac);
			}
		} else if (val instanceof CssURL) {
			value = val;
			expression.next();
		} else {
			throw new InvalidParamException("value",
							expression.getValue(),
							getPropertyName(), ac);
		}

    }

    /**
     * Returns the value of this property
     */
    public Object get() {
		return value;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
		return "content";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
		return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
		return value.toString();
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
		Css1Style style0 = (Css1Style) style;
		if (style0.cssContent != null) {
		    style0.addRedefinitionWarning(ac, this);
		}
		style0.cssContent = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
		if (resolve) {
		    return ((Css1Style) style).getContent();
		} else {
		    return ((Css1Style) style).cssContent;
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
		return value == normal;
    }

    static {
		hash_values = new int[CONTENT.length];
		for (int i=0; i<CONTENT.length; i++)
		    hash_values[i] = CONTENT[i].hashCode();
    }
}
