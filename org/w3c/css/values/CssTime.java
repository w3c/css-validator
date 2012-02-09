//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import java.math.BigDecimal;

/**
 * <H3>Time</H3>
 * <p/>
 * <P>Time units are used with aural cascading style sheets.
 * <p/>
 * <P>These following are legal time units:
 * <p/>
 * <UL>
 * <LI>ms: milliseconds
 * <LI>s: seconds
 * </UL>
 * <p/>
 * <p>Time values may not be negative.
 *
 * @version $Revision$
 */
public class CssTime extends CssValue {

    public static final int type = CssTypes.CSS_TIME;

    public final int getType() {
        return type;
    }

    /**
     * Create a new CssTime.
     */
    public CssTime() {
        value = defaultValue;
    }

    /**
     * Create a new CssTime with a Float object.
     *
     * @param value the Float object
     */
    public CssTime(Float value) {
        this.value = new BigDecimal(value);
    }

    /**
     * Set the value of this time.
     *
     * @param ts  the string representation of the time.
     * @param ac For errors and warnings reports.
     * @throws InvalidParamException The unit is incorrect
     */
    public void set(String ts, ApplContext ac) throws InvalidParamException {
        String s = ts.toLowerCase();
        int length = s.length();
        String unit;

        if (s.charAt(length - 2) == 'm') {
            unit = s.substring(length - 2, length);
            this.value = new BigDecimal(s.substring(0, length - 2));
        } else {
            unit = s.substring(length - 1, length);
            this.value = new BigDecimal(s.substring(0, length - 1));
        }

        if (this.value.signum() == -1) {
            throw new InvalidParamException("negative-value",
                    this.value.toString(), ac);
        }

        this.unit = 1; // there is no unit by default

        if (!BigDecimal.ZERO.equals(this.value)) {
            int hash = unit.hashCode();
            int i = 0;
            while (i < units.length) {
                if (hash == hash_units[i]) {
                    this.unit = i;
                    return;
                }
                i++;
            }
        } else {
            return;
        }

        throw new InvalidParamException("unit", unit, ac);
    }

    /**
     * Returns the current value
     * Float
     * TODO move to a BigDecimal
     */
    public Object get() {
        if (unit == 1) {
            return new Float(value.floatValue() * 1000);
        }
        return value;
    }

    /**
     * Returns the current value
     */
    public String getUnit() {
        return units[unit];
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (BigDecimal.ZERO.equals(value)) {
            return value.toPlainString();
        }
        return value.toPlainString() + getUnit();
    }

    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(Object value) {
        return (value instanceof CssTime && this.value.equals(((CssTime) value).value) &&
                unit == ((CssTime) value).unit);
    }

    private BigDecimal value;
    private int unit;
    private static String[] units = {"ms", "s"};
    private static int[] hash_units;
    private static BigDecimal defaultValue = BigDecimal.ZERO;

    static {
        hash_units = new int[units.length];
        for (int i = 0; i < units.length; i++)
            hash_units[i] = units[i].hashCode();
    }
}

