/**
 * LinkTag
 *
 * @author Vincent Mallet  (vmallet@sophia.inria.fr)
 *
 * $Id$
 *
 * @version $Revision$
 */

// TODO: handle the content-type properly.
//     : handle alternate stylesheets.

package html.tags;

import org.w3c.css.css.StyleSheetOrigin;

import java.io.*;
import java.net.*;

import html.parser.*;

/**
 * This class handles the <LINK> tag in the Html tree built by the Html Parser.
 * If the link refers to an external stylesheet, control will be transfered to the
 * StyleSheet object which will then parse the refered stylesheet.<BR>
 * Note:
 * <UL
 * <LI>Alternate StyleSheets are not handled.
 * <LI>Content-type is not handled.
 * </UL>
 *
 * @author Vincent Mallet (Vincent.Mallet@sophia.inria.fr)
 */

public class LinkTag extends Flow {

    public static boolean debug = false;

    /**
     * Create a new LinkTag.
     */

    public LinkTag() {
	debug = Boolean.getBoolean("html.tags.debug");
	if (debug)
	    System.out.println( "creating LinkTag   this=" +(Object)this);
    }

    /**
     * Initialize the <code>LINK</code> tag with the given element and
     * attributes. First initializes the superclass as before and
     * then handle the case ''Link rel="StyleSheet"''.
     * @param elem the given element
     * @param atts the attributes of this element
     * @param parserFrame the parser frame (containing the StyleSheet)
     */

    public void initialize(Element elem, Attributes atts,
			   ParserFrame parserFrame) {
	// first, initialize as before
	super.initialize(elem, atts, parserFrame);

	// Then treat the StyleSheet link case.

	if (debug)
	    System.out.println( "LinkTag::initialize()   node="
				+ (Object) this + "  ("
				+ getElement().toString() + ")");

	if (atts == null) {    //@@ I dont think this should ever happen...
	    return;
	}

	String rel  = atts.get("rel");
	String type  = atts.get("type");
	String href = atts.get("href");

	if (debug) {
	    System.out.println("LINK rel=\"" + rel
			       + "\"" + "   href=\"" + href + "\"");
	}

	if ((rel != null || type != null)
	    && ((rel == null) || rel.toLowerCase().indexOf("stylesheet") != -1)
	    && ((type == null) || type.equalsIgnoreCase("text/css"))) {
	    // we're dealing with a stylesheet...
	    URL url;

	    try {
		if (parserFrame.baseURL != null) {
		    url = new URL(parserFrame.baseURL, href);
		} else {
		    url = new URL(parserFrame.url, href);
		}
	    } catch (MalformedURLException e) {
		return; // Ignore errors
	    }

	    if (debug) {
		System.out.println("[LinkTag::initialize(): "
				   + "should parse CSS url: "
				   + url.toString() + "]");
	    }
	    String media = atts.get("media");
	    if (media == null) {
		media="all";
	    }
	    parserFrame.styleSheetParser.parseURL(parserFrame.ac,
						  url,
						  atts.get("title"),
						  rel,
						  media,
						  StyleSheetOrigin.AUTHOR);
	    if (debug) {
		System.out.println("[parsed!]");
	    }
	}
    }
}
