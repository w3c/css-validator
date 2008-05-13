// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors.attributes;

import org.w3c.css.selectors.AttributeSelector;
import org.w3c.css.selectors.Selector;
import org.w3c.css.util.ApplContext;

/**
 * AttributeOneOf<br />
 * Created: Sep 1, 2005 4:30:13 PM<br />
 */
public class AttributeOneOf extends AttributeSelector {

    String value;

    public AttributeOneOf(String name, String value) {
	setName(name);
	this.value = value;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    public boolean canApply(Selector other) {
	if (other instanceof AttributeAny) {
	    // [lang~=fr][lang]
	    // return [lang~=fr]
	    return true;
	} else if (other instanceof AttributeExact) {
	    String exact = ((AttributeExact) other).getValue();
	    // [lang~=fr][lang=fr]
	    if(value.equals(exact)) {
		// [lang~=fr][lang=fr]
		return true;
	    }
	    // [lang~=en][lang=fr]
	    return false;
	} else if (other instanceof AttributeOneOf) {
	    return true;
	} else if (other instanceof AttributeBegin) {
	    // [lang=~fr][lang|=fr]
	    return true;
	}
	return false;
    }

    public String toString() {
	return "[" + getName() + "~=\"" + value + "\"]";
    }

    public void applyAttribute(ApplContext ac, AttributeSelector attr) {
	if (getName().equals(attr.getName())) {
	    if((attr instanceof AttributeExact) &&
	       !value.equals(((AttributeExact) attr).getValue())) {
		ac.getFrame().addWarning("incompatible", new String[] { toString(), attr.toString() });
	    }
	}
    }

}
