/*
 * Copyright (c) 2001 World Wide Web Consortium,
 * (Massachusetts Institute of Technology, Institut National de
 * Recherche en Informatique et en Automatique, Keio University). All
 * Rights Reserved. This program is distributed under the W3C's Software
 * Intellectual Property License. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.
 * See W3C License http://www.w3.org/Consortium/Legal/ for more details.
 *
 * $Id$
 */
package org.w3c.css.css;

import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.Locator;
import org.xml.sax.InputSource;

import java.net.URL;
import java.net.MalformedURLException;
import java.io.StringBufferInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLConnection;

import org.w3c.css.util.Util;
import org.w3c.css.util.HTTPURL;
import org.w3c.css.util.ApplContext;

/**
 * @version $Revision$
 * @author  Philippe Le Hegaret
 */
public class XMLStyleSheetHandler implements ContentHandler, LexicalHandler {

    private static long autoIdCount;

    String namespaceURI;
    boolean isRoot = true;

    ApplContext    ac;
    URL documentURI = null;
    URL baseURI = null;

    //  StyleSheet styleSheet = new StyleSheet(); 
    StyleSheetParser styleSheetParser = new StyleSheetParser(); 

    boolean inStyle = false;
    String media  = null;
    String type  = null;
    String title = null;
    StringBuffer text = new StringBuffer(255);

    Locator locator;

    /**
     * Creates a new XMLStyleSheetHandler
     */
    public XMLStyleSheetHandler(URL baseURI, ApplContext ac) {
	this.documentURI = baseURI;
	this.baseURI     = baseURI;
	this.ac = ac;
    }
    
    public void setDocumentLocator (Locator locator) {
	this.locator = locator;
    }

    public void startDocument ()
        throws SAXException {
    }

    public void endDocument()
        throws SAXException {
	ac.setInput("text/xml");
    }

    public void startPrefixMapping (String prefix, String uri)
        throws SAXException {
    }

     public void endPrefixMapping (String prefix)
	 throws SAXException {
     }
   
    public void characters (char ch[], int start, int length)
        throws SAXException {
	if (inStyle) {
	    text.append(ch, start, length);
	}
    }

    public void comment (char ch[], int start, int length)
        throws SAXException {
	if (inStyle) {
	    text.append(ch, start, length);
	}
    }

    public void ignorableWhitespace (char ch[], int start, int length)
        throws SAXException {
    }

    public void processingInstruction (String target, String data)
        throws SAXException {
    }
    
    public void skippedEntity (String name)
        throws SAXException {
    }

    public void startElement(String namespaceURI,
			     String localName,
			     String qName,
			     Attributes atts) throws SAXException {
	if (isRoot) {
	    this.namespaceURI = namespaceURI;
	    isRoot = false;
	}
	if ("http://www.w3.org/1999/xhtml".equals(namespaceURI)) {
	    if ("base".equals(localName)) {
		String href = atts.getValue("href");
	
		if (Util.onDebug) {
		    System.out.println("BASE href=\"" + href + "\"");
		}
	
		if (href != null) {
		    URL url;
		    
		    try {
			baseURI = new URL(documentURI, href); 
		    } catch (MalformedURLException e) {
			return; // Ignore errors
		    }
		}
		
	    } else if ("link".equals(localName)) {

		String rel  = atts.getValue("rel");
		String type  = atts.getValue("type");
		String href = atts.getValue("href");
		
		if (Util.onDebug) {
		    System.out.println("link rel=\"" + rel 
				       + "\" type=\"" + type
				       + "\"" + "   href=\"" + href + "\"");
		}
	
		if (((rel != null) && rel.toLowerCase().indexOf("stylesheet") != -1)
		    || ((type != null) && type.equals("text/css"))) {
		    // we're dealing with a stylesheet...
		    URL url;
		    
		    try { 
			if (baseURI != null) {
			    url = new URL(baseURI, href); 
			} else {
			    url = new URL(href); 
			}
		    } catch (MalformedURLException e) {
			return; // Ignore errors
		    }
		    
		    if (Util.onDebug) {
			System.out.println("[XMLStyleSheetHandler::initialize(): "
					   + "should parse CSS url: " 
					   + url.toString() + "]");
		    }
		    String media = atts.getValue(null, "media");
		    if (media == null) {
			media="all";
		    }
		    styleSheetParser.parseURL(ac,
					      url, 
					      atts.getValue(null, "title"),
					      rel,
					      media,
					      StyleSheetOrigin.AUTHOR);
		    if (Util.onDebug) {
			System.out.println("[parsed!]");
		    }
		}
	    } else if ("style".equals(localName)) {
		media  = atts.getValue("media");
		type  = atts.getValue("type");
		title = atts.getValue("title");
		
		if (media == null) {
		    media = "all";
		}
		if (Util.onDebug) {
		    System.out.println("style media=\"" + media
				       + "\" type=\"" + type
				       + "\"" + "   title=\"" + title + "\"");
		}
	
		text.setLength(0);
		inStyle = true;
	    } else if (atts.getValue(null, "style") != null) {
		String value = atts.getValue("style");

		if (value != null) { // here we have a style attribute
		    String id = atts.getValue("id");
		    handleStyleAttribute(value, id);
		}		
	    }
	} else {
	    String value = atts.getValue("http://www.w3.org/1999/xhtml", "style");
	    
	    if (value != null) { // here we have a style attribute
		String id = atts.getValue("http://www.w3.org/1999/xhtml", "id");
		handleStyleAttribute(value, id);
	    }		
	}
    }
    
