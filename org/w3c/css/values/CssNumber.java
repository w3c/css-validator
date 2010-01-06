//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

/**
 * A CSS float number.
 *
 * @version $Revision$
 */
public class CssNumber extends CssValue implements CssValueFloat {

    public static final int type = CssTypes.CSS_NUMBER;

    public final int getType() {
        return type;
    }

    ApplContext ac;
    Float value;
    boolean isInt = false;

    /**
     * Create a new CssNumber
     */
    public CssNumber() {
    }

    /**
     * Create a new CssNumber
     */
    public CssNumber(ApplContext ac, float value) {
        this.ac = ac;
        this.value = new Float(value);
    }

    public CssNumber(float value) {
        this.value = new Float(value);
    }

    /**
     * Set the value of this frequency.
     *
     * @param s  the string representation of the frequency.
     * @param ac For errors and warnings reports.
     */
    public void set(String s, ApplContext ac) {
        try {
            new Integer(s);
            isInt = true;
        } catch (NumberFormatException e) {
            isInt = false;
        } finally {
            value = new Float(s);
        }
        this.ac = ac;
    }

    /**
     * Returns the value
     */
    public Object get() {
        if (isInt) {
            return new Integer(value.intValue());
        }
        return value;
    }

    /**
     * Return the float value
     */
    public float getValue() {
        return value.floatValue();
    }

    public int getInt() throws InvalidParamException {
        if (isInt) {
            return value.intValue();
        } else {
            throw new InvalidParamException("invalid-color", ac);
        }
    }

    public boolean isInteger() {
        return isInt;
    }

    /**
     * Returns a length.
     * Only zero can be a length.
     *
     * @throws InvalidParamException The value is not zero
     */
    public CssLength getLength() throws InvalidParamException {
        float num = value.floatValue();
        if (num == 0) {
            return new CssLength();
        } else {
            throw new InvalidParamException("zero", "length", ac);
        }
    }

    /**
     * Returns a percentage.
     * Only zero can be a length.
     *
     * @throws InvalidParamException The value is not zero
     */
    public CssPercentage getPercentage() throws InvalidParamException {
        float num = value.floatValue();
        if (num == 0)
            return new CssPercentage();
        else {
            throw new InvalidParamException("zero",
                    value.toString(),
                    "percentage", ac);
        }
    }

    /**
     * Returns a time.
     * Only zero can be a length.
     *
     * @throws InvalidParamException The value is not zero
     */
    public CssTime getTime() throws InvalidParamException {
        float num = value.floatValue();
        if (num == 0)
            return new CssTime();
        else
            throw new InvalidParamException("zero", value.toString(),
                    "time", ac);
    }

    /**
     * Returns a angle.
     * Only zero can be a length.
     *
     * @throws InvalidParamException The value is not zero
     */
    public CssAngle getAngle() throws InvalidParamException {
        float num = value.floatValue();
        if (num == 0)
            return new CssAngle();
        else
            throw new InvalidParamException("zero", value.toString(),
                    "angle", ac);
    }

    /**
     * Returns a frequency.
     * Only zero can be a length.
     *
     * @throws InvalidParamException The value is not zero
     */
    public CssFrequency getFrequency() throws InvalidParamException {
        float num = value.floatValue();
        if (num == 0) {
            return new CssFrequency();
        } else {
            throw new InvalidParamException("zero",
                    value.toString(), "frequency", ac);
        }
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (isInt) {
            return value.intValue() + "";
        }
        return value.toString();
    }

    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(Object value) {
        return (value instanceof CssNumber &&
                this.value.equals(((CssNumber) value).value));
    }

}
