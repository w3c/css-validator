//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.*;

/**
 *   <H4>
 *      &nbsp;&nbsp; 'font'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> [ &lt;font-style&gt; || &lt;font-variant&gt; ||
 *   &lt;font-weight&gt; ]? &lt;font-size&gt; [ / &lt;line-height&gt; ]?
 *   &lt;font-family&gt;<BR>
 *   <EM>Initial:</EM> not defined for shorthand properties<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> allowed on &lt;font-size&gt; and
 *   &lt;line-height&gt;<BR>
 *   <P>
 *   The 'font' property is a shorthand property for setting
 *   'font-style',
 *   'font-variant',
 *   'font-weight',
 *   'font-size',
 *   'line-height' and
 *   'font-family' at the same place in the style sheet.
 *   The syntax of this property is based on a traditional typographical
 *   shorthand notation to set multiple properties related to fonts.
 *   <P> For a definition of allowed and initial values, see the previously
 *   defined properties. Properties for which no values are given are set to
 *   their initial value.
 *   <PRE>
 *   P { font: 12pt/14pt sans-serif }
 *   P { font: 80% sans-serif }
 *   P { font: x-large/110% "new century schoolbook", serif }
 *   P { font: bold italic large Palatino, serif }
 *   P { font: normal small-caps 120%/120% fantasy }
 *   </PRE>
 *   <P> In the second rule, the font size percentage value ('80%') refers to
 *   the font size of the parent element. In the third rule, the line height
 *   percentage refers to the font size of the element itself.
 *   <P> In the first three rules above, the 'font-style', 'font-variant' and
 *   'font-weight' are not explicitly mentioned, which means they are all three
 *   set to their initial value ('normal'). The fourth rule sets the
 *   'font-weight' to 'bold', the 'font-style' to 'italic' and implicitly sets
 *   'font-variant' to 'normal'.
 *   <P> The fifth rule sets the 'font-variant' ('small-caps'), the 'font-size'
 *   (120% of the parent's font), the 'line-height' (120% times the font size)
 *   and the 'font-family' ('fantasy'). It follows that the keyword 'normal'
 *   applies to the two remaining properties: 'font-style' and 'font-weight'.
 *
 * @see CssFontStyle
 * @see CssFontVariant
 * @see CssFontWeight
 * @see CssFontSize
 * @see CssLineHeight
 * @see CssFontFamily
 * @see CssPercentage
 * @see CssLength
 * @version $Revision$
 */
