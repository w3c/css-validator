//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2016.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.svg;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec https://www.w3.org/TR/2014/CR-css-masking-1-20140826/#the-clip-path
 */
public class CssClipPath extends org.w3c.css.properties.css.CssClipPath {

	/**
	 * Create a new CssClipPath
	 */
	public CssClipPath() {
		value = initial;
	}

	/**
	 * Creates a new CssClipPath
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssClipPath(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;

		val = expression.getValue();

		switch (val.getType()) {
			case CssTypes.CSS_FUNCTION:
				CssFunction func = (CssFunction) val;
				String funcname = func.getName().toLowerCase();
				if (funcname.equals("inset")) {
					checkInset(ac, func.getParameters(), this);
				} else {
					throw new InvalidParamException("value", val,
							getPropertyName(), ac);
				}
				value = val;
				break;
			case CssTypes.CSS_URL:
				value = val;
				break;
			case CssTypes.CSS_IDENT:
				if (inherit.equals(val)) {
					value = inherit;
					break;
				}
				if (none.equals(val)) {
					value = none;
					break;
				}
			default:
				throw new InvalidParamException("value",
						val.toString(),
						getPropertyName(), ac);
		}
		expression.next();
	}

	public CssClipPath(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	static void checkInset(ApplContext ac, CssExpression expression,
						   CssProperty caller) throws InvalidParamException {
		if (expression.getCount() > 4) {
			throw new InvalidParamException("unrecognize", ac);
		}
		CssValue val;
		char op;
		for (int i = 0; i < 4; i++) {
			val = expression.getValue();
			op = expression.getOperator();
			if (op != SPACE) {
				throw new InvalidParamException("inset-separator",
						((new Character(op)).toString()), ac);
			}
			switch (val.getType()) {
				case CssTypes.CSS_LENGTH:
					break;
				case CssTypes.CSS_PERCENTAGE:
					break;
				default:
					throw new InvalidParamException("value",
							val.toString(),
							caller.getPropertyName(), ac);
			}
			expression.next();
		}
	}
}

