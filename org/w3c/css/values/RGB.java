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

public class RGB {
    static final String functionname = "rgb";

    private String output = null;
    private boolean percent = false;

    CssValue vr, vg, vb;

    public final void setRed(ApplContext ac, CssValue val)
            throws InvalidParamException {
        CssValue nv = val;
        output = null;
        if (val.getRawType() == CssTypes.CSS_CALC) {
            // TODO add warning about uncheckability
            // might need to extend...
        } else {
            if (val.getType() == CssTypes.CSS_NUMBER) {
                CssCheckableValue v = val.getCheckableValue();
                if (!v.warnPositiveness(ac, "RGB")) {
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
            } else if (val.getType() == CssTypes.CSS_PERCENTAGE) {
                CssCheckableValue v = val.getCheckableValue();
                if (!v.warnPositiveness(ac, "RGB")) {
                    CssNumber nb = new CssNumber();
                    nb.setIntValue(0);
                    nv = nb;
                }
                if (val.getRawType() == CssTypes.CSS_NUMBER) {
                    float p = ((CssNumber) val).getValue();
                    if (p > 100.) {
                        ac.getFrame().addWarning("out-of-range", Util.displayFloat(p));
                        CssNumber nb = new CssNumber();
                        nb.setIntValue(100);
                        nv = nb;
                    }
                }
            }
        }
        vr = nv;
    }

    public final void setGreen(ApplContext ac, CssValue val)
            throws InvalidParamException {
        CssValue nv = val;
        output = null;
        if (val.getRawType() == CssTypes.CSS_CALC) {
            // TODO add warning about uncheckability
            // might need to extend...
        } else {
            if (val.getType() == CssTypes.CSS_NUMBER) {
                CssCheckableValue v = val.getCheckableValue();
                if (!v.warnPositiveness(ac, "RGB")) {
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
            } else if (val.getType() == CssTypes.CSS_PERCENTAGE) {
                CssCheckableValue v = val.getCheckableValue();
                if (!v.warnPositiveness(ac, "RGB")) {
                    CssNumber nb = new CssNumber();
                    nb.setIntValue(0);
                    nv = nb;
                }
                if (val.getRawType() == CssTypes.CSS_NUMBER) {
                    float p = ((CssNumber) val).getValue();
                    if (p > 100.) {
                        ac.getFrame().addWarning("out-of-range", Util.displayFloat(p));
                        CssNumber nb = new CssNumber();
                        nb.setIntValue(100);
                        nv = nb;
                    }
                }
            }
        }
        vg = nv;
    }


    public final void setBlue(ApplContext ac, CssValue val)
            throws InvalidParamException {
        CssValue nv = val;
        output = null;
        if (val.getRawType() == CssTypes.CSS_CALC) {
            // TODO add warning about uncheckability
            // might need to extend...
        } else {
            if (val.getType() == CssTypes.CSS_NUMBER) {
                CssCheckableValue v = val.getCheckableValue();
                if (!v.warnPositiveness(ac, "RGB")) {
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
            } else if (val.getType() == CssTypes.CSS_PERCENTAGE) {
                CssCheckableValue v = val.getCheckableValue();
                if (!v.warnPositiveness(ac, "RGB")) {
                    CssNumber nb = new CssNumber();
                    nb.setIntValue(0);
                    nv = nb;
                }
                if (val.getRawType() == CssTypes.CSS_NUMBER) {
                    float p = ((CssNumber) val).getValue();
                    if (p > 100.) {
                        ac.getFrame().addWarning("out-of-range", Util.displayFloat(p));
                        CssNumber nb = new CssNumber();
                        nb.setIntValue(100);
                        nv = nb;
                    }
                }
            }
        }
        vb = nv;
    }

    /**
     * @return Returns the percent.
     */
    public final boolean isPercent() {
        return percent;
    }

    /**
     * @param percent The percent to set.
     */
    public final void setPercent(boolean percent) {
        this.percent = percent;
    }


    /**
     * Create a new RGB
     */
    public RGB() {
    }

    /**
     * Create a new RGB with default values
     *
     * @param r the red channel, an <EM>int</EM>
     * @param g the green channel, an <EM>int</EM>
     * @param b the blue channel, an <EM>int</EM>
     */
    public RGB(int r, int g, int b) {
        CssNumber n = new CssNumber();
        n.setIntValue(r);
        vr = n;
        n = new CssNumber();
        n.setIntValue(g);
        vg = n;
        n = new CssNumber();
        n.setIntValue(b);
        vb = n;
    }

    public boolean equals(RGB other) {
        if (other != null) {
            return (vr.equals(other.vr) && vg.equals(other.vg) && vb.equals(other.vb));
        }
        return false;
    }

    protected void setRepresentationString(String s) {
        output = s;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (output == null) {
            StringBuilder sb = new StringBuilder(functionname).append('(');
            if (isPercent()) {
                sb.append(vr).append("%, ");
                sb.append(vg).append("%, ");
                sb.append(vb).append("%)");
            } else {
                sb.append(vr).append(", ");
                sb.append(vg).append(", ");
                sb.append(vb).append(')');
            }
            output = sb.toString();
        }
        return output;
    }
}
