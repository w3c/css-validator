// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM and Keio University, 2012.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.properties.css3.CssBackgroundPosition;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.CssVersion;
import org.w3c.css.util.InvalidParamException;

import java.util.ArrayList;

import static org.w3c.css.values.CssOperator.COMMA;
import static org.w3c.css.values.CssOperator.SPACE;

/**
 * @author CSS3 Image
 */
public class CssImage extends CssValue {

    public static final int type = CssTypes.CSS_IMAGE;

    public final int getType() {
        return type;
    }

    static final CssIdent to = CssIdent.getIdent("to");
    static final CssIdent left = CssIdent.getIdent("left");
    static final CssIdent right = CssIdent.getIdent("right");
    static final CssIdent top = CssIdent.getIdent("top");
    static final CssIdent bottom = CssIdent.getIdent("bottom");
    static final CssIdent at = CssIdent.getIdent("at");
    static final CssIdent circle = CssIdent.getIdent("circle");
    static final CssIdent ellipse = CssIdent.getIdent("ellipse");
    static final CssIdent[] extent_keywords;

    static {
        String _val[] = {"closest-corner", "closest-side",
                "farthest-corner", "farthest-side"};
        extent_keywords = new CssIdent[_val.length];
        int i = 0;
        for (String s : _val) {
            extent_keywords[i++] = CssIdent.getIdent(s);
        }
    }

    public static boolean isVerticalIdent(CssIdent ident) {
        return ident.equals(top) || ident.equals(bottom);
    }

    public static CssIdent getLinearGradientIdent(CssIdent ident) {
        if (left.equals(ident)) {
            return left;
        }
        if (right.equals(ident)) {
            return right;
        }
        if (top.equals(ident)) {
            return top;
        }
        if (bottom.equals(ident)) {
            return bottom;
        }
        return null;
    }

    public static CssIdent getExtentIdent(CssIdent ident) {
        for (CssIdent id : extent_keywords) {
            if (id.equals(ident)) {
                return id;
            }
        }
        return null;
    }

    public static CssIdent getShape(CssIdent ident) {
        if (circle.equals(ident)) {
            return circle;
        }
        if (ellipse.equals(ident)) {
            return ellipse;
        }
        return null;
    }

    String name;
    CssValue value;

    private String _cache;

    /**
     * Set the value of this function
     *
     * @param s  the string representation of the frequency.
     * @param ac For errors and warnings reports.
     */
    public void set(String s, ApplContext ac) {
        // @@TODO
    }

    /**
     * @param exp
     * @param ac
     * @throws InvalidParamException
     * @spec http://www.w3.org/TR/2012/CR-css3-images-20120417/#image-list-type
     */
    public void setImageList(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        name = "image";
        _cache = null;
        // ImageList defined in CSS3 and onward
        if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(name).append('(').append(exp.toStringFromStart()).append(')');
            throw new InvalidParamException("notversion", sb.toString(),
                    ac.getCssVersionString(), ac);
        }

