/* Copyright (c) 1996 by Groupe Bull.  All Rights Reserved */
/* $Id$ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */
/* Modified: Vincent.Mallet@sophia.inria.fr */

package html.tags;

import html.parser.*;
import html.tree.*;
import java.beans.*;

import java.util.*;
import java.io.*;
import java.net.*;

import org.w3c.css.css.*;
import org.w3c.css.util.HTTPURL;

import org.w3c.css.util.Util;
import org.w3c.css.util.ApplContext;

public class HtmlParser extends JmlParser implements Runnable,
    HtmlStreamListener
{

  /**
   * The currently parsed URL
   */
  URL         url          = null;  // 970718 -vm
  
  HtmlTree    current      = null;
  HtmlTree    top          = null;
  Vector      listeners    = new Vector();

  ParserFrame parserFrame  = new ParserFrame(); // 970724 -vm
  
  TagFactory  factory;
  String      urlname;

  public HtmlParser(ApplContext ac,
		    String dtdName, String urlname) 
      throws ParserException {
    super(dtdName);
    this.urlname = urlname;
    parserFrame.ac = ac;
    setFactory(new SimpleTagFactory());
  }
  
  public HtmlParser(ApplContext ac, String dtdName, 
		    TagFactory f) throws ParserException {
    super(dtdName);
    parserFrame.ac = ac;
    setFactory(new SimpleTagFactory());
  }
  
  public HtmlTree getRoot() {
    return top;
  }
  
  public void setFactory(TagFactory f) {
    factory = f;
  }
  
  public Tag makeTag(Element elem, Attributes atts) {
    HtmlTree tag = null;
    if(in_error_recovery) {
      tag = (HtmlTree)errorTagTable.get(elem.getName());
    }
    if (tag == null) {
      //	  System.out.println("Creation d'un tag: " + elem.getName());
      parserFrame.line = ln; // -vm
      tag = (HtmlTree)factory.create(elem.getName());
      tag.initialize(elem, atts, parserFrame);
    }
    return tag;
  }
  
  public void addParserListener(HtmlParserListener l) {
    listeners.addElement(l);
  }
  
  public void removeParserListener(HtmlParserListener l) {
    listeners.removeElement(l);
  }
  
  boolean in_error_recovery = false;
  Hashtable errorTagTable;
  protected void startErrorRecovery() {
    errorTagTable = new Hashtable();
    in_error_recovery = true;
  }
  
  protected void endErrorRecovery() {
    in_error_recovery = false;
  }
  
  public void handleStartTag(Tag tag) {
    if(in_error_recovery) {
      current = (HtmlTree)tag;
      return;
    }
    try {
      if(top == null) {
	top = (HtmlTree)tag;
	current = (HtmlTree)tag;
	for(int i = 0; i < listeners.size(); i++) {
	  HtmlParserListener l =
	    (HtmlParserListener)listeners.elementAt(i);
	  l.notifyCreateRoot(url, top); // 970718 -vm (added the url)
	}
	current.enter();
      }
      else {
	if(current == null) {
	  current = top;
	}
	HtmlTree child = (HtmlTree)tag;
	// FIXME: should this really happen ???
	if(current != null) {
	  current.attach(child, current.arity());
	  current = child;
	  current.enter();
	}
	else {
	  //throw new RuntimeException("should not happen");
	}
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    notifyActivity(ln, 0);
  }
  
  public void handleEndTag(Tag tag) {
    if(in_error_recovery) {
      errorTagTable.put(tag.getElement().getName(), tag);
    }
    else {
      //      try {
	// FIXME: should this really happen ???
	if(current != null) {
	  current.exit();
	  current = (HtmlTree)(current.getParent());
	}
//       }
//       catch(Exception e) {
// 	e.printStackTrace();
// 	System.exit(1);
//       }
    }
  }
  
  TextElement proto = new TextElement();
  
  
  Element p = new Element("p", 0);
  
  public void handleText(byte text[]) {
    // discard empty texts.
    if((text.length == 1) && (text[0] == ' ')) return;
    // to handle text, we build a factice Tag, containing the value of the
    // text as attribute.
    Attributes atts = new Attributes();
    atts.put(TextElement.TEXT, new String(text));
    HtmlTree tag = (HtmlTree)factory.create(TextElement.TEXT);
    parserFrame.line = ln;
    tag.initialize(proto, atts, parserFrame);
    //    System.out.println(" 000 HtmlParser::handleText  atts=" + atts);
    
    /*	if("body".equals(current.getElement().getName())) {
	// for text components outside a <p> in the body, we insert the <p>
	// component.
	HtmlTree parent = (HtmlTree)factory.create("p");
	parent.initialize(p, atts);
	
	current.attach(parent, current.arity());
	parent.attach((HtmlTree)tag, 0);
	}
	else */ {
      if(current != null) { // FIXME: should this really happen
	current.attach((HtmlTree)tag, current.arity());
      }
    }
  }
  
  public void handleEmptyTag(Tag tag) {
    //System.out.println("empty tag " + tag);
    if(current != null) {
      current.attach((HtmlTree)tag, current.arity());
    }
    else {
      System.out.println("ERROR: " + tag + "has no parent");
    }
  }


  public void run() {
    InputStream input = makeInput(urlname);
    if (input != null)
      input = new BufferedInputStream(input);

    try {
      long tm = System.currentTimeMillis();
      parse(input, dtd);
      tm = System.currentTimeMillis() - tm;
      if (Boolean.getBoolean("html.tags.verbose"))
	System.out.println("[Parsed " + urlname + " in " + tm + "ms]");
    }
    catch(XMLInputException e) {
      notifyFatalError(null, e, "");
    }
        
    catch(Exception e) {
      if (!Boolean.getBoolean("html.runningServlet")) {
	System.out.println("uncaught error while parsing");
	e.printStackTrace();
      }
      notifyFatalError(null, e, "");
    }
        
    for(int i = 0; i < listeners.size(); i++) {
      HtmlParserListener l =
	(HtmlParserListener)listeners.elementAt(i);
      l.notifyEnd(top, "text/html");
    }
    
    if (Boolean.getBoolean("html.tags.verbose")) {
      System.out.println("\n-------------------");
      System.out.println("[StyleSheet dump:]");
      parserFrame.styleSheetParser.getStyleSheet().dump();
      System.out.println("-------------------");
    }
  }
  
  public void notifyConnection(URLConnection cnx) {
    for(int i = 0; i < listeners.size(); i++) {
      HtmlParserListener l =
	(HtmlParserListener)listeners.elementAt(i);
      l.notifyConnection(cnx);
    }
  }
  
  InputStream makeInput(String urls) {
    InputStream in = null;

    try {
      if (urls.indexOf(':') > 0) {
	URLConnection urlC = null;
	// ugly hack for authentication
	String credential = parserFrame.ac.getCredential();
	urlC = HTTPURL.getConnection(new URL(null, urls), credential);

	parserFrame.url = url = urlC.getURL();
	in = urlC.getInputStream();
	HtmlInputStream hin = new HtmlInputStream(in);
	hin.addHtmlStreamListener(this);
	in = hin;
      }
    } catch (Exception e) {
	//      if (!Boolean.getBoolean("html.runningServlet")) {
      if (!Util.servlet) {
	e.printStackTrace();
	System.out.println("failed to open: " + urls);
      }
      in = null;
      notifyFatalError(null, e, "");
    }
    return in;
  }
  
  String makeURLName(String s) {
    System.out.println("makeURLName: " + s);
    if (s.indexOf(':') > 0) {
      return s;
    }
    else {
      return "file:" + s;
    }
  }
  
  void notifyFatalError(HtmlTree root, Exception x, String s) {
      for(int i = 0; i < listeners.size(); i++) {
	  HtmlParserListener l =
	      (HtmlParserListener)listeners.elementAt(i);
	  l.notifyFatalError(root, x, s);
      }
  }
  
  public void notifyActivity(int lines, long bytes) {
    for(int i = 0; i < listeners.size(); i++) {
      HtmlParserListener l =
	(HtmlParserListener)listeners.elementAt(i);
      l.notifyActivity(ln, bytes);
    }
    
  }
}
