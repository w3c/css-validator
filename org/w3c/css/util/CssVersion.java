// $Id$
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.util;

public enum CssVersion {
    CSS1("css1"), CSS2("css2"), CSS21("css21"), CSS3("css3");
    private final String version;

    CssVersion(String version) {
        this.version = version;
    }

    public String toString() {
        return version;
    }

    public static CssVersion resolve(ApplContext ac, String s)
  //          throws InvalidParamException {
    {
        for (CssVersion v : CssVersion.values()) {
            if (v.toString().equals(s)) {
                return v;
            }
        }
        // TODO this or get the default ???
 //       throw new InvalidParamException("invalid-level", s, ac);
        return getDefault();
    }

    // get the default version of CSS
    public static CssVersion getDefault() {
        return CSS3;
    }
}
