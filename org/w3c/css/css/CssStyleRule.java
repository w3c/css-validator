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
	String ret = new String("<li><span class='selector'>"); 
	if (selectors != null) {
	    ret += selectors + "</span> {<ul class='vRule'>\n";
	}

	for (int i = 0; i < properties.size() ; i++) {
	    CssProperty property = (CssProperty)properties.elementAt(i);
	    ret += "<li>" + property.getPropertyName() + " : "
		+ "<span class='vPropertyValue'>" 
		+ property.toString() + "</span>";
	    if (property.getImportant()) {
		ret += " !important";
	    }
	    ret += ";</li>\n";
	}
	ret += "</ul>}</li>\n\n";
	return ret;	
    }

    private String indent;
    private String selectors;
    private Vector properties;
    private boolean important;

}
