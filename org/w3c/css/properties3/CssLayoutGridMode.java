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
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *  <P>
 *  <EM>Value:</EM> none || line || char || both || inherit<BR>
 *  <EM>Initial:</EM>both<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property selectively enables or disables the two dimensions of the grid.
 */

public class CssLayoutGridMode extends CssProperty {
 
    CssValue mode;

    static CssIdent none = new CssIdent("none");
    static CssIdent line  = new CssIdent("line");
    static CssIdent gridchar = new CssIdent("char");
    static CssIdent both = new CssIdent("both");

    /**
     * Create a new CssLayoutGridMode
     */
    public CssLayoutGridMode() {
	mode = both;
    }

    /**
     * Create a new CssLayoutGridMode
     * 
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssLayoutGridMode(ApplContext ac, CssExpression expression) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(none)) {
	    mode = none;
	    expression.next();
	}
	else if (val.equals(line)) {
	    mode = line;
	    expression.next();
	}
	else if (val.equals(gridchar)) {
	    mode = gridchar;
	    expression.next();
	}
	else if (val.equals(both)) {
	    mode = both;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    mode = inherit;
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
	if (((Css3Style) style).cssLayoutGridMode != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssLayoutGridMode = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getLayoutGridMode();
	}
	else {
	    return ((Css3Style) style).cssLayoutGridMode;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssLayoutGridMode &&
		mode.equals(((CssLayoutGridMode) property).mode));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "layout-grid-mode";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return mode;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return mode.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return mode.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return mode == both;
    }

}
