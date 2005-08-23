//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.8  2005/08/08 13:18:04  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.7  2004/01/10 06:22:24  bjoern
 * Fix for http://www.w3.org/Bugs/Public/show_bug.cgi?id=292
 *
 * Revision 1.6  2003/07/11 13:48:59  sijtsche
 * compile error in useless use of HttpServletResponse fixed
 *
 * Revision 1.5  2003/07/02 14:39:03  plehegar
 * Removed HttpServletResponse import
 *
 * Revision 1.4  2002/08/19 07:33:36  sijtsche
 * TV profile added
 *
 * Revision 1.3  2002/07/22 13:20:38  sijtsche
 * versions CSS3, SVG, SVG basic and SVG tiny added for validation
 *
 * Revision 1.2  2002/04/08 21:16:38  plehegar
 * New
 *
 * Revision 1.1  1998/01/07 13:32:20  plehegar
 * Initial revision
 *
 */
package org.w3c.css.css;

import html.tags.HtmlParser;
import html.tags.HtmlParserListener;
import html.tags.HtmlTag;
import html.tags.HtmlTree;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.w3c.css.parser.CssFouffa;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.HTTPURL;
import org.w3c.css.util.Util;

/**
 * @version $Revision$import javax.servlet.http.HttpServletResponse;
 */
public class StyleSheetCom implements HtmlParserListener {

    /*    ApplContext ac = new ApplContext("ja, en, zh"); */
    ApplContext ac;
    String lang;
    String documentBase = "text";
    URL htmlURL;
    String file;
    int warningLevel = 2;
    PrintWriter out;
    String defaultmedium;
    String cssversion;
    String profile;
    String contenttype;

    // @@ HACK
    static boolean showCSS = false;

    private Exception exception;

    public void htmlRequest() throws Exception {

 	System.err.println( "html request " + htmlURL);
	HtmlParser htmlParser = new HtmlParser(ac, "html4", htmlURL.toString());
	try {
	    Util.fromHTMLFile = true;
	    htmlParser.addParserListener(this);
	    htmlParser.run();
	    if (exception != null) {
		throw (Exception) exception.fillInStackTrace();
	    }
	} catch (html.parser.XMLInputException e) {
	    xmlRequest();
	} finally {
	    Util.fromHTMLFile = false;
	}
    }

    public void xmlRequest() throws Exception {
	StyleSheet style = null;

	XMLStyleSheetHandler handler = new XMLStyleSheetHandler(htmlURL, ac);
	handler.parse(htmlURL);
	style = handler.getStyleSheet();
	if (style != null) {
	    style.setType("text/xml");
	}
	if (style != null) {
	    style.findConflicts(ac);
	    if (documentBase.startsWith("html")) {
		StyleSheetGeneratorHTML2 output =
		    new StyleSheetGeneratorHTML2(ac, file,
						 style,
						 documentBase,
						 warningLevel);
		output.print(out);
	    } else {
		StyleSheetGenerator2 style2 = new StyleSheetGenerator2(file,
								       style,
								       documentBase,
								       warningLevel);
		style2.print(out);
	    }
	} else {
	    System.err.println("No style sheet found in your HTML document");
	}
	ac.setInput("text/xml");
    }

    public void cssRequest(CssSelectors selector, String defaultmedium) {
	CssParser parser = new StyleSheetParser();
	ac.setMedium(defaultmedium);

	/*
	  if (defaultmedium != null) {
	  parser.setDefaultMedium(defaultmedium);
	  }
	*/
	parser.parseURL(ac, htmlURL, null, null, null,
			StyleSheetOrigin.AUTHOR);
	parser.getStyleSheet().findConflicts(ac);
	if (selector != null) {
	    System.err.println("<color value=\""
			       + parser.getStyleSheet().CascadingOrder(new org.w3c.css.properties.css1.CssColor(),
								       parser.getStyleSheet(),
								       selector));
	    CssStyle s = parser.getStyleSheet().getStyle(selector);
	    CssProperty _sl =
		((org.w3c.css.properties.css1.Css1Style) s).getColor();

	    s.print(new org.w3c.css.parser.CssPrinterStyle () {
		    public void print(CssProperty property) {
			System.out.print(property.getPropertyName());
			System.out.print(": ");
			System.out.print(property.toString());
			System.out.println(';');
		    }
		});
	} else if (documentBase.startsWith("html")) {
	    StyleSheetGeneratorHTML2 output =
		new StyleSheetGeneratorHTML2(ac, file,
					     parser.getStyleSheet(),
					     documentBase,
					     warningLevel);
	    output.print(out);
	} else {
	    StyleSheetGenerator2 output =
		new StyleSheetGenerator2(file,
					 parser.getStyleSheet(),
					 documentBase,
					 warningLevel);
	    output.print(out);
	}
    }

