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
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class PageBreakInsideATSC extends CssProperty
    implements CssOperator {

    int value;

    private static String PAGEBREAKINSIDE[] = {
	"auto", "avoid", "inherit" };

    private static int[] hash_values;


    /**
     * Create a new CssPageBreakInsideATSC
     */
    public PageBreakInsideATSC() {
	value = 0;
    }

    /**
     * Create a new CssPageBreakInsideATSC
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public PageBreakInsideATSC(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	//char op = expression.getOperator();

	setByUser();

	ac.getFrame().addWarning("atsc", val.toString());

	if (val instanceof CssIdent) {
	    int hash = val.hashCode();

	    for (int i = 0; i < PAGEBREAKINSIDE.length; i++) {
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

    public PageBreakInsideATSC(ApplContext ac, CssExpression expression)
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
	return "page-break-inside";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == PAGEBREAKINSIDE.length - 1;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return PAGEBREAKINSIDE[value];
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.pageBreakInsideATSC != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.pageBreakInsideATSC = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getPageBreakInsideATSC();
	} else {
	    return ((Css2Style) style).pageBreakInsideATSC;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof PageBreakInsideATSC
		&& value == ((PageBreakInsideATSC) property).value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == 0;
    }

    static {
	hash_values = new int[PAGEBREAKINSIDE.length];
	for (int i=0; i<PAGEBREAKINSIDE.length; i++)
	    hash_values[i] = PAGEBREAKINSIDE[i].hashCode();
    }
}
