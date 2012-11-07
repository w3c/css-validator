// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.CssVersion;
import org.w3c.css.util.InvalidParamException;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;

/**
 * @author CSS3 Image
 */
public class CssImage extends CssValue {

	public static final int type = CssTypes.CSS_IMAGE;

	public final int getType() {
		return type;
	}

	String name;
	CssValue value;

	private String _cache;

	/**
	 * Set the value of this function
	 *
	 * @param s  the string representation of the frequency.
	 * @param ac For errors and warnings reports.
	 */
	public void set(String s, ApplContext ac) {
		// @@TODO
	}


	public void setImageList(CssExpression exp, ApplContext ac)
			throws InvalidParamException {
		name = "image";
		_cache = null;
		// ImageList defined in CSS3 and onward
		if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("image(").append(exp.toStringFromStart()).append(')');
			throw new InvalidParamException("notversion", sb.toString(),
					ac.getCssVersionString(), ac);
		}

		CssValue val;
		char op;
		boolean gotcolor = false;
		ArrayList<CssValue> v = new ArrayList<CssValue>();
		CssColor c;
		while (!exp.end()) {
			val = exp.getValue();
			op = exp.getOperator();
			// color is always last
			if (gotcolor) {
				throw new InvalidParamException("value",
						val.toString(),
						"image()", ac);
			}

			switch (val.getType()) {
				case CssTypes.CSS_URL:
				case CssTypes.CSS_STRING:
					v.add(val);
					break;
				case CssTypes.CSS_HASH_IDENT:
					c = new CssColor();
					c.setShortRGBColor(val.toString(), ac);
					v.add(c);
					gotcolor = true;
					break;
				case CssTypes.CSS_IDENT:
					c = new CssColor();
					c.setIdentColor(val.toString(), ac);
					v.add(c);
					gotcolor = true;
					break;
				default:
					throw new InvalidParamException("value",
							val.toString(),
							"image()", ac);
			}
			exp.next();
			if (!exp.end() && op != COMMA) {
				exp.starts();
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
		}
		value = (v.size() == 1) ? v.get(0) : new CssLayerList(v);
	}

	/**
	 * Returns the value
	 */
	public Object get() {
		// @@TODO
		return null;
	}

	/**
	 * Returns the name of the function
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a string representation of the object.
	 */
	public String toString() {
		if (_cache == null) {
			StringBuilder sb = new StringBuilder();
			sb.append(name).append('(').append(value).append(')');
			_cache = sb.toString();
		}
		return _cache;
	}

	/**
	 * Compares two values for equality.
	 *
	 * @param other The other value.
	 */
	public boolean equals(Object other) {
		// @@FIXME
		return (other instanceof CssImage &&
				this.name.equals(((CssImage) other).name) &&
				this.value.equals(((CssImage) other).value));
	}
}