    public static void main(String args[])
	throws IOException, MalformedURLException {
	int i = 0;
	CssSelectors selector = null;

	StyleSheetCom style = new StyleSheetCom();

        // Fix for http://www.w3.org/Bugs/Public/show_bug.cgi?id=292
        style.contenttype = "text";

	try {
	    style.file = args[i];

	    while (i < args.length && args[i].charAt(0) == '-') {
		String argument = args[i].substring(1).toLowerCase();
		if (argument.equals("e")) {
		    style.warningLevel = -1;
		} else if (argument.equals("s")) {
		    StyleSheetCom.showCSS = true;
		} else if (argument.equals("c")) {
		    selector = createSelectors(args[++i]);
		} else if (argument.equals("format")) {
		    StyleSheetGenerator.
			printAvailableFormat(new PrintWriter(System.err));
		} else if (argument.startsWith("@")) {
		    style.defaultmedium = argument;
		} else if (argument.equals("css1") || argument.equals("css2")
		   	   || argument.equals("css3") ||
			   argument.equals("svg") ||
			   argument.equals("svgbasic") ||
			   argument.equals("svgtiny")) {
		    style.cssversion = argument;
		} else if (argument.equals("mobile") || argument.equals("tv")) {
		    style.profile = argument;
	    } else if (argument.equals("atsc")) {
			style.profile = "atsc-tv";
		} else {
		    int idx = argument.lastIndexOf('.');
		    if(idx >= 0 && idx < argument.length() - 1) {
			style.lang = argument.substring(idx+1);
		    } else {
			style.lang = "en";
		    }
		    style.documentBase = argument;
		}
		i++;
	    }
	    style.ac = new ApplContext(style.lang);
	    if (style.cssversion != null)
		style.ac.setCssVersion(style.cssversion);
	    if (style.profile != null) {
		style.ac.setProfile(style.profile);
		style.ac.setCssVersion("css2");
	    }
	    if (style.cssversion == null && style.ac.getCssVersion() == null) {
		style.ac.setCssVersion("css2");
	    }
	} catch (Exception e) {
	    /* System.out.println( "Usage: validator " +
	       StyleSheetCom.class.getName() +
				// " [-<your format>] [-fromxml] <url>");
				" [-s||-e||-<your format>] [<url>|<file>]*");
	    */
	    System.out.println( "Usage: validator " +
				" [ -s || -e || -<your format> || -cssversion/profile ] [<url>|<file>]*");
	    System.out.println( "\t-s\tShow the validated style sheet");
	    System.out.println( "\t-e\tDo NOT show warnings");
	    System.out.println( "\tuse the option -format to see"
				+ " available format.");
	    System.out.println( "\tCSS version\t-css1 || -css2 || -css3 || -svg || -svgbasic || -svgtiny");
	    System.out.println( "\tProfile\t\t -atsc || -mobile || tv");
	    //System.out.println( "\tCSS version\t-css1 || -css2");
	    //	    System.out.println( "\tProfile\t\t-svg || -atsc || -mobile");
	    System.exit(1);
	}

	String encoding = style.ac.getMsg().getString("output-encoding-name");
	if(encoding != null) style.out = new PrintWriter(new OutputStreamWriter(System.out, encoding));
	else style.out = new PrintWriter(new OutputStreamWriter(System.out));

	while (i < args.length) {
	    try {
		String doc = args[i];

		try {
		    style.htmlURL = new URL(doc);
		    style.file = style.htmlURL.toString();
		} catch (MalformedURLException ex) {
		    File f = new File(args[i]);
		    style.file = f.getAbsolutePath();
		    style.htmlURL = new URL("file", null, -1, style.file);
		}
		i++;

		String urlLower = style.htmlURL.toString().toLowerCase();
		if (urlLower.endsWith(".css")) {
		    style.cssRequest(selector, style.defaultmedium);
		} else if (urlLower.endsWith(".html")
			   || urlLower.endsWith(".shtml")
			   || urlLower.endsWith("/")) {
		    style.htmlRequest();
		} else if (urlLower.endsWith(".xml")) {
		    style.xmlRequest();
		} else {
		    URLConnection urlC = HTTPURL.getConnection(style.htmlURL, null);

		    if (urlC.getContentType() != null) {
			if (urlC.getContentType().indexOf("text/html") != -1) {
			    style.htmlRequest();
			} else if (urlC.getContentType().indexOf("text/xml") != -1) {
			    style.xmlRequest();
			} else if (urlC.getContentType().indexOf("text/css") != -1) {
			    style.cssRequest(selector, style.defaultmedium);
			}
		    } else {
			style.cssRequest(selector, style.defaultmedium);
		    }
		    try {
			urlC.getInputStream().close();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
	    } catch (org.xml.sax.SAXException e) {
		if (e.getException() != null) {
		    e.getException().printStackTrace();
		}
	    } catch (Exception e) {
		e.printStackTrace();
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

	StyleSheet style = null;

	if (root != null) {
	    style = ((HtmlTree) root).getStyleSheet();
	}

	if (style != null) {
	    style.findConflicts(ac);
	    if (documentBase.startsWith("html")) {
		StyleSheetGeneratorHTML2 output =
		    new StyleSheetGeneratorHTML2(ac, file,
						 style,
						 contenttype,
						 warningLevel);
		output.print(out);
	    } else {
		StyleSheetGenerator2 style2 = new StyleSheetGenerator2(file,
								       style,
								       contenttype,
								       warningLevel);
		style2.print(out);
	    }
	} else {
	    System.err.println("No style sheet found in your HTML document");
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

    private static CssSelectors createSelectors(String s) {
	try {
	    CssFouffa fouffa =
		new CssFouffa(null, new java.io.StringBufferInputStream(s),
			      new URL("file://nofile"));
	    return fouffa.parseSelector();
	} catch (Exception e) {
	    System.err.println(e);
	    return null;
	}
    }

}
