//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// Copyright(c) 1995-2000, World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties3;

import java.util.Properties;
import java.net.URL;

import org.w3c.css.properties.CssProperty;

public class Css3Properties {

   public static Properties properties;

   public static String getString(CssProperty property, String prop) {
      return properties.getProperty(property.getPropertyName()+"."+prop);
   }

   public static boolean getInheritance(CssProperty property) {
      return getString(property, "inherited").equals("true");
   }

   static {
      properties = new Properties();
      try {
        URL url = Css3Properties.class.getResource("CSS3Default.properties");
        properties.load(url.openStream());
      } 
      catch (Exception e) {
        System.err.println("org.w3c.css.properties3.Css3Properties: couldn't load properties ");
        System.err.println("  " + e.toString() );
      }
   }
}
