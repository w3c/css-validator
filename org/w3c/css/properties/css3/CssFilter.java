// Initial Author: Chris Rebert <css.validator@chrisrebert.com>
//
// (c) COPYRIGHT World Wide Web Consortium (MIT, ERCIM, Keio University, and Beihang University), 2015.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec http://www.w3.org/TR/2014/WD-filter-effects-1-20141125/#propdef-filter
 * @spec http://www.w3.org/TR/SVG/filters.html#FilterProperty
 * @see <https://msdn.microsoft.com/library/ms530752%28v=vs.85%29.aspx>
 * @see <http://davidwalsh.name/css-image-filters-internet-explorer>
 */
public class CssFilter extends org.w3c.css.properties.css.CssFilter {

	// defined function
	static final String blur = "blur";
	static final String brightness = "brightness";
	static final String contrast = "contrast";
	static final String drop_shadow = "drop-shadow";
	static final String grayscale = "grayscale";
	static final String hue_rotate = "hue-rotate";
	static final String invert = "invert";
	static final String opacity = "opacity";
	static final String sepia = "sepia";
	static final String saturate = "saturate";

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

		ArrayList<CssValue> values = new ArrayList<>();
		CssValue val;
		boolean singleVal = false;

		while (!expression.end()) {
			val = expression.getValue();

			switch (val.getType()) {
				case CssTypes.CSS_URL:
					values.add(val);
					break;
				case CssTypes.CSS_FUNCTION:
					parseFunctionValues(ac, val, this);
					values.add(val);
					break;
				case CssTypes.CSS_IDENT:
					CssIdent ident = (CssIdent) val;
					if (inherit.equals(ident)) {
						value = inherit;
						singleVal = true;
						break;
					}
					if (none.equals(ident)) {
						value = none;
						singleVal = true;
						break;
					}
					if (allowLegacyIEValues(ac)) {
						value = getLegacyIEIdent(ident);
						if (value != null) {
							singleVal = true;
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
		if (singleVal && values.size() > 1) {
			throw new InvalidParamException("value",
					value.toString(),
					getPropertyName(), ac);
		}
		value = (values.size() == 1) ? values.get(0) : new CssValueList(values);

	}

	protected static void parseFunctionValues(ApplContext ac, CssValue func, CssProperty caller)
			throws InvalidParamException {
		CssFunction function = (CssFunction) func;
		String fname = function.getName().toLowerCase();

		switch (fname) {
			case blur:
				parseOneX(ac, function.getParameters(), CssTypes.CSS_LENGTH, caller);
				break;
			case brightness:
			case contrast:
			case grayscale:
			case invert:
			case opacity:
			case saturate:
			case sepia:
				parseOneNonNegativeNumPercent(ac, function.getParameters(), caller);
				break;
			case drop_shadow:
				parseDropShadowFunction(ac, function.getParameters(), caller);
				break;
			case hue_rotate:
				parseOneX(ac, function.getParameters(), CssTypes.CSS_ANGLE, caller);
				break;
			default:
				// unrecognized function
				throw new InvalidParamException("value",
						func.toString(),
						caller.getPropertyName(), ac);
		}
	}

	// parse one value of type (CssTypes.XXX)
	private static void parseOneX(ApplContext ac, CssExpression expression,
								  int type, CssProperty caller)
			throws InvalidParamException {
		if (expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		CssValue val;
		val = expression.getValue();
		// special case, 0 can be a length or an angle...
		if (val.getType() == CssTypes.CSS_NUMBER) {
			if (type == CssTypes.CSS_LENGTH || type == CssTypes.CSS_ANGLE) {
				// if not zero, it will fail
				if (val.getNumber().isZero()) {
					expression.next();
					return;
				}
			}
		}
		if (val.getType() != type) {
			throw new InvalidParamException("value",
					val.toString(),
					caller.getPropertyName(), ac);
		}
		expression.next();
	}

	// parse one value of type (CssTypes.XXX)
	private static void parseOneNonNegativeNumPercent(ApplContext ac, CssExpression expression,
													  CssProperty caller)
			throws InvalidParamException {
		if (expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		CssValue val = expression.getValue();
		char op = expression.getOperator();

		switch (val.getType()) {
			case CssTypes.CSS_NUMBER:
				CssNumber n = (CssNumber) val;
				n.checkPositiveness(ac, caller);
				break;
			case CssTypes.CSS_PERCENTAGE:
				CssPercentage p = (CssPercentage) val;
				p.checkPositiveness(ac, caller);
				break;
			default:
				throw new InvalidParamException("value",
						val.toString(),
						caller.getPropertyName(), ac);
		}
		if (op != SPACE) {
			throw new InvalidParamException("operator",
					((new Character(op)).toString()), ac);
		}
		expression.next();

	}

	private static void parseDropShadowFunction(ApplContext ac, CssExpression expression,
												CssProperty caller)
			throws InvalidParamException {
		if (expression.getCount() > 4) {
			throw new InvalidParamException("unrecognize", ac);
		}
		int nb_length = 0;
		boolean got_color = false;
		CssValue val;
		char op;
		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_NUMBER:
					val.getLength();
				case CssTypes.CSS_LENGTH:
					// color should be last | no more than 3 length.
					if (got_color || nb_length == 3) {
						throw new InvalidParamException("value",
								val.toString(),
								drop_shadow, ac);
					}
					nb_length++;
					expression.next();
					break;
				default:
					// something else is _after_ at least 2 length
					if (nb_length < 2) {
						throw new InvalidParamException("value",
								val.toString(),
								drop_shadow, ac);
					}
					try {
						CssColor tcolor = new CssColor(ac, expression, false);
						got_color = true;
						// note: parsing color does expression.next();
					} catch (Exception e) {
						throw new InvalidParamException("value",
								val.toString(),
								drop_shadow, ac);
					}
			}
			if (op != SPACE) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
		}
	}
}
