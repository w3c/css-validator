//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log$
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:13:51  plehegar
 * Freeze
 *
 * Revision 2.3  1997/08/26 14:05:10  plehegar
 * Added setSelectors()
 *
 * Revision 2.2  1997/08/20 11:41:24  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:22  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:30:07  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:05  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/25 15:46:46  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssOperator;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

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
public class CssListStyleTV extends CssProperty implements CssOperator {

    CssListStyleTypeTV listStyleType;
    CssListStyleImageCSS2 listStyleImage;
    CssListStylePositionCSS2 listStylePosition;

    boolean inheritedValue;

    /**
     * Create a new CssListStyleTV
     */
    public CssListStyleTV() {
	// nothing to do
    }

    /**
     * Create a new CssListStyleTV
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssListStyleTV(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = expression.getValue();
	char op = SPACE;
	boolean find = true;

	setByUser();

	if (val.equals(inherit)) {
	    inheritedValue = true;
	    expression.next();
	    return;
	}

	while (find) {
	    find = false;
	    val = expression.getValue();
	    op = expression.getOperator();

	    if ((listStyleType == null)
		&& (val != null)) {
		try {
		    listStyleType = new CssListStyleTypeTV(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		}
	    }
	    if (!find
		&& (listStyleImage == null)
		&& (val != null)) {
		try {
		    listStyleImage = new CssListStyleImageCSS2(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		}
	    }
	    if (!find
		&& (val != null)
		&& (listStylePosition == null)) {
		try {
		    listStylePosition = new CssListStylePositionCSS2(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		}
	    }
	    if (op != SPACE) {
		throw new InvalidParamException("operator",
						((new Character(op)).toString()),
						ac);
	    }
	}

	if (listStyleType == null) {
	    listStyleType = new CssListStyleTypeTV();
	}

	if (listStyleImage == null) {
	    listStyleImage = new CssListStyleImageCSS2();
	}

	if (listStylePosition == null) {
	    listStylePosition = new CssListStylePositionCSS2();
	}
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
	    String ret = listStyleType.toString();
	    if (!listStyleImage.isDefault()) {
		ret += " " + listStyleImage;
	    }
	    if (!listStylePosition.isDefault()) {
		ret += " " + listStylePosition;
	    }
	    return ret;
	}
    }



    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */
    public void setImportant() {
	if (!inheritedValue) {
	    listStyleType.important = true;
	    listStyleImage.important = true;
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
	    listStyleType.addToStyle(ac, style);
	    listStyleImage.addToStyle(ac, style);
	    listStylePosition.addToStyle(ac, style);
	} else {
	    ((Css1Style) style).cssListStyleTV.inheritedValue = true;
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
	    return ((Css1Style) style).getListStyleTV();
	} else {
	    return ((Css1Style) style).cssListStyleTV;
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
	    listStyleType.setInfo(line, source);
	    listStyleImage.setInfo(line, source);
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
