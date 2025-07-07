// HttpAcceptCharsetList.java
// $Id$
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.www.http;

import java.util.Vector;

public class HttpAcceptCharsetList extends HeaderValue {
    HttpAcceptCharset charsets[] = null;

    protected void parse() {
        Vector     vl = new Vector(4);
        ParseState ps = new ParseState(roff, rlen);
        ps.separator  = (byte) ',';
        ps.spaceIsSep = false;
        while ( HttpParser.nextItem(raw, ps) >= 0 ) {
            vl.addElement(new HttpAcceptCharset(this, raw, ps.start, ps.end));
            ps.prepare();
        }
        charsets = new HttpAcceptCharset[vl.size()];
        vl.copyInto(charsets);
    }

    protected void updateByteValue() {
        HttpBuffer buf = new HttpBuffer();
        if ( charsets != null ) {
            for (int i = 0 ; i < charsets.length ; i++) {
                if ( i > 0 )
                    buf.append(',');
                charsets[i].appendValue(buf);
            }
            raw  = buf.getByteCopy();
            roff = 0;
            rlen = raw.length;
        } else {
            raw  = new byte[0];
            roff = 0;
            rlen = 0;
        }
    }

    public Object getValue() {
        validate();
        return charsets;
    }

    public HttpAcceptCharsetList() {
        this.isValid = false;
    }

}
