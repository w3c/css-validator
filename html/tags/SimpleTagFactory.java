/* Copyright (c) 1996 by Groupe Bull.  All Rights Reserved */
/* $Id$ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tags;

import html.parser.*;
import html.tree.*;

import java.util.*;
import java.io.*;
import java.net.URL;

/**
 * Implements a simple algorithm to build tags from a property database.
 *
 * 1 - try to get a user-specified class name from a property named tag.<tag>.
 * 2 - else, builds a default name html.tags.<tag> Then try to load the
 * corresponding Class an instanciate it. The class instance returned must be a
 * subclass of HtmlTree.
 */
public class SimpleTagFactory implements TagFactory {
    
    Hashtable tagTable = new Hashtable();
    Properties props;
    
    public SimpleTagFactory() {
	java.io.InputStream f = null;
	props = new Properties();
	try {
	    URL url = 
		SimpleTagFactory.class.getResource("SimpleTagFactory.properties");
	    f = url.openStream();
	    props.load(f);
	} catch (Exception e) {
	    // we'll use defaults
	    if (!Boolean.getBoolean("html.runningServlet"))  //vm970813
		System.out.println("SimpleTagFactory: couldn't load properties");
	} finally {
	    try {
		f.close();
	    } catch (Exception e) {}
	}
    }
    
    
    public Tag create(String name) {
	Class tagClass;
	try {
	    // System.out.println("tagTable.get=" + name);
	    if((tagClass=(Class)tagTable.get(name)) == null) {
		String tagClassName = props.getProperty("tag."+name,
							"html.tags."+name);
		// System.out.println("loading "+tagClassName + " for " + name);
		tagClass = Class.forName(tagClassName);
		tagTable.put(name, tagClass);
	    }
	    HtmlTree tag = (HtmlTree)tagClass.newInstance();
	    return tag;
	}
	catch(InstantiationException x) {}
	catch(IllegalAccessException iax) {}
	catch(ClassNotFoundException x){}
	//	new Exception().printStackTrace();
	
	if (!Boolean.getBoolean("html.runningServlet")) {//vm970813
	    // System.out.println("warning: unknown tag: " + name);
	}
	
	HtmlTree tag = new Block();
	//Tag tag = new UnknownTag(elem, atts);
	return tag;
    }
}
