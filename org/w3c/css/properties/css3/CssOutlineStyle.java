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

/**
 * @spec http://www.w3.org/TR/2015/CR-css-ui-3-20150707/#propdef-outline-style
 * @see org.w3c.css.properties.css3.CssBorderStyle
 */
public class CssOutlineStyle extends org.w3c.css.properties.css.CssOutlineStyle {

	public static final CssIdent auto = CssIdent.getIdent("auto");
	public static final CssIdent hidden = CssIdent.getIdent("hidden");

	public static final CssIdent getMatchingIdent(CssIdent ident) {
		if (auto.equals(ident)) {
			return auto;
		}
		return CssBorderStyle.getMatchingIdent(ident);
	}

	/**
	 * Create a new CssOutlineStyle
	 */
	public CssOutlineStyle() {
		value = initial;
	}

	/**
	 * Creates a new CssOutlineStyle
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssOutlineStyle(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		setByUser();
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}

		CssValue val = expression.getValue();

		setByUser();

		// css3 adds an 'auto' value on top of border-style values
		value = null;
		if (val.getType() == CssTypes.CSS_IDENT) {
			CssIdent id_val = (CssIdent) val;
			if (inherit.equals(id_val)) {
				value = inherit;
			} else if (auto.equals(id_val)) {
				value = auto;
			}
		}
		// if we got a match, work on the expression, otherwise
		// delegate to border-style
		if (value != null) {
			expression.next();
		} else {
			// here we delegate to BorderStyle implementation
			value = CssBorderStyle.checkBorderSideStyle(ac, this, expression, check);
			// but hidden is not a valid value...
			if (hidden.equals(value)) {
				throw new InvalidParamException("value", hidden,
						getPropertyName(), ac);
			}
		}
	}

	public CssOutlineStyle(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

