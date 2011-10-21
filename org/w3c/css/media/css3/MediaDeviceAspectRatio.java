// $Id$
//
// (c) COPYRIGHT MIT, ECRIM and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.media.css3;

import org.w3c.css.media.MediaFeature;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2010/CR-css3-mediaqueries-20100727/#device-aspect-ratio
 */
public class MediaDeviceAspectRatio extends MediaFeature {

    int h, w;

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
            if (expression.getCount() != 2) {
                throw new InvalidParamException("unrecognize", ac);
            }
            CssValue val = expression.getValue();

            if (val.getType() == CssTypes.CSS_NUMBER) {
                CssNumber valnum = (CssNumber) val;
                if (!valnum.isInteger()) {
                    throw new InvalidParamException("integer",
                            val.toString(), ac);
                }
                w = valnum.getInt();
                // TODO FIXME this should be replaced when / is no longer
                // an operator
                if (expression.getOperator() != CssOperator.SLASH) {
                    throw new InvalidParamException("operator", expression.toString(), ac);
                }
                // now the second value
                expression.next();
                val = expression.getValue();
                if (val.getType() == CssTypes.CSS_NUMBER) {
                    valnum = (CssNumber) val;
                    if (!valnum.isInteger()) {
                        throw new InvalidParamException("integer",
                                val.toString(), ac);
                    }
                    h = valnum.getInt();
                    // perfect, now set the modifier
                    setModifier(ac, modifier);
                    return;
                }
            }
            // otherwise... fail
            throw new InvalidParamException("value", expression.getValue(),
                    getFeatureName(), ac);
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
        StringBuilder sb = new StringBuilder();
        sb.append(w).append('/').append(h);
        return sb.toString();
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
            if (h == 0 || mar.h == 0) {
                return (w == 0 && mar.w == 0 && h == 0 && mar.h == 0);
            }
            float thisRatio = ((float) w) / ((float) h);
            float otherRatio = ((float) mar.w) / ((float) mar.h);

            return (thisRatio == otherRatio)
                    && (((modifier == null) && (mar.modifier == null)) || ((modifier != null) && modifier.equals(mar.modifier)));
        } catch (ClassCastException cce) {
            return false;
        }
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (h == 0 && w == 0) {
            return getFeatureName();
        }
        StringBuilder sb = new StringBuilder();
        if (modifier != null) {
            sb.append(modifier).append('-');
        }
        sb.append(getFeatureName());
        sb.append(':').append(w).append('/').append(h);
        return sb.toString();
    }
}
