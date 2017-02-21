//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2017.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec https://www.w3.org/TR/2017/CR-css-scroll-snap-1-20170209/#propdef-scroll-snap-margin
 */
public class CssScrollSnapMargin extends org.w3c.css.properties.css.CssScrollSnapMargin {

	private CssScrollSnapMarginTop _longhand_top;
	private CssScrollSnapMarginBottom _longhand_bottom;
	private CssScrollSnapMarginLeft _longhand_left;
	private CssScrollSnapMarginRight _longhand_right;

	/**
	 * Create a new CssScrollSnapMarginInline
	 */
	public CssScrollSnapMargin() {
		value = initial;
		_longhand_bottom = new CssScrollSnapMarginBottom();
		_longhand_left = new CssScrollSnapMarginLeft();
		_longhand_right = new CssScrollSnapMarginRight();
		_longhand_top = new CssScrollSnapMarginTop();
	}

	/**
	 * Creates a new CssScrollSnapMarginInline
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssScrollSnapMargin(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		setByUser();
		CssValue val;
		char op;

		if (check && expression.getCount() > 4) {
			throw new InvalidParamException("unrecognize", ac);
		}
		ArrayList<CssValue> values = new ArrayList<>();
		_longhand_bottom = new CssScrollSnapMarginBottom();
		_longhand_left = new CssScrollSnapMarginLeft();
		_longhand_right = new CssScrollSnapMarginRight();
		_longhand_top = new CssScrollSnapMarginTop();

		for (int i = 0; i < 4; i++) {
			val = expression.getValue();
			op = expression.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_NUMBER:
					val.getCheckableValue().checkEqualsZero(ac, this);
				case CssTypes.CSS_LENGTH:
					values.add(val);
					switch (i) {
						case 0:
							_longhand_top.value = val;
							break;
						case 1:
							_longhand_right.value = val;
							break;
						case 2:
							_longhand_bottom.value = val;
							break;
						case 3:
							_longhand_left.value = val;
							break;
						default:
							// can't happen by design
					}
					break;
				case CssTypes.CSS_IDENT:
					if (inherit.equals(val)) {
						if (expression.getCount() > 1) {
							throw new InvalidParamException("value",
									expression.getValue(),
									getPropertyName(), ac);
						}
						values.add(val);
						_longhand_top.value = inherit;
						_longhand_right.value = inherit;
						_longhand_bottom.value = inherit;
						_longhand_left.value = inherit;
						break;
					}
				default:
					throw new InvalidParamException("value",
							expression.getValue(),
							getPropertyName(), ac);
			}
			if (op != SPACE) {
				throw new InvalidParamException("operator", op,
						getPropertyName(), ac);
			}
			expression.next();
			if (expression.end()) {
				// get out of the loop when end is reached prematurely
				break;
			}
		}
		value = (values.size() == 1) ? values.get(0) : new CssValueList(values);
	}

	public CssScrollSnapMargin(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Add this property to the CssStyle.
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
		super.addToStyle(ac, style);
		_longhand_top.addToStyle(ac, style);
		_longhand_right.addToStyle(ac, style);
		_longhand_bottom.addToStyle(ac, style);
		_longhand_left.addToStyle(ac, style);

	}
}

