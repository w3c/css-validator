//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'background-position'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> [&lt;percentage&gt; | &lt;length&gt;]{1,2} | [top | center
 *   | bottom] || [left | center | right]<BR>
 *   <EM>Initial:</EM> 0% 0%<BR>
 *   <EM>Applies to:</EM> block-level and replaced elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to the size of the element itself<BR>
 *   <P> If a background image has been specified, the value of
 *   'background-position' specifies its initial position.
 *   <P> With a value pair of '0% 0%', the upper left corner of the image is
 *   placed in the upper left corner of the box that surrounds the content of
 *   the element (i.e., not the box that surrounds the padding, border or
 *   margin). A value pair of '100% 100%' places the lower right corner of the
 *   image in the lower right corner of the element. With a value pair of '14%
 *   84%', the point 14% across and 84% down the image is to be placed at the
 *   point 14% across and 84% down the element.
 *   <P> With a value pair of '2cm 2cm', the upper left corner of the image is
 *   placed 2cm to the right and 2cm below the upper left corner of the element.
 *   <P> If only one percentage or length value is given, it sets the horizontal
 *   position only, the vertical position will be 50%. If two values are given,
 *   the horizontal position comes first. Combinations of length and percentage
 *   values are allowed, e.g. '50% 2cm'. Negative positions are allowed.
 *   <P> One can also use keyword values to indicate the position of the
 *   background image. Keywords cannot be combined with percentage values, or
 *   length values.  The possible combinations of keywords and their
 *   interpretations are as follows:

 *   <UL>
 *     <LI>
 *       'top left' and 'left top' both mean the same as '0% 0%'.
 *     <LI>
 *       'top', 'top center' and 'center top' mean the same as '50% 0%'.
 *     <LI>
 *       'right top' and 'top right' mean the same as '100% 0%'.
 *     <LI>
 *       'left', 'left center' and 'center left' mean the same as '0% 50%'.
 *     <LI>
 *       'center' and 'center center' mean the same as '50% 50%'.
 *     <LI>
 *       'right', 'right center' and 'center right' mean the same as '100% 50%'.
 *     <LI>
 *       'bottom left' and 'left bottom' mean the same as '0% 100%'.
 *     <LI>
 *       'bottom', 'bottom center' and 'center bottom' mean the same as 
         '50% 100%'.
 *     <LI>
 *       'bottom right' and 'right bottom' mean the same as '100% 100%'.
 *   </UL>
 *   <P>
 *   examples:
 *   <PRE>
 *   BODY { background: url(banner.jpeg) right top }    / * 100%   0% * /
 *   BODY { background: url(banner.jpeg) top center }   / *  50%   0% * /
 *   BODY { background: url(banner.jpeg) center }       / *  50%  50% * /
 *   BODY { background: url(banner.jpeg) bottom }       / *  50% 100% * /
 *  </PRE>
 *   <P>
 *   If the background image is fixed with regard to the canvas (see the
 *   'background-attachment' property above), the image is placed relative to
 *   the canvas instead of the element. E.g.:
 *   <PRE>
 *   BODY {
 *     background-image: url(logo.png);
 *     background-attachment: fixed;
 *     background-position: 100% 100%;
 *   }
 *  </PRE>
 *   <P>
 *   In the example above, the image is placed in the lower right corner of the
 *   canvas.
 * @version $Revision$
 * @see CssBackgroundAttachment
 */
