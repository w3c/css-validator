//
// @author Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2021.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

import java.math.BigDecimal;

/**
 * A CSS var().
 *
 * @spec https://www.w3.org/TR/2015/CR-css-variables-1-20151203/#funcdef-var
 */
public class CssVariable extends CssCheckableValue {

    public static final int type = CssTypes.CSS_VARIABLE;

    public final int getRawType() {
        return type;
    }

    public final int getType() {
        if (computed_type == CssTypes.CSS_UNKNOWN) {
            return type;
        }
        return computed_type;
    }

    String variable_name = null;
    ApplContext ac;
    int computed_type = CssTypes.CSS_UNKNOWN;
    CssExpression exp = null;
    CssValue _exp_value = null;
    String _toString = null;

    /**
     * Create a new CssVariable
     */
    public CssVariable() {
    }

    public CssVariable(ApplContext ac, String varname) {
        this(ac, varname, null);
    }

    public CssVariable(String varname, CssExpression expression) {
        this(null, varname, expression);
    }

    public CssVariable(ApplContext ac, String varname, CssExpression expression) {
        if (ac != null) {
            this.ac = ac;
        }
        if (varname != null) {
            variable_name = varname;
        }
        if (expression != null) {
            if (expression.getCount() == 1) {
                _exp_value = expression.getValue();
                computed_type = _exp_value.getType();
            }
        }
        this.exp = expression;
    }

    public void set(String s, ApplContext ac) throws InvalidParamException {
        // we don't support this way of setting the value
        // as we rely on the parsing to create it incrementally
        throw new InvalidParamException("unrecognize", s, ac);
    }

    public void set(CssExpression expression) {
        if (expression != null) {
            if (expression.getCount() == 1) {
                CssValue v = expression.getValue();
                computed_type = v.getType();
            }
        }
        this.exp = expression;
    }

    /**
     * Returns the value
     */

    public Object get() {
        return toString();
    }


    public String toString() {
        if (_toString == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("var(").append(variable_name);
            if (exp != null) {
                sb.append(", ").append(exp.toStringFromStart());
            }
            sb.append(')');
            _toString = sb.toString();
        }
        return _toString;
    }

    private boolean _isCheckableType(int type) {
        switch (type) {
            case CssTypes.CSS_ANGLE:
            case CssTypes.CSS_CLAMP:
            case CssTypes.CSS_FLEX:
            case CssTypes.CSS_FREQUENCY:
            case CssTypes.CSS_LENGTH:
            case CssTypes.CSS_NUMBER:
            case CssTypes.CSS_PERCENTAGE:
            case CssTypes.CSS_SEMITONE:
            case CssTypes.CSS_TIME:
            case CssTypes.CSS_VOLUME:
                return true;
            default:
                return false;
        }
    }

    @Override
    public CssCheckableValue getCheckableValue() {
        if (_isCheckableType(computed_type)) {
            return this;
        }
        throw new ClassCastException("unknown");
    }

    @Override
    public boolean isPositive() {
        if (_isCheckableType(computed_type)) {
            try {
                return _exp_value.getCheckableValue().isPositive();
            } catch (Exception ignored) {
            }
            ;
        }
        return false;
    }

    @Override
    public boolean isStrictlyPositive() {
        if (_isCheckableType(computed_type)) {
            try {
                return _exp_value.getCheckableValue().isStrictlyPositive();
            } catch (Exception ignored) {
            }
            ;
        }
        return false;
    }

    @Override
    public boolean isZero() {
        if (_isCheckableType(computed_type)) {
            try {
                return _exp_value.getCheckableValue().isZero();
            } catch (Exception ignored) {
            }
            ;
        }
        return false;
    }

    @Override
    public void checkInteger(ApplContext ac, String callername)
            throws InvalidParamException {
        if (_isCheckableType(computed_type)) {
            _exp_value.getCheckableValue().checkInteger(ac, callername);
        } 
    }

    @Override
    public void setValue(BigDecimal v) {
        // do nothing
    }

    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */
    public boolean equals(Object value) {
        return (value instanceof CssVariable &&
                this.variable_name.equals(((CssVariable) value).variable_name));
    }

}
