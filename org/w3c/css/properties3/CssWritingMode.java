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
 *  <EM>Value:</EM> lr-tb || rl-tb || tb-rl || bt-rl || lr || rl || tb || inherit<BR>
 *  <EM>Initial:</EM>lr-tb<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property sets the primary text advance direction
 */

public class CssWritingMode extends CssProperty {

    CssValue mode;

    static CssIdent lrtb = new CssIdent("lr-tb");
    static CssIdent rltb = new CssIdent("rl-tb");
    static CssIdent tbrl = new CssIdent("tb-rl");
    static CssIdent tblr = new CssIdent("tb-lr");
    static CssIdent lr = new CssIdent("lr");
    static CssIdent rl = new CssIdent("rl");
    static CssIdent tb = new CssIdent("tb");
	static CssIdent ltr = new CssIdent("ltr");
    static CssIdent rtl = new CssIdent("rtl");


    /**
     * Create a new CssWritingMode
     */
    public CssWritingMode() {
	mode = lrtb;
    }

    /**
     * Create a new CssWritingMode
     */
    public CssWritingMode(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if (val.equals(lrtb)) {
	    mode = lrtb;
	    expression.next();
	}
	else if (val.equals(rltb)) {
	    mode = rltb;
	    expression.next();
	}
	else if (val.equals(tbrl)) {
	    mode = tbrl;
	    expression.next();
	}
	else if (val.equals(tblr)) {
	    mode = tblr;
	    expression.next();
	}
	else if (val.equals(lr)) {
	    mode = lr;
	    expression.next();
	}
	else if (val.equals(rl)) {
	    mode = rl;
	    expression.next();
	}
	else if (val.equals(tb)) {
	    mode = tb;
	    expression.next();
	}
	else if (val.equals(ltr)) {
	    mode = ltr;
	    expression.next();
	}
	else if (val.equals(rtl)) {
	    mode = rtl;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    mode = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssWritingMode != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssWritingMode = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getWritingMode();
	}
	else {
	    return ((Css3Style) style).cssWritingMode;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssWritingMode &&
		mode.equals(((CssWritingMode) property).mode));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "writing-mode";
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
	return mode == lrtb;
    }

}
