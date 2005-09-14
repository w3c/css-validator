/*
 * @(#)Element.java	1.1 95/04/23
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

import java.util.Hashtable;
import java.util.BitSet;

/**
 * An element in a DTD.
 *
 * @version 	1.1, 23 Apr 1995
 * @author Arthur van Hoff
 */
public
class Element implements DTDConstants {
    int index;
    String name;
    boolean oStart;
    boolean oEnd;
    BitSet inclusions;
    BitSet exclusions;
    int type = EMPTY;
    ContentModel content;
    AttributeList atts;

    /**
     * Create a new element.
     */
    public Element(String name, int index) {// public - jml - 12/17/96.
	this.name = name;
	this.index = index;
    }

    /**
     * Get the name of the element.
     */
    public String getName() {
	return name;
    }

    /**
     * Return true if the start tag can be omitted.
     */
    public boolean omitStart() {
	return oStart;
    }

    /**
     * Return true if the end tag can be omitted.
     */
    public boolean omitEnd() {
	return oEnd;
    }

    /**
     * Get type.
     */
    public int getType() {
	return type;
    }

    /**
     * Get content model
     */
    public ContentModel getContent() {
	return content;
    }

    /**
     * Get the attributes.
     */
    public AttributeList getAttributes() {
	return atts;
    }

    /**
     * Get index.
     */
    public int getIndex() {
	return index;
    }

    /**
     * Check if empty
     */
    public boolean isEmpty() {
	return type == EMPTY;
    }

    /**
     * Convert to a string.
     */
    public String toString() {
	return name;
    }

    /**
     * Get an attribute by name.
     */
    public AttributeList getAttribute(String name) {
	for (AttributeList a = atts ; a != null ; a = a.next) {
	    if (a.name.equals(name)) {
		return a;
	    }
	}
	return null;
    }

    /**
     * Get an attribute by value.
     */
    public AttributeList getAttributeByValue(String name) {
	for (AttributeList a = atts ; a != null ; a = a.next) {
	    if ((a.values != null) && a.values.contains(name)) {
		return a;
	    }
	}
	return null;
    }

    /**
     * Print (for debugging only).
     */
    public void print() {
	System.out.print("<!ELEMENT " + name +
			 (oStart ? " O" : " -") +
			 (oEnd ? " O " : " - "));
	switch (type) {
	  case MODEL:
	    System.out.print(" " + content);
	    break;
	  case CDATA:
	    System.out.print(" CDATA");
	    break;
	  case RCDATA:
	    System.out.print(" RCDATA");
	    break;
	  case EMPTY:
	    System.out.print(" EMPTY");
	    break;
	  case ANY:
	    System.out.print(" ANY");
	    break;
	}
	if (exclusions != null) {
	    System.out.print(" -" + exclusions);
	}
	if (inclusions != null) {
	    System.out.print(" +" + inclusions);
	}
	System.out.println(" --" + index + "-->");

	if (atts != null) {
	    atts.print(this);
	}
    }

    static Hashtable contentTypes = new Hashtable();

    static {
	contentTypes.put("CDATA", new Integer(CDATA));
	contentTypes.put("RCDATA", new Integer(RCDATA));
	contentTypes.put("EMPTY", new Integer(EMPTY));
	contentTypes.put("ANY", new Integer(ANY));
    }

    static int name2type(String nm) {
	Integer val = (Integer)contentTypes.get(nm);
	return (val != null) ? val.intValue() : 0;
    }
}
