/*
 * @(#)AttributeList.java	1.1 95/04/23  
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

import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;

/**
 * An attribute list descriptor. This defines
 * the legal attributes of an element. It is
 * a linked list.
 *
 * @version 	1.1, 23 Apr 1995
 * @author Arthur van Hoff
 */
public final
class AttributeList implements DTDConstants {
    String name;
    int type;
    Vector values;
    int modifier;
    String value;
    AttributeList next;

    /**
     * Create an attribute list element.
     */
    AttributeList(String name) {
	this.name = name;
    }

    /**
     * Get the name.
     */
    public String getName() {
	return name;
    }

    /**
     * Get the type.
     */
    public int getType() {
	return type;
    }

    /**
     * Get the values.
     */
    public Enumeration getValues() {
	return values.elements();
    }

    /**
     * Get the value.
     */
    public String getValue() {
	return value;
    }

    /**
     * Get the next one.
     */
    public AttributeList getNext() {
	return next;
    }

    /**
     * Convert to a string.
     */
    public String toString() {
	return name;
    }

    /**
     * Print the attribute list.
     */
    public void print(Element elem) {
	System.out.println("<ATTLIST " + elem.getName() + " ");
	for (AttributeList att = this ; att != null ; att = att.next) {
	    System.out.print("\t" + att.getName() + " ");
	    if (att.values != null) {
		System.out.print("(");
		for (Enumeration e = att.values.elements() ; e.hasMoreElements() ; ) {
		    System.out.print(e.nextElement());
		    if (e.hasMoreElements()) {
			System.out.print("|");
		    }
		}
		System.out.print(") ");
	    } else {
		System.out.print(type2name(att.getType()) + " ");
	    }
	    switch (att.modifier) {
	      case REQUIRED:
		System.out.println("#REQUIRED");
		continue;
	      case CURRENT:
		System.out.println("#CURRENT");
		continue;
	      case CONREF:
		System.out.println("#CONREF");
		continue;
	      case IMPLIED:
		System.out.println("#IMPLIED");
		continue;
	      case FIXED:
		System.out.print("#FIXED ");
		break;
	    }
	    System.out.println("\"" + att.value + "\"");
	}
	System.out.println(">");
    }

    /**
     * Create a hastable of attribyte types.
     */
    static Hashtable attributeTypes = new Hashtable();

    static void defineAttributeType(String nm, int val) {
	Integer num = new Integer(val);
	attributeTypes.put(nm, num);
	attributeTypes.put(num, nm);
    }

    static {
	defineAttributeType("CDATA", CDATA);
	defineAttributeType("ENTITY", ENTITY);
	defineAttributeType("ENTITIES", ENTITIES);
	defineAttributeType("ID", ID);
	defineAttributeType("IDREF", IDREF);
	defineAttributeType("IDREFS", IDREFS);
	defineAttributeType("NAME", NAME);
	defineAttributeType("NAMES", NAMES);
	defineAttributeType("NMTOKEN", NMTOKEN);
	defineAttributeType("NMTOKENS", NMTOKENS);
	defineAttributeType("NOTATION", NOTATION);
	defineAttributeType("NUMBER", NUMBER);
	defineAttributeType("NUMBERS", NUMBERS);
	defineAttributeType("NUTOKEN", NUTOKEN);
	defineAttributeType("NUTOKENS", NUTOKENS);

	attributeTypes.put("fixed", new Integer(FIXED));
	attributeTypes.put("required", new Integer(REQUIRED));
	attributeTypes.put("current", new Integer(CURRENT));
	attributeTypes.put("conref", new Integer(CONREF));
	attributeTypes.put("implied", new Integer(IMPLIED));
    }
    
    static int name2type(String nm) {
	Integer i = (Integer)attributeTypes.get(nm);
	return (i == null) ? CDATA : i.intValue();
    }
    static String type2name(int tp) {
	return (String)attributeTypes.get(new Integer(tp));
    }
}
