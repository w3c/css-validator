/*
 * @(#)PublicMapping.java	1.1 95/04/23  
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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.util.Hashtable;
//import net.www.html.URL;
import java.net.URL;                            //??dk
import java.io.IOException;                     //??dk
import java.io.FileNotFoundException;           //??dk

/**
 * A class for mapping public identifiers to URLs.
 *
 * @version 	1.1, 23 Apr 1995
 * @author Arthur van Hoff
 * @modified Dmitri Kondratiev for JDK Beta 2 05/01/96 //??dk
 */
public class PublicMapping {
  public static final boolean debug = false; //@@vm
  String dir;
  Hashtable tab = new Hashtable();

    /**
     * Create a mapping given a base URL.
     */
    public PublicMapping(String dir) throws FileNotFoundException, IOException  {   //??dk
	this.dir = dir;
	// 1.1 - jml - 3/6/97.
	// URL url = getClass().getResource(dir + "/public.map");
	URL url = PublicMapping.class.getResource(dir + "/public.map");
	load(url.openStream());
    }

    /**
     * Load a set of mappings from a stream.
     */
    public void load(InputStream in) throws IOException {             //??dk
	BufferedReader  data = null;
	try {
	    data = new BufferedReader(new InputStreamReader(in));

	    for (String ln = data.readLine() ; ln != null ; ln = data.readLine()) {
		if (ln.startsWith("PUBLIC")) {
		    int len = ln.length();
		    int i = 6;
		    while ((i < len) && (ln.charAt(i) != '"')) i++;
		    int j = ++i;
		    while ((j < len) && (ln.charAt(j) != '"')) j++;
		    String id = ln.substring(i, j);
		    i = ++j;
		    while ((i < len) && ((ln.charAt(i) == ' ') || (ln.charAt(i) == '\t'))) i++;
		    j = i + 1;
		    while ((j < len) && (ln.charAt(j) != ' ') && (ln.charAt(j) != '\t')) j++;
		    String where = ln.substring(i, j);
		    put(id, where);
		}
	    }
	} finally {
	    try {
		data.close();
	    } catch (Exception e) {}
	}
    }

    /**
     * Add a mapping from a public identifier to a URL.
     */
    public void put(String id, String where) {
	tab.put(id, where);
	if (where.endsWith(".dtd")) {
	    tab.put(where.substring(where.lastIndexOf(File.separatorChar) + 1, where.length() - 4), where);
	}
    }

  /**
    * Map a public identifier to a URL. You can also map
    * a DTD file name (without the .dtd) to a URL.
    */
  public InputStream get(String id) throws FileNotFoundException {
    if (debug)
      System.out.println("PublicMapping::get(): id=" + id);

    String name = dir + "/" + tab.get(id);
    try {
      URL url = getClass().getResource(name);
      return new BufferedInputStream(url.openStream());
    }
    catch(Exception x) {
      throw new FileNotFoundException(name);
    }
  }
}
