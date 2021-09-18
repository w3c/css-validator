/**
 * This class is used to generates the index pages
 * of the CSS validator in all the availables languages
 * it'll create the outputs validator.en.html, validator.fr.html, ...
 * according to the template file validator.vm
 */
package org.w3c.css.index;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Messages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.Vector;


/**
 * @author olivier Thereaux <ot@w3.org> for W3C
 */
class AlphaComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }
}

public class TranslationTableGenerator {

    // the velocity context used to generate the index
    // (NB: the same context is used for each index page, changing every thing
    // inside)
    public static VelocityContext vc = new VelocityContext();
    private static String html_files_path = "../../../../";
    private static boolean done = false;
    private static String template_name = "translations.vm";


    /**
     * @param args
     */
    public static void main(String[] args) {
        TranslationTableGenerator.generateTable();
    }

    /**
     * This method generates the index in every possible language we have the translation
     *
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
            //         Velocity.setProperty(Velocity.RUNTIME_LOG,
            //                "velocity-" + new SimpleDateFormat("yyyy-MM-dd_HHmm").format(new Date()) + ".log");
            //
            // Velocity.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.AvalonLogChute");
            Velocity.init();

            Template tpl = Velocity.getTemplate(template_name, "UTF-8");
            out_file = new File(path + html_files_path + "translations.html");

            ApplContext ac_default = new ApplContext(default_lang);
            // Getting the differents languages informations (for the lang choice)
            ArrayList<HashMap<String, String>> languages;
            languages = new ArrayList<HashMap<String, String>>(Messages.languages_name.size());
            HashMap<String, ApplContext> translations = new HashMap<String, ApplContext>();
            HashMap<String, Integer> translation_completeness = new HashMap<String, Integer>();
            table_head.append("<tr><th scope='col'>Property</th>");

            for (int i = 0; i < Messages.languages_name.size(); ++i) {
                name = String.valueOf(Messages.languages_name.get(i));
                HashMap<String, String> l = new HashMap<String, String>();
                l.put("name", name);
                l.put("real", (Messages.languages.get(name)).getProperty("language_name"));
                languages.add(i, l);
                ApplContext ac_translated = new ApplContext(name);
                if (!name.equals(default_lang)) {
                    Locale locale;
                    String engName;
                    int hy = name.indexOf("-");
                    if (hy > 0) {
                        String m = name.substring(0, hy);
                        String s = name.substring(hy + 1);
                        hy = s.indexOf("-");
                        if (hy > 0) {
                            String v = s.substring(hy + 1);
                            s = s.substring(0, hy);
                            locale = new Locale(m, s, v);
                        } else {
                            locale = new Locale(m, s, "");
                        }
                    } else {
                        locale = new Locale(name);
                    }
                    engName = locale.getDisplayName(Locale.US);
                    table_head.append("<th title=\"").append(engName);
                    table_head.append("\">");
                    table_head.append(l.get("real")).append("</th>");
                    l.put("engname", engName);
                }
                translations.put(name, ac_translated);
                translation_completeness.put(name, 0);

            }

            table_head.append("</tr>");
            Vector<String> sorted_properties_keys;
            Set properties_keyset = ac_default.getMsg().properties.keySet();
            sorted_properties_keys = new Vector<String>(properties_keyset);
            Collections.sort(sorted_properties_keys, new AlphaComparator());
            Iterator<String> properties_iterator = sorted_properties_keys.iterator();
            translations_table.append("<tbody>");
            int num_properties = 0;
            while (properties_iterator.hasNext()) {
                ++num_properties;
                String property_name = String.valueOf(properties_iterator.next());
                translations_table.append("<tr><th scope=\"row\" class=\"property_name\">").append(property_name);
                translations_table.append("<p>").append(StringEscapeUtils.escapeHtml4(ac_default.getMsg().getString(property_name)));
                translations_table.append("</p></th>");
                for (int i = 0; i < Messages.languages_name.size(); ++i) {
                    HashMap<String, String> language = languages.get(i);
                    ApplContext translation = translations.get(language.get("name"));
                    String property_translated = translation.getMsg().getStringStrict(property_name);
                    if (language.get("name").equals(default_lang)) {
                        vc.put(property_name, property_translated);
                    } else {
                        URI mail_translation = new URI("mailto", "w3c-translators@w3.org?Subject=[" + property_name + "] CSS Validator Translation&body=Property:\n  " + property_name + "\n\nText in English:\n  " + ac_default.getMsg().getString(property_name) + "\n\nLanguage:\n  " + language.get("name") + "\n\nTranslation:\n\n\n-- Help translate the CSS Validator:\nhttps://jigsaw.w3.org/css-validator/translations.html", "");
                        if (property_translated == null) {
                            translations_table.append("<td class=\"table_translation_missing\"><a title=\"submit a missing translation\" href=\"").append(StringEscapeUtils.escapeHtml4(mail_translation.toASCIIString())).append("\">✘</a></td>\n");
                        } else if (property_translated.matches(".*translation unavailable.*")) {
                            translations_table.append("<td class=\"table_translation_missing\"><a title=\"submit a missing translation\" href=\"").append(StringEscapeUtils.escapeHtml4(mail_translation.toASCIIString())).append("\">✘</a></td>\n");
                        } else {
                            translations_table.append("<td class=\"table_translation_ok\"><span title=\"").append(StringEscapeUtils.escapeHtml4(property_translated)).append("\">✔</span></td>\n");
                            int completed = Integer.parseInt(translation_completeness.get(language.get("name")).toString());
                            completed++;
                            translation_completeness.put(language.get("name"), completed);
                            //System.out.println(language.get("name")+": "+completed);
                        }
                    }
                }
                translations_table.append("</tr>");
                if (num_properties % 12 == 0) {
                    translations_table.append("</tbody><tbody>").append(table_head);
                }
            }
            translations_table.append("</tbody></table>");
            for (int i = 0; i < Messages.languages_name.size(); i++) {
                name = String.valueOf(Messages.languages_name.get(i));
                HashMap<String, String> l = languages.get(i);
                int completeness_percent = 100 * Integer.parseInt(translation_completeness.get(l.get("name")).toString());
                completeness_percent /= ac_default.getMsg().properties.size();
                l.put("completeness", Integer.toString(completeness_percent));
                // FIXME not needed // languages[i] = l;
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
