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

public class RGB {

    String output = null;
    int r, g, b;
    float fr, fg, fb;
    
    boolean percent = false;

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

    public final void setRed(int r) {
	this.r = r;
    }
    public final void setRed(float fr) {
	this.fr = fr;
    }

    public final void setGreen(int g) {
	this.g = g;
    }
    public final void setGreen(float gr) {
	this.fg = fg;
    }

    public final void setBlue(int b) {
	this.b = b;
    }
    public final void setBlue(float fb) {
	this.fb = fb;
    }

    /**
     * Create a new RGB
     */
    public RGB() {
    }

    /**
     * Create a new RGB with default values
     */
    public RGB(int r, int g, int b) {
	this.r = r;
	this.g = g;
	this.b = b;
    }
    
    public RGB(float fr, float fg, float fb) {
	this.fr = fr;
	this.fg = fg;
	this.fb = fb;
	percent = true;
    }

    public boolean equals(RGB other) {
	if (other != null) {
	    if (percent) {
		if (other.percent) {
		    return ((fr == other.fr) &&
			    (fg == other.fg) &&
			    (fb == other.fb));
		}
	    } else {
		if (!other.percent) {
		    return ((r == other.r) &&
			    (g == other.g) &&
			    (b == other.b));		    
		}
	    }
	}
	return false;
    }
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (output == null) {
	    StringBuilder sb = new StringBuilder("rgb(");
	    if (isPercent()) {
		sb.append(fr).append("%, ");
		sb.append(fg).append("%, ");
		sb.append(fb).append("%)");
	    } else {
		sb.append(r).append(", ");
		sb.append(g).append(", ");
		sb.append(b).append(')');
	    }
	    output = sb.toString();
	} 
	return output;
    }
}
