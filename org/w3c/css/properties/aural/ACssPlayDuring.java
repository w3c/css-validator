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
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;

/**
 *  &nbsp;&nbsp;'play-during'
 *
 * <P>
 * <EM>Value: </EM> &lt;url&gt; mix? repeat? | auto | none<BR>
 * <EM>Initial:</EM> auto<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentage values:</EM> NA
 *
 * <p>Similar to the cue-before and cue-after properties, this indicates
 * sound to be played during an element as a background (ie the sound is
 * mixed in with the speech). 
 *
 * <p>The optional 'mix' keyword means the sound inherited from the parent
 * element's play-during property continues to play and the current
 * element sound (pointed to by the URL) is mixed with it. If mix is not
 * specified, the sound replaces the sound of the parent element. 
 *
 * <p>The optional 'repeat' keyword means the sound will repeat if it is
 * too short to fill the entire duration of the element. Without this
 * keyword, the sound plays once and then stops. Thuis is similar to the
 * background repeat properties in CSS1. If the sound is too long for the
 * element, it is clipped once the element is spoken.
 *
 * <p>Auto
 * means that the sound of the parent element continues to play 
 * (it is not restarted, which would have been the case if
 * this property inherited)and none means that there is silence -
 * the sound of the parent element (if any) is silent for the current
 * element and continues after the current element.
 *
 * <!-- cut for now <p class=comment>What happens with mixed-mode
 * rendering if an element is displayed onscreen rather than being
 * spoken, yet has a play-during property? Do we need a property that
 * switches between visual, aural, and other modes on a per-element
 * basis? Need synchronised multimedia feedback on this.  --> <!-- volume
 * wrt speech?? fade in and out? -->
 *
 *
 * <p> Examples:
 * <PRE>
 *   BLOCKQUOTE.sad {play-during: url(violins.aiff) }
 *   BLOCKQUOTE Q {play-during: url(harp.wav) mix}
 *   SPAN.quiet {play-during: none }
 * </pre>
 *
 * <p><b>Note:</b> If a stereo icon is dereferenced the central point of
 * the stereo pair should be placed at the azimuth for that element and
 * the left and right channels should be placed to either side of this
 * position.
 *
 * @version $Revision$
 */
public class ACssPlayDuring extends ACssProperty implements CssOperator {
    
    CssValue value;
    boolean mix;
    boolean repeat;
    
    private URL url;  
    
    private static CssIdent NONE = new CssIdent("none");
    private static CssIdent AUTO = new CssIdent("auto");
    private static CssIdent MIX = new CssIdent("mix");
    private static CssIdent REPEAT = new CssIdent("repeat");

    /**
     * Create a new ACssPlayDuring
     */  
    public ACssPlayDuring() {
	value = AUTO;
    }
    
    /**
     * Creates a new ACssPlayDuring
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */  
    public ACssPlayDuring(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	int valuesNb = expression.getCount();
	
	if(check && valuesNb > 3) {
	    throw new InvalidParamException("unrecognize", ac);	    
	}
	
	CssValue val = expression.getValue();		
	
	if (val instanceof CssURL) {
	    this.value = val;	    
	    if (valuesNb == 3) {
		if (expression.getOperator() != SPACE) {
		    throw new InvalidParamException("operator", 
			    (new Character(expression.getOperator()).toString()),
			    ac);
		}
		expression.next();
		if (expression.getOperator() != SPACE) {
		    throw new InvalidParamException("operator", 
			    (new Character(expression.getOperator()).toString()),
			    ac);
		}
		val = expression.getValue();
		if (!val.equals(MIX) && !val.equals(REPEAT)) {
		    throw new InvalidParamException("few-value", 
			    getPropertyName(), ac);
		}
		else if(!val.equals(MIX)) {
		    mix = true;
		}
		else { // val = REPEAT
		    repeat = true;
		}
		expression.next();
		val = expression.getValue();
		if (mix && val.equals(REPEAT)) {
		    repeat = true;
		}
		else if(repeat && val.equals(MIX)) {
		    mix = true;
		}
		else {
		    throw new InvalidParamException("unrecognize", ac);
		}
	    } else if (valuesNb == 2) {
		if (expression.getOperator() != SPACE) {
		    throw new InvalidParamException("operator", 
			    (new Character(expression.getOperator()).toString()),
			    ac);
		}
		expression.next();
		val = expression.getValue();
		if (val.equals(MIX)) {
		    mix = true;
		} else if (val.equals(REPEAT)) {
		    repeat = true;
		} else {
		    throw new InvalidParamException("unrecognize", ac);
		}
	    }
	    expression.next();
	    return;
	} else if (val.equals(inherit)) {
	    if(check && valuesNb > 1) {
		throw new InvalidParamException("unrecognize", ac);	    
	    }
	    this.value = inherit;
	    expression.next();
	    return;
	} else if (val.equals(NONE)) {
	    if(check && valuesNb > 1) {
		throw new InvalidParamException("unrecognize", ac);	    
	    }
	    this.value = NONE;
	    expression.next();
	    return;
	} else if (val.equals(AUTO)) {
	    if(check && valuesNb > 1) {
		throw new InvalidParamException("unrecognize", ac);	    
	    }
	    this.value = AUTO;
	    expression.next();
	    return;
	}
	
	throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
    }
    
    public ACssPlayDuring(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	return value;
    }
    
    /**
     * Returns some usable value of this property...
     */
    public URL getValue() throws IOException { // vm
	if (value == NONE || value == AUTO || value == inherit)
	    return null;
	else {
	    if (url == null) {
		url = new URL((String) value.get());
	    }
	    return url;
	}
    }
    
    /**
     * The optional 'mix' keyword means the sound inherited from the parent
     * element's play-during property continues to play and the current
     * element sound (pointed to by the URL) is mixed with it. If mix is not
     * specified, the sound replaces the sound of the parent element. 
     */  
    public boolean isMix() {
	return mix;
    }
    
    /**
     * The optional 'repeat' keyword means the sound will repeat if it is
     * too short to fill the entire duration of the element. Without this
     * keyword, the sound plays once and then stops. Thuis is similar to the
     * background repeat properties in CSS1. If the sound is too long for the
     * element, it is clipped once the element is spoken.
     */  
    public boolean isRepeat() {
	return repeat;
    }
    
    /**
     * 'auto' means that the sound of the parent element continues to play 
     * (it is not restarted, which would have been the case if
     * this property inherited).
     */  
    public boolean isAuto() {
	return value == AUTO;
    }
    
    /**
     * 'none' means that there is silence.
     * the sound of the parent element (if any) is silent for the current
     * element and continues after the current element.
     */  
    public boolean isNone() {
	return value == NONE;
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
	if (value == NONE || value == AUTO || value == inherit) {
	    return value.toString();
	} else {
	    String ret = value.toString();
	    if (mix)
		ret += " mix";
	    if (repeat)
		ret += " repeat";
	    return ret;
	}
    }
    
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "play-during";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((ACssStyle) style).acssPlayDuring != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssPlayDuring = this;
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof ACssPlayDuring && 
		value.equals(((ACssPlayDuring) property).value));
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getPlayDuring();
	} else {
	    return ((ACssStyle) style).acssPlayDuring;
	}
    }    
}
