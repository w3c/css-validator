//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.user;

import java.util.Enumeration;

import org.w3c.css.util.Warnings;
import org.w3c.css.parser.CssPrinterStyle;

/**
 * @version $Revision$
 */
public class Css2Style extends org.w3c.css.table.Css2Style {

    CursorCSS2 cursorCSS2;
    Cursor cursor;
    CursorATSC cursorATSC;

    Outline outline = new Outline();
    OutlineATSC outlineATSC = new OutlineATSC();
        
    /**
     * Get the cursor property
     */
    public final CursorCSS2 getCursorCSS2() {
	if (cursorCSS2 == null) {
	    cursorCSS2 = (CursorCSS2) style.CascadingOrder(new CursorCSS2(), 
						   style, selector);
	}
	return cursorCSS2;
    }

    public final Cursor getCursor() {
	if (cursor == null) {
	    cursor = (Cursor) style.CascadingOrder(new Cursor(), style,
						   selector);
	}
	return cursor;
    }

    public final CursorATSC getCursorATSC() {
	if (cursorATSC == null) {
	    cursorATSC = (CursorATSC) style.CascadingOrder(new CursorATSC(), style,
						   selector);
	}
	return cursorATSC;
    }

    /**
     * Get the outline-style property
     */  
    public final OutlineStyle getOutlineStyle() {
        if (outline.style == null) {
            outline.style = 
                (OutlineStyle) style.CascadingOrder(new OutlineStyle(), 
                                                    style, selector);
        }
        return outline.style;
    }

    public final OutlineStyleATSC getOutlineStyleATSC() {
        if (outlineATSC.style == null) {
            outlineATSC.style = 
                (OutlineStyleATSC) style.CascadingOrder(new OutlineStyleATSC(), 
                                                    style, selector);
        }
        return outlineATSC.style;
    }

    /**
     * Get the outline-width property
     */  
    public final OutlineWidth getOutlineWidth() {
        if (outline.width == null) {
            outline.width = 
                (OutlineWidth) style.CascadingOrder(new OutlineWidth(), 
                                                    style, selector);
        }
        return outline.width;
    }

    public final OutlineWidthATSC getOutlineWidthATSC() {
        if (outlineATSC.width == null) {
            outlineATSC.width = 
                (OutlineWidthATSC) style.CascadingOrder(new OutlineWidthATSC(), 
                                                    style, selector);
        }
        return outlineATSC.width;
    }

    /**
     * Get the outline-color property
     */  
    public final OutlineColor getOutlineColor() {
        if (outline.color == null) {
            outline.color = 
                (OutlineColor) style.CascadingOrder(new OutlineColor(), 
                                                    style, selector);
        }
        return outline.color;
    }

    public final OutlineColorATSC getOutlineColorATSC() {
        if (outlineATSC.color == null) {
            outlineATSC.color = 
                (OutlineColorATSC) style.CascadingOrder(new OutlineColorATSC(), 
                                                    style, selector);
        }
        return outlineATSC.color;
    }

    /**
     * Get the outline property
     */
    public final Outline getOutline() {
	if (outline.style == null) {
	    outline.style = getOutlineStyle();
	}
	if (outline.width == null) {
	    outline.width = getOutlineWidth();
	}
	if (outline.color == null) {
	    outline.color = getOutlineColor();
	}
        return outline;
    }

    public final OutlineATSC getOutlineATSC() {
	if (outlineATSC.style == null) {
	    outlineATSC.style = getOutlineStyleATSC();
	}
	if (outlineATSC.width == null) {
	    outlineATSC.width = getOutlineWidthATSC();
	}
	if (outlineATSC.color == null) {
	    outlineATSC.color = getOutlineColorATSC();
	}
        return outlineATSC;
    }

    /**
     * Print this style.
     *
     * @param printer The printer interface.
     */  
    public void print(CssPrinterStyle printer) {
	super.print(printer);
	if (cursor != null) {
	    cursor.print(printer);
	}
	if (cursorATSC != null) {
	    cursorATSC.print(printer);
	}
	if (cursorCSS2 != null) {
	    cursorCSS2.print(printer);
	}
	if (outline != null) {
	    outline.print(printer);
	}
	if (outlineATSC != null) {
	    outlineATSC.print(printer);
	}
    }
}
