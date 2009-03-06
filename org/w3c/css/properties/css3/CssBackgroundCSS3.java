// $Id$
// Author: Jean-Guilhem Rouel
// Revised by: Yves Lafon
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005-2008.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.properties.css1.CssBackgroundAttachmentCSS2;
import org.w3c.css.properties.css21.CssBackgroundCSS21;
import org.w3c.css.properties.css1.CssBackgroundImageCSS2;
import org.w3c.css.properties.css1.CssBackgroundRepeatCSS2;
import org.w3c.css.properties.css21.CssBackgroundPositionCSS21;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssString;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssURL;

/**
 * CssBackgroundCSS21<br />
 * Created: Aug 31, 2005 2:03:41 PM<br />
 */
public class CssBackgroundCSS3 extends CssBackgroundCSS21 {
    /**
     * Create a new CssBackgroundCSS2
     */
    public CssBackgroundCSS3() {
	super();
    }

    /**
     * Set the value of the property
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssBackgroundCSS3(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	CssValue val;
	char op = SPACE;
	boolean find = true;

	// too many values
	if(check && expression.getCount() > 6) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();

	boolean manyValues = (expression.getCount() > 1);

	while (find) {
	    val = expression.getValue();
	    if (val == null) {
		break;
	    }
	    op = expression.getOperator();
	    
	    // if there are many values, we can't have inherit as one of them
	    if(manyValues && val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", null, null, ac);
	    }

	    switch (val.getType()) {
	    case CssTypes.CSS_STRING:
		if (check) {
		    throw new InvalidParamException("unrecognize", ac);
		}
		find = false;
		break;
	    case CssTypes.CSS_URL:
		if (getImage() == null) {
		    setImage(new CssBackgroundImageCSS2(ac, expression));
		    continue;
		}
		find = false;
		break;
	    case CssTypes.CSS_COLOR:
	    case CssTypes.CSS_FUNCTION:
		if (getColor2() == null) {
		    setColor(new CssBackgroundColorCSS3(ac, expression));
		    continue;
		}
		find = false;
		break;
	    case CssTypes.CSS_NUMBER:
	    case CssTypes.CSS_PERCENTAGE:
	    case CssTypes.CSS_LENGTH:
		if (getPosition() == null) {
		    setPosition(new CssBackgroundPositionCSS21(ac,expression));
		    continue;
		}
		find = false;
		break;
	    case CssTypes.CSS_IDENT:
		// the hard part, as ident can be from different subproperties
		find = false;
		CssIdent identval = (CssIdent) val;
		// check background-image ident
		if (CssBackgroundImageCSS2.checkMatchingIdent(identval)) {
		    if (getImage() == null) {
			setImage(new CssBackgroundImageCSS2(ac, expression));
			find = true;
		    } 
		    break;
		}
		// check background-repeat ident
		if (CssBackgroundRepeatCSS2.checkMatchingIdent(identval)) {
		    if (getRepeat() == null) {
			setRepeat(new CssBackgroundRepeatCSS2(ac, expression));
			find = true;
		    }
		    break;
		}
		// check background-attachment ident
		if (CssBackgroundAttachmentCSS2.checkMatchingIdent(identval)) {
		    if (getAttachment() == null) {
			setAttachment(new CssBackgroundAttachmentCSS2(ac, expression));
			find = true;
		    }
		    break;
		}		
		// check backgorund-position ident
		if (CssBackgroundPositionCSS21.checkMatchingIdent(identval)) {
		    if (getPosition() == null) {
			setPosition(new CssBackgroundPositionCSS21(ac, expression));
			find = true;
		    }
		    break;
		}

		if (getColor2() == null) {
		    try {
			setColor(new CssBackgroundColorCSS3(ac, expression));
			find = true;
			break;
		    } catch (InvalidParamException e) {
			// nothing to do, image will test this value
		    }
		}	

	    default:
		if (check) {
		    throw new InvalidParamException("unrecognize", ac);
		}
		find = false;
	    }
	    if(check && !find) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    if (op != SPACE) {
		throw new InvalidParamException("operator",
						Character.toString(op),
						ac);
	    }
	}
    }

    public CssBackgroundCSS3(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
}
