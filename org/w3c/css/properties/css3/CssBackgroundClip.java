// $Id$
// @author Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 2010  World Wide Web Consortium (MIT, ERCIM, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
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
 * @spec http://www.w3.org/TR/2011/CR-css3-background-20110215/#the-background-clip
 */

public class CssBackgroundClip extends org.w3c.css.properties.css.CssBackgroundClip {

	public final static CssIdent border_box;
	public static CssIdent[] allowed_values;
	public final static String val[] = {"border-box", "padding-box", "content-box"};
	Object value;

	static {
		border_box = CssIdent.getIdent("border-box");
		allowed_values = new CssIdent[val.length];
		int i = 0;
		for (String s : val) {
			allowed_values[i++] = CssIdent.getIdent(s);
		}
	}

	public static boolean isMatchingIdent(CssIdent ident) {
		return (getMatchingIdent(ident) != null);

	}

	public static CssIdent getMatchingIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * Create a new CssBackgroundClip
	 */
	public CssBackgroundClip() {
		value = initial;
	}

	/**
	 * Create a new CssBackgroundClip
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Incorrect value
	 */
	public CssBackgroundClip(ApplContext ac, CssExpression expression,
							 boolean check) throws InvalidParamException {

		ArrayList<CssValue> values = new ArrayList<CssValue>();

		CssValue val;
		CssIdent matchingIdent;
		char op;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();
			switch (val.getType()) {
				case CssTypes.CSS_IDENT:
					if (inherit.equals(val)) {
						// if we got inherit after other values, fail
						// if we got more than one value... fail
						if ((values.size() > 0) || (expression.getCount() > 1)) {
							throw new InvalidParamException("value", val,
									getPropertyName(), ac);
						}
						values.add(inherit);
						break;
					}
					matchingIdent = getMatchingIdent((CssIdent) val);
					if (matchingIdent != null) {
						values.add(matchingIdent);
						break;
					}
				default:
					throw new InvalidParamException("value", val,
							getPropertyName(), ac);
			}
			expression.next();
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

	public CssBackgroundClip(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	public void set(Object val) {
		value = val;
	}

	/**
	 * Returns the value of this property
	 */
	public Object get() {
		return value;
	}

	/**
	 * Add this property to the CssStyle
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
		// TODO FIXME -> in CssStyle
		if (((Css3Style) style).cssBackgroundClip != null)
			style.addRedefinitionWarning(ac, this);
		((Css3Style) style).cssBackgroundClip = this;
	}

	/**
	 * Get this property in the style.
	 *
	 * @param style   The style where the property is
	 * @param resolve if true, resolve the style to find this property
	 */
	public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
		if (resolve) {
			return ((Css3Style) style).getCssBackgroundClip();
		} else {
			return ((Css3Style) style).cssBackgroundClip;
		}
	}

	/**
	 * Compares two properties for equality.
	 *
	 * @param property The other property.
	 */
	public boolean equals(CssProperty property) {
		return (property instanceof CssBackgroundClip &&
				value.equals(((CssBackgroundClip) property).value));
	}

	/**
	 * Returns a string representation of the object
	 */
	public String toString() {
		if (value instanceof ArrayList) {
			ArrayList values = (ArrayList) value;
			StringBuilder sb = new StringBuilder();
			for (Object aValue : values) {
				sb.append(aValue.toString()).append(", ");
			}
			sb.setLength(sb.length() - 2);
			return sb.toString();
		}
		return value.toString();
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
		return (border_box == value);
	}

}
