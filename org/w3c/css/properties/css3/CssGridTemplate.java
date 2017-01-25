//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2017.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssBracket;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.properties.css3.CssGridAutoRows.parseTrackSize;
import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec https://www.w3.org/TR/2016/CR-css-grid-1-20160929/#propdef-grid-template
 */
public class CssGridTemplate extends org.w3c.css.properties.css.CssGridTemplate {

	private CssGridTemplateAreas cssGridTemplateAreas;
	private CssGridTemplateColumns cssGridTemplateColumns;
	private CssGridTemplateRows cssGridTemplateRows;

	/**
	 * Create a new CssGridTemplate
	 */
	public CssGridTemplate() {
		value = initial;
		cssGridTemplateAreas = new CssGridTemplateAreas();
		cssGridTemplateColumns = new CssGridTemplateColumns();
		cssGridTemplateRows = new CssGridTemplateRows();
	}

	/**
	 * Creates a new CssGridTemplate
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssGridTemplate(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		CssValue val = null;
		CssValue v;
		char op;

		setByUser();

		ArrayList<CssValue> values = new ArrayList<>();
		ArrayList<CssValue> areaValues = new ArrayList<>();
		ArrayList<CssValue> columnValues = new ArrayList<>();
		ArrayList<CssValue> rowValues = new ArrayList<>();

		cssGridTemplateAreas = new CssGridTemplateAreas();
		cssGridTemplateColumns = new CssGridTemplateColumns();
		cssGridTemplateRows = new CssGridTemplateRows();


		if (expression.getCount() == 1) {
			// can only be 'none' or 'inherit'
			val = expression.getValue();
			if (val.getType() != CssTypes.CSS_IDENT) {
				throw new InvalidParamException("value",
						value.toString(),
						getPropertyName(), ac);
			}
			CssIdent id = (CssIdent) val;
			if (none.equals(id)) {
				values.add(none);
				areaValues.add(none);
				columnValues.add(none);
				rowValues.add(none);
			} else if (inherit.equals(id)) {
				values.add(inherit);
				areaValues.add(inherit);
				columnValues.add(inherit);
				rowValues.add(inherit);
			} else {
				throw new InvalidParamException("value",
						value.toString(),
						getPropertyName(), ac);
			}
			expression.next();
		} else {
			// check if there is a CssString element to decide which case we are in.
			boolean got_string = false;

			while (!expression.end() && !got_string) {
				val = expression.getValue();
				got_string = (val.getType() == CssTypes.CSS_STRING);
				expression.next();
			}
			expression.starts();

			if (!got_string) {
				// we should have  <?grid-template-rows?> / <?grid-template-columns?>
				CssExpression nex = new CssExpression();
				boolean got_slash = false;
				while (!got_slash && !expression.end()) {
					val = expression.getValue();
					op = expression.getOperator();
					got_slash = (val.getType() == CssTypes.CSS_SWITCH);
					if (got_slash) {
						if (op != SPACE) {
							throw new InvalidParamException("operator", op,
									getPropertyName(), ac);
						}
					} else {
						nex.addValue(val);
						nex.setOperator(op);
					}
					expression.next();
				}
				if (!got_slash) {
					throw new InvalidParamException("unrecognize", ac);
				}
				v = CssGridTemplateRows.parseTemplateRows(ac, nex, this);
				rowValues.add(v);
				values.add(v);
				values.add(val);
				nex = new CssExpression();
				while (!expression.end()) {
					val = expression.getValue();
					op = expression.getOperator();
					nex.addValue(val);
					nex.setOperator(op);
					expression.next();
				}
				v = CssGridTemplateRows.parseTemplateRows(ac, nex, this);
				columnValues.add(v);
				values.add(v);
				areaValues.add(none);
			} else {
				// [ <line-names>? <string> <track-size>? <line-names>? ]+ [ / <explicit-track-list> ]?
				boolean got_slash = false;
				CssExpression nex = new CssExpression();
				boolean in_line_names = false;
				int got_line_names = 1;     // why 1? because we can have only 1 <list-name> first

				while (!got_slash && !expression.end()) {
					val = expression.getValue();
					op = expression.getOperator();

					switch (val.getType()) {
						case CssTypes.CSS_STRING:
							if (in_line_names) {
								throw new InvalidParamException("value",
										value.toString(),
										getPropertyName(), ac);
							}
							got_line_names = 0;
							areaValues.add(val);
							values.add(val);
							break;
						case CssTypes.CSS_BRACKET:
							CssBracket bracket = (CssBracket) val;
							if (bracket.isLeft()) {
								if (in_line_names || (got_line_names > 2)) {
									throw new InvalidParamException("value",
											val.toString(),
											getPropertyName(), ac);
								}
								in_line_names = true;
							} else { // bracket.isRight() but it can't be anything else...
								if (!in_line_names) {
									throw new InvalidParamException("value",
											val.toString(),
											getPropertyName(), ac);
								}
								got_line_names++;
								in_line_names = false;
							}
							values.add(val);
							rowValues.add(val);
							break;
						case CssTypes.CSS_SWITCH:
							got_slash = true;
							values.add(val);
							break;
						case CssTypes.CSS_IDENT:
							if (in_line_names) {
								values.add(val);
								rowValues.add(val);
								break;
							}
						default:
							v = parseTrackSize(ac, val, this);
							values.add(v);
							rowValues.add(v);
					}
					if (op != SPACE) {
						throw new InvalidParamException("operator", op,
								getPropertyName(), ac);
					}
					expression.next();
				}
				if (got_slash) {
					while (!expression.end()) {
						val = expression.getValue();
						op = expression.getOperator();
						nex.addValue(val);
						nex.setOperator(op);
						expression.next();
					}
					v = CssGridTemplateRows.parseExplicitTrackList(ac, nex, this);
					columnValues.add(v);
					values.add(v);
				} else {
					columnValues.add(none);
				}
			}
		}

		value = (values.size() == 1) ? values.get(0) : new CssValueList(values);
		cssGridTemplateAreas.value = (areaValues.size() == 1) ? areaValues.get(0) : new CssValueList(areaValues);
		cssGridTemplateColumns.value = (columnValues.size() == 1) ? columnValues.get(0) : new CssValueList(columnValues);
		cssGridTemplateRows.value = (rowValues.size() == 1) ? rowValues.get(0) : new CssValueList(rowValues);
	}

	public CssGridTemplate(ApplContext ac, CssExpression expression)
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
		cssGridTemplateAreas.addToStyle(ac, style);
		cssGridTemplateColumns.addToStyle(ac, style);
		cssGridTemplateRows.addToStyle(ac, style);
	}

}

