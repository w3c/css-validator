/*
 * @(#)ContentModelState.java	1.2 95/04/24  
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
 * A content model state. This is basically a list of pointers to
 * the BNF expression representing the model. Each time a token is
 * reduced a new state is created.<p>
 * See Annex H on page 556 of the SGML handbook for more information.
 *
 * @version 	1.2, 24 Apr 1995
 * @author Arthur van Hoff
 * @modified Dmitri Kondratiev for JDK Beta 2 05/01/96 //??dk
 */
public
class ContentModelState {
    ContentModel model;
    long value;
    ContentModelState next;

    /**
     * Create a content model state for a content model.
     */
    public ContentModelState(ContentModel model) {
	this(model, null, 0);
    }

    /**
     * Create a content model state for a content model given the
     * remaining state that needs to be reduce.
     */
    ContentModelState(Object content, ContentModelState next) {
	this(content, next, 0);
    }

    /**
     * Create a content model state for a content model given the
     * remaining state that needs to be reduce.
     */
    ContentModelState(Object content, ContentModelState next, long value) {
	this.model = (ContentModel)content;
	this.next = next;
	this.value = value;
    }

    /**
     * Check if the state can be terminated. That is thereare no more
     * tokens required in the input stream.
     */
    public boolean terminate() {
	switch (model.type) {
	  case '+':
	    if ((value == 0) && !((ContentModel)model).empty()) {
		return false;
	    }
	  case '*':
	  case '?':
	    return (next == null) || next.terminate();

	  case '|':
	    for (ContentModel m = (ContentModel)model.content ; m != null ; m = m.next) {
		if (m.empty()) {
		    return (next == null) || next.terminate();
		}
	    }
	    return false;

	  case '&': {
	    ContentModel m = (ContentModel)model.content;
		
	    for (int i = 0 ; m != null ; i++, m = m.next) {
		if ((value & (1L << i)) == 0) {
		    if (!m.empty()) {
			return false;
		    }
		}
	    }
	    return (next == null) || next.terminate();
	  }

	  case ',': {
	    ContentModel m = (ContentModel)model.content;
	    for (int i = 0 ; i < value ; i++, m = m.next);

	    for (; (m != null) && m.empty() ; m = m.next);
	    if (m != null) {
		return false;
	    }
	    return (next == null) || next.terminate();
	  }
	    
	default:
	  return false;
	}
    }

    /**
     * Check if the state can be terminated. That is thereare no more
     * tokens required in the input stream.
     */
    public Element first() {
	switch (model.type) {
	  case '*':
	  case '?':
	  case '|':
	  case '&':
	    return null;

	  case '+':
	    return model.first();

	  case ',': {
	      ContentModel m = (ContentModel)model.content;
	      for (int i = 0 ; i < value ; i++, m = m.next);
	      return m.first();
	  }

	  default:
	    return model.first();
	}
    }

    /**
     * Advance this state to a new state. An exception is thrown if the
     * token is illegal at this point in the content model.
     */
    public ContentModelState advance(Object token)  throws Exception {  //??dk
	switch (model.type) {
	  case '+':
	    if (model.first(token)) {
		return new ContentModelState(model.content, 
		        new ContentModelState(model, next, value + 1)).advance(token);
	    }
	    if (value != 0) {
		return next.advance(token);
	    }
	    break;
		
	  case '*':
	    if (model.first(token)) {
		return new ContentModelState(model.content, this).advance(token);
	    }
	    return next.advance(token);
		
	  case '?':
	    if (model.first(token)) {
		return new ContentModelState(model.content, next).advance(token);
	    }
	    return next.advance(token);

	  case '|':
	    for (ContentModel m = (ContentModel)model.content ; m != null ; m = m.next) {
		if (m.first(token)) {
		    return new ContentModelState(m, next).advance(token);
		}
	    }
	    break;

	  case ',': {
	    ContentModel m = (ContentModel)model.content;
	    for (int i = 0 ; i < value ; i++, m = m.next);

	    if (m.first(token) || m.empty()) {
		if (m.next == null) {
		    return new ContentModelState(m, next).advance(token);
		} else {
		    return new ContentModelState(m, 
			    new ContentModelState(model, next, value + 1)).advance(token);
		}
	    } 
	    break;
	  }

	  case '&': {
	    ContentModel m = (ContentModel)model.content;
	    boolean complete = true;
		
	    for (int i = 0 ; m != null ; i++, m = m.next) {
		if ((value & (1L << i)) == 0) {
		    if (m.first(token)) {
			return new ContentModelState(m, 
			        new ContentModelState(model, next, value | (1L << i))).advance(token);
		    }
		    if (!m.empty()) {
			complete = false;
		    }
		}
	    }
	    if (complete) {
		return next.advance(token);
	    }	
	    break;
	  }
		
	  default:
	    if (model.content == token) {
		return next;
	    }
	}
	throw new Exception("invalid token: " + token);
    }
}
