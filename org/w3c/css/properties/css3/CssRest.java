// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2013.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

/**
 * @spec http://www.w3.org/TR/2012/CR-css3-speech-20120320/#rest
 */
public class CssRest extends org.w3c.css.properties.css.CssRest {

	/**
	 * Create a new CssRest
	 */
	public CssRest() {
		cssRestAfter = new CssRestAfter();
		cssRestBefore = new CssRestBefore();
		value = initial;
	}

	/**
	 * Creates a new CssRest
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssRest(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 2) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		char op;

		cssRestBefore = new CssRestBefore(ac, expression, false);
		if (expression.end()) {
			cssRestAfter = new CssRestAfter();
			cssRestAfter.value = cssRestBefore.value;
			value = cssRestBefore.value;
		} else {
			op = expression.getOperator();
			if (op != CssOperator.SPACE) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
			cssRestAfter = new CssRestAfter(ac, expression, false);
			if (cssRestBefore.value == inherit || cssRestAfter.value == inherit) {
				throw new InvalidParamException("value",
						inherit, getPropertyName(), ac);
			}
			ArrayList<CssValue> values = new ArrayList<CssValue>(2);
			values.add(cssRestBefore.value);
			values.add(cssRestAfter.value);
			value = new CssValueList(values);
		}
	}

	public CssRest(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}
}

