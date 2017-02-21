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
public class CssScrollPadding extends org.w3c.css.properties.css.CssScrollPadding {

	private CssScrollPaddingInlineStart _longhand_inline_start;
	private CssScrollPaddingInlineEnd _longhand_inline_end;
	private CssScrollPaddingBlockStart _longhand_block_start;
	private CssScrollPaddingBlockEnd _longhand_block_end;

	/**
	 * Create a new CssScrollPaddingInline
	 */
	public CssScrollPadding() {
		value = initial;
		_longhand_inline_start = new CssScrollPaddingInlineStart();
		_longhand_inline_end = new CssScrollPaddingInlineEnd();
		_longhand_block_start = new CssScrollPaddingBlockStart();
		_longhand_block_end = new CssScrollPaddingBlockEnd();
	}

	/**
	 * Creates a new CssScrollPaddingInline
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssScrollPadding(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		setByUser();
		CssValue val;
		char op;

		if (check && expression.getCount() > 4) {
			throw new InvalidParamException("unrecognize", ac);
		}
		ArrayList<CssValue> values = new ArrayList<>();
		_longhand_inline_start = new CssScrollPaddingInlineStart();
		_longhand_inline_end = new CssScrollPaddingInlineEnd();
		_longhand_block_start = new CssScrollPaddingBlockStart();
		_longhand_block_end = new CssScrollPaddingBlockEnd();

		for (int i = 0; i < 4; i++) {
			val = expression.getValue();
			op = expression.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_NUMBER:
					val.getCheckableValue().checkEqualsZero(ac, this);
				case CssTypes.CSS_LENGTH:
				case CssTypes.CSS_PERCENTAGE:
					values.add(val);
					switch (i) {
						case 0:
							_longhand_block_start.value = val;
							break;
						case 1:
							_longhand_inline_start.value = val;
							break;
						case 2:
							_longhand_block_end.value = val;
							break;
						case 3:
							_longhand_inline_end.value = val;
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
						_longhand_block_start.value = inherit;
						_longhand_inline_start.value = inherit;
						_longhand_block_end.value = inherit;
						_longhand_inline_end.value = inherit;
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
		}
		value = (values.size() == 1) ? values.get(0) : new CssValueList(values);
	}

	public CssScrollPadding(ApplContext ac, CssExpression expression)
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
		_longhand_block_start.addToStyle(ac, style);
		_longhand_inline_start.addToStyle(ac, style);
		_longhand_block_end.addToStyle(ac, style);
		_longhand_inline_end.addToStyle(ac, style);

	}
}

