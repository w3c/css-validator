//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 3.1  1997/08/29 13:23:27  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:42:09  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/11 08:05:17  plehegar
 * Freeze
 *
 * Revision 1.1  1997/07/28 21:32:31  plehegar
 * Initial revision
 *
 */

package org.w3c.css.servlet;

import java.io.IOException;
import java.io.EOFException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// multipart/form-data
import org.w3c.css.util.Codecs;
import org.w3c.css.util.NVPair;

import org.w3c.css.util.ApplContext;

import org.w3c.css.util.HTTPURL;

import org.w3c.css.parser.CssFouffa;
import org.w3c.css.css.CssParser;
// import org.w3c.css.css.StyleSheetXMLParser;
import org.w3c.css.css.StyleSheetParser;
import org.w3c.css.css.HTMLStyleSheetParser;
import org.w3c.css.css.StyleSheet;
import org.w3c.css.css.StyleSheetGeneratorHTML2;
import org.w3c.css.aural.ACssStyle;
import org.w3c.css.util.Util; 
import org.w3c.css.util.FakeFile;

import org.xml.sax.SAXParseException;

/**
 * This class is a servlet to use the validator.
 *
 * @version $Revision$
 */
public final class check extends HttpServlet {
    
    private CssParser parser;
    private URL htmlURL;
    private boolean debug;
    private boolean auralMode;
    
    /**
     * Initializes the servlet and logs the initialization. The init
     * method is called once, automatically, by the network service each
     * time it loads the servlet.  It is guaranteed to finish before any
     * service requests are accepted.  On fatal initialization errors, an
     * UnavailableException should be thrown.  Do not call the method
     * System.exit.
     *
     * <p> The init method stores the ServletConfig object.  Servlet
     * writers who specialize this method should call either super.init,
     * or store the ServletConfig object themselves.  If an implementor
     * decides to store the ServletConfig object in a different location,
     * then the getServletConfig method must also be overridden.
     *
     * <P>
     * <DL><STRONG>Init parameters:</STRONG>
     * <DT>debug
     * <DD><code>true</code> if you want to be in debug mode.
     * <DT>aural
     * <DD><code>true</code> if you want to be in aural mode.
     * <DT>import
     * <DD><code>false</code> if you don't want to activate the import statement.
     *     For security reasons, you shoud be careful when you lunch the servlet
     *     on a HTTP server with special access authorization.
     * <DT>input
     * <DD><code>html</code> if the user have an HTML input or <code>xml</code> 
     *     otherwise. <strong>deprecated</strong>
     * </DL>
     *
     * @param config servlet configuration information.
     * @exception ServletException if a servlet exception has occurred.  
     */
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	

	// [SECURITY] don't forget this !
	Util.servlet = true;
	
	if (config.getInitParameter("debug") != null) {
	    // servlet debug mode
	    // define a boolean property CSS.StyleSheet.debug if you want more debug.
	    this.debug = config.getInitParameter("debug").equals("true");
	}
	
	parser = new StyleSheetParser();
	
