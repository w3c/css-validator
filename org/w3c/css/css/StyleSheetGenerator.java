package org.w3c.css.css;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.generic.EscapeTool;
import org.w3c.css.error.ErrorReportHTML;
import org.w3c.css.parser.CssError;
import org.w3c.css.parser.CssErrorToken;
import org.w3c.css.parser.CssParseException;
import org.w3c.css.parser.Errors;
import org.w3c.css.properties.PropertiesLoader;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.CssProfile;
import org.w3c.css.util.CssVersion;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Messages;
import org.w3c.css.util.Utf8Properties;
import org.w3c.css.util.Warnings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TimeZone;

/**
 * @author Julien Grand-Mourcel
 * @author Maria Kaceriakova
 * @date 2007-06-29
 * This class uses Velocity to output the validator results
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
    private static final Utf8Properties availableFormat;
    private static final Utf8Properties availablePropertiesURL;
    private static ToolManager velocityToolManager;

    static {
        availableFormat = new Utf8Properties();
        try {
            java.io.InputStream f;
            f = StyleSheetGenerator.class.getResourceAsStream(
                    "format.properties");
            availableFormat.load(f);
            f.close();
        } catch (Exception e) {
            System.err.println("org.w3c.css.css.StyleSheetGeneratorHTML: " +
                    "couldn't load format properties ");
            System.err.println("  " + e.toString());
        }

        availablePropertiesURL = new Utf8Properties();
        try {
            java.io.InputStream f;
            f = StyleSheetGenerator.class.getResourceAsStream(
                    "urls.properties");
            availablePropertiesURL.load(f);
            f.close();
        } catch (Exception e) {
            System.err.println("org.w3c.css.css.StyleSheetGeneratorHTML: " +
                    "couldn't load URLs properties ");
            System.err.println("  " + e.toString());
        }

        try {
            Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            Velocity.setProperty("classpath.resource.loader.class",
                    ClasspathResourceLoader.class.getName());
            Velocity.init();
            velocityToolManager = new ToolManager();
        } catch (Exception e) {
            System.err.println("Failed to initialize Velocity. " +
                    "Validator might not work as expected.");
        }
    }

    public StyleSheetGenerator(String title, StyleSheet style, String document,
                               int warningLevel) {
        this(null, title, style, document, warningLevel);
    }

    public StyleSheetGenerator(ApplContext ac, String title, StyleSheet style,

                               String document, int warningLevel) {
        ArrayList<CssRuleList> items;

        this.ac = ac;
        this.style = style;
        this.title = title;
        this.template_file = availableFormat.getProperty(document);

        context = new VelocityContext(velocityToolManager.createContext());
        context.put("esc", new EscapeTool());
        // add a static ref for templates that needs to do some escaping
        context.put("Messages", Messages.class);
        // adjust the source name if needed
        if (ac.isInputFake()) {
            title = title.substring(title.lastIndexOf('/') + 1);
        }
        context.put("file_title", queryReplace(title));

        // W3C_validator_result
        warnings = style.getWarnings();
        errors = style.getErrors();
        items = style.newGetRules();

        // Setting all the variables of the velocity context
        ApplContext ac_default = new ApplContext(default_lang);
        String k, v;
        if (ac.getLang() == null || ac.getLang().equals(default_lang)) {
            for (Object s : ac_default.getMsg().properties.keySet()) {
                k = String.valueOf(s);
                context.put(k, ac.getMsg().getString(k));
            }
        } else {
            for (Object s : ac_default.getMsg().properties.keySet()) {
                k = String.valueOf(s);
                v = ac.getMsg().getString(k);
                context.put(k, (v == null) ? ac_default.getMsg().getString(k) : v);
            }
        }

        if (ac.getLink() != null) {
            int arraysize = Messages.languages_name.size();
            ArrayList<HashMap<String, String>> languages;
            languages = new ArrayList<HashMap<String, String>>(arraysize);
            String name;
            for (int i = 0; i < arraysize; ++i) {
                name = String.valueOf(Messages.languages_name.get(i));
                HashMap<String, String> l = new HashMap<String, String>();
                l.put("name", name);
                l.put("real", ((Utf8Properties) Messages.languages.get(name)).getProperty("language_name"));
                languages.add(i, l);
            }
            context.put("languages", languages.toArray());
            String link = ac.getLink().replaceAll("&lang=.*&", "&");
            link = link.replaceAll("&lang=.*$", "");
            context.put("link", "?" + link.replaceAll("&", "&amp;"));
        }

        // generated values
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date now = new Date();
        context.put("currentdate", formatter.format(now));
        context.put("currentdatelong", now.getTime());
        context.put("lang", ac.getContentLanguage());
        context.put("errors_count", Integer.valueOf(errors.getErrorCount()));
        context.put("warnings_count", Integer.valueOf(warnings.getWarningCount()));
        context.put("ignored-warnings_count", Integer.valueOf(warnings.getIgnoredWarningCount()));
        context.put("warning_level", Integer.valueOf(warningLevel));
        context.put("rules_count", Integer.valueOf(items.size()));
        context.put("no_errors_report", Boolean.FALSE);
        context.put("charset", ac.getContentEncoding());
        context.put("cssversion", ac.getCssVersionString());
        context.put("css_profile", ac.getProfileString());
        // we are currently not using profile in the templates
        // so we compute here the version + profile in the $css variable.
        // TODO evaluate which is better.
        if (ac.getCssProfile() == CssProfile.EMPTY) {
            context.put("css", ac.getMsg().getString(ac.getCssVersionString()));
        } else {
            context.put("css", ac.getMsg().getString(ac.getCssVersionString()) + " + " +
                    ac.getMsg().getString(ac.getCssProfile().toString()));
        }
        context.put("css_link", getURLProperty("@url-base_" + ac.getCssVersionString()));
        context.put("is_valid", (errors.getErrorCount() == 0) ? "true" : "false");
        context.put("fake_input", Boolean.valueOf(ac.isInputFake()));
        context.put("author", "www-validator-css");
        context.put("author-email", "Email.html");
        if (style.charset != null)
            context.put("style_charset", style.charset);

        produceError();
        produceWarning();
        produceStyleSheet();

        try {
            String _template_dir = "org/w3c/css/css/";
            template = Velocity.getTemplate(_template_dir + template_file);
            template.setEncoding("utf-8");
        } catch (ResourceNotFoundException rnfe) {
            System.err.println(rnfe.getMessage());
            rnfe.printStackTrace();
        } catch (ParseErrorException pee) {
            System.err.println(pee.getMessage());
            pee.printStackTrace();
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

    public static String printAvailableFormatList(String def) {
        Enumeration<?> e = availableFormat.propertyNames();
        StringBuilder sb = new StringBuilder();
        ArrayList<String> t = (ArrayList<String>) Collections.list(e);
        Collections.sort(t);
        boolean isFirst = true;
        for (String s : t) {
            if (!isFirst) {
                sb.append(", ");
            }
            isFirst = false;
            sb.append(s);
            if (s.equals(def)) {
                sb.append(" (default)");
            }
        }
        return sb.toString();
    }


    /**
     * Test if <tt>document</tt> is an available output
     *
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
                CssError[] error = errors.getErrors();
                int nbError = error.length;
                for (int i = 0; i < nbError; i++) {
                    CssError csserror = error[i];
                    Throwable ex = csserror.getException();
                    Hashtable<String, Object> h = new Hashtable<String, Object>();
                    errors_content[i] = h;
                    h.put("Error", csserror);
                    h.put("CtxName", "nocontext");
                    h.put("CtxMsg", "");
                    h.put("ErrorMsg", ((ex.getMessage() == null) ? "" :
                            ex.getMessage()));
                    h.put("ClassName", "unkownerror");
                    h.put("Type", csserror.getType());
                    if (ex instanceof FileNotFoundException) {
                        h.put("ClassName", "notfound");
                        h.put("ErrorMsg",
                                ac.getMsg().getGeneratorString("not-found") +
                                        ": " + ex.getMessage());
                    } else if (ex instanceof CssParseException) {
                        produceParseException((CssParseException) ex, h);
                    } else if (ex instanceof InvalidParamException) {
                        h.put("ClassName", "invalidparam");
                        h.put("ErrorMsg", queryReplace((String) h.get("ErrorMsg")));
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
                        h.put("CtxMsg", stringError);// .substring(0,
                        // index));
                        h.put("ClassName", "io");

                    } else if (csserror instanceof CssErrorToken) {
                        CssErrorToken terror = (CssErrorToken) csserror;
                        h.put("ClassName", "errortoken");
                        String str = ac.getMsg().getErrorString("errortoken");
                        if (str == null) {
                            str = "Parse Error %s";
                        }
                        String[] params = new String[4];
                        params[0] = terror.getErrorToken();
                        params[1] = Integer.toString(terror.getLine());
                        StringBuilder sb = new StringBuilder();
                        boolean notfirst = false;
                        for (String t : terror.getExpected()) {
                            if (notfirst) {
                                sb.append(", ");
                            }
                            sb.append(t);
                        }
                        params[2] = sb.toString();
                        params[3] = terror.getSkippedString();
                        // replace all parameters
                        String[] msg_parts = str.split("%s", -1);
                        int j = 0;
                        sb.setLength(0);
                        sb.append(msg_parts[0]);
                        for (int k = 1; k < msg_parts.length; k++) {
                            if (j < params.length) {
                                sb.append(params[j++]);
                            }
                            sb.append(msg_parts[k]);
                        }
                        h.put("ErrorMsg", queryReplace(sb.toString()));
                    } else {
                        h.put("ClassName", "unkownerror");
                        h.put("ErrorMsg", ac.getMsg().getErrorString("unknown")
                                + " " + ex);
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
     *
     * @param error,    the error to check
     * @param ht_error, the Hastable with information about this error
     */
    private void produceParseException(CssParseException error,
                                       Hashtable<String, Object> ht_error) {
        if (error.getContexts() != null && error.getContexts().size() != 0) {
            ht_error.put("CtxName", "codeContext");
            StringBuilder buf = new StringBuilder();
            // Loop on the list of contexts for errors
            Enumeration e;
            Iterator li = error.getContexts().iterator();
            while (li.hasNext()) {
                Object t = li.next();
                // if the list is not null, add a comma
                if (t != null) {
                    buf.append(t);
                    if (li.hasNext()) {
                        buf.append(", ");
                    }
                }
            }
            if (buf.length() != 0) {
                ht_error.put("CtxMsg", String.valueOf(buf));
            }
        } else {
            ht_error.put("CtxName", "nocontext");
        }
        ht_error.put("ClassName", "parse-error");
        String name = error.getProperty();
        String ret;
        if ((name != null) && (getURLProperty(name) != null) &&
                PropertiesLoader.getProfile(ac.getPropertyKey()).containsKey(name)) {
            //we add a link information
            // we check if the property doesn't exist in this css version
            ht_error.put("link_before_parse_error",
                    ac.getMsg().getGeneratorString("property"));
            // Since CSS3 is only a working draft, the links don't exist yet
            // in CSS3...
            // And this is the same with CSS1 because the links are not working
            // the same way...
            // This can be removed as soon as the CSS3 specifications are made
            // and CSS1 use the links
            // and the link is changed in urls.properties
            String lnk;
            CssVersion v = ac.getCssVersion();
            if (v == CssVersion.CSS3) {
                lnk = getURLProperty("@url-base_css2.1");
            } else if (v == CssVersion.CSS1) {
                lnk = getURLProperty("@url-base_css2");
            } else {
                Object _lnk = context.get("cs_link");
                lnk = (_lnk == null) ? "[error]" : _lnk.toString();
            }
            // this would be replaced by :
            // ht_error.put("link_value_parse_error",
            //              context.get("css_link") + getURLProperty(name));
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
                ht_error.put("span_value_parse_error",
                        queryReplace(error.getSkippedString()));
            } else if (error.getExp() != null) {
                ret += " : ";// + queryReplace(error.getExp().toStringFromStart());
                ht_error.put("span_class_parse_error", "exp");
                ht_error.put("span_value_parse_error",
                        queryReplace(error.getExp().toString()));
            }
        } else {
            ret = ac.getMsg().getGeneratorString("unrecognize");
            ht_error.put("span_class_parse_error", "unrecognized");
            if (error.getSkippedString() != null) {
                ht_error.put("span_value_parse_error",
                        queryReplace(error.getSkippedString()));
            } else if (error.getExp() != null) {
                ht_error.put("span_value_parse_error",
                        queryReplace(error.getExp().toStringFromStart()));
            } else {
                // nothing useful to add
            }
        }
        ht_error.put("ErrorMsg", ret);
    }

    /**
     * @param s, the string to convert
     * @return the string s with html character escaped
     */
    private String queryReplace(String s) {
        if (!"xhtml.properties".equals(this.template_file)) {
            return s;
        }
        if (s != null) {
            int len = s.length();
            StringBuilder ret = new StringBuilder(len + 16);
            char c;

            for (int i = 0; i < len; i++) {
                switch (c = s.charAt(i)) {
                    case '&':
                        ret.append("&amp;");
                        break;
                    case '\'':
                        ret.append("&#39;");
                        break;
                    case '"':
                        ret.append("&quot;");
                        break;
                    case '<':
                        ret.append("&lt;");
                        break;
                    case '>':
                        ret.append("&gt;");
                        break;
                    case '\u201C':
                        ret.append("<code>");
                        break;
                    case '\u201D':
                        ret.append("</code>");
                        break;
                    default:
                        ret.append(c);
                }
            }
            return ret.toString();
        }
        return "[empty string]";
    }

    /**
     * Display the output
     *
     * @require VelocityContext to be set and init()
     */
    public void print(PrintWriter out) {
        this.out = out;
        try {
            // Velocity context must have been setup in IndexGenerator
            template.merge(context, out);
        } catch (Exception e) {
            new ErrorReportHTML(ac, title, "", e).print(out);
        }
        out.flush();
    }

    /**
     * The user doesn't want to see the error report when this function
     * is called
     */
    public void desactivateError() {
        context.put("no_errors_report", Boolean.TRUE);
        // activate the no errors report
    }

}
