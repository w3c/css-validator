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

package html.tags;

import java.io.*;

import html.tree.*;
import html.parser.*;

/**
 * This class handles the HTML root tag
 */

public class RootTag extends Block {

    public static final boolean debug =  false;

    /**
     * Create a new StyleTag.
     */

    public RootTag() {
	if (debug) {
	    System.out.println( "creating RootTag   this=" +(Object)this);
	}
    }

    public void initialize(Element elem, Attributes atts,
			   ParserFrame parserFrame) {
	super.initialize(elem, atts, parserFrame);

	if (!"html".equals(elem.getName().toLowerCase())) {
	    throw new XMLInputException(null);
	}
	if (atts != null) {
	    String xml = atts.get("xmlns");
	    if (xml != null) {
		throw new XMLInputException(xml);
	    }
	}
    }
}
