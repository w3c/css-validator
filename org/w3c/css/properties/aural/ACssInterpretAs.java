//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2005/08/08 13:18:03  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.1  2003/07/30 06:34:52  sijtsche
 * new speech property
 *
 * Revision 1.2  2002/04/08 21:16:56  plehegar
 * New
 *
 * Revision 2.1  1997/08/29 13:11:50  plehegar
 * Updated
 *
 * Revision 1.6  1997/08/25 13:52:36  plehegar
 * Added getValue()
 *
 * Revision 1.5  1997/08/22 15:00:35  plehegar
 * Bugs
 *
 * Revision 1.4  1997/08/22 14:58:25  plehegar
 * Added getPropertyInStyle()
 *
 * Revision 1.3  1997/08/21 21:13:38  plehegar
 * Added time
 *
 * Revision 1.2  1997/08/21 14:34:19  vmallet
 * Minor modifications so we could compile it.
 *
 * Revision 1.1  1997/08/14 12:58:48  plehegar
 * Initial revision
 *
 */

package org.w3c.css.properties.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssDate;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTime;
import org.w3c.css.values.CssValue;

/**

 *
 * @version $Revision$
 */
public class ACssInterpretAs extends ACssProperty {

    CssValue value;

    private static CssTime defaultValue;

	private static String[] interpretas = { "currency", "measure", "telephone", "address", "name", "net" };

    /**
     * Create a new ACssInterpretAs
     */
    public ACssInterpretAs() {
	// Initial is User Agent Specific
	if (defaultValue == null) {
	    defaultValue = new CssTime(ACssProperties.getValue(this, "default"));
	}
	value = defaultValue;
    }

    /**
     * Creates a new ACssInterpretAs
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */
    public ACssInterpretAs(ApplContext ac, CssExpression expression, 
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	
	setByUser();
	
	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	} else if (val instanceof CssTime) {
	    float num = ((Float) val.get()).floatValue();
	    if (num < 0) {
		throw new InvalidParamException("negative-value",
			val.toString(), ac);
	    }
	    value = val;
	    expression.next();
	    return;
	} else if (val instanceof CssDate) {
	    value = val;
	    expression.next();
	    return;
	} else if (val instanceof CssIdent) {
	    for (int i=0; i < interpretas.length; i++) {
		if (val.toString().equals(interpretas[i])) {
		    value = val;
		    expression.next();
		    return;
		}
	    }
	}
	
	throw new InvalidParamException("value", val.toString(),
		getPropertyName(), ac);
    }
    
    public ACssInterpretAs(ApplContext ac, CssExpression expression)
    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the current value
     */
    public Object get() {
	return value;
    }

    /**
     * Returns some usable value of this property...
     */
    public int getValue() { // vm
	return ((Float) value.get()).intValue();
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (value != null)
	    return value.toString();
	else
	    return null;
    }


    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "interpret-as";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((ACssStyle) style).acssInterpretAs != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssInterpretAs = this;
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	if (value != null) {
	    return (property instanceof ACssInterpretAs &&
		    value.equals(((ACssInterpretAs) property).value));
	} else {
	    return false;
	}
    }
    
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getInterpretAs();
	} else {
	    return ((ACssStyle) style).acssInterpretAs;
	}
    }

}
