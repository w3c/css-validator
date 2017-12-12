// $Id$
//
// (c) COPYRIGHT MIT, ECRIM and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.media.css3;

import org.w3c.css.media.MediaFeature;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2012/REC-css3-mediaqueries-20120619/#width
 */
public class MediaWidth extends MediaFeature {

	/**
	 * Create a new MediaWidth
	 */
	public MediaWidth() {
	}

	/**
	 * Create a new MediaWidth.
	 *
	 * @param expression The expression for this media feature
	 * @throws InvalidParamException Values are incorrect
	 */
	public MediaWidth(ApplContext ac, String modifier,
					  CssExpression expression, boolean check)
			throws InvalidParamException {

		if (expression != null) {
			if (check && expression.getCount() > 1) {
				throw new InvalidParamException("unrecognize", ac);
			}
			if (expression.getCount() == 0) {
				throw new InvalidParamException("few-value", getFeatureName(), ac);
			}
			CssValue val = expression.getValue();

			switch (val.getType()) {
				case CssTypes.CSS_NUMBER:
					// a bit stupid as the only value would be 0...
					val = ((CssNumber) val).getLength();
				case CssTypes.CSS_LENGTH:
					CssLength l = (CssLength) val;
					if (!l.isPositive()) {
						throw new InvalidParamException("negative-value",
								val.toString(), ac);
					}
					value = val;
					expression.next();
					break;
				default:
					throw new InvalidParamException("value", expression.getValue(),
							getFeatureName(), ac);
			}
			setModifier(ac, modifier);
		} else {
			if (modifier != null) {
				throw new InvalidParamException("nomodifiershortmedia",
						getFeatureName(), ac);
			}
		}
	}

	public MediaWidth(ApplContext ac, String modifier, CssExpression expression)
			throws InvalidParamException {
		this(ac, modifier, expression, false);
	}

	/**
	 * Returns the value of this media feature.
	 */

	public Object get() {
		return value;
	}

	/**
	 * Returns the name of this media feature.
	 */
	public final String getFeatureName() {
		return "width";
	}

	/**
	 * Compares two media features for equality.
	 *
	 * @param other The other media features.
	 */
	public boolean equals(MediaFeature other) {
		try {
			MediaWidth mw = (MediaWidth) other;
			return (((value == null) && (mw.value == null)) || ((value != null) && value.equals(mw.value)))
					&& (((modifier == null) && (mw.modifier == null)) || ((modifier != null) && modifier.equals(mw.modifier)));
		} catch (ClassCastException cce) {
			return false;
		}

	}
}