        CssValue val;
        char op;
        boolean gotcolor = false;
        ArrayList<CssValue> v = new ArrayList<CssValue>();
        CssColor c;
        while (!exp.end()) {
            val = exp.getValue();
            op = exp.getOperator();
            // color is always last
            if (gotcolor) {
                throw new InvalidParamException("value",
                        val.toString(),
                        "image()", ac);
            }

            switch (val.getType()) {
                case CssTypes.CSS_URL:
                case CssTypes.CSS_STRING:
                    v.add(val);
                    break;
                case CssTypes.CSS_HASH_IDENT:
                    c = new CssColor();
                    c.setShortRGBColor(ac, val.toString());
                    v.add(c);
                    gotcolor = true;
                    break;
                case CssTypes.CSS_COLOR:
                    v.add(val);
                    gotcolor = true;
                    break;
                case CssTypes.CSS_IDENT:
                    if (CssColorCSS3.currentColor.equals((CssIdent) val)) {
                        v.add(val);
                        gotcolor = true;
                        break;
                    }
                    c = new CssColor();
                    c.setIdentColor(ac, val.toString());
                    v.add(c);
                    gotcolor = true;
                    break;
                default:
                    throw new InvalidParamException("value",
                            val.toString(),
                            "image()", ac);
            }
            exp.next();
            if (!exp.end() && op != COMMA) {
                exp.starts();
                throw new InvalidParamException("operator",
                        ((new Character(op)).toString()), ac);
            }
        }
        value = (v.size() == 1) ? v.get(0) : new CssLayerList(v);
    }

    /**
     * @param exp
     * @param ac
     * @throws InvalidParamException
     * @spec http://www.w3.org/TR/2012/CR-css3-images-20120417/#linear-gradient-type
     */
    public void setLinearGradient(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        name = "linear-gradient";
        _cache = null;
        _setLinearGradient(exp, ac);
    }

    /**
     * @param exp
     * @param ac
     * @throws InvalidParamException
     * @spec http://www.w3.org/TR/2012/CR-css3-images-20120417/#linear-gradient-type
     */
    public void setRepeatingLinearGradient(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        name = "repeating-linear-gradient";
        _cache = null;
        _setLinearGradient(exp, ac);
    }

    /**
     * @param exp
     * @param ac
     * @throws InvalidParamException
     * @spec http://www.w3.org/TR/2012/CR-css3-images-20120417/#linear-gradient-type
     */
    private void _setLinearGradient(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        // ImageList defined in CSS3 and onward
        if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(name).append('(').append(exp.toStringFromStart()).append(')');
            throw new InvalidParamException("notversion", sb.toString(),
                    ac.getCssVersionString(), ac);
        }
        ArrayList<CssValue> v = new ArrayList<CssValue>();
        CssValue val = exp.getValue();
        char op = exp.getOperator();

        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                // 0 is an acceptable value since CR-css-images-3-20191010
                val.getAngle();
            case CssTypes.CSS_ANGLE:
                v.add(val);
                if (op != COMMA) {
                    exp.starts();
                    throw new InvalidParamException("operator",
                            ((new Character(op)).toString()), ac);
                }
                exp.next();
                break;
            case CssTypes.CSS_IDENT:
                CssIdent ident = (CssIdent) val;
                if (to.equals(ident)) {
                    CssValueList vl = new CssValueList();
                    vl.add(to);
                    // we must now eat one or two valid idents
                    // this is boringly boring...
                    CssIdent v1 = null;
                    CssIdent v2 = null;
                    if (op != SPACE) {
                        exp.starts();
                        throw new InvalidParamException("operator",
                                ((new Character(op)).toString()), ac);
                    }
                    exp.next();
                    if (exp.end()) {
                        throw new InvalidParamException("few-value", name, ac);
                    }
                    val = exp.getValue();
                    op = exp.getOperator();
                    boolean isV1Vertical, isV2Vertical;
                    if (val.getType() != CssTypes.CSS_IDENT) {
                        throw new InvalidParamException("value",
                                val.toString(),
                                name, ac);
                    }
                    v1 = getLinearGradientIdent((CssIdent) val);
                    if (v1 == null) {
                        throw new InvalidParamException("value",
                                val.toString(),
                                name, ac);
                    }
                    vl.add(v1);
                    isV1Vertical = isVerticalIdent(v1);
                    exp.next();
                    if (exp.end()) {
                        throw new InvalidParamException("few-value", name, ac);
                    }
                    if (op == SPACE) {
                        // the operator is a space, we should have
                        // another
                        val = exp.getValue();
                        op = exp.getOperator();
                        if (val.getType() != CssTypes.CSS_IDENT) {
                            throw new InvalidParamException("value",
                                    val.toString(),
                                    name, ac);
                        }
                        v2 = getLinearGradientIdent((CssIdent) val);
                        if (v2 == null) {
                            throw new InvalidParamException("value",
                                    val.toString(),
                                    name, ac);
                        }
                        isV2Vertical = isVerticalIdent(v2);
                        if ((isV1Vertical && isV2Vertical) ||
                                (!isV1Vertical && !isV2Vertical)) {
                            throw new InvalidParamException("value",
                                    val.toString(),
                                    name, ac);
                        }
                        vl.add(v2);
                        exp.next();
                    }
                    v.add(vl);
                    if (op != COMMA) {
                        exp.starts();
                        throw new InvalidParamException("operator",
                                ((new Character(op)).toString()), ac);
                    }
                }
                if (top.equals(ident) || bottom.equals(ident)
                        || left.equals(ident) || right.equals(ident)) {
                    throw new InvalidParamException( //
                            "linear-gradient-missing-to",
                            "to " + ident, ident, ac);
                }
                break;
            default:
                // we defer errors to the next step
        }
        // now we a list of at least two color stops.
        ArrayList<CssValue> stops = parseColorStops(exp, ac);
        if (stops.size() < 2) {
            throw new InvalidParamException("few-value", name, ac);
        }

        v.addAll(stops);
        value = new CssLayerList(v);
    }

    /**
     * @param exp
     * @param ac
     * @throws InvalidParamException
     * @spec http://www.w3.org/TR/2012/CR-css3-images-20120417/#radial-gradient-type
     */
    public void setRadialGradient(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        name = "radial-gradient";
        _cache = null;
        _setRadialGradient(exp, ac);
    }

    /**
     * @param exp
     * @param ac
     * @throws InvalidParamException
     * @spec http://www.w3.org/TR/2012/CR-css3-images-20120417/#repeating-radial-gradient-type
     */
    public void setRepeatingRadialGradient(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        name = "repeating-radial-gradient";
        _cache = null;
        _setRadialGradient(exp, ac);
    }

    /**
     * @param exp
     * @param ac
     * @throws InvalidParamException
     * @spec http://www.w3.org/TR/2012/CR-css3-images-20120417/#linear-gradient-type
     */
    private void _setRadialGradient(CssExpression exp, ApplContext ac)
            throws InvalidParamException {
        // ImageList defined in CSS3 and onward
        if (ac.getCssVersion().compareTo(CssVersion.CSS3) < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(name).append('(').append(exp.toStringFromStart()).append(')');
            throw new InvalidParamException("notversion", sb.toString(),
                    ac.getCssVersionString(), ac);
        }
        ArrayList<CssValue> v = new ArrayList<CssValue>();
        CssValue val = exp.getValue();
        char op = exp.getOperator();

        // check if there is something before the color stops list
        boolean parse_prolog = false;
        switch (val.getType()) {
            case CssTypes.CSS_NUMBER:
                val.getLength();
            case CssTypes.CSS_LENGTH:
            case CssTypes.CSS_PERCENTAGE:
                parse_prolog = true;
                break;
            case CssTypes.CSS_IDENT:
                CssIdent id = (CssIdent) val;
                parse_prolog = at.equals(id) ||
                        (getShape(id) != null) ||
                        (getExtentIdent(id) != null);
                break;
        }

        if (parse_prolog) {
            CssExpression newexp = new CssExpression();
            boolean done = false;
            while (!done && !exp.end()) {
                val = exp.getValue();
                op = exp.getOperator();
                newexp.addValue(val);
                done = (op == COMMA);
                exp.next();
            }
            v.add(parseRadialProlog(newexp, ac));
        }
        // now we a list of at least two color stops.
        ArrayList<CssValue> stops = parseColorStops(exp, ac);
        if (stops.size() < 2) {
            throw new InvalidParamException("few-value", name, ac);
        }

        v.addAll(stops);
        value = new CssLayerList(v);
    }

    private CssValue parseRadialProlog(CssExpression expression,
                                       ApplContext ac)
            throws InvalidParamException {
        // the fun begins :)
        CssIdent shape = null;
        CssValue extend = null;
        CssValue extend2 = null;
        CssValue atPosition = null;

        ArrayList<CssValue> v = new ArrayList<CssValue>();

        CssValue val;
        boolean shapeInMiddle = false;

        while (!expression.end()) {
            val = expression.getValue();
            switch (val.getType()) {
                case CssTypes.CSS_PERCENTAGE:
                    if (shapeInMiddle) {
                        throw new InvalidParamException("value",
                                val, name, ac);
                    }
                    CssPercentage p = val.getPercentage();
                    if (!p.isPositive()) {
                        throw new InvalidParamException("negative-value",
                                val, name, ac);
                    }
                    if (extend == null) {
                        extend = val;
                        break;
                    }
                    if (extend2 == null) {
                        extend2 = val;
                        break;
                    }
                    throw new InvalidParamException("value",
                            val, name, ac);
                case CssTypes.CSS_NUMBER:
                case CssTypes.CSS_LENGTH:
                    if (shapeInMiddle) {
                        throw new InvalidParamException("value",
                                val, name, ac);
                    }
                    CssLength l = val.getLength();
                    if (!l.isPositive()) {
                        throw new InvalidParamException("negative-value",
                                val, name, ac);
                    }
                    if (extend == null) {
                        extend = val;
                        break;
                    } else {
                        if (extend.getType() == CssTypes.CSS_IDENT) {
                            // don't mix ident and length/percentage
                            throw new InvalidParamException("value",
                                    val, name, ac);
                        }
                    }
                    if (extend2 == null) {
                        extend2 = val;
                        break;
                    }
                    throw new InvalidParamException("value",
                            val, name, ac);
                case CssTypes.CSS_IDENT:
                    CssIdent id = (CssIdent) val;
                    // final 'at'
                    if (at.equals(id)) {
                        CssExpression exp = new CssExpression();
                        expression.next();
                        while (!expression.end()) {
                            exp.addValue(expression.getValue());
                            expression.next();
                        }
                        atPosition = checkPosition(exp, ac);
                        break;
                    }
                    if (shape == null) {
                        shape = getShape(id);
                        if (shape != null) {
                            shapeInMiddle = (expression.getCount() != expression.getRemainingCount());
                            break;
                        }
                    }
                    if (extend == null) {
                        extend = getExtentIdent(id);
                        if (extend != null) {
                            if (shapeInMiddle) {
                                throw new InvalidParamException("value",
                                        val, name, ac);
                            }
                            break;
                        }
                    }
                    // unrecognized ident
                default:
                    throw new InvalidParamException("value",
                            val.toString(),
                            name, ac);

            }
            expression.next();
        }
        // extra checks...
        // circle can have at most one extend and it must not be a percentage
        if (shape == circle && (extend2 != null || (extend != null && extend.getType() == CssTypes.CSS_PERCENTAGE))) {
            throw new InvalidParamException("value",
                    expression.toStringFromStart(), name, ac);
        }
        // ellipsis must have one extent ident or two (percentage/length)
        if (shape == ellipse && (extend2 == null && (extend != null && extend.getType() != CssTypes.CSS_IDENT))) {
            throw new InvalidParamException("value",
                    expression.toStringFromStart(), name, ac);
        }
        // if shape is null, it's a circle
        if (shape == null && extend2 == null && extend != null && extend.getType() == CssTypes.CSS_PERCENTAGE) {
            throw new InvalidParamException("value",
                    expression.toStringFromStart(), name, ac);
        }
        if (shape != null) {
            v.add(shape);
        }
        if (extend != null) {
            v.add(extend);
            if (extend2 != null) {
                v.add(extend2);
            }
        }
        if (atPosition != null) {
            v.add(at);
            v.add(atPosition);
        }
        return (v.size() == 1) ? v.get(0) : new CssValueList(v);
    }

    private final ArrayList<CssValue> parseColorStops(CssExpression expression,
                                                      ApplContext ac)
            throws InvalidParamException {
        ArrayList<CssValue> v = new ArrayList<CssValue>();
        CssValue val;
        char op;
        CssColor stopcol;
        CssValue stopcolv;
        CssValue stopval;

        while (!expression.end()) {
            val = expression.getValue();
            op = expression.getOperator();

            switch (val.getType()) {
                case CssTypes.CSS_HASH_IDENT:
                    stopcol = new CssColor();
                    stopcol.setShortRGBColor(ac, val.toString());
                    stopcolv = stopcol;
                    break;
                case CssTypes.CSS_IDENT:
                    if (CssColorCSS3.currentColor.equals((CssIdent) val)) {
                        stopcolv = CssColorCSS3.currentColor;
                        break;
                    }
                    stopcol = new CssColor();
                    stopcol.setIdentColor(ac, val.toString());
                    stopcolv = stopcol;
                    break;
                case CssTypes.CSS_COLOR:
                    stopcolv = val;
                    break;
                default:
                    throw new InvalidParamException("value", val.toString(),
                            "color", ac);
            }
            if (op == SPACE && expression.getRemainingCount() > 1) {
                expression.next();
                stopval = expression.getValue();
                op = expression.getOperator();

                switch (stopval.getType()) {
                    case CssTypes.CSS_NUMBER:
                        stopval.getLength();
                    case CssTypes.CSS_LENGTH:
                    case CssTypes.CSS_PERCENTAGE:
                        ArrayList<CssValue> stop = new ArrayList<CssValue>(2);
                        stop.add(stopcolv);
                        stop.add(stopval);
                        v.add(new CssValueList(stop));
                        break;
                    default:
                        throw new InvalidParamException("value", val.toString(),
                                "color-stop", ac);
                }
            } else {
                v.add(stopcolv);
            }
            expression.next();
            if (!expression.end() && op != COMMA) {
                expression.starts();
                throw new InvalidParamException("operator",
                        ((new Character(op)).toString()), ac);
            }
        }
        return v;
    }

    private CssValue checkPosition(CssExpression expression, ApplContext ac)
            throws InvalidParamException {
        switch (ac.getCssVersion()) {
            case CSS3:
                return CssBackgroundPosition.checkSyntax(ac, expression, name);
            default:
                StringBuilder sb = new StringBuilder();
                sb.append(name).append('(').append(expression.toStringFromStart()).append(')');
                throw new InvalidParamException("notversion", sb.toString(),
                        ac.getCssVersionString(), ac);
        }
    }

    /**
     * Returns the value
     */
    public Object get() {
        // @@TODO
        return null;
    }

    /**
     * Returns the name of the function
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
        if (_cache == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(name).append('(').append(value).append(')');
            _cache = sb.toString();
        }
        return _cache;
    }

    /**
     * Compares two values for equality.
     *
     * @param other The other value.
     */
    public boolean equals(Object other) {
        // @@FIXME
        return (other instanceof CssImage &&
                this.name.equals(((CssImage) other).name) &&
                this.value.equals(((CssImage) other).value));
    }
}
