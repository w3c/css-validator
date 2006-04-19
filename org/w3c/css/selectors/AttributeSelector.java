// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors;

import org.w3c.css.util.ApplContext;

/**
 * Attribute<br />
 * Created: Sep 1, 2005 3:39:15 PM<br />
 */
public abstract class AttributeSelector implements Selector {

    private String name;

    /**
     * Creates a new empty attribute selector
     */
    public AttributeSelector() {
    }

    /**
     * Creates a new attribute selector given its name
     * @param name the name of this attribute
     */
    public AttributeSelector(String name) {        
	this.name = name;
    }

    /**
     * Sets the name of this attribute selector
     * @param name the name of this attribute
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @see Selector#getName()
     */
    public String getName() {
	return name;
    }

    public abstract void applyAttribute(ApplContext ac, AttributeSelector attr);

    /**
     * @see Selector#toString()
     */
    public String toString() {
	return "[" + name + "]";
    }

}
