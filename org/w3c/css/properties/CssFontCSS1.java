//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 3.1  1997/08/29 13:13:45  plehegar
 * Freeze
 *
 * Revision 2.3  1997/08/26 13:51:56  plehegar
 * Added setSelectors()
 *
 * Revision 1.1  1997/07/16 16:06:44  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

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
public class CssFontCSS1 extends CssProperty 
    implements CssOperator, CssFontConstantCSS1 {
    
    CssValue value;

    CssFontStyleCSS1   fontStyle;
    CssFontVariantCSS1 fontVariant;
    CssFontWeightCSS1  fontWeight;
    
    CssFontSizeCSS1    fontSize;
    CssLineHeightCSS1  lineHeight;
    CssFontFamilyCSS1  fontFamily;
    
    // internal hack for strings comparaison
    private static int[] hash_values;
    
    /**
     * Create a new CssFontCSS1
     */
    public CssFontCSS1() {
    }  
    
    /**
     * Creates a new CssFont
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */  
    public CssFontCSS1(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = expression.getValue();
	char op = SPACE;
	boolean find = true;
	int max_values = 3;
	int normal = "normal".hashCode();

	while (find && max_values-- > 0) {
	    find = false;
	    val = expression.getValue();
	    op = expression.getOperator();
	    
	    if (val == null) {
		throw new InvalidParamException("few-value", getPropertyName(), ac);
	    }
	    
	    if (fontStyle == null) {
		try {
		    fontStyle = new CssFontStyleCSS1(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		}
	    }	
	    if (!find && fontVariant == null) {
		try {
		    fontVariant = new CssFontVariantCSS1(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		}
	    }
	    if (!find && fontWeight == null) {
		try {
		    fontWeight = new CssFontWeightCSS1(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		}
	    }
	    if (find && op != SPACE) {
		throw new InvalidParamException("operator", 
						((new Character(op)).toString()), ac);
	    }
	    
	}
	
	if (fontStyle == null) {
	    fontStyle = new CssFontStyleCSS1();
	}
	if (fontVariant == null) {
	    fontVariant = new CssFontVariantCSS1();
	}
	if (fontWeight == null) {
	    fontWeight = new CssFontWeightCSS1();
	}
	
	val = expression.getValue();
	op = expression.getOperator();
	
	    
	if (val == null) {
	    throw new InvalidParamException("few-value", getPropertyName(), ac);
	}
	    
	fontSize = new CssFontSizeCSS1(ac, expression);
	
	if (op == SLASH) {
	    op = expression.getOperator();
	    lineHeight = new CssLineHeightCSS1(ac, expression);
	} else {
	    lineHeight = new CssLineHeightCSS1();
	}
	
	if (op == SPACE && expression.getValue() != null) {
	    fontFamily = new CssFontFamilyCSS1(ac, expression);
	} else {
	    expression.starts();
	    throw new InvalidParamException("few-value", expression.toString(), ac);
	}

	setByUser();
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
	    if (fontStyle.isByUser()) {
		ret += " " + fontStyle;
	    }
	    if (fontVariant.isByUser()) {
		ret += " " + fontVariant;
	    }
	    if (fontWeight.isByUser()) {
		ret += " " + fontWeight;
	    }
	    ret += " " + fontSize;
	    if (lineHeight.isByUser()) {
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
	    fontStyle.important = true;
	    fontVariant.important = true;
	    fontWeight.important = true;
	    fontSize.important = true;
	    lineHeight.important = true;
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
     * Print this property.
     *
     * @param printer The printer
     * @see #toString()
     * @see #getPropertyName()
     */  
    public void print(CssPrinterStyle printer) {
	if (value != null) {
	    printer.print(this);
	} else if ((fontStyle != null && fontVariant != null &&
	     fontWeight != null && fontSize !=null &&
	     lineHeight != null && fontFamily != null) &&
	    (getImportant() ||
	     (!fontStyle.important &&
	      !fontVariant.important &&
	      !fontWeight.important &&
	      !fontSize.important &&
	      !lineHeight.important &&
	      !fontFamily.important))) {
	    printer.print(this);
	} else {
	    if (fontStyle != null) {
		fontStyle.print(printer);
	    }
	    if (fontVariant != null) {
		fontVariant.print(printer);
	    }
	    if (fontWeight != null) {
		fontWeight.print(printer);
	    }
	    if (fontSize != null) {
		fontSize.print(printer);
	    }
	    if (lineHeight != null) {
		lineHeight.print(printer);
	    }
	    if ((fontFamily != null) &&
		((fontFamily.isSoftlyInherited()) || (fontFamily.size() != 0))) {
		fontFamily.print(printer);
	    }
	}	
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (value != null) {
	    ((Css1Style) style).cssFontCSS1.value = value;
	} else {
	    fontStyle.addToStyle(ac, style);
	    fontVariant.addToStyle(ac, style);
	    fontSize.addToStyle(ac, style);
	    fontWeight.addToStyle(ac, style);
	    lineHeight.addToStyle(ac, style);
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
	    fontStyle.setInfo(line, source);
	    fontVariant.setInfo(line, source);
	    fontWeight.setInfo(line, source);
	    fontSize.setInfo(line, source);
	    lineHeight.setInfo(line, source);
	    fontFamily.setInfo(line, source);
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
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
	    return ((Css1Style) style).getFontCSS1();
	} else {
	    return ((Css1Style) style).cssFontCSS1;
	}
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "font";
    }

}





