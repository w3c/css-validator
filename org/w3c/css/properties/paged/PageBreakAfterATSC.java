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
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class PageBreakAfterATSC extends CssProperty
    implements CssOperator {

    int value;

    private static String PAGEBREAKAFTER[] = {
	"auto", "always", "avoid", "left", "right", "inherit" };

    private static int[] hash_values;


    /**
     * Create a new CssPageBreakAfterATSC
     */
    public PageBreakAfterATSC() {
	value = 0;
    }

    /**
     * Create a new CssPageBreakAfterATSC
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public PageBreakAfterATSC(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
//	char op = expression.getOperator();

	setByUser();

	ac.getFrame().addWarning("atsc", val.toString());

	if (val instanceof CssIdent) {
	    int hash = val.hashCode();

	    for (int i = 0; i < PAGEBREAKAFTER.length; i++) {
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	    }
	}

	throw new InvalidParamException("value",
					val.toString(), getPropertyName(), ac);
    }

    public PageBreakAfterATSC(ApplContext ac, CssExpression expression)
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
	return "page-break-after";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == PAGEBREAKAFTER.length - 1;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return PAGEBREAKAFTER[value];
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.pageBreakAfterATSC != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.pageBreakAfterATSC = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getPageBreakAfterATSC();
	} else {
	    return ((Css2Style) style).pageBreakAfterATSC;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof PageBreakAfterATSC
		&& value == ((PageBreakAfterATSC) property).value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == 0;
    }

    static {
	hash_values = new int[PAGEBREAKAFTER.length];
	for (int i=0; i<PAGEBREAKAFTER.length; i++)
	    hash_values[i] = PAGEBREAKAFTER[i].hashCode();
    }
}
