//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;

import org.w3c.css.parser.AtRule;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.parser.Errors;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.SortedHashtable;
import org.w3c.css.util.Util;
import org.w3c.css.util.Warnings;

/**
 * This class contains a style sheet with all rules, errors and warnings.
 *
 * @version $Revision$
 */
public class StyleSheet {

    private CssCascadingOrder cascading;
    private SortedHashtable rules;
    private Errors   errors;
    private Warnings warnings;
    private String   type;
    private Vector atRuleList;
    private boolean doNotAddRule;
    private boolean doNotAddAtRule;
    private static final boolean debug = false;

    /**
     * Create a new StyleSheet.
     */
    public StyleSheet() {
	rules = new SortedHashtable();
	errors  = new Errors();
	warnings = new Warnings();
	cascading = new CssCascadingOrder();
	atRuleList = new Vector();
    }

    public void setWarningLevel(int warningLevel) {
    	warnings.setWarningLevel(warningLevel);
    }
    
    /**
     * Get a style in a specific context.
     * No resolution are perfomed when this function is called
     *
     * @param context The context for the style
     * @return The style for the specific context.
     */
    public CssStyle getStyle(CssSelectors context) {
	if (debug) {
	    Util.verbose("StyleSheet.getStyle("+context+")");
	}
	if (getContext(context) != null) {
	    CssSelectors realContext = (CssSelectors) getContext(context);
	    CssStyle style = realContext.getStyle();
	    style.setStyleSheet(this);
	    style.setSelector(realContext);
	    return style;
	} else {
	    getRules().put(context, context);
	    context.getStyle().setStyleSheet(this);
	    context.getStyle().setSelector(context);
	    return context.getStyle();
	}

    }

    /**
     * Add a property to this style sheet.
     *
     * @param selector The context where the property is defined
     * @param property The property to add
     */
    public void addProperty(CssSelectors selector, CssProperty property) {
	if (debug) {
	    Util.verbose("add property "
			 + getContext(selector)
			 + " " + property);
	}
	getContext(selector).addProperty(property, warnings);
    }

    public void remove(CssSelectors selector) {
	rules.remove(selector);
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getType() {
	if (type == null) {
	    return "text/css";
	} else {
	    return type;
	}
    }

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

    /**
     * Returns all rules
     */
    public final Hashtable getRules() {
	return rules;
    }

    /**
     * Returns the property for a context.
     *
     * @param property The default value returned if there is no property.
     * @param style The current style sheet where we can find all properties
     * @param selector The current context
     * @return the property with the right value
     */
    public final CssProperty CascadingOrder(CssProperty property,
					    StyleSheet style,
					    CssSelectors selector) {
	return cascading.order(property, style, selector);
    }

    /**
     * Find all conflicts for this style sheet.
     */
    public void findConflicts(ApplContext ac) {
	Hashtable rules = getRules();
	CssSelectors[] all = new CssSelectors[rules.size()];
	all = (CssSelectors[]) rules.values().toArray(all);
	for (int i = 0; i< all.length; i++) {
	    all[i].markAsFinal();
	}
	for (int i = 0; i< all.length; i++) {
	    CssSelectors sel = all[i];
	    sel.findConflicts(ac, warnings, all);
	}
    }

    /**
     * Returns the unique context for a context
     *
     * @param selector the context to find.
     */
    protected CssSelectors getContext(CssSelectors selector) {
	if (getRules().containsKey(selector)) {
	    return (CssSelectors) getRules().get(selector);
	} else {
	    if (selector.getNext() != null) {
		CssSelectors next = getContext(selector.getNext());
		selector.setNext(next);
	    }
	    getRules().put(selector, selector);
	    return selector;
	}
    }

    /**
     * dump this style sheet.
     */
    public void dump() {
	StyleSheetGenerator style =
	    new StyleSheetGenerator("", this, "text", -1);
	style.print(new PrintWriter(System.out));
    }

    //part added by Sijtsche de Jong

    public void addCharSet(String charset) {
	this.charset = charset;
    }

    public void newAtRule(AtRule atRule) {
	CssRuleList rulelist = new CssRuleList();
	rulelist.addAtRule(atRule);
	atRuleList.addElement(rulelist);
	indent = "   ";
    }

    public void endOfAtRule() {
	if (!doNotAddAtRule) {
	    CssRuleList rulelist = new CssRuleList();
	    atRuleList.addElement(rulelist); //for the new set of rules
	}
	important = false;
	selectortext = "";
	indent = "";
	doNotAddAtRule = false;
    }

    public void setImportant(boolean important) {
	this.important = important;
    }

    public void setSelectorList(Vector selectors) {
	String slave = selectors.toString();
	selectortext = slave.substring(slave.indexOf("[") + 1,
				       slave.lastIndexOf("]"));
    }

    public void setProperty(Vector properties) {
	this.properties = properties;
    }

    public void endOfRule() {
	CssRuleList rulelist;
	boolean useless;
	if (!doNotAddRule) {
	    CssStyleRule stylerule = new CssStyleRule(indent, selectortext,
						      properties, important);
	    if (!atRuleList.isEmpty()) {
		rulelist = (CssRuleList)atRuleList.lastElement();
		useless = atRuleList.removeElement(rulelist);
	    } else {
		rulelist = new CssRuleList();
	    }
	    rulelist.addStyleRule(stylerule);
	    atRuleList.addElement(rulelist);
	}
	selectortext = "";
	doNotAddRule = false;
    }

    public void removeThisRule() {
	doNotAddRule = true;
    }

    public void removeThisAtRule() {
	doNotAddAtRule = true;
    }

    public Vector newGetRules() {
	return atRuleList;
    }

    String selectortext;
    boolean important;
    Vector properties;
    String indent =  new String();
    public String charset;
}
