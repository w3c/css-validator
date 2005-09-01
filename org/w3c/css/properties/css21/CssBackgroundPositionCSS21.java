// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssBackgroundPositionCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 * CssBackgroundPositionCSS21<br />
 * Created: Aug 31, 2005 5:45:30 PM<br />
 */
public class CssBackgroundPositionCSS21 extends CssBackgroundPositionCSS2 {

    /**
     * Create a new CssBackgroundPositionCSS2
     */
    public CssBackgroundPositionCSS21() {
	super();
    }  
    
    /**
     * Creates a new CssBackgroundPositionCSS2
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssBackgroundPositionCSS21(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 2) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	setByUser();
	CssValue val = expression.getValue();
	char op  = expression.getOperator();
	
	if (op != SPACE)
	    throw new  InvalidParamException("operator", 
					     ((new Character(op)).toString()),
					     ac);
	
	if (val.equals(inherit)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    setFirst(inherit);
	    setSecond(inherit);
	    expression.next();
	    return;
	}
		
	CssValue next = expression.getNextValue();	

	if(val instanceof CssIdent) {	    
	    int index1 = IndexOfIdent((String) val.get());
	    if(index1 == -1) {
		throw new InvalidParamException("value", val, "background-position", ac);
	    }
	    // two keywords
	    if(next instanceof CssIdent) {		
		int index2 = IndexOfIdent((String) next.get());
		if(index2 == -1 && check) {
		    throw new InvalidParamException("value", next, "background-position", ac);
		}
		// one is vertical, the other is vertical
		// or the two are 'center'
		if((isHorizontal(index1) && isVertical(index2)) ||
			(isHorizontal(index2) && isVertical(index1))) {
		    setFirst(val);
		    setSecond(next);		    
		}
		// both are horizontal or vertical but not 'center'
		else if(check){		    
		    throw new InvalidParamException("incompatible",
			    val, next, ac);
		}
		else {
		    setFirst(val);
		}
	    }
	    // a keyword and a percentage/length
	    else if(next instanceof CssLength || next instanceof CssPercentage
		    || next instanceof CssNumber) {
		if(isHorizontal(index1)) {
		    if(next instanceof CssNumber) {
			next = ((CssNumber) next).getLength();
		    }
		    setFirst(val);
		    setSecond(next);
		}
		// if the keyword is the first value, it can only be an 
		// horizontal one 
		else {
		    throw new InvalidParamException("incompatible",
			    val, next, ac);
		}
	    }
	    // only one value
	    else if(next == null) {
		setFirst(val);
	    }
	    // the second value is invalid
	    else if(check) {		
		throw new InvalidParamException("value", next, 
			getPropertyName(), ac);
	    }
	    else {
		setFirst(val);
	    }
	}
	else if(val instanceof CssLength || val instanceof CssPercentage ||
		val instanceof CssNumber) {
	    if(val instanceof CssNumber) {
		val = ((CssNumber) val).getLength();
	    }
	    // a percentage/length and an keyword
	    if(next instanceof CssIdent) {
		int index = IndexOfIdent((String) next.get());
		if(check && index == -1) {
		    throw new InvalidParamException("value", next, "background-position", ac);
		}
		// the keyword must be a vertical one
		if(isVertical(index)) {
		    setFirst(val);
		    setSecond(next);
		}
		else if(check) {
		    throw new InvalidParamException("incompatible",
			    val, next, ac);
		}
		else {
		    setFirst(val);
		}
	    }
	    else if(next instanceof CssLength || next instanceof CssPercentage
		    || next instanceof CssNumber) {
		if(next instanceof CssNumber) {
		    next = ((CssNumber) next).getLength();
		}
		setFirst(val);
		setSecond(next);
	    }
	    else if(next == null || !check) {
		setFirst(val);
	    }
	    else {
		throw new InvalidParamException("incompatible", val, next, ac);
	    }
	}
	else if(check){
	    throw new InvalidParamException("value", expression.getValue(), 
		    getPropertyName(), ac);
	}
	
	// we only move the cursor if we found valid values
	if(getFirst() != null) {	    
	    expression.next();	    
	}
	if(getSecond() != null) {	    
	    expression.next();	    
	}	
    }
    
    public CssBackgroundPositionCSS21(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
}
