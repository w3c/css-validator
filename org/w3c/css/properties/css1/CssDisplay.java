//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 14th 2000 Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.5  2005/08/08 13:18:12  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.4  2003/07/25 11:00:58  sijtsche
 * additional values for UI added
 *
 * Revision 1.3  2002/12/24 12:32:47  sijtsche
 * new values added
 *
 * Revision 1.2  2002/04/08 21:17:43  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:13:44  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:21  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:15  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:30:00  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:19:55  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/25 14:31:21  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *
 * @version $Revision$
 */
public class CssDisplay extends CssProperty {

    int value;

    private static String[] DISPLAY = {
		"none", "block", "inline", "list-item", "run-in", "compact", "table-row",
		"table-cell", "table-row-group", "table-header-group", "table-footer-group",
		"table-column", "table-column-group", "table-caption", "ruby-text", "ruby-base",
		"ruby-base-group", "ruby-text-group", "initial", "inherit", "inline-block", "icon"
    };

    private static int[] hash_values;

    /**
     * Create a new CssDisplay
     */
    public CssDisplay() {
	// nothing to do
    }

    /**
     * Create a new CssDisplay
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorect
     */
    public CssDisplay(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();

	setByUser();

	if ( val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < DISPLAY.length; i++) {
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	    }
	}

	throw new InvalidParamException("value", expression.getValue(),
					getPropertyName(), ac);
    }

    public CssDisplay(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
		return DISPLAY[value];
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
		return "display";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
		return value == DISPLAY.length - 1;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
		return DISPLAY[value];
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
		Css1Style style0 = (Css1Style) style;
		if (style0.cssDisplay != null)
		    style0.addRedefinitionWarning(ac, this);
		style0.cssDisplay = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
		if (resolve) {
		    return ((Css1Style) style).getDisplay();
		} else {
		    return ((Css1Style) style).cssDisplay;
		}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
		return (property instanceof CssDisplay &&
			value == ((CssDisplay) property).value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
		return value == 0;
    }

    static {
		hash_values = new int[DISPLAY.length];
		for (int i = 0; i < DISPLAY.length; i++)
		    hash_values[i] = DISPLAY[i].hashCode();
    }
}
