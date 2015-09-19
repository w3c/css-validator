// Author: Chris Rebert <css.validator@chrisrebert.com>
//
// (c) COPYRIGHT World Wide Web Consortium (MIT, ERCIM, Keio University, and Beihang University), 2015.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.WarningParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssString;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssValue;

/**
 * FIXME: This doesn't implement filter-effects-1 yet!
 * @spec http://www.w3.org/TR/filter-effects-1/#FilterProperty
 * @spec http://www.w3.org/TR/SVG/filters.html#FilterProperty
 * @see https://msdn.microsoft.com/library/ms530752%28v=vs.85%29.aspx
 * @see http://davidwalsh.name/css-image-filters-internet-explorer
 */
public class CssFilter extends org.w3c.css.properties.css.CssFilter {
    private static final Set<String> LEGACY_IE_IDENTS = new HashSet<String>();
    static {
        LEGACY_IE_IDENTS.add("gray");
        LEGACY_IE_IDENTS.add("fliph");
        LEGACY_IE_IDENTS.add("flipv");
        LEGACY_IE_IDENTS.add("xray");
    }

    /**
     * Create a new CssFilter
     */
    public CssFilter() {
        value = initial;
    }

    public CssFilter(final ApplContext ac, final CssExpression expression) throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Emit warnings instead of errors for legacy proprietary IE filters
    */
    private boolean allowLegacyIEValues(final ApplContext ac) {
        return ac.getTreatVendorExtensionsAsWarnings();
    }

    /**
     * Create a new CssFilter
     *
     * @param expression The expressions for this property
     * @exception InvalidParamException Expressions are incorrect
     */
    public CssFilter(final ApplContext ac, final CssExpression expression, final boolean check) throws InvalidParamException {
        setByUser();
        final CssValue val = expression.getValue();
        if (val instanceof CssURL) {
            value = val;
            expression.next();
            return;
        }
        if (val instanceof CssIdent) {
            if (val.equals(inherit)) {
                value = inherit;
                expression.next();
                return;
            } else if (val.equals(none)) {
                value = none;
                expression.next();
                return;
            } else if (allowLegacyIEValues(ac)) {
                if (LEGACY_IE_IDENTS.contains(val.toString())) {
                    value = val;
                    expression.next();
                    throw new WarningParamException("vendor-extension", expression.toStringFromStart());
                }
            }
        }
        // FIXME: Does not yet handle the following forms of legacy IE filters since they fail to parse as `CssValue`s:
        // filter: progid:DXImageTransform.Microsoft.Alpha(Opacity=40);
        // filter: alpha(opacity=40);
        if (allowLegacyIEValues(ac) && val instanceof CssString) {
            final String funcall = val.toString().substring(1).trim().toLowerCase(Locale.ENGLISH);
            if (funcall.startsWith("progid:") ||
                funcall.startsWith("alpha") ||
                funcall.startsWith("blur") ||
                funcall.startsWith("wave")) {
                    value = val;
                    expression.next();
                    throw new WarningParamException("vendor-extension", expression.toStringFromStart());
            }
        }
        throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
    }
}
