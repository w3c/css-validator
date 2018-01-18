//
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2016.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.svg;

import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.properties.css3.CssBackgroundClip;
import org.w3c.css.properties.css3.CssBorderRadius;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssFunction;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @spec https://www.w3.org/TR/2014/CR-css-masking-1-20140826/#the-clip-path
 */
public class CssClipPath extends org.w3c.css.properties.css.CssClipPath {

    public static final CssIdent[] geometry_box_allowed_values;
    public static final CssIdent inset_round;

    static {
        String[] _allowed_values = {"margin-box", "fill-box", "stroke-box", "view-box"};

        geometry_box_allowed_values = new CssIdent[_allowed_values.length];
        for (int i = 0; i < geometry_box_allowed_values.length; i++) {
            geometry_box_allowed_values[i] = CssIdent.getIdent(_allowed_values[i]);
        }
        inset_round = CssIdent.getIdent("round");
    }

    public static final CssIdent getGeometryBoxAllowedValue(CssIdent ident) {
        // <geometry-box> = <shape-box> | fill-box | stroke-box | view-box
        // <shape-box> = <box> | margin-box
        CssIdent idt = CssBackgroundClip.getMatchingIdent(ident);
        if (idt != null) {
            return idt;
        }
        for (CssIdent id : geometry_box_allowed_values) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
    }

    /**
     * Create a new CssClipPath
     */
    public CssClipPath() {
        value = initial;
    }

    /**
     * Creates a new CssClipPath
     *
     * @param expression The expression for this property
     * @throws org.w3c.css.util.InvalidParamException
     *          Expressions are incorrect
     */
    public CssClipPath(ApplContext ac, CssExpression expression, boolean check)
            throws InvalidParamException {
        if (check && expression.getCount() > 1) {
            throw new InvalidParamException("unrecognize", ac);
        }
        setByUser();

        ArrayList<CssValue> values = new ArrayList<CssValue>();
        boolean gotGeometryBox = false;
        boolean gotBasicShape = false;
        while (!expression.end()) {
            CssValue val;
            char op = expression.getOperator();
            val = expression.getValue();

            switch (val.getType()) {
                case CssTypes.CSS_FUNCTION:
                    if (!gotBasicShape) {
                        CssFunction func = (CssFunction) val;
                        String funcname = func.getName().toLowerCase();
                        switch (funcname) {
                            case "inset":
                                checkInset(ac, func.getParameters(), this);
                                break;
                            case "circle":
                            case "ellipse":
                            case "polygon":
                                // not yet implemented
                            default:
                                throw new InvalidParamException("value", val,
                                        getPropertyName(), ac);
                        }
                        gotBasicShape = true;
                        values.add(val);
                        break;
                    }
                    throw new InvalidParamException("value", val,
                            getPropertyName(), ac);
                case CssTypes.CSS_URL:
                    value = val;
                    break;
                case CssTypes.CSS_IDENT:
                    if (inherit.equals(val)) {
                        value = inherit;
                        break;
                    }
                    if (none.equals(val)) {
                        value = none;
                        break;
                    }
                    if (!gotGeometryBox) {
                        CssIdent ident = getGeometryBoxAllowedValue((CssIdent) val);
                        if (ident != null) {
                            gotGeometryBox = true;
                            values.add(ident);
                            break;
                        }
                    }

                default:
                    throw new InvalidParamException("value",
                            val.toString(),
                            getPropertyName(), ac);
            }
            expression.next();
            if (op != SPACE) {
                throw new InvalidParamException("operator",
                        ((new Character(op)).toString()), ac);
            }
        }
        if (gotBasicShape || gotGeometryBox) {
            value = (values.size() == 1) ? values.get(0) : new CssValueList(values);
        }
    }

    public CssClipPath(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    static void checkInset(ApplContext ac, CssExpression expression,
                           CssProperty caller) throws InvalidParamException {
        CssValue val;
        char op;
        int nb_shape_arg = 0;

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();

            switch (val.getType()) {
                case CssTypes.CSS_NUMBER:
                    val.getCheckableValue().checkEqualsZero(ac, caller);
                case CssTypes.CSS_LENGTH:
                case CssTypes.CSS_PERCENTAGE:
                    nb_shape_arg++;
                    if (nb_shape_arg > 4) {
                        throw new InvalidParamException("unrecognize", ac);
                    }
                    break;
                case CssTypes.CSS_IDENT:
                     if (inset_round.equals((CssIdent)val)) {
                         // the remainder must be a border-radius
                         CssExpression nex = new CssExpression();
                         expression.next();
                         while (!expression.end()) {
                             nex.addValue(expression.getValue());
                             nex.setOperator(expression.getOperator());
                             expression.next();
                         }
                         if (nex.getCount() == 0) {
                             throw new InvalidParamException("unrecognize", ac);
                         }
                         CssBorderRadius.checkBorderCornerRadius(ac, caller, nex, true);
                         break;
                     }
                default:
                    throw new InvalidParamException("value",
                            val.toString(),
                            caller.getPropertyName(), ac);
            }
            if (op != SPACE) {
                throw new InvalidParamException("inset-separator",
                        ((new Character(op)).toString()), ac);
            }
            expression.next();
        }
    }
}

