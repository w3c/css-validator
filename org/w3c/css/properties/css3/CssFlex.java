// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.math.BigDecimal;

/**
 * @spec http://www.w3.org/TR/2012/CR-css3-flexbox-20120918/#flex
 */
public class CssFlex extends org.w3c.css.properties.css.CssFlexFlow {

	private CssFlexGrow flexGrow;
	private CssFlexShrink flexShrink;
	private CssFlexBasis flexBasis;

	/**
	 * Create a new CssFlexFlow
	 */
	public CssFlex() {
		value = initial;
		flexGrow = new CssFlexGrow();
		flexShrink = new CssFlexShrink();
		flexBasis = new CssFlexBasis();
	}

	/**
	 * Creates a new CssFlexFlow
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFlex(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 3) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue growVal = null;
		CssValue shrinkVal = null;
		CssValue basisVal = null;
		CssValue val;
		char op;
		boolean gotNumber = false;

		while (!expression.end()) {
			val = expression.getValue();
			op = expression.getOperator();

			switch (val.getType()) {
				case CssTypes.CSS_IDENT:
					CssIdent ident = (CssIdent) val;
					if (inherit.equals(ident)) {
						value = inherit;
						if (expression.getCount() > 1) {
							throw new InvalidParamException("value",
									val.toString(),
									getPropertyName(), ac);
						}
						break;
					}
					if (none.equals(ident)) {
						value = none;
						if (expression.getCount() > 1) {
							throw new InvalidParamException("value",
									val.toString(),
									getPropertyName(), ac);
						}
						break;
					}
					if (basisVal == null) {
						basisVal = CssFlexBasis.getAllowedIdent(ident);
						if (basisVal == null) {
							throw new InvalidParamException("value",
									val.toString(),
									getPropertyName(), ac);
						}
						gotNumber = false;
						break;
					}
					// unrecognized token...
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
				case CssTypes.CSS_NUMBER:
					if (growVal == null) {
						CssNumber num = val.getNumber();
						num.checkPositiveness(ac, this);
						growVal = val;
						gotNumber = true;
						break;
					}
					// we can get shrink only after grow
					if (gotNumber && shrinkVal == null) {
						CssNumber num = val.getNumber();
						num.checkPositiveness(ac, this);
						shrinkVal = val;
						break;
					}
					// we got too many number -> fail if not zero...
					// which happens only of basisVal is null
					// and we get a length, so val = 0
					// otherwise it will either fail or flow to default:
				case CssTypes.CSS_LENGTH:
					if (basisVal == null) {
						CssLength l = val.getLength();
						l.checkPositiveness(ac, this);
						basisVal = l;
						break;
					}
				case CssTypes.CSS_PERCENTAGE:
					if (basisVal == null) {
						CssPercentage p = val.getPercentage();
						p.checkPositiveness(ac, this);
						basisVal = p;
						break;
					}
				default:
					throw new InvalidParamException("value",
							val.toString(),
							getPropertyName(), ac);
			}
			if (op != CssOperator.SPACE) {
				throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
			}
			expression.next();
		}
		// for addToStyle, redefinitions and equality check
		flexBasis = new CssFlexBasis();
		flexGrow = new CssFlexGrow();
		flexShrink = new CssFlexShrink();
		if (value == inherit) {
			flexBasis.value = inherit;
			flexGrow.value = inherit;
			flexShrink.value = inherit;
		} else if (value == none) {
			flexBasis.value = CssFlexBasis.auto;
			CssNumber z = new CssNumber();
			z.setValue(BigDecimal.ZERO);
			flexGrow.value = z;
			flexShrink.value = z;
		} else {
			CssValueList v = new CssValueList();
			if (growVal != null) {
				v.add(growVal);
				flexGrow.value = growVal;
				if (shrinkVal != null) {
					v.add(shrinkVal);
					flexShrink.value = shrinkVal;
				}
			}
			if (basisVal != null) {
				v.add(basisVal);
				flexBasis.value = basisVal;
			}
			value = v;
		}
	}


	public CssFlex(ApplContext ac, CssExpression expression)
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
		flexBasis.addToStyle(ac, style);
		flexGrow.addToStyle(ac, style);
		flexShrink.addToStyle(ac, style);
	}
}

