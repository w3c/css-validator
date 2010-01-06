//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT, ERCIM and Keio, 1997-2010.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

/**
 * <H3>Date</H3>
 * <p/>
 * Legal format is dd/mm/yyyy as in SSML
 *
 * @version $Revision$
 */
public class CssDate extends CssValue {

    public static final int type = CssTypes.CSS_DATE;

    public final int getType() {
        return type;
    }

    String day = "";
    String month = "";
    String year = "";

    /**
     * Create a new CssDate.
     */
    public CssDate() {
    }

    /**
     * Set the value of this date.
     *
     * @param s  the string representation of the time.
     * @param ac For errors and warnings reports.
     * @throws InvalidParamException The date format is incorrect
     */
    public void set(String s, ApplContext ac) throws InvalidParamException {
        s = s.toLowerCase();
        int first_slash = s.indexOf('/');
        int last_slash;

        if (first_slash == -1) {
            // invalid date
            throw new InvalidParamException("value",
                    s, ac);
        }
        last_slash = s.lastIndexOf('/');
        if (first_slash == last_slash) {
            // only one / in date is invalid date
            throw new InvalidParamException("value",
                    s, ac);
        }

        day = s.substring(0, first_slash - 1);
        month = s.substring(first_slash, last_slash - 1);
        year = s.substring(last_slash);

        int d, m, y;

        try {
            d = Integer.parseInt(day);
            m = Integer.parseInt(month);
            y = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            throw new InvalidParamException("value", s, ac);
        }

        if (d > 31 || d <= 0 || m > 12 || m <= 0 || y < 0) {
            throw new InvalidParamException("value", s, ac);
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
        StringBuilder sb = new StringBuilder(day);
        sb.append('/').append(month).append('/').append(year);
        return sb.toString();
    }

    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(Object value) {
        return (value instanceof CssDate &&
                this.day.equals(((CssDate) value).day) &&
                this.month.equals(((CssDate) value).month)
                && this.year.equals(((CssDate) value).year));
    }

}

