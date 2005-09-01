// $Id$
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css21;

import org.w3c.css.properties.css1.CssBackgroundAttachmentCSS2;
import org.w3c.css.properties.css1.CssBackgroundCSS2;
import org.w3c.css.properties.css1.CssBackgroundImageCSS2;
import org.w3c.css.properties.css1.CssBackgroundRepeatCSS2;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * CssBackgroundCSS21<br />
 * Created: Aug 31, 2005 2:03:41 PM<br />
 */
public class CssBackgroundCSS21 extends CssBackgroundCSS2 {
    /**
     * Create a new CssBackgroundCSS2
     */
    public CssBackgroundCSS21() {
	super();
    }  
    
    /**
     * Set the value of the property
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */  
    public CssBackgroundCSS21(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {			

	CssValue val = expression.getValue();
	char op = SPACE;
	boolean find = true;
	
	// too many values
	if(check && expression.getCount() > 6) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	setByUser();

	boolean manyValues = (expression.getCount() > 1);
	
	while (find) {
	    find = false;
	    val = expression.getValue();
	    op = expression.getOperator();
	    
	    if (val == null) {
		break;
	    }
	    
	    // if there are many values, we can't have inherit as one of them
	    if(manyValues && val != null && val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", null, null, ac);
	    }
	    
	    if (getColor2() == null) {
		try {
		    setColor(new CssBackgroundColorCSS21(ac, expression));
		    find = true;
		} catch (InvalidParamException e) {
		    // nothing to do, image will test this value
		}
	    }
	    if (!find && getImage() == null) {
		try {
		    setImage(new CssBackgroundImageCSS2(ac, expression));
		    find = true;
		} catch (InvalidParamException e) {
		    // nothing to do, repeat will test this value
		}
	    }
	    if (!find && getRepeat() == null) {
		try {		    
		    setRepeat(new CssBackgroundRepeatCSS2(ac, expression));		    
		    find = true;
		} catch (InvalidParamException e) {		    
		    // nothing to do, attachment will test this value
		}
	    }
	    if (!find && getAttachment() == null) {
		try {
		    setAttachment(new CssBackgroundAttachmentCSS2(ac, expression));
		    find = true;
		} catch (InvalidParamException e) {
		    // nothing to do, position will test this value
		}
	    }
	    if (!find && getPosition() == null) {
		try {
		    setPosition(new CssBackgroundPositionCSS21(ac, expression));
		    find = true;
		} catch (InvalidParamException e) {
		    // nothing to do
		}
	    }
	    if(check && val != null && !find) {		
		throw new InvalidParamException("unrecognize", ac);
	    }
	    if (op != SPACE) {
		throw new InvalidParamException("operator", 
						((new Character(op)).toString()),
						ac);
	    }
	    if(check && !find && val != null) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	}

    }
    
    public CssBackgroundCSS21(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	this(ac, expression, false);
    }
}
