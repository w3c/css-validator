//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html


package org.w3c.css.servlet;

import java.io.IOException;
import java.io.EOFException;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.HttpURLConnection;
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

import org.w3c.css.util.ApplContext;

// multipart/form-data
import org.w3c.css.util.Codecs;
import org.w3c.css.util.NVPair;

import org.w3c.css.util.HTTPURL;

import org.w3c.css.parser.CssFouffa;
import org.w3c.css.css.CssParser;
import org.w3c.css.css.StyleSheetParser;
import org.w3c.css.css.HTMLStyleSheetParser;
import org.w3c.css.css.StyleSheet;
import org.w3c.css.css.StyleReportFactory;
import org.w3c.css.css.StyleReport;
import org.w3c.css.aural.ACssStyle;
import org.w3c.css.util.Util;
import org.w3c.css.util.FakeFile;
import org.w3c.css.util.Messages;

import org.w3c.www.mime.MimeType;

import org.xml.sax.SAXParseException;

/**
 * This class is a servlet to use the validator.
 *
 * @version $Revision$
 */
public final class CssValidator extends HttpServlet {

    private URL htmlURL;
    private boolean auralMode;

    private String returnMode;

    final static String texthtml    = "text/html";
    final static String textplain   = "text/plain";
    final static String textunknwon = "text/unknown";
    final static String soap12      = "application/soap+xml";
    final static String server_name = "Jigsaw/2.2.2 "+
                                      "W3C_CSS_Validator_JFouffa/2.0";

