/*
 * (c) COPYRIGHT 1999 World Wide Web Consortium
 * (Massachusetts Institute of Technology, Institut National de Recherche
 *  en Informatique et en Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 *
 * $Id$
 */
package org.w3c.css.util;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.HashMap;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

import org.w3c.css.parser.Frame;
import org.w3c.www.http.HttpAcceptCharset;
import org.w3c.www.http.HttpAcceptCharsetList;
import org.w3c.www.http.HttpFactory;

import org.apache.velocity.io.UnicodeInputStream;

/**
 * @version $Revision$
 * @author Philippe Le Hegaret
 */
public class ApplContext {

    // the charset of the first source (url/uploaded/text)
    public static Charset defaultCharset;  
    public static Charset utf8Charset;

    static {
	try {
	    defaultCharset = Charset.forName("iso-8859-1");
	    utf8Charset = Charset.forName("utf-8");
	} catch (Exception ex) {
	    // we are in deep trouble here
	    defaultCharset = null;
	    utf8Charset    = null;
	}
    }

    // charset definition of traversed URLs
    private HashMap<URL,Charset> uricharsets = null;

    // namespace definitions
    private HashMap<URL,HashMap<String,String>> namespaces = null;

    // default prefix
    public static String defaultPrefix = "*defaultprefix*";
    public static String noPrefix = "*noprefix*";

    String credential = null;
    String lang;
    Messages msgs;
    Frame frame;
    String cssversion;
    String profile;
    String input;
    Class cssselectorstyle;
    int origin = -1;
    String medium;
    private String link;
    int warningLevel = 0;
    boolean treatVendorExtensionsAsWarnings = false;

    FakeFile fakefile = null;
    String   faketext = null;
    URL      fakeurl  = null;

    /**
     * Creates a new ApplContext
     */
    public ApplContext(String lang) {
	this.lang = lang;
	msgs = new Messages(lang);
    }

    public int getWarningLevel() {
	return warningLevel;
    }

