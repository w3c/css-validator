//
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewriten 2010 Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 1995-2010  World Wide Web Consortium (MIT, ERCIM, Keio, Beihang)
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssCheckableValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2011/CR-css3-multicol-20110412/#column-width
 * @spec https://www.w3.org/TR/2016/WD-css-sizing-3-20160512/#column-sizing
 */

public class CssColumnWidth extends org.w3c.css.properties.css.CssColumnWidth {

	public static final CssIdent[] allowed_values;

	static {
		// fill to fit-content from css-sizing
		String[] _allowed_values = {"auto", "fill", "max-content", "min-content", "fit-content"};

		allowed_values = new CssIdent[_allowed_values.length];
		int i = 0;
		for (String s : _allowed_values) {
			allowed_values[i++] = CssIdent.getIdent(s);
		}
	}

	public static CssIdent getAllowedIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * Create a new CssColumnWidth
	 */
	public CssColumnWidth() {
		value = initial;
	}

	/**
	 * Create a new CssColumnWidth
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Incorrect value
	 */
	public CssColumnWidth(ApplContext ac, CssExpression expression,
						  boolean check) throws InvalidParamException {

		setByUser();
		CssValue val = expression.getValue();

		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}

		switch (val.getType()) {
			case CssTypes.CSS_NUMBER:
				val.getCheckableValue().checkEqualsZero(ac, this);
				// if we didn't fall in the first trap, there is another one :)
				throw new InvalidParamException("strictly-positive",
						expression.getValue(),
						getPropertyName(), ac);
			case CssTypes.CSS_LENGTH:
				CssCheckableValue l = val.getCheckableValue();
				l.checkStrictPositiveness(ac, this);
				value = val;
				break;
			case CssTypes.CSS_IDENT:
				if (inherit.equals(val)) {
					value = inherit;
				} else {
					CssIdent id = getAllowedIdent((CssIdent) val);
					if (id != null) {
						value = id;
					} else {
						throw new InvalidParamException("unrecognize", ac);
					}
				}
				break;

			default:
				throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
		}
		expression.next();
	}

	public CssColumnWidth(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */

	public boolean isDefault() {
		return (value == initial);
	}

}
