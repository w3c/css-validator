//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.parser;

import java.util.StringTokenizer;

// ~=
/**
 * 
 */
public class AttributeOneOf extends Attribute implements CssSelectorsConstant {
    String[] values = new String[ATTRIBUTE_LENGTH];
    int length = 0;

    AttributeOneOf addValue(String value) {
	if (length == ATTRIBUTE_LENGTH) {
	    throw new ArrayIndexOutOfBoundsException();
	}
	StringTokenizer tokens = new StringTokenizer(value);
	
	while (tokens.hasMoreTokens()) {
	    boolean found = false;
	    value = tokens.nextToken();

	    for (int i = 0; (i < length) && !found; i++) {
		if (values[i].equals(value)) {
		    found = true;
		}
	    }
	    if (!found) {
		values[length++] = value;
	    }
	}
	return this;
    }

    public boolean isClass() {
	return getName().equals("class");
    }

    public String getValue() {
	return values[0];
    }

    public String getValue(int index) {
	return values[index];
    }

    public int size() {
	return length;
    }

    public Attribute applyAttribute(Attribute attr) throws AttributeException {
	if (attr instanceof AttributeAny) {
	    // [lang~=fr][lang]
	    // return [lang~=fr]
	    return this;
	} else if (attr instanceof AttributeExact) {
	    String exact = ((AttributeExact) attr).value;
	    // [lang~=fr][lang=fr]
	    for (int i = 0; i < length; i++) {
		if (exact.indexOf(values[i]) == -1) {
		    // [lang~=en][lang=fr]
		    throw new AttributeException();
		}
	    }
	    // [lang~=fr][lang=fr]
	    // return [lang=fr]
	    return attr;
	} else if (attr instanceof AttributeOneOf) {
	    int slength = ((AttributeOneOf) attr).length;
	    String[] svalues = ((AttributeOneOf) attr).values;
	    for (int i = 0; i < slength; i++) {
		addValue(svalues[i]);
	    }
	    return this;
	} else if (attr instanceof AttributeBegin) {
	    // [lang=~fr][lang|=fr]
	    String value = ((AttributeBegin) attr).value;
	    for (int i = 0; i < length; i++) {
		
	    }
	    return this;
	}
	throw new AttributeException();
    }
    
    public boolean canApply(Attribute attr) {
	if (attr instanceof AttributeAny) {
	    // [lang~=fr][lang]
	    // return [lang~=fr]
	    return true;
	} else if (attr instanceof AttributeExact) {
	    String exact = ((AttributeExact) attr).value;
	    // [lang~=fr][lang=fr]
	    for (int i = 0; i < length; i++) {
		if (exact.equals(values[i])) {
		    // [lang~=fr][lang=fr]
		    return true;
		}
	    }
	    // [lang~=en][lang=fr]
	    return false;
	} else if (attr instanceof AttributeOneOf) {
	    int slength = ((AttributeOneOf) attr).length;
	    String[] svalues = ((AttributeOneOf) attr).values;
	    for (int i = 0; i < slength; i++) {
		addValue(svalues[i]);
	    }
	    return false;
	} else if (attr instanceof AttributeBegin) {
	    // [lang=~fr][lang|=fr]
	    String value = ((AttributeBegin) attr).value;
	    for (int i = 0; i < length; i++) {
		// ??
	    }
	    return false;
	}
	return false;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {    
	String ret = "";
	for (int i = 0; i < length; i++) {
	    if (isClass()) {
		ret += "." + values[i];
	    } else {
		ret += "[" + name + "~=" + values[i] + "]";
	    }
	}
	return ret;
    }
}
