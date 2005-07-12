//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.parser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Vector;

import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Utf8Properties;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;

/**
 * @version $Revision$
 * @author Philippe Le H�garet
 */
public class CssPropertyFactory implements Cloneable {

    // all recognized properties are here.
	private Utf8Properties properties;

	private Utf8Properties allprops;

    private String usermedium;

    public CssPropertyFactory getClone() {
    try {
        return (CssPropertyFactory) clone();
    } catch (CloneNotSupportedException ex) {
        ex.printStackTrace();
        return null;
    }
    }

    /**
     * Create a new CssPropertyFactory
     */
    public CssPropertyFactory(URL url, URL allprop_url) {
		properties = new Utf8Properties();
    InputStream f = null;
    try {
        f = url.openStream();
        properties.load(f);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
        if (f != null)
            f.close();
        } catch (Exception e) {
        e.printStackTrace();
        } // ignore
    }

		// list of all properties
		allprops = new Utf8Properties();
    InputStream f_all = null;
    try {
        f_all = allprop_url.openStream();
        allprops.load(f_all);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
        if (f_all != null)
            f_all.close();
        } catch (Exception e) {
        e.printStackTrace();
        } // ignore
    }
    }

    public String getProperty(String name) {
    return properties.getProperty(name);
    }

    private Vector getVector(String media) {
    Vector list = new Vector(4);
    String medium = new String();
    StringTokenizer tok = new StringTokenizer(media, ",");

    while (tok.hasMoreTokens()) {
        medium = tok.nextToken();
        medium = medium.trim();
        list.addElement(medium);
    }

    return list;
    }

    public void setUserMedium(String usermedium) {
    this.usermedium = usermedium;
    }

	public synchronized CssProperty createMediaFeature(ApplContext ac,
			AtRule atRule, String property, CssExpression expression)
    throws Exception {

    String result = "ok";
    String media = atRule.toString();
    int pos = -1;
    int pos2 = media.toUpperCase().indexOf("AND");

    if (pos2 == -1) {
        pos2 = media.length();
    }

    if (media.toUpperCase().indexOf("NOT") != -1) {
        pos = media.toUpperCase().indexOf("NOT");
        media = media.substring(pos + 4, pos2);
    } else if (media.toUpperCase().indexOf("ONLY") != -1) {
        pos = media.toUpperCase().indexOf("ONLY");
        media = media.substring(pos + 4, pos2);
    } else {
        pos = media.indexOf(" ");
        media = media.substring(pos + 1, pos2);
    }

    media = media.trim();

    String classname = properties.getProperty("mediafeature" + "."
                          + property);

		if (classname == null) {
        if (atRule instanceof AtRuleMedia && (!media.equals("all"))) {
                // I don't know this property
				// throw new InvalidParamException("noexistence-media",
        //              property,
        //              media, ac);
            ac.getFrame().addWarning("noexistence-media", property);
            classname = allprops.getProperty(property);
        } else {
        // I don't know this property
				throw new InvalidParamException("noexistence", property, media,
						ac);
        }
    }

    try {
        // create an instance of your property class
        Class expressionclass = new CssExpression().getClass();
        if (expression != null) {
        expressionclass = expression.getClass();
        }
        Class[] parametersType = { ac.getClass(), expressionclass };
			Constructor constructor = Class.forName(classname).getConstructor(
					parametersType);
        Object[] parameters = { ac, expression };
        // invoke the constructor
        return (CssProperty) constructor.newInstance(parameters);
    } catch (InvocationTargetException e) {
        // catch InvalidParamException
        InvocationTargetException iv = e;
        Exception ex = (Exception) iv.getTargetException();
        throw ex;
    }

    }

    public synchronized CssProperty createProperty(ApplContext ac,
			AtRule atRule, String property, CssExpression expression)
    throws Exception {

    String result = "ok";
    String classname;
    String media = atRule.toString();
    int pos = -1;
    String upperMedia = media.toUpperCase();
    int pos2 = upperMedia.indexOf("AND ");

    if (pos2 == -1) {
        pos2 = media.length();
    }

    if ((pos = upperMedia.indexOf("NOT")) != -1) {
        media = media.substring(pos + 4, pos2);
    } else if ((pos = upperMedia.indexOf("ONLY")) != -1) {
        media = media.substring(pos + 4, pos2);
    } else {
        pos = media.indexOf(' ');
        media = media.substring(pos + 1, pos2);
    }

    media = media.trim();

    Vector list = new Vector(getVector(media));

    if (atRule instanceof AtRuleMedia) {
        if (media.equals("all")) {
        classname = properties.getProperty(property);
        } else {
        for (int i = 0; i < list.size() - 1; i++) {
					String medium = (String) list.elementAt(i);
					String name = properties.getProperty(medium + "."
							+ property);
            if (name == null) {
            result = medium;
            }
        }

        if (result.equals("ok")) {
					classname = properties.getProperty((String) list
							.firstElement()
							+ "." + property);
				} else {
            classname = null;
        }
        }
    } else {
			classname = properties.getProperty("@" + atRule.keyword() + "."
					+ property);
    }

    CssProperty prop = null;

		// System.out.println(allprops.getProperty(property));

    if (classname == null && usermedium != null) {
        if (atRule instanceof AtRuleMedia) {
        // I don't know this property
        if (!media.equals("all"))
            ac.getFrame().addWarning("notforusermedium", property);
        classname = properties.getProperty(property);
			} else {
				// NEW
        if (allprops.getProperty(property) == null) {
            // I don't know this property
            throw new InvalidParamException("noexistence", property,
                            media, ac);
        } else {
            ac.getFrame().addWarning("otherprofile", property);
            classname = allprops.getProperty(property);
        }
        }
		} else if (classname == null) { // && CssFouffa.usermedium == null)
        if (atRule instanceof AtRuleMedia && (!media.equals("all"))) {
            // I don't know this property
				/*
				 * throw new InvalidParamException("noexistence-media",
				 * property, media, ac);
				 */
            ac.getFrame().addWarning("noexistence-media", property);
            classname = allprops.getProperty(property);

        } else {
            // I don't know this property
				// NEW
            if (allprops.getProperty(property) == null) {
            // I don't know this property
					throw new InvalidParamException("noexistence", property,
                            media, ac);
            } else {
            ac.getFrame().addWarning("otherprofile", property);
            classname = allprops.getProperty(property);
            }
        }
        }

    CssIdent initial = new CssIdent("initial");

		if (expression.getValue().equals(initial)
				&& ac.getCssVersion().equals("css3")) {
        try {
        // create an instance of your property class
				Class[] parametersType = {};
				Constructor constructor = Class.forName(classname)
						.getConstructor(parametersType);
				Object[] parameters = {};
        // invoke the constructor
        return (CssProperty) constructor.newInstance(parameters);
        } catch (InvocationTargetException e) {
        // catch InvalidParamException
        InvocationTargetException iv = e;
        Exception ex = (Exception) iv.getTargetException();
        throw ex;
        }
    } else {

        try {
        // create an instance of your property class
				Class[] parametersType = { ac.getClass(), expression.getClass() };
				Constructor constructor = Class.forName(classname)
						.getConstructor(parametersType);
        Object[] parameters = { ac, expression };
        // invoke the constructor
        return (CssProperty) constructor.newInstance(parameters);
        } catch (InvocationTargetException e) {
        // catch InvalidParamException
        InvocationTargetException iv = e;
        Exception ex = (Exception) iv.getTargetException();
        throw ex;
        }
    }
    }
}
