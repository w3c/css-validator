//
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 14th 2000 Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang 1997-2016.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2007/WD-css3-box-20070809/#the-lsquo
 * @spec https://www.w3.org/TR/2016/CR-css-flexbox-1-20160526/#flex-containers
 * @spec http://www.w3.org/TR/2011/WD-css3-lists-20110524/#display-marker
 */
public class CssDisplay extends org.w3c.css.properties.css.CssDisplay {

	public static CssIdent[] allowed_values;

	static {
		String[] DISPLAY = {
				"inline", "block", "inline-block", "list-item", "run-in",
				"compact", "table", "inline-table", "table-row-group",
				"table-header-group", "table-footer-group", "table-row",
				"table-column-group", "table-column", "table-cell",
				"table-caption", "ruby", "ruby-base", "ruby-text",
				"ruby-base-group", "ruby-text-group", "none"
		};
		String[] FLEX_DISPLAY = {"flex", "inline-flex"};
		String[] LISTS_DISPLAY = {"marker"};
		allowed_values = new CssIdent[DISPLAY.length+FLEX_DISPLAY.length+LISTS_DISPLAY.length];
		int i = 0;
		for (String aDISPLAY : DISPLAY) {
			allowed_values[i++] = CssIdent.getIdent(aDISPLAY);
		}
		for(String aDISPLAY : FLEX_DISPLAY) {
			allowed_values[i++] = CssIdent.getIdent(aDISPLAY);
		}
		for(String aDISPLAY : LISTS_DISPLAY) {
			allowed_values[i++] = CssIdent.getIdent(aDISPLAY);
		}
	}

	public static CssIdent getMatchingIdent(CssIdent ident) {
		for (CssIdent id : allowed_values) {
			if (id.equals(ident)) {
				return id;
			}
		}
		return null;
	}

	/**
	 * Create a new CssDisplay
	 */
	public CssDisplay() {
		value = initial;
	}

	/**
	 * Create a new CssDisplay
	 *
	 * @param ac         The context
	 * @param expression The expression for this property
	 * @param check      true if explicit check is needed
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Values are incorect
	 */
	public CssDisplay(ApplContext ac, CssExpression expression,
					  boolean check) throws InvalidParamException {

		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}

		CssValue val = expression.getValue();

		setByUser();

		if (val.getType() == CssTypes.CSS_IDENT) {
			CssIdent id_val = (CssIdent) val;
			if (inherit.equals(id_val)) {
				value = inherit;
			} else {
				value = getMatchingIdent(id_val);
			}
			if (value == null) {
				// do templates...
			}
			if (value != null) {
				expression.next();
				return;
			}
		}

		throw new InvalidParamException("value", expression.getValue(),
				getPropertyName(), ac);
	}

	public CssDisplay(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

	/**
	 * Is the value of this property is a default value.
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
		return (value == inline);
	}

}
