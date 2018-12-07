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

public class HSLA extends HSL {
    CssValue va;

    /**
     * Create a new HSLA
     */
    public HSLA() {
    }

    public void setAlpha(ApplContext ac, CssValue alpha)
            throws InvalidParamException {
        output = null;
        va = RGBA.filterAlpha(ac, alpha);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (output == null) {
            StringBuilder sb = new StringBuilder("hsl(");
            sb.append(vh).append(", ");
            sb.append(vs).append(", ");
            sb.append(vl).append(", ");
            sb.append(va).append(")");
            output = sb.toString();
        }
        return output;

    }
}
