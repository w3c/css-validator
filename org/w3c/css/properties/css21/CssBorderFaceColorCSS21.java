// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssBorderFaceColorCSS2;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 * CssBorderFaceColorCSS21<br />
 * Created: Aug 31, 2005 3:29:22 PM<br />
 */
public class CssBorderFaceColorCSS21 extends CssBorderFaceColorCSS2 {

    /**
     * Create a new CssBorderFaceColor
     */
    public CssBorderFaceColorCSS21() {
	super();
    }

    /**
     * Create a new CssBorderFaceColor with a color property.
     *
     * @param color A color property
     */
    public CssBorderFaceColorCSS21(org.w3c.css.properties.css1.CssColorCSS2 color) {
	super(color);
    }

    /**
     * Create a new CssBorderFaceColor with an another CssBorderFaceColor
     *
     * @param another An another face.
     */
    public CssBorderFaceColorCSS21(CssBorderFaceColorCSS2 another) {
	super(another);
    }

    /**
     * Create a new CssBorderFaceColor with an expression
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException color is not a color
     */
    public CssBorderFaceColorCSS21(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();

	if (val instanceof org.w3c.css.values.CssColor) {
	    setFace(val);
	} else if (val.equals(CssProperty.inherit)) {
	    setFace(CssProperty.inherit);
	} else if(val.equals(CssProperty.transparent)) {
	    setFace(CssProperty.transparent);
	} else if (val instanceof CssIdent) {
	    setFace(new org.w3c.css.values.CssColorCSS21(ac, (String) val.get()));
	} else {
	    throw new InvalidParamException("value", val.toString(),
					    "border-color", ac);
	}
	expression.next();
    }

    public CssBorderFaceColorCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
}
