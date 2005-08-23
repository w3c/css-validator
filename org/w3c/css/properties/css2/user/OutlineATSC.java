//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css2.user;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision$
 */
public class OutlineATSC extends UserProperty implements CssOperator {
    
    OutlineColorATSC color;
    OutlineWidthATSC width;
    OutlineStyleATSC style;

    boolean same;
    
    /**
     * Create a new OutlineATSC
     */
    public OutlineATSC() {
    }  
    
    /**
     * Create a new OutlineATSC
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public OutlineATSC(ApplContext ac, CssExpression expression,
	    boolean check)  throws InvalidParamException {
	
	if(check && expression.getCount() > 3) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
        char op = SPACE;
        boolean find = true;
        int max_values = 3;

	ac.getFrame().addWarning("atsc", val.toString());

	if (val.equals(inherit)) {
	    if(expression.getCount() > 1) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    this.same = true;
	    color = new OutlineColorATSC(ac, expression);
	    width = new OutlineWidthATSC();
	    width.value = inherit;
	    style = new OutlineStyleATSC();
	    style.value = OutlineStyleATSC.BORDERSTYLE.length - 1;
	    return;
	}
	
        while (find && max_values-- > 0) {
            find = false;
            val = expression.getValue();
            op = expression.getOperator();
            
            if(val != null && val.equals(inherit)) {
        	throw new InvalidParamException("unrecognize", ac);
            }
            
            if (val == null) {
                break;
	    }
            
            if (op != SPACE) {
                throw new InvalidParamException("operator", 
                                                ((new Character(op)).toString()), ac);
	    }
            
            if (style == null) {
                try {
                    style = new OutlineStyleATSC(ac, expression);
                    find = true;
		} catch (InvalidParamException e) {
		}
            }   
            if (!find && color == null) {
                try {
                    color = new OutlineColorATSC(ac, expression);
                    find = true;
                } catch (InvalidParamException e) {
                }
            }
            if (!find && width == null) {
        	width = new OutlineWidthATSC(ac, expression);
        	find = true;
            }
            if(val != null && !find) {
        	throw new InvalidParamException("unrecognize", ac);
            }
        }

	if (max_values >= 2) {
	    throw new InvalidParamException("few-value", getPropertyName(), ac);
	}
	/*
	if (color == null) {
	    color = new OutlineColorATSC();
	}
	if (width == null) {
	    width = new OutlineWidthATSC();
	}
	if (style == null) {
	    style = new OutlineStyleATSC();
	}
	*/
    }
    
    public OutlineATSC(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     * not useful
     */
    public Object get() {
	return color;
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "outline";
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	// outlineColor and outlineWidth must be not null !
	if (same) {
	    return color.toString();
	} else {
	    String ret ="";
	    if (color != null) {
		ret += " " + color;
	    }
	    if (width != null) {
		ret += " " + width;
		}
	    if (style != null) {
		ret += " "  + style;
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
	if(color != null)
	    color.setImportant();
	if(width != null)
	    width.setImportant();
	if(style != null)
	    style.setImportant();
    }
    
    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return ((width == null || width.getImportant())
		&& (color == null || color.getImportant())
		&& (style == null || style.getImportant()));
    }
    
    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */  
    public void print(CssPrinterStyle printer) {
	if ((color != null && width != null && style != null) &&
	    (getImportant() ||
	     (!color.getImportant()
	      && !style.getImportant()
	      && !width.getImportant()))) {
	    printer.print(this);
	} else {	
	    if (color != null) {
		color.print(printer);
	    }
	    if (width != null) {
		width.print(printer);
	    }
	    if (style != null) {
		style.print(printer);
	    }
	}
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style0) {
	// outlineColor and outlineWidth can't be null ...
	((Css2Style) style0).outline.same = same;
	if(color != null)
	    color.addToStyle(ac, style0);
	if(width != null)
	    width.addToStyle(ac, style0);
	if(style != null)
	    style.addToStyle(ac, style0);
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
	if(color != null)
	    color.setInfo(line, source);
	if(width != null)
	    width.setInfo(line, source);
	if(style != null)
	    style.setInfo(line, source);
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return false; // @FIXME
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
	if (color != null) {
	    color.setSelectors(selector);
	}
	if (width != null) {
	    width.setSelectors(selector);
	}
	if (style != null) {
	    style.setSelectors(selector);
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
	    return ((Css2Style) style).getOutlineATSC();
	} else {
	    return ((Css2Style) style).outlineATSC;
	}
    }
    
}
