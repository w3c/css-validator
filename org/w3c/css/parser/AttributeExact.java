//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.parser;

// [lang=fr]
/**
 * 
 */
public class AttributeExact extends Attribute {
    String value;

    final AttributeExact setValue(String value) {
	this.value = value;
	return this;
    }

    public final String getValue() {
	return value;
    }
    
    public boolean isId() {
	return getName().equals("id");
    }

    public Attribute applyAttribute(Attribute attr) throws AttributeException {
	if (attr instanceof AttributeAny) {
	    // [lang=fr][lang]
	    // return [lang=fr]
	    return this;
	} else if (attr instanceof AttributeExact) {
	    if (!value.equals(((AttributeExact) attr).value)) {
		// [lang=fr][lang=en]
		throw new AttributeException();
	    } else {
		// [lang=en][lang=en]
		// return [lang=en]
		return this;
	    }
	} else if (attr instanceof AttributeOneOf) {
	    // [lang=fr][lang~=fr]
	    return attr.applyAttribute(this);
	} else if (attr instanceof AttributeBegin) {
	    // [lang=fr][lang|=fr]
	    return attr.applyAttribute(this);
	}
	throw new AttributeException();
    }

    public boolean canApply(Attribute attr) {
	if (attr instanceof AttributeAny) {
	    // [lang=fr][lang]
	    return true;
	} else if (attr instanceof AttributeExact) {
	    if (!value.equals(((AttributeExact) attr).value)) {
		// [lang=fr][lang=en]
		return false;
	    } else {
		// [lang=en][lang=en]
		return true;
	    }
	} else if (attr instanceof AttributeOneOf) {
	    // [lang=fr][lang~=fr]
	    return attr.canApply(this);
	} else if (attr instanceof AttributeBegin) {
	    // [lang=fr][lang|=fr]
	    return attr.canApply(this);
	}
	return false;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {    
	if (isId()) {
	    return "#" + getValue();
	} else {
	    return "[" + getName() + "=" + getValue() + "]";
	}
    }
}
