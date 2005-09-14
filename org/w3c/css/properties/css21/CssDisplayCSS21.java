// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssDisplayCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 * CssDisplayCSS21<br />
 * Created: Aug 30, 2005 3:54:43 PM<br />
 */
public class CssDisplayCSS21 extends CssDisplayCSS2 {

    private static String[] DISPLAY = {
	"inline", "block", "inline-block", "list-item", "run-in", "table",
	"inline-table", "table-row-group", "table-column-group",
	"table-header-group", "table-footer-group", "table-row", "table-column",
	"table-cell", "table-caption", "none", "inherit" };

    private static int[] hash_values;

    static {
	hash_values = new int[DISPLAY.length];
	for (int i = 0; i < DISPLAY.length; i++)
	    hash_values[i] = DISPLAY[i].hashCode();
    }

    /**
     * Create a new CssDisplay
     */
    public CssDisplayCSS21() {
	// nothing to do
    }

    /**
     * Create a new CssDisplay
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorect
     */
    public CssDisplayCSS21(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();

	setByUser();

	if ( val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < DISPLAY.length; i++) {
		if (hash_values[i] == hash) {
		    setValue(i);
		    expression.next();
		    return;
		}
	    }
	}

	throw new InvalidParamException("value", expression.getValue(),
					getPropertyName(), ac);
    }

    public CssDisplayCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return DISPLAY[getValue()];
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return getValue() == DISPLAY.length - 1;
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return DISPLAY[getValue()];
    }
}
