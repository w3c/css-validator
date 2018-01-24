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
package org.w3c.css.util.xml;

import org.w3c.css.util.Utf8Properties;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.net.URL;

/**
 * @author Philippe Le Hegaret
 * @version $Revision$
 */
public class XMLCatalog extends Utf8Properties implements ContentHandler {

    URL baseURI;

    /**
     * Creates a new XMLCatalog
     */
    public XMLCatalog() {
        try {
            baseURI = XMLCatalog.class.getResource("catalog.xml");
            parse(baseURI.toString());
        } catch (Exception e) {
            System.err.println("org.w3c.css.css.XMLStyleSheetHandler: "
                    + "couldn't load catalog.xml");
            System.err.println("  " + e.getMessage());
        }
    }

    public void setDocumentLocator(Locator locator) {
    }

    public void startDocument() throws SAXException {
    }

    public void endDocument() throws SAXException {
    }

    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {
    }

    public void endPrefixMapping(String prefix) throws SAXException {
    }

    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {
        if ("system".equals(qName)) {
            String systemId = atts.getValue("systemId");
            String uri = atts.getValue("uri");
            if ((systemId != null) && (uri != null)) {
                try {
                    setProperty(systemId, (new URL(baseURI, uri)).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if ("public".equals(qName)) {
            String publicId = atts.getValue("publicId");
            String uri = atts.getValue("uri");
            if ((publicId != null) && (uri != null)) {
                try {
                    setProperty(publicId, (new URL(baseURI, uri)).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void endElement(String namespaceURI, String localName, String qName)
            throws SAXException {
    }

    public void characters(char ch[], int start, int length)
            throws SAXException {
    }

    public void ignorableWhitespace(char ch[], int start, int length)
            throws SAXException {
    }

    public void processingInstruction(String target, String data)
            throws SAXException {
    }

    public void skippedEntity(String name) throws SAXException {
    }

    void parse(String urlString) throws Exception {
        org.xml.sax.XMLReader xmlParser = new org.apache.xerces.parsers.SAXParser();

        try {
            xmlParser.setFeature(
                    "http://xml.org/sax/features/namespace-prefixes", true);

            xmlParser.setFeature("http://xml.org/sax/features/validation",
                    false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        xmlParser.setContentHandler(this);
        InputSource source = new InputSource();
        source.setSystemId(urlString);
        xmlParser.parse(source);
    }
}
