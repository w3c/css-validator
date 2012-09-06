// $Id$
//
// (c) COPYRIGHT MIT, INRIA and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-text-20120814/#word-spacing0
 */
public class CssWordSpacing extends org.w3c.css.properties.css.CssWordSpacing {

	private static CssIdent normal = CssIdent.getIdent("normal");

	/**
	 * Create a new CssWordSpacing.
	 */
	public CssWordSpacing() {
		value = initial;
	}

	/**
	 * Create a new CssWordSpacing with an expression
	 *
	 * @param expression The expression
	 * @throws org.w3c.css.util.InvalidParamException
	 *          The expression is incorrect
	 */
	public CssWordSpacing(ApplContext ac, CssExpression expression,
						  boolean check) throws InvalidParamException {

		if (check && expression.getCount() > 3) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		ArrayList<CssValue> v = new ArrayList<CssValue>(4);
		CssValue val;
		char op;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_NUMBER:
					val = val.getLength();
				case CssTypes.CSS_LENGTH:
					v.add(val);
					break;
				case CssTypes.CSS_PERCENTAGE:
					v.add(val);
					break;
				case CssTypes.CSS_IDENT:
					if (inherit.equals(val)) {
						// inherit can only be alone
						if (expression.getCount() > 1) {
							throw new InvalidParamException("value", expression.getValue(),
									getPropertyName(), ac);
						}
						value = inherit;
						break;
					} else if (normal.equals(val)) {
						v.add(normal);
						break;
					}
				default:
					throw new InvalidParamException("value", expression.getValue(),
							getPropertyName(), ac);
			}
			if (op != CssOperator.SPACE) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
			expression.next();
		}
		if (value != inherit) {
			value = (v.size() == 1) ? v.get(0) : new CssValueList(v);
		}
	}

	public CssWordSpacing(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}
