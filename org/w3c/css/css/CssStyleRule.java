/* Made by Sijtsche de Jong */

package org.w3c.css.css;

import java.util.Vector;

import org.w3c.css.properties.CssProperty;


public class CssStyleRule {

    public CssStyleRule(String indent, String selectors, 
			Vector properties, boolean important) {
	this.selectors = selectors;
	this.properties = properties;
	this.important = important;
	this.indent = indent;
	if (indent.length() > 0) {
	    HTMLindent = "&nbsp;&nbsp;";
	} else { 
	    HTMLindent = "";
	}
    }

    public String toString() {
	String ret = new String();
	if (selectors != null) {
	    ret += selectors + " {\n";
	}

	for (int i = 0; i < properties.size() ; i++) {
	    CssProperty property = (CssProperty)properties.elementAt(i);
	    ret += indent + "   " + property.getPropertyName() + " : " + 
		property.toString();
	    if (property.getImportant()) {
		ret += " important";
	    }
	    ret += ";\n";
	}
	if (selectors != null) {
	    ret += indent + "}\n\n";
	}

	return ret;

    }

    public String toHTML() {
	String ret = new String("<dt>"); 
	if (selectors != null) {
	    ret += "<strong>" + selectors + "</strong> {<br>\n";
	}

	for (int i = 0; i < properties.size() ; i++) {
	    CssProperty property = (CssProperty)properties.elementAt(i);
	    ret += HTMLindent + "&nbsp;&nbsp;" 
		+ property.getPropertyName() + " : " + "<em>" 
		+ property.toString() + "</em>";
	    if (property.getImportant()) {
		ret += " important";
	    }
	    ret += ";<br>\n";
	}
	if (indent.length() > 0 && selectors != "") {
	    ret += indent + "}<br>\n";
	} else if (selectors != null) {
	    ret += indent + "}<br><br>\n\n";
	}

	return ret;	
    }

    private String indent;
    private String HTMLindent;
    private String selectors;
    private Vector properties;
    private boolean important;

}
