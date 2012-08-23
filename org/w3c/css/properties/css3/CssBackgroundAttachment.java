// $Id$
// @author Yves Lafon <ylafon@w3.org>

// (c) COPYRIGHT MIT, ERCIM and Keio, 2010.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css3;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;

/**
 * @spec http://www.w3.org/TR/2009/CR-css3-background-20091217/#the-background-attachment
 */
public class CssBackgroundAttachment extends org.w3c.css.properties.css.CssBackgroundAttachment {

	private static CssIdent[] allowed_values;

	static {
		allowed_values = new CssIdent[3];
		allowed_values[0] = CssIdent.getIdent("scroll");
		allowed_values[1] = CssIdent.getIdent("fixed");
		allowed_values[2] = CssIdent.getIdent("local");
	}

	public static boolean isMatchingIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return true;
			}
		}
		return false;
	}

	public static CssIdent getMatchingIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	Object value;

	/**
	 * Create a new CssBackgroundAttachment
	 */
	public CssBackgroundAttachment() {
		value = initial;
	}

	public void set(Object val) {
		value = val;
	}

	/**
	 * Creates a new CssBackgroundAttachment
	 *
	 * @param ac         the context
	 * @param expression The expression for this property
	 * @param check      if some length checking is required
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Values are incorrect
	 */
	public CssBackgroundAttachment(ApplContext ac, CssExpression expression,
								   boolean check) throws InvalidParamException {

		ArrayList<CssValue> values = new ArrayList<CssValue>();
		char op;
		CssValue val;

		setByUser();

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();
			if (val.getType() != CssTypes.CSS_IDENT) {
				throw new InvalidParamException("value", val,
						getPropertyName(), ac);
			}
			if (inherit.equals(val)) {
				// if we got inherit after other values, fail
				// if we got more than one value... fail
				if ((values.size() > 0) || (expression.getCount() > 1)) {
					throw new InvalidParamException("value", val,
							getPropertyName(), ac);
				}
				values.add(inherit);
			} else {
				// check that it's in the allowed values
				CssValue new_val = getMatchingIdent((CssIdent) val);
				if (new_val == null) {
					throw new InvalidParamException("value", val,
							getPropertyName(), ac);
				}
				values.add(new_val);
			}
			expression.next();
			// and check that values are separated by commas
			if (!expression.end() && (op != COMMA)) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
		}

		if (values.size() == 1) {
			value = values.get(0);
		} else {
			value = values;
		}
	}

	public CssBackgroundAttachment(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Returns the value of this property
	 */
	public Object get() {
		return value;
	}

	/**
	 * Returns true if this property is "softly" inherited
	 * e.g. his value equals inherit
	 */
	public boolean isSoftlyInherited() {
		return (inherit == value);
	}

	/**
	 * Returns a string representation of the object.
	 */
	public String toString() {
		if (value instanceof ArrayList) {
			ArrayList values = (ArrayList) value;
			StringBuilder sb = new StringBuilder();
			boolean addComma = false;
			for (Object aValue : values) {
				if (addComma) {
					sb.append(", ");
				} else {
					addComma = true;
				}
				sb.append(aValue);
			}
			return sb.toString();
		}
		return value.toString();
	}

	/**
	 * Compares two properties for equality.
	 *
	 * @param property The other property.
	 */
	public boolean equals(CssProperty property) {
		return (property instanceof CssBackgroundAttachment &&
				value.equals(((CssBackgroundAttachment) property).value));
	}

	/**
	 * Is the value of this property is a default value.
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
		return (initial == value);
	}
}
