// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2017.
// Please first read the full copyright statement at:
// https://www.w3.org/Consortium/Legal/2015/copyright-software-and-document

package org.w3c.css.properties.css3;

import java.util.ArrayList;
import java.util.Arrays;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssValueList;

/**
 * <P>
 * <EM>Value:</EM> auto | none | [ [ pan-x | pan-left | pan-right ] || [ pan-y |
 * pan-up | pan-down ] || pinch-zoom ] | manipulation<BR>
 * <EM>Initial:</EM> auto<BR>
 * <EM>Applies to:</EM> all elements except: non-replaced inline elements, table
 * rows, row groups, table columns, and column groups.<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentages:</EM> no<BR>
 * <EM>Media:</EM> :visual
 * <P>
 * The touch-action CSS property determines whether touch input <EM>MAY</EM>
 * trigger default behavior supplied by user agent.<BR>
 * This includes, but is not limited to, behaviors such as panning or zooming.
 */

public class CssTouchAction extends CssProperty {

    CssValue touchaction;

    /* See https://w3c.github.io/pointerevents/#the-touch-action-css-property
     * and https://compat.spec.whatwg.org/#touch-action (for `pinch-zoom`). */

    CssIdent auto = new CssIdent("auto");
    CssIdent none = new CssIdent("none");
    CssIdent panX = new CssIdent("pan-x");
    CssIdent panLeft = new CssIdent("pan-left");
    CssIdent panRight = new CssIdent("pan-right");
    CssIdent panY = new CssIdent("pan-y");
    CssIdent panUp = new CssIdent("pan-up");
    CssIdent panDown = new CssIdent("pan-down");
    CssIdent pinchZoom = new CssIdent("pinch-zoom");
    CssIdent manipulation = new CssIdent("manipulation");

    /**
     * Create a new CssTouchAction
     */
    public CssTouchAction() {
        touchaction = new CssValueList(
                new ArrayList<CssValue>(Arrays.asList(auto)));
    }

    /**
     * Create a new CssTouchAction
     *
     * @param expression
     *            The expression for this property
     * @exception InvalidParamException
     *                Incorrect value
     */
    public CssTouchAction(ApplContext ac, CssExpression expression,
            boolean check) throws InvalidParamException {
        CssValue val;
        ArrayList<CssValue> values = new ArrayList<CssValue>();
        boolean gotHorizontal = false;
        boolean gotVertical = false;
        boolean gotPinchZoom = false;

        setByUser();

        while (!expression.end()) {
            val = expression.getValue();

            if (val.getType() != CssTypes.CSS_IDENT) {
                throw new InvalidParamException("value", val, getPropertyName(),
                        ac);
            } else if (val.equals(auto) || val.equals(none)
                    || val.equals(manipulation)) {
                if (expression.getCount() > 1) {
                    throw new InvalidParamException("unrecognize", ac);
                }
            } else if (val.equals(panX) //
                    || val.equals(panLeft) //
                    || val.equals(panRight)) {
                if (gotHorizontal) {
                    throw new InvalidParamException("unrecognize", ac);
                }
                gotHorizontal = true;
            } else if (val.equals(panY) //
                    || val.equals(panUp) //
                    || val.equals(panDown)) {
                if (gotVertical) {
                    throw new InvalidParamException("unrecognize", ac);
                }
                gotVertical = true;
            } else if (val.equals(pinchZoom)) {
                if (gotPinchZoom) {
                    throw new InvalidParamException("unrecognize", ac);
                }
                gotPinchZoom = true;
            } else {
                throw new InvalidParamException("value", val.toString(),
                        getPropertyName(), ac);
            }
            values.add(val);
            expression.next();
        }
        touchaction = new CssValueList(values);
    }

    public CssTouchAction(ApplContext ac, CssExpression expression)
            throws InvalidParamException {
        this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style
     *            The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
        if (((Css3Style) style).cssTouchAction != null)
            style.addRedefinitionWarning(ac, this);
        ((Css3Style) style).cssTouchAction = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style
     *            The style where the property is
     * @param resolve
     *            if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
        if (resolve) {
            return ((Css3Style) style).getAlignmentAdjust();
        } else {
            return ((Css3Style) style).cssTouchAction;
        }
    }

    /**
     * Compares two properties for equality.
     *
     * @param value
     *            The other property.
     */
    public boolean equals(CssProperty property) {
        return (property instanceof CssTouchAction
                && touchaction.equals(((CssTouchAction) property).touchaction));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
        return "touch-action";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
        return touchaction;
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
        return touchaction.toString();
    }

    /**
     * Is the value of this property a default value It is used by all macro for
     * the function <code>print</code>
     */
    public boolean isDefault() {
        return "auto".equals(touchaction.toString());
    }

}
