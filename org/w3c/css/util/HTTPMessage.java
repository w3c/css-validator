/*
 * Copyright (c) 2000 World Wide Web Consortium,
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

package org.w3c.css.util;

import java.util.Hashtable;

/**
 * @version $Revision$
 * @author  Philippe Le Hegaret
 */
public class HTTPMessage {

    // HTTP message well-known headers
    public static int H_CACHE_CONTROL     = 0;
    public static int H_CONNECTION        = 1;
    public static int H_PROXY_CONNECTION  = 2;
    public static int H_DATE              = 3;
    public static int H_PRAGMA            = 4;
    public static int H_TRANSFER_ENCODING = 5;
    public static int H_UPGRADE           = 6;
    public static int H_VIA               = 7;

    public static int H_PROTOCOL          = 8;
    public static int H_PROTOCOL_REQUEST  = 9;
    public static int H_PROTOCOL_INFO     = 10;
    public static int H_PROTOCOL_QUERY    = 11;

    public static int H_SET_COOKIE        = 12;
    public static int H_COOKIE            = 13;

    public static int H_TRAILER           = 14;

    public static int H_MAN_EXT           = 15;
    public static int H_OPT_EXT           = 16;
    public static int H_CMAN_EXT          = 17;
    public static int H_COPT_EXT          = 18;

    public static int MAX_HEADERS = 61;

    protected static Hashtable	factory = new Hashtable(MAX_HEADERS);


    protected static String descriptors[] = new String[MAX_HEADERS];

    protected final static void registerHeader(int i, String name) {
	// no test for efficiency
        descriptors[i] = name;
	factory.put(name, new Tuple(name, i));
    }

    public static int getOffset(String name) {
	Tuple t = (Tuple) factory.get(name);
	return (t == null)? -1 : t.getOffset();
    }

    public static String getName(int offset) {
	// no test for efficiency
	return descriptors[offset];
    }

    static {
	registerHeader(H_CACHE_CONTROL,
		       "Cache-Control");
	registerHeader(H_CONNECTION,
		       "Connection");
	registerHeader(H_PROXY_CONNECTION,
		       "Proxy-Connection");
	registerHeader(H_DATE,
		       "Date");
	registerHeader(H_PRAGMA,
		       "Pragma");
        registerHeader(H_TRANSFER_ENCODING,
		       "Transfer-Encoding");
	registerHeader(H_UPGRADE,
		       "Upgrade");
	registerHeader(H_VIA,
		       "Via");
	registerHeader(H_TRAILER,
		       "Trailer");
	registerHeader(H_PROTOCOL,
		       "Protocol");
	registerHeader(H_PROTOCOL_REQUEST,
		       "Protocol-Request");
	registerHeader(H_PROTOCOL_QUERY,
		       "Protocol-Query");
	registerHeader(H_PROTOCOL_INFO,
		       "Protocol-Info");
	registerHeader(H_SET_COOKIE,
		       "Set-Cookie");
	registerHeader(H_COOKIE,
		       "Cookie");
	registerHeader(H_MAN_EXT,
		       "Man");
	registerHeader(H_OPT_EXT,
		       "Opt");
	registerHeader(H_CMAN_EXT,
		       "C-Man");
	registerHeader(H_COPT_EXT,
		       "C-Opt");
    }
}

class Tuple {
    String name    = null ;     // lower case name.
    int    offset  = -1;        // Offset of header in its message holder.

    Tuple(String name, int offset) {
	this.name   = name;
	this.offset = offset;
    }

    String getName() {
	return name;
    }

    int getOffset() {
	return offset;
    }
}
