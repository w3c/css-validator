// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors.attributes;

import org.w3c.css.selectors.AttributeSelector;
import org.w3c.css.selectors.Selector;
import org.w3c.css.util.ApplContext;

/**
 * AttributeExact<br />
 * Created: Sep 1, 2005 4:22:42 PM<br />
 */
public class AttributeExact extends AttributeSelector {

    private String value;

    public AttributeExact(String name, String value) {
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
	    // [lang=fr][lang]
	    return true;
	} else if (other instanceof AttributeExact ||
		other instanceof AttributeOneOf ||
		other instanceof AttributeBegin) {
	    if (!value.equals(((AttributeExact) other).getValue())) {
		// [lang=fr][lang=en]
		return false;
	    } else {
		// [lang=en][lang=en]
		return true;
	    }
	}
	return false;
    }

    public void applyAttribute(ApplContext ac, AttributeSelector attr) {
	if (attr instanceof AttributeExact) {
	    if (!value.equals(((AttributeExact) attr).getValue())) {
		ac.getFrame().addWarning("incompatible", toString(), attr.toString());
	    }
	}
	else if(attr instanceof AttributeOneOf) {
	    if (!value.equals(((AttributeOneOf) attr).getValue())) {
		ac.getFrame().addWarning("incompatible", toString(), attr.toString());
	    }
	}
	else if(attr instanceof AttributeBegin) {
	    if (!value.equals(((AttributeBegin) attr).getValue())) {
		ac.getFrame().addWarning("incompatible", toString(), attr.toString());
	    }
	}
    }

    public String toString() {
	return "[" + getName() + "=\"" + value + "\"]";
    }

}
