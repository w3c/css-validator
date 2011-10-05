// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewritten 2010 Yves Lafon <ylafon@w3.org>
//
// COPYRIGHT (c) 1995-2010 World Wide Web Consortium, (MIT, ERCIM and Keio)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * http://www.w3.org/TR/2009/CR-css3-multicol-20091217/#column-gap
 * <p/>
 * Name:  	column-gap
 * Value: 	&lt;length&gt; | normal
 * Initial: 	normal
 * Applies to: 	multicol elements
 * Inherited: 	no
 * Percentages: 	N/A
 * Media: 	visual
 * Computed value: 	absolute length or ‚Äònormal‚Äô
 * <p/>
 * The ‚Äòcolumn-gap‚Äô property sets the gap between columns. If there is a
 * column rule between columns, it will appear in the middle of the gap.
 * <p/>
 * The ‚Äònormal‚Äô value is UA-specific. A value of ‚Äò1em‚Äô is suggested.
 * <p/>
 * Column gaps cannot be negative.
 */

public class CssColumnGap extends org.w3c.css.properties.css.CssColumnGap {

    CssValue columngap;

    static CssIdent normal;

    static {
        normal = CssIdent.getIdent("normal");
    }

    /**
     * Create a new CssColumnGap
     */
    public CssColumnGap() {
        columngap = initial;
    }

    /**
     * Create a new CssColumnGap
     */
    public CssColumnGap(ApplContext ac, CssExpression expression,
                        boolean check) throws InvalidParamException {
        setByUser();
        CssValue val = expression.getValue();
        Float value;

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                val = ((CssNumber) val).getLength();
            case CssTypes.CSS_LENGTH:
                value = (Float) ((CssLength) val).get();
                if (value == null || value.floatValue() < 0.0) {
                    throw new InvalidParamException("negative-value",
                            expression.getValue(),
                            getPropertyName(), ac);
                }
                columngap = val;
                break;
            case CssTypes.CSS_IDENT:
                if (normal.equals(val)) {
                    columngap = normal;
                    break;
                }
                if (inherit.equals(val)) {
                    columngap = inherit;
                    break;
                }
            default:
                throw new InvalidParamException("value", expression.getValue(),
                        getPropertyName(), ac);
        }
        expression.next();
    }

    public CssColumnGap(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssColumnGap &&
                columngap.equals(((CssColumnGap) property).columngap));
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return columngap;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
        return (inherit == columngap);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
        return columngap.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (columngap == initial);
    }

}