    public void setWarningLevel(int warningLevel) {
	this.warningLevel = warningLevel;
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
     * Searches the properties list for a content-encoding one. If it does not
     * exist, searches for output-encoding-name. If it still does not exists,
     * the method returns the default utf-8 value
     * 
     * @return the output encoding of this ApplContext
     */
    public String getContentEncoding() {
	// return (msgs != null) ? msgs.getString("content-encoding") : null;
	String res = null;
	if (msgs != null) {
	    res = msgs.getString("content-encoding");
	    if (res == null) {
		res = msgs.getString("output-encoding-name");
	    }
	    if (res != null) {
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
	
    public String getLink() {
	return link;
    }

    public void setLink(String queryString) {
	this.link = queryString;
    }

    public boolean getTreatVendorExtensionsAsWarnings() {
        return treatVendorExtensionsAsWarnings;
    }

    public void setTreatVendorExtensionsAsWarnings(
        boolean treatVendorExtensionsAsWarnings) {
        this.treatVendorExtensionsAsWarnings = treatVendorExtensionsAsWarnings;
    }

    /**
     * Sets the content encoding to the first charset that appears in
     * <i>acceptCharset</i>. If the charset is not supported, the content
     * encoding will be utf-8
     * 
     * @param acceptCharset
     *            a String representing the Accept-Charset request parameter
     */
    public void setContentEncoding(String acceptCharset) {
	if (acceptCharset != null) {
	    // uses some Jigsaw classes to parse the Accept-Charset
	    // these classes need to load a lot of stuff, so it may be quite
	    // long the first time
	    HttpAcceptCharsetList charsetList;
	    HttpAcceptCharset[] charsets;

	    charsetList = HttpFactory.parseAcceptCharsetList(acceptCharset);
	    charsets = (HttpAcceptCharset[]) charsetList.getValue();

	    String encoding = null;
	    double quality = 0.0;

	    String biasedcharset = getMsg().getString("output-encoding-name");

	    for (int i = 0; i < charsets.length && quality < 1.0; i++) {
		HttpAcceptCharset charset = charsets[i];

		String currentCharset = charset.getCharset();

		// checks that the charset is supported by Java

		if (isCharsetSupported(currentCharset)) {
		    double currentQuality = charset.getQuality();

		    // we prefer utf-8
		    // FIXME (the bias value and the biased charset
		    // should be dependant on the language)
		    if ((biasedcharset != null) && 
			!biasedcharset.equalsIgnoreCase(currentCharset)) {
			currentQuality = currentQuality * 0.5;
		    }
		    if (currentQuality > quality) {
			quality = currentQuality;
			encoding = charset.getCharset();
		    }
		}
	    }
	    if (encoding != null) {
		getMsg().properties.setProperty("content-encoding", encoding);
	    } else {
		// no valid charset
		getMsg().properties.remove("content-encoding");
	    }
	} else {
	    // no Accept-Charset given
	    getMsg().properties.remove("content-encoding");
	}
    }

    private boolean isCharsetSupported(String charset) {
	if ("*".equals(charset)) {
	    return true;
	}
	try {
	    return Charset.isSupported(charset);
	} catch (Exception e) {
	    return false;
	}
    }

    /**
     * used for storing the charset of the document in use
     *  and its update by a @charset statement, or through 
     *  automatic discovery
     */
    public void setCharsetForURL(URL url, String charset) {
	if (uricharsets == null) {
	    uricharsets = new HashMap<URL,Charset>();
	}
	Charset c = null;
	try {
	    c = Charset.forName(charset);
	} catch (IllegalCharsetNameException icex) {
	    // FIXME add a warning in the CSS
	} catch (UnsupportedCharsetException ucex) {
	    // FIXME inform about lack of support
	}
	if (c != null) {
	    uricharsets.put(url, c);
	}
    }

    /**
     * used for storing the charset of the document in use
     *  and its update by a @charset statement, or through 
     *  automatic discovery
     */
    public void setCharsetForURL(URL url, Charset charset) {
	if (uricharsets == null) {
	    uricharsets = new HashMap<URL,Charset>();
	}
	uricharsets.put(url, charset);
    }
  
    /**
     * used for storing the charset of the document in use
     *  and its update by a @charset statement, or through 
     *  automatic discovery
     */
    public String getCharsetForURL(URL url) {
	Charset c;
	if (uricharsets == null) {
	    return null;
	}
	c = uricharsets.get(url);
	if (c != null) {
	    return c.toString();
	}
	return null;
    }
    
    /**
     * used for storing the charset of the document in use
     *  and its update by a @charset statement, or through 
     *  automatic discovery
     */
    public Charset getCharsetObjForURL(URL url) {
	Charset c;
	if (uricharsets == null) {
	    return null;
	}
	return uricharsets.get(url);
    }

    /**
     * store content of uploaded file 
     */
    public void setFakeFile(FakeFile fakefile) {
	this.fakefile = fakefile;
    }

    /**
     * store content of entered text
     */
    public void setFakeText(String faketext) {
	this.faketext = faketext;
    }

    public InputStream getFakeInputStream(URL source) 
	throws IOException 
    {
	InputStream is = null;
	if (fakefile != null) {
	    is = fakefile.getInputStream();
	}
	if (faketext != null) {
	    is = new ByteArrayInputStream(faketext.getBytes());
	}
	if (is == null) {
	    return null;
	}
	Charset c = getCharsetObjForURL(source);
	if (c == null) {
	    UnicodeInputStream uis = new UnicodeInputStream(is);
	    String guessedCharset = uis.getEncodingFromStream();
	    if (guessedCharset != null) {
		setCharsetForURL(source, guessedCharset);
	    }
	    return uis;
	} else {
	    if (utf8Charset.compareTo(c) == 0) {
		return new UnicodeInputStream(is);
	    }
	}
	return is;
    }
    
    public boolean isInputFake() {
	return ((faketext != null) || (fakefile != null));
    }

    public void setFakeURL(String fakeurl) {
	try {
	    this.fakeurl = new URL(fakeurl);
	} catch (Exception ex) {
	}
    }

    public URL getFakeURL() {
	return fakeurl;
    }
	
    /**
     * support for namespaces
     */
    public void setNamespace(URL url, String prefix, String nsname) {
	if (namespaces == null) {
	    namespaces = new HashMap<URL,HashMap<String,String>>();
	}
	// reformat the prefix if null.
	if ((prefix == null) || "".equals(prefix)) {
	    prefix = defaultPrefix;
	}

	HashMap<String,String> nsdefs = namespaces.get(url);
	if (nsdefs == null) {
	    nsdefs =  new HashMap<String,String>();
	    nsdefs.put(prefix, nsname);
	    namespaces.put(url, nsdefs);
	} else {
	    // do we need to check if we have a redefinition ?
	    nsdefs.put(prefix, nsname);
	}
    }

    // true if a namespace is defined in the document (CSS fragment)
    // defined by the URL, with prefix "prefix"
    public boolean isNamespaceDefined(URL url, String prefix) {
	if (prefix == null) { // no prefix, always match
	    return true;
	}
	if (prefix.equals("*")) { // any ns, always true
	    return true;
	}
	if ("".equals(prefix)) {
	    prefix = "*defaultprefix*";
	}
	HashMap<String,String> nsdefs = namespaces.get(url);
	if (nsdefs == null) {
	    return false;
	}
	return nsdefs.containsKey(prefix);
    }
}
