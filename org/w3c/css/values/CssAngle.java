//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT, ERCIM and Keio, 1997-2010.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.CssVersion;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;

/**
 * <H3>Angle</H3>
 * <p/>
 * <P>Angle units are used with aural cascading style sheets.
 * <p/>
 * <P>These are the legal angle units:
 * <p/>
 * <UL>
 * <LI>deg: degrees
 * <LI>grad: gradians
 * <LI>rad: radians
 * </UL>
 * <p/>
 * <p>Values in these units may be negative. They should be normalized to the
 * range 0-360deg by the UA. For example, -10deg and 350deg are equivalent.
 *
 * @version $Revision$
 */
public class CssAngle extends CssValue implements CssValueFloat {

    public static final int type = CssTypes.CSS_ANGLE;

    public final int getType() {
        return type;
    }

    Float value;
    String unit;

    static final String deg = "deg";
    static final String rad = "rad";
    static final String grad = "grad";
    static final String turn = "turn"; // CSS3
    static final String allowed_values[];

    static Float defaultValue = new Float(0);

    static {
        allowed_values = new String[4];
        allowed_values[0] = deg;
        allowed_values[1] = rad;
        allowed_values[2] = grad;
        allowed_values[3] = turn;
    }

    /**
     * Create a new CssAngle.
     */
    public CssAngle() {
        this(defaultValue);
    }

    /**
     * Create a new CssAngle
     */
    public CssAngle(float v) {
        this(new Float(v));
    }

    /**
     * Create a new CssAngle
     */
    public CssAngle(Float angle) {
        value = angle;
    }

    private String checkUnit(String unitString, ApplContext ac)
            throws InvalidParamException {
        for (String s : allowed_values) {
            if (s.matches(unitString)) {
                if ((s == turn) && (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0)) {
                    throw new InvalidParamException("unit", s, ac);
                }
                return s;
            }
        }
        throw new InvalidParamException("unit", unitString, ac);
    }

    /**
     * Set the value of this angle.
     *
     * @param s  The string representation of the angle
     * @param ac For errors and warnings reports
     * @throws InvalidParamException The unit is incorrect
     */
    public void set(String s, ApplContext ac) throws InvalidParamException {
        s = s.toLowerCase();
        int length = s.length();
        // by construction we should receive only valid strings with valid
        // units however let's check things...
        // a valid string should be at least [0-9]<3 letter units> so >= 4 chars
        if (length >= 4) {
            char c = s.charAt(length - 4);
            if (c == 't' || c == 'g') {
                // turn or grad
                unit = checkUnit(s.substring(length - 4), ac);
                value = Float.valueOf(s.substring(0, length - 4));
            } else {
                // others so deg, rad 3 letters long.
                unit = checkUnit(s.substring(length - 3), ac);
                value = Float.valueOf(s.substring(0, length - 3));
            }
        } else {
            throw new InvalidParamException("unit", s, ac);
        }
    }

    /**
     * Returns the current value
     */
    public Object get() {
        return value;
    }

    public float getValue() {
        return value.floatValue();
    }

    /**
     * Returns the current value
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (value.floatValue() != 0) {
            return Util.displayFloat(value) + getUnit();
        } else {
            // let's say that 0 is 0deg for sanity.
            return Util.displayFloat(value) + deg;
        }
    }

    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(Object value) {
        return (value instanceof CssAngle &&
                this.value.equals(((CssAngle) value).value) &&
                unit == ((CssAngle) value).unit);
    }

    private float normalize(float degree) {
        degree %= 360.f;
        if (degree < 0.f) {
            degree += 360.f;
        }
        return degree;
    }

    //@@FIXME I should return the remainder for all ...

    public float getDegree() {
        float angle = value.floatValue();
        if (unit == deg) {
            return normalize(angle);
        } else if (unit == rad) {
            return normalize(angle * (180.f / ((float) Math.PI)));
        } else if (unit == grad) {
            return normalize(angle * (9.f / 10.f));
        } else if (unit == turn) {
            return normalize(angle * 360.f);
        }

        System.err.println("[ERROR] in org.w3c.css.values.CssAngle");
        System.err.println("[ERROR] Please report (" + unit + ")");
        return (float) 0;
    }

    public boolean isDegree() {
        return unit == deg;
    }

    public boolean isGradian() {
        return unit == grad;
    }

    public boolean isRadian() {
        return unit == rad;
    }

    public boolean isTurn() {
        return unit == turn;
    }


}

