// $Id$
// @author Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.values;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import java.math.BigDecimal;

public abstract class CssCheckableValue extends CssValue {

	abstract public boolean isPositive();

	abstract public void checkPositiveness(ApplContext ac, CssProperty property)
			throws InvalidParamException;

	abstract public boolean isStrictlyPositive();

	abstract public void checkStrictPositiveness(ApplContext ac, CssProperty property)
			throws InvalidParamException;

	abstract public void warnPositiveness(ApplContext ac, CssProperty property);

	public boolean isInteger() {
		return false;
	}

	/**
	 * check if the value is an integer
	 *
	 * @param ac       the validation context
	 * @param property the property the value is defined in
	 * @throws InvalidParamException
	 */
	public void checkInteger(ApplContext ac, CssProperty property)
			throws InvalidParamException {
	}


	/**
	 * set the native value
	 *
	 * @param v the BigDecimal
	 */
	public abstract void setValue(BigDecimal v);

	/**
	 * Get this value as acheckable value
	 *
	 * @return
	 */
	public CssCheckableValue getCheckableValue() {
		return this;
	}
}
