// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssListStyleCSS2;
import org.w3c.css.properties.css1.CssListStyleImageCSS2;
import org.w3c.css.properties.css1.CssListStylePositionCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * CssListStyleCSS21<br />
 * Created: Aug 31, 2005 12:09:48 PM<br />
 */
public class CssListStyleCSS21 extends CssListStyleCSS2 {
    /**
     * Create a new CssListStyleCSS2
     */
    public CssListStyleCSS21() {
	// nothing to do
    }

    /**
     * Create a new CssListStyleCSS2
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssListStyleCSS21(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	int exprLength = expression.getCount();

	if(check && exprLength > 3) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	char op = SPACE;
	boolean find = true;

	setByUser();

	if (val.equals(inherit)) {
	    if(exprLength > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    setInheritedValue(true);
	    expression.next();
	    return;
	}

	while (find) {
	    find = false;
	    val = expression.getValue();
	    op = expression.getOperator();

	    if(val != null && val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }

	    if ((getListStyleType() == null)	&& (val != null)) {
		try {
		    setListStyleType(new CssListStyleTypeCSS21(ac, expression));
		    find = true;
		} catch (InvalidParamException e) {
		}
	    }
	    if (!find && (getListStyleImage() == null) && (val != null)) {
		try {
		    setListStyleImage(new CssListStyleImageCSS2(ac, expression));
		    find = true;
		} catch (InvalidParamException e) {
		}
	    }
	    if (!find && (val != null) && (getListStylePosition() == null)) {
		setListStylePosition(new CssListStylePositionCSS2(ac, expression));
		find = true;
	    }
	    if(val != null && !find) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    if (op != SPACE) {
		throw new InvalidParamException("operator",
						((new Character(op)).toString()),
						ac);
	    }
	}

    }

    public CssListStyleCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
}
