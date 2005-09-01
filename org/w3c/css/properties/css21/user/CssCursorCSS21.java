// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21.user;

import org.w3c.css.properties.css2.user.CursorCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;

/**
 * CssCursorCSS21<br />
 * Created: Aug 30, 2005 4:47:16 PM<br />
 */
public class CssCursorCSS21 extends CursorCSS2 {
    
    private static String CURSOR[] = {
	"auto", "crosshair", "default", "pointer", "move", "e-resize", 
	"ne-resize", "nw-resize", "n-resize", "se-resize", "sw-resize", 
	"s-resize", "w-resize", "text", "wait", "help", "progress" };
    
    private static int[] hash_values;
       
    /**
     * Create a new Cursor
     */
    public CssCursorCSS21() {
	super();
    }
    
    /**
     * Create a new Cursor
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssCursorCSS21(ApplContext ac, CssExpression expression, boolean check) 
	throws InvalidParamException {
	CssValue val = expression.getValue();
	char op = expression.getOperator();
	
	setByUser();
	
	if (val.equals(inherit)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    setInheritedValue(true);
	    expression.next();
	    return;
	}
	
	while ((op == COMMA) && (val instanceof CssURL)) {
	    if(val != null && val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    getUris().addElement(val);
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
		    setValue(i);
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
    
    public CssCursorCSS21(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (isInheritedValue()) {
	    return inherit.toString();
	} else {
	    int i = 0;
	    int l = getUris().size();
	    String ret = "";
	    while (i != l) {
		ret += getUris().elementAt(i++) + 
		    (new Character(COMMA)).toString() + " ";
	    }
	    ret += " " + CURSOR[getValue()];
	    return ret;
	}
    }
    
    static {
	hash_values = new int[CURSOR.length];
	for (int i=0; i<CURSOR.length; i++)
	    hash_values[i] = CURSOR[i].hashCode();
    }
}
