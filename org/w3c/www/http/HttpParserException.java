// HttpParserException.java
// $Id$
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.www.http;

import org.w3c.www.mime.MimeParserException;

public class HttpParserException extends MimeParserException {

    public HttpParserException(String msg) {
        super(msg);
    }

}
