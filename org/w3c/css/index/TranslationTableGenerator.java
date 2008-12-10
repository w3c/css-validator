/**
 * This class is used to generates the index pages
 * of the CSS validator in all the availables languages
 * it'll create the outputs validator.en.html, validator.fr.html, ...
 * according to the template file validator.vm
 */
package org.w3c.css.index;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.commons.lang.StringEscapeUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.Collections;
import java.util.Comparator;
import java.lang.Integer;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Messages;
import org.w3c.css.util.Utf8Properties;


/**
 * @author olivier Thereaux <ot@w3.org> for W3C
 * 
 * 
 */
class AlphaComparator implements Comparator {
    public int compare(Object o1, Object o2) {
	String s1 = (String) o1;
	String s2 = (String) o2;
	//	return s1.toLowerCase().compareTo(s2.toLowerCase());
	return s1.compareToIgnoreCase(s2);
    }
}

public class TranslationTableGenerator {

    // the velocity context used to generate the index
    // (NB: the same context is used for each index page, changing every thing
    // inside)
    private static String html_files_path = "../../../../";
    private static boolean done = false;
    public static VelocityContext vc;
    private static String template_name = "translations.vm";
    
    static {
	vc = new VelocityContext();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	TranslationTableGenerator.generateTable();
    }

    /**
     * This method generates the index in every possible language we have the translation
     * @see org.w3c.css.util.Messages
     */
    public static synchronized void generateTable() {
		
	String default_lang = "en";
	String name, path;
	StringBuilder table_head = new StringBuilder();
	StringBuilder translations_table = new StringBuilder();
	File out_file;
	path = TranslationTableGenerator.class.getResource("").getPath();

        try {
	    path = new URI(path).getPath();
	    Velocity.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path);
	    Velocity.addProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path + "../css/");
	    Velocity.init();
		
	    Template tpl = Velocity.getTemplate(template_name, "UTF-8");
	    out_file = new File(path + html_files_path + "translations.html");
		
            ApplContext ac_default= new ApplContext(default_lang);
	    // Getting the differents languages informations (for the lang choice)
	    HashMap[] languages = new HashMap[Messages.languages_name.size()];
	    HashMap translations = new HashMap();
	    HashMap translation_completeness = new HashMap();
	    table_head.append("<tr><th scope='col'>Property</th>");
	    for (int i = 0; i < Messages.languages_name.size(); ++i) {
		name = String.valueOf(Messages.languages_name.get(i));
		HashMap l = new HashMap();
		l.put("name", name);
		l.put("real", ((Utf8Properties) Messages.languages.get(name)).getProperty("language_name"));
		languages[i] = l;
		ApplContext ac_translated = new ApplContext(name);
		if (!name.equals(default_lang)) {
		    table_head.append("<th>").append(l.get("real")).append("</th>");
                }
		translations.put(name,ac_translated);
		translation_completeness.put(name, 0);
		        
	    }
	    table_head.append("</tr>");
	    Vector sorted_properties_keys = new Vector(ac_default.getMsg().properties.keySet());
	    Collections.sort(sorted_properties_keys, new AlphaComparator());
            Iterator properties_iterator = sorted_properties_keys.iterator();
	    translations_table.append("<tbody>");
	    int num_properties = 0;
	    while (properties_iterator.hasNext()) {
		++num_properties;
		String property_name = String.valueOf(properties_iterator.next());
		translations_table.append("<tr><th scope=\"row\" class=\"property_name\">").append(property_name);
		translations_table.append("<p>").append(StringEscapeUtils.escapeHtml(ac_default.getMsg().getString(property_name)));
		translations_table.append("</p></th>");
		for (int i = 0; i < Messages.languages_name.size(); ++i) {
		    HashMap language = languages[i];
		    ApplContext translation = (ApplContext) translations.get(language.get("name"));
		    String property_translated = translation.getMsg().getString(property_name);
		    if (language.get("name").equals(default_lang)) {
			vc.put(property_name, property_translated);
		    } else {
			URI mail_translation = new URI("mailto", "w3c-translators@w3.org?Subject=["+property_name+"] CSS Validator Translation&body=Property:\n  "+property_name+"\n\nText in English:\n  "+ac_default.getMsg().getString(property_name)+"\n\nLanguage:\n  "+language.get("name")+"\n\nTranslation:\n\n\n-- Help translate the CSS Validator:\nhttp://qa-dev.w3.org:8001/css-validator/translations.html", "");
			if (property_translated == null) {
			    translations_table.append("<td class=\"table_translation_missing\"><a title=\"submit a missing translation\" href=\"").append(StringEscapeUtils.escapeHtml(mail_translation.toASCIIString())).append("\">✘</a></td>\n");    			        
			} else if ( property_translated.matches(".*translation unavailable.*")) {
			    translations_table.append("<td class=\"table_translation_missing\"><a title=\"submit a missing translation\" href=\"").append(StringEscapeUtils.escapeHtml(mail_translation.toASCIIString())).append("\">✘</a></td>\n");
			} else {
			    translations_table.append("<td class=\"table_translation_ok\"><span title=\"").append(StringEscapeUtils.escapeHtml(property_translated)).append("\">✔</span></td>\n");
			    int completed = Integer.parseInt(translation_completeness.get(language.get("name")).toString());
			    completed++;
			    translation_completeness.put(language.get("name"), completed);
			    //System.out.println(language.get("name")+": "+completed);
			}
		    }
                }
                translations_table.append("</tr>");
                if ( num_properties % 12 == 0) {
                    translations_table.append("</tbody><tbody>").append(table_head);
                }
            }
            translations_table.append("</tbody></table>");
            for (int i = 0; i < Messages.languages_name.size(); i++) {
		name = String.valueOf(Messages.languages_name.get(i));
		HashMap l = (HashMap) languages[i];
		int completeness_percent = 100*Integer.parseInt(translation_completeness.get(l.get("name")).toString());
		completeness_percent = completeness_percent /ac_default.getMsg().properties.size();
		l.put("completeness", completeness_percent);
		languages[i] = l;
	    }
	    vc.put("languages", languages);
	    vc.put("num_languages", Messages.languages_name.size());
	    vc.put("total_properties", ac_default.getMsg().properties.size());
    	    vc.put("translations_table", translations_table.toString());
    	    vc.put("lang", "en");
	    OutputStreamWriter aFileWriter = new OutputStreamWriter(new FileOutputStream(out_file), "UTF-8");
	    tpl.merge(vc, aFileWriter);
	    aFileWriter.close();
    	} catch (URISyntaxException e) {
	    e.printStackTrace();
    	} catch (ResourceNotFoundException e) {
	    e.printStackTrace();
	} catch (ParseErrorException e) {
	    e.printStackTrace();
	} catch (MethodInvocationException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    done = true;
	}
    }
}
