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
 * @spec http://www.w3.org/TR/2011/WD-css3-fonts-20111004/#propdef-font-variant-numeric
 */
public class CssFontVariantNumeric extends org.w3c.css.properties.css.CssFontVariantNumeric {

	public static final String[] numericFigValues = {"lining-nums", "oldstyle-nums"};
	public static final String[] numericSpaValues = {"proportional-nums", "tabular-nums"};
	public static final String[] numericFraValues = {"diagonal-fractions", "stacked-fractions"};

	public static final CssIdent normal, slashedZero;

	static {
		normal = CssIdent.getIdent("normal");
		slashedZero = CssIdent.getIdent("slashed-zero");
	}

	public static final CssIdent getNumericFigValues(CssIdent ident) {
		String s_id = ident.toString().toLowerCase();
		for (String s : numericFigValues) {
			if (s_id.equals(s)) {
				return ident;
			}
		}
		return null;
	}

	public static final CssIdent getNumericSpaValues(CssIdent ident) {
		String s_id = ident.toString().toLowerCase();
		for (String s : numericSpaValues) {
			if (s_id.equals(s)) {
				return ident;
			}
		}
		return null;
	}

	public static final CssIdent getNumericFraValues(CssIdent ident) {
		String s_id = ident.toString().toLowerCase();
		for (String s : numericFraValues) {
			if (s_id.equals(s)) {
				return ident;
			}
		}
		return null;
	}

	/**
	 * Create a new CssFontVariantNumeric
	 */
	public CssFontVariantNumeric() {
		value = initial;
	}

	/**
	 * Creates a new CssFontVariantNumeric
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFontVariantNumeric(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 4) {
			throw new InvalidParamException("unrecognize", ac);
		}

		setByUser();

		CssValue val;
		char op;

		CssIdent fraValue = null;
		CssIdent figValue = null;
		CssIdent spaValue = null;
		CssIdent zerValue = null;
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
					if (figValue == null) {
						figValue = getNumericFigValues(ident);
						match = (figValue != null);
					}
					if (!match && fraValue == null) {
						fraValue = getNumericFraValues(ident);
						match = (fraValue != null);
					}
					if (!match && spaValue == null) {
						spaValue = getNumericSpaValues(ident);
						match = (spaValue != null);
					}
					if (!match && zerValue == null) {
						match = slashedZero.equals(ident);
						if (match) {
							zerValue = slashedZero;
						}
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
			if (figValue != null) {
				value = figValue;
			} else if (fraValue != null) {
				value = fraValue;
			} else if (spaValue != null) {
				value = spaValue;
			} else if (zerValue != null) {
				value = zerValue;
			}
		} else {
			// do this to keep the same order for comparisons
			ArrayList<CssValue> v = new ArrayList<CssValue>();
			if (figValue != null) {
				v.add(figValue);
			}
			if (fraValue != null) {
				v.add(fraValue);
			}
			if (spaValue != null) {
				v.add(spaValue);
			}
			if (zerValue != null) {
				v.add(zerValue);
			}
			value = new CssValueList(v);
		}

	}

	public CssFontVariantNumeric(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

