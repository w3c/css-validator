/*
 * (c) COPYRIGHT 1999 World Wide Web Consortium
 * (Massachusetts Institute of Technology, Institut National de Recherche
 *  en Informatique et en Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 *
 * $Id$
 */
package org.w3c.css.util;

import org.w3c.css.parser.Frame;

/**
 * @version $Revision$
 * @author  Philippe Le Hegaret
 */
public class ApplContext {

    String credential = null;

    String lang;
    Messages msgs;
    Frame  frame;
    String cssversion;
    String profile;
    String input;
    Class cssselectorstyle;

    int origin = -1;
    String medium;

    /**
     * Creates a new ApplContext
     */
    public ApplContext(String lang) {
	this.lang =lang;
        msgs = new Messages(lang);
    }

    // as ugly as everything else
    public String getCredential() {
	return credential;
    }

    public void setCredential(String credential) {
	this.credential = credential;
    }

    public void setFrame(Frame frame) {
	this.frame = frame;
	frame.ac = this;
    }

    public Frame getFrame() {
	return frame;
    }

    public Class getCssSelectorsStyle() {
	return cssselectorstyle;
    }

    public void setCssSelectorsStyle(Class s) {
	cssselectorstyle = s;
    }

    public Messages getMsg() {
	return msgs;
    }

    public String getContentType() {
	return (msgs != null)? 
	    msgs.getString("content-type")
	    : null;
    }

    public String getContentLanguage() {
	return (msgs != null)? 
	    msgs.getString("content-language")
	    : null;
    }

    public String getContentEncoding() {
	return (msgs != null)? 
	    msgs.getString("content-encoding")
	    : null;
    }

    public String getLang() {
	return lang;
    }

    public void setCssVersion(String cssversion) {
        this.cssversion = cssversion;
    }

    public String getCssVersion() {
	if (cssversion == null) {
	    cssversion = "css2";
	}
	return cssversion;
    }
    
    public void setProfile(String profile) {
	this.profile = profile;
    }

    public String getProfile() {
	if (profile == null) {
	    return "";
	}
	return profile;
    }

    public void setOrigin(int origin) {
	this.origin = origin;
    }

    public int getOrigin() {
	return origin;
    }

    public void setMedium(String medium) {
	this.medium = medium;
    }

    public String getMedium() {
	return medium;
    }

    public String getInput() {
	return input;
    }

    public void setInput(String input) {
	this.input = input;
    }
}
