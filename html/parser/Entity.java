/*
 * @(#)Entity.java	1.2 95/05/03
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
import java.io.File;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;            //??dk
import java.net.URL;                            //??dk
import java.io.FileNotFoundException;           //??dk

/**
 * An entity in a DTD.
 *
 * @version 	1.2, 03 May 1995
 * @author Arthur van Hoff
 * @modified Dmitri Kondratiev for JDK Beta 2 05/01/96 //??dk
 * @modified Jean-Michel Leon for JDK 1.1 - jml - 3/6/97.
 */
public
class Entity implements DTDConstants {
    String name;
    int type;
    byte data[];

    /**
     * Create an entity.
     */
    Entity(String name, int type, byte data[]) {
	this.name = name;
	this.type = type;
	this.data = data;
    }

    /**
     * Get the name of the entity.
     */
    public String getName() {
	return name;
    }

    /**
     * Get the type of the entity.
     */
    public int getType() {
	return type & 0xFFFF;
    }

    /**
     * Return true if it is a parameter entity.
     */
    public boolean isParameter() {
	return (type & PARAMETER) != 0;
    }

    /**
     * Return true if it is a parameter entity.
     */
    public boolean isGeneral() {
	return (type & GENERAL) != 0;
    }

    /**
     * Return the data.
     */
    public byte getData()[] {
	return data;
    }

    /**
     * Return the data as a string.
     */
    public String getString() {
	// deprecated 1.1 - jml - 2/26/97.
	// 1.0 style
	//	return new String(data, 0, 0, data.length);
	// 1.1 style
	return new String(data);
	// deprecated 1.1 - jml - 2/26/97.
    }

    /**
     * Return the data as a stream.
     */
    public InputStream getInputStream() throws FileNotFoundException {  //??dk
	if ((type & PUBLIC) != 0) {
	    return DTD.mapping.get(getString());
	}
	if ((type & SYSTEM) != 0) {
	    String iname = DTD.mapping.dir + "/" + getString();
	    URL url = Entity.class.getResource(iname); // plh050598
	    try {
		return new BufferedInputStream(url.openStream());
	    }
	    catch(Exception x) {
		throw new FileNotFoundException(iname);
	    }
	}
	return new ByteArrayInputStream(data);                      //??dk
    }

    /**
     * Print (for debugging only).
     */
    public void print() {
	if (isParameter()) {
	    return;
	}
	System.out.print("<!ENTITY ");
	System.out.print(name);
	switch (getType()) {
	  case PUBLIC:  System.out.print(" PUBLIC"); break;
	  case CDATA:   System.out.print(" CDATA"); break;
	  case SDATA:   System.out.print(" SDATA"); break;
	  case PI:	System.out.print(" PI"); break;
	  case STARTTAG:System.out.print(" STARTTAG"); break;
	  case ENDTAG:  System.out.print(" ENDTAG"); break;
	  case MS:	System.out.print(" MS"); break;
	  case MD:	System.out.print(" MD"); break;
	}
	System.out.print(" \"" + getString() + "\"");
	System.out.println(">");
    }

    static Hashtable entityTypes = new Hashtable();

    static {
	entityTypes.put("PUBLIC", new Integer(PUBLIC));
	entityTypes.put("CDATA", new Integer(CDATA));
	entityTypes.put("SDATA", new Integer(SDATA));
	entityTypes.put("PI", new Integer(PI));
	entityTypes.put("STARTTAG", new Integer(STARTTAG));
	entityTypes.put("ENDTAG", new Integer(ENDTAG));
	entityTypes.put("MS", new Integer(MS));
	entityTypes.put("MD", new Integer(MD));
	entityTypes.put("SYSTEM", new Integer(SYSTEM));
    }

    static int name2type(String nm) {
	Integer i = (Integer)entityTypes.get(nm);
	return (i == null) ? CDATA : i.intValue();
    }
}

