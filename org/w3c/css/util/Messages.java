//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.util;

import java.net.URL;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * @version $Revision$
 */
public class Messages {

    /**
     * Message properties
     */
    public Utf8Properties properties;

    private static Hashtable languages = new Hashtable();

    /**
     * Creates a new Messages
     */
    public Messages(String lang) {
	if (lang != null) {
	    StringTokenizer lanTok = new StringTokenizer(lang, ",");
	    int maxTok = lanTok.countTokens();

	    String slang[] = new String[maxTok];
	    float  qlang[] = new float[maxTok];

	    // quick and dirty, it would be better to use Jigsaw's classes
	    while (lanTok.hasMoreTokens()) {
		String l = lanTok.nextToken().trim().toLowerCase();
		int qualsep = l.indexOf(';');
		float qval = 1;
		if (qualsep != -1) {
		    String p = l.substring(qualsep+1);
		    l = l.substring(0, qualsep);
		    if (p.startsWith("q=")) {
			qval = Float.parseFloat(p.substring(2));
		    }
		}
		for (int i=0 ; i<maxTok; i++) {
		    if (slang[i] == null) {
			slang[i] = l;
			qlang[i] = qval;
			break;
		    } else if (qval > qlang[i]) {
			System.arraycopy(slang, i, slang, i+1, (maxTok-i-1));
			System.arraycopy(qlang, i, qlang, i+1, (maxTok-i-1));
			slang[i] = l;
			qlang[i] = qval;
			break;
		    }

		}
	    }
	    for (int i=0 ; i< maxTok; i++) {
		String l = slang[i];
		properties = (Utf8Properties) languages.get(l);
		if (properties != null) {
		    break;
		}
		int minusIndex = l.indexOf('-');
		if (minusIndex != -1) {
		    // suppressed -cn in zh-cn (example)
		    l = l.substring(0, minusIndex);
		    properties = (Utf8Properties) languages.get(l);
		}
		if (properties != null) {
		    break;
		}
	    }
	}
	if (properties == null) {
	    properties = (Utf8Properties) languages.get("en");
	}
    }

    /**
     * Get a property.
     */
    public String getString(String message) {
	return properties.getProperty(message);
    }

    /**
     * Get a warning property.
     *
     * @param message
     *            the warning property.
     */
    public String getWarningString(String message) {
	return getString(new StringBuffer("warning.").append(message)
			                                          .toString());
    }

    /**
     * Get a warning level property.
     *
     * @param message
     *            the warning property.
     */
    public String getWarningLevelString(String message) {
	return getString(new StringBuffer("warning.").append(message).append(
	                                                 ".level").toString());
    }

    /**
     * Get an error property.
     *
     * @param message
     *            the error property.
     */
    public String getErrorString(String message) {
	return getString(new StringBuffer("error.").
			                           append(message).toString());
    }

    /**
     * Get an generator property.
     *
     * @param message
     *            the generator property.
     */
    public String getGeneratorString(String message) {
	return getString(new StringBuffer("generator.").append(message)
			 .toString());
    }

    /**
     * Get an generator property.
     *
     * @param message
     *            the generator property.
     */
    public String getGeneratorString(String message, String param) {
	String str = getString(new StringBuffer("generator.").append(message)
			       .toString());

	// replace all parameters
	int i = str.indexOf("%s");
	if (i >= 0) {
	    str = str.substring(0, i) + param + str.substring(i + 2);
	}
	return str;
    }

    /**
     * Get an generator property.
     *
     * @param message
     *            the generator property.
     */
    public String getServletString(String message) {
	return getString(new StringBuffer("servlet.").append(message)
			 .toString());
    }

    static {
	Utf8Properties tmp;
	try {
	    URL url = Messages.class.getResource("Messages.properties.en");
	    java.io.InputStream f = url.openStream();
	    try {
		tmp = new Utf8Properties();
		tmp.load(f);
		languages.put("en", tmp);
	    } finally {
		f.close();
	    }
	} catch (Exception e) {
	    System.err.println("org.w3c.css.util.Messages: "+
			       "couldn't load properties en");
	    System.err.println("  " + e.toString());
	}

	try {
	    URL url = Messages.class.getResource("Messages.properties.zh-cn");
	    java.io.InputStream f = url.openStream();
	    try {
		tmp = new Utf8Properties();
		tmp.load(f);
		languages.put("zh-cn", tmp);
	    } finally {
		f.close();
	    }
	} catch (Exception e) {
	    System.err.println("org.w3c.css.util.Messages: "+
			       "couldn't load properties cn");
	    System.err.println("  " + e.toString());
	}

	try {
	    URL url = Messages.class.getResource("Messages.properties.ja");
	    java.io.InputStream f = url.openStream();
	    try {
		tmp = new Utf8Properties();
		tmp.load(f);
		languages.put("ja", tmp);
	    } finally {
		f.close();
	    }
	} catch (Exception e) {
	    System.err.println("org.w3c.css.util.Messages:"+
			       " couldn't load properties ja");
	    System.err.println("  " + e.toString());
	}

        try {
            URL url = Messages.class.getResource("Messages.properties.fr");
            java.io.InputStream f = url.openStream();
            try {
		tmp = new Utf8Properties();
                tmp.load(f);
                languages.put("fr", tmp);
		languages.put("fr_FR", tmp);
            } finally {
                f.close();
            }
        } catch (Exception e) {
	    System.err.println("org.w3c.css.util.Messages: "+
			       "couldn't load properties fr");
	    System.err.println("  " + e.toString());
        }

	// -----------------------

        try {
            URL url = Messages.class.getResource("Messages.properties.de");
            java.io.InputStream f = url.openStream();
            try {
		tmp = new Utf8Properties();
                tmp.load(f);
                languages.put("de", tmp);
                languages.put("de_DE", tmp);
                languages.put("de_AT", tmp);
                languages.put("de_CH", tmp);
            } finally {
                f.close();
            }
	} catch (Exception e) {
	    System.err.println("org.w3c.css.util.Messages: "+
			       "couldn't load properties de");
	    System.err.println("  " + e.toString());
        }

	// ------------------------------------------------

	try {
	    URL url = Messages.class.getResource("Messages.properties.nl");
	    java.io.InputStream f = url.openStream();
	    try {
		tmp = new Utf8Properties();
		tmp.load(f);
		languages.put("nl", tmp);
	    } finally {
		f.close();
	    }
	} catch (Exception e) {
	    System.err.println("org.w3c.css.util.Messages: "+
			       "couldn't load properties nl");
	    System.err.println("  " + e.toString());
	}

	// ------------------------------------------------

        try {
	    URL url = Messages.class.getResource("Messages.properties.es");
	    java.io.InputStream f = url.openStream();
	    try {
		tmp = new Utf8Properties();
		tmp.load(f);
		languages.put("es", tmp);
		languages.put("es_ES", tmp);
	    } finally {
		f.close();
	    }
	} catch (Exception e) {
	    System.err.println("org.w3c.css.util.Messages: "+
			       "couldn't load properties es");
	    System.err.println("  " + e.toString());
	}
    }
}
