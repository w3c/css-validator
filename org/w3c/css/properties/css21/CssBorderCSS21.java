// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssBorderBottomStyleCSS2;
import org.w3c.css.properties.css1.CssBorderBottomWidthCSS2;
import org.w3c.css.properties.css1.CssBorderCSS2;
import org.w3c.css.properties.css1.CssBorderFaceStyleCSS2;
import org.w3c.css.properties.css1.CssBorderFaceWidthCSS2;
import org.w3c.css.properties.css1.CssBorderLeftStyleCSS2;
import org.w3c.css.properties.css1.CssBorderLeftWidthCSS2;
import org.w3c.css.properties.css1.CssBorderRightStyleCSS2;
import org.w3c.css.properties.css1.CssBorderRightWidthCSS2;
import org.w3c.css.properties.css1.CssBorderTopColorCSS2;
import org.w3c.css.properties.css1.CssBorderTopStyleCSS2;
import org.w3c.css.properties.css1.CssBorderTopWidthCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * CssBorderCSS21<br />
 * Created: Aug 31, 2005 2:07:02 PM<br />
 */
public class CssBorderCSS21 extends CssBorderCSS2 {

    /**
     * Create a new CssBorderFaceCSS2
     */
    public CssBorderCSS21() {
	super();
    }

    /**
     * Create a new CssBorderFaceCSS2
     *
     * @param value The value for this property
     * @exception InvalidParamException The value is incorrect
     */
    public CssBorderCSS21(ApplContext ac, CssExpression value,
	    boolean check) throws InvalidParamException {

	if(check && value.getCount() > 3) {
	     throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = value.getValue();

	setByUser();

	setTop(new CssBorderTopCSS21(ac, value));

	if (val == value.getValue()) {
	    throw new InvalidParamException("value",
					    value.getValue(),
					    getPropertyName(), ac);
	}
	setRight(new CssBorderRightCSS21());
	setBottom(new CssBorderBottomCSS21());
	setLeft(new CssBorderLeftCSS21());

	CssBorderTopCSS21 top = (CssBorderTopCSS21) getTop();
	CssBorderRightCSS21 right = (CssBorderRightCSS21) getRight();
	CssBorderLeftCSS21 left = (CssBorderLeftCSS21) getLeft();
	CssBorderBottomCSS21 bottom = (CssBorderBottomCSS21) getBottom();
	
	CssBorderTopWidthCSS2 w = top.getWidth2();
	CssBorderTopStyleCSS2 s = top.getStyle2();
	CssBorderTopColorCSS2 c = top.getColor2();

	if(w != null) {
	    CssBorderFaceWidthCSS2 bfw = (CssBorderFaceWidthCSS2) w.get();
	    right.setWidth(new CssBorderRightWidthCSS2(bfw));
	    left.setWidth(new CssBorderLeftWidthCSS2(bfw));
	    bottom.setWidth(new CssBorderBottomWidthCSS2(bfw));
	}
	if(s != null) {
	    CssBorderFaceStyleCSS2 bfs = (CssBorderFaceStyleCSS2) s.get();
	    right.setStyle(new CssBorderRightStyleCSS2(bfs));
	    left.setStyle(new CssBorderLeftStyleCSS2(bfs));
	    bottom.setStyle(new CssBorderBottomStyleCSS2(bfs));
	}
	if(c != null) {
	    CssBorderFaceColorCSS21 bfc = (CssBorderFaceColorCSS21) c.get();
	    right.setColor(new CssBorderRightColorCSS21(bfc));
	    left.setColor(new CssBorderLeftColorCSS21(bfc));
	    bottom.setColor(new CssBorderBottomColorCSS21(bfc));
	}
    }

    public CssBorderCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

}
