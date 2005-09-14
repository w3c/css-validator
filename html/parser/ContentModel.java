/*
 * @(#)ContentModel.java	1.2 95/04/24
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

/**
 * A representation of a content model. A content model is
 * basically a restricted BNF expression. It is restricted in
 * the sense that it must be deterministic. This means that you
 * don't have to represent it as a finite state automata.<p>
 * See Annex H on page 556 of the SGML handbook for more information.
 *
 * @version 	1.2, 24 Apr 1995
 * @author Arthur van Hoff
 */
class ContentModel {
    /**
     * Type. Either '*', '?', '+', ',', '|', '&'.
     */
    int type;

    /**
     * The content. Either an Element or a ContentModel.
     */
    Object content;

    /**
     * The next content model (in a ',', '|' or '&' expression).
     */
    ContentModel next;

    /**
     * Create a content model for an element.
     */
    ContentModel(Element content) {
	this.content = content;
    }

    /**
     * Create a content model of a particular type.
     */
    ContentModel(int type, ContentModel content) {
	this.type = type;
	this.content = content;
    }

    /**
     * Return true if the content model could
     * match an empty input stream.
     */
    public boolean empty() {
	switch (type) {
	  case '*':
	  case '?':
	    return true;

	  case '+':
	  case '|':
	    for (ContentModel m = (ContentModel)content ; m != null ; m = m.next) {
		if (m.empty()) {
		    return true;
		}
	    }
	    return false;

	  case ',':
	  case '&':
	    for (ContentModel m = (ContentModel)content ; m != null ; m = m.next) {
		if (!m.empty()) {
		    return false;
		}
	    }
	    return true;

	  default:
	    return false;
	}
    }

    /**
     * Return true if the token could potentially be the
     * first token in the input stream.
     */
    public boolean first(Object token) {
	switch (type) {
	  case '*':
	  case '?':
	  case '+':
	    return ((ContentModel)content).first(token);

	  case ',':
	    for (ContentModel m = (ContentModel)content ; m != null ; m = m.next) {
		if (m.first(token)) {
		    return true;
		}
		if (!m.empty()) {
		    return false;
		}
	    }
	    return false;

	  case '|':
	  case '&':
	    for (ContentModel m = (ContentModel)content ; m != null ; m = m.next) {
		if (m.first(token)) {
		    return true;
		}
	    }
	    return false;

	  default:
	    return content.equals(token);
	}
    }

    /**
     * Return the element that must be next.
     */
    public Element first() {
	switch (type) {
	  case '&':
	  case '|':
	  case '*':
	  case '?':
	    return null;

	  case '+':
	  case ',':
	    return ((ContentModel)content).first();

	  default:
	    return (Element)content;
	}
    }

    /**
     * Convert to a string.
     */
    public String toString() {
	switch (type) {
	  case '*':
	    return content + "*";
	  case '?':
	    return content + "?";
	  case '+':
	    return content + "+";

	  case ',':
	  case '|':
	  case '&':
	    char data[] = {' ', (char)type, ' '};
	    String str = "";
	    for (ContentModel m = (ContentModel)content ; m != null ; m = m.next) {
		str = str + m;
		if (m.next != null) {
		    str += new String(data);
		}
	    }
	    return "(" + str + ")";

	  default:
	    return content.toString();
	}
    }
}
