//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2016.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec https://www.w3.org/TR/2016/CR-css-grid-1-20160929/#propdef-grid-gap
 */
public class CssGridGap extends org.w3c.css.properties.css.CssGridGap {

	private CssGridColumnGap column;
	private CssGridRowGap row;

	/**
	 * Create a new CssGridGap
	 */
	public CssGridGap() {
		value = initial;
		column = new CssGridColumnGap();
		row = new CssGridRowGap();
	}

	/**
	 * Creates a new CssGridGap
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssGridGap(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 2) {
			throw new InvalidParamException("unrecognize", ac);
		}

		CssValue val;
		// get the separator now in case we need it.
		char op = expression.getOperator();
		// create the values we will fill
		column = new CssGridColumnGap();
		row = new CssGridRowGap();

		val = CssGridRowGap.checkSyntax(ac, expression, false, this);
		row.value = val;
		if (!expression.end()) {
			if (op != SPACE) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
			val = CssGridRowGap.checkSyntax(ac, expression, false, this);
			column.value = val;
			ArrayList<CssValue> v = new ArrayList<>(2);
			v.add(row.value);
			v.add(column.value);
			value = new CssValueList(v);
		} else {
			value = val;
			row.value = val;
		}
	}

	public CssGridGap(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Add this property to the CssStyle.
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
		super.addToStyle(ac, style);
		column.addToStyle(ac, style);
		row.addToStyle(ac, style);
	}
}

