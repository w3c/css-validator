/**
 * StyleTag
 *
 * @author Vincent Mallet  (vmallet@sophia.inria.fr)
 *
 * $Id$
 *
 * @version $Revision$
 */


package html.tags;

import java.io.*;

import html.tree.*;
import html.parser.*;

/**
 * This class handles the <STYLE> tag in the Html Tree built by
 * the Html parser. It transfers control to the StyleSheet which
 * will parse the text contained here.
 *
 * @author Vincent Mallet (Vincent.Mallet@sophia.inria.fr)
 *
 * @version $Revision$
 */

public class StyleTag extends Block {

    public static final boolean debug =  false;

    String media = null;
    String title = null;
    String type = null;

    /**
     * Create a new StyleTag.
     */

    public StyleTag() {
	if (debug)
	    System.out.println( "creating StyleTag   this=" +(Object)this);
    }


    public void initialize(Element elem, Attributes atts,
			   ParserFrame parserFrame) {
	super.initialize(elem, atts, parserFrame);
	//    System.err.println("style tag line=" + line);
	if (atts != null) {
	    media = atts.get("media");
	    title = atts.get("title");
	    type  = atts.get("type");
	}
    }


    /**
     * This method is called just after the attachment of a child to the tree
     * has been done.
     * @param htmlChild the child that has just been attached to the tree.
     */

    //    public void attachHook(HtmlTree htmlChild) {

    public void attach(Tree child, int rank) {
	super.attach(child, rank);

	HtmlTree   htmlChild = (HtmlTree) child;

	if (debug) {
	    System.out.println( "StyleTag::attach()   child="
				+ (Object)htmlChild + "  ("
				+ htmlChild.getElement().toString() + ")");
	}

	String text = getChildText(htmlChild);

	if (text != null) {
	    if ((type == null) || type.equalsIgnoreCase("text/css")) {
		if (media == null) {
		    media = "all";
		}
		parserFrame.styleSheetParser
		    .parseStyleElement(parserFrame.ac,
				     new ByteArrayInputStream(text.getBytes()),
				       title, media,
				       parserFrame.getURI(), line);
	    }
	}
    }

    /**
     * Helper function. Retrieves the text stored in the new child element.
     * @param htmlChild the child containing the text
     * @return the text contained in the child, or null if no text present.
     */
    protected String getChildText(HtmlTree htmlChild) {
	Attributes atts = htmlChild.getAttributes();

	if (atts == null)     //@@ I dont think this should ever happen...
	    return null;

	return atts.get("text");
    }
}
