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
package org.w3c.css.parser;

import java.util.Enumeration;

public class MediaEnumeration implements Enumeration {

    String [] media;
    int current = 0;

    MediaEnumeration(AtRuleMedia media) {
	this.media = media.media;
	while ((current < this.media.length)
	       && (this.media[current] != null)) {
	    current++;
	}
    }

    public boolean hasMoreElements() {
	while (current < media.length) {
	    if (media[current] != null) {
		return true;
	    }
	    current++;
	}

	return false;
    }

    public Object nextElement() {
	return media[current];
    }
}
