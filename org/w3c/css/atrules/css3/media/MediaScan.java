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
 * @spec http://www.w3.org/TR/2012/REC-css3-mediaqueries-20120619/#scan
 */
public class MediaScan extends MediaFeature {

    static CssIdent progressive, interlace;

    static {
        progressive = CssIdent.getIdent("progressive");
        interlace = CssIdent.getIdent("interlace");
    }

    /**
     * Create a new MediaScan
     */
    public MediaScan() {
    }

    /**
     * Create a new MediaScan.
     *
     * @param expression The expression for this media feature
     * @throws org.w3c.css.util.InvalidParamException
     *          Values are incorrect
     */
    public MediaScan(ApplContext ac, String modifier,
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
            if (expression.getCount() == 0) {
                throw new InvalidParamException("few-value", getFeatureName(), ac);
            }
            CssValue val = expression.getValue();

            switch (val.getType()) {
                case CssTypes.CSS_IDENT:
                    CssIdent id = (CssIdent) val;
                    if (progressive.equals(id)) {
                        value = progressive;
                        break;
                    }
                    if (interlace.equals(id)) {
                        value = interlace;
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

    public MediaScan(ApplContext ac, String modifier, CssExpression expression)
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
        return "scan";
    }

    /**
     * Compares two media features for equality.
     *
     * @param other The other media features.
     */
    public boolean equals(MediaFeature other) {
        try {
            MediaScan ms = (MediaScan) other;
            return (((value == null) && (ms.value == null)) || ((value != null) && value.equals(ms.value)));
        } catch (ClassCastException cce) {
            return false;
        }

    }
}
