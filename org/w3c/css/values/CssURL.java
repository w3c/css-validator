//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 2.4  1997/08/26 14:27:07  plehegar
 * Updated
 *
 * Revision 1.1  1997/07/11 15:30:09  plehegar
 * Initial revision
 *
 */
package org.w3c.css.values;

import java.net.URL;
import java.net.MalformedURLException;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.HTTPURL;
import org.w3c.css.util.ApplContext;

/**
 *   <H3>
 *     &nbsp;&nbsp; URL
 *   </H3>
 *   <P>
 *   A Uniform Resource Locator (URL) is identified with a functional notation:
 *   <PRE>
 *   BODY { background: url(http://www.bg.com/pinkish.gif) }
 *   </PRE>
 *   <P>
 *   The format of a URL value is 'url(' followed by optional white space followed
 *   by an optional single quote (') or double quote (") character followed by
 *   the URL itself (as defined in <A HREF="#ref11">[11]</A>) followed by an optional
 *   single quote (') or double quote (") character followed by optional whitespace
 *   followed by ')'. Quote characters that are not part of the URL itself must
 *   be balanced.
 *   <P>
 *   Parentheses, commas, whitespace characters, single quotes (') and double
 *   quotes (") appearing in a URL must be escaped with a backslash: '\(', '\)',
 *   '\,'.
 *   <P>
 *   Partial URLs are interpreted relative to the source of the style sheet, not
 *   relative to the document:
 *   <PRE>
 *   BODY { background: url(yellow) }
 *   </PRE>
 *   See also
 *  <P>
 *  <A NAME="ref11">[11]</A> T Berners-Lee, L Masinter, M McCahill: "Uniform
 *  Resource Locators (URL)", <A href="ftp://ds.internic.net/rfc/rfc1738.txt">RFC
 *  1738</A>, CERN, Xerox Corporation, University of Minnesota, December 1994
 * @version $Revision$
 */
public class CssURL extends CssValue {
    
    String value;

    URL base;
    
    /**
     * Set the value of this URL.
     *
     * @param s     the string representation of the URL.
     * @param frame For errors and warnings reports.
     * @exception InvalidParamException The unit is incorrect
     * @deprecated
     */  
    public void set(String s, ApplContext ac)
	    throws InvalidParamException {
	throw new InvalidParamException("Deprecated method invocation", ac);
    }

    /**
     * Set the value of this URL.
     *
     * @param s     the string representation of the URL.
     * @param frame For errors and warnings reports.
     * @param base the base location of the style sheet
     * @exception InvalidParamException The unit is incorrect
     */  
    public void set(String s, ApplContext ac, URL base) 
	    throws InvalidParamException {
	String urlHeading = s.substring(0,3).toLowerCase();
	String urlname = s.substring(4, s.length()-1).trim();
	this.base = base;

	try {
	    CssString convert = new CssString();
	    convert.set(urlname, ac);
	    value = (String) convert.get();
	} catch (InvalidParamException e) {
	    value = urlname;
	}
	
	if (!urlHeading.startsWith("url"))
	    throw new InvalidParamException("url", s, ac);
    }
    
    /**
     * Get the internal value.
     */  
    public Object get() {
	return value;
    }
    
    /**
     * Returns the URL
     */
    public URL getURL() throws MalformedURLException {
	return HTTPURL.getURL(base, value);
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return "url(" + value + ")";
    }
    
    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */  
    public boolean equals(Object url) {
	return (url instanceof CssURL && value.equals(((CssURL) url).value));
    }
    
}
