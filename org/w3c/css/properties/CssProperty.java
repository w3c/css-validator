//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.3  2003/01/03 12:06:16  sijtsche
 * standard value initial added
 *
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 3.4  1997/09/10 14:36:15  plehegar
 * Added documentation
 *
 * Revision 3.3  1997/09/09 12:53:32  plehegar
 * Added final method
 *
 * Revision 3.2  1997/09/09 10:00:03  plehegar
 * Added getSourceFile() and getLine()
 *
 * Revision 1.1  1997/07/08 08:33:18  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.util.ApplContext;

import org.w3c.css.values.CssIdent;

import org.w3c.css.css.StyleSheetOrigin;

/**
 * <DL>
 * <DT>
 * <STRONG>property</STRONG>
 * <DD>
 * a stylistic parameter that can be influenced through CSS. This specification
 * defines a list of properties and their corresponding values.
 * </DL>
 *
 * If you want to add some properties to the parser, you should subclass this
 * class.
 *
 * @version $Revision$
 */
public abstract class CssProperty
        implements Cloneable, StyleSheetOrigin {

    /**
     * True if this property is important. false otherwise.
     */
    protected boolean important;

    /**
     * The origin of this property.
     * the author's style sheets override the reader's style sheet which
     * override the UA's default values. An imported style sheet has the same
     * origin as the style sheet from which it is imported.
     *
     * @see StyleSheetOrigin#BROWSER
     * @see StyleSheetOrigin#READER
     * @see StyleSheetOrigin#AUTHOR
     */
    protected int origin;

    /**
     * A uniq number for this property.
     * Used by the cascading order algorithm to sort by order specified.
     * If two rules have the same weight, the latter specified wins.
     */
    protected long order;

    /**
     * the position of the first character of this value.
     */
    protected int line;

    /**
     * the origin file.
     */
    protected String sourceFile;

    /**
     * the context.
     */
    protected CssSelectors context;

    // internal counter
    private static long increment;

    /** for validator only, true if the property comes from a file */
    boolean byUser = false;

    /**
     * This keyword is used a lot of time in CSS2
     */
    public static final CssIdent inherit = new CssIdent("inherit");

    /**
    * Value introduced in CSS3
    */
    public static final CssIdent initial = new CssIdent("initial");

    /**
     * Create a new CssProperty.
     */
    public CssProperty() {
	order = increment++;
    }

    /**
     * Returns true if the property is inherited.
     */
    public boolean Inherited() {
	return CssProperties.getInheritance(this);
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
	return false;
    }

    /**
     * Returns the value of this property.
     * It is not very usable, implements your own function.
     */
    public abstract Object get();

    /**
     * Returns the name of this property IN LOWER CASE.
     */
    public abstract String getPropertyName();

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public abstract boolean equals(CssProperty property);

    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */
    public void print(CssPrinterStyle printer) {
	if (byUser || Inherited() || important) {
	    // if (Inherited() || important) {
	    printer.print(this);
	}
    }

    /**
     * Returns a string representation of values.
     * <BR>
     * So if you want have something like this :
     * <SAMP> property-name : property-value1 properpty-value2 ...</SAMP>
     * <BR>
     * You should write something like this :
     *  <code>property.getPropertyName() + " : " + property.toString()</code>
     */
    public abstract String toString();

    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */
    public void setImportant() {
	important = true;
    }

    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return important;
    }

    /**
     * Calculate an hashCode for this property.
     */
    public final int hashCode() {
	return getPropertyName().hashCode();
    }

    /**
     * Update the source file and the line.
     * Overrides this method for a macro
     *
     * @param line The line number where this property is defined
     * @param source The source file where this property is defined
     */
    public void setInfo(int line, String source) {
	this.line = line;
	this.sourceFile = source;
    }

    /**
     * Fix the origin of this property
     * Overrides this method for a macro
     *
     * @see #BROWSER
     * @see #READER
     * @see #AUTHOR
     */
    public void setOrigin(int origin) {
	this.origin = origin;
    }

    /**
     * Returns the attribute origin
     *
     * @return the value of the attribute
     */
    public int getOrigin() {
        return origin;
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     *
     * @see #print(CssPrinterStyle)
     */
    public boolean isDefault() {
	return false;
    }

    /**
     * Set the context.
     * Overrides this method for a macro
     *
     * @see org.w3c.css.css.CssCascadingOrder#order
     * @see org.w3c.css.css.StyleSheetParser#handleRule
     */
    public void setSelectors(CssSelectors context) {
	this.context = context;
    }

    /**
     * Returns the context.
     *
     * @see org.w3c.css.css.CssCascadingOrder
     */
    public CssSelectors getSelectors() {
	return context;
    }

    /**
     * Duplicate this property.
     *
     * @see org.w3c.css.css.CssCascadingOrder#order
     */
    public CssProperty duplicate() {
	try {
	    return (CssProperty) clone();
	} catch (CloneNotSupportedException e) {
	    return null;
	}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public abstract void addToStyle(ApplContext ac, CssStyle style);

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public abstract CssProperty getPropertyInStyle(CssStyle style,
						   boolean resolve);

    /**
     * Returns the source file.
     */
    public final String getSourceFile() {
	return sourceFile;
    }

    /**
     * Returns the line number in the source file.
     */
    public final int getLine() {
	return line;
    }

    /**
     * Calculate the explicit weight and the origin.
     * Declarations marked '!important' carry more weight than unmarked
     * (normal) declarations.
     *
     * @see org.w3c.css.css.CssCascadingOrder
     */
    public final int getExplicitWeight() {
	// browser < reader < author < browser !important < reader !important
	//                                                < author !important
	// here, I use a little trick :
	//  1 < 2 < 3 < 4 ( 1 + 3 ) < 5 ( 2 + 3 ) < 6 ( 3 + 3 )
	return origin + ((important)?AUTHOR:0);
    }

    /**
     * Calculate the order specified.
     *
     * @see org.w3c.css.css.CssCascadingOrder
     * @see #order
     */
    public final long getOrderSpecified() {
	return order;
    }

    /**
     * Mark this property comes from the user
     *
     * @param byUser the new value for the attribute
     */
    public void setByUser() {
        byUser = true;
    }

    /**
     * Returns the attribute byUser
     *
     * @return the value of the attribute
     */
    public boolean isByUser() {
        return byUser;
    }

}




