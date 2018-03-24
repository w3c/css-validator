// $Id$
//
// (c) COPYRIGHT MIT, ECRIM and Keio University, 2011
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.atrules.css3.media;

import org.w3c.css.atrules.css.media.MediaFeature;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2012/REC-css3-mediaqueries-20120619/#orientation
 */
public class MediaOrientation extends MediaFeature {

    static CssIdent portrait, landscape;

    static {
        portrait = CssIdent.getIdent("portrait");
        landscape = CssIdent.getIdent("landscape");
    }

    /**
     * Create a new MediaOientation
     */
    public MediaOrientation() {
    }

    /**
     * Create a new MediaOrientation.
     *
     * @param expression The expression for this media feature
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorrect
     */
    public MediaOrientation(ApplContext ac, String modifier,
                            CssExpression expression, boolean check)
            throws InvalidParamException {

        if (modifier != null) {
            throw new InvalidParamException("nomodifiermedia",
                    getFeatureName(), ac);
        }

        if (expression != null) {
            if (expression.getCount() > 1) {
                throw new InvalidParamException("unrecognize", ac);
            }

            CssValue val = expression.getValue();

            switch (val.getType()) {
                case CssTypes.CSS_IDENT:
                    CssIdent id = (CssIdent) val;
                    if (portrait.equals(id)) {
                        value = portrait;
                        break;
                    }
                    if (landscape.equals(id)) {
                        value = landscape;
                        break;
                    }
                    // let it flow through the exception
                default:
                    throw new InvalidParamException("value", expression.getValue(),
                            getFeatureName(), ac);
            }
        } else {
            // TODO add a warning for value less mediafeature that makes no sense
        }
    }

    public MediaOrientation(ApplContext ac, String modifier, CssExpression expression)
            throws InvalidParamException {
        this(ac, modifier, expression, false);
    }

    // just in case someone wants to call it externally...
    public void setModifier(ApplContext ac, String modifier)
            throws InvalidParamException {
        throw new InvalidParamException("nomodifiermedia",
                getFeatureName(), ac);
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
        return "orientation";
    }

    /**
     * Compares two media features for equality.
     *
     * @param other The other media features.
     */
    public boolean equals(MediaFeature other) {
        try {
            MediaOrientation mo = (MediaOrientation) other;
            return (((value == null) && (mo.value == null)) || ((value != null) && value.equals(mo.value)));
        } catch (ClassCastException cce) {
            return false;
        }

    }
}
