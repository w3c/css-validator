//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * multicol
 *  <P>
 *  <EM>Value:</EM> &lt;column-width&gt; || &lt;column-counte&gt; 
 *  <EM>Initial:</EM>See individual properties<BR>
 *  <EM>Applies to:</EM>non-replaced block-level elements 
 *                      (except table elements), table cells, 
 *                      and inline-block elementsmulticol elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>N/A<BR>
 *  <EM>Media:</EM>:visual
 */

public class CssColumns extends CssProperty implements CssOperator {
    CssIdent value = null;
    CssColumnWidth width = null;
    CssColumnCount count = null;

    /**
     * Create a new CssColumns
     */
    public CssColumns() {
    }

    /**
     * Create a new CssColumns
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssColumns(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	CssValue val = expression.getValue();
	char op = SPACE;
	int nb_val = expression.getCount();
	int nb_auto = 0;

	if (check && nb_val > 2) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	setByUser();
	
	while (!expression.end()) {
	    boolean ok = true;
	    val = expression.getValue();
	    op = expression.getOperator();
	    if (op != SPACE) {
		throw new InvalidParamException("operator",
					      ((new Character(op)).toString()),
						ac);
	    }
	    switch (val.getType()) {
	    case CssTypes.CSS_NUMBER:
		if (count != null) {
		    throw new InvalidParamException("unrecognize", ac);
		}
		count = new CssColumnCount(ac, expression);
		break;
	    case CssTypes.CSS_LENGTH:
		if (width != null) {
		    throw new InvalidParamException("unrecognize", ac);
		}
		width = new CssColumnWidth(ac, expression);
		break;
	    case CssTypes.CSS_IDENT:
		if (inherit.equals((CssIdent)val)) {
		    if (nb_val > 1) {
			throw new InvalidParamException("unrecognize", ac);
		    }
		    value = inherit;
		    expression.next();
		    break;
		}
		if (CssColumnCount.auto.equals((CssIdent)val)) {
		    nb_auto++;
		    expression.next();
		    break;
		}
	    default:
		throw new InvalidParamException("value",
						expression.getValue(),
						getPropertyName(), ac);
	    }
	}
	if (nb_val == 1) {
	    if (nb_auto == 1) {
		value = CssIdent.getIdent("auto");
	    }
	} else {
	    if (nb_auto == 2) {
		count = new CssColumnCount();
		width = new CssColumnWidth();
	    } else if (nb_auto == 1) {
		if (count != null) {
		    width = new CssColumnWidth();
		} else {
		    count = new CssColumnCount();
		}
	    }
	}
    }

    public CssColumns(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssColumns != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssColumns = this;
	if (count != null) {
	    count.addToStyle(ac, style);
	}
	if (width != null) {
	    width.addToStyle(ac, style);
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
	    return ((Css3Style) style).getColumns();
	}
	else {
	    return ((Css3Style) style).cssColumns;
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
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "columns";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return null;
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	StringBuilder sb = new StringBuilder();
	boolean first = true;
	if (value != null) {
	    return value.toString();
	}
	if (count != null) {
	    sb.append(count);
	    first = false;
	}
	if (width != null) {
	    if (!first) {
		sb.append(' ');
	    }
	    sb.append(width);
	}
	return sb.toString();
    }
}
