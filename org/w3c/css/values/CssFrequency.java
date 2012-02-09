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
 * <H3> Frequencies</H3>
 * <p/>
 * <P>Frequency units are used with aural cascading style sheets.
 * <p/>
 * <p>There are two legal frequency units:
 * <p/>
 * <ul>
 * <li>Hz: Hertz
 * <li>kHz: kilo Hertz
 * </ul>
 * <p/>
 * <P> For example, 200Hz is a bass sound, and 6kHz is a treble sound.
 *
 * @version $Revision$
 */
public class CssFrequency extends CssValue {

    public static final int type = CssTypes.CSS_FREQUENCY;

    public final int getType() {
        return type;
    }

    private BigDecimal value;
    private int unit;
    private static String[] units = {"Hz", "kHz"};
    private static int[] hash_units;
    private static BigDecimal defaultValue = BigDecimal.ZERO;

    static {
        hash_units = new int[units.length];
        for (int i = 0; i < units.length; i++)
            hash_units[i] = units[i].toLowerCase().hashCode();
    }

    /**
     * Create a new CssFrequency
     */
    public CssFrequency() {
        value = defaultValue;
    }

    /**
     * Create a new CssFrequency with a float number.
     *
     * @param value the float number.
     */
    public CssFrequency(BigDecimal value) {
        this.value = value;
    }

    /**
     * Set the value of this frequency.
     *
     * @param s  the string representation of the frequency.
     * @param ac For errors and warnings reports.
     * @throws InvalidParamException The unit is incorrect
     */
    public void set(String s, ApplContext ac) throws InvalidParamException {
        s = s.toLowerCase();
        int length = s.length();
        String unit;
        BigDecimal v;
        if (s.charAt(length - 3) == 'k') {
            unit = s.substring(length - 3, length);
            v = new BigDecimal(s.substring(0, length - 3));
        } else {
            unit = s.substring(length - 2, length);
            v = new BigDecimal(s.substring(0, length - 2));
        }
        int hash = unit.hashCode();


        int i = 0;
        while (i < units.length) {
            if (hash == hash_units[i]) {
                this.unit = i;
                break;
            }
            i++;
        }

        if (i == units.length) {
            throw new InvalidParamException("unit", unit, ac);
        }

        this.value = v;

    }

    /**
     * Returns the current value
     */
    public Object get() {
        // TODO FIXME should not be a Float...
        if (unit == 1) {
            return new Float(value.floatValue() * 1000);
        }
        return value.floatValue();
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
        return (value instanceof CssFrequency
                && this.value.equals(((CssFrequency) value).value)
                && unit == ((CssFrequency) value).unit);
    }


}

