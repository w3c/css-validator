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

/**
 * @version $Revision$
 * @author  Philippe Le Hegaret
 */

package org.w3c.css.util;

public class HTTPReplyMessage extends HTTPEntityMessage {
    // HTTP Reply message well-known headers
    public static int H_ACCEPT_RANGES       = 50;
    public static int H_AGE                 = 51;
    public static int H_LOCATION            = 52;
    public static int H_PROXY_AUTHENTICATE  = 53;
    public static int H_PUBLIC              = 54;
    public static int H_RETRY_AFTER         = 55;
    public static int H_SERVER              = 56;
    public static int H_VARY                = 57;
    public static int H_WARNING             = 58;
    public static int H_WWW_AUTHENTICATE    = 59;
    public static int H_AUTHENTICATION_INFO = 60;
    
    static {
	registerHeader(H_ACCEPT_RANGES,
		       "Accept-Ranges");
	registerHeader(H_AGE,
		       "Age");
	registerHeader(H_LOCATION,
		       "Location");
	registerHeader(H_PROXY_AUTHENTICATE,
		       "Proxy-Authenticate");
	registerHeader(H_PUBLIC,
		       "Public");
        // String as it can be a number or a date...
	registerHeader(H_RETRY_AFTER,
		       "Retry-After");
	registerHeader(H_SERVER,
		       "Server");
	registerHeader(H_TRAILER,
		       "Trailer");
	registerHeader(H_VARY,
		       "Vary");
	registerHeader(H_WARNING,
		       "Warning");
	registerHeader(H_WWW_AUTHENTICATE,
		       "WWW-Authenticate");
	registerHeader(H_AUTHENTICATION_INFO,
		       "Authentication-Info");
    }
}
