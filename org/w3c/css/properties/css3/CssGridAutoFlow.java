//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2017.
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

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec https://www.w3.org/TR/2017/CR-css-grid-1-20170209/#propdef-grid-auto-flow
 */
public class CssGridAutoFlow extends org.w3c.css.properties.css.CssGridAutoFlow {

	public static final CssIdent[] allowed_values;
	public static final CssIdent dense = CssIdent.getIdent("dense");
	public static final CssIdent row;
	public static final CssIdent column;


	static {
		row = CssIdent.getIdent("row");
		column = CssIdent.getIdent("column");
		allowed_values = new CssIdent[]{row, column};
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
	 * Create a new CssGridAutoFlow
	 */
	public CssGridAutoFlow() {
		value = initial;
	}

	/**
	 * Creates a new CssGridAutoFlow
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssGridAutoFlow(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 2) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val, ident;
		char op;


		boolean got_axis = false;
		ArrayList<CssValue> values = new ArrayList<>();

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_IDENT:
					if (inherit.equals(val)) {
						if (expression.getCount() > 1) {
							throw new InvalidParamException("unrecognize", ac);
						}
						values.add(inherit);
						break;
					}
					ident = getAllowedIdent((CssIdent) val);
					if (ident != null) {
						if (got_axis) {
							throw new InvalidParamException("value",
									val.toString(),
									getPropertyName(), ac);
						} else {
							got_axis = true;
							values.add(ident);
							break;
						}
					}
					if (dense.equals(val)) {
						// did we get another 'dense' ?
						if (values.size() > 0 && !got_axis) {
							throw new InvalidParamException("value",
									val.toString(),
									getPropertyName(), ac);
						}
						values.add(dense);
						break;
					}
				default:
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
			}
			if (op != SPACE) {
				throw new InvalidParamException("operator", op,
						getPropertyName(), ac);
			}
			expression.next();
		}
		value = (values.size() == 1) ? values.get(0) : new CssValueList(values);
	}

	public CssGridAutoFlow(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

