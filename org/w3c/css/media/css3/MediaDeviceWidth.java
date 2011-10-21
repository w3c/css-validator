// $Id$
//
// (c) COPYRIGHT MIT, ECRIM and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.media.css3;

import org.w3c.css.media.MediaFeature;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2010/CR-css3-mediaqueries-20100727/#device-width
 */
public class MediaDeviceWidth extends MediaFeature {

    /**
     * Create a new MediaWidth
     */
    public MediaDeviceWidth() {
    }

    /**
     * Create a new MediaWidth.
     *
     * @param expression The expression for this media feature
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorrect
     */
    public MediaDeviceWidth(ApplContext ac, String modifier,
                            CssExpression expression, boolean check)
            throws InvalidParamException {

        if (expression != null) {
            if (expression.getCount() > 1) {
                throw new InvalidParamException("unrecognize", ac);
            }

            CssValue val = expression.getValue();

            switch (val.getType()) {
                case CssTypes.CSS_NUMBER:
                    // a bit stupid as the only value would be 0...
                    val = ((CssNumber) val).getLength();
                case CssTypes.CSS_LENGTH:
                    CssLength l = (CssLength) val;
                    if (l.floatValue() < 0.f) {
                        throw new InvalidParamException("negative-value",
                                val.toString(), ac);
                    }
                    value = val;
                    expression.next();
                    break;
                default:
                    throw new InvalidParamException("value", expression.getValue(),
                            getFeatureName(), ac);
            }
            setModifier(ac, modifier);
        } else {
            if (modifier != null) {
                throw new InvalidParamException("nomodifiershortmedia",
                        getFeatureName(), ac);
            }
        }
    }

    public MediaDeviceWidth(ApplContext ac, String modifier, CssExpression expression)
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
        return "device-width";
    }

    /**
     * Compares two media features for equality.
     *
     * @param other The other media features.
     */
    public boolean equals(MediaFeature other) {
        try {
            MediaDeviceWidth mdw = (MediaDeviceWidth) other;
            return (((value == null) && (mdw.value == null)) || ((value != null) && value.equals(mdw.value)))
                    && (((modifier == null) && (mdw.modifier == null)) || ((modifier != null) && modifier.equals(mdw.modifier)));
        } catch (ClassCastException cce) {
            return false;
        }

    }
}
