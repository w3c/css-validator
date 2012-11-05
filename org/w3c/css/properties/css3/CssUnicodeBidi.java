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

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-writing-modes-20120501/#unicode-bidi
 */
public class CssUnicodeBidi extends org.w3c.css.properties.css.CssUnicodeBidi {

	public static final CssIdent[] allowed_values_single;
	public static final CssIdent[] allowed_values_multi;

	static {
		String[] _allowed_values_single = {"normal", "embed"};
		String[] _allowed_values_multi = {"isolate", "bidi-override"};
		int i = 0;
		allowed_values_single = new CssIdent[_allowed_values_single.length];
		for (String s : _allowed_values_single) {
			allowed_values_single[i++] = CssIdent.getIdent(s);
		}
		i = 0;
		allowed_values_multi = new CssIdent[_allowed_values_multi.length];
		for (String s : _allowed_values_multi) {
			allowed_values_multi[i++] = CssIdent.getIdent(s);
		}
	}

	public static final CssIdent getAllowedSingleIdent(CssIdent ident) {
		for (CssIdent id : allowed_values_single) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	public static final CssIdent getAllowedIdentMulti(CssIdent ident) {
		for (CssIdent id : allowed_values_multi) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}


	public static final CssIdent getAllowedIdent(CssIdent ident) {
		CssIdent id = getAllowedSingleIdent(ident);
		if (id == null) {
			id = getAllowedIdentMulti(ident);
		}
		return id;
	}

	/**
	 * Create a new CssUnicodeBidi
	 */
	public CssUnicodeBidi() {
		value = initial;
	}

	/**
	 * Creates a new CssUnicodeBidi
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssUnicodeBidi(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 2) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		char op;

		val = expression.getValue();
		op = expression.getOperator();

		if (val.getType() != CssTypes.CSS_IDENT) {
			throw new InvalidParamException("value", val,
					getPropertyName(), ac);
		}
		CssIdent id = (CssIdent) val;
		if (inherit.equals(id)) {
			value = inherit;
			if (expression.getCount() > 1) {
				throw new InvalidParamException("value", val,
						getPropertyName(), ac);
			}
		} else {
			switch (expression.getCount()) {
				case 1:
					value = getAllowedSingleIdent(id);
					if (value == null) {
						throw new InvalidParamException("value", val,
								getPropertyName(), ac);
					}
					break;
				case 2:
					ArrayList<CssValue> vals = new ArrayList<CssValue>(4);
					CssValue v;
					v = getAllowedIdentMulti(id);
					if (v == null) {
						throw new InvalidParamException("value", val,
								getPropertyName(), ac);
					}
					vals.add(v);
					if (op != SPACE) {
						throw new InvalidParamException("operator",
								((new Character(op)).toString()), ac);
					}
					expression.next();
					val = expression.getValue();
					if (val.getType() != CssTypes.CSS_IDENT) {
						throw new InvalidParamException("value", val,
								getPropertyName(), ac);
					}
					v = getAllowedIdentMulti((CssIdent) val);
					if (v == null) {
						throw new InvalidParamException("value", val,
								getPropertyName(), ac);
					}
					vals.add(v);
					if (v == vals.get(0)) {
						// we can't have the same ident twice
						// and yes we can use == here
						// TODO fixme duplicate keyword error
						throw new InvalidParamException("value", val,
								getPropertyName(), ac);
					}
					value = new CssValueList(vals);
					break;
				default:
					throw new InvalidParamException("unrecognize", ac);
			}
		}
		expression.next();
	}

	public CssUnicodeBidi(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}
}

