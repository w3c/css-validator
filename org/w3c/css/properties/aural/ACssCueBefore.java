//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.3  2005/08/08 13:18:03  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.2  2002/04/08 21:16:56  plehegar
 * New
 *
 * Revision 2.1  1997/08/29 13:11:50  plehegar
 * Updated
 *
 * Revision 1.1  1997/08/14 12:58:44  plehegar
 * Initial revision
 *
 */

package org.w3c.css.properties.aural;

import java.io.IOException;
import java.net.URL;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.HTTPURL;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;

/**
 * &nbsp;&nbsp;  'cue-before'
 *
 * <P>
 * <EM>Value: </EM> &lt;url&gt; | none<BR>
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
public class ACssCueBefore extends ACssProperty {
    
    CssValue value;
    
    private URL url;
    private static CssIdent none = new CssIdent("none");

    /**
     * Create a new ACssCueBefore
     */  
    public ACssCueBefore() {
	// Initial is User Agent Specific
	value = none;
    }
    
    /**
     * Create a new ACssCueBefore
     */  
    public ACssCueBefore(ACssCueAfter cueBefore) {
	value = cueBefore.value;
    }
    
    /**
     * Creates a new ACssCueBefore
     * @param value the value of the size
     * @exception InvalidParamException The value is incorrect
     */  
    public ACssCueBefore(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	
	if (val instanceof CssURL) {
	    value = val;
	    expression.next();
	    return;
	} else if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	} else if (val.equals(none)) {
	    value = none;
	    expression.next();
	    return;
	}
	
	throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
    }
    
    public ACssCueBefore(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	if (value == none)
	    return null;
	else
	    return value;
    }
    
    /**
     * Returns some usable value of this property...
     */
    public URL getValue() throws IOException { // vm
	if (value == none)
	    return null;
	else {
	    if (url == null) {
		url = HTTPURL.getURL(new URL(sourceFile), (String) value.get());
	    }
	    return url;
	}
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
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "cue-before";
    }
    
    public void addToStyle(ApplContext ac, CssStyle style) {
	ACssCue acssCue = ((ACssStyle) style).acssCue;
	if (acssCue.cueBefore != null)
	    style.addRedefinitionWarning(ac, this);
	acssCue.cueBefore = this;
    }
    
    public boolean equals(CssProperty property) {
	return (property instanceof ACssCueBefore && value.equals(((ACssCueBefore) property).value));
    }
    
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getCueBefore();
	} else {
	    return ((ACssStyle) style).acssCue.cueBefore;
	}
    }
    
}
