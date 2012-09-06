// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2011/REC-css3-color-20110607/#opacity
 */

public class CssOpacity extends org.w3c.css.properties.css.CssOpacity {

	/**
	 * Create a new CssOpacity
	 */
	public CssOpacity() {
		value = initial;
	}

	/**
	 * Create a new CssOpacity
	 *
	 * @param expression The expression for this property
	 * @throws InvalidParamException Values are incorrect
	 */
	public CssOpacity(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		setByUser(); // tell this property is set by the user
		CssValue val = expression.getValue();

		switch (val.getType()) {
			case CssTypes.CSS_NUMBER:
				CssNumber number = val.getNumber();
				// this will generate a warning if necessary
				number.setFloatValue(clampedValue(ac, number.getValue()));
				value = number;
				break;
			case CssTypes.CSS_IDENT:
				if (inherit.equals(val)) {
					value = inherit;
					break;
				}
				// let it flow through the exception
			default:
				throw new InvalidParamException("value", val.toString(),
						getPropertyName(), ac);
		}
		expression.next();
	}

	public CssOpacity(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Brings all values back between 0 and 1
	 *
	 * @param opacity The value to be modified if necessary
	 */
	private float clampedValue(ApplContext ac, float opacity) {
		if (opacity < 0.f || opacity > 1.f) {
			ac.getFrame().addWarning("out-of-range", Util.displayFloat(opacity));
			return ((opacity < 0.f) ? 0.f : 1.f);
		}
		return opacity;
	}

	/**
	 * Is the value of this property a default value It is used by all macro for
	 * the function <code>print</code>
	 */
	public boolean isDefault() {
		if (value.getType() == CssTypes.CSS_NUMBER) {
			try {
				return (value.getNumber().getValue() == 1.f);
			} catch (Exception ex) {
				return false;
			}
		}
		return (value == initial);
	}

}
