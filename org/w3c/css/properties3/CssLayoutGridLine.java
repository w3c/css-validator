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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssPercentage;

/**
 *  <P>
 *  <EM>Value:</EM> none || auto || &lt;length&gt; || &lt;percentage&gt; || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>relative to element total line heigths<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property sets the line grid value for an element. 
 */

public class CssLayoutGridLine extends CssProperty {
 
    CssValue line;

    static CssIdent none = new CssIdent("none");
    static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssLayoutGridLine
     */
    public CssLayoutGridLine() {
	line = none;
    }

    /**
     * Create a new CssLayoutGridLine
     * 
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssLayoutGridLine(ApplContext ac, CssExpression expression) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(none)) {
	    line = none;
	    expression.next();
	}
	else if (val.equals(auto)) {
	    line = auto;
	    expression.next();
	}
	else if (val instanceof CssLength) {
	    line = val;
	    expression.next();
	}
	else if (val instanceof CssPercentage) {
	    line = val;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    line = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssLayoutGridLine != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssLayoutGridLine = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getLayoutGridLine();
	}
	else {
	    return ((Css3Style) style).cssLayoutGridLine;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssLayoutGridLine &&
		line.equals(((CssLayoutGridLine) property).line));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "layout-grid-line";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return line;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return line.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return line.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return line == none;
    }

}
