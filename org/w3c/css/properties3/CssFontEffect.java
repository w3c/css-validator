//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *  <P>
 *  <EM>Value:</EM> none || emboss || engrave || outline || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>all elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property controls the special effect applied to glyphs.
 *  <PRE>
 *  H1 { font-effect: outline}
 *  </PRE>
 */

public class CssFontEffect extends CssProperty {
 
    CssValue effect;
    ApplContext ac;

    static CssIdent none = new CssIdent("none");
    static CssIdent emboss = new CssIdent("emboss");
    static CssIdent engrave = new CssIdent("engrave");
    static CssIdent outline = new CssIdent("outline");

    /**
     * Create a new CssFontEffect
     */
    public CssFontEffect() {
	effect = none;
    }

    /**
     * Create a new CssFontEffect
     * 
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssFontEffect(ApplContext ac, CssExpression expression) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(none)) {
	    effect = none;
	    expression.next();
	}
	else if (val.equals(emboss)) {
	    effect = emboss;
	    expression.next();
	}
	else if (val.equals(engrave)) {
	    effect = engrave;
	    expression.next();
	}
	else if (val.equals(outline)) {
	    effect = outline;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    effect = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssFontEffect != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssFontEffect = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getFontEffect();
	}
	else {
	    return ((Css3Style) style).cssFontEffect;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssFontEffect &&
		effect.equals(((CssFontEffect) property).effect));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "font-effect";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return effect;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return effect.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return effect.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return effect == none;
    }

}
			      




    

					       
