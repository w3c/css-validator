/*
 * @(#)TagStack.java	1.3 95/05/01  
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

import java.util.BitSet;

/**
 * A stack of tags. Used while parsing an HTML document.
 *
 * @version 	1.3, 01 May 1995
 * @author Arthur van Hoff
 */
public
class TagStack implements DTDConstants {
    Tag tag;
    Element elem;
    ContentModelState state;
    TagStack next;
    BitSet inclusions;
    BitSet exclusions;
    boolean net;
    boolean pre;

    /**
     * Construct a stack element.
     */
    TagStack(Tag tag, TagStack next) {
	this.tag = tag;
	this.elem = tag.getElement();
	this.next = next;

	Element elem = tag.getElement();
	if (elem.getContent() != null) {
	    this.state = new ContentModelState(elem.getContent());
	}

	if (next != null) {
	    inclusions = next.inclusions;
	    exclusions = next.exclusions;
	    pre = next.pre;
	}
	if (tag.isPreformatted()) {
	    pre = true;
	}

	if (elem.inclusions != null) {
	    if (inclusions != null) {
		inclusions = (BitSet)inclusions.clone();
		inclusions.or(elem.inclusions);
	    } else {
		inclusions = elem.inclusions;
	    }
	}
	if (elem.exclusions != null) {
	    if (exclusions != null) {
		exclusions = (BitSet)exclusions.clone();
		exclusions.or(elem.exclusions);
	    } else {
		exclusions = elem.exclusions;
	    }
	}
    }

    /**
     * Return the element that must come next in the
     * input stream.
     */
    public Element first() {
	return (state != null) ? state.first() : null;
    }

    /**
     * Advance the state by reducing the given element.
     * Returns false if the element is not legal and the
     * state is not advanced.
     */
    boolean advance(Element elem) {
	if ((exclusions != null) && exclusions.get(elem.getIndex())) {
	    return false;
	}
	if (state != null) {
	    try {
		state = state.advance(elem);
		return true;
	    } catch (Exception e) {
	    }
	} else if (this.elem.getType() == ANY) {
	    return true;
	}
	return (inclusions != null) && inclusions.get(elem.getIndex());
    }

    /**
     * Return true if the current state can be terminated.
     */
    boolean terminate() {
	return (state == null) || state.terminate();
    }

    /**
     * Convert to a string.
     */
    public String toString() {
	return (next == null) ? 
	    "<" + tag.getElement().getName() + ">" : 
	    next + " <" + tag.getElement().getName() + ">";
    }
}
