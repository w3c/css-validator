// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2013.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTime;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2012/CR-css3-speech-20120320/#pause-after
 */
public class CssPauseAfter extends org.w3c.css.properties.css.CssPauseAfter {


	public static final CssIdent[] allowed_values;

	static {
		String[] _allowed_values = {"none", "x-weak", "weak", "medium", "strong", "x-strong"};
		int i = 0;
		allowed_values = new CssIdent[_allowed_values.length];
		for (String s : _allowed_values) {
			allowed_values[i++] = CssIdent.getIdent(s);
		}
	}

	public static final CssIdent getAllowedIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

    /**
     * Create a new CssPauseAfter
     */
    public CssPauseAfter() {
		value = initial;
    }

    /**
     * Creates a new CssPauseAfter
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssPauseAfter(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}
		setByUser();

		CssValue val;
		char op;

		val = expression.getValue();
		op = expression.getOperator();

		switch (val.getType()) {
			case CssTypes.CSS_TIME:
				CssTime t = val.getTime();
				t.checkPositiveness(ac, this);
				value = val;
				break;
			case CssTypes.CSS_IDENT:
				CssIdent id = (CssIdent) val;
				if (inherit.equals(id)) {
					value = inherit;
					break;
				}
				value = getAllowedIdent(id);
				if (value != null) {
					break;
				}
			default:
				throw new InvalidParamException("value",
						val.toString(),
						getPropertyName(), ac);
		}
		expression.next();
    }

    public CssPauseAfter(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}

