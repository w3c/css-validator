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
 *  <EM>Percentages:</EM>relative to element line width<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property affects the dimension perpendicular to that controlled by 
 *  'layout-grid-line'. It controls the character (or "horizontal", if in 
 *  horizontal layout) grid size for an element if the 'layout-grid-type' 
 *  property is set to 'strict' or 'fixed'. However, if 'layout-grid-type 
 *  is 'loose', then this property sets the size of the increment added to \
 *  each wide-cell glyph, and, indirectly, of that added to each narrow-cell 
 *  glyph, as per the description in the specification of 'layout-grid-type'. 
 *  Its effect in 'loose' grid is somewhat similar to the effect of the 
 *  'letter-spacing' property. 
 */

public class CssLayoutGridChar extends CssProperty {
 
    CssValue gridchar;

    static CssIdent none = new CssIdent("none");
    static CssIdent auto = new CssIdent("auto");

    /**
     * Create a new CssLayoutGridChar
     */
    public CssLayoutGridChar() {
	gridchar = none;
    }

    /**
     * Create a new CssLayoutGridChar
     * 
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssLayoutGridChar(ApplContext ac, CssExpression expression) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(none)) {
	    gridchar = none;
	    expression.next();
	}
	else if (val.equals(auto)) {
	    gridchar = auto;
	    expression.next();
	}
	else if (val instanceof CssLength) {
	    gridchar = val;
	    expression.next();
	}
	else if (val instanceof CssPercentage) {
	    gridchar = val;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    gridchar = inherit;
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
	if (((Css3Style) style).cssLayoutGridChar != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssLayoutGridChar = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getLayoutGridChar();
	}
	else {
	    return ((Css3Style) style).cssLayoutGridChar;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssLayoutGridChar &&
		gridchar.equals(((CssLayoutGridChar) property).gridchar));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "layout-grid-char";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return gridchar;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return gridchar.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return gridchar.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return gridchar == none;
    }

}
