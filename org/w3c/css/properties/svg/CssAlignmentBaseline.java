// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2015.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.svg;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.CssVersion;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2011/REC-SVG11-20110816/text.html#AlignmentBaselineProperty
 */
public class CssAlignmentBaseline extends org.w3c.css.properties.css.CssAlignmentBaseline {

	public static final CssIdent[] svg_allowed_values;
	public static final CssIdent[] css3_allowed_values;

	static {
		String[] _allowed_values = {"auto", "baseline", "before-edge", "text-before-edge",
				"middle", "central", "after-edge", "text-after-edge", "ideographic",
				"alphabetic", "hanging", "mathematical"};
		int i = 0;
		svg_allowed_values = new CssIdent[_allowed_values.length];
		for (String s : _allowed_values) {
			svg_allowed_values[i++] = CssIdent.getIdent(s);
		}

		String[] _allowed_css3_values = {"baseline", "text-bottom", "alphabetic", "middle",
				"central", "mathematical", "text-top", "bottom", "center", "top"};
		i = 0;
		css3_allowed_values = new CssIdent[_allowed_css3_values.length];
		boolean add_it;
		for (String s : _allowed_css3_values) {
			// get only the ones not defined by SVG
			add_it = false;
			for (String other : _allowed_values) {
				add_it = other.equals(s);
				if (add_it)
					break;
			}
			if (add_it) {
				css3_allowed_values[i++] = CssIdent.getIdent(s);
			}
		}
	}

	public static final CssIdent getAllowedIdent(CssIdent ident) {
		for (CssIdent id : svg_allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	public static final CssIdent getAllowedCSS3Ident(CssIdent ident) {
		for (CssIdent id : css3_allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * Create a new CssAlignmentBaseline
	 */
	public CssAlignmentBaseline() {
		value = initial;
	}

	/**
	 * Creates a new CssAlignmentBaseline
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssAlignmentBaseline(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;

		val = expression.getValue();

		if (val.getType() == CssTypes.CSS_IDENT) {
			CssIdent id = (CssIdent) val;
			if (inherit.equals(id)) {
				value = inherit;
			} else {
				value = getAllowedIdent(id);
				if (value == null) {
					// we also check if CSS version is CSS3 and onward.
					if (ac.getCssVersion().compareTo(CssVersion.CSS3) >= 0) {
						value = getAllowedCSS3Ident(id);
					}
				}
				if (value == null) {
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
		expression.next();
	}

	public CssAlignmentBaseline(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

