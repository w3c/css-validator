// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten 2010 by Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 1995-2010  World Wide Web Consortium (MIT, ERCIM and Keio)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import static org.w3c.css.properties.css3.CssBreakBefore.auto;
import static org.w3c.css.properties.css3.CssBreakBefore.getMatchingIdent;


/**
 * @spec https://www.w3.org/TR/2017/CR-css-break-3-20170209/#propdef-break-after
 */

public class CssBreakAfter extends org.w3c.css.properties.css.CssBreakAfter {

	/**
	 * Create a new CssPageBreakAfter
	 */
	public CssBreakAfter() {
		value = initial;
	}

	/**
	 * Create a new CssPageBreakAfter
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Incorrect value
	 */
	public CssBreakAfter(ApplContext ac, CssExpression expression,
						 boolean check) throws InvalidParamException {

		setByUser();
		CssValue val = expression.getValue();

		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}

		if (val.getType() != CssTypes.CSS_IDENT) {
			throw new InvalidParamException("value",
					expression.getValue(),
					getPropertyName(), ac);
		}
		// ident, so inherit, or allowed value
		if (inherit.equals(val)) {
			value = inherit;
		} else {
			val = getMatchingIdent((CssIdent) val);
			if (val == null) {
				throw new InvalidParamException("value",
						expression.getValue(),
						getPropertyName(), ac);
			}
			value = val;
		}
		expression.next();
	}

	public CssBreakAfter(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
		return (auto == value);
	}

}
