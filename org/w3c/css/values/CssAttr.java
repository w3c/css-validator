//
// @author Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2016.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.values;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import java.math.BigDecimal;

/**
 * A CSS Attr.
 *
 * @spec https://www.w3.org/TR/2015/CR-css-values-3-20150611/#attr-notation
 */
public class CssAttr extends CssCheckableValue {

	public static final int type = CssTypes.CSS_ATTR;

	public final int getRawType() {
		return type;
	}

	public final int getType() {
		if (computed_type == CssTypes.CSS_UNKNOWN) {
			return type;
		}
		return computed_type;
	}

	ApplContext ac;
	int computed_type = CssTypes.CSS_UNKNOWN;
	CssValue value = null;
	CssValue value_type = null;
	CssValue default_value = null;
	String _ts = null;


	/**
	 * Create a new CssAttr
	 */
	public CssAttr() {
	}

	public void set(String s, ApplContext ac) throws InvalidParamException {
		// we don't support this way of setting the value
		// as we rely on the parsing a CssExpression
		throw new InvalidParamException("unrecognize", s, ac);
	}

	public void setValue(BigDecimal d) {
		// we don't support this way of setting the value
		// as we rely on the parsing a CssExpression
	}


	/**
	 * Returns the value
	 */

	public Object get() {
		return toString();
	}


	public String toString() {
		if (_ts == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("attr(").append(value);
			if (value_type != null) {
				sb.append(' ').append(value_type);
			}
			if (default_value != null) {
				sb.append(" , ").append(default_value);
			}
			sb.append(')');
			_ts = sb.toString();
		}
		return _ts;
	}


	public boolean isInteger() {
		return false;
	}

	/**
	 * Returns true is the value is positive of null
	 *
	 * @return a boolean
	 */
	public boolean isPositive() {
		// TODO do our best...
		return false;
	}

	/**
	 * Returns true is the value is positive of null
	 *
	 * @return a boolean
	 */
	public boolean isStrictlyPositive() {
		return false;
		// TODO do our best...
	}

	/**
	 * Returns true is the value is zero
	 *
	 * @return a boolean
	 */
	public boolean isZero() {
		// TODO do our best...
		return false;
	}


	/**
	 * Compares two values for equality.
	 *
	 * @param value The other value.
	 */
	public boolean equals(Object value) {
		return (value instanceof CssAttr &&
				toString().equals(((CssAttr)value).toString()));
	}

	/**
	 * check if the value is positive or null
	 *
	 * @param ac       the validation context
	 * @param property the property the value is defined in
	 * @throws org.w3c.css.util.InvalidParamException
	 *
	 */
	public void checkPositiveness(ApplContext ac, CssProperty property)
			throws InvalidParamException {
		// TODO do our best...
		if (false /*!isPositive()*/) {
			throw new InvalidParamException("negative-value",
					toString(), property.getPropertyName(), ac);
		}
	}

	/**
	 * check if the value is strictly positive
	 *
	 * @param ac       the validation context
	 * @param property the property the value is defined in
	 * @throws org.w3c.css.util.InvalidParamException
	 *
	 */
	public void checkStrictPositiveness(ApplContext ac, CssProperty property)
			throws InvalidParamException {
		// TODO do our best...
		if (false/*!isStrictlyPositive()*/) {
			throw new InvalidParamException("strictly-positive",
					toString(), property.getPropertyName(), ac);
		}
	}

	/**
	 * check if the value is an integer
	 *
	 * @param ac       the validation context
	 * @param property the property the value is defined in
	 * @throws org.w3c.css.util.InvalidParamException
	 *
	 */
	public void checkInteger(ApplContext ac, CssProperty property)
			throws InvalidParamException {
		// TODO do our best...
		if (false/*!isInteger()*/) {
			throw new InvalidParamException("integer",
					toString(), property.getPropertyName(), ac);
		}
	}

	/**
	 * warn if the value is not positive or null
	 *
	 * @param ac       the validation context
	 * @param property the property the value is defined in
	 */
	public void warnPositiveness(ApplContext ac, CssProperty property) {
		// TODO do our best...
		if (false/*!isPositive()*/) {
			ac.getFrame().addWarning("negative", toString());
		}
	}

	public CssLength getLength() throws InvalidParamException {
		if (computed_type == CssTypes.CSS_LENGTH) {
			// TODO fixme...
			// we might change this to CssCheckableValue instead.
		}
		throw new ClassCastException("unknown");
	}

	public CssPercentage getPercentage() throws InvalidParamException {
		if (computed_type == CssTypes.CSS_PERCENTAGE) {
			// TODO
		}
		throw new ClassCastException("unknown");
	}

	public CssNumber getNumber() throws InvalidParamException {
		if (computed_type == CssTypes.CSS_NUMBER) {
			//TODO
		}
		throw new ClassCastException("unknown");
	}

	public CssTime getTime() throws InvalidParamException {
		if (computed_type == CssTypes.CSS_TIME) {
			//TODO
		}
		throw new ClassCastException("unknown");
	}

	public CssAngle getAngle() throws InvalidParamException {
		if (computed_type == CssTypes.CSS_ANGLE) {
			//TODO
		}
		throw new ClassCastException("unknown");
	}

	public CssFrequency getFrequency() throws InvalidParamException {
		if (computed_type == CssTypes.CSS_FREQUENCY) {
			//TODO
		}
		throw new ClassCastException("unknown");
	}
}
