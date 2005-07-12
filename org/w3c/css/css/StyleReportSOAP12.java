// $Id$
// Author: Yves Lafon <ylafon@w3.org>
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;

import org.w3c.css.parser.CssError;
import org.w3c.css.parser.CssErrorToken;
import org.w3c.css.parser.CssParseException;
import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.Errors;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Utf8Properties;
import org.w3c.css.util.Util;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;

/**
 * @version $Revision$
 */
public final class StyleReportSOAP12 extends StyleReport 
    implements CssPrinterStyle {

    StyleSheet style;

    Vector items;

    Warnings warnings;

    Errors errors;

    ApplContext ac;
    
    private CssSelectors selector;

    private CssProperty property;

    private PrintWriter out;

    private int warningLevel;
    
    private Utf8Properties general;

    private static Utf8Properties availableFormat;

    private static Utf8Properties availablePropertiesURL;

    private static Hashtable formats = new Hashtable();

    int counter = 0;

    private final static String DEFAULT_CHARSET = "utf-8";

    /**
     * Create a new StyleSheetGenerator
     *
     * @param title
     *            The title for the generated document
     * @param style
     *            The style sheet
     * @param document
     *            The name of the source file
     * @param warningLevel
     *            If you want to reduce warning output. (-1 means no warnings)
     */
    public StyleReportSOAP12(ApplContext ac, String title, StyleSheet style,
			     String document, int warningLevel) {

	if (document == null) {
	    document = "soap12.en";
	}
	if (Util.onDebug) {
	    System.err.println("document format is " + document);
	}
	this.ac = ac;
	this.style = style;
	general = new Utf8Properties(setDocumentBase(getDocumentName(ac, document)));
	general.put("file-title", title);
	warnings = style.getWarnings();
	errors = style.getErrors();
	items =  style.newGetRules();

	this.warningLevel = warningLevel;
	
	general.put("cssversion", ac.getCssVersion());
	general.put("errors-count", Integer.toString(errors.getErrorCount()));
	general.put("warnings-count", Integer.toString(warnings
						       .getWarningCount()));
	general.put("rules-count", Integer.toString(items.size()));
	general.put("isvalid", (errors.getErrorCount() == 0) ? "true"
		    : "false");
	
	if (ac.getContentEncoding() != null) {
	    general.put("encoding", " encoding=\"" + ac.getContentEncoding()
			+ "\" ");
	} else {
	    general.put("encoding", " ");
	}
	if (errors.getErrorCount() == 0) {
	    desactivateError();
	}
	if ((errors.getErrorCount() != 0) || (!title.startsWith("http://"))) {
	    general.put("no-errors", "");
	}
	if (style.charset == null) {
	    general.put("charset-rule", "");
	}
	if (warnings.getWarningCount() == 0 || warningLevel == -1) {
	    general.put("go-warnings", ""); // remove go-warnings
	    general.put("warnings", ""); // remove warnings
	}
	if (items.size() == 0) {
	    general.put("go-rules", ""); // remove go-rules
	    general.put("rules", ""); // remove rules
	    general.put("no-errors", "");
	} else {
	    general.put("no-rules", ""); // remove no-rules
	}
	
	if (errors.getErrorCount() != 0 || warnings.getWarningCount() != 0) {
	    // remove no-error-or-warning
	    general.put("no-error-or-warning", ""); 
	}

	if (Util.onDebug)
	    general.list(System.err);

	DateFormat df = null;

	if (ac.getLang() != null) {
	    try {
		df = DateFormat.getDateTimeInstance(DateFormat.FULL,
						    DateFormat.FULL, new Locale(ac.getLang()
										.substring(0, 2), "US"));
	    } catch (Exception e) {
		df = DateFormat.getDateTimeInstance(DateFormat.FULL, 
						    DateFormat.FULL, Locale.US);
	    }
	}
	if (df != null) {
	    general.put("today", df.format(new Date()));
	} else {
	    general.put("today", new Date().toString());
	}
	SimpleDateFormat formatter = new SimpleDateFormat(
	    "yyyy.MM.dd'T'hh:mm:ss'Z'");
	formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	general.put("currentdate", formatter.format(new Date()));
    }
    
    public void desactivateError() {
	general.put("go-errors", ""); // remove go-errors
	general.put("errors", ""); // remove errors
    }
    
    /**
     * Returns a string representation of the object.
     */
    public void print(PrintWriter out) {
	this.out = out; // must be in first !
	String output = processSimple("document");
	if (output != null) {
	    out.println(output);
	} else {
	    out.println(ac.getMsg().getGeneratorString("request"));
	}
	
	out.flush();
    }
    
    // prints the stylesheet at the screen
    public void produceStyleSheet() {

    }
    
    public void print(CssProperty property) {
	Utf8Properties prop = new Utf8Properties(general);
	prop.put("property-name", property.getPropertyName().toString());
	prop.put("property-value", property.toString());
	
	if (!property.getImportant()) {
	    prop.put("important-style", "");
	}
	out.print(processStyle(prop.getProperty("declaration"), prop));
    }
    
    public void produceParseException(CssParseException error) {
	if (error.getContexts() != null && error.getContexts().size() != 0) {
	    StringBuffer buf = new StringBuffer();
	    for (Enumeration e = error.getContexts().elements(); e
		     .hasMoreElements();) {
		Object t = e.nextElement();
		if (t != null) {
		    buf.append(t);
		    if (e.hasMoreElements())
			buf.append(", ");
		}
	    }
	    if (buf.length() != 0) {
		out.print("parse-error</m:errortype>\n\t\t\t<m:context>");
		out.print(buf);
		out.print("</m:context>\n");
	    }
	}
	else {
	    out.print("parse-error</m:errortype>\n");
	}
	String name = error.getProperty();
	if ((name != null) && (getURLProperty(name) != null)) {
	    out.print("\t\t\t<m:property>");
	    out.print(name);
	    out.print("</m:property>\n");
	}
	if ((error.getException() != null) && (error.getMessage() != null)) {
	    if (error.isParseException()) {
		out.print("\t\t\t<m:message>");
		out.print(queryReplace(error.getMessage()));
		out.print("</m:message>\n");
	    } else {
		Exception ex = error.getException();
		if (ex instanceof NumberFormatException) {
		    out.print("\t\t\t<m:errorsubtype>invalid-number"
			      + "</m:error-subtype>\n");
		} else {
		    out.print("\t\t\t<m:message>");
		    out.print(queryReplace(ex.getMessage()));
		    out.print("</m:message>\n");
		}
	    }
	    if (error.getSkippedString() != null) {
		out.print("\t\t\t<m:skippedstring>");
		out.print(queryReplace(error.getSkippedString()));
		out.print("</m:skippedstring>\n");
	    } else if (error.getExp() != null) {
		out.print("\t\t\t<m:expression>\n\t\t\t\t<m:start>");
		out.print(queryReplace(error.getExp().toStringFromStart()));
		out.print("</m:start>\n\t\t\t\t<m:end>");
		out.print(queryReplace(error.getExp().toString()));
		out.print("</m:end>\n\t\t\t</m:expression>\n");
	    }
	} else {
	    out.print("\t\t\t<m:errorsubtype>unrecognized</m:errorsubtype>\n");
	    out.print("\t\t\t<m:skippedstring>");
	    out.print(queryReplace(error.getSkippedString()));
	    out.print("</m:skippedstring>\n");
	}
    }

    /**
     * Produce SOAP elements for all the errors found
     *  in the StyleSheet <i>style</i>	 
     */
    public void produceError() {
	String oldSourceFile = null;
	boolean open = false;

	try {
	    if (errors.getErrorCount() != 0) {
		int i = 0;
		for (CssError[] error = errors.getErrors(); i < error.length; i++) {
		    Exception ex = error[i].getException();
		    String file = error[i].getSourceFile();
		    if (!file.equals(oldSourceFile)) {
			oldSourceFile = file;
			if (open) {
			    out.print("</m:errorlist>\n");
			}
			out.print("<m:errorlist>\n");
			open = true;
		    }
		    out.print("\t\t<m:error>\n\t\t\t<m:line>");
		    out.print(error[i].getLine());
		    out.print("</m:line>\n\t\t\t<m:errortype>");						
		    if (ex instanceof FileNotFoundException) {
			out.print("not-found");
			out.print("</m:errortype>\n\t\t\t<m:message>");
			out.print(ex.getMessage());
			out.print("</m:message>\n");			
		    } else if (ex instanceof CssParseException) {
			produceParseException((CssParseException) ex);
		    } else if (ex instanceof InvalidParamException) {
			out.print("invalid-parameter");
			out.print("</m:errortype>\n\t\t\t<m:message>");
			out.print(queryReplace(ex.getMessage()));
			out.print("\t\t\t</m:message>\n");
		    } else if (ex instanceof IOException) {
			out.print("IOException</m:errortype>\n");
			out.print("\t\t\t<m:message>");
			out.print(queryReplace(ex.getMessage()));
			out.print("\t\t\t</m:message>\n");
		    } else if (error[i] instanceof CssErrorToken) {
			out.print("csserror</m:errortype>");
			CssErrorToken terror = (CssErrorToken) error[i];
			out.print("\n\t\t\t\t<m:description>");
			out.print(terror.getErrorDescription());
			out.print("</m:description>\n");
			out.print("\t\t\t\t<m:skippedstring>");
			out.print(terror.getSkippedString());
			out.print("</m:skippedstring>\n");
		    } else {
			out.print("uncaught");
			out.print("</m:errortype>\n\t\t\t<m:message>");
			out.print(queryReplace(ex.getMessage()));
			out.print("</m:message>\n");
			if (ex instanceof NullPointerException) {
			    // ohoh, a bug
			    out.print("nullpointer");
			    out.print("</m:errortype>\n\t\t\t<m:message>");
			    ex.printStackTrace(out);
			    out.print("</m:message>\n");
			}
		    }
		    out.print("\t\t</m:error>\n");
		}
		out.print("\t</m:errorlist>");
	    }
	} catch (Exception e) {
	    out.print("<m:processingerror>");
	    out.println(ac.getMsg().getGeneratorString("request"));
	    e.printStackTrace(out);
	    out.print("</m:processingerror>\n");
	}
    }
    
    /**
     * Produce SOAP elements for all the warnings found
     *  in the StyleSheet <i>style</i>	 
     */
    public void produceWarning() {
	boolean open = false;
	String oldSourceFile = "";
	int oldLine = -1;
	String oldMessage = "";
	try {
	    if (warnings.getWarningCount() != 0) {
		int i = 0;
		warnings.sort();
		for (Warning[] warning = warnings.getWarnings(); i < warning.length; i++) {
		    
		    Warning warn = warning[i];
		    if (warn.getLevel() <= warningLevel) {
			if (!warn.getSourceFile().equals(oldSourceFile)) {
			    if (open) {
				out.print("</m:warninglist>\n");
			    }
			    oldSourceFile = warn.getSourceFile();
			    out.print("<m:warninglist>\n");
			    open = true;
			}
			if (warn.getLine() != oldLine
			    || !warn.getWarningMessage().equals(oldMessage)) {
			    oldLine = warn.getLine();
			    oldMessage = warn.getWarningMessage();
			    out.print("<m:warning>\n<m:line>");
			    out.print(oldLine);
			    out.print("</m:line>\n");
			    
			    //    if (warn.getLevel() != 0) {
			    //	ret.append(" Level : ");
			    //	ret.append(warn.getLevel());
			    // }
			    out.print("<m:level>");
			    out.print(warn.getLevel());
			    out.print("</m:level>\n");
			    // out.print("<message>");
			    // out.print(oldMessage);
			    // out.print("</message>\n");
			    if (warn.getContext() != null) {
				out.print("<m:context>");
				out.print(warn.getContext());
				out.print("</m:context>\n");
			    }
			    out.print("</m:warning>\n");
			}
		    }
		}
		out.print("</m:warninglist>");
	    }
	} catch (Exception e) {
	    out.print("<m:processingerror>");
	    out.println(ac.getMsg().getGeneratorString("request"));
	    e.printStackTrace(out);
	    out.print("</m:processingerror>\n");
	}
    }
    
    /*
     * Replace all occurences of < and > in a String with
     *  their html values: &lt; and &gt;
     * @param s the String with < and >
     * @return the corresponding String with &lt; and &gt; replacing < and >
     */
    private String queryReplace(String s) {
	if (s != null) {
	    int len = s.length();
	    StringBuffer ret = new StringBuffer(len);	    
	    
	    for (int i = 0; i < len; i++) {
		char c = s.charAt(i);
		if (c == '<') {
		    ret.append("&lt;");
		} else if (c == '>') {
		    ret.append("&gt;");
		} else {
		    ret.append(c);
		}
	    }
	    return ret.toString();
	} else {
	    return "[empty string]";
	}
    }
    
    private final String processSimple(String s) {
	return processStyle(general.getProperty(s), general);
    }
    
    private String processStyle(String str, Utf8Properties prop) {
	try {
	    int i = 0;
	    while ((i = str.indexOf("<!-- #", i)) >= 0) {
		int lastIndexOfEntity = str.indexOf("-->", i);
		String entity = str.substring(i + 6, lastIndexOfEntity - 1)
		    .toLowerCase();

		if (entity.equals("rule")) {
                    out.print(str.substring(0, i));
		    str = str.substring(lastIndexOfEntity + 3);
		    i = 0;
		    produceStyleSheet();
		} else if (entity.equals("selectors")) {
		    if (selector.getNext() != null) {
			// contextuals selectors
			String value = prop.getProperty(entity);
			if (value != null) {
			    str = str.substring(0, i) + value
				+ str.substring(lastIndexOfEntity + 3);
			} else {
			    i += 6; // skip this unknown entity
			}
		    } else {
			str = str.substring(lastIndexOfEntity + 3);
			i = 0;
		    }
		} else if (entity.equals("selector")) {
		    str = str.substring(lastIndexOfEntity + 3);
		    i = 0;
		} else if (entity.equals("charset")) {
		    out.print(str.substring(0, i));
		    str = str.substring(lastIndexOfEntity + 3);
		    i = 0;
		    out.print(ac.getContentEncoding());
		} else if (entity.equals("declaration")) {
		    str = str.substring(lastIndexOfEntity + 3);
		    i = 0;
		} else if (entity.equals("warning")) {
		    out.print(str.substring(0, i));
		    str = str.substring(lastIndexOfEntity + 3);
		    i = 0;
		    produceWarning();
		} else if (entity.equals("error")) {
		    out.print(str.substring(0, i));
		    str = str.substring(lastIndexOfEntity + 3);
		    i = 0;
		    produceError();
		} else if (entity.equals("hook-html-validator")) {
		    out.print(str.substring(0, i));
		    str = str.substring(lastIndexOfEntity + 3);
		    i = 0;
		    if (style.getType().equals("text/html")) { 
			out.println(ac.getMsg().getGeneratorString("doc-html",
								   general.get("file-title").toString()));
		    } else {
			out.println(ac.getMsg().getGeneratorString("doc"));
		    }
		} else {
		    String value = prop.getProperty(entity);
		    if (value != null) {
			str = str.substring(0, i) + value
			    + str.substring(lastIndexOfEntity + 3);
		    } else {
			i += 6; // skip this unknown entity
		    }
		}
	    }
	    
	    return str;
	} catch (Exception e) {
	    e.printStackTrace();
	    return str;
	}
    }
    
    /**
     * List the available output formats on a PrintWriter
     * @param out the PrintWriter used to write the listing
     */
    public final static void printAvailableFormat(PrintWriter out) {
	Enumeration e = availableFormat.propertyNames();
	out.println(" -- listing available output format --");
	while (e.hasMoreElements()) {
	    String key = ((String) e.nextElement()).toLowerCase();
	    out.println("Format : " + key);
	    out.println("   File : " + getDocumentName(null, key));
	}
	out.flush();
    }
    
    private Utf8Properties setDocumentBase(String document) {
	Utf8Properties properties = (Utf8Properties) formats.get(document);
	if (properties == null) {
	    URL url;
	    properties = new Utf8Properties();
	    try {
		url = StyleSheetGenerator.class.getResource(document);
		java.io.InputStream f = url.openStream();
		properties.load(f);
		f.close();
		properties.put("author", "www-validator-css");
		properties.put("author-email", "Email.html");
	    } catch (Exception e) {
		System.err.println("org.w3c.css.css.StyleSheetGenerator: "
				   + "couldn't load properties " + document);
		System.err.println("  " + e.toString());
		printAvailableFormat(new PrintWriter(System.err));
	    }
	    formats.put(document, properties);
	}

	return new Utf8Properties(properties);
    }
    
    private final static String getDocumentName(ApplContext ac, 
						String documentName) {
	documentName = documentName.toLowerCase();
	String document = null;
	if (ac != null && ac.getLang() != null) {
	    StringTokenizer tokens = new StringTokenizer(ac.getLang(), ",");
	    
	    while (tokens.hasMoreTokens()) {
		String l = tokens.nextToken().trim().toLowerCase();
		document = availableFormat.getProperty(documentName + "." + l);
		if (document != null) {
		    break;
		}
		int minusIndex = l.indexOf('-');
		if (minusIndex != -1) {
		    // suppressed -cn in zh-cn (example)
		    l = l.substring(0, minusIndex);
		    document = availableFormat.getProperty(documentName + "."
							   + l);
		}
		if (document != null) {
		    break;
		}		
	    }
	}
	if (document == null) {
	    document = availableFormat.getProperty(documentName);
	}
	if (document == null) {
	    System.err.println("Unable to find " + documentName
			       + " output format");
	    return documentName;
	} else {
	    return document;
	}
    }
    
    private final static String getURLProperty(String name) {
	return availablePropertiesURL.getProperty(name);
    }
    
    static {
	URL url;
	// the different available output formats
	availableFormat = new Utf8Properties();
	try {
	    url = StyleSheetGenerator.class.getResource("format.properties");
	    java.io.InputStream f = url.openStream();
	    availableFormat.load(f);
	    f.close();
	} catch (Exception e) {
	    System.err.println("org.w3c.css.css.StyleSheetGenerator: "
			       + "couldn't load format properties ");
	    System.err.println("  " + e.toString());
	}
	// links to properties documentation
	availablePropertiesURL = new Utf8Properties();
	try {
	    url = StyleSheetGenerator.class.getResource("urls.properties");
	    java.io.InputStream f = url.openStream();
	    availablePropertiesURL.load(f);
	    f.close();
	} catch (Exception e) {
	    System.err.println("org.w3c.css.css.StyleSheetGenerator: "
			       + "couldn't load URLs properties ");
	    System.err.println("  " + e.toString());
	}
    }
}
