/*
 * (c) COPYRIGHT 1995-1999 MIT, INRIA and Keio University. All Rights reserved.
 * W3C Intellectual Property Notice and Legal Disclaimers:
 *  http://www.w3.org/Consortium/Legal/
 *
 * BaseTag.java
 * $Id$
 */

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

public class BaseTag extends Flow {
    
    public static boolean debug = false;
    
    /**
     * Create a new BaseTag.
     */
    
    public BaseTag() {
	debug = Boolean.getBoolean("html.tags.debug");
	if (debug) {
	    System.out.println( "creating BaseTag   this=" + (Object) this);
	}
    }
    
    /**
     * Initialize the <code>BASE</code> tag with the given element and
     * attributes. First initializes the superclass as before and
     * then handle the case ''Base href="..."''.
     * @param elem the given element
     * @param atts the attributes of this element
     * @param parserFrame the parser frame
     */
    
    public void initialize(Element elem, Attributes atts, 
			   ParserFrame parserFrame) {
	// first, initialize as before
	super.initialize(elem, atts, parserFrame);
	
	// Then treat the StyleSheet link case.
	
	if (debug)
	    System.out.println( "BaseTag::initialize()   node=" 
				+ (Object) this + "  (" 
				+ getElement().toString() + ")");
	
	if (atts == null) {    //@@ I dont think this should ever happen...
	    return; 
	}

	String href = atts.get("href");
	
	if (debug) {
	    System.out.println("BASE href=\"" + href + "\"");
	}
	
	if (href != null) {
	    URL url;
	    
	    try { 
		url = new URL(href); 
		parserFrame.setBaseURI(url);
	    } catch (MalformedURLException e) {
		return; // Ignore errors
	    }
	}
    }
}
