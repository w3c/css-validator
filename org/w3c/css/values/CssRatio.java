//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT, ERCIM and Keio, 1997-2010.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;

/**
 * @spec http://www.w3.org/TR/2010/CR-css3-mediaqueries-20100727/#values
 * @since CSS3
 */
public class CssRatio extends CssValue {

    public static final int type = CssTypes.CSS_RATIO;

    public final int getType() {
        return type;
    }

    float w;
    boolean isWInt;
    float h;
    boolean isHInt;


    /**
     * Create a new CssRatio.
     */
    public CssRatio() {
    }

    /**
     * Set the value of this ratio.
     *
     * @param s  the string representation of the ratio.
     * @param ac For errors and warnings reports.
     * @throws org.w3c.css.util.InvalidParamException
     *          (incorrect format)
     */
    public void set(String s, ApplContext ac) throws InvalidParamException {
        String sw, sh;
        s = s.toLowerCase();
        int slash = s.indexOf('/');

        if (slash == -1) {
            // invalid ratio
            throw new InvalidParamException("value",
                    s, ac);
        }
        // as we got spaces we need to trim the strings...
        sw = s.substring(0, slash).trim();
        sh = s.substring(slash + 1).trim();
        try {
            w = Integer.parseInt(sw);
            isWInt = true;
        } catch (NumberFormatException nex) {
            try {
                w = Float.parseFloat(sw);
            } catch (NumberFormatException ne) {
                // not an int, not a float... bail out
                throw new InvalidParamException("value", s, ac);
            }
        }
        // sanity check
        if (w <= 0.f) {
            throw new InvalidParamException("strictly-positive", s, ac);
        }

        try {
            h = Integer.parseInt(sh);
            isHInt = true;
        } catch (NumberFormatException nex) {
            try {
                h = Float.parseFloat(sh);
            } catch (NumberFormatException ne) {
                // not an int, not a float... bail out
                throw new InvalidParamException("value", s, ac);
            }
        }
        // sanity check
        if (h <= 0.f) {
            throw new InvalidParamException("strictly-positive", s, ac);
        }

    }

    /**
     * Returns the current value
     */
    public Object get() {
        return toString();
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((isWInt) ? Integer.toString((int) w) : Util.displayFloat(w));
        sb.append('/');
        sb.append((isHInt) ? Integer.toString((int) h) : Util.displayFloat(h));
        return sb.toString();
    }

    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(Object value) {
        try {
            CssRatio other = (CssRatio) value;
            // check that the ratio are the same
            return (Float.compare(w / h, other.w / other.h) == 0);
        } catch (ClassCastException cce) {
            return false;
        }
    }
}

