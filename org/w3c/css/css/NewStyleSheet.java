//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.css;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

import org.w3c.css.parser.Errors;
import org.w3c.css.util.Warnings;
import org.w3c.css.parser.AtRule;
import org.w3c.css.parser.AtRuleMedia;
import org.w3c.css.parser.AtRuleFontFace;
import org.w3c.css.parser.AtRulePreference;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.properties.CssProperty;

public class NewStyleSheet {

    String uri;
    String title;
    private Errors errors;
    private Warnings warnings;

    /**
     * Add some errors to this style.
     *
     * @param errors Some errors.
     */  
    public void addErrors(Errors errors) {
	if (errors.getErrorCount() != 0) {
	    getErrors().addErrors(errors);
	}
    }
    
    /**
     * Add some warnings to this style.
     *
     * @param warnings Some warnings.
     */  
    public void addWarnings(Warnings warnings) {
	if (warnings.getWarningCount() != 0)
	    getWarnings().addWarnings(warnings);
    }
    
    /**
     * Returns all errors.
     */  
    public final Errors getErrors() {
	return errors;
    }
    
    /**
     * Returns all warnings.
     */  
    public final Warnings getWarnings() {
	return warnings;
    }

    static Vector atRuleList = new Vector(); //contains all @rules (CssRuleLists)

    public void newAtRule(AtRule atRule) {
	CssRuleList rulelist = new CssRuleList();
	rulelist.addAtRule(atRule.toString());
	atRuleList.addElement(rulelist);
	indent = "   ";
    }

    public void endOfAtRule() {
	CssRuleList rulelist = new CssRuleList();
	atRuleList.addElement(rulelist); //for the new set of rules
	pseudopage = "";
	important = false;
	selectortext = "";
	indent = "";
    }

    public void setImportant(boolean important) {
	this.important = important;
    }

    public void setSelectorList(Vector selectors) {
	String slave = selectors.toString();
	slave = slave.substring(slave.indexOf("[") + 1, slave.indexOf("]"));
	selectortext = slave;
    }

    public void setProperty(Vector properties) {
	this.properties = properties;
    }

    public void endOfRule() {
	CssStyleRule stylerule = new CssStyleRule(indent, selectortext, 
						  properties, important);
	CssRuleList rulelist;
	if (!atRuleList.isEmpty()) {
	    rulelist = (CssRuleList)atRuleList.lastElement();
	    useless = atRuleList.remove(rulelist);
	} else {
	    rulelist = new CssRuleList();
	}
    	rulelist.addStyleRule(stylerule);
	atRuleList.addElement(rulelist);
    }

    public void pseudoPage(String name) {
	pseudopage = name;
    }
    
    public Vector getRules() {
	return atRuleList;
    }

    static String pseudopage;
    static String selectortext;
    static boolean important;
    static Vector properties;
    boolean useless;
    static String indent =  new String();
}
