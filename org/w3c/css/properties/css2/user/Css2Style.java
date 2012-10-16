//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css2.user;

/**
 * @version $Revision$
 */
public class Css2Style extends org.w3c.css.properties.css2.table.Css2Style {

	CursorCSS2 cursorCSS2;
	Cursor cursor;
	CursorATSC cursorATSC;

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


}
