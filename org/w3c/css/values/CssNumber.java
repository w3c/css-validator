// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import java.math.BigDecimal;

/**
 * A CSS number.
 *
 * @version $Revision$
 */
public class CssNumber extends CssCheckableValue implements CssValueFloat {

	public static final int type = CssTypes.CSS_NUMBER;

	public final int getType() {
		return type;
	}

	ApplContext ac;
	BigDecimal value;
	boolean isInt = false;

	/**
	 * Create a new CssNumber
	 */
	public CssNumber() {
	}

	/**
	 * Create a new CssNumber
	 */
	public CssNumber(ApplContext ac, float value) {
		this.ac = ac;
		this.value = new BigDecimal(value);
	}

	public CssNumber(float value) {
		this.value = new BigDecimal(value);
	}

	/**
	 * Set the value of this frequency.
	 *
	 * @param s  the string representation of the frequency.
	 * @param ac For errors and warnings reports.
	 */
	public void set(String s, ApplContext ac) {
		value = new BigDecimal(s);
		try {
			value.toBigIntegerExact();
			isInt = true;
		} catch (ArithmeticException e) {
			isInt = false;
		}
		this.ac = ac;
	}

	/**
	 * Set the value explicitly
	 */
	public void setIntValue(int v) {
		isInt = true;
		value = new BigDecimal(v);

	}

	/**
	 * Set the value explicitly
	 */
	public void setFloatValue(float v) {
		isInt = false;
		value = new BigDecimal(v);
	}

	/**
	 * Returns the value
	 */
	public Object get() {
		return value;
	}

	/**
	 * Return the float value
	 */
	public float getValue() {
		return value.floatValue();
	}

	public int getInt() throws InvalidParamException {
		if (isInt) {
			try {
				return value.intValueExact();
			} catch (ArithmeticException aex) {
				throw new InvalidParamException("out-of-range", ac);
			}
		}
		// FIXME ???
		throw new InvalidParamException("invalid-color", ac);
	}

	public boolean isInteger() {
		return isInt;
	}

	/**
	 * Returns true is the value is positive of null
	 *
	 * @return a boolean
	 */
	public boolean isPositive() {
		return (value.signum() >= 0);
	}

	/**
	 * Returns true is the value is positive of null
	 *
	 * @return a boolean
	 */
	public boolean isStrictlyPositive() {
		return (value.signum() == 1);
	}

	/**
	 * Returns true is the value is zero
	 *
	 * @return a boolean
	 */
	public boolean isZero() {
		return BigDecimal.ZERO.equals(value);
	}

	/**
	 * Returns a length.
	 * Only zero can be a length.
	 *
	 * @throws InvalidParamException The value is not zero
	 */
	public CssLength getLength() throws InvalidParamException {
		if (value.equals(BigDecimal.ZERO)) {
			return new CssLength();
		}
		throw new InvalidParamException("zero", "length", ac);
	}

	/**
	 * Returns a percentage.
	 * Only zero can be a length.
	 *
	 * @throws InvalidParamException The value is not zero
	 */
	public CssPercentage getPercentage() throws InvalidParamException {
		if (value.equals(BigDecimal.ZERO)) {
			return new CssPercentage();
		}
		throw new InvalidParamException("zero",
				value.toString(),
				"percentage", ac);
	}

	/**
	 * Returns a time.
	 * Only zero can be a length.
	 *
	 * @throws InvalidParamException The value is not zero
	 */
	public CssTime getTime() throws InvalidParamException {
		if (value.equals(BigDecimal.ZERO)) {
			return new CssTime();
		}
		throw new InvalidParamException("zero", value.toString(),
				"time", ac);
	}

	/**
	 * Returns a angle.
	 * Only zero can be a length.
	 *
	 * @throws InvalidParamException The value is not zero
	 */
	public CssAngle getAngle() throws InvalidParamException {
		if (value.equals(BigDecimal.ZERO)) {
			return new CssAngle();
		}
		throw new InvalidParamException("zero", value.toString(),
				"angle", ac);
	}

	/**
	 * Returns a frequency.
	 * Only zero can be a length.
	 *
	 * @throws InvalidParamException The value is not zero
	 */
	public CssFrequency getFrequency() throws InvalidParamException {
		if (value.equals(BigDecimal.ZERO)) {
			return new CssFrequency();
		}
		throw new InvalidParamException("zero",
				value.toString(), "frequency", ac);
	}

	public CssNumber getNumber() throws InvalidParamException {
		return this;
	}

	/**
	 * Returns a string representation of the object.
	 */
	public String toString() {
		return value.toPlainString();
	}

	/**
	 * Compares two values for equality.
	 *
	 * @param value The other value.
	 */
	public boolean equals(Object value) {
		return (value instanceof CssNumber &&
				this.value.equals(((CssNumber) value).value));
	}

	/**
	 * check if the value is positive or null
	 * @param ac the validation context
	 * @param property the property the value is defined in
	 * @throws InvalidParamException
	 */
	public void checkPositiveness(ApplContext ac, CssProperty property)
			throws InvalidParamException {
		if (!isPositive()) {
			throw new InvalidParamException("negative-value",
					toString(), property.getPropertyName(), ac);
		}
	}

	/**
	 * check if the value is strictly positive
	 * @param ac the validation context
	 * @param property the property the value is defined in
	 * @throws InvalidParamException
	 */
	public void checkStrictPositiveness(ApplContext ac, CssProperty property)
			throws InvalidParamException {
		if (!isStrictlyPositive()) {
			throw new InvalidParamException("negative-value",
					toString(), property.getPropertyName(), ac);
		}
	}
}
