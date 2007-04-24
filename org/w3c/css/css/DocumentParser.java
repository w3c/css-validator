//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.HTTPURL;
import org.w3c.css.util.Util;

import org.w3c.www.mime.MimeType;
import org.w3c.www.mime.MimeTypeFormatException;

/**
 * @version $Revision$
 */
public final class DocumentParser {

    private StyleSheet style;
    private URL htmlURL;
    private Exception exception;
    private ApplContext ac;

    /**
     * Create a new DocumentParser
     *
     * @exception Exception An error
     */
    public DocumentParser(ApplContext ac, String urlString)
	throws Exception {
	this.htmlURL = HTTPURL.getURL(urlString);
	this.ac = ac;
	urlString = htmlURL.toString();
	String urlLower = urlString.toLowerCase();
	String media = ac.getMedium();
	String urlProtocol = htmlURL.getProtocol();

	if (!"http".equals(urlProtocol) && !"https".equals(urlProtocol)) {	    	    
	    if (urlLower.endsWith(".css")) {
		StyleSheetParser parser = new StyleSheetParser();
		parser.parseURL(ac, htmlURL, null, null, media,
				StyleSheetOrigin.AUTHOR);
		style = parser.getStyleSheet();
	    } else if (urlLower.endsWith(".html")
		       || urlLower.endsWith(".shtml")
		       || urlLower.endsWith("/")) {
		TagSoupStyleSheetHandler handler;
		handler = new TagSoupStyleSheetHandler(htmlURL, ac);
		handler.parse(htmlURL);
		style = handler.getStyleSheet();
		if (style != null) {
		    style.setType("text/html");
		}
	    } else if (urlLower.endsWith(".xhtml") 
		       || urlLower.endsWith(".xml")) {
		XMLStyleSheetHandler handler;
		handler = new XMLStyleSheetHandler(htmlURL, ac);
		handler.parse(htmlURL);
		style = handler.getStyleSheet();
		if (style != null) {
		    style.setType("text/xml");
		}	
	    } else {
		throw new Exception("Unknown file");
	    }	    
	} else {
	    URLConnection connection = null;

	    try {
		boolean isXML = false;
		String cType;

		// @@ hum, maybe? (plh, yes probably :-) )
		String credential = ac.getCredential();

		connection = HTTPURL.getConnection(htmlURL, ac);
		htmlURL = connection.getURL();

		String httpCL = connection.getHeaderField("Content-Location");
		if (httpCL != null) {
		    htmlURL = HTTPURL.getURL(htmlURL, httpCL);
		}

		cType = connection.getContentType();
		if (cType == null) {
		    cType = "unknown/unknown";
		}
		MimeType contentType = null;
		try {
		    contentType = new MimeType(cType);
		} catch (MimeTypeFormatException ex) {
		}

		if (Util.onDebug) {
		    System.err.println( "[DEBUG] content type is [" +
					contentType + ']');
		}

		if (contentType.match(MimeType.TEXT_HTML) ==
		                           MimeType.MATCH_SPECIFIC_SUBTYPE) {
		    TagSoupStyleSheetHandler handler;
		    handler = new TagSoupStyleSheetHandler(htmlURL, ac);
		    handler.parse(urlString, connection);
		    style = handler.getStyleSheet();		    
		    
		    if (style != null) {			
			style.setType("text/html");
		    }
		} else if (contentType.match(MimeType.TEXT_CSS) ==
			                   MimeType.MATCH_SPECIFIC_SUBTYPE ) {
		    StyleSheetParser parser = new StyleSheetParser();
		    parser.parseURL(ac, htmlURL, null, null, media,
				    StyleSheetOrigin.AUTHOR);
		    style = parser.getStyleSheet();
		} else if ((contentType.match(MimeType.TEXT_XML) ==
		                            MimeType.MATCH_SPECIFIC_SUBTYPE) ||
		    (contentType.match(MimeType.APPLICATION_XHTML_XML) ==
		                            MimeType.MATCH_SPECIFIC_SUBTYPE)) {
		    XMLStyleSheetHandler handler;
		    handler = new XMLStyleSheetHandler(htmlURL, ac);
		    handler.parse(urlString, connection);
		    style = handler.getStyleSheet();		    
		    
		    if (style != null) {			
			style.setType("text/xml");
		    }
		} else {
		    throw new IOException("Unknown mime type : "+ contentType);
		}
	    } finally {
		try {
		    connection.getInputStream().close();
		} catch (Exception e) {}
	    }
	}
    }

    /**
     * Returns the recognized style sheet.
     * @return A style sheet.
     */
    public StyleSheet getStyleSheet() {
	return style;
    }

} // HTMLStyleSheetParser
