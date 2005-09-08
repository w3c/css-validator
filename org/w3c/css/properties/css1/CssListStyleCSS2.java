//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *      &nbsp;&nbsp; 'list-style'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;keyword&gt; || &lt;position&gt; || &lt;url&gt;<BR>
 *   <EM>Initial:</EM> not defined for shorthand properties<BR>
 *   <EM>Applies to:</EM> elements with 'display' value 'list-item'<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   The 'list-style' property is a shorthand notation for setting the three
 *   properties 'list-style-type', 'list-style-image' and 'list-style-position'
 *   at the same place in the style sheet.
 *   <PRE>
 *   UL { list-style: upper-roman inside }
 *   UL UL { list-style: circle outside }
 *   LI.square { list-style: square }
 * </PRE>
 *   <P>
 *   Setting 'list-style' directly on 'LI' elements can have unexpected results.
 *   Consider:
 *   <PRE>
 *   &lt;STYLE TYPE="text/css"&gt;
 *     OL.alpha LI  { list-style: lower-alpha }
 *     UL LI        { list-style: disc }
 *   &lt;/STYLE&gt;
 *   &lt;BODY&gt;
 *     &lt;OL CLASS=alpha&gt;
 *       &lt;LI&gt;level 1
 *       &lt;UL&gt;
 *          &lt;LI&gt;level 2
 *       &lt;/UL&gt;
 *     &lt;/OL&gt;
 *   &lt;/BODY&gt;
 * </PRE>
 *   <P> Since the specificity (as defined in the <A
 *   HREF="#cascading-order">cascading order</A>) is higher for the first rule
 *   in the style sheet in the example above, it will override the second rule
 *   on all 'LI' elements and only 'lower-alpha' list styles will be used. It is
 *   therefore recommended to set 'list-style' only on the list type elements:
 *   <PRE>
 *   OL.alpha  { list-style: lower-alpha }
 *   UL        { list-style: disc }
 * </PRE>
 *   <P> In the above example, inheritance will transfer the 'list-style' values
 *   from 'OL' and 'UL' elements to 'LI' elements.
 *   <P>
 *   A URL value can be combined with any other value:
 *   <PRE>
 *   UL { list-style: url(http://png.com/ellipse.png) disc }
 * </PRE>
 *   <P> In the example above, the 'disc' will be used when the image is
 *   unavailable.
 *
 * @version $Revision$ 
 */
public class CssListStyleCSS2 extends CssProperty implements CssOperator {
    
    CssListStyleTypeCSS2 listStyleType;
    CssListStyleImageCSS2 listStyleImage;
    CssListStylePositionCSS2 listStylePosition;

    boolean inheritedValue;
    
    /**
     * Create a new CssListStyleCSS2
     */
    public CssListStyleCSS2() {
	// nothing to do
    }  
    
    /**
     * Create a new CssListStyleCSS2
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssListStyleCSS2(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	int exprLength = expression.getCount();
	
	if(check && exprLength > 3) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	char op = SPACE;
	boolean find = true;
	
	setByUser();

	if (val.equals(inherit)) {
	    if(exprLength > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    inheritedValue = true;
	    expression.next();
	    return;
	}
	
	while (find) {
	    find = false;
	    val = expression.getValue();
	    op = expression.getOperator();
	    
	    if(val != null && val.equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    
	    if ((listStyleType == null)	&& (val != null)) {
		try {
		    listStyleType = new CssListStyleTypeCSS2(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		}
	    }
	    if (!find && (listStyleImage == null) && (val != null)) {
		try {
		    listStyleImage = new CssListStyleImageCSS2(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		} 
	    }
	    if (!find && (val != null) && (listStylePosition == null)) {
		listStylePosition = new CssListStylePositionCSS2(ac, expression);
		find = true;
	    }
	    if(val != null && !find) {		
		throw new InvalidParamException("unrecognize", ac);
	    }
	    if (op != SPACE) {
		throw new InvalidParamException("operator", 
						((new Character(op)).toString()),
						ac);
	    }
	}
	/*
	if (listStyleType == null) {
	    listStyleType = new CssListStyleTypeCSS2();
	}
	
	if (listStyleImage == null) {
	    listStyleImage = new CssListStyleImageCSS2();
	}
	
	if (listStylePosition == null) {
	    listStylePosition = new CssListStylePositionCSS2();
	}
	*/
    }
    
    public CssListStyleCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * @return Returns the inheritedValue.
     */
    public boolean isInheritedValue() {
        return inheritedValue;
    }

    /**
     * @param inheritedValue The inheritedValue to set.
     */
    public void setInheritedValue(boolean inheritedValue) {
        this.inheritedValue = inheritedValue;
    }

    /**
     * @return Returns the listStyleImage.
     */
    public CssListStyleImageCSS2 getListStyleImage() {
        return listStyleImage;
    }

    /**
     * @param listStyleImage The listStyleImage to set.
     */
    public void setListStyleImage(CssListStyleImageCSS2 listStyleImage) {
        this.listStyleImage = listStyleImage;
    }

    /**
     * @return Returns the listStylePosition.
     */
    public CssListStylePositionCSS2 getListStylePosition() {
        return listStylePosition;
    }

    /**
     * @param listStylePosition The listStylePosition to set.
     */
    public void setListStylePosition(CssListStylePositionCSS2 listStylePosition) {
        this.listStylePosition = listStylePosition;
    }

    /**
     * @return Returns the listStyleType.
     */
    public CssListStyleTypeCSS2 getListStyleType() {
        return listStyleType;
    }

    /**
     * @param listStyleType The listStyleType to set.
     */
    public void setListStyleType(CssListStyleTypeCSS2 listStyleType) {
        this.listStyleType = listStyleType;
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return listStyleType;
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "list-style";
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return inheritedValue;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (inheritedValue) {
	    return inherit.toString();
	} else {
	    String ret = "";
	    if(listStyleType != null) {		
		ret = listStyleType.toString();
	    }
	    if (listStyleImage != null &&!listStyleImage.isDefault()) {
		ret += " " + listStyleImage;
	    }
	    if (listStylePosition != null && !listStylePosition.isDefault()) {
		ret += " " + listStylePosition;
	    }
	    return ret.trim();
	}
    }
    
    
    
    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */  
    public void setImportant() {
	if (!inheritedValue) {
	    if(listStyleType != null)		
		listStyleType.important = true;
	    if(listStyleImage != null)
		listStyleImage.important = true;
	    if(listStylePosition != null)
		listStylePosition.important = true;
	}
    }
    
    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return ((listStyleType == null || listStyleType.important) &&
		(listStyleImage == null || listStyleImage.important) &&
		(listStylePosition == null || listStylePosition.important));
    }
    
    /**
     * Print this property.
     *
     * @see #toString()
     * @see #getPropertyName()
     */  
    public void print(CssPrinterStyle printer) {
	if (inheritedValue) {
	    printer.print(this);
	} else if ((listStyleType != null && listStyleImage != null &&
		    listStylePosition != null) &&
		   (getImportant() ||
		    (!listStyleType.important &&
		     !listStyleImage.important &&
		     !listStylePosition.important))) {
	    printer.print(this);
	} else {
	    if (listStyleType != null) {
		listStyleType.print(printer);
	    }
	    if (listStyleImage != null) {
		listStyleImage.print(printer);
	    }
	    if (listStylePosition != null) {
		listStylePosition.print(printer);
	    }
	}
	
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
	if (listStyleType != null) {
	    listStyleType.setSelectors(selector);
	}
	if (listStyleImage != null) {
	    listStyleImage.setSelectors(selector);
	}
	if (listStylePosition != null) {
	    listStylePosition.setSelectors(selector);
	}
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (!inheritedValue) {
	    if(listStyleType != null)
		listStyleType.addToStyle(ac, style);
	    if(listStyleImage != null)
		listStyleImage.addToStyle(ac, style);
	    if(listStylePosition != null)
		listStylePosition.addToStyle(ac, style);
	} else {
	    ((Css1Style) style).cssListStyleCSS2.inheritedValue = true;
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
	    return ((Css1Style) style).getListStyleCSS2();
	} else {
	    return ((Css1Style) style).cssListStyleCSS2;
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
	if (!inheritedValue) {
	    if(listStyleType != null)
		listStyleType.setInfo(line, source);
	    if(listStyleImage != null)
		listStyleImage.setInfo(line, source);
	    if(listStylePosition != null)
		listStylePosition.setInfo(line, source);
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	// @FIXME
	return false;
    }
    
}
