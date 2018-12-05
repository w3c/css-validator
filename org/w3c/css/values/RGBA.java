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

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;

public class RGBA extends RGB {
    static final String functionname = "rgba";

    private String output = null;
    String fname;

    CssValue va;

    public final void setAlpha(ApplContext ac, CssValue val)
            throws InvalidParamException {
        CssValue nv = val;
        output = null;
        if (val.getRawType() == CssTypes.CSS_CALC) {
            // TODO add warning about uncheckability
            // might need to extend...
        } else {
            if (val.getType() == CssTypes.CSS_NUMBER) {
                CssCheckableValue v = val.getCheckableValue();
                if (!v.warnPositiveness(ac, "RGBA")) {
                    CssNumber nb = new CssNumber();
                    nb.setIntValue(0);
                    nv = nb;
                }
                if (val.getRawType() == CssTypes.CSS_NUMBER) {
                    float p = ((CssNumber) val).getValue();
                    if (p > 255.) {
                        ac.getFrame().addWarning("out-of-range", Util.displayFloat(p));
                        CssNumber nb = new CssNumber();
                        nb.setIntValue(255);
                        nv = nb;
                    }
                }
            }
        }
        va = nv;
    }


    public boolean equals(RGBA other) {
        if (other != null) {
            return super.equals(other) && va.equals(other.va);
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
     *
     * @param r the red channel
     * @param g the green channel
     * @param b the blue channel
     * @param a the alpha channel
     */
    public RGBA(int r, int g, int b, float a) {
        super(r, g, b);
        CssNumber n = new CssNumber();
        n.setFloatValue(a);
        va = n;
        setPercent(false);
    }


    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (output == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(fname).append('(');
            if (isPercent()) {
                sb.append(vr).append("%, ");
                sb.append(vg).append("%, ");
                sb.append(vb).append("%, ");
            } else {
                sb.append(vr).append(", ");
                sb.append(vg).append(", ");
                sb.append(vb).append(", ");

            }
            sb.append(va).append(')');
            output = sb.toString();
        }
        return output;
    }
}
