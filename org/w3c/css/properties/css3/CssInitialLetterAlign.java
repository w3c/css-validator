//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang 2015.
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
 * @spec http://www.w3.org/TR/2016/WD-css-inline-3-20160524/#propdef-initial-letter
 */
public class CssInitialLetterAlign extends org.w3c.css.properties.css.CssInitialLetterAlign {

	CssIdent border_box = CssIdent.getIdent("border-box");

	public static final CssIdent[] allowed_values;

	static {
		String[] _allowed_values = {"alphabetic", "ideographic", "hebrew", "hanging"};
		int i = 0;
		allowed_values = new CssIdent[_allowed_values.length];
		for (String s : _allowed_values) {
			allowed_values[i++] = CssIdent.getIdent(s);
		}
	}

	public static final CssIdent getAllowedIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * Create a new CssInitialLetterAlign
	 */
	public CssInitialLetterAlign() {
		value = initial;
	}

	/**
	 * Set the value of the property
	 *
	 * @param expression The expression for this property
	 * @param check      set it to true to check the number of values
	 * @throws org.w3c.css.util.InvalidParamException
	 *          The expression is incorrect
	 */
	public CssInitialLetterAlign(ApplContext ac, CssExpression expression,
								 boolean check) throws InvalidParamException {

		if (check && expression.getCount() > 2) {
			throw new InvalidParamException("unrecognize", ac);
		}

		ArrayList<CssValue> v = new ArrayList<>(2);
		setByUser();
		char op;
		CssValue val;
		boolean got_border_box = false;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_IDENT:
					CssIdent id = (CssIdent) val;
					if (inherit.equals(id)) {
						if (expression.getCount() > 1) {
							throw new InvalidParamException("value", expression.getValue(),
									getPropertyName(), ac);
						}
						value = inherit;
						break;
					}
					// border_box can only be first.
					if (border_box.equals(id) && v.isEmpty()) {
						v.add(border_box);
						got_border_box = true;
						break;
					}
					id = getAllowedIdent(id);
					if (id != null) {
						// a match! We can't have two of them so...
						if (v.isEmpty() || got_border_box) {
							v.add(id);
							break;
						}
					}
				default:
					throw new InvalidParamException("value", val,
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

	public CssInitialLetterAlign(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}


}

