/*
 * @(#)Properties.java	1.9 95/06/21 Arthur van Hoff
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

package html;

import java.io.PrintStream;
import java.io.InputStream;
import java.util.*;

/**
 * Persistent properties class. Basically a hashtable that can
 * be saved/loaded from a stream. If a property is not found
 * a property list containing defaults is searched. This allows
 * arbitrary nesting.
 *
 * @author Arthur van Hoff
 * @version 	1.9, 21 Jun 1995
 */
public
class Properties extends Hashtable {
    protected Properties defaults;

    /**
     * Create an empty property list.
     */
    public Properties() {
	this(null);
    }

    /**
     * Create an empty property list with defaults.
     */
    public Properties(Properties defaults) {
	this.defaults = defaults;
    }

    /**
     * Load properties from an InputStream.
     * REMIND: Need to deal with \r, and \r\n
     */
    public synchronized void load(InputStream in) {
	int ch = in.read();
	while (true) {
	    switch (ch) {
	      case -1:
		return;

	      case '#':
	      case '!':
		do {
		    ch = in.read();
		} while ((ch >= 0) && (ch != '\n') && (ch != '\r'));
		continue;

	      case '\n':
	      case '\r':
	      case ' ':
	      case '\t':
		ch = in.read();
		continue;
	    }

	    // Read the key
	    StringBuffer key = new StringBuffer();
	    while ((ch >= 0) && (ch != '=') && (ch != ':') && 
		   (ch != ' ') && (ch != '\t') && (ch != '\n') && (ch != '\r')) {
		key.appendChar(ch);
		ch = in.read();
	    }
	    while ((ch == ' ') && (ch == '\t')) {
		ch = in.read();
	    }
	    if ((ch == '=') || (ch == ':')) {
		ch = in.read();
	    }
	    while ((ch == ' ') && (ch == '\t')) {
		ch = in.read();
	    }

	    // Read the value
	    StringBuffer val = new StringBuffer();
	    while ((ch >= 0) && (ch != '\n') && (ch != '\r')) {
		if (ch == '\\') {
		    switch (ch = in.read()) {
		      case '\n': 
			  while (((ch = in.read()) == ' ') || (ch == '\t'));
			  continue;
		      case 't': ch = '\t'; break;
		      case 'n': ch = '\n'; break;
		      case 'r': ch = '\r'; break;
		      default:
			if ((ch >= '0') || (ch <= '7')) {
			    ch = ((ch - '0') << 6) | ((in.read() - '0') << 6) | (in.read() - '0');
			}
		    }
		}
		val.appendChar(ch);
		ch = in.read();
	    }

	    //System.out.println(key + " = '" + val + "'");
	    put(key.toString(), val.toString());
	}
    }

    /**
     * Get a property. If the key is not found in this
     * property list, try the defaults. Return null if
     * the property is not found.
     */
    public String getProperty(String key) {
	String val = (String)super.get(key);
	return ((val == null) && (defaults != null)) ? defaults.getProperty(key) : val;
    }

    /**
     * Get a property. If the key is not found in this
     * property list, try the defaults. Return def if
     * the property is not found.
     */
    public String getProperty(String key, String def) {
	String val = getProperty(key);
	return (val == null) ? def : val;
    }

    /**
     * Enumerate all the keys.
     */
    public Enumeration propertyNames() {
	Hashtable h = new Hashtable();
	enumerate(h);
	return h.keys();
    }

    /**
     * List properties, for debugging
     */
    public void list(PrintStream out) {
	out.println("-- listing properties --");
	Hashtable h = new Hashtable();
	enumerate(h);
	for (Enumeration e = h.keys() ; e.hasMoreElements() ;) {
	    String key = (String)e.nextElement();
	    String val = (String)h.get(key);
	    if (val.length() > 40) {
		val = val.substring(37) + "...";
	    }
	    out.println(key + "=" + val);
	}
	
    }
    
    /**
     * Enumerate all key/value pairs.
     */
    private synchronized void enumerate(Hashtable h) {
	if (defaults != null) {
	    defaults.enumerate(h);
	}
	for (Enumeration e = keys() ; e.hasMoreElements() ;) {
	    String key = (String)e.nextElement();
	    h.put(key, get(key));
	}
    }
}
