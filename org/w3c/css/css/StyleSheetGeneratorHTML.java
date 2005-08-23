//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.3  2005/07/12 14:47:55  ylafon
 * Utf8 properties (Jean-Guilhem Rouel)
 *
 * Revision 1.2  2002/05/19 04:16:31  plehegar
 * Replaced the email address
 *
 * Revision 1.1  2002/03/13 19:55:01  plehegar
 * New
 *
 *
 * @@HACK
 */
package org.w3c.css.css;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.StringTokenizer;

import org.w3c.css.parser.CssError;
import org.w3c.css.parser.CssErrorToken;
import org.w3c.css.parser.CssParseException;
import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.Errors;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.SortedHashtable;
import org.w3c.css.util.Utf8Properties;
import org.w3c.css.util.Util;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;

/**
 * @version $Revision$
 */
public final class StyleSheetGeneratorHTML implements CssPrinterStyle {

    StyleSheet style;

    SortedHashtable items;

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
	public StyleSheetGeneratorHTML(ApplContext ac, String title,
			StyleSheet style, String document, int warningLevel) {
	this.ac = ac;
	this.style = style;
		general = new Utf8Properties(setDocumentBase(getDocumentName(ac, document)));
	general.put("file-title", title);
	warnings = style.getWarnings();
	errors = style.getErrors();
	items = (SortedHashtable) style.getRules();
	this.warningLevel = warningLevel;
	
		general.put("errors-count", Integer.toString(errors.getErrorCount()));
		general.put("warnings-count", Integer.toString(warnings
				.getWarningCount()));
		general.put("rules-count", Integer.toString(items.size()));
	
	if (errors.getErrorCount() == 0) {
	    desactivateError();
	}
		if ((errors.getErrorCount() != 0) || (!title.startsWith("http://"))) {
	    general.put("no-errors", "");
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
    
    public void produceRule() {
	Object[] array = items.getSortedArray();
	for (int i = 0; i < array.length; i++) {
	    selector = (CssSelectors) array[i];
	    if (!selector.isEmpty()) {
		out.print(processStyle(general.getProperty("rule"), general));
	    }
	}
    }
    
    public void produceSelector(CssSelectors selectorLocal) {
	out.print(selectorLocal);
    }
    
    public void produceDeclaration() {
	selector.getStyle().print(this);
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
    
	public void produceParseException(CssParseException error, StringBuffer ret) {
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
		ret.append(ac.getMsg().getGeneratorString("context"));
		ret.append(" : <STRONG>").append(buf);
		ret.append("</STRONG> ");
	    }
	}
	String name = error.getProperty();
	if ((name != null) && (getURLProperty(name) != null)) {
	    ret.append(ac.getMsg().getGeneratorString("property"));
	    ret.append(" : <A HREF=\"");
	    ret.append(getURLProperty("@url-base"));
	    ret.append(getURLProperty(name)).append("\">");
	    ret.append(name).append("</A>");
	}
	if ((error.getException() != null) && (error.getMessage() != null)) {
	    ret.append("\n<DD>");
	    if (error.isParseException()) {
		ret.append(queryReplace(error.getMessage())).append('\n');
	    } else {
		Exception ex = error.getException();
		if (ex instanceof NumberFormatException) {
					ret
							.append(ac.getMsg().getGeneratorString(
									"invalid-number"));
		} else {
		    ret.append(queryReplace(ex.getMessage()));
		}
	    }
	    if (error.getSkippedString() != null) {
		ret.append(" : <STRONG>");
		ret.append(queryReplace(error.getSkippedString()));
		ret.append("</STRONG>\n");
	    } else if (error.getExp() != null) {
		ret.append(" : ");
		ret.append(queryReplace(error.getExp().toStringFromStart()));
		ret.append("<STRONG>");
		ret.append(queryReplace(error.getExp().toString()));
		ret.append("</STRONG>\n");		
	    }
	} else {
	    ret.append("\n<DD>");
	    ret.append(ac.getMsg().getGeneratorString("unrecognize"));
	    ret.append(" - <STRONG>");
	    ret.append(queryReplace(error.getSkippedString()));
	    ret.append("</STRONG>\n");
	}
	
    }

    public void produceError() {
	StringBuffer ret = new StringBuffer(1024);
	String oldSourceFile = null;

	try {
	    if (errors.getErrorCount() != 0) {
		int i = 0;
		ret.append("\n<UL>");
				for (CssError[] error = errors.getErrors(); i < error.length; i++) {
		    Exception ex = error[i].getException();
		    String file = error[i].getSourceFile();
		    if (!file.equals(oldSourceFile)) {
			oldSourceFile = file;
						/*
						 * if (i != 0) { ret.append("</DL>"); }
						 */
			ret.append("\n<LI><DL> URI : "
				   + "<A TARGET=\"workspace\" HREF=\"");
			ret.append(file).append("\">");
			ret.append(file).append("</A>");
		    }
		    ret.append("\n<DT>");
		    ret.append(ac.getMsg().getGeneratorString("line"));
		    ret.append(": ").append(error[i].getLine());
		    ret.append(' ');
		    if (ex instanceof FileNotFoundException) {
			ret.append("\n<DD>");
			ret.append(ac.getMsg().getGeneratorString("not-found"));
			ret.append("<STRONG>");
			ret.append(ex.getMessage());
			ret.append("</STRONG>\n");			
		    } else if (ex instanceof CssParseException) {
			produceParseException((CssParseException) ex, ret);
		    } else if (ex instanceof InvalidParamException) {
			ret.append("\n<DD>");
			ret.append(queryReplace(ex.getMessage())).append('\n');
		    } else if (ex instanceof IOException) {
			String stringError = ex.toString();
			int index = stringError.indexOf(':');
			ret.append("\n<DD>");
			ret.append(stringError.substring(0, index));
			ret.append(" : <STRONG>");
			ret.append(ex.getMessage()).append("<STRONG>\n");
		    } else if (error[i] instanceof CssErrorToken) {
			CssErrorToken terror = (CssErrorToken) error[i];
			ret.append("\n<DD>").append("   ");
			ret.append(terror.getErrorDescription()).append(" : ");
			ret.append(terror.getSkippedString()).append('\n');			
		    } else {
			ret.append("\n<DD>");
			ret.append("<STRONG>Uncaught error</STRONG>");
			ret.append(ex).append('\n');
			
			if (ex instanceof NullPointerException) {
			    // ohoh, a bug
			    ex.printStackTrace();
			}
		    }
		}
		ret.append("</DL></UL>");
	    }

	    out.println(ret.toString());
	} catch (Exception e) {
	    out.println(ac.getMsg().getGeneratorString("request"));
	    e.printStackTrace();
	}
    }
    
    public void produceWarning() {

	StringBuffer ret = new StringBuffer(1024);
	String oldSourceFile = "";
	int oldLine = -1;
	String oldMessage = "";
	try {
	    if (warnings.getWarningCount() != 0) {
		int i = 0;
		warnings.sort();
		ret.append("\n<UL>");
				for (Warning[] warning = warnings.getWarnings(); i < warning.length; i++) {
		    
		    Warning warn = warning[i];
		    if (warn.getLevel() <= warningLevel) {
			if (!warn.getSourceFile().equals(oldSourceFile)) {
			    if (i != 0) {
				ret.append("</DL>");
			    }
			    oldSourceFile = warn.getSourceFile();
			    ret.append("\n<LI><DL><DT> URI : "
				       + "<A TARGET=\"workspace\" HREF=\"");
			    ret.append(oldSourceFile).append("\">");
			    ret.append(oldSourceFile).append("</A>");
			}
						if (warn.getLine() != oldLine
								|| !warn.getWarningMessage().equals(oldMessage)) {
			    oldLine = warn.getLine();
			    oldMessage = warn.getWarningMessage();
			    ret.append("\n<DD><STRONG> Line : ");
			    ret.append(oldLine);
			    
			    if (warn.getLevel() != 0) {
				ret.append(" Level : ");
				ret.append(warn.getLevel());
			    }
			    ret.append(" </STRONG> ");
			    ret.append(oldMessage);
			    
			    if (warn.getContext() != null) {
				ret.append(" : ").append(warn.getContext());
			    }
			}
		    }
		}
		ret.append("</DL></UL>");
	    }
	    out.println(ret.toString());
	} catch (Exception e) {
	    out.println(ac.getMsg().getGeneratorString("request"));
	    e.printStackTrace();
	}
    }
    
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
		    produceRule();
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
			out.print(str.substring(0, i));
						str = str.substring(lastIndexOfEntity + 3);
			i = 0;
			produceSelector(selector);
		    }
		} else if (entity.equals("selector")) {
		    out.print(str.substring(0, i));
					str = str.substring(lastIndexOfEntity + 3);
		    i = 0;
		    produceSelector(selector);
		} else if (entity.equals("declaration")) {
		    out.print(str.substring(0, i));
					str = str.substring(lastIndexOfEntity + 3);
		    i = 0;
		    produceDeclaration();
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
