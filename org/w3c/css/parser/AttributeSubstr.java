// Made by Sijtsche de Jong

package org.w3c.css.parser;

/**
 * E[foo*="bar"]
 */
public class AttributeSubstr extends Attribute implements CssSelectorsConstant {

    String value;

    public boolean isId() {
	return getName().equals("id");
    }

    final AttributeSubstr setValue(String value) {
	this.value = value;
	return this;
    }
    
    public final String getValue() {
	return value;
    }

    /* Sorry Philippe, without documentation
       I don't know what this method should do*/
    public Attribute applyAttribute(Attribute attr) 
	throws AttributeException {
	return this;
    }

    public boolean canApply(Attribute attr) {
	return true;
    }

    public String toString() {
	return "[" + getName() + "*=" + getValue() + "]";
    }

}
