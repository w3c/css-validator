/*
 * Copyright (c) 2001 World Wide Web Consortium,
 * (Massachusetts Institute of Technology, Institut National de
 * Recherche en Informatique et en Automatique, Keio University). All
 * Rights Reserved. This program is distributed under the W3C's Software
 * Intellectual Property License. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.
 * See W3C License http://www.w3.org/Consortium/Legal/ for more details.
 *
 * $Id$
 */
package org.w3c.css.properties;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssColor;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

public class TextShadowFace implements CssOperator {

    CssColor color;
    CssLength[] lengths;

    CssValue val;
    char op;

    TextShadowFace(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {
	
	if(check && expression.getCount() > 4) {
	    throw new InvalidParamException("unrecognize", ac);
	}	
	
	val = expression.getValue();
	op = expression.getOperator();

	if (val instanceof CssColor) {
	    color = (CssColor) val;
	    expression.next();
	    if (op == SPACE) {
		getLengths(ac, expression);
	    }
	} else if (val instanceof CssIdent) {
	    color = new CssColor(ac, (String) val.get());
	    expression.next();
	    if (op == SPACE) {
		getLengths(ac, expression);
	    }
	} else if (val instanceof CssLength || val instanceof CssNumber) {
	    getLengths(ac, expression);	    
	    val = expression.getValue();
	    if (val instanceof CssColor) {
		color = (CssColor) val;
		op = expression.getOperator();
		expression.next();
	    } else if (val instanceof CssIdent) {
		color = new CssColor(ac, (String) val.get());
		op = expression.getOperator();
		expression.next();
	    }
	} else {
	    throw new InvalidParamException("value", expression.getValue(), 
					    "text-shadow", ac);
	}
    }

    TextShadowFace(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);	
    }
    
    void getLengths(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssLength le = getLength(expression.getValue());
	op = expression.getOperator();

 	if (le != null) {
	    lengths = new CssLength[3];
	    lengths[0] = le;
	    expression.next();
	    le = getLength(expression.getValue());
	    if ((op == SPACE) && (le != null)) {
		lengths[1] = le;
		op = expression.getOperator();
		expression.next();		
		le = getLength(expression.getValue());
		if ((op == SPACE) && (le != null)) {
		    lengths[2] = le;
		    op = expression.getOperator();
		    expression.next();
		}
	    } else {
		throw new InvalidParamException("two-lengths", 
						expression.getValue(),
						"text-shadow", ac);
	    }
	}
    }


    CssLength getLength(CssValue val) throws InvalidParamException {
	if (val instanceof CssLength) {
	    return (CssLength) val;
	} else if (val instanceof CssNumber) {
	    return ((CssNumber) val).getLength();
	} else {
	    return null;
	}
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	String ret = "";
        if (color != null) {
	    ret += " " + color;
	}
	if (lengths != null) {
	    ret += " " + lengths[0] + " " + lengths[1];
	    if (lengths[2] != null) {
		ret += " " + lengths[2];
	    }
	}
	return ret.substring(1);
    }

}
