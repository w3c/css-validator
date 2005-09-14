// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssBorderBottomColorCSS2;
import org.w3c.css.properties.css1.CssBorderColorCSS2;
import org.w3c.css.properties.css1.CssBorderLeftColorCSS2;
import org.w3c.css.properties.css1.CssBorderRightColorCSS2;
import org.w3c.css.properties.css1.CssBorderTopColorCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * CssBorderColorCSS21<br />
 * Created: Aug 31, 2005 2:09:05 PM<br />
 */
public class CssBorderColorCSS21 extends CssBorderColorCSS2 {

    /**
     * Create a new CssBorderColorCSS2 with all four sides
     */
    public CssBorderColorCSS21(CssBorderTopColorCSS2 top,
			  CssBorderBottomColorCSS2 bottom,
			  CssBorderRightColorCSS2 right,
			  CssBorderLeftColorCSS2 left) {
	super(top, bottom, right, left);
    }

    /**
     * Create a new CssBorderColorCSS21
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderColorCSS21(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();

	switch (expression.getCount()) {
	case 1:
	    setTop(new CssBorderTopColorCSS21(ac, expression));
//	    setBottom(new CssBorderBottomColorCSS21(
//		    	(CssBorderFaceColorCSS21) getTop().get()));
//	    setRight(new CssBorderRightColorCSS21(
//		    (CssBorderFaceColorCSS21) getTop().get()));
//	    setLeft(new CssBorderLeftColorCSS21(
//		    (CssBorderFaceColorCSS21) getTop().get()));
	    break;
	case 2:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    setTop(new CssBorderTopColorCSS21(ac, expression));
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    setRight(new CssBorderRightColorCSS21(ac, expression));
//	    setBottom(new CssBorderBottomColorCSS21(
//		    (CssBorderFaceColorCSS21) getTop().get()));
//	    setLeft(new CssBorderLeftColorCSS21(
//		    (CssBorderFaceColorCSS21) getRight().get()));
	    break;
	case 3:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    setTop(new CssBorderTopColorCSS21(ac, expression));
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()), ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    setRight(new CssBorderRightColorCSS21(ac, expression));
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    setBottom(new CssBorderBottomColorCSS21(ac, expression));
//	    setLeft(new CssBorderLeftColorCSS21(
//		    (CssBorderFaceColorCSS21) getRight().get()));
	    break;
	case 4:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    setTop(new CssBorderTopColorCSS21(ac, expression));
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    setRight(new CssBorderRightColorCSS21(ac, expression));
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator",
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    setBottom(new CssBorderBottomColorCSS21(ac, expression));
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    setLeft(new CssBorderLeftColorCSS21(ac, expression));
	    break;
	default:
	    if(check) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	}
    }

    public CssBorderColorCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

}
