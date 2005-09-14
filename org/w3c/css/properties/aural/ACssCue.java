//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.aural;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;


/**
 * &nbsp;&nbsp;  'cue'
 *
 * <P>
 * <EM>Value: </EM> &lt;'cue-before'&gt; || &lt;'cue-before'&gt;<BR>
 * <EM>Initial:</EM> none<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentage values:</EM> NA
 *
 * <P>Auditory icons are another way to distinguish semantic
 * elements. Sounds may be played before, and/or after the element to
 * delimit it. The same sound can be used both before and after, using the
 * shorthand 'cue' property.
 *
 * <p> Examples:
 * <PRE>
 *   A {cue-before: url(bell.aiff); cue-after: url(dong.wav) }
 *   H1 {cue-before: url(pop.au); cue-after: url(pop.au) }
 *   H1 {cue: url(pop.au) }  / * same as previous * /
 * </pre>
 *
 * <p class=comment>The <tt>:before</tt> and <tt>:after</tt>
 * pseudo-elements (see frostings document) could be used to generate
 * this content, rather than using two special-purpose properties. This
 * would be more general.</p>
 *
 * @version $Revision$
 */
public class ACssCue extends ACssProperty implements CssOperator {

    ACssCueBefore cueBefore;
    ACssCueAfter cueAfter;

    boolean same;

    /**
     * Create a new ACssCue
     */
    public ACssCue() {
    }

    /**
     * Create a new ACssCue
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public ACssCue(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {
	switch (expression.getCount()) {
	case 1:
	    same = true;
	    cueBefore = new ACssCueBefore(ac, expression);
	    cueAfter = new ACssCueAfter(cueBefore);
	    break;
	case 2:
	    if (expression.getOperator() != SPACE) {
		throw new InvalidParamException("operator",
			(new Character(expression.getOperator()).toString()),
			ac);
	    }
	    if(check && expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    cueBefore = new ACssCueBefore(ac, expression);
	    if(check && expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    cueAfter = new ACssCueAfter(ac, expression);
	    break;
	default:
	    if(check) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	}
    }

    public ACssCue(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return cueBefore;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "cue";
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	// cueBefore and cueAfter must be not null !
	if (same) {
	    return cueBefore.toString();
	} else {
	    return cueBefore + " " + cueAfter;
	}
    }

    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */
    public void setImportant() {
	super.setImportant();
	cueBefore.setImportant();
	cueAfter.setImportant();
    }

    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return ((cueAfter == null || cueAfter.getImportant()) &&
		(cueBefore == null || cueBefore.getImportant()));
    }

    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */
    public void print(CssPrinterStyle printer) {
	if ((cueBefore != null && cueAfter != null) &&
	    (getImportant() ||
	     (!cueBefore.getImportant() &&
	      !cueAfter.getImportant()))) {
	    printer.print(this);
	} else {
	    if (cueBefore != null)
		cueBefore.print(printer);
	    if (cueAfter != null && !same)
		cueAfter.print(printer);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	// cueBefore and cueAfter can't be null ...
	((ACssStyle) style).acssCue.same = same;
	cueBefore.addToStyle(ac, style);
	cueAfter.addToStyle(ac, style);
    }

    /**
     * Update the source file and the line.
     * Overrides this method for a macro
     *
     * @param line The line number where this property is defined
     * @param source The source file where this property is defined
     */
    public void setInfo(int line, String source) {
	super.setInfo(line, source);
	// cueBefore and cueAfter can't be null ...
	cueBefore.setInfo(line, source);
	cueAfter.setInfo(line, source);
    }

    /**
     * Set the context.
     * Overrides this method for a macro
     *
     * @see org.w3c.css.css.CssCascadingOrder#order
     * @see org.w3c.css.css.StyleSheetParser#handleRule
     */
    public void setSelectors(CssSelectors selector) {
	super.setSelectors(selector);
	if (cueBefore != null) {
	    cueBefore.setSelectors(selector);
	}
	if (cueAfter != null) {
	    cueAfter.setSelectors(selector);
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return false; // ????
    }


    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getCue();
	} else {
	    return ((ACssStyle) style).acssCue;
	}
    }

}
