// Author: Chris Rebert <css.validator@chrisrebert.com>
//
// (c) COPYRIGHT World Wide Web Consortium (MIT, ERCIM, Keio University, and Beihang University), 2015.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * FIXME: This doesn't implement filter-effects-1 yet!
 *
 * @spec http://www.w3.org/TR/filter-effects-1/#FilterProperty
 * @spec http://www.w3.org/TR/SVG/filters.html#FilterProperty
 * @see https://msdn.microsoft.com/library/ms530752%28v=vs.85%29.aspx
 * @see http://davidwalsh.name/css-image-filters-internet-explorer
 */
public class CssFilter extends org.w3c.css.properties.css.CssFilter {

	public static final CssIdent[] legacy_ie_idents;

	static {
		String[] _allowed_values = {"gray", "fliph", "flipv", "xray"};
		legacy_ie_idents = new CssIdent[_allowed_values.length];
		int i = 0;
		for (String s : _allowed_values) {
			legacy_ie_idents[i++] = CssIdent.getIdent(s);
		}
	}

	public static CssIdent getLegacyIEIdent(CssIdent ident) {
		for (CssIdent id : legacy_ie_idents) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * Create a new CssFilter
	 */
	public CssFilter() {
		value = initial;
	}

	public CssFilter(final ApplContext ac, final CssExpression expression) throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Emit warnings instead of errors for legacy proprietary IE filters
	 */
	private boolean allowLegacyIEValues(final ApplContext ac) {
		return ac.getTreatVendorExtensionsAsWarnings();
	}

	/**
	 * Create a new CssFilter
	 *
	 * @param expression The expressions for this property
	 * @throws InvalidParamException Expressions are incorrect
	 */
	public CssFilter(final ApplContext ac, final CssExpression expression, final boolean check) throws InvalidParamException {
		setByUser();

		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		final CssValue val = expression.getValue();

		switch (val.getType()) {
			case CssTypes.CSS_URL:
				value = val;
				break;
			case CssTypes.CSS_FUNCTION:
				// fix the alpha/blur/wave case
				// need to investigate progid:
				throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);

			case CssTypes.CSS_IDENT:
				CssIdent ident = (CssIdent) val;
				if (inherit.equals(ident)) {
					value = inherit;
					break;
				}
				if (none.equals(ident)) {
					value = none;
					break;
				}
				if (allowLegacyIEValues(ac)) {
					value = getLegacyIEIdent(ident);
					if (value != null) {
						ac.getFrame().addWarning("vendor-extension", expression.toStringFromStart());
						break;
					}
					// not found? let it flow and fail.
				}
			default:
				throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
		}
		expression.next();

	}
}
