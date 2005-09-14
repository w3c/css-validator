/*
 * @(#)DTD.java	1.8 95/06/07
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

import java.io.File;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.Hashtable;
import java.util.Vector;
import java.util.BitSet;
import java.util.Enumeration;
//import net.www.html.URL;
import java.net.URL;   //??dk
import java.util.Properties;          //??dk
import java.io.FileNotFoundException; //??dk
import java.io.IOException;           //??dk

/**
 * The representation of an SGML DTD.
 *
 * @version 	1.8, 07 Jun 1995
 * @author Arthur van Hoff
 * @modified Dmitri Kondratiev for JDK Beta 2 05/01/96 //??dk
 * @modified Jean-Michel Leon for JDK 1.1 - jml - 3/6/97.
 */
public
class DTD implements DTDConstants {
    String name;
    Vector elements = new Vector();
    Hashtable elementHash = new Hashtable();
    Hashtable entityHash = new Hashtable();
    Element pcdata = getElement("#pcdata");
    Element app = getElement("app");
    Element html = getElement("html");
    Element head = getElement("head");
    Element body = getElement("body");
    static Properties props;

    /**
     * Create a new DTD.
     */
    public DTD(String name) {
	this.name = name;
	defineEntity("#RE", GENERAL, '\r');
	defineEntity("#RS", GENERAL, '\n');
	defineEntity("#SPACE", GENERAL, ' ');
    }

    /**
     * Get the name of the DTD.
     */
    public String getName() {
	return name;
    }

    /**
     * Get an entity by name.
     */
    public Entity getEntity(String name) {
	return (Entity)entityHash.get(name);
    }

    /**
     * Get a character entity.
     */
    public Entity getEntity(int ch) {
	return (Entity)entityHash.get(new Integer(ch));
    }

    /**
     * Get an element by name. A new element is
     * created if the element doesn't exist.
     */
    public Element getElement(String name) {
	Element e = (Element)elementHash.get(name);
	if (e == null) {
	    e = new Element(name, elements.size());
	    elements.addElement(e);
	    elementHash.put(name, e);
	}
	return e;
    }

   /** - jml - 5/7/97.
    * Get an element by name.
    *
    * @return null if the element does not exists
    */
    public Element findElement(String name) {
	return (Element)elementHash.get(name);
    }

    /**
     * Get an element by index.
     */
    public Element getElement(int index) {
	return (Element)elements.elementAt(index);
    }

    /**
     * Define an entity.
     */
    void defineEntity(String name, int type, byte data[]) {
	if (entityHash.get(name) == null) {
	    Entity ent = new Entity(name, type, data);
	    entityHash.put(name, ent);
	    if (((type & GENERAL) != 0) && (data.length == 1)) {
		switch (type & ~GENERAL) {
		  case CDATA:
		  case SDATA:
		    entityHash.put(new Integer(data[0] & 0xFF), ent);
		    break;
		}
	    }
	}
    }

    /**
     * Define a character entity.
     */
    void defineEntity(String name, int type, int ch) {
	byte data[] = {(byte)(ch & 0xFF)};
	defineEntity(name, type, data);
    }

    /**
     * Define an element.
     */
    void defineElement(String name, int type,
		       boolean omitStart, boolean omitEnd, ContentModel content,
		       BitSet exclusions, BitSet inclusions) {
	Element e = getElement(name);
	e.type = type;
	e.oStart = omitStart;
	e.oEnd = omitEnd;
	e.content = content;
	e.exclusions = exclusions;
	e.inclusions = inclusions;
    }

    /**
     * Define the attributes of an element.
     */
    void defineAttributes(String name, AttributeList atts) {
	Element e = getElement(name);
	e.atts = atts;
    }

    /**
     * To string.
     */
    public String toString() {
	return name;
    }

    /**
     * Print the DTD (for debugging only).
     */
    public void print() {
	System.out.println("<!DOCTYPE " + name + " [");
	System.out.println();
	for (Enumeration e = entityHash.elements() ; e.hasMoreElements() ; ) {
	    ((Entity)e.nextElement()).print();
	}
	System.out.println();
	for (Enumeration e = elements.elements() ; e.hasMoreElements() ; ) {
	    ((Element)e.nextElement()).print();
	}
	System.out.println("]>");
    }

    /**
     * The hashtable of DTDs.
     */
    static Hashtable dtdHash = new Hashtable();

    /**
     * The mapping that is used to find DTDs
     */
    static PublicMapping mapping;

    /**
     * Get a DTD.
     */
    public static DTD getDTD(String name) throws FileNotFoundException, IOException {    //??dk
	name = name.toLowerCase();
	DTD dtd = (DTD)dtdHash.get(name);
	if (dtd == null) {
	    if (mapping == null) {
		mapping = new PublicMapping(props.getProperty("dtds"));
	    }

	    InputStream in = mapping.get(name);
	    dtd = new DTD(name);
	    new DTDParser().parse(in, dtd);

	    // Define the app tag
	    dtd.app.atts = new AttributeList("class");
	    dtd.app.atts.modifier = REQUIRED;
	    dtd.app.atts.type = CDATA;

	    // Add app the inclusions of the body tag
	    if (dtd.body.inclusions == null) {
		dtd.body.inclusions = new BitSet();
	    }
	    dtd.body.inclusions.set(dtd.app.getIndex());

	    dtdHash.put(name, dtd);
	}
	return dtd;
    }
}
