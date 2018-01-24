// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssString;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

/**
 * @spec http://www.w3.org/TR/2012/WD-css3-text-20120814/#text-align0
 */
public class CssTextAlign extends org.w3c.css.properties.css.CssTextAlign {

    // [ [ start | end | left | right | center ] || <string> ] |
    // // justify | match-parent | start end

    private static CssIdent[] nonUniqueValues;
    private static CssIdent[] uniqueValues;
    private static CssIdent start, end;

    static {
        String[] _unique_values = {"justify", "match-parent"};
        String[] _non_unique_values = {"start", "end", "left", "right", "center"};
        nonUniqueValues = new CssIdent[_non_unique_values.length];
        int i = 0;
        for (String s : _non_unique_values) {
            nonUniqueValues[i++] = CssIdent.getIdent(s);
        }

        uniqueValues = new CssIdent[_unique_values.length];
        i = 0;
        for (String s : _unique_values) {
            uniqueValues[i++] = CssIdent.getIdent(s);
        }

        start = CssIdent.getIdent("start");
        end = CssIdent.getIdent("end");
    }

    public static CssIdent getUniqueIdent(CssIdent ident) {
        for (CssIdent id : uniqueValues) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
    }

    public static CssIdent getNonUniqueIdent(CssIdent ident) {
        for (CssIdent id : nonUniqueValues) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
    }

    /**
     * Create a new CssTextAlign
     */
    public CssTextAlign() {
        value = initial;
    }

    /**
     * Creates a new CssTextAlign
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssTextAlign(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        setByUser();
        CssValue val;
        char op;

        if (check && expression.getCount() > 2) {
            throw new InvalidParamException("unrecognize", ac);
        }

        CssString stringValue = null;
        CssIdent identValue = null;
        CssIdent endValue = null;

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();

            switch (val.getType()) {
                case CssTypes.CSS_STRING:
                    if (stringValue != null || endValue != null) {
                        throw new InvalidParamException("value",
                                val, getPropertyName(), ac);
                    }
                    stringValue = (CssString) val;
                    if (stringValue.toString().length() != 3) {
                        // TODO specific error (string length)
                        throw new InvalidParamException("value",
                                stringValue, getPropertyName(), ac);
                    }
                    break;
                case CssTypes.CSS_IDENT:
                    CssIdent ident = (CssIdent) val;
                    // ident, so inherit, or allowed value
                    if (inherit.equals(val)) {
                        value = inherit;
                        if (check && expression.getCount() > 1) {
                            throw new InvalidParamException("unrecognize", ac);
                        }
                        break;
                    }
                    if (stringValue != null) {
                        identValue = getNonUniqueIdent(ident);
                        // we got string, we can only get
                        // non-unique values
                        if (identValue != null) {
                            break;
                        }
                    } else {
                        // ok, so here we can have either values
                        // and if we get two values, we need to
                        // have start and end.
                        if (identValue != null) {
                            if (start.equals(identValue) && end.equals(ident)) {
                                endValue = end;
                                break;
                            }
                            // we have two ident values, let it fail
                            // (through the default: in the case
                        } else {
                            identValue = getNonUniqueIdent(ident);
                            if (identValue == null) {
                                identValue = getUniqueIdent(ident);
                            }
                            if (identValue != null) {
                                break;
                            }
                        }
                        // unknown value, let it fail
                    }
                default:
                    throw new InvalidParamException("value",
                            val, getPropertyName(), ac);
            }
            if (op != CssOperator.SPACE) {
                throw new InvalidParamException("operator",
                        ((new Character(op)).toString()), ac);
            }
            expression.next();
        }

        // now reconstruct the value
        if (value != inherit) {
            if (expression.getCount() == 1) {
                if (identValue != null) {
                    value = identValue;
                } else if (stringValue != null) {
                    value = stringValue;
                }
                // other case (endValue alone non-null)
                // can't happen
            } else {
                // sanity check...
                if (stringValue != null && identValue != null) {
                    if (getUniqueIdent(identValue) != null) {
                        // TODO specific error msg
                        throw new InvalidParamException("value",
                                identValue, getPropertyName(), ac);
                    }
                }
                ArrayList<CssValue> v = new ArrayList<CssValue>(4);
                if (identValue != null) {
                    v.add(identValue);
                }
                if (endValue != null) {
                    v.add(endValue);
                }
                if (stringValue != null) {
                    v.add(stringValue);
                }
                value = new CssValueList(v);
            }
        }
    }

    public CssTextAlign(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }
}

