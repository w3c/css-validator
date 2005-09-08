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
import org.w3c.css.values.CssValue;


/**
 *  &nbsp;&nbsp; 'pause'
 *
 * <P>
 * <EM>Value: </EM>[&lt;timee&gt; | &lt;percentage&gt; ]{1,2};<BR>
 * <EM>Initial:</EM> UA specific<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentage values:</EM> NA<BR>
 *
 * <P>The 'pause' property is a shorthand for setting 'pause-before' and
 * 'pause-after'.  If two values are given, the first value is
 * pause-before and the second is pause-after. If only one value is
 * given, it applies to both properties.
 *
 *
 * <P>
 * Examples:
 * <PRE>
 *   H1 { pause: 20ms } /* pause-before: 20ms; pause-after: 20ms * /
 *   H2 { pause: 30ms 40ms } /* pause-before: 30ms; pause-after: 40ms * /
 *   H3 { pause-after: 10ms } /* pause-before: ?; pause-after: 10ms * /
 * </PRE>
 *
 *
 * @version $Revision$
 */
public class ACssPause extends ACssProperty implements CssOperator {
    
    ACssPauseBefore pauseBefore;
    ACssPauseAfter pauseAfter;

    boolean same;
    
    /**
     * Create a new ACssPause
     */
    public ACssPause() {
    }  
    
    /**
     * Create a new ACssPause
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public ACssPause(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {
	CssValue val = expression.getValue();

	if (val.equals(inherit)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    this.same = true;
	    pauseBefore = new ACssPauseBefore(ac, expression);
	    pauseAfter = new ACssPauseAfter(pauseBefore);
	    return;
	}
	
	switch (expression.getCount()) {
	case 1:
	    this.same = true;
	    pauseBefore = new ACssPauseBefore(ac, expression);
	    pauseAfter = new ACssPauseAfter(pauseBefore);
	    break;
	case 2:
	    if (expression.getOperator() != SPACE) {
		throw new InvalidParamException("operator", 
						(new Character(expression.getOperator()).toString()),
						ac);
	    }
	    pauseBefore = new ACssPauseBefore(ac, expression);
	    pauseAfter = new ACssPauseAfter(ac, expression);
	    break;
	default:
	    if(check) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	}
    }
    
    public ACssPause(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return pauseBefore;
    }
    
    /**
     * Get the before property
     */
    public ACssPauseBefore getBefore() {
	return pauseBefore;
    }
    
    /**
     * Get the after property
     */
    public ACssPauseAfter getAfter() {
	return pauseAfter;
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "pause";
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	// pauseBefore and pauseAfter must be not null !
	if (same) {
	    return pauseBefore.toString();
	} else {
	    return pauseBefore + " " + pauseAfter;
	}
    }
    
    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */  
    public void setImportant() {
	super.setImportant();
	pauseBefore.setImportant();
	pauseAfter.setImportant();
    }
    
    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return ((pauseAfter == null || pauseAfter.getImportant()) &&
		(pauseBefore == null || pauseBefore.getImportant()));
    }
    
    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */  
    public void print(CssPrinterStyle printer) {
	if ((pauseBefore != null && pauseAfter != null) &&
	    (getImportant() ||
	     (!pauseBefore.getImportant() &&
	      !pauseAfter.getImportant()))) {
	    printer.print(this);
	} else {	
	if (pauseBefore != null) {
		pauseBefore.print(printer);
	    }
	    if (pauseAfter != null && !same) {
		pauseAfter.print(printer);
	    }
	}
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	// pauseBefore and pauseAfter can't be null ...
	((ACssStyle) style).acssPause.same = same;
	pauseBefore.addToStyle(ac, style);
	pauseAfter.addToStyle(ac, style);
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
	// pauseBefore and pauseAfter can't be null ...
	pauseBefore.setInfo(line, source);
	pauseAfter.setInfo(line, source);
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return false; // @FIXME
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
	if (pauseBefore != null) {
	    pauseBefore.setSelectors(selector);
	}
	if (pauseAfter != null) {
	    pauseAfter.setSelectors(selector);
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
	    return ((ACssStyle) style).getPause();
	} else {
	    return ((ACssStyle) style).acssPause;
	}
    }
    
}
