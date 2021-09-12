//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.HTTPURL;
import org.w3c.css.util.Util;
import org.w3c.www.mime.MimeType;
import org.w3c.www.mime.MimeTypeFormatException;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @version $Revision$
 */
public final class DocumentParser {

    public static MimeType wap;

    static {
        try {
            wap = new MimeType("application/vnd.wap.xhtml+xml");
        } catch (MimeTypeFormatException mex) {
            wap = null;
        }
    }

    private StyleSheet style;
    private URL htmlURL;
    private Exception exception;
    private ApplContext ac;

    public DocumentParser(ApplContext ac, Reader reader) throws Exception {
        this(ac, reader, "urn:unknown", null);
    }

    public DocumentParser(ApplContext ac, Reader reader, String urlString, MimeType mediatype) throws Exception {
        this.htmlURL = HTTPURL.getURL(urlString);
        this.ac = ac;
        String media = ac.getMedium();

        if (mediatype == null) {
            mediatype = MimeType.TEXT_CSS;
        }
        if (mediatype.match(MimeType.TEXT_CSS) == MimeType.MATCH_SPECIFIC_SUBTYPE) {
            StyleSheetParser csshandler = new StyleSheetParser(ac);
            csshandler.parseStyleSheet(ac, reader, htmlURL);
            style = csshandler.getStyleSheet();
        } else if (mediatype.match(MimeType.TEXT_HTML) == MimeType.MATCH_SPECIFIC_SUBTYPE) {
            //TagSoupStyleSheetHandler htmlhandler = new TagSoupStyleSheetHandler(htmlURL, ac);
            HTMLParserStyleSheetHandler htmlhandler = new HTMLParserStyleSheetHandler(htmlURL, ac);
            htmlhandler.parse(reader);
            style = htmlhandler.getStyleSheet();
            if (style != null) {
                style.setType("text/html");
            }
        } else if (mediatype.toString().endsWith("+xml") ||
                (mediatype.match(MimeType.APPLICATION_XML) == MimeType.MATCH_SPECIFIC_SUBTYPE)) {
            XMLStyleSheetHandler xmlhandler = new XMLStyleSheetHandler(htmlURL, ac);
            xmlhandler.parse(reader);
            style = xmlhandler.getStyleSheet();
            if (style != null) {
                style.setType("text/xml");
            }
        }
    }

    /**
     * Create a new DocumentParser
     *
     * @throws Exception An error
     */
    public DocumentParser(ApplContext ac, String urlString) throws Exception {
        this.htmlURL = HTTPURL.getURL(urlString);
        this.ac = ac;
        urlString = htmlURL.toString();
        String urlLower = urlString.toLowerCase();
        String media = ac.getMedium();
        String urlProtocol = htmlURL.getProtocol();

        if (!"http".equals(urlProtocol) && !"https".equals(urlProtocol)) {
            if (urlLower.endsWith(".css")) {
                StyleSheetParser parser = new StyleSheetParser(ac);
                parser.parseURL(ac, htmlURL, null, null, media, StyleSheetOrigin.AUTHOR);
                style = parser.getStyleSheet();
            } else if (urlLower.endsWith(".html") || urlLower.endsWith(".htm") ||
                    urlLower.endsWith(".shtml") || urlLower.endsWith("/")) {
                //TagSoupStyleSheetHandler handler = new TagSoupStyleSheetHandler(htmlURL, ac);
                HTMLParserStyleSheetHandler handler = new HTMLParserStyleSheetHandler(htmlURL, ac);
                handler.parse(htmlURL);
                style = handler.getStyleSheet();
                if (style != null) {
                    style.setType("text/html");
                }
            } else if (urlLower.endsWith(".xhtml") || urlLower.endsWith(".xml")) {
                // Seems like we need to use tagsout in this case as well
                XMLStyleSheetHandler handler = new XMLStyleSheetHandler(htmlURL, ac);
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
                    // an error, let's try to parse it as HTML
                    contentType = MimeType.TEXT_HTML;
                }

                if (Util.onDebug) {
                    System.err.println("[DEBUG] content type is [" + contentType + ']');
                }

                if (contentType.match(MimeType.TEXT_HTML) == MimeType.MATCH_SPECIFIC_SUBTYPE) {
                    HTMLParserStyleSheetHandler handler;
                    handler = new HTMLParserStyleSheetHandler(htmlURL, ac);
//                    TagSoupStyleSheetHandler handler;
//                    handler = new TagSoupStyleSheetHandler(htmlURL, ac);
                    handler.parse(urlString, connection);
                    style = handler.getStyleSheet();

                    if (style != null) {
                        style.setType("text/html");
                    }
                } else if (contentType.match(MimeType.TEXT_CSS) == MimeType.MATCH_SPECIFIC_SUBTYPE) {
                    StyleSheetParser parser = new StyleSheetParser(ac);
                    parser.parseURL(ac, htmlURL, null, null, media, StyleSheetOrigin.AUTHOR);
                    style = parser.getStyleSheet();
                } else if ((contentType.match(MimeType.TEXT_XML) == MimeType.MATCH_SPECIFIC_SUBTYPE)
                        || (contentType.match(MimeType.APPLICATION_XHTML_XML) == MimeType.MATCH_SPECIFIC_SUBTYPE)
                        || (contentType.match(wap) == MimeType.MATCH_SPECIFIC_SUBTYPE)) {
                    // TagSoup ?
                    XMLStyleSheetHandler handler = new XMLStyleSheetHandler(htmlURL, ac);
                    handler.parse(urlString, connection);
                    style = handler.getStyleSheet();

                    if (style != null) {
                        style.setType("text/xml");
                    }
                } else {
                    throw new IOException("Unknown mime type : " + contentType);
                }
            } finally {
                try {
                    connection.getInputStream().close();
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Returns the recognized style sheet.
     *
     * @return A style sheet.
     */
    public StyleSheet getStyleSheet() {
        return style;
    }

} // HTMLStyleSheetParser
