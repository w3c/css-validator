// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
// Rewriten 2010 Yves Lafon <ylafon@w3.org>
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
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2011/CR-css3-multicol-20110412/#column-width
 *
 * <p/>
 * Name:  	column-width
 * Value: 	&lt;length&gt; | auto
 * Initial: 	auto
 * Applies to: 	non-replaced block-level elements (except table elements),
 * table cells, and inline-block elements
 * Inherited: 	no
 * Percentages: 	N/A
 * Media: 	visual
 * Computed value: 	the absolute length
 * <p/>
 * This property describes the width of columns in multicol elements.
 */

public class CssColumnWidth extends org.w3c.css.properties.css.CssColumnWidth {

    private static final String propertyName = "column-width";

    CssValue width;

    static CssIdent auto;

    static {
        auto = CssIdent.getIdent("auto");
    }

    /**
     * Create a new CssColumnWidth
     */
    public CssColumnWidth() {
        width = initial;
    }

    /**
     * Create a new CssColumnWidth
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException Incorrect value
     */
    public CssColumnWidth(ApplContext ac, CssExpression expression,
                          boolean check) throws InvalidParamException {

        setByUser();
        CssValue val = expression.getValue();
        Float value;

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                val = ((CssNumber) val).getLength();
                // if we didn't fall in the first trap, there is another one :)
                throw new InvalidParamException("strictly-positive",
                        expression.getValue(),
                        getPropertyName(), ac);
            case CssTypes.CSS_LENGTH:
                value = (Float) ((CssLength) val).get();
                if (value == null || value.floatValue() <= 0.0) {
                    throw new InvalidParamException("strictly-positive",
                            expression.getValue(),
                            getPropertyName(), ac);
                }
                width = val;
                break;
            case CssTypes.CSS_IDENT:
                if (inherit.equals(val)) {
                    width = inherit;
                    break;
                } else if (auto.equals(val)) {
                    width = auto;
                    break;
                }
            default:
                throw new InvalidParamException("value", expression.getValue(),
                        getPropertyName(), ac);
        }
        expression.next();
    }

    public CssColumnWidth(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssColumnWidth &&
                width.equals(((CssColumnWidth) property).width));
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return width;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
        return (inherit == width);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
        return width.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return (auto == initial);
    }

}
