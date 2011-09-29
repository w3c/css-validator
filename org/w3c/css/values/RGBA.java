/*
 * Copyright (c) 2001 World Wide Web Consortium,
 * (Massachusetts Institute of Technology, Institut National de
 * Recherche en Informatique et en Automatique, Keio University). All
 * Rights Reserved. This program is distributed under the W3C's Software
 * Intellectual Property License. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.
 * See W3C License http://www.w3.org/Consortium/Legal/ for more details.
 *
 * $Id$
 */
package org.w3c.css.values;

import org.w3c.css.util.Util;

public class RGBA {
    static final String functionname = "rgba";

    String fname;
    String output = null;
    int r, g, b;
    float fr, fg, fb, a;

    boolean percent = false;

    /**
     * @return Returns the percent.
     */
    public boolean isPercent() {
        return percent;
    }

    /**
     * @param percent The percent to set.
     */
    public void setPercent(boolean percent) {
        this.percent = percent;
    }

    public final void setRed(int r) {
        this.r = r;
        this.fr = r;
    }

    public final void setRed(float fr) {
        this.fr = fr;
    }

    public final void setGreen(int g) {
        this.g = g;
        this.fg = g;
    }

    public final void setGreen(float fg) {
        this.fg = fg;
    }

    public final void setBlue(int b) {
        this.b = b;
        this.fb = b;
    }

    public final void setBlue(float fb) {
        this.fb = fb;
    }

    public final void setAlpha(float a) {
        this.a = a;
    }

    public boolean equals(RGBA other) {
        if (other != null) {
            if (percent) {
                if (other.percent) {
                    return ((fr == other.fr) &&
                            (fg == other.fg) &&
                            (fb == other.fb) &&
                            (a == other.a));
                }
            } else {
                if (!other.percent) {
                    return ((r == other.r) &&
                            (g == other.g) &&
                            (b == other.b) &&
                            (a == other.a));
                }
            }
        }
        return false;
    }

    /**
     * Create a new RGBA
     */
    public RGBA() {
        fname = functionname;
    }

    /**
     * Create a RGBA and with a specific function name
     * (like astc-rgba http://www.atsc.org/cms/standards/a100/a_100_2.pdf #5.2.1.8.4.1
     */
    public RGBA(String fname) {
        this.fname = fname;
    }

    /**
     * Create a new RGBA with default values
     * @param r the red channel
     * @param g the green channel
     * @param b the blue channel
     * @param a the alpha channel
     */
    public RGBA(int r, int g, int b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.percent = false;
    }

    /**
     * @param r the red channel
     * @param g the green channel
     * @param b the blue channel
     * @param a the alpha channel
     * Create a new RGBA with default values
     */
    public RGBA(float r, float g, float b, float a) {
        this.fr = r;
        this.fg = g;
        this.fb = b;
        this.a = a;
        this.percent = true;
    }


    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (output == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(fname).append('(');
            if (isPercent()) {
                sb.append(Util.displayFloat(fr)).append("%, ");
                sb.append(Util.displayFloat(fg)).append("%, ");
                sb.append(Util.displayFloat(fb)).append("%, ");
            } else {
                sb.append(r).append(", ");
                sb.append(g).append(", ");
                sb.append(b).append(", ");
            }
            sb.append(Util.displayFloat(a)).append(')');
            output = sb.toString();
        }
        return output;
    }
}
