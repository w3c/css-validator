/* Made by Sijtsche de Jong */

package org.w3c.css.css;

import org.w3c.css.parser.AtRule;
import java.util.Vector;

public class CssRuleList {

    AtRule atRule;
    String atRuleString;
    Vector rulelist;
    public String pseudopage;
    String indent;

    public CssRuleList() {
	atRule = null;
	atRuleString = new String();
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

    public void addAtRule(AtRule atRule) {
		this.atRule = atRule;
		atRuleString = atRule.toString();
    }

    public String getAtRule() {
		return atRuleString;
    }

    public String toString() {
	String ret = new String();

	if (atRule.isEmpty()) {

		if (null != atRule && !atRuleString.equals("")) {
		    ret += atRuleString + " \n";
		}

	} else {

		if (!atRuleString.equals("")) {
		    ret += atRuleString + " {\n";
		    indent = "   ";
		}
		for (int i = 0; i < rulelist.size() ; i++ ) {
		    ret += indent + ((CssStyleRule)rulelist.elementAt(i)).toString();
		}

		if (!atRuleString.equals("")) {
		    ret += "}\n";
		}
	}
	return ret;
    }

    public String toHTML() {
	String ret = new String();

	if (null != atRule && atRule.isEmpty()) {

		if (!atRuleString.equals("")) {
		    ret += "<li><span class='atSelector'>" + atRuleString + "</span></li> \n\n";
		}

	} else {

		if (!atRuleString.equals("")) {
		    ret += "<li><span class='atSelector'>" + atRuleString + "</span> {\n" +
			"<ul>\n";
		}
		for (int i = 0; i < rulelist.size() ; i++ ) {
		    ret += ((CssStyleRule)rulelist.elementAt(i)).toHTML();
		}

		if (!atRuleString.equals("")) {
		    ret += "</ul>}</li>\n";
		}
	}

	return ret;
    }

    public void clear() {
	atRuleString = "";
	rulelist.clear();
	pseudopage = "";
    }
}
