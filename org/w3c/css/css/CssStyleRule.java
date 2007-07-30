// $Id$
// Author: Sijtsche de Jong
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.util.Vector;

import org.w3c.css.properties.css1.CssProperty;

public class CssStyleRule {

	public CssStyleRule(String indent, String selectors, Vector properties, boolean important) {
		this.selectors = selectors;
		this.properties = properties;
		this.indent = indent;
	}
	/**
	 * This function is only used inside the velocity template
	 * @return the list of selectors in a string
	 */
	public String getSelectors() {
		return selectors;
	}
	
	/**
	 * This function is only used inside the velocity template
	 * @return the list of properties in a Vector
	 */
	public Vector getProperties() {
		return properties;
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

	private String indent;
	private String selectors;
	private Vector properties;

}
