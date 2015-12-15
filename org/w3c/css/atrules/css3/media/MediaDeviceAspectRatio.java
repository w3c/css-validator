// $Id$
//
// (c) COPYRIGHT MIT, ECRIM and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.atrules.css3.media;

import org.w3c.css.atrules.css.media.MediaFeature;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2012/REC-css3-mediaqueries-20120619/#device-aspect-ratio
 */
public class MediaDeviceAspectRatio extends MediaFeature {

    /**
     * Create a new MediaHeight
     */
    public MediaDeviceAspectRatio() {
    }

    /**
     * Create a new MediaHeight.
     *
     * @param expression The expression for this media feature
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorrect
     */
    public MediaDeviceAspectRatio(ApplContext ac, String modifier,
                                  CssExpression expression, boolean check)
            throws InvalidParamException {

        if (expression != null) {
            if (expression.getCount() != 1) {
                throw new InvalidParamException("unrecognize", ac);
            }
            CssValue val = expression.getValue();

            if (val.getType() == CssTypes.CSS_RATIO) {
                value = val;
                setModifier(ac, modifier);
            } else {
                throw new InvalidParamException("unrecognize", ac);
            }
            setModifier(ac, modifier);
        } else {
            if (modifier != null) {
                throw new InvalidParamException("nomodifiershortmedia",
                        getFeatureName(), ac);
            }
        }

    }

    public MediaDeviceAspectRatio(ApplContext ac, String modifier, CssExpression expression)
            throws InvalidParamException {
        this(ac, modifier, expression, false);
    }

    /**
     * Returns the value of this media feature.
     */

    public Object get() {
        return value;
    }

    /**
     * Returns the name of this media feature.
     */
    public final String getFeatureName() {
        return "device-aspect-ratio";
    }

    /**
     * Compares two media features for equality.
     *
     * @param other The other media features.
     */
    public boolean equals(MediaFeature other) {
        try {
            MediaDeviceAspectRatio mar = (MediaDeviceAspectRatio) other;
            if (value == null) {
                return (other.value == null);
            }
            return value.equals(other.value);
        } catch (ClassCastException cce) {
            return false;
        }
    }

}
