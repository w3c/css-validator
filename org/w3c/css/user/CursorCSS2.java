//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.user;
import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class CursorCSS2 extends CssProperty 
    implements CssOperator {
    
    int value;
    Vector uris = new Vector();
    boolean inheritedValue;
    
    private static String CURSOR[] = {
	"auto", "crosshair", "default", "pointer", "move", "e-resize", 
	"ne-resize", "nw-resize", "n-resize", "se-resize", "sw-resize", 
	"s-resize", "w-resize", "text", "wait", "help" };
    
    private static int[] hash_values;
    
    
    /**
     * Create a new Cursor
     */
    public CursorCSS2() {
	value = 0;
    }
    
    /**
     * Create a new Cursor
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CursorCSS2(ApplContext ac, CssExpression expression, boolean check) 
	throws InvalidParamException {
	CssValue val = expression.getValue();
	char op = expression.getOperator();
	
	setByUser();
	
	if (val.equals(inherit)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    inheritedValue = true;
	    expression.next();
	    return;
	}
	
	while ((op == COMMA) && (val instanceof CssURL)) {
	    if(val != null && val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    uris.addElement(val);
	    expression.next();
	    val = expression.getValue();
	    op = expression.getOperator();
	} 
	if (val instanceof CssURL) {
	    throw new InvalidParamException("comma", 
					    val.toString(), 
					    getPropertyName(), ac);
	}
	
	if (val instanceof CssIdent) {
	    int hash = val.hashCode();
	    
	    for (int i = 0; i < CURSOR.length; i++) {
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    if(check && !expression.end()) {
			throw new InvalidParamException("unrecognize", ac);
		    }
		    return;
		}
	    }
	}
	
	throw new InvalidParamException("value", 
					val.toString(), getPropertyName(), ac);
    }
    
    public CursorCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return null;
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "cursor";
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return inheritedValue;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (inheritedValue) {
	    return inherit.toString();
	} else {
	    int i = 0;
	    int l = uris.size();
	    String ret = "";
	    while (i != l) {
		ret += uris.elementAt(i++) + 
		    (new Character(COMMA)).toString() + " ";
	    }
	    ret += " " + CURSOR[value];
	    return ret;
	}
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.cursorCSS2 != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cursorCSS2 = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getCursorCSS2();
	} else {
	    return ((Css2Style) style).cursor;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof Cursor 
		&& value == ((Cursor) property).value);
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == 0;
    }
    
    static {
	hash_values = new int[CURSOR.length];
	for (int i=0; i<CURSOR.length; i++)
	    hash_values[i] = CURSOR[i].hashCode();
    }
}
