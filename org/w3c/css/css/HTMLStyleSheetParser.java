//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:16:38  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:23:27  plehegar
 * Freeze
 *
 */
package org.w3c.css.css;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
// import org.w3c.www.protocol.http.HttpURLConnection;

import html.tags.HtmlTree;
import html.tags.HtmlParser;
import html.tags.HtmlParserListener;
import html.tags.HtmlTag;

import org.w3c.css.util.Util;
import org.w3c.css.util.HTTPURL;
import org.w3c.css.util.ApplContext;

/**
 * @version $Revision$
 */
public final class HTMLStyleSheetParser implements HtmlParserListener {
    
    private StyleSheet style;
    private URL htmlURL;
    private Exception exception;
    private ApplContext ac;

    /**
     * Create a new HTMLStyleSheetParser
     *
     * @exception Exception An error
     */
    public HTMLStyleSheetParser(ApplContext ac, String urlString)
	throws Exception {
	this.htmlURL = HTTPURL.getURL(urlString);
	this.ac = ac;
	urlString = htmlURL.toString();
	String urlLower = urlString.toLowerCase();
	String media = ac.getMedium();

	if (!"http".equals(htmlURL.getProtocol())) {
	    if (urlLower.endsWith(".css")) {
		StyleSheetParser parser = new StyleSheetParser();
		parser.parseURL(ac, htmlURL, null, null, media, 
				StyleSheetOrigin.AUTHOR);
		style = parser.getStyleSheet();
	    } else if (urlLower.endsWith(".html")
		       || urlLower.endsWith(".shtml")
		       || urlLower.endsWith("/")) {
		HtmlParser htmlParser = new HtmlParser(ac, "html4", urlString);
		try {
		    Util.fromHTMLFile = true;
		    htmlParser.addParserListener(this);
		    htmlParser.run();
		    if (exception != null) {
			throw (Exception) exception.fillInStackTrace();
		    }
		} catch (html.parser.XMLInputException e) {
		    XMLStyleSheetHandler handler = new XMLStyleSheetHandler(htmlURL, ac);
		    handler.parse(htmlURL);
		    style = handler.getStyleSheet();
		    if (style != null) {
			style.setType("text/xml");
		    }
		    if (Util.onDebug) {
			e.printStackTrace();
		    }
		} finally {
		    Util.fromHTMLFile = false;
		}
	    }
	    throw new Exception("Unknown file");
	} else {
	    URLConnection connection = null;
	    
	    try {
		boolean isXML = false;
		String contentType;

		// @@ hum, maybe? (plh, yes probably :-) )
		String credential = ac.getCredential();

		connection = HTTPURL.getConnection(htmlURL, ac);

		contentType = connection.getContentType();
		if (contentType == null) {
		    contentType = "unknown";
		}
		contentType = contentType.toLowerCase();
		if (Util.onDebug) {
		    System.err.println( "[DEBUG] content type is [" + contentType + ']');
		}

		if (contentType.indexOf("text/html") != -1) {
		    HtmlParser htmlParser = new HtmlParser(ac, "html4", urlString);
		    try {
			Util.fromHTMLFile = true;
			htmlParser.addParserListener(this);
			htmlParser.run();
			
			if (exception != null) {
			    throw (Exception) exception.fillInStackTrace();
			}
		    } catch (html.parser.XMLInputException e) {
			isXML = true;
		    } finally {
			Util.fromHTMLFile = false;
		    }
		} else if (contentType.indexOf("text/css") != -1) {
		    StyleSheetParser parser = new StyleSheetParser();
		    parser.parseURL(ac, htmlURL, null, null, media, 
				    StyleSheetOrigin.AUTHOR);
		    style = parser.getStyleSheet();
		} else if (contentType.indexOf("text/xml") == -1) {
		    throw new IOException("Unknown mime type : " + contentType);
		}
		
		if ((contentType.indexOf("text/xml") != -1) || isXML
		    || (contentType.indexOf("application/xhtml+xml") != -1)) {
		    XMLStyleSheetHandler handler = new XMLStyleSheetHandler(htmlURL, ac);
		    handler.parse(urlString, connection.getInputStream());
		    style = handler.getStyleSheet();
		    if (style != null) {
			style.setType("text/xml");
		    }		    
		}	    
	    } finally {
		try {
		    connection.getInputStream().close();
		} catch (Exception e) {}
	    }
	}
    }  
    
    /**
     * Notifies root creation.
     *
     * Sent when the parser builds the root of the HTML tree.
     *
     * @param url the URL being parsed.
     * @param root the new root Tag for this parser.
     */    
    public void notifyCreateRoot(URL url, HtmlTag root) {
    }
    
    public void notifyActivity(int lines, long bytes) {
    }
    
    public void notifyConnection(URLConnection cnx) {
    }
    
    /**
     * Notifies successful termination.
     *
     * @param root the root of the current Tree.
     */    
    public void notifyEnd(HtmlTag root, String contentType) {
	if (root != null) {
	    style = ((HtmlTree) root).getStyleSheet();
	    if (style != null) {
		style.setType("text/html");
	    }
	}
	ac.setInput(contentType);
    }
    
    /**
     * Notifies a fatal error.
     *
     * This notification is sent when the parser need to stop during or before
     * parsing, due to an unexpected exception.
     *
     * @param root the root of the current Tree, if any.
     * @param x the exception that cause the Parser stop
     * @param msg an error message information
     */
    public void notifyFatalError(HtmlTag root, Exception x, String s) {
	exception = x;
    }
    
    /**
     * Returns the recognized style sheet.
     * @return A style sheet.
     */  
    public StyleSheet getStyleSheet() {
	return style;
    }

} // HTMLStyleSheetParser
