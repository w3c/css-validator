// $Id$
// Author: Sijtsche de Jong
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.util.Vector;

import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.Util;

public class CssStyleRule {

	public CssStyleRule(String indent, String selectors, Vector properties, boolean important) {
		this.selectors = selectors;
		this.properties = properties;
		this.indent = indent;
	}

	public String toString() {
		StringBuffer ret = new StringBuffer();
		if (selectors != null) {
			ret.append(selectors);
			ret.append(' ');
			ret.append('{');
			ret.append('\n');
		}

		for (int i = 0; i < properties.size(); i++) {
			CssProperty property = (CssProperty) properties.elementAt(i);
			ret.append(indent);
			ret.append("   ");
			ret.append(property.getPropertyName());
			ret.append(" : ");
			ret.append(property.toString());
			if (property.getImportant()) {
				ret.append(" important");
			}
			ret.append(';');
			ret.append('\n');
		}
		if (selectors != null) {
			ret.append(indent);
			ret.append('}');
			ret.append('\n');
			ret.append('\n');
		}
		return ret.toString();
	}

	/*
	 * public String toHTML() { StringBuffer ret = new StringBuffer("<li><span
	 * class='selector'>"); if (selectors != null) { ret.append(selectors);
	 * ret.append("</span> {<ul class='vRule'>\n"); }
	 * 
	 * for (int i = 0; i < properties.size() ; i++) { CssProperty property =
	 * (CssProperty)properties.elementAt(i); ret.append("<li>");
	 * ret.append(property.getPropertyName()); ret.append(" : <span
	 * class='vPropertyValue'>"); ret.append(property.toString()); ret.append("</span>");
	 * if (property.getImportant()) { ret.append(" !important"); } ret.append(";</li>\n"); }
	 * ret.append("</ul>}</li>\n\n"); return ret.toString(); }
	 */
	
	/**
	 * This method returns a part of the style sheet to be displayed
	 * Some identation (\t) was necessary to maintain the correct formatting
	 * of the html output.
	 */
	public String toHTML() {
		String ret = "";
		if (properties != null) {
			ret = "\n\t\t\t\t<div class='selector'>\n\t\t\t\t\t<span class='selectorValue'>";
			if (selectors != null) {
				ret += selectors;
				ret += "</span> {\n\t\t\t\t\t<div class='RuleList'>\n";
			}

			for (int i = 0; i < properties.size(); i++) {
				CssProperty property = (CssProperty) properties.elementAt(i);
				ret += "\t\t\t\t\t\t<div class='Rule'>";
				ret += "<span class='Property'>";
				ret += Util.escapeHTML(property.getPropertyName());
				ret += "</span>";
				ret += " : <span class='PropertyValue'>";
				ret += Util.escapeHTML(property.toString());
				ret += "</span>";
				if (property.getImportant()) {
					ret += " !important";
				}
				ret += ";</div>\n";
			}
			ret += "\t\t\t\t\t</div>}\n\t\t\t\t</div>\n\n";
		}
		return ret;
	}

	private String indent;
	private String selectors;
	private Vector properties;

}
