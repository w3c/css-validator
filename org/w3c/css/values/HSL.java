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

public class HSL {
	String output = null;
	Object h;
	Object s;
	Object l;

    /**
     * Create a new HSL
     */
    public HSL() {
    }

    /**
     * Create a new HSL with default values
     */
    public HSL(Object h, Object s, Object l) {
		this.h = h;
		this.s = s;
		this.l = l;
	}

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
		if (output == null) {
		    return "hsl("+h+","+s+","+l+")";
		} else {
		    return output;
		}
    }
}