//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// Copyright(c) 1995-2000, World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import java.net.URL;

import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.Utf8Properties;

public class Css3Properties {

    public static Utf8Properties properties;

    public static String getString(CssProperty property, String prop) {
	return properties.getProperty(property.getPropertyName() + "." + prop);
    }

    public static boolean getInheritance(CssProperty property) {
	return getString(property, "inherited").equals("true");
    }

    static {
	properties = new Utf8Properties();
	try {
	    URL url = Css3Properties.class
	    .getResource("CSS3Default.properties");
	    properties.load(url.openStream());
	} catch (Exception e) {
	    System.err
	    .println("org.w3c.css.properties3.Css3Properties: couldn't load properties ");
	    System.err.println("  " + e.toString());
	}
    }
}
