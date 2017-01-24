//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2016.
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

import static org.w3c.css.properties.css3.CssGridTemplateRows.parseAutoTrackList;
import static org.w3c.css.properties.css3.CssGridTemplateRows.parseTrackList;

/**
 * @spec https://www.w3.org/TR/2016/CR-css-grid-1-20160929/#propdef-grid-template-columns
 */
public class CssGridTemplateColumns extends org.w3c.css.properties.css.CssGridTemplateColumns {

	/**
	 * Create a new CssGridTemplateColumns
	 */
	public CssGridTemplateColumns() {
		value = initial;
	}

	/**
	 * Creates a new CssGridTemplateColumns
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssGridTemplateColumns(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		CssValue val;
		char op;

		ArrayList<CssValue> values = new ArrayList<>();
		CssIdent ident;

		val = expression.getValue();
		op = expression.getOperator();

		switch (val.getType()) {
			case CssTypes.CSS_IDENT:
				if (inherit.equals(val)) {
					if (expression.getCount() > 1) {
						throw new InvalidParamException("unrecognize", ac);
					}
					values.add(inherit);
					expression.next();
					break;
				}
				if (none.equals(val)) {
					if (expression.getCount() > 1) {
						throw new InvalidParamException("unrecognize", ac);
					}
					values.add(none);
					expression.next();
					break;
				}
			default:
				expression.mark();
				try {
					values.add(parseTrackList(ac, expression, this));
				} catch (InvalidParamException ex) {
					// perhaps an AutoTrackList?
					expression.reset();
					values.add(parseAutoTrackList(ac, expression, this));
				}
		}
		value = (values.size() == 1) ? values.get(0) : new CssValueList(values);

	}

	public CssGridTemplateColumns(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

