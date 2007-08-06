package org.w3c.css.css;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.Vector;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.MethodInvocationException;
import org.w3c.css.error.ErrorReportHTML;
import org.w3c.css.parser.CssError;
import org.w3c.css.parser.CssErrorToken;
import org.w3c.css.parser.CssParseException;
import org.w3c.css.parser.Errors;
import org.w3c.css.properties.PropertiesLoader;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Messages;
import org.w3c.css.util.Utf8Properties;
import org.w3c.css.util.Warnings;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/**
 * @author Julien Grand-Mourcel
 * @author Maria Kaceriakova
 * @date 2007-06-29
 * This class uses Velocity to output the validator results
 * 
 */
public class StyleSheetGenerator extends StyleReport {

	private PrintWriter out;
	private Template template;
	private String template_file;
	private String default_lang = "en"; 
	private ApplContext ac;
	private StyleSheet style;
	private String title;
	private VelocityContext context;
	private Warnings warnings;
	private Errors errors;
	private Vector items;
	private static Utf8Properties availableFormat;
	private static Utf8Properties availablePropertiesURL;

	static {
		URL url;
		availableFormat = new Utf8Properties();
		try {
			url = StyleSheetGenerator.class.getResource("format.properties");
			java.io.InputStream f = url.openStream();
			availableFormat.load(f);
			f.close();
		} catch (Exception e) {
			System.err.println("org.w3c.css.css.StyleSheetGeneratorHTML: couldn't load format properties ");
			System.err.println("  " + e.toString());
		}

		availablePropertiesURL = new Utf8Properties();
		try {
			url = StyleSheetGenerator.class.getResource("urls.properties");
			java.io.InputStream f = url.openStream();
			availablePropertiesURL.load(f);
			f.close();
		} catch (Exception e) {
			System.err.println("org.w3c.css.css.StyleSheetGeneratorHTML: couldn't load URLs properties ");
			System.err.println("  " + e.toString());
		}
	}
	
	public StyleSheetGenerator(String title, StyleSheet style, String document, int warningLevel) {
		this(null, title, style, document, warningLevel);
	}

