// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten 2010 Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT 1995-2010  World Wide Web Consortium (MIT, ERCIM and Keio)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

import java.util.ArrayList;

/**
 * @spec http://www.w3.org/TR/2011/CR-css3-multicol-20110412/#filling-columns
 */

public class CssColumnFill extends org.w3c.css.properties.css.CssColumnFill {

    CssIdent value;

    private static ArrayList<CssIdent> allowed_values;

    static {
        allowed_values = new ArrayList<CssIdent>(2);
        allowed_values.add(CssIdent.getIdent("balance"));
        allowed_values.add(CssIdent.getIdent("auto"));
    }

    static public CssIdent getAllowedValue(CssIdent ident) {
        for (CssIdent id : allowed_values) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
    }

    /**
     * Create a new CssColumnWidth
     */
    public CssColumnFill() {
        value = initial;
    }

    /**
     * Create a new CssColumnFill
     *
     * @param ac         the context
     * @param expression The expression for this property
     * @param check      if length check is needed
     * @throws org.w3c.css.util.InvalidParamException
     *          Incorrect value
     */
    public CssColumnFill(ApplContext ac, CssExpression expression,
                         boolean check) throws InvalidParamException {

        setByUser();
        CssValue val = expression.getValue();

        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        if (val.getType() != CssTypes.CSS_IDENT) {
            throw new InvalidParamException("value",
                    expression.getValue(),
                    getPropertyName(), ac);
        }
        // ident, so inherit, or allowed value
        if (inherit.equals(val)) {
            value = inherit;
        } else {
            val = getAllowedValue((CssIdent) val);
            if (val == null) {
                throw new InvalidParamException("value",
                        expression.getValue(),
                        getPropertyName(), ac);
            }
            value = (CssIdent) val;
        }
        expression.next();
    }

    public CssColumnFill(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssColumnFill &&
                value.equals(((CssColumnFill) property).value));
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return value;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
        return (inherit == value);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
        return value.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (initial == value);
    }

}
