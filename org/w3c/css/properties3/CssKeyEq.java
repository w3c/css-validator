//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssFunction;

/**
 *  <P>
 *  <EM>Value:</EM> none || &lt;key-press-combination&gt;+ || &lt;system-key-equivalent&gt; || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all enabled elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:interactive
 */

public class CssKeyEq extends CssProperty {

    CssValue keyCombi;
    Vector values = new Vector();

    CssIdent none = new CssIdent("none");
    CssIdent listitemmarker = new CssIdent("list-item-marker");

    private static String keys[] =
        { " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
	  "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
	  "accesskey", "fn", "fcn", "caps", "cmd", "rcmd", "lcmd", "opt",
	  "ropt", "lopt", "ctrl", "rctrl", "lctrl", "shift", "rshift",
	  "lshift", "alt", "ralt", "lalt", "win", "rwin", "lwin", "meta",
	  "rmeta", "lmeta", "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8",
	  "f9", "f10", "f11", "f12", "f13", "f14", "f15", "tab", "esc",
	  "enter", "return", "menu", "help", "namemenu", "rcl", "snd",
	  "up", "down", "left", "right", "home", "end", "pgup", "pgdn",
	  "bs", "del", "ins", "undo", "cut", "copy", "paste", "clr", "sto",
	  "prtsc", "sysrq", "scrlock", "pause", "brk", "numlock", "pwr"};

    private static String systemkeys[] =
        { "system-new", "system-open", "system-close", "system-save",
	  "system-print", "system-quit", "system-terminate-operation",
	  "system-undo", "system-redo", "system-cut", "system-copy",
	  "system-paste", "system-clear", "system-duplicate",
	  "system-select-all", "system-find", "system-find-again",
	  "system-ok", "system-cancel", "system-apply"
	};

    /**
     * Create a new CssKeyEq
     */
    public CssKeyEq() {
	keyCombi = none;
    }

    /**
     * Create a new CssKeyEq
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssKeyEq(ApplContext ac, CssExpression expression) throws InvalidParamException {
	String kc = new String();
	int hyphenindex;
	int counter = 0;
	char op = expression.getOperator();
        CssValue val = expression.getValue();
	String part = new String();
	String rest = new String();
	Vector ks = new Vector();
	setByUser();

	if (val.equals(none)) {
	    keyCombi = none;
	    expression.next();
	    return;
	}
	else if (val.equals(inherit)) {
	    keyCombi = inherit;
	    expression.next();
	    return;
	} else if (val.equals(listitemmarker)) {
		keyCombi = listitemmarker;
		expression.next();
		return;
	}
	else if (val instanceof CssFunction) {
	    CssFunction attr = (CssFunction) val;
	    CssExpression params = attr.getParameters();
	    CssValue v = params.getValue();
	    if (attr.getName().equals("attr")) {
		if ((params.getCount() != 1)
		    || !(v instanceof CssIdent)) {
		    throw new InvalidParamException("attr", params.getValue(),
						    getPropertyName(), ac);
		}
	    }
	    else throw new InvalidParamException("value", expression.getValue(),
						 getPropertyName(), ac);
	    keyCombi = val;
	    expression.next();
	    return;
	}

	// check if it is a system-key-equivalent
	for (int i = 0;i < systemkeys.length; i++) {
	    if (val.toString().equals(systemkeys[i])) {
		keyCombi = val;
		expression.next();
		return;
	    }
	}
	// no error, because it still can be a normal key combination

       	while ((op == CssOperator.SPACE)
	     && (counter < expression.getCount())) {
	    kc = val.toString();
	    if (kc.indexOf("-") < 0) { // only one key
		int i = 0;
		for (;i < keys.length; i++) {
		    if (kc.equals(keys[i])) {
			break;
		    }
		}
		if (i == keys.length) {
		    throw new InvalidParamException("value",
						    expression.getValue(),
						    getPropertyName(), ac);
		}
	    }
	    else { // keycombination

		int hyphenidx = kc.indexOf("-");
		part = kc.substring(0, hyphenidx).trim();
		rest = kc.substring(hyphenidx + 1, kc.length());
		ks.addElement(part);

		while (rest.indexOf("-") >= 0) {
		    hyphenidx = rest.indexOf("-");
		    part = rest.substring(0, hyphenidx).trim();
		    rest = rest.substring(hyphenidx + 1, rest.length());
		    ks.addElement(part);
		}
		ks.addElement(rest);

		for (int idx = 0; idx < ks.size(); idx++) {
		    int i = 0;
		    for (;i < keys.length; i++) {
			if ((keys[i]).equals((String)ks.elementAt(idx))) {
			    break;
			}
		    }
		    if (i == keys.length) {
			throw new InvalidParamException("value",
							expression.getValue(),
							getPropertyName(), ac);
		    }
		}
	    }
	    values.addElement(val);
	    expression.next();
	    counter++;
	    val = expression.getValue();
	    op = expression.getOperator();
	    ks.clear();
	}

    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssKeyEq != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssKeyEq = this;

    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getKeyEq();
	} else {
	    return ((Css3Style) style).cssKeyEq;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssKeyEq &&
		keyCombi.equals( ((CssKeyEq) property).keyCombi));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "key-equivalent";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return values;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	// @@TODO
	return false;
	//values.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	if (keyCombi != null)
	    return keyCombi.toString();
	else
	    return values.firstElement().toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return keyCombi == none;
    }

}
