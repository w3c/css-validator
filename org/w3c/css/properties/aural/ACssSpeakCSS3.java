//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  &nbsp;&nbsp; 'speak'
 *
 * @version $Revision$
 */
public class ACssSpeakCSS3 extends CssProperty {

    CssValue value;

    private static int[] hash_values;

    private static String[] SPEAK = { "normal", "none", "spell-out", "digits", "literal-punctuation", "no-punctuation", "inherit" };

    private static CssIdent defaultValue = new CssIdent(SPEAK[1]);

    /**
     * Create a new ACssSpeakCSS3
     */
    public ACssSpeakCSS3() {
	value = defaultValue;
    }

    /**
     * Creates a new ACssSpeakCSS3
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public ACssSpeakCSS3(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this();

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	CssValue val = expression.getValue();
	int index;

	setByUser();

	if (val.equals(inherit)) {
	    value = inherit;
	} else if (val instanceof CssIdent) {
	    value = checkIdent(ac, (CssIdent) val);
	} else {
	    throw new InvalidParamException("value",
					    expression.getValue().toString(),
					    getPropertyName(), ac);
	}

	expression.next();
    }

    public ACssSpeakCSS3(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }


    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
		return "speak";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
		return value.equals(inherit);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
		return value.toString();
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((ACssStyle) style).acssSpeakCSS3 != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssSpeakCSS3 = this;
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
		return (property instanceof ACssSpeakCSS3 &&
			value.equals(((ACssSpeakCSS3) property).value));
    }

    private CssIdent checkIdent(ApplContext ac, CssIdent ident)
	throws InvalidParamException {

		int hash = ident.hashCode();
		for (int i = 0; i < SPEAK.length; i++) {
		    if (hash_values[i] == hash) {
				return ident;
		    }
		}

		throw new InvalidParamException("value", ident.toString(),
					getPropertyName(), ac);
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getSpeakCSS3();
	} else {
	    return ((ACssStyle) style).acssSpeakCSS3;
	}
    }

    static {
	hash_values = new int[SPEAK.length];
	for (int i = 0; i < SPEAK.length; i++)
	    hash_values[i] = SPEAK[i].hashCode();
    }
}

