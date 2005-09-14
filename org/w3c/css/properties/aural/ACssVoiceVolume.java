//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;


/**
 *  &nbsp;&nbsp; 'volume'
 *
 * <P>
 * <EM>Value:</EM> &lt;percentage&gt; | silent | x-soft | soft | medium | loud | x-loud<br>
 * <EM>Initial:</EM> medium<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> yes<BR>
 * <EM>Percentage values:</EM> relative to user-specified mapping
 *
 * <P>The legal range of percentage values is 0% to 100%. Note that '0%'
 * <strong>does not mean the same as "silent"</strong>. 0% represents the
 * <em>minimum audible</em> volume level and 100% corresponds to the
 * <em>maximum comfortable</em> level. There is a fixed mapping between
 * keyword values and percentages:
 *
 * <UL>
 * <li>'silent' = no sound at all, the element is  spoken silently
 * <LI>'x-soft' = '0%'
 * <LI>'soft' = '25%'
 * <LI>'medium' = '50%'
 * <LI>'loud' = '75%'
 * <LI>'x-loud' = '100%'
 * </UL>
 *
 * <P>VoiceVolume refers to the median volume of the waveform. In other words,
 * a highly inflected voice at a volume of 50 might peak well above
 * that. The overall values are likely to be human adjustable
 * for comfort, for example with a physical volume control (which would
 * increase both the 0% and 100% values proportionately); what this
 * property does is adjust the dynamic range.
 *
 *
 * <p>The UA <em>should</em> allow the values corresponding to 0% and
 * 100% to be set by the listener. No one setting is universally
 * applicable; suitable values depend on the equipment in use (speakers,
 * headphones), the environment (in car, home theater, library) and
 * personal preferences. Some examples:
 *
 * <ul><li>A browser for in-car use has a setting for when there is lots
 * of background noise. 0% would map to a fairly high level and 100% to a
 * quite high level. The speech is easily audible over the road noise but
 * the overall dynamic range is compressed. Plusher cars with better
 * insulation allow a wider dynamic range.
 *
 *
 * <li>Another speech browser is being used in the home, late at night,
 * (don't annoy the neighbors) or in a shared study room. 0% is set to
 * a very quiet level and 100% to a fairly quiet level, too. As with the first
 * example, there is a low slope; the dynamic range is reduced. The
 * actual volumes are low here, wheras they were high in the first
 * example.
 *
 * <li>In a quiet and isolated house, an expensive hi-fi home theatre
 * setup. 0% is set fairly low and 100% to quite high; there is wide
 * dynamic range.
 * </ul>
 *
 * <p>The same authors stylesheet could be used in all cases, simply by
 * mapping the 0 and 100 points suitably at the client side.
 *
 * <p>If an element has a volume of silent, it is spoken silently. It
 * takes up the same time as if it had been spoken, including any pause
 * before and after the element, but no sound is generated. This may be
 * used in language teaching applications, for example. A pause is
 * gdenerated for the pupil to speak the element themselves. <b>Note:</b>
 * the value is inherited so child elements will also be silent.  Child
 * elements may however set the volume to a non-silent value and will
 * then be spoken.
 *
 * <p>To inhibit the speaking of an element and all it's children so that
 * it takes no time at all (for example, to get the effect of collapsing
 * and expanding lists) use the CSS1 property 'display'
 *
 * <pre>display: none</pre>
 *
 * <p>When using the rule <tt>display: none</tt> the element takes up
 * <em>no time</em>; it is not represented as a pause the length of the
 * spoken text.
 *
 * @version $Revision$
 */
public class ACssVoiceVolume extends ACssProperty {

    CssValue value;

    private static int[] hash_values;

    private static String[] VOLUME = { "silent", "x-soft", "soft",
				       "medium", "loud", "x-loud", "louder", "softer", "inherit" };

    private static CssIdent defaultValue = new CssIdent(VOLUME[3]);

    /**
     * Create a new ACssVoiceVolume
     */
    public ACssVoiceVolume() {
	value = defaultValue;
    }

    /**
     * Creates a new ACssVoiceVolume
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public ACssVoiceVolume(ApplContext ac, CssExpression expression,
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
	} else if (val instanceof CssPercentage) {
	    value = val;
	} else if (val instanceof CssNumber) {
	    CssNumber num = (CssNumber) val;
	    int i = (int) num.getValue();
	    if (i < 0) {
		value = new CssNumber(ac, 0);
	    } else if (i > 100) {
		value = new CssNumber(ac, 100);
	    } else {
		value = val;
	    }
	    expression.next();
	} else {
	    throw new InvalidParamException("value",
					    expression.getValue().toString(),
					    getPropertyName(), ac);
	}

	expression.next();
    }

    public ACssVoiceVolume(ApplContext ac, CssExpression expression)
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
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
	return value.equals(inherit);
    }

    /**
     * Returns some usable value of this property...
     *
     * @deprecated
     */
    public float getValue() { // vm
	return ((CssNumber) value).getValue();
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "voice-volume";
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
	if (((ACssStyle) style).acssVoiceVolume != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssVoiceVolume = this;
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof ACssVoiceVolume &&
		value.equals(((ACssVoiceVolume) property).value));
    }

    private CssIdent checkIdent(ApplContext ac, CssIdent ident)
	throws InvalidParamException {

	int hash = ident.hashCode();
	for (int i = 0; i < VOLUME.length; i++) {
	    if (hash_values[i] == hash) {
		return ident;
	    }
	}

	throw new InvalidParamException("value", ident.toString(),
					getPropertyName(), ac);
    }

    /** @deprecated */
    private CssPercentage ValueOfIdent(ApplContext ac, CssIdent ident)
	throws InvalidParamException {

	int hash = ident.hashCode();
	for (int i = 0; i < VOLUME.length; i++) {
	    if (hash_values[i] == hash) {
		return new CssPercentage(ACssProperties.getValue(this, VOLUME[i]));
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
	    return ((ACssStyle) style).getVoiceVolume();
	} else {
	    return ((ACssStyle) style).acssVoiceVolume;
	}
    }

    static {
	hash_values = new int[VOLUME.length];
	for (int i = 0; i < VOLUME.length; i++)
	    hash_values[i] = VOLUME[i].hashCode();
    }
}

