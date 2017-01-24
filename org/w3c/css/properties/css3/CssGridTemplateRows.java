//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2016.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssBracket;
import org.w3c.css.values.CssCheckableValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.properties.css3.CssGridAutoRows.parseFixedSize;
import static org.w3c.css.properties.css3.CssGridAutoRows.parseTrackSize;
import static org.w3c.css.values.CssOperator.COMMA;
import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec https://www.w3.org/TR/2016/CR-css-grid-1-20160929/#propdef-grid-template-rows
 */
public class CssGridTemplateRows extends org.w3c.css.properties.css.CssGridTemplateRows {

	public static final CssIdent[] allowed_repeat_values;
	public static final String repeat_func = "repeat";

	static {
		String[] _allowed_repeat_values = {"auto-fill", "auto-fit"};
		allowed_repeat_values = new CssIdent[_allowed_repeat_values.length];
		int i = 0;
		for (String s : _allowed_repeat_values) {
			allowed_repeat_values[i++] = CssIdent.getIdent(s);
		}
	}

	protected enum RepeatType {TRACK_REPEAT, AUTO_REPEAT, FIXED_REPEAT}

	public static CssIdent getAllowedRepeatIdent(CssIdent ident) {
		for (CssIdent id : allowed_repeat_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * Create a new CssGridTemplateRows
	 */
	public CssGridTemplateRows() {
		value = initial;
	}

	/**
	 * Creates a new CssGridTemplateRows
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssGridTemplateRows(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		CssValue val;
		char op;

		ArrayList<CssValue> values = new ArrayList<>();
		CssIdent ident;

		val = expression.getValue();
		op = expression.getOperator();

		switch (val.getType()) {
			case CssTypes.CSS_IDENT:
				if (inherit.equals(val)) {
					if (expression.getCount() > 1) {
						throw new InvalidParamException("unrecognize", ac);
					}
					values.add(inherit);
					expression.next();
					break;
				}
				if (none.equals(val)) {
					if (expression.getCount() > 1) {
						throw new InvalidParamException("unrecognize", ac);
					}
					values.add(none);
					expression.next();
					break;
				}
			default:
				expression.mark();
				try {
					values.add(parseTrackList(ac, expression, this));
				} catch (InvalidParamException ex) {
					// perhaps an AutoTrackList?
					expression.reset();
					values.add(parseAutoTrackList(ac, expression, this));
				}
		}
		value = (values.size() == 1) ? values.get(0) : new CssValueList(values);

	}

	protected static CssValue parseTrackList(ApplContext ac, CssExpression exp, CssProperty caller)
			throws InvalidParamException {
		ArrayList<CssValue> values = new ArrayList<>();
		CssValue val;
		char op;

		boolean in_line_names = false;
		boolean got_line_names = false;
		boolean got_size = false;
		while (!exp.end()) {
			val = exp.getValue();
			op = exp.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_BRACKET:
					CssBracket bracket = (CssBracket) val;
					if (bracket.isLeft()) {
						if (in_line_names || got_line_names) {
							throw new InvalidParamException("value",
									val.toString(),
									caller.getPropertyName(), ac);
						}
						in_line_names = true;
					} else { // bracket.isRight() but it can't be anything else...
						if (!in_line_names) {
							throw new InvalidParamException("value",
									val.toString(),
									caller.getPropertyName(), ac);
						}
						got_line_names = true;
						in_line_names = false;
					}
					values.add(val);
					break;
				case CssTypes.CSS_IDENT:
					if (in_line_names) {
						// todo check unreserved words
						values.add(val);
						break;
					}
					values.add(parseTrackSize(ac, val, caller));
					got_line_names = false;
					got_size = true;
					break;
				case CssTypes.CSS_FUNCTION:
					CssFunction function = (CssFunction) val;
					if (repeat_func.equals(function.getName())) {
						values.add(parseRepeatFunction(ac, function, RepeatType.TRACK_REPEAT, caller));
						got_line_names = false;
						got_size = true;
						break;
					}
					// not a repeat function, let it flow.
				default:
					// should be a tracksize, or fail.
					values.add(parseTrackSize(ac, val, caller));
					got_size = true;
					got_line_names = false;

			}
			if (op != SPACE) {
				throw new InvalidParamException("operator", op,
						caller.getPropertyName(), ac);
			}
			exp.next();
		}
		if (values.isEmpty() || !got_size) {
			throw new InvalidParamException("unrecognize", ac);
		}
		return (values.size() == 1) ? values.get(0) : new CssValueList(values);
	}


	protected static CssValue parseAutoTrackList(ApplContext ac, CssExpression exp, CssProperty caller)
			throws InvalidParamException {
		ArrayList<CssValue> values = new ArrayList<>();
		CssValue val;
		char op;

		boolean in_line_names = false;
		boolean got_line_names = false;
		boolean got_auto = false;
		while (!exp.end()) {
			val = exp.getValue();
			op = exp.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_BRACKET:
					CssBracket bracket = (CssBracket) val;
					if (bracket.isLeft()) {
						if (in_line_names || got_line_names) {
							throw new InvalidParamException("value",
									val.toString(),
									caller.getPropertyName(), ac);
						}
						in_line_names = true;
					} else { // bracket.isRight() but it can't be anything else...
						if (!in_line_names) {
							throw new InvalidParamException("value",
									val.toString(),
									caller.getPropertyName(), ac);
						}
						got_line_names = true;
						in_line_names = false;
					}
					values.add(val);
					break;
				case CssTypes.CSS_IDENT:
					if (in_line_names) {
						// todo check unreserved words
						values.add(val);
						break;
					}
					// no other ident allowed.
					throw new InvalidParamException("value",
							val.toString(),
							caller.getPropertyName(), ac);
				case CssTypes.CSS_FUNCTION:
					CssFunction function = (CssFunction) val;
					if (repeat_func.equals(function.getName())) {
						if (exp.getRemainingCount() == 1) {
							values.add(parseRepeatFunction(ac, function, RepeatType.AUTO_REPEAT, caller));
							got_auto = true;
						} else {
							values.add(parseRepeatFunction(ac, function, RepeatType.FIXED_REPEAT, caller));
						}
						got_line_names = false;
						break;
					}
					// not a repeat function, let it flow.
				default:
					// should be a tracksize, or fail.
					values.add(parseTrackSize(ac, val, caller));
					got_line_names = false;

			}
			if (op != SPACE) {
				throw new InvalidParamException("operator", op,
						caller.getPropertyName(), ac);
			}
			exp.next();
		}
		if (values.isEmpty() || !got_auto) {
			throw new InvalidParamException("unrecognize", ac);
		}
		return (values.size() == 1) ? values.get(0) : new CssValueList(values);
	}

	/**
	 * @spec https://www.w3.org/TR/2016/CR-css-grid-1-20160929/#funcdef-repeat
	 */
	protected static CssFunction parseRepeatFunction(ApplContext ac, CssFunction func,
													 RepeatType type,
													 CssProperty caller)
			throws InvalidParamException {
		CssExpression exp = func.getParameters();
		CssExpression nex;
		CssValue val;
		char op;

		if (exp.getCount() < 2) {
			throw new InvalidParamException("unrecognize", ac);
		}
		nex = new CssExpression();
		val = exp.getValue();
		op = exp.getOperator();

		switch (val.getType()) {
			case CssTypes.CSS_IDENT:
				CssIdent id = getAllowedRepeatIdent((CssIdent) val);
				if (id == null || type != RepeatType.AUTO_REPEAT) {
					throw new InvalidParamException("value",
							val.toString(),
							caller.getPropertyName(), ac);
				}
				break;
			case CssTypes.CSS_NUMBER:
				if (type != RepeatType.TRACK_REPEAT && type != RepeatType.FIXED_REPEAT) {
					throw new InvalidParamException("value",
							val.toString(),
							caller.getPropertyName(), ac);
				}
				CssCheckableValue v = val.getCheckableValue();
				v.checkInteger(ac, caller);
				v.checkPositiveness(ac, caller);
				break;
			default:
				throw new InvalidParamException("value",
						val.toString(),
						caller.getPropertyName(), ac);
		}
		if (op != COMMA) {
			throw new InvalidParamException("operator", op,
					caller.getPropertyName(), ac);
		}
		exp.next();
		boolean got_line_names = false;
		boolean in_line_names = false;

		while (!exp.end()) {
			val = exp.getValue();
			op = exp.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_BRACKET:
					CssBracket bracket = (CssBracket) val;
					if (bracket.isLeft()) {
						if (in_line_names || got_line_names) {
							throw new InvalidParamException("value",
									val.toString(),
									caller.getPropertyName(), ac);
						}
						in_line_names = true;
					} else { // bracket.isRight() but it can't be anything else...
						if (!in_line_names) {
							throw new InvalidParamException("value",
									val.toString(),
									caller.getPropertyName(), ac);
						}
						got_line_names = true;
						in_line_names = false;
					}
					break;
				case CssTypes.CSS_IDENT:
					if (in_line_names) {
						// todo check unreserved words
						break;
					}
					// same branch for FLEX as it can only be TRACK_REPEAT.
				case CssTypes.CSS_FLEX:
					if (type == RepeatType.TRACK_REPEAT) {
						parseTrackSize(ac, val, caller);
						got_line_names = false;
					}
					break;
				case CssTypes.CSS_NUMBER:
				case CssTypes.CSS_LENGTH:
				case CssTypes.CSS_PERCENTAGE:
				case CssTypes.CSS_FUNCTION:
					switch (type) {
						case AUTO_REPEAT:
						case FIXED_REPEAT:
							parseFixedSize(ac, val, caller);
							break;
						case TRACK_REPEAT:
							parseTrackSize(ac, val, caller);
							break;
						default:
							// wrong type?
							throw new InvalidParamException("value",
									val.toString(),
									caller.getPropertyName(), ac);
					}
					// we made it! now wait for a possible line name
					got_line_names = false;
					break;
				default:
					throw new InvalidParamException("value",
							val.toString(),
							caller.getPropertyName(), ac);
			}
			if (op != SPACE) {
				throw new InvalidParamException("operator", op,
						caller.getPropertyName(), ac);
			}
			exp.next();
		}
		exp.starts();
		// we reached the end without closing the line-names...
		if (in_line_names) {
			throw new InvalidParamException("value",
					val.toString(),
					caller.getPropertyName(), ac);
		}
		return func;
	}

	public CssGridTemplateRows(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

