/*
 * @(#)Attributes.java	1.1 95/04/23  
 *
 * Copyright (c) 1994 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies. Please refer to the file "copyright.html"
 * for further important copyright and licensing information.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package html.parser;

import java.util.StringTokenizer;

/**
 * An HTML attribute list. This is used instead of a Hashtable
 * because it is desirable to perserve the attribute order and
 * cloning must be very cheap.<p>
 *
 * The attribute value pairs are stored in a single array of
 * strings. The attribute names and values are stored alternating.
 * When an attribute list is cloned the actual values are shared
 * until the first time either attribute list is modified.
 *
 * @version 	1.1, 23 Apr 1995
 * @author Arthur van Hoff
 */
public
class Attributes {
    private static String nodata[] = new String[0];
    private String data[];
    private int len;
    private boolean cow;

    /**
     * Construct empty list.
     */
    public Attributes() {
	data = nodata;
    }

    /**
     * Construct from a string.
     */
    public Attributes(String attvals) {
	data = nodata;
	for (StringTokenizer e = new StringTokenizer(attvals, ",") ; e.hasMoreTokens() ; ) {
	    String str = e.nextToken();
	    int i = str.indexOf('=');
	    append(str.substring(0, i), str.substring(i + 1));
	}
    }

    /**
     * Return the length of the list.
     */
    public int length() {
	return len>>1;
    }

    /**
     * Get an attribute name.
     */
    public String getName(int i) {
	return data[i<<1];
    }

    /**
     * Get a value by index.
     */
    public String get(int i) {
	return data[(i<<1) + 1];
    }

    /**
     * Get an attribute value by name. Returns null
     * if the attribute was not found.
     */
    public synchronized String get(String name) {
	for (int i = 0 ; i < len ; i += 2) {
	    if (data[i].equals(name)) {
		return data[i+1];
	    }
	}
	return null;
    }

    /**
     * Change an attribute.
     */
    public synchronized void put(String name, String value) {
	for (int i = 0 ; i < len ; i += 2) {
	    if (data[i].equals(name)) {
		if (cow) {
		    String newdata[] = new String[len];
		    System.arraycopy(data, 0, newdata, 0, len);
		    data = newdata;
		    cow = false;
		}
		data[i + 1] = value;
		return;
	    }
	}
	append(name, value);
    }

      /**
      * Get an int attribute value.
      */
 
     public synchronized int getInt(String attrname, int def) 
 	throws NumberFormatException
     {
 	String value = get(attrname);
 	if ( value == null )
 	    return def;
 	return Integer.parseInt(value);
     }
 
    /**
     * Append an attribute value.
     */
    public synchronized void append(String name, String value) {
	if ((len == data.length) || cow) {
	    String newdata[] = new String[len + 8];
	    System.arraycopy(data, 0, newdata, 0, len);
	    data = newdata;
	    cow = false;
	}

	data[len++] = name;
	data[len++] = value;
    }

    /**
     * Clone this attribute list.
     */
    public synchronized Object clone() {
	Attributes atts = (Attributes)clone();
	atts.cow = cow = true;
	return atts;
    }

    /**
     * Convert to string. Very inefficient, use only for debugging.
     */
    public synchronized String toString() {
	String str = "";
	for (int i = 0 ; i < len ; i += 2) {
	    str += ((i > 0) ? " " : "") + data[i] + "=\"" + data[i+1] + "\"";
	}
	return str;
    }
}
