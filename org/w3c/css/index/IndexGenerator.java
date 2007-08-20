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
import java.util.HashMap;
import java.util.Iterator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.w3c.css.css.StyleSheetGenerator;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Messages;
import org.w3c.css.util.Utf8Properties;

/**
 * @author julien
 * 
 */
public class IndexGenerator {

	// the velocity context used to generate the index
	// (NB: the same context is used for each index page, changing every thing
	// inside)
	public static VelocityContext vc = new VelocityContext();
	private static String template_name = "validator.vm";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IndexGenerator.generatesIndex(false);
	}

	/**
	 * This method generates the index in every possible language we have the translation
	 * @see org.w3c.css.util.Messages
	 * @param servlet, if this method is called from the servlet,
	 * the path is a bit different and need to be changed.
	 */
	public static void generatesIndex(boolean servlet) {
		String default_lang = "en", k, name, path;
		ApplContext ac_default = new ApplContext(default_lang);
		File tmpFile;
		Iterator it;

		// Getting the differents languages informations (for the lang choice)
		HashMap[] languages = new HashMap[Messages.languages_name.size()];
		for (int i = 0; i < Messages.languages_name.size(); ++i) {
			name = String.valueOf(Messages.languages_name.get(i));
			HashMap l = new HashMap();
			l.put("name", name);
			l.put("real", ((Utf8Properties) Messages.languages.get(name)).getProperty("language_name"));
			languages[i] = l;
		}
		// Adding the result to the context
		vc.put("languages", languages);

		try {
			//setting the path were to find the template
			path = StyleSheetGenerator.class.getResource("").toString();
			if (servlet)
				path = path.replace("file://localhost", "");
			else
				path = new URI(path).getPath();
			Velocity.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path);
			Velocity.init();
			if (!new File(path + template_name).exists())
				template_name = "org/w3c/css/css/" + template_name;
			Template tpl = Velocity.getTemplate(template_name, "UTF-8");
			int count = 0;

			// For each language, we set the context are create the template
			for (int i = 0; i < Messages.languages_name.size(); ++i) {
				name = String.valueOf(Messages.languages_name.get(i));
				tmpFile = new File(path + "../../../../validator.html." + name);

				// Checking if the index files exists
				// and if they have been created after the last template modification
				if ((tmpFile.lastModified() < tpl.getLastModified()) || !tmpFile.exists()) {
					ApplContext ac = new ApplContext(name);
					vc.put("lang", name);

					if (ac.getLang().equals(default_lang)) {
						it = ac_default.getMsg().properties.keySet().iterator();
						while (it.hasNext()) {
							k = String.valueOf(it.next());
							vc.put(k, ac.getMsg().getString(k));
						}
					} else {
						it = ac_default.getMsg().properties.keySet().iterator();
						while (it.hasNext()) {
							k = String.valueOf(it.next());
							if (ac.getMsg().getString(k) == null)
								vc.put(k, ac_default.getMsg().getString(k));
							else
								vc.put(k, ac.getMsg().getString(k));
						}
					}
					OutputStreamWriter aFileWriter = new OutputStreamWriter(new FileOutputStream(tmpFile), "UTF-8");
					tpl.merge(vc, aFileWriter);
					aFileWriter.close();
					++count;
				}
			}
			System.err.println("INFO: IndexGenerator : " + count + " index file(s) created or modified");
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
