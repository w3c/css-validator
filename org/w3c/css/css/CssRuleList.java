// $Id$
// Author: Sijtsche de Jong
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.util.Vector;

import org.w3c.css.parser.AtRule;
import org.w3c.css.util.Util;

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
		StringBuffer ret = new StringBuffer();
		if (null != atRule && atRule.isEmpty()) {
			if (!atRuleString.equals("")) {
				ret.append(atRuleString);
				ret.append("\n\n");
			}
		} else {
			if (!atRuleString.equals("")) {
				ret.append(atRuleString);
				ret.append(" {\n\n");
			}
			for (int i = 0; i < rulelist.size(); i++) {
				ret.append((CssStyleRule) rulelist.elementAt(i));
			}

			if (!atRuleString.equals("")) {
				ret.append("}\n");
			}
		}
		return ret.toString();
	}

	/*
	 * public String toString() { StringBuffer ret = new StringBuffer();
	 * 
	 * if (atRule == null || atRule.isEmpty()) { if (null != atRule &&
	 * !atRuleString.equals("")) { ret.append(atRuleString); ret.append(' ');
	 * ret.append('\n'); } } else { if (!atRuleString.equals("")) {
	 * ret.append(atRuleString); ret.append(' '); ret.append('{');
	 * ret.append('\n'); indent = " "; } for (int i = 0; i < rulelist.size() ;
	 * i++ ) { ret.append(indent);
	 * ret.append(((CssStyleRule)rulelist.elementAt(i)).toString()); }
	 * 
	 * if (!atRuleString.equals("")) { ret.append('}'); ret.append('\n'); } }
	 * return ret.toString(); }
	 */

	/*
	 * public String toHTML() { StringBuffer ret = new StringBuffer();
	 * 
	 * if (null != atRule && atRule.isEmpty()) { if (!atRuleString.equals("")) {
	 * ret.append("<li><span class='atSelector'>"); ret.append(atRuleString);
	 * ret.append("</span></li> \n\n"); } } else { if
	 * (!atRuleString.equals("")) { ret.append("<li><span
	 * class='atSelector'>"); ret.append(atRuleString); ret.append("</span> {\n<ul>\n"); }
	 * for (int i = 0; i < rulelist.size() ; i++ ) {
	 * ret.append(((CssStyleRule)rulelist.elementAt(i)).toHTML()); }
	 * 
	 * if (!atRuleString.equals("")) { ret.append("</ul>}</li>\n"); } }
	 * return ret.toString(); }
	 */
	public String toHTML() {
		String ret = "\t\t\t\t\t";
		if (null != atRule && atRule.isEmpty()) {
			if (!atRuleString.equals("")) {
				ret += "<div class='atRule'><span class='atSelector'>";
				ret += Util.escapeHTML(atRuleString);
				ret += "</span></div> \n\n";
			}
		} else {
			if (!atRuleString.equals("")) {
				ret += "<div class='atRule'><span class='atSelector'>";
				ret += Util.escapeHTML(atRuleString);
				ret += "</span> {\n\t\t\t\t\t\t<div>\n";
			}
			for (int i = 0; i < rulelist.size(); i++) {
				ret += ((CssStyleRule) rulelist.elementAt(i)).toHTML();
			}
			if (!atRuleString.equals("")) {
				ret += "\t\t\t\t\t\t</div>}\n\t\t\t\t\t</div>\n";
			}
		}
		return ret;
	}

	public void clear() {
		atRuleString = "";
		rulelist.removeAllElements();
		pseudopage = "";
	}
}
