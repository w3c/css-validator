//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.parser;

// [lang]
public class AttributeAny extends Attribute {
    public Attribute applyAttribute(Attribute attr) throws AttributeException {
	// here it's very simple
	// if you said [lang][lang~=fr], I only see [lang~=fr]
	return attr;
    }

    public boolean canApply(Attribute attr) {
	// this attribute can be apply in all cases
	return true;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {    
	return "[" + getName() + "]";
    }
}

