// HttpParser.java
// $Id$
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.www.http;

/**
 * A private class to help with the parsing.
 * Contains only some static method, helping to parse various byte
 * buffers into Java object (Yes, I am still and again trying to reduce 
 * memory consumption).
 * <p>I don't know wether this sucks or not. One hand I am sparing a tremedous
 * amount of Strings creation, on the other end I am recoding a number of
 * parsers that are available on String instances.
 */

public class HttpParser {
    private static final boolean debug = false;

    /**
     * Emit an error.
     * @param mth The method trigerring the error.
     * @param msg An associated message.
     * @exception HttpInvalidValueException To indicate the error to caller.
     */

    protected static void error(String mth, String msg)
            throws HttpInvalidValueException
    {
        throw new HttpInvalidValueException(mth+": "+msg);
    }

    /**
     * Skip leading LWS, <em>not</em> including CR LF.
     * Update the input offset, <em>after</em> any leading space.
     * @param buf The buffer to be parsed.
     * @param ptr The buffer pointer to be updated on return.
     * @return The potentially advanced buffer input offset.
     */

    public static final int skipSpaces(byte buf[], ParseState ps) {
        int len = (ps.bufend > 0) ? ps.bufend : buf.length;
        int off = ps.ioff;
        while (off < len) {
            if ((buf[off] != (byte) ' ')
                    && (buf[off] != (byte) '\t')
                    && (buf[off] != (byte) ps.separator)) {
                ps.ioff = off;
                return off;
            }
            off++;
        }
        return off;
    }

    /**
     * Parse list of items, taking care of quotes and optional LWS.
     * The output offset points to the <em>next</em> element of the list.
     * @eturn The starting location (i.e. <code>ps.start</code> value), or
     * <strong>-1</strong> if no item available (end of list).
     */

    public static final int nextItem(byte buf[], ParseState ps) {
        // Skip leading spaces, if needed:
        int off = -1;
        int len = -1;
        if ( ps.isSkipable )
            ps.start = off = skipSpaces(buf, ps) ;
        else
            ps.start = off = ps.ioff ;
        len = (ps.bufend > 0) ? ps.bufend : buf.length;
        if ( debug )
            System.out.println("parsing: ["+new String(buf, 0, off, len-off)+
                    "]");
        // Parse !
        if ( off >= len )
            return -1;
        // Setup for parsing, and parse
        ps.start = off;
        loop:
        while (off < len) {
            if ( buf[off] == (byte) '"' ) {
                // A quoted item, read as one chunk
                off++;
                while (off < len ) {
                    if (buf[off] == (byte) '\\') {
                        off += 2;
                    } else if (buf[off] == (byte) '"') {
                        off++;
                        continue loop;
                    } else {
                        off++;
                    }
                }
                if ( off == len )
                    error("nextItem", "Un-terminated quoted item.");
            } else if ((buf[off] == ps.separator)
                    || (ps.spaceIsSep
                    && ((buf[off] == ' ') || (buf[off] == '\t')))) {
                break loop;
            }
            off++;
        }
        ps.end = off;
        // Item start is set, we are right at the end of item
        if ( ps.isSkipable ) {
            ps.ioff = off ;
            ps.ooff = skipSpaces(buf, ps);
        }
        // Check for either the end of the list, or the separator:
        if (ps.ooff < ps.bufend) {
            if (buf[ps.ooff] == (byte) ps.separator)
                ps.ooff++;
        }
        if ( debug )
            System.out.println("nextItem = ["+new String(buf, 0, ps.start,
                    ps.end-ps.start)+"]");
        return (ps.end > ps.start) ? ps.start : -1;
    }

    public static double parseQuality(byte buf[], ParseState ps) {
        // Skip spaces if needed
        int off = -1;
        if ( ps.isSkipable )
            ps.start = off = skipSpaces(buf, ps);
        else
            ps.start = off = ps.ioff;
        // Parse the integer from byte[] straight (without creating Strings)
        int     len = (ps.bufend > 0) ? ps.bufend : buf.length;
        String  str = new String(buf, 0, off, len-off);
        try {
            return Double.valueOf(str).doubleValue();
        } catch (Exception ex) {
            error("parseQuality", "Invalid floating point number.");
        }
        // Not reached:
        return 1.0;
    }

}
