/* Copyright (c) 1996 by Groupe Bull.  All Rights Reserved */
/* $Id$ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */
/* Modified: Vincent Mallet (Vincent.Mallet@sophia.inria.fr)

package html.parser;

import java.util.*;
import java.io.*;
import java.net.URL;


/**
 * A Simple wrapper class to keep the original code clean and be able to access
 * package-private variables and methods.
 */

public class JmlParser extends html.parser.Parser {
  static final String defaultDTD = "html2-net";
  String dtdname;
    
  public JmlParser() {
    this(defaultDTD);
  }
    
  public JmlParser(String dtdn) throws ParserException {
	
    dtdname=dtdn;

    // Load properties
    DTD.props = new Properties();
    try {
      URL url = getClass().getResource("properties");
      DTD.props.load(url.openStream());
    } catch (Exception e) {
      throw new ParserException("Failed to load properties..."); //vm970808
      
      //vm970808       System.out.println("Failed to load properties...");
      //vm970808       e.printStackTrace();
      //vm970808       System.exit(1);
    }
	
    // load the right DTD
    DTD.props = new Properties(DTD.props);
    try {
      dtd = DTD.getDTD(dtdname);
    }
    catch(IOException x){
      throw new ParserException("Failed to load dtd...");
      //vm970808 System.out.println("Failed to load dtd...");
      //vm970808 x.printStackTrace();
      //vm970808 System.exit(1);
    }
  }

  //     public void parse(InputStream in) {
  // 	parse(in, dtd);
  //     }

  public void parseFile(String filename) throws ParserException {
    InputStream in;
    try {
      if (filename.indexOf(':') > 0) {
	in = new URL(null, filename).openStream();
      } else {
	in = new java.io.BufferedInputStream(new java.io.FileInputStream(filename));
      }
      try {
	long tm = System.currentTimeMillis();
	parse(in, dtd);
		
	tm = System.currentTimeMillis() - tm;
	System.out.println("[Parsed " +filename+ " in " + tm + "ms]");
      }
      catch(Exception e) {
	throw new ParserException("Uncaught error while parsing");

	//vm970808 System.out.println("uncaught error while parsing");
	//vm970808 e.printStackTrace();
	//vm970808 System.exit(1);
      }
	    
    } catch (Exception e) {
      throw new ParserException("Failed to open: " + filename); //vm970808

      //vm970808 e.printStackTrace();
      //vm970808 System.out.println("failed to open: " + filename);
      //vm970808 System.exit(1);
    }
  }
}


