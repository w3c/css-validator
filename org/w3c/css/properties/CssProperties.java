//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties;

import java.util.Properties;
import java.net.URL;

/**
 * @version $Revision$
 */
public class CssProperties {
  public static Properties properties;

  public static String getString(CssProperty property, String prop) {
    StringBuffer st = new StringBuffer(property.getPropertyName());
    return properties.getProperty(st.append('.').append(prop).toString());
  }

  public static boolean getInheritance(CssProperty property) {
    return getString(property, "inherited").equals("true");
  }
  
  static {
    properties = new Properties();
    try {
      URL url = CssProperties.class.getResource("CSS1Default.properties");
      java.io.InputStream f = url.openStream();
      properties.load(f);
      f.close();
    } catch (Exception e) {
      System.err.println("org.w3c.css.properties.CssProperties: couldn't load properties ");
      System.err.println("  " + e.toString() );
    }
  }
}
