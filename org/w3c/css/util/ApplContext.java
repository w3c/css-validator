/*
 * (c) COPYRIGHT 1999 World Wide Web Consortium
 * (Massachusetts Institute of Technology, Institut National de Recherche
 *  en Informatique et en Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 *
 * $Id$
 */
package org.w3c.css.util;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.StringTokenizer;

import org.w3c.css.parser.Frame;
import org.w3c.www.http.HttpAccept;
import org.w3c.www.http.HttpAcceptCharset;
import org.w3c.www.http.HttpAcceptCharsetList;
import org.w3c.www.http.HttpFactory;

/**
 * @version $Revision$
 * @author  Philippe Le Hegaret
 */
public class ApplContext {

    String credential = null;

    String lang;
    
    Messages msgs;
    
    Frame  frame;
    
    String cssversion;
    
    String profile;
    
    String input;
    
    Class cssselectorstyle;

    int origin = -1;
    
    String medium;

    /**
     * Creates a new ApplContext
     */
    public ApplContext(String lang) {
	this.lang = lang;
        msgs = new Messages(lang);
    }

    // as ugly as everything else
    public String getCredential() {
	return credential;
    }

    public void setCredential(String credential) {
	this.credential = credential;
    }

    public void setFrame(Frame frame) {
	this.frame = frame;
	frame.ac = this;
    }

    public Frame getFrame() {
	return frame;
    }

    public Class getCssSelectorsStyle() {
	return cssselectorstyle;
    }

    public void setCssSelectorsStyle(Class s) {
	cssselectorstyle = s;
    }

    public Messages getMsg() {
	return msgs;
    }

    public String getContentType() {
	return (msgs != null) ? msgs.getString("content-type") : null;
    }

    public String getContentLanguage() {
	return (msgs != null) ? msgs.getString("content-language") : null;
    }

    /**
     * Searches the properties list for a content-encoding one.
     * If it does not exist, searches for output-encoding-name.
     * If it still does not exists, the method returns the default utf-8 value
     * @return the output encoding of this ApplContext
     */
    public String getContentEncoding() {
	//return (msgs != null) ? msgs.getString("content-encoding") : null;
	String res = null;
	if(msgs != null) {
	    res = msgs.getString("content-encoding");
	    if(res == null) {
		res = msgs.getString("output-encoding-name");
	    }		
	    if(res != null) {
		// if an encoding has been found, return it
		return res;
	    }
	}
	// default encoding
	return Utf8Properties.ENCODING;
    }

    public String getLang() {
	return lang;
    }

    public void setCssVersion(String cssversion) {
        this.cssversion = cssversion;
    }

    public String getCssVersion() {
	if (cssversion == null) {
	    cssversion = "css2";
	}
	return cssversion;
    }
    
    public void setProfile(String profile) {
	this.profile = profile;
    }

    public String getProfile() {
	if (profile == null) {
	    return "";
	}
	return profile;
    }

    public void setOrigin(int origin) {
	this.origin = origin;
    }

    public int getOrigin() {
	return origin;
    }

    public void setMedium(String medium) {
	this.medium = medium;
    }

    public String getMedium() {
	return medium;
    }

    public String getInput() {
	return input;
    }

    public void setInput(String input) {
	this.input = input;
    }
    
    /**
     * Sets the content encoding to the first charset that appears in
     * <i>acceptCharset</i>.
     * If the charset is not supported, the content encoding will be utf-8
     * @param acceptCharset a String representing the Accept-Charset request parameter
     */
    public void setContentEncoding(String acceptCharset) {	
	if(acceptCharset != null) {		    
	    // uses some Jigsaw classes to parse the Accept-Charset
	    // these classes need to load a lot of stuff, so it may be quite long
	    // the first time 
	    HttpAcceptCharsetList charsetList = 
		HttpFactory.parseAcceptCharsetList(acceptCharset);
	    HttpAcceptCharset[] charsets = (HttpAcceptCharset[])charsetList.getValue();
	    
	    String encoding = null;
	    double quality = 0.0;
	    
	    for(int i = 0; i < charsets.length && quality < 1.0 ; i++) {
		HttpAcceptCharset charset = charsets[i];
		
		String currentCharset = charset.getCharset();
		
		// checks that the charset is supported by Java
		
		
		if(isCharsetSupported(currentCharset)) {
		    double currentQuality = charset.getQuality();
		    
		    if(currentQuality > quality) {
			quality = currentQuality;
			encoding = charset.getCharset();
		    }
		}
	    }		
	    if(encoding != null) {
		getMsg().properties.setProperty("content-encoding", encoding);
	    }
	    else {
		// no valid charset
		getMsg().properties.remove("content-encoding");
	    }
	}
	else {
	    // no Accept-Charset given 
	    getMsg().properties.remove("content-encoding");
	}
    }
    
    private boolean isCharsetSupported(String charset) {	
	try {
	    return Charset.isSupported(charset);		
	}
	catch(Exception e) {
	    return false;
	}	
    }
    
    /*
    // First version of the Accept-Charset parser
    public void setContentEncoding(String acceptCharset) {
	String encoding = Utf8Properties.ENCODING;
	double quality = 0.;
	System.out.println(acceptCharset);
	if(acceptCharset != null) {	    
	    StringTokenizer csTok = new StringTokenizer(acceptCharset, ",");
	    // 1.0 is the highest possible quality, so if we reach it, we 
	    // don't need to continue
	    while(csTok.hasMoreTokens() && quality < 1.0) {
		String[] currentCharset = csTok.nextToken().split(";");		
		if(currentCharset.length > 0) {
		    String enc = currentCharset[0].trim().toLowerCase();		    
		    // we need to know if the current charset has a valid name
		    // and is supported
		    boolean supported = false;
		    try {
			supported = Charset.isSupported(enc);		
		    }
		    catch(IllegalCharsetNameException e) {
			supported = false;
		    }
		    catch(IllegalArgumentException e) {
			supported = false;
		    }
		    // if the current encoding is supported, we try to get its quality		    
		    if(supported) {
			if(currentCharset.length == 2) {
			    String sQual = Util.strip(currentCharset[1]);
			    // now, sQual should be "q=x.y"
			    try {
				sQual = sQual.substring(2, sQual.length());
				double qual = Double.parseDouble(sQual);
				if(qual > quality) {
				    quality = qual;
				}
			    }
			    catch(Exception e) {
				// if the quality field is not correct, we ignore
				// this encoding
				continue;
			    }			    
			}			
			else {
			    // if no quality is given for this encoding,
			    // we consider that quality=1, according to RFC2616 14.2
			    quality = 1.0;
			}
			encoding = enc;
		    }		    
		}
	    }
	    if(encoding != null) {
		getMsg().properties.setProperty("content-encoding", encoding);
	    }
	}
    }
    */
}
