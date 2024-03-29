// $Id$
//
// (c) COPYRIGHT MIT, ECRIM and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.atrules.css3.media;

import org.w3c.css.atrules.css.media.MediaFeature;
import org.w3c.css.atrules.css.media.MediaRangeFeature;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssComparator;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec https://www.w3.org/TR/2021/WD-mediaqueries-5-20211218/#descdef-media-vertical-viewport-segments
 */
public class MediaVerticalViewportSegments extends MediaRangeFeature {

    /**
     * Create a new MediaVerticalViewportSegments
     */
    public MediaVerticalViewportSegments() {
    }

    /**
     * Create a new MediaVerticalViewportSegments.
     *
     * @param expression The expression for this media feature
     * @throws InvalidParamException
     *          Values are incorrect
     */
    public MediaVerticalViewportSegments(ApplContext ac, String modifier,
                                         CssExpression expression, boolean check)
            throws InvalidParamException {

        if (expression != null) {
            if (expression.getCount() > 1) {
                throw new InvalidParamException("unrecognize", ac);
            }
            if (expression.getCount() == 0) {
                throw new InvalidParamException("few-value", getFeatureName(), ac);
            }
            CssValue val = expression.getValue();

            switch (val.getType()) {
                case CssTypes.CSS_NUMBER:
                    value = checkValue(ac, expression, getFeatureName());
                    break;
                case CssTypes.CSS_COMPARATOR:
                    // mediaqueries-4 case, expand the comparator and check its value
                    if (modifier != null) {
                        throw new InvalidParamException("nomodifierrangemedia",
                                getFeatureName(), ac);
                    }
                    CssComparator p = (CssComparator) val;
                    value = checkValue(ac, p.getParameters(), getFeatureName());
                    comparator = p.toString();
                    expression.next();
                    if (!expression.end()) {
                        val = expression.getValue();
                        if (val.getType() != CssTypes.CSS_COMPARATOR) {
                            throw new InvalidParamException("unrecognize", ac);
                        }
                        CssComparator p2;
                        p2 = (CssComparator) val;
                        otherValue = checkValue(ac, p2.getParameters(), getFeatureName());
                        otherComparator = p2.toString();
                        checkComparators(ac, p, p2, getFeatureName());
                    }
                    break;
                default:
                    throw new InvalidParamException("value", expression.getValue(),
                            getFeatureName(), ac);
            }
            expression.next();
            setModifier(ac, modifier);
        } else {
            if (modifier != null) {
                throw new InvalidParamException("nomodifiershortmedia",
                        getFeatureName(), ac);
            }
        }
    }

    public MediaVerticalViewportSegments(ApplContext ac, String modifier, CssExpression expression)
            throws InvalidParamException {
        this(ac, modifier, expression, false);
    }

    static CssValue checkValue(ApplContext ac, CssExpression expression, String caller)
            throws InvalidParamException {
        if (expression.getCount() == 0) {
            throw new InvalidParamException("few-value", caller, ac);
        }
        CssValue val = expression.getValue();
        CssValue value = null;

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                val.getCheckableValue().checkInteger(ac, caller);
                value = val;
                break;
            default:
                throw new InvalidParamException("value", expression.getValue(),
                        caller, ac);
        }
        return value;
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
        return "vertical-viewport-segments";
    }

    /**
     * Compares two media features for equality.
     *
     * @param other The other media features.
     */
    public boolean equals(MediaFeature other) {
        try {
            MediaVerticalViewportSegments mm = (MediaVerticalViewportSegments) other;
            return (((value == null) && (mm.value == null)) || ((value != null) && value.equals(mm.value)))
                    && (((modifier == null) && (mm.modifier == null)) || ((modifier != null) && modifier.equals(mm.modifier)));
        } catch (ClassCastException cce) {
            return false;
        }

    }
}