    public void endElement (String namespaceURI, String localName,
                            String qName)
        throws SAXException {
	int line = 0;

	if (locator != null) {
	    line = locator.getLineNumber();
	}
	if ("http://www.w3.org/1999/xhtml".equals(namespaceURI)) {
	    if ("style".equals(localName)) {
		inStyle = false;
		if (text.length() != 0) {
		    if ((type == null) || type.equals("text/css")) {
			if (Util.onDebug) {
			    System.err.println( "PARSE [" + text.toString() + "]" );
			}
			styleSheetParser
			    .parseStyleElement(ac,
					       new StringBufferInputStream(text.toString()), 
					       title, media, 
					       documentURI, line);
	    }
	}
		
	    }
	}
    }

    public void handleStyleAttribute(String value, String id) {
	if (id == null) { // but we have no id: create one.
	    // a normal id should NOT contain a "#" character.
	    id = "#autoXML" + autoIdCount; 
	    // workaround a java hashcode bug.
	    id += "" + autoIdCount++;   
	}
	int line = 0;
	if (locator != null) {
	    line = locator.getLineNumber();
	}
	// parse the style attribute.
	styleSheetParser
	    .parseStyleAttribute(ac,
				 new ByteArrayInputStream(value.getBytes()), 
				 id, documentURI, line);
    }

    public StyleSheet getStyleSheet() {
	return styleSheetParser.getStyleSheet();
    }        

    public void startDTD (String name, String publicId,
                                   String systemId)
        throws SAXException {
    }

    public void endDTD ()
        throws SAXException {
    }

    public void startEntity (String name)
        throws SAXException {
    }

    public void endEntity (String name)
        throws SAXException {
    }

    public void startCDATA ()
        throws SAXException {
    }

    public void endCDATA ()
        throws SAXException {
    }

    void parse(URL url, String credential) throws Exception {
	InputSource source = new InputSource();
	URLConnection connection;
	InputStream in;
	org.xml.sax.XMLReader xmlParser = new org.apache.xerces.parsers.SAXParser();
	try {
	    xmlParser.setProperty("http://xml.org/sax/properties/lexical-handler",
				  this);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	xmlParser.setContentHandler(this);

	connection = HTTPURL.getConnection(url, credential);
	in = connection.getInputStream();
	source.setByteStream(in);
	try {
	    xmlParser.parse(url.toString());
	} finally {
	    in.close();
	}
    }

    void parse(String urlString, InputStream input) throws Exception {
	org.xml.sax.XMLReader xmlParser = new org.apache.xerces.parsers.SAXParser();
	try {
	    xmlParser.setProperty("http://xml.org/sax/properties/lexical-handler",
				  this);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	xmlParser.setContentHandler(this);
	InputSource source = new InputSource(input);
	source.setSystemId(urlString);
	xmlParser.parse(source);
    }
}
