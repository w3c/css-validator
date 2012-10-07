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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2012/CR-css3-flexbox-20120918/#flex-basis-propdef
 * @see CssWidth
 */
public class CssFlexBasis extends org.w3c.css.properties.css.CssFlexBasis {

	public static final CssIdent auto = CssIdent.getIdent("auto");

	/**
	 * Create a new CssFlexBasis
	 */
	public CssFlexBasis() {
		value = initial;
	}

	/**
	 * Creates a new CssFlexBasis
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFlexBasis(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}

		/**
		 * WARNING - this is a replication of CSS3 Width parsing
		 * instead of creating a new object. This is because the exception
		 * would be tight to width's getPropertyName()
		 */

		CssValue val = expression.getValue();

		setByUser();

		switch (val.getType()) {
			case CssTypes.CSS_IDENT:
				CssIdent ident = (CssIdent) val;
				if (inherit.equals(val)) {
					value = inherit;
				} else if (auto.equals(val)) {
					value = auto;
				} else {
					throw new InvalidParamException("unrecognize", ac);
				}
				break;
			case CssTypes.CSS_NUMBER:
			case CssTypes.CSS_LENGTH:
				CssLength l = val.getLength();
				l.checkPositiveness(ac, this);
				value = l;
				break;
			case CssTypes.CSS_PERCENTAGE:
				CssPercentage p = val.getPercentage();
				p.checkPositiveness(ac, this);
				value = p;
				break;
			default:
				throw new InvalidParamException("value", val, getPropertyName(), ac);
		}
		expression.next();
	}

	public CssFlexBasis(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Is the value of this property is a default value.
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
		return ((value == auto) || (value == initial));
	}
}

