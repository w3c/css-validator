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
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *  <P>
 *  <EM>Value:</EM>  none | solid | double | dotted | thick | dashed | dot-dash | dot-dot-dash | wave | inherit<BR>
 *  <EM>Initial:</EM>solid<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property specifies the overline style to use when 'text-decoration' 
 *  is set to 'overline'.
 */

public class CssTextOLStyle extends CssProperty {
 
    CssValue olstyle;

    static CssIdent solid = new CssIdent("solid");

    private static String[] values = {
	"none", "solid", "double", "dotted", "thick", "dashed", "dot-dash", 
	"dot-dot-dash", "wave", "inherit"
    };

    /**
     * Create a new CssTextOLStyle
     */
    public CssTextOLStyle() {
	olstyle = solid;
    }

    /**
     * Create a new CssTextOLStyle
     * 
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssTextOLStyle(ApplContext ac, CssExpression expression) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();

	int i = 0;
	for (;i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		olstyle = val;
		expression.next();
		break;
	    }
	}
	if (i == values.length) {
	    throw new InvalidParamException("value", 
					    expression.getValue(), 
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextOLStyle != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextOLStyle = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextOLStyle();
	}
	else {
	    return ((Css3Style) style).cssTextOLStyle;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextOLStyle &&
		olstyle.equals(((CssTextOLStyle) property).olstyle));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-overline-style";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return olstyle;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return olstyle.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return olstyle.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return olstyle == solid;
    }

}
