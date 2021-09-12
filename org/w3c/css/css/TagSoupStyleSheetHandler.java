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

import org.w3c.css.parser.CssError;
import org.w3c.css.parser.Errors;
import org.w3c.css.parser.analyzer.TokenMgrError;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.CssVersion;
import org.w3c.css.util.HTTPURL;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;
import org.w3c.css.util.xml.XMLCatalog;
import org.w3c.www.mime.MimeType;
import org.w3c.www.mime.MimeTypeFormatException;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.LexicalHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * @author Philippe Le Hegaret
 * @version $Revision$
 */
public class TagSoupStyleSheetHandler implements ContentHandler, LexicalHandler,
        ErrorHandler, EntityResolver {

    static String XHTML_NS = "http://www.w3.org/1999/xhtml";

    private static long autoIdCount;

    String namespaceURI;
    boolean isRoot = true;

    ApplContext ac;
    URL documentURI = null;
    URL baseURI = null;

    StyleSheetParser styleSheetParser = null;

    boolean inStyle = false;
    String media = null;
    String type = null;
    String title = null;
    String charset = null;
    StringBuilder text = new StringBuilder();

    Locator locator;

    static XMLCatalog catalog = new XMLCatalog();

    /**
     * Creates a new TagSoupStyleSheetHandler
     */
    public TagSoupStyleSheetHandler(URL baseURI, ApplContext ac) {
        this.documentURI = baseURI;
        this.baseURI = baseURI;
        this.ac = ac;
        this.styleSheetParser = new StyleSheetParser(ac);
    }

    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    public void startDocument()
            throws SAXException {
    }

    public void endDocument()
            throws SAXException {
        ac.setInput("text/xml");
    }

    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {
    }

    public void endPrefixMapping(String prefix)
            throws SAXException {
    }

    public void characters(char ch[], int start, int length)
            throws SAXException {
        if (inStyle) {
            text.append(ch, start, length);
        }
    }

    public void comment(char ch[], int start, int length)
            throws SAXException {
        if (inStyle) {
            int line = (locator != null ? locator.getLineNumber() : -1);
            Warning w = new Warning(baseURI.toString(), line,
                    "style-inside-comment", 0, ac);
            Warnings warnings = new Warnings(ac.getWarningLevel());
            warnings.addWarning(w);
            styleSheetParser.notifyWarnings(warnings);
            //text.append(ch, start, length); // ignoring, per http://www.w3.org/Bugs/Public/show_bug.cgi?id=761
        }
    }

    public void ignorableWhitespace(char ch[], int start, int length)
            throws SAXException {
    }

    public void processingInstruction(String target, String data)
            throws SAXException {
        HashMap<String, String> atts = getValues(data);

        if ("xml-stylesheet".equals(target)) {
            String rel = atts.get("alternate");
            String type = atts.get("type");
            String href = atts.get("href");

            if (Util.onDebug) {
                System.err.println("<?xml-stylesheet alternate=\"" + rel
                        + "\" type=\"" + type
                        + "\"" + "   href=\"" + href + "\"?>");
            }

            if ("yes".equalsIgnoreCase(rel)) {
                rel = "alternate stylesheet";
            } else {
                rel = "stylesheet";
            }
            if (href == null) {
                int line = -1;

                if (locator != null) {
                    line = locator.getLineNumber();
                }
                CssError er =
                        new CssError(baseURI.toString(), line,
                                new InvalidParamException("unrecognized.link",
                                        ac));
                Errors ers = new Errors();
                ers.addError(er);
                styleSheetParser.notifyErrors(ers);
            }

            if (href.charAt(0) == '#') {
                // internal style sheet, will be processed by the parser
                return;
            }

            if (type != null) {
                MimeType mt = null;
                try {
                    mt = new MimeType(type);
                } catch (Exception ex) { /* at worst, null */ }
                if (mt != null && (MimeType.TEXT_CSS.match(mt) ==
                        MimeType.MATCH_SPECIFIC_SUBTYPE)) {
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
                        System.err.println("[TagSoupStyleSheetHandler::" +
                                "initialize(): "
                                + "should parse CSS url: "
                                + url.toString() + "]");
                    }
                    String media = atts.get("media");
                    if (media == null && ac.getCssVersion() != CssVersion.CSS1) {
                        media = "all";
                    }
                    styleSheetParser.parseURL(ac,
                            url,
                            atts.get("title"),
                            rel,
                            media,
                            StyleSheetOrigin.AUTHOR);
                    if (Util.onDebug) {
                        System.err.println("[parsed!]");
                    }
                }
            }
        }
    }

    public void skippedEntity(String name)
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
        if (XHTML_NS.equals(namespaceURI)) {
            if ("base".equals(localName)) {
                String href = atts.getValue("href");

                if (Util.onDebug) {
                    System.err.println("BASE href=\"" + href + "\"");
                }

                if (href != null) {
                    //URL url;

                    try {
                        baseURI = new URL(documentURI, href);
                        documentURI = baseURI;
                    } catch (MalformedURLException e) {
                        return; // Ignore errors
                    }
                }

            } else if ("link".equals(localName)) {

                String rel = atts.getValue("rel");
                String type = atts.getValue("type");
                String href = atts.getValue("href");

                if (Util.onDebug) {
                    System.err.println("TAGSOUP: link rel=\"" + rel
                            + "\" type=\"" + type
                            + "\"" + "   href=\"" + href + "\"");
                }


                if ((rel != null) &&
                        rel.toLowerCase().indexOf("stylesheet") != -1) {
                    // we're dealing with a stylesheet...
                    // @@TODO alternate stylesheet
                    URL url;

                    // first we check if there is an href
                    if (href == null) {
                        int line = -1;

                        if (locator != null) {
                            line = locator.getLineNumber();
                        }
                        CssError er =
                                new CssError(baseURI.toString(), line,
                                        new InvalidParamException("unrecognized.link", ac));
                        Errors ers = new Errors();
                        ers.addError(er);
                        styleSheetParser.notifyErrors(ers);
                        return;
                    }
                    // If so, check the type
                    if (type == null) {
                        int line = (locator != null ? locator.getLineNumber() : -1);
                        Warning w = new Warning(baseURI.toString(), line,
                                "link-type", 0, ac);
                        Warnings warnings = new Warnings(ac.getWarningLevel());
                        warnings.addWarning(w);
                        styleSheetParser.notifyWarnings(warnings);
                    } else {
                        MimeType mt = null;
                        try {
                            mt = new MimeType(type);
                        } catch (MimeTypeFormatException mtfe) {
                            return;
                        }
                        if (MimeType.TEXT_CSS.match(mt) !=
                                MimeType.MATCH_SPECIFIC_SUBTYPE) {
                            return;
                        }
                    }
                    // then prepare for parsing
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
                        System.err.println("[TagSoupStyleSheetHandler::initialize(): "
                                + "should parse CSS url: "
                                + url.toString() + "]");
                    }
                    String media = atts.getValue("media");
                    if (media == null && ac.getCssVersion() != CssVersion.CSS1) {
                        media = "all";
                    }
                    styleSheetParser.parseURL(ac,
                            url,
                            atts.getValue("title"),
                            rel,
                            media,
                            StyleSheetOrigin.AUTHOR);
                    if (Util.onDebug) {
                        System.err.println("[parsed!]");
                    }
                }
            } else if ("style".equals(localName)) {
                media = atts.getValue("media");
                type = atts.getValue("type");
                title = atts.getValue("title");

                if (media == null && ac.getCssVersion() != CssVersion.CSS1) {
                    media = "all";
                }
                if (Util.onDebug) {
                    System.err.println("style media=\"" + media
                            + "\" type=\"" + type
                            + "\"" + "   title=\"" + title + "\"");
                }

                if (type == null) {
                    // By default we consider that it is CSS for HTML content
                    text.setLength(0);
                    inStyle = true;
                } else {
                    try {
                        MimeType mt = new MimeType(type);
                        if (MimeType.TEXT_CSS.match(mt) ==
                                MimeType.MATCH_SPECIFIC_SUBTYPE) {
                            text.setLength(0);
                            inStyle = true;
                        }
                    } catch (MimeTypeFormatException ex) {
                        // do nothing
                    }
                }
            } else if (atts.getValue("style") != null) {
                String value = atts.getValue("style");

                if (value != null) { // here we have a style attribute
                    String id = atts.getValue("id");
                    handleStyleAttribute(value, id);
                }
            }
        } else {
            // the style attribute, recommended by UI Tech TF
            String value = atts.getValue(XHTML_NS, "style");

            if (value != null) { // here we have a style attribute
                String id = atts.getValue(XHTML_NS, "id");
                handleStyleAttribute(value, id);
            }
        }
    }

    public void endElement(String namespaceURI, String localName,
                           String qName)
            throws SAXException {
        int line = 0;

        if (locator != null) {
            line = locator.getLineNumber();
        }
        if (XHTML_NS.equals(namespaceURI)) {
            if ("style".equals(localName)) {
                if (inStyle) {
                    inStyle = false;
                    if (text.length() != 0) {
                        if (Util.onDebug) {
                            System.err.println("PARSE [" + text.toString() + "]");
                        }
                        styleSheetParser
                                .parseStyleElement(ac,
                                        new StringReader(text.toString()),
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
            StringBuilder sb = new StringBuilder("#autoXML");
            sb.append(autoIdCount);
            // FIXME why two times?
            sb.append(autoIdCount++);
            id = sb.toString();
        }
        int line = 0;
        if (locator != null) {
            line = locator.getLineNumber();
        }
        // parse the style attribute.
        try {
            styleSheetParser
                    .parseStyleAttribute(ac,
                            new ByteArrayInputStream(value.getBytes()),
                            id, documentURI, line);
        } catch (TokenMgrError ex) {
            CssError err = new CssError(baseURI.toString(), line,
                    ex);
            Errors errs = new Errors();
            errs.addError(err);
            styleSheetParser.notifyErrors(errs);
        }
    }

    public StyleSheet getStyleSheet() {
        return styleSheetParser.getStyleSheet();
    }

    public void startDTD(String name, String publicId,
                         String systemId)
            throws SAXException {
    }

    public void endDTD()
            throws SAXException {
    }

    public void startEntity(String name)
            throws SAXException {
    }

    public void endEntity(String name)
            throws SAXException {
    }

    public void startCDATA()
            throws SAXException {
    }

    public void endCDATA()
            throws SAXException {
    }

    public void error(SAXParseException exception) throws SAXException {
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        throw exception;
    }

    public void warning(SAXParseException exception) throws SAXException {
    }

    public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException, IOException {
        String uri = null;

        if (publicId != null) {
            if ("-//W3C//DTD XHTML 1.0 Transitional//EN".equals(publicId)) {
                if (!"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd".equals(systemId)) {
                    if (ac != null && ac.getFrame() != null) {
                        ac.getFrame().addWarning("xhtml.system_identifier.invalid");
                    }
                }
            } else if ("-//W3C//DTD XHTML 1.0 Strict//EN".equals(publicId)) {
                if (!"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd".equals(systemId)) {
                    if (ac != null && ac.getFrame() != null) {
                        ac.getFrame().addWarning("xhtml.system_identifier.invalid");
                    }
                }
            } else if ("-//W3C//DTD XHTML 1.0 Frameset//EN".equals(publicId)) {
                if (!"http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd".equals(systemId)) {
                    if (ac != null && ac.getFrame() != null) {
                        ac.getFrame().addWarning("xhtml.system_identifier.invalid");
                    }
                }
            }
            uri = catalog.getProperty(publicId);
        }
        if (uri == null && systemId != null) {
            uri = catalog.getProperty(systemId);
        }
        if (uri != null) {
            return new InputSource(uri);
        } else {
            return new InputSource(new URL(baseURI, systemId).toString());
        }
    }

    public void parse(InputSource source, String fileName) throws IOException, SAXException {
        org.xml.sax.XMLReader xmlParser = new org.ccil.cowan.tagsoup.Parser();
        try {
            xmlParser.setProperty("http://xml.org/sax/properties/lexical-handler",
                    this);
            xmlParser.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
            xmlParser.setFeature("http://xml.org/sax/features/validation", false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        xmlParser.setContentHandler(this);
        if (fileName != null) {
            baseURI = new URL(fileName);
            documentURI = new URL(fileName);
            source.setSystemId(fileName);
        }
        URL ref = ac.getReferrer();
        try {
            ac.setReferrer(documentURI);
            xmlParser.parse(source);
        } finally {
            ac.setReferrer(ref);
        }
    }

    /**
     * Parse an HTML document, given as an InputStream
     *
     * @param is     the inputstream of the document
     * @param docref the String version of the URI of the document
     * @throws IOException
     * @throws SAXException
     */
    public void parse(InputStream is, String docref) throws IOException, SAXException {
        InputSource inputSource = new InputSource(is);
        try {
            parse(inputSource, docref);
        } finally {
            is.close();
        }
    }

    /**
     * Parse an HTML document, given as a Reader
     *
     * @param reader the Reader of the document
     * @throws IOException
     * @throws SAXException
     */
    public void parse(Reader reader) throws IOException, SAXException {
        InputSource inputSource = new InputSource(reader);
        try {
            parse(inputSource, null);
        } finally {
            reader.close();
        }
    }

    /**
     * Parse an HTML document, given as a Reader
     *
     * @param reader the Reader of the document
     * @param docref the String version of the URI of the document
     * @throws IOException
     * @throws SAXException
     */
    public void parse(Reader reader, String docref) throws IOException, SAXException {
        InputSource inputSource = new InputSource(reader);
        try {
            parse(inputSource, docref);
        } finally {
            reader.close();
        }
    }

    void parse(URL url) throws Exception {
        InputSource source = new InputSource();
        URLConnection connection;
        InputStream in;
        org.xml.sax.XMLReader xmlParser = new org.ccil.cowan.tagsoup.Parser();
        try {
            xmlParser.setProperty("http://xml.org/sax/properties/lexical-handler",
                    this);
            xmlParser.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
            xmlParser.setFeature("http://xml.org/sax/features/validation", false);
            /*
          xmlParser.setFeature("http://xml.org/sax/features/external-parameter-entities",
	      false);
	      xmlParser.setFeature("http://xml.org/sax/features/external-general-entities",
	      false);
	    */
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        xmlParser.setContentHandler(this);

        connection = HTTPURL.getConnection(url, ac);
        in = HTTPURL.getInputStream(ac, connection);
        String streamEncoding = HTTPURL.getCharacterEncoding(ac, connection);

        String httpCL = connection.getHeaderField("Content-Location");
        if (httpCL != null) {
            baseURI = HTTPURL.getURL(baseURI, httpCL);
            documentURI = baseURI;
            if (streamEncoding != null) {
                ac.setCharsetForURL(baseURI, streamEncoding, false);
            }
        }
        if (streamEncoding != null) {
            source.setEncoding(streamEncoding);
        }
        //else {
        //    String ctype = connection.getContentType();
        //   if (ctype != null) {
        //	try {
        //	    MimeType repmime = new MimeType(ctype);
        //	    if (repmime.hasParameter("charset"))
        //		source.setEncoding(repmime.getParameterValue("charset"));
        //	} catch (Exception ex) {}
        //   }
        //}
        source.setByteStream(in);
        URL ref = ac.getReferrer();
        try {
            ac.setReferrer(documentURI);
            xmlParser.parse(url.toString());
        } finally {
            ac.setReferrer(ref);
            in.close();
        }
    }

    void parse(String urlString, URLConnection connection) throws Exception {
        org.xml.sax.XMLReader xmlParser = new org.ccil.cowan.tagsoup.Parser();
        try {
            xmlParser.setProperty("http://xml.org/sax/properties/lexical-handler",
                    this);
            xmlParser.setFeature("http://xml.org/sax/features/namespace-prefixes", true);

            xmlParser.setFeature("http://xml.org/sax/features/validation", false);
            xmlParser.setErrorHandler(this);
            xmlParser.setEntityResolver(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        xmlParser.setContentHandler(this);
        InputStream cis = HTTPURL.getInputStream(ac, connection);
        InputSource source = new InputSource(cis);
        String streamEncoding = HTTPURL.getCharacterEncoding(ac, connection);
        String httpCL = connection.getHeaderField("Content-Location");
        if (httpCL != null) {
            baseURI = HTTPURL.getURL(baseURI, httpCL);
            documentURI = baseURI;
            if (streamEncoding != null) {
                ac.setCharsetForURL(baseURI, streamEncoding, false);
            }
        }
        if (streamEncoding != null) {
            source.setEncoding(streamEncoding);
        } //else {
        //  String ctype = connection.getContentType();
        //   if (ctype != null) {
        //	try {
        //	    MimeType repmime = new MimeType(ctype);
        //	    if (repmime.hasParameter("charset")) {
        //		source.setEncoding(repmime.getParameterValue("charset"));
        //	    } else {
        //		// if text/html and no given charset, let's assume
        //		// iso-8859-1. Ideally, the parser would change the
        //		// encoding if it find a mismatch, not sure, but well...
        //		if (repmime.match(MimeType.TEXT_HTML) ==
        //		    MimeType.MATCH_SPECIFIC_SUBTYPE) {
        //		    source.setEncoding("iso-8859-1");
        //		}
        //	    }
        //	} catch (Exception ex) {}
        //   }
        //}
        source.setSystemId(urlString);
        URL ref = ac.getReferrer();
        try {
            ac.setReferrer(documentURI);
            xmlParser.parse(source);
        } finally {
            ac.setReferrer(ref);
            cis.close();
        }
    }

    HashMap<String, String> getValues(String data) {
        int length = data.length();
        int current = 0;
        char c;
        StringBuilder name = new StringBuilder(10);
        StringBuilder value = new StringBuilder(128);
        StringBuilder entity_name = new StringBuilder(16);
        int state = 0;
        HashMap<String, String> table = new HashMap<String, String>();

        while (current < length) {
            c = data.charAt(current);

            switch (state) {
                case 0:
                    switch (c) {
                        case ' ':
                        case '\t':
                        case '\n': // \r are normalized per XML spec
                            // nothing
                            break;
                        case '"':
                        case '\'':
                            return table;
                        case 'h':
                        case 't':
                        case 'm':
                        case 'c':
                        case 'a':
                        case 'r':
                            name.setLength(0); // reset the name
                            value.setLength(0); // reset the value
                            name.append(c); // start to build the name
                            state = 1;
                            break;
                        default:
                            // anything else is invalid
                            return table;
                    }
                    break;
                case 1: // in the "attribute" name inside the PI
                    if ((c >= 'a') && (c <= 'z')) {
                        name.append(c);
                    } else if ((c == ' ') || (c == '\t') || (c == '\n')) {
                        state = 2;
                    } else if (c == '=') {
                        state = 3;
                    } else {
                        // anything else is invalid
                        state = 0;
                    }
                    break;
                case 2: // waiting for =
                    switch (c) {
                        case ' ':
                        case '\t':
                        case '\n':
                            // nothing
                            break;
                        case '=':
                            state = 3;
                            break;
                        default:
                            // anything else is invalid
                            return table;
                    }
                    break;
                case 3: // waiting for ' or "
                    switch (c) {
                        case ' ':
                        case '\t':
                        case '\n':
                            // nothing
                            break;
                        case '"':
                            state = 4;
                            break;
                        case '\'':
                            state = 5;
                            break;
                        default:
                            // anything else is invalid
                            return table;
                    }
                    break;
                case 4:
                case 5: // in the "attribute" value inside the PI
                    switch (c) {
                        case '&':
                            // predefined entities amp, lt, gt, quot, apos
                            entity_name.setLength(0);
                            state += 10;
                            break;
                        case '<':
                            return table;
                        case '"':
                            if (state == 4) {
                                state = 6;
                            } else {
                                value.append(c);
                            }
                            break;
                        case '\'':
                            if (state == 5) {
                                state = 6;
                            } else {
                                value.append(c);
                            }
                            break;
                        default:
                            value.append(c);
                    }
                    break;
                case 6: // waiting a white space
                    table.put(name.toString(), value.toString());
                    name.setLength(0); // reset the name
                    value.setLength(0); // reset the value
                    switch (c) {
                        case ' ':
                        case '\n':
                        case '\t':
                            state = 0;
                            break;
                        default:
                            return table;
                    }
                    break;
                case 14:
                case 15: // in the entity
                    switch (c) {
                        case 'a':
                        case 'm':
                        case 'p':
                        case 'l':
                        case 't':
                        case 'g':
                        case 'q':
                        case 'u':
                        case 'o':
                        case 's':
                            entity_name.append(c);
                            break;
                        case ';':
                            String entity = entity_name.toString();
                            if ("amp".equals(entity)) {
                                value.append('&');
                            } else if ("lt".equals(entity)) {
                                value.append('<');
                            } else if ("gt".equals(entity)) {
                                value.append('>');
                            } else if ("quote".equals(entity)) {
                                value.append('"');
                            } else if ("apos".equals(entity)) {
                                value.append('\'');
                            } else {
                                return table;
                            }
                            state -= 10;
                            break;
                        default:
                            return table;
                    }
            }
            current++;
        }
        if (name.length() != 0 && value.length() != 0) {
            table.put(name.toString(), value.toString());
        }
        return table;
    }
}
