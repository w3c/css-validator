// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-text-20120814/#overflow-wrap0
 *
 * Note that word-wrap is also an alias for this.
 */
public class CssOverflowWrap extends org.w3c.css.properties.css.CssOverflowWrap {

	public static final CssIdent normal, break_word;

	static {
		normal = CssIdent.getIdent("normal");
		break_word = CssIdent.getIdent("break-word");
	}

	/**
	 * Create a new CssOverflowWrap
	 */
	public CssOverflowWrap() {
		value = initial;
	}

	/**
	 * Creates a new CssOverflowWrap
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssOverflowWrap(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		char op;

		val = expression.getValue();
		op = expression.getOperator();

		if (val.getType() == CssTypes.CSS_IDENT) {
			CssIdent ident = (CssIdent) val;
			if (inherit.equals(ident)) {
				value = inherit;
			} else if (normal.equals(ident)) {
				value = normal;
			} else if (break_word.equals(ident)) {
				value = break_word;
			} else {
				throw new InvalidParamException("value",
						val.toString(),
						getPropertyName(), ac);
			}
		} else {
			throw new InvalidParamException("value",
					val.toString(),
					getPropertyName(), ac);
		}
		expression.next();

	}

	public CssOverflowWrap(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

