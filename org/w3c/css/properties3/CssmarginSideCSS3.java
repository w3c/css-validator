//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 2.2  1997/08/20 11:41:26  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:28  plehegar
 * Nothing
 *
 * Revision 1.6  1997/08/06 17:30:12  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.5  1997/07/30 13:20:12  plehegar
 * Updated package
 *
 * Revision 1.4  1997/07/24 01:17:28  plehegar
 * Changed an exported status
 *
 * Revision 1.3  1997/07/24 01:06:55  plehegar
 * Added some stuffs
 *
 * Revision 1.2  1997/07/23 23:52:50  plehegar
 * bug (extends)
 *
 * Revision 1.1  1997/07/23 23:51:30  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties3;

import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssIdent;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties.CssProperty;

/**
 * @version $Revision$
 */
public abstract class CssMarginSideCSS3 extends CssProperty {

    CssValue value;
    private static CssIdent auto = new CssIdent("auto");
	private static CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssMarginSideCSS3
     */
    public CssMarginSideCSS3() {
	value = new CssLength();
    }

    /**
     * Create a new CssMarginSideCSS3 with an another CssMarginSideCSS3
     *
     * @param another The another SideCSS3.
     */
    public CssMarginSideCSS3(CssMarginSideCSS3 another) {
	value = another.value;
    }

    /**
     * Create a new CssMarginSideCSS3
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssMarginSideCSS3(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = expression.getValue();

	setByUser();

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	} else if (val instanceof CssLength || val instanceof CssPercentage) {
	    value = val;
	    expression.next();
	} else if (val instanceof CssNumber) {
	    value = ((CssNumber) val).getLength();
	    expression.next();
	} else if (val.equals(auto)) {
	    value = auto;
	    expression.next();
	} else if (val.equals(initial)) {
	    value = initial;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", val.toString(),
					    getPropertyName(), ac);
	}
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }

    /**
     * Returns the internal CssValue value.
     */
    public CssValue getValue() {
	return value;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value.toString();
    }

    /**
     * Compares two SideCSS3s for equality.
     *
     * @param SideCSS3 The other SideCSS3.
     */
    public boolean equals(CssMarginSideCSS3 SideCSS3) {
	return value.equals(SideCSS3.value);
    }

    /**
     * Is this property contains a default value.
     */
    public boolean isDefault() {
	if (value != auto)
	    return ((Float) value.get()).floatValue() == 0;
	else
	    return false;
    }


}