public class CssBackgroundPositionCSS2 extends CssProperty
        implements CssBackgroundConstants, CssOperator {

    CssValue first;
    CssValue second;

    /**
     * Create a new CssBackgroundPositionCSS2
     */
    public CssBackgroundPositionCSS2() {
	first  = DefaultValue0;
	second = DefaultValue0;
    }

    /**
     * Creates a new CssBackgroundPositionCSS2
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBackgroundPositionCSS2(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	int nb_val =  expression.getCount();
	boolean first_is_keyword, second_is_keyword;
	int index_first, index_second;

	if(check && nb_val > 2) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	CssValue nextval;
	CssValue val     = expression.getValue();
	char     op      = expression.getOperator();

	if (op != SPACE)
	    throw new  InvalidParamException("operator",
					     ((new Character(op)).toString()),
					     ac);
	
	if (nb_val == 1) { /* default to 'center' */
	    second_is_keyword = true;
	    second = null;
	}

	index_first = -1;
	index_second = -1;

	first = null;
	switch(val.getType()) {
	case CssTypes.CSS_IDENT:
	    /* check for inherit, only one value allowed */
	    if (inherit.equals(val)) {
		if(nb_val > 1) {
		    throw new InvalidParamException("unrecognize", ac);
		}
		first = inherit;
		second = inherit;
		expression.next();
		return;
	    }
	    first_is_keyword = true;
	    // FIXME do something better
	    index_first = IndexOfIdent((String) val.get());
	    if(index_first == -1) {
		throw new InvalidParamException("value", val,
						"background-position", ac);
	    }
	    first = val;
	    break;
	case CssTypes.CSS_NUMBER:
	    val = ((CssNumber) val).getLength();
	case CssTypes.CSS_PERCENTAGE:
	case CssTypes.CSS_LENGTH:
	    first_is_keyword = false;
	    first = val;
	    break;
	default:
	    throw new InvalidParamException("value", val,
					    "background-position", ac);
	}
	if (nb_val == 1) {
	    if (first != null) {
		expression.next();
	    }
	    return;
	}
	/* now check the second value */
	nextval = expression.getNextValue();
	second = null;
	switch(nextval.getType()) {
	case CssTypes.CSS_IDENT:
	    if (inherit.equals(nextval)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    index_second = IndexOfIdent((String) nextval.get());
	    if(index_second == -1 && check) {
		throw new InvalidParamException("value", nextval, 
						"background-position", ac);
	    }
	    if (first_is_keyword) {
		// two keywords, check that they are compatible
		if((isHorizontal(index_first) && isVertical(index_second)) ||
		   (isHorizontal(index_second) && isVertical(index_first))) {
		    second = nextval;
		} else {
		    if (check) {
			throw new InvalidParamException("incompatible",
							val, nextval, ac);
		    }
		}
	    } else {
	// first was not a keyword, so second should be vertical
	// http://www.w3.org/TR/CSS21/colors.html#propdef-background-position
		if (isVertical(index_second)) {
		    second = nextval;
		} else {
        // FIXME, should we create a better error msg, like "wrong order" ?
		    if (check) {
			throw new InvalidParamException("incompatible",
							val, nextval, ac);
		    }
		} 
	    }
	    break;
	case CssTypes.CSS_NUMBER:
	    nextval = ((CssNumber) nextval).getLength();
	case CssTypes.CSS_PERCENTAGE:
	case CssTypes.CSS_LENGTH:
	    if (first_is_keyword) {
       	// check that the first is indeed horizontal
       	// http://www.w3.org/TR/CSS21/colors.html#propdef-background-position
		if (!isHorizontal(index_first) && check) {
		    throw new InvalidParamException("incompatible",
						    val, nextval, ac);
		} 
	    }
	    second = nextval;
	    break;
	default:
	    throw new InvalidParamException("value", nextval,
					    "background-position", ac);
	}
	if (first != null) {
	    expression.next();
	    if (second != null) {
		expression.next();
	    }
	}   
    }

    protected boolean isHorizontal(int index) {
	return index == POSITION_LEFT || index == POSITION_RIGHT ||
		index == POSITION_CENTER;
    }

    protected boolean isVertical(int index) {
	return index == POSITION_TOP || index == POSITION_BOTTOM ||
	index == POSITION_CENTER;
    }

    public CssBackgroundPositionCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * @return Returns the first.
     */
    public CssValue getFirst() {
        return first;
    }

    /**
     * @param first The first to set.
     */
    public void setFirst(CssValue first) {
        this.first = first;
    }

    /**
     * @return Returns the second.
     */
    public CssValue getSecond() {
        return second;
    }

    /**
     * @param second The second to set.
     */
    public void setSecond(CssValue second) {
        this.second = second;
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return first;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "background-position";
    }

    /**
     * Returns the horizontal value of the position
     */
    public CssValue getHorizontalPosition() {
	return first;
    }

    /**
     * Returns the vertical value of the position
     */
    public CssValue getVerticalPosition() {
	return second;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return first == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (first == inherit) {
	    return inherit.toString();
	} else {
	    StringBuilder sb = new StringBuilder();
	    if (first != null) {
		sb.append(first);
	    }
	    if (second != null) {
		if (first != null) {
		    sb.append(' ');
		}
		sb.append(second);
	    }
	    return sb.toString();
	}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBackgroundCSS2 cssBackground = ((Css1Style) style).cssBackgroundCSS2;
	if (cssBackground.position != null)
	    style.addRedefinitionWarning(ac, this);
	cssBackground.position = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBackgroundPositionCSS2();
	} else {
	    return ((Css1Style) style).cssBackgroundCSS2.position;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	if (!(property instanceof CssBackgroundPositionCSS2)) {
	    return false;
	}
	CssBackgroundPositionCSS2 cprop = (CssBackgroundPositionCSS2) property;
	return (first.equals(cprop.first) && second.equals(cprop.second));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return first.equals(DefaultValue0) && second.equals(DefaultValue0);
    }

    protected int IndexOfIdent(String ident) {
	int hash = ident.hashCode();
	for (int i = 0; i < POSITION.length; i++)
	    if (hash_values[i] == hash)
		return i;
	return -1;
    }

    static public boolean checkMatchingIdent(CssIdent idval) {
	for (int i=0 ; i < hash_values.length; i++) {
	    if (hash_values[i] == idval.hashCode()) {
		return true;
	    }
	}
	return false;
    }

    private static int[] hash_values;

    private static CssPercentage DefaultValue0 = new CssPercentage(0);

    static {
	hash_values = new int[POSITION.length];
	for (int i = 0; i < POSITION.length; i++)
	    hash_values[i] = POSITION[i].hashCode();
    }
}
