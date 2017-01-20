//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2017.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec https://www.w3.org/TR/2016/CR-css-grid-1-20160929/#propdef-grid-auto-columns
 */
public class CssGridAutoColumns extends org.w3c.css.properties.css.CssGridAutoColumns {

	/**
	 * Create a new CssGridAutoColumns
	 */
	public CssGridAutoColumns() {
		value = initial;
	}

	/**
	 * Creates a new CssGridAutoColumns
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssGridAutoColumns(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		setByUser();

		CssValue val;
		char op;

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
				case CssTypes.CSS_NUMBER:
				case CssTypes.CSS_LENGTH:
				case CssTypes.CSS_PERCENTAGE:
				case CssTypes.CSS_FLEX:
					values.add(CssGridAutoRows.parseTrackBreadth(ac, val, this));
					break;
				case CssTypes.CSS_FUNCTION:
					CssFunction function = (CssFunction) val;
					String fname = function.getName().toLowerCase();
					if (CssGridAutoRows.minmax.equals(fname)) {
						values.add(CssGridAutoRows.parseMinmaxFunction(ac, function,
								CssGridAutoRows.ArgType.INFLEXIBLE_BREADTH,
								CssGridAutoRows.ArgType.TRACK_BREADTH, this));
						break;
					} else if (CssGridAutoRows.fit_content.equals(fname)) {
						values.add(CssGridAutoRows.parseFitContent(ac, function, this));
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

	public CssGridAutoColumns(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

