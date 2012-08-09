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
 * @spec http://www.w3.org/TR/2011/WD-css3-fonts-20111004/#propdef-font-variant-ligatures
 */
public class CssFontVariantLigatures extends org.w3c.css.properties.css.CssFontVariantLigatures {

	public static final String[] commonLigValues = {"common-ligatures", "no-common-ligatures"};
	public static final String[] discretionaryLigValues = {"discretionary-ligatures",
			"no-discretionary-ligatures"};
	public static final String[] historicalLigValues = {"historical-ligatures",
			"no-historical-ligatures"};

	public static final CssIdent normal;

	static {
		normal = CssIdent.getIdent("normal");
	}

	public static final CssIdent getCommonLigValues(CssIdent ident) {
		String s_id = ident.toString();
		for (String s : commonLigValues) {
			if (s_id.equals(s)) {
				return ident;
			}
		}
		return null;
	}

	public static final CssIdent getDiscretionaryLigValues(CssIdent ident) {
		String s_id = ident.toString();
		for (String s : discretionaryLigValues) {
			if (s_id.equals(s)) {
				return ident;
			}
		}
		return null;
	}

	public static final CssIdent getHistoricalLigValues(CssIdent ident) {
		String s_id = ident.toString();
		for (String s : historicalLigValues) {
			if (s_id.equals(s)) {
				return ident;
			}
		}
		return null;
	}

	/**
	 * Create a new CssFontVariantLigatures
	 */
	public CssFontVariantLigatures() {
		value = initial;
	}

	/**
	 * Creates a new CssFontVariantLigatures
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFontVariantLigatures(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 3) {
			throw new InvalidParamException("unrecognize", ac);
		}

		setByUser();

		CssValue val;
		char op;

		CssIdent histValue = null;
		CssIdent commonValue = null;
		CssIdent discValue = null;
		boolean match;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();

			if (val.getType() == CssTypes.CSS_IDENT) {
				CssIdent ident = (CssIdent) val;
				if (inherit.equals(ident)) {
					if (expression.getCount() != 1) {
						throw new InvalidParamException("value",
								val.toString(),
								getPropertyName(), ac);
					}
					value = inherit;
				} else if (normal.equals(ident)) {
					if (expression.getCount() != 1) {
						throw new InvalidParamException("value",
								val.toString(),
								getPropertyName(), ac);
					}
					value = normal;
				} else {
					// no inherit, nor normal, test the up-to-three values
					match = false;
					if (commonValue == null) {
						commonValue = getCommonLigValues(ident);
						match = (commonValue != null);
					}
					if (!match && histValue == null) {
						histValue = getHistoricalLigValues(ident);
						match = (histValue != null);
					}
					if (!match && discValue == null) {
						discValue = getDiscretionaryLigValues(ident);
						match = (discValue != null);
					}
					if (!match) {
						throw new InvalidParamException("value",
								val.toString(),
								getPropertyName(), ac);
					}
				}
			} else {
				throw new InvalidParamException("value",
						val.toString(),
						getPropertyName(), ac);
			}
			if (op != CssOperator.SPACE) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
			expression.next();
		}
		// now set the right value
		if (expression.getCount() == 1) {
			// the last test is here in case value is already set
			// (normal or inherit)
			if (commonValue != null) {
				value = commonValue;
			} else if (histValue != null) {
				value = histValue;
			} else if (discValue != null) {
				value = discValue;
			}
		} else {
			// do this to keep the same order for comparisons
			ArrayList<CssValue> v = new ArrayList<CssValue>();
			if (commonValue != null) {
				v.add(commonValue);
			}
			if (histValue != null) {
				v.add(histValue);
			}
			if (discValue != null) {
				v.add(discValue);
			}
			value = new CssValueList(v);
		}

	}

	public CssFontVariantLigatures(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

