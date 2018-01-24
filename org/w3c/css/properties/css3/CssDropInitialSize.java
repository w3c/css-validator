//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 * <p/>
 * <EM>Value:</EM> auto || &lt;percentage&gt; || &lt;length&gt; || inherit<BR>
 * <EM>Initial:</EM>auto<BR>
 * <EM>Applies to:</EM>inline-level elements<BR>
 * <EM>Inherited:</EM>yes<BR>
 * <EM>Percentages:</EM>refers to the 'line-height' of the element<BR>
 * <EM>Media:</EM>:visual
 * <p/>
 * The 'alignment-adjust' property allows more precise alignment of
 * elements, such as graphics, that do not have a baseline-table
 * or lack the desired baseline in their baseline-table. With the
 * 'alignment-adjust' property, the position of the baseline
 * identified by the 'alignment-baseline' can be explicitly determined.
 */

public class CssDropInitialSize extends CssProperty {

    CssValue dropvalue;

    CssIdent auto = new CssIdent("auto");
    CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssDropInitialSize
     */
    public CssDropInitialSize() {
        dropvalue = auto;
    }

    /**
     * Create a new CssDropInitialSize
     *
     * @param expression The expression for this property
     * @throws InvalidParamException Incorrect value
     */
    public CssDropInitialSize(ApplContext ac, CssExpression expression,
                              boolean check) throws InvalidParamException {

        setByUser();
        CssValue val = expression.getValue();

        if (val.equals(auto)) {
            dropvalue = auto;
            expression.next();
        } else if (val.equals(initial)) {
            dropvalue = initial;
            expression.next();
        } else if (val instanceof CssNumber) {
            dropvalue = val;
            expression.next();
        } else if (val instanceof CssPercentage) {
            dropvalue = val;
            expression.next();
        } else if (val instanceof CssLength) {
            dropvalue = val;
            expression.next();
        } else {
            throw new InvalidParamException("value", expression.getValue(),
                    getPropertyName(), ac);
        }
    }

    public CssDropInitialSize(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        if (((Css3Style) style).cssDropInitialSize != null)
            style.addRedefinitionWarning(ac, this);
        ((Css3Style) style).cssDropInitialSize = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style   The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css3Style) style).getDropInitialSize();
        } else {
            return ((Css3Style) style).cssDropInitialSize;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssDropInitialSize &&
                dropvalue.equals(((CssDropInitialSize) property).dropvalue));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
        return "drop-initial-size";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return dropvalue;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
        return dropvalue.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
        return dropvalue.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
        return dropvalue == auto;
    }

}
