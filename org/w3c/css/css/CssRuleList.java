/* Made by Sijtsche de Jong */

package org.w3c.css.css;

import java.util.Vector;

public class CssRuleList {

    String atRule;
    Vector rulelist;
    public String pseudopage;
    String indent;

    public CssRuleList() {
	atRule = new String();
	rulelist = new Vector();
	String pseudopage = new String();
	indent = new String();
    }

    public void addStyleRule(CssStyleRule stylerule) {
	rulelist.addElement(stylerule);
    }

    public Vector getStyleRules() {
	return rulelist;
    }

    public void addAtRule(String atRule) {
	this.atRule = atRule;
    }

    public String getAtRule() {
	return atRule;
    }

    public String toString() {
	String ret = new String();
	if (!atRule.equals("")) {
	    ret += atRule + " {\n";
	    indent = "   ";
	}
	for (int i = 0; i < rulelist.size() ; i++ ) {
	    ret += indent + ((CssStyleRule)rulelist.elementAt(i)).toString();
	}

	if (!atRule.equals("")) {
	    ret += "}\n";
	}

	return ret;
    }

    public String toHTML() {
	String ret = new String();
	if (!atRule.equals("")) {
	    ret += "<li><span class='atSelector'>" + atRule + "</span> {\n" + 
		"<ul>\n";
	}
	for (int i = 0; i < rulelist.size() ; i++ ) {
	    ret += ((CssStyleRule)rulelist.elementAt(i)).toHTML();
	}

	if (!atRule.equals("")) {
	    ret += "</ul>}</li>\n";
	}

	return ret;
    }

    public void clear() {
	atRule = "";
	rulelist.clear();
	pseudopage = "";
    }
}
