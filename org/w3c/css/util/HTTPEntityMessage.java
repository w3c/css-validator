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

public class HTTPEntityMessage extends HTTPMessage {
    
    // HTTP Entity message well-known headers
    public static int H_ALLOW               = 19;
    public static int H_CONTENT_LENGTH      = 20;
    public static int H_CONTENT_BASE        = 21;
    public static int H_CONTENT_ENCODING    = 22;
    public static int H_CONTENT_LANGUAGE    = 23;
    public static int H_CONTENT_LOCATION    = 24;
    public static int H_CONTENT_MD5         = 25;
    public static int H_CONTENT_RANGE       = 26;
    public static int H_CONTENT_TYPE        = 27;
    public static int H_ETAG                = 28;
    public static int H_EXPIRES             = 29;
    public static int H_LAST_MODIFIED       = 30;
    
    static {
	registerHeader(H_ALLOW,
		       "Allow");
	registerHeader(H_CONTENT_LENGTH,
		       "Content-Length");
	registerHeader(H_CONTENT_BASE,
		       "Content-Base");
	registerHeader(H_CONTENT_ENCODING,
		       "Content-Encoding");
	registerHeader(H_CONTENT_LANGUAGE,
		       "Content-Language");
	registerHeader(H_CONTENT_LOCATION,
		       "Content-Location");
	registerHeader(H_CONTENT_MD5,
		       "Content-Md5");
	registerHeader(H_CONTENT_RANGE,
		       "Content-Range");
	registerHeader(H_CONTENT_TYPE,
		       "Content-Type");
	registerHeader(H_ETAG,
		       "Etag");
	registerHeader(H_EXPIRES,
		       "Expires");
	registerHeader(H_LAST_MODIFIED,
		       "Last-Modified");
    }
}
