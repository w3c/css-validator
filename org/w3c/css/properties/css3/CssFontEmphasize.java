//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 * <P>
 * <EM>Value:</EM> &lt;font-emphasize-style&gt; || &lt;font-emphasize-position&gt; || inherit<BR>
 * <EM>Initial:</EM>see individual properties<BR>
 * <EM>Applies to:</EM>all elements or replaced elements??<BR>
 * <EM>Inherited</EM>yes<BR>
 * <EM>Percentages:</EM>no<BR>
 * <EM>Media:</EM>visual
 * <P>
 * This is a shorthand property for setting the style and position of the font emphasis decoration. 
 */

public class CssFontEmphasize extends CssProperty 
implements CssOperator {
    
    CssValue fontemph;
    CssFontEmphasizePos fep;
    CssFontEmphasizeStyle fes;

    /**
     * Creates a new CssFontEmphasize
     */
    public CssFontEmphasize() {	
    }
    
    /**
     * Creates a new CssFontEmphasize
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssFontEmphasize(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	CssValue val = expression.getValue();
	int maxvalues = 2;
	boolean correct = true;
	char op = SPACE;

	while (correct && (val != null) && (maxvalues-- > 0)) {
	    
	    correct = false;

	    if (fep == null) {
		try {
		    fep = new CssFontEmphasizePos(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (fes == null) {
		try {
		    fes = new CssFontEmphasizeStyle(ac, expression);
		    correct = true;
		}
		catch (InvalidParamException e) {
		}
	    }
	    if (!correct) {
		throw new InvalidParamException("value", expression.getValue(),
						getPropertyName(), ac);
	    }
	    
	    val = expression.getValue();
	    op = expression.getOperator();

	}

    }

    public CssFontEmphasize(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssFontEmphasize != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssFontEmphasize = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getFontEmphasize();
	}
	else {
	    return ((Css3Style) style).cssFontEmphasize;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return false;
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "font-emphasize";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return null;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    //    public boolean isSoftlyInherited() {
    //	return fontemph.equals(inherit);
    //}

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	String ret = "";
	if (fes.isByUser()) {
	    ret += " " + fes;
	}
	if (fep.isByUser()) {
	    ret += " " + fep;
	}
	return ret.substring(1);
    }

    /*
       Is the value of this property a default value
       It is used by alle macro for the function <code>print</code>
     
       public boolean isDefault() {
       return fontemph == none;
       }
    */
}
