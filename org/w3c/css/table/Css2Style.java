//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.table;

import java.util.Enumeration;

import org.w3c.css.util.Warnings;
import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.aural.ACssStyle;

/**
 * @version $Revision$
 */
public class Css2Style extends ACssStyle {

    RowSpan rowSpan;
    RowSpanATSC rowSpanATSC;
    ColumnSpan columnSpan;
    ColumnSpanATSC columnSpanATSC;
    CaptionSide captionSide;
    TableLayout tableLayout;
    TableLayoutATSC tableLayoutATSC;
    BorderCollapse borderCollapse;
    BorderCollapseATSC borderCollapseATSC;
    BorderSpacing borderSpacing;
    BorderSpacingATSC borderSpacingATSC;
    EmptyCells emptyCells;
    EmptyCellsATSC emptyCellsATSC;
    SpeakHeader speakHeader;
    SpeakHeaderATSC speakHeaderATSC;

    /**
     * Get the row-span property
     */
    public final RowSpan getRowSpan() {
	if (rowSpan == null) {
	    rowSpan = (RowSpan)
		style.CascadingOrder(new RowSpan(), style, selector);
	}
	return rowSpan;
    }

    public final RowSpanATSC getRowSpanATSC() {
	if (rowSpanATSC == null) {
	    rowSpanATSC = (RowSpanATSC)
		style.CascadingOrder(new RowSpanATSC(), style, selector);
	}
	return rowSpanATSC;
    }

    /**
     * Get the column-span property
     */

    public final ColumnSpan getColumnSpan() {
	if (columnSpan == null) {
	    columnSpan = (ColumnSpan)
		style.CascadingOrder(new ColumnSpan(), style, selector);
	}
	return columnSpan;
    }

    public final ColumnSpanATSC getColumnSpanATSC() {
	if (columnSpanATSC == null) {
	    columnSpanATSC = (ColumnSpanATSC)
		style.CascadingOrder(new ColumnSpanATSC(), style, selector);
	}
	return columnSpanATSC;
    }

    /**
     * Get the table-layout property
     */
    public final TableLayout getTableLayout() {
	if (tableLayout == null) {
	    tableLayout = (TableLayout)
		style.CascadingOrder(new TableLayout(), style, selector);
	}
	return tableLayout;
    }

    public final TableLayoutATSC getTableLayoutATSC() {
	if (tableLayoutATSC == null) {
	    tableLayoutATSC = (TableLayoutATSC)
		style.CascadingOrder(new TableLayoutATSC(), style, selector);
	}
	return tableLayoutATSC;
    }

    /**
     * Get the caption-side property
     */
    public final CaptionSide getCaptionSide() {
	if (captionSide == null) {
	    captionSide = (CaptionSide)
		style.CascadingOrder(new CaptionSide(), style, selector);
	}
	return captionSide;
    }

    /**
     * Get the border-collapse property
     */
    public final BorderCollapse getBorderCollapse() {
	if (borderCollapse == null) {
	    borderCollapse = (BorderCollapse)
		style.CascadingOrder(new BorderCollapse(), style, selector);
	}
	return borderCollapse;
    }

    public final BorderCollapseATSC getBorderCollapseATSC() {
	if (borderCollapseATSC == null) {
	    borderCollapseATSC = (BorderCollapseATSC)
		style.CascadingOrder(new BorderCollapseATSC(), style, selector);
	}
	return borderCollapseATSC;
    }

    /**
     * Get the border-spacing property
     */
    public final BorderSpacing getBorderSpacing() {
	if (borderSpacing == null) {
	    borderSpacing = (BorderSpacing)
		style.CascadingOrder(new BorderSpacing(), style, selector);
	}
	return borderSpacing;
    }

    public final BorderSpacingATSC getBorderSpacingATSC() {
	if (borderSpacingATSC == null) {
	    borderSpacingATSC = (BorderSpacingATSC)
		style.CascadingOrder(new BorderSpacingATSC(), style, selector);
	}
	return borderSpacingATSC;
    }

    /**
     * Get the emtpy-cells property
     */
    public final EmptyCells getEmptyCells() {
	if (emptyCells == null) {
	    emptyCells = (EmptyCells)
		style.CascadingOrder(new EmptyCells(), style, selector);
	}
	return emptyCells;
    }

    public final EmptyCellsATSC getEmptyCellsATSC() {
	if (emptyCellsATSC == null) {
	    emptyCellsATSC = (EmptyCellsATSC)
		style.CascadingOrder(new EmptyCellsATSC(), style, selector);
	}
	return emptyCellsATSC;
    }

    /**
     * Get the emtpy-header property
     */
    public final SpeakHeader getSpeakHeader() {
	if (speakHeader == null) {
	    speakHeader = (SpeakHeader)
		style.CascadingOrder(new SpeakHeader(), style, selector);
	}
	return speakHeader;
    }

    public final SpeakHeaderATSC getSpeakHeaderATSC() {
	if (speakHeaderATSC == null) {
	    speakHeaderATSC = (SpeakHeaderATSC)
		style.CascadingOrder(new SpeakHeaderATSC(), style, selector);
	}
	return speakHeaderATSC;
    }


    /**
     * Print this style.
     *
     * @param printer The printer interface.
     */
    public void print(CssPrinterStyle printer) {
	super.print(printer);

	if (rowSpan != null) {
	    rowSpan.print(printer);
	}
	if (rowSpanATSC != null) {
	    rowSpanATSC.print(printer);
	}
	if (columnSpan != null) {
	    columnSpan.print(printer);
	}
	if (columnSpanATSC != null) {
	    columnSpanATSC.print(printer);
	}
	if (tableLayout != null) {
	    tableLayout.print(printer);
	}
	if (tableLayoutATSC != null) {
	    tableLayoutATSC.print(printer);
	}
	if (captionSide != null) {
	    captionSide.print(printer);
	}
	if (borderCollapse != null) {
	    borderCollapse.print(printer);
	}
	if (borderCollapseATSC != null) {
	    borderCollapseATSC.print(printer);
	}
	if (borderSpacing != null) {
	    borderSpacing.print(printer);
	}
	if (borderSpacingATSC != null) {
	    borderSpacingATSC.print(printer);
	}
	if (emptyCells != null) {
	    emptyCells.print(printer);
	}
	if (emptyCellsATSC != null) {
	    emptyCellsATSC.print(printer);
	}
	if (speakHeader != null) {
	    speakHeader.print(printer);
	}
	if (speakHeaderATSC != null) {
	    speakHeaderATSC.print(printer);
	}
    }
}
