/*
 * @(#)HTMLOutputStream.java	1.3 95/05/03  
 *
 * Copyright (c) 1994 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies. Please refer to the file "copyright.html"
 * for further important copyright and licensing information.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package html.parser;

import java.io.OutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;                                   //??dk

/**
 * This class assists in writing HTML to an OutputStream.
 * The output is processed, special characters are escaped,
 * attributes are quoted and newlines are inserted where
 * appropriate.<p>
 *
 * This is all complicated by the rules for ignoring spaces
 * and RE arounds tags. This class hides all that functionality
 * for you.
 *
 * @version 	1.3, 03 May 1995
 * @author Arthur van Hoff
 * @modified Dmitri Kondratiev for JDK Beta 2 05/01/96 //??dk
 */
public
class HTMLOutputStream extends FilterOutputStream implements DTDConstants {
    DTD dtd;
    int col;
    int plain;
    int pre;
    boolean newline;
    boolean space;
    boolean eatspace;

    final int MAXCOL = 60;

    /**
     * Create an HTMLOutputStream.
     */
    public HTMLOutputStream(OutputStream out, DTD dtd) {
	super(out);
	this.dtd = dtd;
    }

    /**
     * Write a character.
     */
    public void write(int c) throws IOException {               //??dk
	out.write(c);
	col = (c == '\n') ? 0 : col + 1;
    }

    /**
     * Write a literal string. The characters are
     * NOT escaped.
     */
    void write(String str) throws IOException {                 //??dk
	int len = str.length();
	for (int i = 0 ; i < len ; i++) {
	    write(str.charAt(i));
	}
    }

    /**
     * Write a character, escape if necessary.
     */
    void writeEscaped(int c) throws IOException {                            //??dk
	switch (c) {
	  case '<': write("&lt;"); break;
	  case '>': write("&gt;"); break;
	  case '&': write("&amp;"); break;
	  case '"': write("&quot;"); break;

	  default:
	    if ((c < ' ') || (c > 127)) {
		Entity ent = dtd.getEntity(c);
		write('&');
		if (ent != null) {
		    write(ent.getName());
		} else {
		    write('#');
		    write(String.valueOf(c));
		}
		write(';');
	    } else {
		write(c);
	    }
	}
    }
    
    /**
     * Write a text character.
     */
    void writeText(int c) throws IOException {                      //??dk
	switch (c) {
	  case '\n':
	  case '\t':
	  case ' ':
	    if (pre == 0) {
		space = !eatspace;
	    } else {
		write(c);
	    }
	    return;
	}
	if (space) {
	    write((newline || (col >= MAXCOL)) ? '\n' : ' ');
	    space = false;
	}
	eatspace = newline = false;
	writeEscaped(c);
    }

    /**
     * Output a tag with attribytes.
     */
    void writeTag(Element elem, Attributes atts) throws IOException {   //??dk
	write('<');
	write(elem.getName());
	
	if (atts != null) {
	    int natts = atts.length();
	    for (int i = 0 ; i < natts ; i++) {
		String attname = atts.getName(i);
		String attvalue = atts.get(i);
		AttributeList attlist = elem.getAttribute(attname);
		int type = (attlist == null) ? CDATA : attlist.getType();

		write(' ');
		write(attname);

		int len = attvalue.length();

		switch (type) {
		  case ID:
		  case NAME:
		  case NMTOKEN:
		  case NUMBER:
		  case NUTOKEN:
		    write('=');
		    for (int j = 0; j < len ; j++) {
			writeEscaped(attvalue.charAt(j));
		    }
		    break;
		  default:
		    write('=');
		    write('"');
		    for (int j = 0; j < len ; j++) {
			writeEscaped(attvalue.charAt(j));
		    }
		    write('"');
		    break;
		}

	    }
	}
	write('>');
    }

    /**
     * Output a start Tag
     */
    public void startTag(Tag tag) throws IOException {                      //??dk
	if ((pre == 0) && (space || tag.isBlock()) && !eatspace) {
	    write('\n');
	}
	newline = space = false;
	writeTag(tag.getElement(), tag.getAttributes());
	if (pre == 0) {
	    write('\n');
	}
	if (tag.isPreformatted()) {
	    pre++;
	} else if (tag.getElement().getType() == CDATA) {
	    plain++;
	}
	eatspace = (pre == 0) && tag.isBlock();
    }
    public void startTag(Element elem, Attributes atts) throws IOException {  //??dk
	// REMIND: REMOVE
	writeTag(elem, atts);
	write('\n');
    }

    /**
     * Output an end Tag
     */
    public void endTag(Tag tag) throws IOException {                           //??dk
	if (tag.isPreformatted()) {
	    pre--;
	} else if (tag.getElement().getType() == CDATA) {
	    plain--;
	}
	newline = space = false;
	if ((pre == 0) && !eatspace) {
	    write('\n');
	}
	write('<');
	write('/');
	write(tag.getElement().getName());
	write('>');
	if (pre == 0) {
	    if (tag.isBlock()) {
		write('\n');
		eatspace = true;
	    } else {
		newline = true;
	    }
	}
    }
    public void endTag(Element elem) throws IOException {                   //??dk
	// REMIND: REMOVE
	write('\n');
	write('<');
	write('/');
	write(elem.getName());
	write('>');
    }

    /**
     * Output an empty Tag
     */
    public void emptyTag(Tag tag) throws IOException {                      //??dk
	if ((pre == 0) && (space || newline || tag.isBlock()) && !eatspace) {
	    write('\n');
	}
	eatspace = newline = space = false;

	writeTag(tag.getElement(), tag.getAttributes());

	if (pre == 0) {
	    if (tag.isBlock()) {
		write('\n');
		eatspace = true;
	    } else {
		newline = true;
	    }
	}
    }
    public void emptyTag(Element elem, Attributes atts) throws IOException {  //??dk
	// REMIND: REMOVE
	writeTag(elem, atts);
    }

    /**
     * Output text.
     */
    public void text(byte data[]) throws IOException {                        //??dk
	text(data, 0, data.length);
    }

    /**
     * Output text.
     */
    public void text(byte data[], int off, int len) throws IOException {        //??dk
	if (plain == 0) {
	    for (int i = 0 ; i < len ; i++) {
		writeText(data[i + off] & 0xFF);
	    }
	} else {
	    out.write(data, off, len);
	}
    }

    /**
     * Output text.
     */
    public void text(String str) throws IOException {                         //??dk
	if (plain == 0) {
	    int len = str.length();
	    for (int i = 0 ; i < len ; i++) {
		writeText(str.charAt(i));
	    }
	} else {
	    write(str);
	}
    }

    /**
     * Output comment.
     */
    public void comment(String str) throws IOException {                      //??dk
	if (newline) {
	    write('\n');
	}
	write('<');
	write('!');
	write('-');
	write('-');
	write(str);
	write('-');
	write('-');
	write('>');
	eatspace = space = false;
	newline = (pre == 0);
    }
}
