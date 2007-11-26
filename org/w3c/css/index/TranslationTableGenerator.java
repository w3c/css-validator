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
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Messages;
import org.w3c.css.util.Utf8Properties;


/**
 * @author olivier Thereaux <ot@w3.org> for W3C
 * 
 * 
 */
public class TranslationTableGenerator {

	// the velocity context used to generate the index
	// (NB: the same context is used for each index page, changing every thing
	// inside)
	private static String html_files_path = "../../../../";
	private static boolean done = false;
	public static VelocityContext vc = new VelocityContext();
	private static String template_name = "translations.vm";

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
		
		String default_lang = "en", name, path, translations_table;
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
            translations_table = "<table id=\"translation_summary\"><tr><th scope='col' id=\"properties\">Property</th>";
    		for (int i = 0; i < Messages.languages_name.size(); ++i) {
    			name = String.valueOf(Messages.languages_name.get(i));
    			HashMap l = new HashMap();
    			l.put("name", name);
    			l.put("real", ((Utf8Properties) Messages.languages.get(name)).getProperty("language_name"));
    			languages[i] = l;
    			ApplContext ac_translated = new ApplContext(name);
    			translations.put(name,ac_translated);
    			translations_table = translations_table + "<th scope=\"col\">"+l.get("real")+"</th>";
    		}
    		vc.put("languages", languages);
            Iterator properties_iterator = ac_default.getMsg().properties.keySet().iterator();
    		translations_table = translations_table + "</tr>";
    		while (properties_iterator.hasNext()) {
    		    String property_name = String.valueOf(properties_iterator.next());
    			translations_table = translations_table + "<tr><th scope=\"row\">"+property_name;
    			translations_table = translations_table+"<p>"+StringEscapeUtils.escapeHtml(ac_default.getMsg().getString(property_name))+"</p></th>";
    			for (int i = 0; i < Messages.languages_name.size(); ++i) {
    			    HashMap language = languages[i];
    			    ApplContext translation = (ApplContext) translations.get(language.get("name"));
    			    String property_translated = translation.getMsg().getString(property_name);
    			    if (language.get("name").equals(default_lang)) {
    			        vc.put(property_name, property_translated);
    			    }
    			    if (property_translated == null) {
        			    translations_table = translations_table + "<td class=\"table_translation_missing\">✘</td>";    			        
    			    }
    			    else if ( property_translated.matches(".*translation unavailable.*")){
        			    translations_table = translations_table + "<td class=\"table_translation_missing\"><span title=\""+StringEscapeUtils.escapeHtml(property_translated)+"\">✘</span></td>";
    			    }
    			    else {
    			        translations_table = translations_table + "<td class=\"table_translation_ok\"><span title=\""+StringEscapeUtils.escapeHtml(property_translated)+"\">✔</span></td>";
    			    }
                }
                translations_table = translations_table + "</tr>";
    		}
            translations_table = translations_table + "</table>";
    	    vc.put("translations_table", translations_table);
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
