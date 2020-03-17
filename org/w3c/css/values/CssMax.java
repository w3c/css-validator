//
// @author Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2020.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * CSS max().
 *
 * @spec https://www.w3.org/TR/2019/WD-css-values-4-20190131/#funcdef-max
 */
public class CssMax extends CssCheckableValue {

    public static final int type = CssTypes.CSS_MAX;

    public final int getRawType() {
        return type;
    }

    public final int getType() {
        if (computed_type == CssTypes.CSS_MAX) {
            return type;
        }
        return computed_type;
    }

    ApplContext ac;
    int computed_type = CssTypes.CSS_UNKNOWN;
    ArrayList<CssValue> values = null;
    String _toString = null;


    /**
     * Create a new CssCalc
     */
    public CssMax() {
    }

    public CssMax(ApplContext ac) {
        this(ac, null);
    }

    public CssMax(CssValue value) {
        this(null, value);
    }

    public CssMax(ApplContext ac, CssValue value) {
        if (ac != null) {
            this.ac = ac;
        }
        if (value != null) {
            computed_type = value.getType();
            if (values == null) {
                values = new ArrayList<>();
            }
            values.add(value);
            try {
                computed_type = _checkAcceptableType(value.getType());
            } catch (Exception ex) { // todo report error here or wait ?
            }
        }
    }

    public void set(String s, ApplContext ac) throws InvalidParamException {
        // we don't support this way of setting the value
        // as we rely on the parsing to create it incrementally
        throw new InvalidParamException("unrecognize", s, ac);
    }

    public void setValue(BigDecimal d) {
        // we don't support this way of setting the value
        // as we rely on the parsing to create it incrementally
    }

    /**
     * Add one operand, if we already got one we will... Add one operand.
     *
     * @param value
     * @return
     */
    public CssMax addValue(CssValue value)
            throws InvalidParamException {
        boolean first = false;
        if (values == null) {
            values = new ArrayList<>();
            first = true;
        }
        values.add(value);
        _computeResultingType(false);
        return this;
    }

    public void validate() throws InvalidParamException {
        _computeResultingType(true);
    }

    private int _checkAcceptableType(int type)
            throws InvalidParamException {
        //  <length>, <frequency>, <angle>, <time>, <number>, or <integer>
        if (type != CssTypes.CSS_PERCENTAGE &&
                type != CssTypes.CSS_LENGTH &&
                type != CssTypes.CSS_NUMBER &&
                type != CssTypes.CSS_ANGLE &&
                type != CssTypes.CSS_FREQUENCY &&
                type != CssTypes.CSS_TIME) {
            throw new InvalidParamException("invalidtype", toStringUnprefixed(), ac);
        }
        return type;
    }

    private void _computeResultingType(boolean first)
            throws InvalidParamException {
        int valtype = CssTypes.CSS_MAX;
        boolean firstVal = true;
        CssValue prevVal = null;

        for (CssValue v : values) {
            if (firstVal) {
                valtype = v.getType();
                _checkAcceptableType(valtype);
                computed_type = valtype;
                firstVal = false;
                prevVal = v;
            } else {
                if (valtype == v.getType()) {
                    prevVal = v;
                    continue;
                }
                if (valtype == CssTypes.CSS_PERCENTAGE) {
                    valtype = _checkAcceptableType(v.getType());
                    prevVal = v;
                    continue;
                }
                if (v.getType() == CssTypes.CSS_PERCENTAGE) {
                    continue;
                }
                if (valtype == CssTypes.CSS_NUMBER && prevVal.getNumber().isZero()) {
                    valtype = _checkAcceptableType(v.getType());
                    prevVal = v;
                    continue;
                }
                if (v.getType() == CssTypes.CSS_NUMBER && v.getNumber().isZero()) {
                    continue;
                }
                throw new InvalidParamException("incompatibletypes", toStringUnprefixed(), ac);
            }
        }
        computed_type = valtype;
    }

    /**
     * Returns the value
     */

    public Object get() {
        return toString();
    }

    protected String toStringUnprefixed() {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (CssValue v : values) {
            if (!isFirst) {
                sb.append(", ");
            } else {
                isFirst = false;
            }
            sb.append(v);
        }
        return sb.toString();
    }

    public String toString() {
        if (_toString == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("max(").append(toStringUnprefixed()).append(')');
            _toString = sb.toString();
        }
        return _toString;
    }


    public boolean isInteger() {
        return false;
    }

    /**
     * Returns true is the value is positive of null
     *
     * @return a boolean
     */
    public boolean isPositive() {
        // TODO do our best...
        return false;
    }

    /**
     * Returns true is the value is positive of null
     *
     * @return a boolean
     */
    public boolean isStrictlyPositive() {
        return false;
        // TODO do our best...
    }

