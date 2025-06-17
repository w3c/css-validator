// HeaderValue.java
// $Id$
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.www.http;

public abstract class HeaderValue implements Cloneable {
    /**
     * The header value, as a byte array, if available.
     */
    protected byte raw[] = null;
    /**
     * The offset of the value in the above buffer, in case the buffer is
     * shared.
     */
    protected int roff = -1;
    /**
     * The length of the byte value in case the above buffer is shared.
     */
    protected int rlen = -1;
    /**
     * Are the parsed values up to date with the lastly set unparsed value ?
     */
    protected boolean isValid = false;

    /**
     * Parse this header value into its various components.
     * @exception HttpParserException if unable to parse.
     */

    abstract protected void parse()
            throws HttpParserException;

    /**
     * Update the RFC822 compatible header value for this object.
     */

    abstract protected void updateByteValue();

    /**
     * Compute the new RFC822 compatible representation of this header value.
     * If our value is up to date, we just return, otherwise, the abstract
     * <code>updateByteValue</code> is called to perform the job.
     */

    protected final void checkByteValue() {
        if ( raw == null ) {
            updateByteValue();
            roff = 0;
            rlen = raw.length;
        }
    }

    /**
     * Validate the parsed value according to the last set raw value.
     * This will trigger the header value parsing, if it is required at this
     * point.
     * @exception HttpInvalidValueException If the value couldn't be parsed 
     * properly.
     */

    protected final void validate()
            throws HttpInvalidValueException
    {
        if ( isValid )
            return;
        try {
            parse();
        } catch (HttpParserException ex) {
            throw new HttpInvalidValueException(ex.getMessage());
        }
        isValid = true;
    }

    /**
     * Invalidate the current byte value for this header, if any.
     */

    protected void invalidateByteValue() {
        raw = null;
    }

    /**
     * Emit a parsing error.
     * @param msg The error message.
     * @exception HttpParserException If the parsing failed.
     */

    protected void error(String msg)
            throws HttpParserException
    {
        throw new HttpParserException(msg);
    }

    /**
     * Append this header byte value to the given buffer.
     * @param buf The buffer to append the byte value to.
     */

    public void appendValue(HttpBuffer buf) {
        checkByteValue();
        buf.append(raw, roff, rlen);
    }

    /**
     * Return the HTTP encoding for this header value.
     * This method is slow, and defeats nearly all the over-engeneered 
     * optimization of the HTTP parser.
     * @return A String representing the header value in a format compatible 
     * with HTTP.
     */

    public String toExternalForm() {
        checkByteValue();
        return new String(raw, 0, roff, rlen-roff);
    }

    /**
     * Print this header value as it would be emitted.
     * @return A String representation of this header value.
     */

    public String toString() {
        return toExternalForm();
    }

    /**
     * Set this Header Value by parsing the given String.
     * @param strval The String value for that object.
     * @return Itself.
     */

    public void setString(String strval) {
        int slen = strval.length();
        raw      = new byte[slen];
        roff     = 0;
        rlen     = slen;
        strval.getBytes(0, slen, raw, 0);
        isValid  = false;
    }

    /**
     * Get this header parsed value, in its native type.
     * HeaderValue implementors can be used as wrappers for the actual
     * parsed header value. In such case this method should return the wrapped
     * value (you would otherwise, probably want to return 
     * <strong>this</strong>).
     */

    abstract public Object getValue() ;

    // needs to define it as this is an abstract class
    protected Object clone()
            throws CloneNotSupportedException
    {
        return super.clone();
    }

    public HeaderValue() {
        isValid = false;
    }

}
