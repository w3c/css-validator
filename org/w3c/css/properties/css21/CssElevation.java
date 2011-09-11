//
// $Id$
//
// (c) COPYRIGHT MIT, ERCIM and Keio University 2011
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css21;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssAngle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;


/**
 * @version $Revision$
 * @spec http://www.w3.org/TR/2011/REC-CSS2-20110607/aural.html#propdef-elevation
 */
public class CssElevation extends org.w3c.css.properties.css.CssElevation {

    CssIdent identValue;
    CssAngle angleValue;

    private static int[] hash_values;
    private static CssIdent defaultValue;
    private static String[] elValues = {"below", "level", "above",
            "highter", "lower"};


    static {
        defaultValue = new CssIdent("level");
        hash_values = new int[elValues.length];
        for (int i = 0; i < elValues.length; i++)
            hash_values[i] = elValues[i].hashCode();
    }

    /**
     * Create a new ACssElevation
     */
    public CssElevation() {
    }

    // check that the ident is a valid one
    private boolean checkIdent(CssIdent ident) {
        int hash = ident.hashCode();
        for (int h : hash_values) {
            if (h == hash) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new ACssElevation
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorrect
     */
    public CssElevation(ApplContext ac, CssExpression expression,
                        boolean check) throws InvalidParamException {
        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }

        CssValue val = expression.getValue();
        //int index;
        setByUser();

        switch (val.getType()) {
            case CssTypes.CSS_IDENT:
                CssIdent ident = (CssIdent) val;
                if (inherit.equals(ident)) {
                    identValue = inherit;
                    expression.next();
                    return;
                }
                if (checkIdent(ident)) {
                    identValue = ident;
                    expression.next();
                    return;
                }
                throw new InvalidParamException("unrecognize", ac);
            case CssTypes.CSS_ANGLE:
                angleValue = (CssAngle) val;
                float v = angleValue.getDegree();
                if (v > 90 && v < 270) {
                    throw new InvalidParamException("elevation.range", ac);
                }
                expression.next();
                return;
        }

        throw new InvalidParamException("value",
                expression.getValue().toString(),
                getPropertyName(), ac);
    }

    public CssElevation(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        if (identValue != null) {
            return identValue;
        }
        return angleValue;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
        return inherit.equals(identValue);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (identValue != null) {
            return identValue.toString();
        }
        return angleValue.toString();
    }


    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
        CssElevation other;
        try {
            other = (CssElevation) property;
        } catch (ClassCastException cc) {
            return false;
        }
        if (identValue != null) {
            return ((angleValue == null) && (other.angleValue == null) &&
                    (identValue.equals(other.identValue)));
        }
        return ((other.identValue == null) &&
                (angleValue.equals(other.angleValue)));
    }


    private CssIdent checkIdent(ApplContext ac, CssIdent ident)
            throws InvalidParamException {
        int hash = ident.hashCode();
        for (int i = 0; i < elValues.length; i++) {
            if (hash_values[i] == hash) {
                return ident;
            }
        }

        throw new InvalidParamException("value", ident.toString(),
                getPropertyName(), ac);
    }
}

