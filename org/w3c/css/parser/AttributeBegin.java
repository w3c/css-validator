//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.parser;

// [lang|=fr]
/**
 * 
 */
public class AttributeBegin extends Attribute {
    String value;

    final AttributeBegin setValue(String value) {
	this.value = value;
	return this;
    }

    public final String getValue() {
	return value;
    }
    
    public Attribute applyAttribute(Attribute attr) throws AttributeException {
	if (attr instanceof AttributeAny) {
	    // [lang|=fr][lang]
	    return this;
	} else if (attr instanceof AttributeExact) {
	    String v = ((AttributeExact) attr).value;
	    int index = v.indexOf('-');
	    if (index > 0) {
		v = v.substring(0, index);
	    }
	    if (!value.equals(v)) {
		// [lang|=fr][lang=en-US]
		throw new AttributeException();
	    } else {
		// on [lang|=en][lang=en-US]
		// return [lang=en-US]
		return attr;
	    }
	} else if (attr instanceof AttributeOneOf) {
	    return attr.applyAttribute(this);
	} else if (attr instanceof AttributeBegin) {
	    if (!value.equals(((AttributeBegin) attr).value)) {
		// [lang=fr][lang|=en]
		throw new AttributeException();
	    } else {
		// [lang=en][lang|=en]
		// return [lang=en]
		return this;
	    }	    
	}
	throw new AttributeException();
    }

    public boolean canApply(Attribute attr) {
	if (attr instanceof AttributeAny) {
	    // [lang|=fr][lang]
	    return true;
	} else if (attr instanceof AttributeExact) {
	    String v = ((AttributeExact) attr).value;
	    int index = v.indexOf('-');
	    if (index > 0) {
		v = v.substring(0, index);
	    }
	    if (!value.equals(v)) {
		// [lang|=fr][lang=en-US]
		return false;
	    } else {
		// [lang|=en][lang=en-US]
		return true;
	    }
	} else if (attr instanceof AttributeOneOf) {
	    return attr.canApply(this);
	} else if (attr instanceof AttributeBegin) {
	    if (!value.equals(((AttributeBegin) attr).value)) {
		// [lang|=fr][lang|=en]
		return false;
	    } else {
		// [lang|=en][lang|=en]
		return true;
	    }	    
	}
	return false;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {    
	return "[" + getName() + "|=" + getValue() + "]";
    }
}

