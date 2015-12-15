// $Id$
//
// (c) COPYRIGHT MIT, ECRIM and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.atrules.css3.media;

import org.w3c.css.atrules.css.media.MediaFeature;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2012/REC-css3-mediaqueries-20120619/#color-index
 */
public class MediaColorIndex extends MediaFeature {

    /**
     * Create a new MediaColorIndex
     */
    public MediaColorIndex() {
    }

    /**
     * Create a new MediaColorIndex.
     *
     * @param expression The expression for this media feature
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorrect
     */
    public MediaColorIndex(ApplContext ac, String modifier,
                           CssExpression expression, boolean check)
            throws InvalidParamException {

        if (expression != null) {
            if (expression.getCount() > 1) {
                throw new InvalidParamException("unrecognize", ac);
            }
            CssValue val = expression.getValue();
            // it must be a >=0 integer only
            if (val.getType() == CssTypes.CSS_NUMBER) {
                CssNumber valnum = (CssNumber) val;
                if (!valnum.isInteger()) {
                    throw new InvalidParamException("integer",
                            val.toString(), ac);
                }
                if (!valnum.isPositive()) {
                    throw new InvalidParamException("negative-value",
                                val.toString(), ac);
                }
                value = valnum;
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

    public MediaColorIndex(ApplContext ac, String modifier, CssExpression expression)
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
        return "color-index";
    }

    /**
     * Compares two media features for equality.
     *
     * @param other The other media features.
     */
    public boolean equals(MediaFeature other) {
        try {
            MediaColorIndex mci = (MediaColorIndex) other;
            return (((value == null) && (mci.value == null)) || ((value != null) && value.equals(mci.value)))
                    && (((modifier == null) && (mci.modifier == null)) || ((modifier != null) && modifier.equals(mci.modifier)));
        } catch (ClassCastException cce) {
            return false;
        }

    }
}
