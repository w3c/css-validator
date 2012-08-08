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
 * @spec http://www.w3.org/TR/2011/WD-css3-fonts-20111004/#propdef-font-synthesis
 */
public class CssFontSynthesis extends org.w3c.css.properties.css.CssFontSynthesis {

	public static final CssIdent style;
	public static final CssIdent weight;

	static {
		style = CssIdent.getIdent("style");
		weight = CssIdent.getIdent("weight");
	}

	/**
	 * Create a new CssFontSynthesis
	 */
	public CssFontSynthesis() {
		value = initial;
	}

	/**
	 * Creates a new CssFontSynthesis
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFontSynthesis(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {

		if (check && expression.getCount() > 2) {
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
			} else if (none.equals(ident)) {
				value = none;
			} else if (weight.equals(ident)) {
				if (expression.getCount() > 1) {
					if (op != CssOperator.SPACE) {
						throw new InvalidParamException("operator",
								((new Character(op)).toString()), ac);
					}
					expression.next();
					val = expression.getValue();
					if (!style.equals(val)) {
						throw new InvalidParamException("value",
								val.toString(),
								getPropertyName(), ac);
					}
					ArrayList<CssValue> v = new ArrayList<CssValue>(2);
					v.add(style);
					v.add(weight);
					value = new CssValueList(v);
				} else {
					value = weight;
				}
			} else if (style.equals(ident)) {
				if (expression.getCount() > 1) {
					if (op != CssOperator.SPACE) {
						throw new InvalidParamException("operator",
								((new Character(op)).toString()), ac);
					}
					expression.next();
					val = expression.getValue();
					if (!weight.equals(val)) {
						throw new InvalidParamException("value",
								val.toString(),
								getPropertyName(), ac);
					}
					ArrayList<CssValue> v = new ArrayList<CssValue>(2);
					v.add(style);
					v.add(weight);
					value = new CssValueList(v);
				} else {
					value = style;
				}
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

	public CssFontSynthesis(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

