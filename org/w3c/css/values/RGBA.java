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

public class RGBA {
    String output = null;
    Object r;
    Object g;
    Object b;
    Object a;
    
    /**
     * Create a new RGBA
     */
    public RGBA() {
    }
    
    /**
     * Create a new RGBA with default values
     */
    public RGBA(Object r, Object g, Object b, Object a) {
	this.r = r;
	this.g = g;
	this.b = b;
	this.a = a;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (output == null) {
	    return "rgba(" + r + ", " + g + ", " + b + ", " + a + ")";
	} else {
	    return output;
	}
    }
}