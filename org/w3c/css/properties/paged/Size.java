//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.paged;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class Size extends CssProperty
        implements CssOperator {

    CssValue l1, l2;

    private static CssIdent auto = new CssIdent("auto");
    private static CssIdent portrait = new CssIdent("portrait");
    private static CssIdent landscape = new CssIdent("landscape");

    /**
     * Create a new CssSize
     */
    public Size() {
	l1 = auto;
    }

    /**
     * Create a new CssSize
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public Size(ApplContext ac, CssExpression expression, boolean check)
	    throws InvalidParamException {

	if(check && expression.getCount() > 2) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	char op = expression.getOperator();

	setByUser();

	if (val.equals(inherit)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    l1 = inherit;
	    expression.next();
	    return;
	} else if (val.equals(auto)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    l1 = auto;
	    expression.next();
	    return;
	} else if (val.equals(portrait)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    l1 = portrait;
	    expression.next();
	    return;
	} else if (val.equals(landscape)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    l1 = landscape;
	    expression.next();
	    return;
	}

	val = getLength(val);
	if (val != null) {
	    l1 = val;
	    expression.next();
	    if (!expression.end()) {
		if(expression.getValue().equals(inherit)) {
		    throw new InvalidParamException("unrecognize", ac);
		}
		val = getLength(expression.getValue());
		if ((val == null)
		    || (op != SPACE)) {
		    throw new InvalidParamException("value",
						    val.toString(),
						    getPropertyName(), ac);
		} else {
		    l2 = val;
		    expression.next();
		    return;
		}
	    } else {
		expression.next();
		return;
	    }
	} else {
	    throw new InvalidParamException("value",
					    val.toString(), getPropertyName(), ac);
	}
    }

    public Size(ApplContext ac, CssExpression expression)
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
	return "size";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return l1 == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (l2 != null) {
	    return l1.toString() + " " + l2.toString();
	} else {
	    return l1.toString();
	}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.size != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.size = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getSize();
	} else {
	    return ((Css2Style) style).size;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof Size
		&& l1.equals(((Size) property).l1)
		&& ((l2 == null)
		    || l2.equals(((Size) property).l2)));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return l1 == auto;
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

}
