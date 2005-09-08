//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

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
 * &nbsp;&nbsp;  'cue-after'
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
public class ACssCueAfter extends ACssProperty {
    
    CssValue value;
    
    private URL url;
    private static CssIdent none = new CssIdent("none");

    /**
     * Create a new ACssCueAfter
     */  
    public ACssCueAfter() {
	value = none;
    }
    
    /**
     * Create a new ACssCueAfter
     */  
    public ACssCueAfter(ACssCueBefore cueBefore) {
	value = cueBefore.value;
    }
    
    /**
     * Creates a new ACssCueAfter
     * @param value the value of the size
     * @exception InvalidParamException The value is incorrect
     */  
    public ACssCueAfter(ApplContext ac, CssExpression value, boolean check)
    	throws InvalidParamException {
	
	if(check && value.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = value.getValue();
	
	if (val instanceof CssURL) {
	    this.value = val;
	    value.next();
	    return;
	} else if (val.equals(inherit)) {
	    this.value = inherit;
	    value.next();
	    return;
	} else if (val.equals(none)) {
	    this.value = none;
	    value.next();
	    return;
	}
	
	throw new InvalidParamException("value", val.toString(), 
					getPropertyName(), ac);
    }
    
    public ACssCueAfter(ApplContext ac, CssExpression expression)
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
	return "cue-after";
    }
    
    public void addToStyle(ApplContext ac, CssStyle style) {
	ACssCue acssCue = ((ACssStyle) style).acssCue;
	if (acssCue.cueAfter != null)
	    style.addRedefinitionWarning(ac, this);
	acssCue.cueAfter = this;
    }
    
    public boolean equals(CssProperty property) {
	return (property instanceof ACssCueAfter && value.equals(((ACssCueAfter) property).value));
    }
    
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getCueAfter();
	} else {
	    return ((ACssStyle) style).acssCue.cueAfter;
	}
    }
    
}
