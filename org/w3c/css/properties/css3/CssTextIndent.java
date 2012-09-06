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
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-text-20120814/#text-indent0
 */
public class CssTextIndent extends org.w3c.css.properties.css.CssTextIndent {

	public static final CssIdent hanging, each_line;

	static {
		hanging = CssIdent.getIdent("hanging");
		each_line = CssIdent.getIdent("each-line");
	}

	/**
	 * Create a new CssTextIndent
	 */
	public CssTextIndent() {
		value = initial;
	}

	/**
	 * Creates a new CssTextIndent
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssTextIndent(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 3) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		char op;

		CssValue sizeVal = null;
		CssValue hangVal = null;
		CssValue eachVal = null;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();
			switch (val.getType()) {
				case CssTypes.CSS_NUMBER:
					val = val.getLength();
				case CssTypes.CSS_LENGTH:
				case CssTypes.CSS_PERCENTAGE:
					if (sizeVal != null) {
						throw new InvalidParamException("value",
								val.toString(), getPropertyName(), ac);
					}
					sizeVal = val;
					// we check that we are at the beginning or at the end
					// as it can't happen between two idents.
					if ((hangVal != null || eachVal != null) && expression.getRemainingCount() > 1) {
						// TODO specific error (can't happen between...)
						throw new InvalidParamException("value",
								val.toString(), getPropertyName(), ac);
					}
					break;
				case CssTypes.CSS_IDENT:
					CssIdent ident = (CssIdent) val;
					if (inherit.equals(ident)) {
						value = inherit;
						if (check && expression.getCount() > 1) {
							throw new InvalidParamException("unrecognize", ac);
						}
						break;
					}
					if (hangVal == null && hanging.equals(ident)) {
						hangVal = hanging;
						break;
					}
					if (eachVal == null && each_line.equals(ident)) {
						eachVal = each_line;
						break;
					}
				default:
					throw new InvalidParamException("value",
							expression.getValue().toString(),
							getPropertyName(), ac);
			}
			expression.next();
		}
		// sanity check, we must have a length or percentage
		if (value != inherit) {
			if (sizeVal == null) {
				throw new InvalidParamException("value",
						expression.toString(), getPropertyName(), ac);
			}
			if (expression.getCount() == 1) {
				value = sizeVal;
			} else {
				ArrayList<CssValue> v = new ArrayList<CssValue>(4);
				v.add(sizeVal);
				if (hangVal != null) {
					v.add(hangVal);
				}
				if (eachVal != null) {
					v.add(eachVal);
				}
				value = new CssValueList(v);
			}
		}
	}

	public CssTextIndent(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