    /**
     * Create a new CssValidator.
     */
    public CssValidator() {
    }

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
	    Util.onDebug = config.getInitParameter("debug").equals("true");
	    System.err.println( "RUN IN DEBUG MODE: "
				+ config.getInitParameter("debug").equals("true"));
	} else if (Util.onDebug) {
	    System.err.println( "RUN IN DEBUG MODE but activated outside the servlet" );
	}

	if ((config.getInitParameter("import") != null) &&
	    (config.getInitParameter("import").equals("false"))) {
	    Util.importSecurity = true;
	}

    }

    private PrintWriter getLocalPrintWriter(OutputStream os, String encoding)
	throws IOException {
	if (encoding != null) {
	    return new PrintWriter(new OutputStreamWriter(os, encoding));
	} else {
	    return new PrintWriter(os);
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

	boolean errorReport = true;
	int warningLevel = 2;
	CssParser parser = null;

	String uri = null;
	try {
	    uri = req.getParameter("uri");
	} catch (Exception ex) {
	    // pb in URI decoding (bad escaping, most probably)
	    handleError(res, "No file",
			new IOException("Invalid escape sequence in URI"));
	}
	String text = null;
	try {
	    text = req.getParameter("text");
	} catch (Exception ex) {
	    // pb in URI decoding (bad escaping, most probably)
	    // not sure it will work here, as it may be catched by the first
	    // getParameter call
	    handleError(res, "Invalid text",
			new IOException("Invalid escape sequence in URI"));
	}
	String output = req.getParameter("output");
	String warning = req.getParameter("warning");
	String error = req.getParameter("error");
	String profile = req.getParameter("profile");
	String usermedium = req.getParameter("usermedium");
	ApplContext ac = new ApplContext(req.getHeader("Accept-Language"));

	String credential = req.getHeader("Authorization");
	if ((credential != null) && (credential.length() > 1)) {
	    ac.setCredential(credential);
	}

	if (usermedium == null || "".equals(usermedium)) {
		usermedium = "all";
	}

	InputStream in = req.getInputStream();

	ac.setMedium(usermedium);

	if (req.getParameter("debug") != null) {
	    Util.onDebug = req.getParameter("debug").equals("true")
		|| Util.onDebug;
	    if (Util.onDebug) {
		System.err.println("SWITCH DEBUG MODE REQUEST");
	    }
	}

	text = Util.suppressWhiteSpace(text);
	uri = Util.suppressWhiteSpace(uri);

	if (output == null) {
	    output = texthtml;
	}

	if (profile != null && !"none".equals(profile)) {
	    if ("css1".equals(profile) || "css2".equals(profile) ||
	    	"css3".equals(profile) || "svg".equals(profile) ||
		"svgbasic".equals(profile) || "svgtiny".equals(profile)) {
		ac.setCssVersion(profile);
	    } else {
		ac.setProfile(profile);
		ac.setCssVersion("css2");
	    }
	} else {
	    ac.setCssVersion("css2");
	}
	if (Util.onDebug) {
	    System.err.println( "[DEBUG]  profile is : " + ac.getCssVersion()
				+ " medium is " + usermedium);
	}

	// verify the request
	if ((uri == null) && (text == null)) {
	    // res.sendError(res.SC_BAD_REQUEST,
            //                "You have send an invalid request.");
	    handleError(res, "No file",
	     new IOException(ac.getMsg().getServletString("invalid-request")));
	    return;
	}

	in.close();

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

	// set the error erport
	if (error != null && error.equals("no")) {
	    errorReport = false;
	}

	// debug mode
	Util.verbose("\nServlet request ");
	if (uri != null) {
	    Util.verbose("Source file : " + uri );
	} else {
	    Util.verbose("TEXTAREA Input");
	}
	//	verbose("From " + req.getRemoteHost() +
	//       " (" + req.getRemoteAddr() + ") at " + (new Date()) );

	if (uri != null) {
	    // HTML document
	    try {
		uri = HTTPURL.getURL(uri).toString();
		HTMLStyleSheetParser URLparser = new HTMLStyleSheetParser(ac,
									  uri);
		handleRequest(ac, res, uri, URLparser.getStyleSheet(), output,
			      warningLevel, errorReport);
	    } catch (ProtocolException pex) {
		if (Util.onDebug) {
		    pex.printStackTrace();
		}
		res.setHeader("WWW-Authenticate", pex.getMessage());
		res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	    } catch (Exception e) {
		handleError(res, uri, e);
	    }
	} else {
	    Util.verbose("- TextArea Data -");
	    Util.verbose(text);
	    Util.verbose("- End of TextArea Data");

	    parser = new StyleSheetParser();

	    try {
		parser.parseStyleElement(ac,
				     new ByteArrayInputStream(text.getBytes()),
				     null, usermedium,
				     new URL("file://localhost/TextArea"), 0);
		handleRequest(ac, res,
			      "file://localhost/TextArea",
			      parser.getStyleSheet(),
			      output, warningLevel, errorReport);
	    } catch (Exception e) {
		handleError(res, "TextArea", e);
	    }
	}
	Util.verbose("CssValidator: Request terminated.\n");
    }

    /**
     * Performs the HTTP POST operation.  An HTTP BAD_REQUEST error
     * is reported if
     * an error occurs. The headers that are set should include content type,
     * length, and encoding.  Setting content length allows the servlet to take
     * advantage of HTTP "connection keep alive".  If content length can not be
     * set in advance, the performance penalties associated with not using keep
     * alives will sometimes be avoided if the response entity fits in an
     * internal
     * buffer.  The servlet implementor must write the headers before the
     * response data because the headers can be flushed at any time after
     * the data starts to be written.
     *
     * <P> This method does not need to be either "safe" or "idempotent".
     * Operations requested through POST could be ones for which users
     * need to be held accountable.  Specific examples including updating
     * stored data or buying things online.
     *
     * <P>
     * <DL><STRONG>Forms parameters:</STRONG>
     * <DT>file
     * <DD>The input file to be parsed.
     * <DT>output
     * <DD>The format output.
     * <DT>input
     * <DD>HTML if the user have an HTML input or XML otherwise.
     * </DL>
     *
     * @param req encapsulates the request to the servlet
     * @param resp encapsulates the response from the servlet
     * @exception ServletException if the request could not be handled
     * @exception IOException if detected when handling the request
     * @see org.w3c.css.css.StyleSheetGenerator
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {

	ApplContext ac = new ApplContext(req.getHeader("Accept-Language"));
	boolean errorReport = true;
	int warningLevel = 2;
	CssParser parser = null;
	FakeFile file = null;
	String output = null;
	boolean XMLinput = false;
	String warning = null;
	String error = null;
	String profile = null;
	String usermedium = "all";

	ServletInputStream in = req.getInputStream();
	byte[] buf = new byte[2048];
	byte[] general = new byte[65536];
	int count = 0;
	int len;

	if (req.getParameter("debug") != null) {
	    Util.onDebug = req.getParameter("debug").equals("true") ||
		Util.onDebug;
	    if (Util.onDebug) {
		System.err.println("SWITCH DEBUG MODE REQUEST");
	    }
	}

	Util.verbose("\nCssValidator: Servlet request ");
	//	verbose("From " + req.getRemoteHost() +
	//		" (" + req.getRemoteAddr() + ") at " + (new Date()) );
	Util.verbose("Content-length : " + req.getContentLength());

	if (req.getContentType().trim().startsWith("multipart/form-data")) {
	    Util.verbose("Content-type : multipart/form-data");
	}

	try {
	    while ((len = in.readLine(buf, 0, buf.length)) != -1) {
		if (len >= 2 && buf[len-1] == '\n' && buf[len-2] == '\r') {
		    len -= 1;
		    buf[len-1] = (byte) '\n';
		}
		if (len  != 0 && buf[len-1] == '\r') {
		    buf[len-1] = (byte) '\n';
		}

		if (general.length < (count + len)) {
		    byte[] old = general;
		    general = new byte[old.length * 2];
		    System.arraycopy(old, 0, general, 0, old.length);
		}
		System.arraycopy(buf, 0, general, count, len);
		count += len;
	    }
	} finally {
	    in.close();
	}

	try {
	    buf = new byte[count];
	    System.arraycopy(general, 0, buf, 0, count);
	    NVPair[] tmp = Codecs.mpFormDataDecode(buf, req.getContentType());
	    for (int i = 0; i < tmp.length; i++) {
			if (tmp[i].getName().equals("file")) {
				file = (FakeFile) tmp[i].getValue();
			} else if (tmp[i].getName().equals("output")) {
				output = (String) tmp[i].getValue();
			} else if (tmp[i].getName().equals("warning")) {
				warning = (String) tmp[i].getValue();
			} else if (tmp[i].getName().equals("error")) {
				warning = (String) tmp[i].getValue();
			} else if (tmp[i].getName().equals("input")) {
				XMLinput = ((String) tmp[i].getValue()).equals("XML");
			} else if (tmp[i].getName().equals("profile")) {
				profile = (String) tmp[i].getValue();
			} else if (tmp[i].getName().equals("usermedium")) {
				usermedium = (String) tmp[i].getValue();
				if (usermedium == null || "".equals(usermedium)) {
					usermedium = "all";
				}
			}
	    }
	} catch (Exception e) {
	    System.out.println("Oups! Error in Util/Codecs.java?!?");
	    e.printStackTrace();
	}

	ac.setMedium(usermedium);

	if (output == null) {
	    output = texthtml;
	}
	if (file == null || file.getSize() == 0) {
	    //	    res.sendError(res.SC_BAD_REQUEST,
	    //	  "You have send an invalid request");
	    handleError(res, "No file",
	     new IOException(ac.getMsg().getServletString("invalid-request")));
	    return;
	}

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

	// set the error report
	if (error != null && error.equals("no")) {
	    errorReport = false;
	}

	Util.verbose("File : " + file.getName());

	parser = new StyleSheetParser();

	try {
	    parser.parseStyleElement(ac, file.getInputStream(),
				     null, null,
				     new URL("file://localhost/"
					     + file.getName()),
				     0);

	    handleRequest(ac, res, "file://localhost/" + file.getName(),
			  parser.getStyleSheet(), output,
			  warningLevel, errorReport);
	} catch (Exception e) {
	    handleError(res, file.getName(), e);
	}

	Util.verbose("CssValidator: Request terminated.\n");
    }

    private void handleRequest(ApplContext ac,
			       HttpServletResponse res,
			       String title, StyleSheet styleSheet,
			       String output, int warningLevel,
			       boolean errorReport)
            throws Exception {

	// I don't want cache for the response (inhibits proxy)
	res.setHeader("Pragma", "no-cache"); // @@deprecated
	res.setHeader("Cache-Control", "no-cache");
	// Here is a little joke :-)
//	res.setHeader("Server", server_name);

	// set the content-type for the response
	MimeType outputMt = null;
	if (output.equals(texthtml)) {
	    outputMt = MimeType.TEXT_HTML.getClone();
	} else if (output.equals(soap12)) {
	    outputMt = new MimeType(soap12);
//	    outputMt = MimeType.TEXT_PLAIN.getClone();
	} else {
	    outputMt = MimeType.TEXT_PLAIN.getClone();
	}
	if (ac.getContentEncoding() != null) {
	    outputMt.setParameter("charset", ac.getContentEncoding());
	}
	res.setContentType(outputMt.toString());

	if (ac.getContentLanguage() != null) {
	    res.setHeader("Content-Language", ac.getContentLanguage());
	} else {
	    res.setHeader("Content-Language", "en");
	}

	if (styleSheet == null) {
	    throw new IOException(ac.getMsg().getServletString("process")
				  + " " + title);
	}

	if ("text/xml".equals(ac.getInput())
	    && "text/html".equals(output)) {
	    output = "xhtml";
	} else if ("text/html".equals(output)) {
	    output = "html";
	} else if ("application/soap+xml".equals(output)) {
	    output = "soap12";
	}
	styleSheet.findConflicts(ac);

	StyleReport style = StyleReportFactory.getStyleReport(ac, title,
							      styleSheet,
							      output,
							      warningLevel);
	if (!errorReport) {
	    style.desactivateError();
	}
	PrintWriter out = getLocalPrintWriter(res.getOutputStream(),
				ac.getMsg().getString("output-encoding-name"));

	try {
	    style.print(out);
	} finally {
	    out.close();
	}
    }

    private void handleError(HttpServletResponse res,
			     String title, Exception e) {
	System.err.println( "[ERROR VALIDATOR] " + title);
	System.err.println( e.toString() );
        e.printStackTrace();

	// I don't want cache for the response (inhibits proxy)
	res.setHeader("Pragma", "no-cache"); // @@deprecated
	res.setHeader("Cache-Control", "no-cache");

	// Here is a little joke :-)
	res.setHeader("Server", server_name);
	res.setHeader("Content-Language", "en");
	res.setHeader("Content-Type", "text/html; charset=us-ascii");
	// res.setHeader("Content-Encoding", "us-ascii");

	PrintWriter out = null;

	try {
	    out = getLocalPrintWriter(res.getOutputStream(), null);
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
		    out.println("<p>Please, validate your XML document"+
				" first!</p>");
		    if (saxe.getLineNumber() != -1) {
			out.print("<p>Line ");
			out.print(saxe.getLineNumber());
			out.println("</p>");
		    }
		    if (saxe.getColumnNumber() != -1) {
			out.print("<p>Column ");
			out.print(saxe.getColumnNumber());
			out.print("</p>\n");
		    }
		    out.println("<p>" + e.getMessage());
		} else if (e instanceof NullPointerException) {
		    out.println("<p>Oups! Internal error!</p><p>");
		    e.printStackTrace();
		} else {
		    out.println(e.toString());
		}
		out.println("</p></div>\n<hr />\n<p><img src='images/mwc"+
			    "ss.gif' alt='made with CSS' /></p>\n<addres"+
			    "s><a href='Email.html'>www-validator-css</a"+
			    "></address>\n</body></html>");
		out.flush();
		/*
		System.err.println("CSS Validator: request failed.");
		e.printStackTrace();
		*/
	    }
	} catch (Exception unknown) {
	    if (out != null) {
		out.println("org.w3c.css.servlet.CssValidator: couldn't "+
			    "load  error file");
		out.flush();
	    }
	    unknown.printStackTrace();
	} finally {
	    if (out != null) {
		out.close();
	    }
	}
    }

}
