// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;

import org.w3c.css.util.Utf8Properties;
import org.w3c.css.util.Util;

/**
 * PropertiesLoader<br />
 * Created: Aug 16, 2005 5:19:44 PM<br />
 * This class is a factory used to load all the CSS-related properties files
 */
public class PropertiesLoader {

    public static Utf8Properties DEFAULT_PROFILE;

    /**
     * Basic configuration of the CSS Validator
     */
    public static Utf8Properties config;

    /**
     * The association between properties and the media for which they
     * are defined
     */
    public static Utf8Properties mediaProperties;

    /**
     * The list of existing profiles associated to their Java classes
     */
    private static Utf8Properties profiles;

    /**
     * This hashtable contains for each css profile, an Utf8Properties
     * containing all its properties
     */
    private static Hashtable allProps;

    private static Utf8Properties loadProfile(String profile,
	    String profilePath) throws IOException {

	Utf8Properties result = new Utf8Properties();
	InputStream f = null;

	URL url = null;

	// the url of the properties file of the selected profile
	if(profilePath != null) {
	    url = PropertiesLoader.class.getResource(profilePath);
	}

	f = url.openStream();

	// we load the properties
	result.load(f);
	// we add the profile to the profiles Hashtable
	allProps.put(new String(profile), result);

	if(Util.onDebug) {
	    System.out.println(profile + " profile loaded");
	}
	return result;
    }

    /**
     *
     * @param profile the profile needed
     * @return an Utf8Properties containing all the properties for the specified profile
     */
    public static Utf8Properties getProfile(String profile) {
	Utf8Properties result = (Utf8Properties) allProps.get(profile);
	// the profile has not been loaded yet
	if(result == null) {
	    result = new Utf8Properties();

	    String profilePath = (String) profiles.get(profile);

	    if(profilePath != null && !profilePath.equals("")) {
		try {
		    return loadProfile(profile, profilePath);
		}
		catch(IOException e) {
		    if(Util.onDebug) {
			System.out.println(PropertiesLoader.class +
				": Error while loading " + profile +
				" profile");
		    }
		    e.printStackTrace();
		}
	    }
	    // if the wanted profile is unknown, or there has been an error
	    // while loading it, we return the default profile
	    return DEFAULT_PROFILE;
	}
	else {
	    return result;
	}
    }

    static {
	config = new Utf8Properties();
	mediaProperties = new Utf8Properties();
	profiles = new Utf8Properties();

	allProps = new Hashtable();

	InputStream f = null;

	try {
	    // first, we load the general Config
	    URL url = PropertiesLoader.class.getResource("Config.properties");
	    f = url.openStream();
	    config.load(f);

	    // the media associated to each property
	    url = PropertiesLoader.class.getResource(config.getProperty("media"));
	    f = url.openStream();
	    mediaProperties.load(f);

	    // profiles
	    url = PropertiesLoader.class.getResource(
		    config.getProperty("profilesProperties"));

	    f = url.openStream();
	    profiles.load(f);

	    // Load the default profile
	    String defaultProfile =  config.getProperty("defaultProfile");
	    String defaultPath = (String) profiles.get(defaultProfile);
	    DEFAULT_PROFILE = loadProfile(defaultProfile, defaultPath);

	    if(Util.onDebug) {
		System.out.println("Default profile (" + defaultProfile +
			") loaded");
	    }

	} catch (Exception e) {
	    System.err.println(PropertiesLoader.class +
		    ": Error while loading default config");
	    e.printStackTrace();
	} finally {
	    try {
		if (f != null) {
		    f.close();
		    f = null;
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

    }

}
