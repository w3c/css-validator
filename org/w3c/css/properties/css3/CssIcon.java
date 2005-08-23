//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated september 14th 2000 by Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css3;
import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class CssIcon extends CssProperty
    implements CssOperator {

    int value;
    CssValue icon;
    Vector uris = new Vector();
    CssIdent auto = new CssIdent("auto");
    boolean inheritedValue;

    /**
     * Create a new CssIcon
     */
    public CssIcon() {
		value = 0;
    }

    /**
     * Create a new CssIcon
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssIcon(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	CssValue val = expression.getValue();
	char op = expression.getOperator();
	
	setByUser();
	boolean correct = false;
	
	
	if (val.equals(inherit)) {
	    inheritedValue = true;
	    icon = inherit;
	    expression.next();
	    return;
	} else if (val.equals(auto)) {
	    icon = auto;
	    expression.next();
	    return;
	}
	
	while (val != null) {
	    if (val instanceof CssURL) {
		uris.addElement(val);
		expression.next();
		val = expression.getValue();
		op = expression.getOperator();
		correct = true;
	    } else {
		throw new InvalidParamException("value",
			val.toString(), getPropertyName(), ac);
	    }
	}
	
	if (!correct) {
	    throw new InvalidParamException("value",
		    val.toString(), getPropertyName(), ac);
	}
    }
    
    public CssIcon(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
		return null;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
		return "icon";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
		return inheritedValue;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
		if (icon != null) {
		    return icon.toString();
		} else {
		    int i = 0;
		    int l = uris.size();
		    String ret = "";
		    while (i != l) {
			ret += uris.elementAt(i++) +
			    (new Character(COMMA)).toString() + " ";
		    }

		    return ret;
		}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
		Css3Style style0 = (Css3Style) style;
		if (style0.cssIcon != null)
		    style0.addRedefinitionWarning(ac, this);
		style0.cssIcon = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
		if (resolve) {
		    return ((Css3Style) style).getIcon();
		} else {
		    return ((Css3Style) style).cssIcon;
		}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
		return (property instanceof CssIcon
			&& value == ((CssIcon) property).value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
		return value == 0;
    }

}
