// $Id$
// Author: Yves Lafon <ylafon@w3.org>
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import org.w3c.css.util.ApplContext;

public class StyleReportFactory {

    /**
     * Give back a "StyleReport" object based on various parameters, but mainly
     * output"
     */
    public static StyleReport getStyleReport(ApplContext ac, String title, StyleSheet style, String document,
                                             int warningLevel) {
        String output = (StyleSheetGenerator.isAvailableFormat(document)) ? document : "xhtml";
        return new StyleSheetGenerator(ac, title, style, output, warningLevel);
    }
}
