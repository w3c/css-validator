// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssBorderTopStyleCSS2;
import org.w3c.css.properties.css1.CssBorderTopWidthCSS2;
import org.w3c.css.properties.css1.CssBorderTopCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * CssBorderTopCSS21<br />
 * Created: Aug 31, 2005 2:08:11 PM<br />
 */
public class CssBorderTopCSS21 extends CssBorderTopCSS2 {

    /**
     * Create a new CssBorderTopCSS21
     */
    public CssBorderTopCSS21() {
	super();
    }

    /**
     * Create a new CssBorderTopCSS21
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssBorderTopCSS21(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = null;
	char op;
	boolean find = true;

	if(check && expression.getCount() > 3) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	boolean manyValues = (expression.getCount() > 1);

	setByUser();

	while (find) {
	    val = expression.getValue();
	    if (val == null)
		break;
	    op = expression.getOperator();
	    if (op != SPACE)
		throw new InvalidParamException("operator",
						Character.toString(op),
						ac);	 
	    switch (val.getType()) {
	    case CssTypes.CSS_COLOR:
		if (getColor() == null) {
		    // throws an exception if the value is not valid
		    setColor(new CssBorderTopColorCSS21(ac, expression));
		    continue;
		}
		break;
	    case CssTypes.CSS_NUMBER:
	    case CssTypes.CSS_LENGTH:
		if (getWidth() == null) {
		    setWidth(new CssBorderTopWidthCSS2(ac, expression));
		    continue;
		}
		break;
	    case CssTypes.CSS_IDENT:
		if(manyValues && inherit.equals(val)) {
		    throw new InvalidParamException("unrecognize", null, 
						    null, ac);
		}
		if (getWidth() == null) {
		    try {
			setWidth(new CssBorderTopWidthCSS2(ac, expression));
			continue;
		    } catch(InvalidParamException e){
			// nothing to do, color will test this value
		    }
		}
		if (getStyle() == null) {
		    try {
			setStyle(new CssBorderTopStyleCSS2(ac, expression));
			continue;
		    } catch(InvalidParamException e){
			// nothing to do, color will test this value
		    }
		}
		if (getColor() == null) {
		    // throws an exception if the value is not valid
		    setColor(new CssBorderTopColorCSS21(ac, expression));
		    continue;
		}
	    default:
		find = false;
	    }
	    throw new InvalidParamException("unrecognize", null, 
					    null, ac);
	}
    }

    public CssBorderTopCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression,false);
    }

}
