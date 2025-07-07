// HttpBuffer.java
// $Id$
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.www.http;

/**
 * A cool StringBuffer like class, for converting header values to String.
 * Note that for good reasons, this class is <em>not</em> public.
 */

class HttpBuffer {
    private static final int INIT_SIZE = 128 ;

    byte    buf[];
    int     len   = 0;

    final void append(byte b) {
        ensureCapacity(1);
        buf[len++] = b;
    }

    final void append(char ch) {
        append((byte) ch);
    }

    final void ensureCapacity(int sz) {
        int req = len + sz ;
        if ( req >= buf.length ) {
            int nsz = buf.length << 1;
            if ( nsz < req )
                nsz = req + 1;
            byte nb[] = new byte[nsz];
            System.arraycopy(buf, 0, nb, 0, len);
            buf = nb;
        }
    }

    void append(byte b[], int o, int l) {
        ensureCapacity(l);
        System.arraycopy(b, o, buf, len, l);
        len += l;
    }

    void append(String str) {
        int l = str.length();
        ensureCapacity(l);
        str.getBytes(0, l, buf, len);
        len += l;
    }

    void append(double d) {
        append(Double.toString(d));
    }

    public String toString() {
        return new String(buf, 0, 0, len);
    }

    /**
     * Get a copy of the current byte buffer.
     */

    public byte[] getByteCopy() {
        byte v[] = new byte[len];
        System.arraycopy(buf, 0, v, 0, len);
        return v;
    }

    public final byte[] getBytes() {
        return buf;
    }

    public final int length() {
        return len;
    }

    public final void reset() {
        len = 0;
    }

    HttpBuffer() {
        this.buf = new byte[INIT_SIZE];
    }

}
