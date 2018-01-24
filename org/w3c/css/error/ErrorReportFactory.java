// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.error;

import org.w3c.css.util.ApplContext;

/**
 * ErrorReportFactory<br />
 * Created: Jul 13, 2005 2:00:54 PM<br />
 */
public class ErrorReportFactory {
    /**
     * Give back an "ErrorReport" object based on various parameters, but mainly
     * output"
     */
    public static ErrorReport getErrorReport(ApplContext ac, String title,
                                             String output, Exception e,
                                             boolean validURI) {
        if ((output == null) || (output.equals("html"))
                || (output.equals("xhtml"))) {
            return new ErrorReportHTML(ac, title, output, e);
        }
        if (output.equals("soap12")) {
            return new ErrorReportSOAP12(ac, title, output, e, validURI);
        }
        return new ErrorReportHTML(ac, title, output, e);
    }
}
