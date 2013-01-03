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
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

/**
 * @spec http://www.w3.org/TR/2013/WD-css-text-decor-3-20130103/#text-underline-position
 */
public class CssTextUnderlinePosition extends org.w3c.css.properties.css.CssTextUnderlinePosition {

	public static final CssIdent auto, alphabetic, below;
	public static final CssIdent[] horizontalValues;

	static {
		String[] _horizontalValues = {"left", "right"};
		horizontalValues = new CssIdent[_horizontalValues.length];
		int i = 0;
		for (String s : _horizontalValues) {
			horizontalValues[i++] = CssIdent.getIdent(s);
		}
		alphabetic = CssIdent.getIdent("alphabetic");
		below = CssIdent.getIdent("under");
		auto = CssIdent.getIdent("auto");
	}

	public static CssIdent getHorizontalValue(CssIdent ident) {
		for (CssIdent id : horizontalValues) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	public static final CssIdent getAllowedValue(CssIdent ident) {
		if (auto.equals(ident)) {
			return auto;
		}
		if (alphabetic.equals(ident)) {
			return alphabetic;
		}
		if (below.equals(ident)) {
			return below;
		}
		return getHorizontalValue(ident);
	}

	/**
	 * Create a new CssTextUnderlinePosition
	 */
	public CssTextUnderlinePosition() {
		value = initial;
	}

	/**
	 * Creates a new CssTextUnderlinePosition
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssTextUnderlinePosition(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 4) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		char op;

		CssIdent horValue = null;
		CssIdent verValue = null;

		val = expression.getValue();
		op = expression.getOperator();

		if (val.getType() != CssTypes.CSS_IDENT) {
			throw new InvalidParamException("value",
					val.toString(),
					getPropertyName(), ac);
		}

		CssIdent ident = (CssIdent) val;
		if (inherit.equals(ident)) {
			value = inherit;
			if (check && expression.getCount() != 1) {
				throw new InvalidParamException("value",
						val.toString(),
						getPropertyName(), ac);
			}
		} else if (auto.equals(ident)) {
			value = auto;
			if (check && expression.getCount() != 1) {
				throw new InvalidParamException("value",
						val.toString(),
						getPropertyName(), ac);
			}
		} else if (alphabetic.equals(ident)) {
			value = alphabetic;
			if (check && expression.getCount() != 1) {
				throw new InvalidParamException("value",
						val.toString(),
						getPropertyName(), ac);
			}
		} else {
			int nbgot = 0;
			do {
				boolean match = false;
				if (verValue == null && below.equals(ident)) {
					verValue = below;
					match = true;
				} else if (horValue == null) {
					horValue = getHorizontalValue(ident);
					match = (horValue != null);
				}
				if (!match) {
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
				}
				nbgot++;
				if (expression.getRemainingCount() == 1 || (!check && nbgot == 2)) {
					// if we have both, exit
					// (needed only if check == false...
					break;
				}
				if (op != CssOperator.SPACE) {
					throw new InvalidParamException("operator",
							((new Character(op)).toString()), ac);
				}
				expression.next();
				val = expression.getValue();
				op = expression.getOperator();
				if (val.getType() != CssTypes.CSS_IDENT) {
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
				}
				ident = (CssIdent) val;
			} while (!expression.end());
			// now construct the value
			ArrayList<CssValue> v = new ArrayList<CssValue>(nbgot);
			if (horValue != null) {
				v.add(horValue);
			}
			if (verValue != null) {
				v.add(verValue);
			}
			value = (nbgot > 1) ? new CssValueList(v) : v.get(0);
		}
		expression.next();

	}

	public CssTextUnderlinePosition(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}
}

