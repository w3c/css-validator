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

public class HWB {
    String output = null;
    float fh;
    float fw = 100.f;
    float fb = 100.f;
    float fa = 100.f;
    boolean faSet = false;

    /**
     * Create a new HWB
     */
    public HWB() {
    }

    public void setHue(float hue) {
        this.fh = (float) ((((double) hue % 360.0) + 360.0) % 360.0);
    }

    public void setHue(CssNumber hue) {
        setHue(hue.getValue());
    }

    public void setWhiteness(float sat) {
        this.fw = sat;
    }

    public void setWhiteness(CssNumber sat) {
        setWhiteness(sat.getValue());
    }

    public void setBlackness(float light) {
        this.fb = light;
    }

    public void setBlackness(CssNumber light) {
        setBlackness(light.getValue());
    }

    public void setAlpha(float alpha) {
        this.fa = alpha;
        this.faSet = true;
    }

    public void setAlpha(CssNumber alpha) {
        setAlpha(alpha.getValue());
    }

    public void normalizeHW() {
        if (fw + fb > 100.f) {

        }

    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (output == null) {
            StringBuilder sb = new StringBuilder("hwb(");
            sb.append(Util.displayFloat(fh)).append(", ");
            sb.append(Util.displayFloat(fw)).append("%, ");
            sb.append(Util.displayFloat(fb));
            if (faSet) {
                sb.append("%)");
            } else {
                sb.append("%, ").append(Util.displayFloat(fa)).append(')');
            }
            output = sb.toString();
        }
        return output;
    }
}
