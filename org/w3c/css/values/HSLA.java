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

public class HSLA {
    String output = null;
    float fh;
    float fs;
    float fl;
    float fa;

    /**
     * Create a new HSL
     */
    public HSLA() {
    }

    public void setHue(float hue) {
        this.fh = (float) ((((double) hue % 360.0) + 360.0) % 360.0);
    }

    public void setHue(CssNumber hue) {
        setHue(hue.getValue());
    }

    public void setSaturation(float sat) {
        this.fs = sat;
    }

    public void setSaturation(CssNumber sat) {
        setSaturation(sat.getValue());
    }

    public void setLightness(float light) {
        this.fl = light;
    }

    public void setLightness(CssNumber light) {
        setLightness(light.getValue());
    }

    public void setAlpha(float alpha) {
        this.fa = alpha;
    }

    public void setAlpha(CssNumber alpha) {
        setAlpha(alpha.getValue());
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (output == null) {
            StringBuilder sb = new StringBuilder("hsla(");
            sb.append(Util.displayFloat(fh)).append(", ");
            sb.append(Util.displayFloat(fs)).append("%, ");
            sb.append(Util.displayFloat(fl)).append("%, ");
            sb.append(Util.displayFloat(fa)).append(')');
            output = sb.toString();
        }
        return output;
    }
}