public class CssFont extends CssProperty
    implements CssOperator, CssFontConstant {

    CssValue value;

    CssFontStyle   fontStyle;
    CssFontVariant fontVariant;
    CssFontWeight  fontWeight;

    CssFontSize    fontSize;
    CssLineHeight  lineHeight;
    CssFontFamily  fontFamily;

    // internal hack for strings comparaison
    private static int[] hash_values;

    static CssIdent normal = new CssIdent("normal");

    /**
     * Create a new CssFont
     */
    public CssFont() {
    }

    /**
     * Creates a new CssFont
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */
    public CssFont(ApplContext ac, CssExpression expression,boolean check)
    	throws InvalidParamException {

	CssValue val = expression.getValue();
	char op = SPACE;
	boolean find = true;
	int max_values = 3;

	int normalNb = 0;

	if (val instanceof CssIdent) {
	    CssIdent ident = checkIdent((CssIdent) val);
	    if (ident != null) {
		if(expression.getCount() > 1) {
		    throw new InvalidParamException("unrecognize", ac);
		}
		value = ident;
		expression.next();
		return;
	    } // else continue;
	}

	while (find && max_values-- > 0) {
	    find = false;
	    val = expression.getValue();
	    op = expression.getOperator();

	    if (val == null) {
		throw new InvalidParamException("few-value", getPropertyName(), ac);
	    }

	    if(val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }

	    if(val.equals(normal)) {
		normalNb++;
		expression.next();
		find = true;
	    }

	    if (!find && fontStyle == null) {
		try {
		    fontStyle = new CssFontStyle(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		}
	    }

	    if (!find && fontVariant == null) {
		try {
		    fontVariant = new CssFontVariant(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		}
	    }

	    if (!find && fontWeight == null) {
		try {
		    fontWeight = new CssFontWeight(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		    // we have now (or not)
		    // [ 'font-style' || 'font-variant' || 'font-weight' ]?
		    //break;
		}
	    }

	    if (find && op != SPACE) {
		throw new InvalidParamException("operator",
						((new Character(op)).toString()), ac);
	    }
	}

	// "normal" values
	CssExpression normalExpr = new CssExpression();
	normalExpr.addValue(normal);

	for(int i = 0; i < normalNb; i++) {
	    if (fontStyle == null) {
		fontStyle = new CssFontStyle(ac, normalExpr);
		normalExpr.starts();
	    }
	    else if (fontVariant == null) {
		fontVariant = new CssFontVariant(ac, normalExpr);
		normalExpr.starts();
	    }
	    else if (fontWeight == null) {
		fontWeight = new CssFontWeight(ac, normalExpr);
		normalExpr.starts();
	    }
	}

	val = expression.getValue();
	op = expression.getOperator();

	if (val == null) {
	    throw new InvalidParamException("few-value", getPropertyName(), ac);
	}

	if(val.equals(inherit)) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	fontSize = new CssFontSize(ac, expression);

    if (val.getType() == CssTypes.CSS_SWITCH) {
        expression.next();
        val = expression.getValue();
	    op = expression.getOperator();
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    lineHeight = new CssLineHeight(ac, expression);
	}

	if(expression.getValue().equals(inherit)) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	if (op == SPACE && expression.getValue() != null) {
	    fontFamily = new CssFontFamily(ac, expression, true);
	} else {
	    expression.starts();
	    throw new InvalidParamException("few-value", expression.toString(), ac);
	}
	setByUser();
    }

    public CssFont(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return null;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (value != null) {
	    return value.toString();
	} else {
	    String ret = "";
	    if (fontStyle != null) {
		ret += " " + fontStyle;
	    }
	    if (fontVariant != null) {
		ret += " " + fontVariant;
	    }
	    if (fontWeight != null) {
		ret += " " + fontWeight;
	    }
	    if(fontSize != null) {
		ret += " " + fontSize;
	    }
	    if (lineHeight != null) {
		ret += "/" + lineHeight;
	    }
	    if (fontFamily.size() != 0) {
		ret += " " + fontFamily;
	    }
	    return ret.substring(1);
	}
    }

    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */
    public void setImportant() {
	super.setImportant();
	if (value == null) {
	    if(fontStyle != null)
		fontStyle.important = true;
	    if(fontVariant != null)
		fontVariant.important = true;
	    if(fontWeight != null)
		fontWeight.important = true;
	    if(fontSize != null)
		fontSize.important = true;
	    if(lineHeight != null)
		lineHeight.important = true;
	    if(fontFamily != null)
		fontFamily.important = true;
	}
    }

    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	if (value != null) {
	    return super.getImportant();
	} else {
	    return ((fontStyle == null || fontStyle.important) &&
		    (fontVariant == null || fontVariant.important) &&
		    (fontWeight == null || fontWeight.important) &&
		    (fontSize == null || fontSize.important) &&
		    (lineHeight == null || lineHeight.important) &&
		    (fontFamily == null || fontFamily.important));
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (value != null) {
	    ((Css1Style) style).cssFont.value = value;
	} else {
	    if(fontStyle != null)
		fontStyle.addToStyle(ac, style);
	    if(fontVariant != null)
		fontVariant.addToStyle(ac, style);
	    if(fontSize != null)
		fontSize.addToStyle(ac, style);
	    if(fontWeight != null)
		fontWeight.addToStyle(ac, style);
	    if(lineHeight != null)
		lineHeight.addToStyle(ac, style);
	    if(fontFamily != null)
		fontFamily.addToStyle(ac, style);
	}
    }

    /**
     * Update the source file and the line.
     * Overrides this method for a macro
     *
     * @param line The line number where this property is defined
     * @param source The source file where this property is defined
     */
    public void setInfo(int line, String source) {
	super.setInfo(line, source);
	if (value == null) {
	    if(fontStyle != null)
		fontStyle.setInfo(line, source);
	    if(fontVariant != null)
		fontVariant.setInfo(line, source);
	    if(fontWeight != null)
		fontWeight.setInfo(line, source);
	    if(fontSize != null)
		fontSize.setInfo(line, source);
	    if(lineHeight != null)
		lineHeight.setInfo(line, source);
	    if(fontFamily != null)
		fontFamily.setInfo(line, source);
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
	return false;
    }

    /**
     * Set the context.
     * Overrides this method for a macro
     *
     * @see org.w3c.css.css.CssCascadingOrder#order
     * @see org.w3c.css.css.StyleSheetParser#handleRule
     */
    public void setSelectors(CssSelectors selector) {
	super.setSelectors(selector);
	if (fontStyle != null) {
	    fontStyle.setSelectors(selector);
	}
	if (fontVariant != null) {
	    fontVariant.setSelectors(selector);
	}
	if (fontWeight != null) {
	    fontWeight.setSelectors(selector);
	}
	if (fontSize != null) {
	    fontSize.setSelectors(selector);
	}
	if (lineHeight != null) {
	    lineHeight.setSelectors(selector);
	}
	if (fontFamily != null) {
	    fontFamily.setSelectors(selector);
	}
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getFont();
	} else {
	    return ((Css1Style) style).cssFont;
	}
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "font";
    }

    private CssIdent checkIdent(CssIdent ident) {
	int hash = ident.hashCode();
	for (int i = 0; i < CssFontConstant.FONT.length; i++) {
	    if (hash_values[i] == hash) {
		return ident;
	    }
	}

	return null;
    }

    static {
	hash_values = new int[CssFontConstant.FONT.length];
	for (int i=0; i<CssFontConstant.FONT.length; i++)
	    hash_values[i] = CssFontConstant.FONT[i].hashCode();
    }
}





