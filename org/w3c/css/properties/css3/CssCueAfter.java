// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2013.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec http://www.w3.org/TR/2012/CR-css3-speech-20120320/#cue-after
 */
public class CssCueAfter extends org.w3c.css.properties.css.CssCueAfter {

	/**
	 * Create a new CssCueAfter
	 */
	public CssCueAfter() {
		value = initial;
	}

	/**
	 * Creates a new CssCueAfter
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssCueAfter(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 2) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		char op;

		val = expression.getValue();
		op = expression.getOperator();

		switch (val.getType()) {
			case CssTypes.CSS_URL:
				value = val;
				// now let's check for a volume...
				if (expression.getRemainingCount() > 1) {
					CssValue vnext = expression.getNextValue();
					if (vnext.getType() == CssTypes.CSS_VOLUME) {
						// we got a volume, so let's do extra checks, then
						// construct the value...
						if (op != SPACE) {
							throw new InvalidParamException("operator",
									((new Character(op)).toString()), ac);
						}
						expression.next();
						ArrayList<CssValue> values = new ArrayList<CssValue>(2);
						values.add(val);
						values.add(vnext);
						value = new CssValueList(values);
					} else if (check) {
						throw new InvalidParamException("value",
								vnext, getPropertyName(), ac);
					}
				}
				break;
			case CssTypes.CSS_IDENT:
				if (inherit.equals(val)) {
					if (expression.getCount() > 1) {
						throw new InvalidParamException("value",
								inherit, getPropertyName(), ac);
					}
					value = inherit;
					break;
				}
				if (none.equals(val)) {
					if (check && expression.getCount() > 1) {
						throw new InvalidParamException("value",
								inherit, getPropertyName(), ac);
					}
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

	public CssCueAfter(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}
}