	if ((config.getInitParameter("import") != null) &&
	    (config.getInitParameter("import").equals("false"))) {
	    Util.importSecurity = true;
	}
	
    }
    
    /** 
     * Performs the HTTP GET operation.  An HTTP BAD_REQUEST error is
     * reported if an error occurs. This servlet writers shouldn't set the
     * headers for the requested entity (content type and encoding).
     *
     * <P> Note that the GET operation is expected to be <em>safe</em>,
     * without any side effects for which users might be held responsible.
     * For example, most form queries have no side effects.  Requests
     * intended to change stored data should use some other HTTP method.
     * (There have been cases of significant security breaches reported
     * because web-based applications used GET inappropriately.)
     *
     * <P> The GET operation is also expected to be <em>idempotent</em>,
     * meaning that it can safely be repeated.  This is not quite the same
     * as being safe, but in some common examples the requirements have
     * the same result.  For example, repeating queries is both safe and
     * idempotent (unless payment is required!), but buying something or
     * modifying data is neither safe nor idempotent.
     *
     * <P>
     * <DL><STRONG>Forms parameters:</STRONG>
     * <DT>URL
     * <DD>the URL to be parsed.
     * <DT>submitURL
     * <DD>if the user want to parse an URL.
     * <DT>text
     * <DD>The text to be parsed.
     * <DT>submitTEXT
     * <DD>if the user want to parse the text.
     * <DT>output
     * <DD>HTML if the user want an HTML output or XML otherwise.
     * <DT>input
     * <DD>HTML if the user have an HTML input or XML otherwise.
     * </DL>
     *
     * @param req encapsulates the request to the servlet.
     * @param resp encapsulates the response from the servlet.
     * @exception ServletException if the request could not be handled.
     * @exception IOException if detected when handling the request.  
     * @see org.w3c.css.css.StyleSheetGenerator
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
	int warningLevel = 2;

	String uri = null;
	String warning = req.getParameter("warning");
        String output = req.getParameter("output");
	ApplContext ac = new ApplContext(req.getHeader("Accept-Language"));
	InputStream in = req.getInputStream();

	// Here is a little joke :-)
	res.setHeader("Server", CssValidator.server_name);

	// I don't want cache for the response (inhibits proxy)
	res.setHeader("Pragma", "no-cache"); // @@deprecated
	res.setHeader("Cache-Control", "no-cache");

	if (req.getParameter("debug") != null) {
	    Util.onDebug = req.getParameter("debug").equals("true");
	    if (Util.onDebug) {
		debug = true;
		System.err.println("SWITCH DEBUG MODE REQUEST");
	    }
	}
	
	uri = req.getHeader("Referer");
	if (req.getPathInfo() == null) {
	    res.setHeader("Content-Length", "0");
	    res.sendRedirect("http://" + req.getServerName() + ":"
			     + req.getServerPort() +
			     "/css-validator/");
	    return;
	}
	if (!req.getPathInfo().equals("/referer")) {
	    res.setHeader("Content-Length", "0");
	    res.sendRedirect("http://" + req.getServerName() + ":"
			     + req.getServerPort() + "/css-validator"
			     + req.getPathInfo());

	    //handleError(out, "No file",
	    //	new IOException("You have send an invalid request."));
	    return;
	}
	if (uri == null) {
	    res.setHeader("Content-Length", "0");
	    res.sendRedirect("http://" + req.getServerName() + ":"
			     + req.getServerPort() +
			     "/css-validator/");
	    return;
	}
	
	uri = Util.suppressWhiteSpace(uri);
	
	if (output == null) {
	    output = CssValidator.texthtml;
	}
	
	in.close();
	
	PrintWriter out = new PrintWriter(res.getOutputStream());

	// set the content-type for the response
	// set the content-type for the response
	if (ac.getContentType() != null) {
	    res.setContentType(ac.getContentType());
	} else if (output.equals(CssValidator.texthtml)) {
	    res.setContentType(CssValidator.texthtml);
	} else {
	    res.setContentType(CssValidator.textplain);
	}
	if (ac.getContentLanguage() != null) {
	    res.setHeader("Content-Language", ac.getContentLanguage());
	} else {
	    res.setHeader("Content-Language", "en");
	}
	if (ac.getContentEncoding() != null) {
	    res.setHeader("Content-Encoding", ac.getContentEncoding());
	} /* else {
	    res.setHeader("Content-Encoding", "identity");
	    } */
	
	// Here is a little joke :-)
	res.setHeader("Server", CssValidator.server_name);
	
	// set the warning output
	if (warning != null) {
	    if (warning.equals("no")) {
		warningLevel = -1;
	    } else {
		try {
		    warningLevel = Integer.parseInt(warning);
		} catch (Exception e) {
		    System.err.println( e );
		}
	    }
	}
	
	// debug mode
	verbose("\nServlet request ");
	verbose("Source file : " + uri );
	
	// HTML document
	try {
	    uri = HTTPURL.getURL(uri).toString();
	    HTMLStyleSheetParser URLparser = new HTMLStyleSheetParser(ac, uri);
	    handleRequest(ac, out, uri, URLparser.getStyleSheet(), output,
			  warningLevel, true);

	} catch (Exception e) {
	    handleError(out, uri, e);
	} finally {
	    out.close();
	}

	verbose("CssValidator: Request terminated.\n");
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {

	handleError(new PrintWriter(res.getOutputStream()), "No file",
		    new IOException("You have send an invalid request."));
    }
    
    private void handleRequest(ApplContext ac, PrintWriter out, String title, 
			       StyleSheet styleSheet, String output,
			       int warningLevel, boolean errorReport) 
            throws Exception {
	if (styleSheet == null) {
	    throw new IOException("Can't process the file : " + title);
	}
	
	styleSheet.findConflicts(ac);

	if ("text/xml".equals(ac.getInput())
	    && "text/html".equals(output)) {
	    output = "xhtml";
	} else if ("text/html".equals(output)) {
	    output = "html";
	}
	
	StyleSheetGeneratorHTML2 style;
	style = new StyleSheetGeneratorHTML2(ac, title, styleSheet, 
					    output, warningLevel);
	if (!errorReport) {
	    style.desactivateError();
	}
	style.print(out);
    }
    
    private void handleError(PrintWriter out, String title, Exception e) {
	try {
	    URL localURL = CssValidator.class.getResource("error.html");
	    DataInputStream in = new DataInputStream(localURL.openStream());
	    try {
		while (true) {
		    out.print((char) in.readUnsignedByte());
		}
	    } catch (EOFException eof) {
		out.println("<h2>Target: " + title + "</h2>");
		out.println("<div class=\"error\">");
		if (e instanceof IOException) {
		    out.println("<p>I/O Error: ");
		    out.println(e.getMessage());
		} else if (e instanceof SAXParseException) {
		    SAXParseException saxe = (SAXParseException) e;
		    out.println("<p>Please, validate your XML document first!</p>");
		    if (saxe.getLineNumber() != -1) {
			out.println("<p>Line " + saxe.getLineNumber() + "</p>");
		    }
		    if (saxe.getColumnNumber() != -1) {
			out.println("<p>Column " + saxe.getColumnNumber() + "</p>");
		    }
		    out.println("<p>" + e.getMessage());
		} else if (e instanceof NullPointerException) {
		    out.println("<p>Oups! Internal error!</p><p>");
		    e.printStackTrace();
		} else {
		    out.println(e.toString());
		}
		out.println("</p></div>");
    
		out.println("<hr />");
		out.println("<p><img src='images/mwcss.gif' alt='made with CSS'  width='72' height='48' /></p>");
		out.println("<address><a href='mailto:www-validator-css@w3.org'>www-validator-css</a></address>");
		out.println("</body></html>");
		out.flush();
		System.err.println("CSS Validator: request failed.");
		e.printStackTrace();
	    }
	} catch (Exception unknown) {
	    out.println("org.w3c.css.servlet.CssValidator: couldn't load  error file");
	    out.flush();
	    unknown.printStackTrace();
	}
    }
    
    // trace function
    private final void verbose(String s) {
	if (debug) {
	    System.err.println( s );
	}
    }
    
}
