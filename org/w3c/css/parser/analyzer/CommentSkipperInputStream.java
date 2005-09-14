/*
 * Copyright (c) 2001 World Wide Web Consortium,
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
package org.w3c.css.parser.analyzer;

import java.io.IOException;
import java.io.InputStream;


/**
 * @version $Revision$
 * @author  Philippe Le Hegaret
 */
public class CommentSkipperInputStream extends InputStream {

    int previous;
    InputStream in;
    /**
     * Creates a new CommentSkipperInputStream
     */
    public CommentSkipperInputStream(InputStream input) {
	in = input;
    }


    public int read() throws IOException {
	int c;
	if (previous != 0) {
	    c = previous;
	    previous = 0;
	    return c;
	}

	c = in.read();

	if (c != '/') {
	    return c;
	}
	previous = in.read();

	if (previous != '*') {
	    return c;
	}
	previous = 0;
	do {
	    do {
		c = in.read();
	    } while ((c != -1) && (c != '*'));
	    c = in.read();
	} while ((c != -1) && (c != '/'));
	if (c == '/') {
	    return read();
	} else {
	    return -1;
	}
    }

    public void close() throws IOException {
	in.close();
    }
}
