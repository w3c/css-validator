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

public class HTTPRequestMessage extends HTTPEntityMessage {

    // HTTP Request message well-known headers
    public static int H_ACCEPT              = 31;
    public static int H_ACCEPT_CHARSET      = 32;
    public static int H_ACCEPT_ENCODING     = 33;
    public static int H_ACCEPT_LANGUAGE     = 34;
    public static int H_AUTHORIZATION       = 35;
    public static int H_EXPECT              = 36;
    public static int H_FROM                = 37;
    public static int H_HOST                = 38;
    public static int H_IF_MODIFIED_SINCE   = 39;
    public static int H_IF_MATCH            = 40;
    public static int H_IF_NONE_MATCH       = 41;
    public static int H_IF_RANGE            = 42;
    public static int H_IF_UNMODIFIED_SINCE = 43;
    public static int H_MAX_FORWARDS        = 44;
    public static int H_PROXY_AUTHORIZATION = 45;
    public static int H_RANGE               = 46;
    public static int H_REFERER             = 47;
    public static int H_TE                  = 48;
    public static int H_USER_AGENT          = 49;

    static {
	registerHeader(H_ACCEPT,
		       "Accept");
	registerHeader(H_ACCEPT_CHARSET,
		       "Accept-Charset");
	registerHeader(H_ACCEPT_ENCODING,
		       "Accept-Encoding");
	registerHeader(H_ACCEPT_LANGUAGE,
		       "Accept-Language");
	registerHeader(H_AUTHORIZATION,
		       "Authorization");
	registerHeader(H_FROM,
		       "From");
	registerHeader(H_HOST,
		       "Host");
	registerHeader(H_IF_MODIFIED_SINCE,
		       "If-Modified-Since");
	registerHeader(H_IF_MATCH,
		       "If-Match");
	registerHeader(H_IF_NONE_MATCH,
		       "If-None-Match");
	registerHeader(H_IF_RANGE,
		       "If-Range");
	registerHeader(H_IF_UNMODIFIED_SINCE,
		       "If-Unmodified-Since");
	registerHeader(H_MAX_FORWARDS,
		       "Max-Forwards");
	registerHeader(H_PROXY_AUTHORIZATION,
		       "Proxy-Authorization");
	registerHeader(H_RANGE,
		       "Range");
	registerHeader(H_REFERER,
		       "Referer");
	registerHeader(H_USER_AGENT,
		       "User-Agent");
	registerHeader(H_EXPECT,
		       "Expect");
	registerHeader(H_TE,
		       "TE");
    }

}
