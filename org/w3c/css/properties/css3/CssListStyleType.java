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

import java.util.Arrays;

/**
 * @spec http://www.w3.org/TR/2011/WD-css3-lists-20110524/#list-style-type
 */
public class CssListStyleType extends org.w3c.css.properties.css.CssListStyleType {

	public static final CssIdent[] allowed_values;

	static {
		String[] _allowed_values = {"none", "inline", "ethiopic-numeric",
				"triangle", "trinary", "go", "fixed-decimal", "unary",
				"box-corner", "dice", "box", "check", "circle", "diamond",
				"disc", "dash", "square", "arabic-indic", "bengali",
				"binary", "burmese", "cambodian", "cjk-decimal", "decimal",
				"devanagari", "eastern-nagari", "fullwidth-decimal",
				"gujarati", "gurmukhi", "kannada", "khmer",
				"lower-hexadecimal", "lao", "lepcha", "malayalam", "marathi",
				"mongolian", "myanmar", "new-base-60", "octal", "oriya",
				"persian", "super-decimal", "tamil", "telugu", "tibetan",
				"thai", "upper-hexadecimal", "afar", "agaw", "ari", "blin",
				"cjk-earthly-branch", "cjk-heavenly-stem", "dizi",
				"fullwidth-lower-alpha", "fullwidth-upper-alpha",
				"gedeo", "gumuz", "hadiyya", "harari", "hindi",
				"hiragana-iroha", "hiragana", "kaffa", "katakana-iroha",
				"katakana", "kebena", "kembata", "konso", "korean-consonant",
				"korean-syllable", "kunama", "lower-alpha",
				"lower-belorussian", "lower-bulgarian", "lower-greek",
				"lower-macedonian", "lower-oromo-qubee", "lower-russian",
				"lower-russian-full", "lower-serbo-croatian",
				"lower-ukrainian", "lower-ukrainian-full",
				"meen", "oromo", "saho", "sidama", "silti",
				"thai-alphabetic", "tigre", "upper-alpha", "upper-belorussian",
				"upper-bulgarian", "upper-macedonian", "upper-oromo-qubee",
				"upper-russian", "upper-russian-full", "upper-serbo-croatian",
				"upper-ukrainian", "upper-ukrainian-full", "wolaita",
				"yemsa", "asterisks", "footnotes", "lower-alpha-symbolic",
				"upper-alpha-symbolic", "circled-decimal",
				"circled-lower-latin", "circled-upper-latin", "circled-korean-consonants",
				"circled-korean-syllables", "decimal-leading-zero",
				"dotted-decimal", "double-circled-decimal",
				"filled-circled-decimal", "fullwidth-upper-roman",
				"fullwidth-lower-roman", "parenthesized-decimal",
				"parenthesized-lower-latin", "parenthesized-hangul-consonants",
				"parenthesized-hangul-syllable", "persian-abjad",
				"persian-alphabetic", "hebrew", "simple-upper-roman",
				"simple-lower-roman", "upper-roman", "lower-roman",
				"lower-armenian", "upper-armenian", "armenian", "georgian",
				"ancient-tamil", "japanese-informal", "japanese-formal",
				"korean-hangul-formal", "korean-hanja-informal",
				"korean-hanja-formal", "greek"};
		int i = 0;
		allowed_values = new CssIdent[_allowed_values.length];
		for (String s : _allowed_values) {
			allowed_values[i++] = CssIdent.getIdent(s);
		}
		Arrays.sort(allowed_values);
	}

	public static final CssIdent getAllowedIdent(CssIdent ident) {
		int idx = Arrays.binarySearch(allowed_values, ident);
		return (idx < 0) ? null : allowed_values[idx];
	}

	/**
	 * Create a new CssListStyleType
	 */
	public CssListStyleType() {
	}


	/**
	 * Set the value of the property<br/>
	 * Does not check the number of values
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          The expression is incorrect
	 */
	public CssListStyleType(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Set the value of the property
	 *
	 * @param expression The expression for this property
	 * @param check      set it to true to check the number of values
	 * @throws org.w3c.css.util.InvalidParamException
	 *          The expression is incorrect
	 */
	public CssListStyleType(ApplContext ac, CssExpression expression,
							boolean check) throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		char op;

		val = expression.getValue();
		op = expression.getOperator();

		switch (val.getType()) {
			case CssTypes.CSS_STRING:
				value = val;
				break;
			case CssTypes.CSS_IDENT:
				CssIdent id = (CssIdent) val;
				if (inherit.equals(id)) {
					value = inherit;
					break;
				} else {
					value = getAllowedIdent(id);
					if (value == null) {
						// it's still acceptable
						// but the name should be listed in a
						// @counter-style rule
						value = val;
						// TODO check counter-style
						break;
					}
				}
			default:
				throw new InvalidParamException("value",
						val.toString(),
						getPropertyName(), ac);
		}
		expression.next();
	}

}