    /**
     * Returns true is the value is zero
     *
     * @return a boolean
     */
    public boolean isZero() {
        // TODO do our best...
        return false;
    }


    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(Object value) {
        if (!(value instanceof CssMax)) {
            return false;
        }
        CssMax other = (CssMax) value;
        boolean match;
        // this is inherently wrong, as we should check only the min value, but in that case we
        // would need to explicitly compute them which is not done.
        for (CssValue v : this.values) {
            match = false;
            for (CssValue ov : other.values) {
                if (v.equals(ov)) {
                    match = true;
                    break;
                }
            }
            if (!match) {
                return false;
            }
        }
        return true;
    }

    /**
     * check if the value is positive or null
     *
     * @param ac         the validation context
     * @param callername the property the value is defined in
     * @throws InvalidParamException
     */
    public void checkPositiveness(ApplContext ac, String callername)
            throws InvalidParamException {
        // TODO do our best...
        if (false /*!isPositive()*/) {
            throw new InvalidParamException("negative-value",
                    toString(), callername, ac);
        }
    }

    /**
     * check if the value is strictly positive
     *
     * @param ac         the validation context
     * @param callername the property the value is defined in
     * @throws InvalidParamException
     */
    public void checkStrictPositiveness(ApplContext ac, String callername)
            throws InvalidParamException {
        // TODO do our best...
        if (false/*!isStrictlyPositive()*/) {
            throw new InvalidParamException("strictly-positive",
                    toString(), callername, ac);
        }
    }

    /**
     * check if the value is an integer
     *
     * @param ac         the validation context
     * @param callername the property the value is defined in
     * @throws InvalidParamException
     */
    public void checkInteger(ApplContext ac, String callername)
            throws InvalidParamException {
        // TODO do our best...
        if (false/*!isInteger()*/) {
            throw new InvalidParamException("integer",
                    toString(), callername, ac);
        }
    }

    /**
     * warn if the value is not positive or null
     *
     * @param ac         the validation context
     * @param callername the property the value is defined in
     */
    public boolean warnPositiveness(ApplContext ac, String callername) {
        // TODO do our best...
        if (false/*!isPositive()*/) {
            ac.getFrame().addWarning("negative", toString());
            return false;
        }
        return true;
    }

    public CssLength getLength() throws InvalidParamException {
        if (computed_type == CssTypes.CSS_LENGTH) {
            for (CssValue v : values) {
                if (v.getType() == CssTypes.CSS_LENGTH) {
                    return v.getLength();
                }
            }
        }
        throw new ClassCastException("unknown");
    }

    public CssPercentage getPercentage() throws InvalidParamException {
        if (computed_type == CssTypes.CSS_PERCENTAGE) {
            for (CssValue v : values) {
                if (v.getType() == CssTypes.CSS_PERCENTAGE) {
                    return v.getPercentage();
                }
            }
        }
        throw new ClassCastException("unknown");
    }

    public CssNumber getNumber() throws InvalidParamException {
        if (computed_type == CssTypes.CSS_NUMBER) {
            for (CssValue v : values) {
                if (v.getType() == CssTypes.CSS_NUMBER) {
                    return v.getNumber();
                }
            }
        }
        throw new ClassCastException("unknown");
    }

    public CssTime getTime() throws InvalidParamException {
        if (computed_type == CssTypes.CSS_TIME) {
            for (CssValue v : values) {
                if (v.getType() == CssTypes.CSS_TIME) {
                    return v.getTime();
                }
            }
        }
        throw new ClassCastException("unknown");
    }

    public CssAngle getAngle() throws InvalidParamException {
        if (computed_type == CssTypes.CSS_ANGLE) {
            for (CssValue v : values) {
                if (v.getType() == CssTypes.CSS_ANGLE) {
                    return v.getAngle();
                }
            }
        }
        throw new ClassCastException("unknown");
    }

    public CssFrequency getFrequency() throws InvalidParamException {
        if (computed_type == CssTypes.CSS_FREQUENCY) {
            for (CssValue v : values) {
                if (v.getType() == CssTypes.CSS_FREQUENCY) {
                    return v.getFrequency();
                }
            }
        }
        throw new ClassCastException("unknown");
    }

    /**
     * check if the value is equal to zero
     *
     * @param ac         the validation context
     * @param callername the property the value is defined in
     * @throws InvalidParamException
     */
    public void checkEqualsZero(ApplContext ac, String callername)
            throws InvalidParamException {
        // we can't check so we only warn.
        // TODO should we do that only for CSS_NUMBER type?
        warnEqualsZero(ac, callername);
    }

    /**
     * warn if the value is not zero
     *
     * @param ac         the validation context
     * @param callername the property the value is defined in
     */
    public boolean warnEqualsZero(ApplContext ac, String callername) {
        // TODO should we do that only for CSS_NUMBER type?
        if (!isZero()) {
            ac.getFrame().addWarning("dynamic", toString());
            return false;
        }
        return true;
    }
}
