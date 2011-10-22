//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 25th 2000 Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.CssVersion;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;

/**
 * @since CSS3
 * @spec http://www.w3.org/TR/2010/CR-css3-mediaqueries-20100727/#values
 */
public class CssResolution extends CssValue {

    public static final int type = CssTypes.CSS_RESOLUTION;

    public final int getType() {
        return type;
    }

    private Float value;
    private String unit;
    boolean isInt;

    /**
     * Create a new CssResolution
     */
    public CssResolution() {
    }

    private void setValue(String s) {
        try {
            new Integer(s);
            isInt = true;
        } catch (NumberFormatException e) {
            isInt = false;
        } finally {
            value = new Float(s);
        }
    }

    /**
     * Set the value of this Resolution.
     *
     * @param s  the string representation of the Resolution.
     * @param ac For errors and warnings reports.
     * @throws InvalidParamException The unit is incorrect
     */
    public void set(String s, ApplContext ac) throws InvalidParamException {

        s = s.toLowerCase();
        int length = s.length();
        String unit = "";
        if (s.contains("dpi")) {
            unit = s.substring(length - 3, length);
            setValue(s.substring(0, length - 3));
            if (unit.equals("dpi")) {
                this.unit = unit;
            }
            return;
        } else if (s.contains("dpcm")) {
            unit = s.substring(length - 4, length);
            setValue(s.substring(0, length - 4));
            if (unit.equals("dpcm")) {
                this.unit = unit;
            }
            return;
        }

        if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
            throw new InvalidParamException("unit", unit, ac);
        }

        throw new InvalidParamException("unit", unit, ac);
    }

    /**
     * Returns the current value
     */
    public Object get() {
        if (isInt) {
            return new Integer(value.intValue());
        }
        return value;
    }

    /**
     * @return a float
     *
     */
    public float getFloatValue() {
        return value.floatValue();
    }

    /**
     * @return the current value
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isInt) {
           sb.append(Integer.toString(value.intValue()));
        } else {
            sb.append(Util.displayFloat(value));
        }
        sb.append(unit);
        return sb.toString();
    }

    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(Object value) {
        return (value instanceof CssResolution &&
                this.value.equals(((CssResolution) value).value) &&
                unit.equals(((CssResolution) value).unit));
    }
}

