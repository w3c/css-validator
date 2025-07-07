// ParseState.java
// $Id$
// (c) COPYRIGHT MIT and INRIA, 1996.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.www.http;

class ParseState {
    int ioff = -1;		// input offset
    int ooff = -1;		// output ofset (where parsing should continue)
    int start = -1 ;		// Start of parsed item (if needed)
    int end = -1;		// End of parsed item (if needed)
    int bufend = -1 ;		// End of the buffer to parse

    boolean isSkipable = true ;	// Always skip space when this make sense
    boolean isQuotable = true ; // Support quted string while parsing next item
    boolean spaceIsSep = true;

    byte separator = (byte) ','; 	// Separator for parsing list

    final void prepare() {
        ioff  = ooff;
        start = -1;
        end   = -1;
    }

    final void prepare(ParseState ps) {
        this.ioff   = ps.start;
        this.bufend = ps.end;
    }

    ParseState(int ioff) {
        this.ioff = ioff;
    }

    ParseState(int ioff, int bufend) {
        this.ioff   = ioff;
        this.bufend = bufend;
    }

    ParseState() {
    }

}
