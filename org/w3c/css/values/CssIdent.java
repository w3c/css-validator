//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import java.util.HashMap;

import org.w3c.css.util.ApplContext;

/**
 * @version $Revision$
 */
public class CssIdent extends CssValue {

    public static HashMap<String,CssIdent> allowedvalues;
    static {
	allowedvalues = new HashMap<String,CssIdent>();
	allowedvalues.put("inherit", new CssIdent("inherit"));
	allowedvalues.put("none", new CssIdent("inherit"));
    }
    /**
     * Get a cached CssIdent, useful for common values like "inherit"
     * @param name, the ident name
     * @return a CssIdent
     */
    public static CssIdent getIdent(String name) {
	CssIdent val = allowedvalues.get(name);
	if (val != null) {
	    return val;
	}
	val = new CssIdent(name);
	allowedvalues.put(name, val);
	return val;
    }

    public static final int type = CssTypes.CSS_IDENT;
    
    private int hashcode = 0;

    public final int getType() {
	return type;
    }
    
    /**
     * Create a new CssIdent
     */
    public CssIdent() {
    }
    
    /**
     * Create a new CssIdent
     *
     * @param s The identificator
     */
    public CssIdent(String s) {
	value = s;
    }

    /**
     * Set the value of this ident.
     *
     * @param s     the string representation of the identificator.
     * @param frame For errors and warnings reports.
     */
    public void set(String s, ApplContext ac) {
	value = s;
	hashcode = 0;
    }

    /**
     * Returns the internal value.
     */
    public Object get() {
	return value;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value;
    }

    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(Object value) {
	return (value instanceof CssIdent && value.hashCode() == hashCode());
    }

    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(CssIdent value) {
	return (value.hashCode() == hashCode());
    }

    /**
     * Returns a hashcode for this ident.
     */
    public int hashCode() {
	// we cache, as we use toLowerCase and don't store the resulting string
	if (hashcode == 0) {
	    hashcode = value.toLowerCase().hashCode();
	}
	return hashcode;
    }

    private String value;
}
