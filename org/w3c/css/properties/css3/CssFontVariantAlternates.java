// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

/**
 * @spec http://www.w3.org/TR/2011/WD-css3-fonts-20111004/#propdef-font-variant-alternates
 */
public class CssFontVariantAlternates extends org.w3c.css.properties.css.CssFontVariantAlternates {

	public static final String[] contextualAltValues = {"contextual", "no-contextual"};

	public static final CssIdent normal;
	public static final CssIdent ruby;
	public static final CssIdent historicalForms;

	static {
		normal = CssIdent.getIdent("normal");
		ruby = CssIdent.getIdent("ruby");
		historicalForms = CssIdent.getIdent("historical-forms");
	}

	public static final CssIdent getContextualAltValues(CssIdent ident) {
		String s_id = ident.toString().toLowerCase();
		for (String s : contextualAltValues) {
			if (s_id.equals(s)) {
				return ident;
			}
		}
		return null;
	}

	/**
	 * Create a new CssFontVariantAlternates
	 */
	public CssFontVariantAlternates() {
		value = initial;
	}

	// here we just check that we got identifiers, comma separated for
	// the multipleValues case

	private void checkFuncExpression(ApplContext ac, CssExpression expression,
									 boolean multipleValues)
			throws InvalidParamException {
		CssValue val;
		char op;
		if (expression.getCount() == 0 || (!multipleValues && expression.getCount() > 1)) {
			throw new InvalidParamException("unrecognize", ac);
		}
		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();

			if (val.getType() != CssTypes.CSS_IDENT) {
				throw new InvalidParamException("value",
						val.toString(),
						getPropertyName(), ac);
			}
			expression.next();
			if (expression.getRemainingCount() > 0) {
				if (op != CssOperator.COMMA) {
					throw new InvalidParamException("operator",
							((new Character(op)).toString()), ac);
				}
			}
		}
	}

	/**
	 * Creates a new CssFontVariantAlternates
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFontVariantAlternates(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 9) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		char op;

		CssIdent contextualAltVal = null;
		CssFunction stylistic = null;
		CssIdent histValue = null;
		CssFunction styleSet = null;
		CssFunction charVariant = null;
		CssFunction swash = null;
		CssFunction ornaments = null;
		CssFunction annotation = null;
		CssIdent rubyVal = null;
		boolean match;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_IDENT:
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
						if (contextualAltVal == null) {
							contextualAltVal = getContextualAltValues(ident);
							value = contextualAltVal;
							match = (contextualAltVal != null);
						}
						if (!match && histValue == null) {
							if (historicalForms.equals(ident)) {
								histValue = historicalForms;
								value = histValue;
								match = true;
							}
						}
						if (!match && rubyVal == null) {
							if (ruby.equals(ident)) {
								rubyVal = ruby;
								value = rubyVal;
								match = true;
							}
						}
						if (!match) {
							throw new InvalidParamException("value",
									val.toString(),
									getPropertyName(), ac);
						}
					}
					break;
				case CssTypes.CSS_FUNCTION:
					match = false;
					CssFunction func = (CssFunction) val;
					String funcname = func.getName();
					if (stylistic == null) {
						if ("stylistic".equals(funcname)) {
							checkFuncExpression(ac, func.getParameters(), false);
							stylistic = func;
							value = stylistic;
							match = true;
						}
					}
					if (!match && styleSet == null) {
						if ("styleset".equals(funcname)) {
							checkFuncExpression(ac, func.getParameters(), true);
							styleSet = func;
							value = styleSet;
							match = true;
						}
					}
					if (!match && charVariant == null) {
						if ("character-variant".equals(funcname)) {
							checkFuncExpression(ac, func.getParameters(), true);
							charVariant = func;
							value = charVariant;
							match = true;
						}
					}
					if (!match && swash == null) {
						if ("swash".equals(funcname)) {
							checkFuncExpression(ac, func.getParameters(), false);
							swash = func;
							value = swash;
							match = true;
						}
					}
					if (!match && ornaments == null) {
						if ("ornaments".equals(funcname)) {
							checkFuncExpression(ac, func.getParameters(), false);
							ornaments = func;
							value = ornaments;
							match = true;
						}
					}
					if (!match && annotation == null) {
						if ("annotation".equals(funcname)) {
							checkFuncExpression(ac, func.getParameters(), false);
							annotation = func;
							value = annotation;
							match = true;
						}
					}
					if (match) {
						break;
					}
					// let if fail
				default:
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
		if (expression.getCount() > 1) {
			// do this to keep the same order for comparisons
			ArrayList<CssValue> v = new ArrayList<CssValue>();
			if (contextualAltVal != null) {
				v.add(contextualAltVal);
			}
			if (stylistic != null) {
				v.add(stylistic);
			}
			if (histValue != null) {
				v.add(histValue);
			}
			if (styleSet != null) {
				v.add(styleSet);
			}
			if (charVariant != null) {
				v.add(charVariant);
			}
			if (swash != null) {
				v.add(swash);
			}
			if (ornaments != null) {
				v.add(ornaments);
			}
			if (annotation != null) {
				v.add(annotation);
			}
			if (rubyVal != null) {
				v.add(rubyVal);
			}
			value = new CssValueList(v);
		}

	}

	public CssFontVariantAlternates(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

