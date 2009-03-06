//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.css3;

import org.w3c.css.properties.css1.CssBackgroundColorCSS2;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.properties.css1.CssColor;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'background-color'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;color&gt; | transparent<BR>
 *   <EM>Initial:</EM> transparent<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property sets the background color of an element.
 *   <PRE>
 *   H1 { background-color: #F00 }
 *   </PRE>
 * @version $Revision$
 */
public class CssBackgroundColorCSS3 extends CssBackgroundColorCSS2 {

    /**
     * Create a new CssBackgroundColorCSS3
     */
    public CssBackgroundColorCSS3() {
	super();
    }

    /**
     * Create a new CssBackgroundColorCSS2
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBackgroundColorCSS3(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	CssColor tempcolor = new CssColor(ac, expression, check);
	setColor((CssValue) tempcolor.get());
    }

    public CssBackgroundColorCSS3(ApplContext ac, CssExpression expression)
	throws InvalidParamException 
    {
	this(ac, expression, false);
    }
}
