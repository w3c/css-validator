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
import java.util.Arrays;

/**
 * @spec http://www.w3.org/TR/2011/WD-css3-fonts-20111004/#propdef-font-variant-east-asian
 */
public class CssFontVariantEastAsian extends org.w3c.css.properties.css.CssFontVariantEastAsian {

	public static final String[] _eastAsianVariantValues = {"jis78", "jis83", "jis90", "jis04",
			"simplified", "traditional"};
	public static final String[] _eastAsianWidthValues = {"full-width", "proportional-width"};

	public static final CssIdent[] eastAsianVariantValues;
	public static final CssIdent[] eastAsianWidthValues;

	public static final CssIdent normal;

	static {
		normal = CssIdent.getIdent("normal");

		eastAsianVariantValues = new CssIdent[_eastAsianVariantValues.length];
		for (int i = 0; i < eastAsianVariantValues.length; i++) {
			eastAsianVariantValues[i] = CssIdent.getIdent(_eastAsianVariantValues[i]);
		}
		Arrays.sort(eastAsianVariantValues);
		eastAsianWidthValues = new CssIdent[_eastAsianWidthValues.length];
		for (int i = 0; i < eastAsianWidthValues.length; i++) {
			eastAsianWidthValues[i] = CssIdent.getIdent(_eastAsianWidthValues[i]);
		}
		Arrays.sort(eastAsianWidthValues);
	}

	public static final CssIdent getEastAsianVariantValue(CssIdent ident) {
		int idx = Arrays.binarySearch(eastAsianVariantValues, ident);
		if (idx >= 0) {
			return eastAsianVariantValues[idx];
		}
		return null;
	}

	public static final CssIdent getEastAsianWidthValue(CssIdent ident) {
		for (CssIdent id : eastAsianWidthValues) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	public static final CssIdent getAllowedValue(CssIdent ident) {
		CssIdent id = getEastAsianWidthValue(ident);
		if (id == null) {
			id = getEastAsianVariantValue(ident);
		}
		return id;
	}

	/**
	 * Create a new CssFontVariantEastAsian
	 */
	public CssFontVariantEastAsian() {
		value = initial;
	}

	/**
	 * Creates a new CssFontVariantEastAsian
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFontVariantEastAsian(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 2) {
			throw new InvalidParamException("unrecognize", ac);
		}

		setByUser();

		CssValue val;
		char op;

		val = expression.getValue();
		op = expression.getOperator();

		value = null;

		if (val.getType() == CssTypes.CSS_IDENT) {
			CssIdent ident = (CssIdent) val;
			if (inherit.equals(ident)) {
				value = inherit;
			} else if (normal.equals(ident)) {
				value = normal;
			} else {
				value = getEastAsianVariantValue(ident);
				if (value != null) {
					if (expression.getCount() > 1) {
						if (op != CssOperator.SPACE) {
							throw new InvalidParamException("operator",
									((new Character(op)).toString()), ac);
						}
						expression.next();
						val = expression.getValue();
						CssIdent value2 = null;
						if (val.getType() == CssTypes.CSS_IDENT) {
							value2 = getEastAsianWidthValue((CssIdent) val);
						}
						if (value2 == null) {
							throw new InvalidParamException("value",
									val.toString(),
									getPropertyName(), ac);
						}
						ArrayList<CssValue> v = new ArrayList<CssValue>(2);
						v.add(value);
						v.add(value2);
						value = new CssValueList(v);
					}
				} else {
					value = getEastAsianWidthValue(ident);
					if (value != null) {
						if (expression.getCount() > 1) {
							if (op != CssOperator.SPACE) {
								throw new InvalidParamException("operator",
										((new Character(op)).toString()), ac);
							}
							expression.next();
							val = expression.getValue();
							CssIdent value2 = null;
							if (val.getType() == CssTypes.CSS_IDENT) {
								value2 = getEastAsianVariantValue((CssIdent) val);
							}
							if (value2 == null) {
								throw new InvalidParamException("value",
										val.toString(),
										getPropertyName(), ac);
							}
							ArrayList<CssValue> v = new ArrayList<CssValue>(2);
							v.add(value2);
							v.add(value);
							value = new CssValueList(v);
						}
					} else {
						throw new InvalidParamException("value",
								val.toString(),
								getPropertyName(), ac);
					}
				}
			}
		} else {
			throw new InvalidParamException("value",
					val.toString(),
					getPropertyName(), ac);
		}
		expression.next();
	}

	public CssFontVariantEastAsian(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

