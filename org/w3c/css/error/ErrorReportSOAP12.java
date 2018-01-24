// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.error;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Utf8Properties;

import java.io.PrintWriter;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * ErrorReportSOAP12<br />
 * Created: Jul 13, 2005 2:06:00 PM<br />
 * This class allows to create 2 different soap messages to handle errors:
 * <ul>
 * <li>Sender errors if the URI given in parameter of the servlet is invalid</li>
 * <li>Receiver errors if the URI given is valid but unreachable</li>
 * </ul>
 */
public class ErrorReportSOAP12 extends ErrorReport {

    private String title;
    private boolean validURI;
    private Exception exception;

    private ApplContext ac;
    private static Utf8Properties messages;

    private PrintWriter out;

    ErrorReportSOAP12(ApplContext ac, String title, String output, Exception e,
                      boolean validURI) {
        this.ac = ac;
        this.exception = e;
        this.validURI = validURI;
        this.title = title;
    }

    /**
     * @see org.w3c.css.error.ErrorReport#print(java.io.PrintWriter)
     */
    public void print(PrintWriter out) {
        this.out = out;

        // the error message
        String errorMessage = exception.getMessage();
        // the string containing the soap response pattern
        String report;
        if (validURI) {
            report = messages.getProperty("receiver");
            if (exception instanceof UnknownHostException) {
                errorMessage = "The host name " + errorMessage +
                        " couldn't be resolved";
            }
        } else {
            report = messages.getProperty("sender");
        }
        processError(report, errorMessage, title + ' ' + exception);
    }

    /**
     * Prints on the output the soap message str, where each entity<br/>
     * has been replaced by it's value<br/>
     * An entity is an xml comment (&lt;!-- --&gt;) containing a single word
     * beginning by #<br/>
     * Valid entities names are:
     * <ul>
     * <li><i>charset</i>: prints the charset defined in the Applcontext</li>
     * <li><i>reason</i>: prints the reason of the error</li>
     * <li><i>details</i>: prints the detailed reason of the error</li>
     * </ul>
     *
     * @param str          the soap message pattern
     * @param errorMessage the error message (see reason entity)
     * @param details      the detailed message error (see details entity)
     */
    private void processError(String str, String errorMessage, String details) {
        try {
            int i = 0;
            while ((i = str.indexOf("<!-- #", i)) >= 0) {
                int lastIndexOfEntity = str.indexOf("-->", i);
                String entity = str.substring(i + 6, lastIndexOfEntity - 1)
                        .toLowerCase();
                // reason entity
                if (entity.equals("reason")) {
                    out.print(str.substring(0, i));
                    str = str.substring(lastIndexOfEntity + 3);
                    i = 0;
                    out.print(errorMessage);
                }
                // details entity
                else if (entity.equals("details")) {
                    out.print(str.substring(0, i));
                    str = str.substring(lastIndexOfEntity + 3);
                    i = 0;
                    out.print(details);
                }
                //charset entity
                else if (entity.equals("charset")) {
                    out.print(str.substring(0, i));
                    str = str.substring(lastIndexOfEntity + 3);
                    i = 0;
                    out.print(ac.getContentEncoding());
                } else {
                    i += 6; // skip this unknown entity
                }
            }
            // print the end of the string
            if (str != null) {
                out.print(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (str != null) {
                out.print(str);
            }
        }
    }

    static {
        // load the soaperror.properties
        URL url;
        messages = new Utf8Properties();
        try {
            url = ErrorReportSOAP12.class.getResource("soaperror.properties");
            java.io.InputStream f = url.openStream();
            messages.load(f);
            f.close();
        } catch (Exception e) {
            System.err.println("org.w3c.css.error.ErrorReportSOAP12: "
                    + "couldn't load soap error messages properties ");
            System.err.println("  " + e.toString());
        }
    }
}
