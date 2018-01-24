// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.error;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Util;
import org.xml.sax.SAXParseException;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

/**
 * ErrorReportHTML<br />
 * Created: Jul 13, 2005 2:05:51 PM<br />
 * This class is used to create an (x)html page when a URI error is thrown by
 * the servlet
 */
public class ErrorReportHTML extends ErrorReport {

    // ApplContext ac;
    String title;
    Exception e;

    public ErrorReportHTML(ApplContext ac, String title, String output, Exception e) {
        // ac is not used for now, but may be useful
        // this.ac = ac;
        this.title = title;
        this.e = e;
    }

    /**
     * @see org.w3c.css.error.ErrorReport#print(java.io.PrintWriter)
     */
    public void print(PrintWriter out) {
        try {
            URL localURL = ErrorReportHTML.class.getResource("error.html");
            DataInputStream in = new DataInputStream(localURL.openStream());
            try {
                while (true) {
                    out.print((char) in.readUnsignedByte());
                }
            } catch (EOFException eof) {
                out.println("<h2>Target: " + Util.escapeHTML(title) + "</h2>");
                out.println("<div class=\"error\">");
                if (e instanceof ResourceNotFoundException) {
                    out.println("<p>" + e.toString() + "</p>");
                } else if (e instanceof IOException) {
                    out.println("<p>I/O Error: ");
                    out.println(Util.escapeHTML(e.getMessage()));
                } else if (e instanceof SAXParseException) {
                    SAXParseException saxe = (SAXParseException) e;
                    out.println("<p>Please, validate your XML document" + " first!</p>");
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
                    out.println("<p>" + Util.escapeHTML(e.getMessage()));
                } else if (e instanceof NullPointerException) {
                    out.println("<p>Oups! Internal error!</p><p>");
                    e.printStackTrace();
                } else {
                    out.println(e.toString());
                }
                out.println("</p></div>\n<hr />\n<p><img src='images/mwc"
                        + "ss.gif' alt='made with CSS' /></p>\n<addres" + "s><a href='Email.html'>www-validator-css</a"
                        + "></address>\n</body></html>");
                out.flush();
                /*
				 * System.err.println("CSS Validator: request failed.");
				 * e.printStackTrace();
				 */
            }
        } catch (Exception unknown) {
            if (out != null) {
                out.println("org.w3c.css.servlet.CssValidator: couldn't " + "load  error file");
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