	public StyleSheetGenerator(ApplContext ac, String title, StyleSheet style, String document, int warningLevel) {
		this.ac = ac;
		this.style = style;
		this.title = title;
		this.template_file = availableFormat.getProperty(document);

		context = new VelocityContext();
		context.put("file_title", title);
		// W3C_validator_result
		warnings = style.getWarnings();
		errors = style.getErrors();
		items = style.newGetRules();

		// Setting all the variables of the velocity context
		ApplContext ac_default = new ApplContext(default_lang);
		String k;
		if (ac.getLang() == null || ac.getLang().equals(default_lang)) {
			Iterator it = ac_default.getMsg().properties.keySet().iterator();
			while (it.hasNext()) {
				k = String.valueOf(it.next());
				context.put(k, ac.getMsg().getString(k));
			}
		} else {
			Iterator it = ac_default.getMsg().properties.keySet().iterator();
			while (it.hasNext()) {
				k = String.valueOf(it.next());
				if (ac.getMsg().getString(k) == null)
					context.put(k, ac_default.getMsg().getString(k));
				else
					context.put(k, ac.getMsg().getString(k));
			}
		}
		
		if (ac.getLink() != null) {
			HashMap[] languages = new HashMap[Messages.languages_name.size()];
			String name;
			for (int i = 0; i < Messages.languages_name.size(); ++i) {
				name = String.valueOf(Messages.languages_name.get(i));
				HashMap l = new HashMap();
				l.put("name", name);
				l.put("real", ((Utf8Properties) Messages.languages.get(name)).getProperty("language_name"));
				languages[i] = l;
			}
			context.put("languages", languages);
			String link = ac.getLink().replaceAll("&lang=.*&", "&");
			link = link.replaceAll("&lang=.*$", "");
			context.put("link", "?" + link.replaceAll("&", "&amp;"));
		}
		
		// generated values
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mss'Z'");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		context.put("currentdate", formatter.format(new Date()));
		context.put("lang", ac.getContentLanguage());
		context.put("errors_count", new Integer(errors.getErrorCount()));
		context.put("warnings_count", new Integer(warnings.getWarningCount()));
		context.put("ignored-warnings_count", new Integer(warnings.getIgnoredWarningCount()));
		context.put("warning_level", new Integer(warningLevel));
		context.put("rules_count", new Integer(items.size()));
		context.put("no_errors_report", new Boolean(false));
		context.put("charset", ac.getContentEncoding());
		context.put("cssversion", ac.getCssVersion());
		context.put("css_link", getURLProperty("@url-base_"+ac.getCssVersion()));
		context.put("is_valid", (errors.getErrorCount() == 0) ? "true" : "false");
		context.put("author", "www-validator-css");
		context.put("author-email", "Email.html");
		if (style.charset != null)
			context.put("style_charset", style.charset);

		produceError();
		produceWarning();
		produceStyleSheet();

		try {
            Velocity.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, this.getClass().getResource("").getPath());
            Velocity.init();
			template = Velocity.getTemplate(template_file);
		} catch (ResourceNotFoundException rnfe) {
			System.err.println(rnfe.getMessage());
			rnfe.printStackTrace();
		} catch (ParseErrorException pee) {
			System.err.println(pee.getMessage());
			pee.printStackTrace();
		} catch (MethodInvocationException mie) {
			System.err.println(mie.getMessage());
			mie.printStackTrace();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private final static String getURLProperty(String name) {
		return availablePropertiesURL.getProperty(name);
	}
	
	public final static void printAvailableFormat(PrintWriter out) {
        Enumeration e = availableFormat.propertyNames();
                out.println(" -- listing available output format --");
        while (e.hasMoreElements()) {
            String key = ((String) e.nextElement()).toLowerCase();
                        out.println("Format : " + key);
                        out.println("   File : " + getDocumentName(key));
        }
        out.flush();
    }
	

	/**
	 * Test if <tt>document</tt> is an available output
	 * @param document, the desired output
	 * @return <tt>true</tt> if the desired output is available
	*/
	public static boolean isAvailableFormat(String document) {
		return availableFormat.containsKey(document);
	}
	

    private final static String getDocumentName(String documentName) {
                String document = availableFormat.getProperty(documentName
                                .toLowerCase());
        if (document == null) {
                        System.err.println("Unable to find " + documentName.toLowerCase()
                                        + " output format");
            return documentName;
        } else {
            return document;
        }
    }
	
	/**
	 * Add the style information to the context
	 */
	private void produceStyleSheet() {
		context.put("at_rules_list", style.newGetRules());
	}

	/**
	 * Add the errors information to the context
	 * For each error,
	 * <ul>
	 * <li> the error type and message, and
	 * <li> the context type and value
	 * </ul>
	 * are set.
	 */
	private void produceError() {
		Hashtable[] errors_content = new Hashtable[errors.getErrorCount()];

		try {
			if (errors.getErrorCount() != 0) {
				int i = 0;
				for (CssError[] error = errors.getErrors(); i < error.length; i++) {
					Exception ex = error[i].getException();
					errors_content[i] = new Hashtable();
					errors_content[i].put("Error", error[i]);
					errors_content[i].put("CtxName", "nocontext");
					errors_content[i].put("CtxMsg", "");
					errors_content[i].put("ErrorMsg", ((ex.getMessage() == null) ? "" : ex.getMessage()));
					errors_content[i].put("ClassName", "unkownerror");

					if (ex instanceof FileNotFoundException) {
						errors_content[i].put("ClassName", "notfound");
						errors_content[i].put("ErrorMsg", ac.getMsg().getGeneratorString("not-found") + ": " + ex.getMessage());

					} else if (ex instanceof CssParseException) {
						produceParseException((CssParseException) ex, errors_content[i]);
					} else if (ex instanceof InvalidParamException) {
						errors_content[i].put("ClassName", "invalidparam");
					} else if (ex instanceof IOException) {
						String stringError = ex.toString();
						// int index = stringError.indexOf(':');
						// The Exception name 'StringError' was previously
						// displayed
						// between </td> and <td class='nocontext' ...
						// It's now displayed inside the <td class='nocontext'>
						// tag
						// because we would need a new variable to put it there
						// for
						// just one rare case
						// TODO: why not using ex.toString()?
						errors_content[i].put("CtxMsg", stringError);// .substring(0,
																		// index));
						errors_content[i].put("ClassName", "io");

					} else if (error[i] instanceof CssErrorToken) {
						CssErrorToken terror = (CssErrorToken) error[i];
						errors_content[i].put("ClassName", "errortoken");
						errors_content[i].put("ErrorMsg", terror.getErrorDescription() + " : "
								+ terror.getSkippedString());
					} else {
						errors_content[i].put("ClassName", "unkownerror");
						errors_content[i].put("ErrorMsg", ac.getMsg().getErrorString("unknown") + " " + ex);
						if (ex instanceof NullPointerException) {
							// ohoh, a bug
							ex.printStackTrace();
						}
					}
				}
			}
			context.put("errors_content", errors_content);
		} catch (Exception e) {
			context.put("errors_content", errors_content);
			context.put("request", ac.getMsg().getGeneratorString("request"));
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Add an array of warnings to the context so it c an be displayed
	 * 
	 */
	private void produceWarning() {
		try {
			if (warnings.getWarningCount() != 0) {
				warnings.sort();
				context.put("warnings_list", warnings.getWarnings());
			}
		} catch (Exception e) {
			out.println(ac.getMsg().getGeneratorString("request"));
			e.printStackTrace();
		}
	}

	/**
	 * Some error need more details
	 * We can add a link or information of this kind
	 * @param error, the error to check
	 * @param ht_error, the Hastable with information about this error
	 */
	private void produceParseException(CssParseException error, Hashtable ht_error) {
		if (error.getContexts() != null && error.getContexts().size() != 0) {
			ht_error.put("CtxName", "codeContext");
			StringBuffer buf = new StringBuffer();
			// Loop on the list of contexts for errors
			for (Enumeration e = error.getContexts().elements(); e.hasMoreElements();) {
				Object t = e.nextElement();
				// if the list is not null, add a comma
				if (t != null) {
					buf.append(t);
					if (e.hasMoreElements()) {
						buf.append(", ");
					}
				}
			}
			if (buf.length() != 0)
				ht_error.put("CtxMsg", String.valueOf(buf));
		} else {
			ht_error.put("CtxName", "nocontext");
		}
		ht_error.put("ClassName", "parse-error");
		String name = error.getProperty();
		String ret;
		if ((name != null) && (getURLProperty(name) != null) && PropertiesLoader.getProfile(ac.getCssVersion()).containsKey(name)) {
			//we add a link information
			// we check if the property doesn't exist in this css version
			ht_error.put("link_before_parse_error", ac.getMsg().getGeneratorString("property"));
			// Since CSS3 is only a working draft, the links don't exist yet in CSS3...
			// And this is the same with CSS1 because the links are not working the same way...
			// This can be removed as soon as the CSS3 specifications are made and CSS1 use the links
			// and the link is changed in urls.properties
			String lnk;
			if (ac.getCssVersion().equals("css3"))
				lnk = getURLProperty("@url-base_css2.1");
			else if (ac.getCssVersion().equals("css1"))
				lnk = getURLProperty("@url-base_css2"); 
			else
				lnk = context.get("css_link").toString();
			// this would be replaced by :
			// ht_error.put("link_value_parse_error", context.get("css_link") + getURLProperty(name));
			ht_error.put("link_value_parse_error", lnk + getURLProperty(name));
			ht_error.put("link_name_parse_error", name);
		}
		if ((error.getException() != null) && (error.getMessage() != null)) {
			if (error.isParseException()) {
				ret = queryReplace(error.getMessage());
			} else {
				Exception ex = error.getException();
				if (ex instanceof NumberFormatException) {
					ret = ac.getMsg().getGeneratorString("invalid-number");
				} else {
					ret = queryReplace(ex.getMessage());
				}
			}
			if (error.getSkippedString() != null) {
				ht_error.put("span_class_parse_error", "skippedString");
				ht_error.put("span_value_parse_error", queryReplace(error.getSkippedString()));
			} else if (error.getExp() != null) {
				ret += " : " + queryReplace(error.getExp().toStringFromStart());
				ht_error.put("span_class_parse_error", "exp");
				ht_error.put("span_value_parse_error", queryReplace(error.getExp().toString()));
			}
		} else {
			ret = ac.getMsg().getGeneratorString("unrecognize");
			ht_error.put("span_class_parse_error", "unrecognized");
			ht_error.put("span_value_parse_error", queryReplace(error.getSkippedString()));
		}
		ht_error.put("ErrorMsg", ret);
	}

	/**
	 * 
	 * @param s, the string to convert
	 * @return the string s with html character escaped
	 */
	private String queryReplace(String s) {
		if (s != null) {
		    int len = s.length();
		    StringBuffer ret = new StringBuffer(len);
		    char c;

		    for (int i = 0; i < len; i++) {
		    	switch (c = s.charAt(i)) {
			    	case '&'  : ret.append("&amp;"); break;
			    	case '\'' : ret.append("&apos;"); break;
			    	case '"'  : ret.append("&quot;"); break;
			    	case '<'  : ret.append("&lt;"); break;
			    	case '>'  : ret.append("&gt;"); break;
			    	default   : ret.append(c);
		    	}
		    }
		    return ret.toString();
		}
		return "[empty string]";
	}

	/**
	 * Display the output 
	 */
	public void print(PrintWriter out) {
		this.out = out;
		try {
			template.merge(context, out);
		} catch (Exception e) {
			new ErrorReportHTML(ac, title, "", e).print(out);
		}
		out.flush();
	}

	/**
	 * The user doesn't want to see the error report when this function is called
	 */
	public void desactivateError() {
		context.put("no_errors_report", new Boolean(true)); // activate the no errors report
	}

}
