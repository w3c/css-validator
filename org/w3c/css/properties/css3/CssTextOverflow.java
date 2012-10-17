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
import org.w3c.css.values.CssLayerList;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-ui-20120117/#text-overflow0
 */
public class CssTextOverflow extends org.w3c.css.properties.css.CssTextOverflow {

	public static final CssIdent[] allowed_values;

	static {
		String[] _allowed_values = {"clip", "ellipsis"};
		allowed_values = new CssIdent[_allowed_values.length];
		int i = 0;
		for (String s : _allowed_values) {
			allowed_values[i++] = CssIdent.getIdent(s);
		}
	}

	public static CssIdent getAllowedIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * Create a new CssTextOverflow
	 */
	public CssTextOverflow() {
		value = initial;
	}

	/**
	 * Creates a new CssTextOverflow
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssTextOverflow(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {


		if (check && expression.getCount() > 2) {
			throw new InvalidParamException("unrecognize", ac);
		}

		setByUser();

		CssValue val;
		char op;
		ArrayList<CssValue> values = new ArrayList<CssValue>();

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();
			switch (val.getType()) {
				case CssTypes.CSS_STRING:
					values.add(val);
					break;
				case CssTypes.CSS_IDENT:
					if (inherit.equals(val)) {
						if (expression.getCount() > 1) {
							throw new InvalidParamException("value",
									inherit.toString(),
									getPropertyName(), ac);
						}
						values.add(inherit);
						break;
					}
					CssIdent match = getAllowedIdent((CssIdent) val);
					if (match != null) {
						values.add(match);
						break;
					}
					// unrecognized, let it fail
				default:
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
			}
			if (op != SPACE) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
			expression.next();

		}
		value = (values.size() == 1) ? values.get(0) : new CssLayerList(values);
	}

	public CssTextOverflow(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}
}

