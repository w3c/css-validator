//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.parser;

/**
 * 
 */
public abstract class Attribute {
    String name;

    final Attribute setName(String name) {
	this.name = name;
	return this;
    }

    public final String getName() {
	return name;
    }

    public boolean isId() {
	return false;
    }

    public boolean isClass() {
	return false;
    }

    public abstract Attribute applyAttribute(Attribute attr) 
	throws AttributeException;

    public abstract boolean canApply(Attribute attr);
}

