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
public class Marks extends CssProperty
        implements CssOperator {

    CssValue l1, l2;

    private static CssIdent none = new CssIdent("none");
    private static CssIdent crop = new CssIdent("crop");
    private static CssIdent cross = new CssIdent("cross");

    /**
     * Create a new CssMarks
     */
    public Marks() {
	l1 = none;
    }

    /**
     * Create a new CssMarks
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public Marks(ApplContext ac, CssExpression expression, boolean check)
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
	} else if (val.equals(none)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    l1 = none;
	    expression.next();
	    return;
	} else if (val.equals(crop)) {
	    l1 = crop;
	    expression.next();
	    if (!expression.end()) {
		val = expression.getValue();
		if ((op == SPACE) && (val.equals(cross))) {
		    l2 = cross;
		    expression.next();
		    return;
		} else {
		    throw new InvalidParamException("value",
						    val.toString(),
						    getPropertyName(), ac);
		}
	    }
	    return;
	} else if (val.equals(cross)) {
	    l1 = cross;
	    expression.next();
	    if (!expression.end()) {
		val = expression.getValue();
		if ((op == SPACE) && (val.equals(crop))) {
		    l2 = crop;
		    expression.next();
		    return;
		} else {
		    throw new InvalidParamException("value",
						    val.toString(),
						    getPropertyName(), ac);
		}
	    }
	    return;
	}

	throw new InvalidParamException("value",
					val.toString(), getPropertyName(), ac);
    }

    public Marks(ApplContext ac, CssExpression expression)
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
	return "marks";
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
	if (style0.marks != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.marks = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getMarks();
	} else {
	    return ((Css2Style) style).marks;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof Marks
		&& l1.equals(((Marks) property).l1)
		    || l2.equals(((Marks) property).l2));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return l1 == none;
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
