//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.paged;

import org.w3c.css.parser.CssPrinterStyle;


/**
 * @version $Revision$
 */
public class Css2Style extends org.w3c.css.properties.css2.user.Css2Style {

    Size size;
    Marks marks;

    PageBreakBefore pageBreakBefore;
    PageBreakBeforeATSC pageBreakBeforeATSC;
    PageBreakAfter pageBreakAfter;
    PageBreakAfterATSC pageBreakAfterATSC;
    PageBreakInside pageBreakInside;
    PageBreakInsideATSC pageBreakInsideATSC;

    Page page;
    PageATSC pageATSC;
    Orphans orphans;
    OrphansATSC orphansATSC;
    Widows widows;
    WidowsATSC widowsATSC;

    /**
     * Get the size property
     */
    public final Size getSize() {
	if (size == null) {
	    size = (Size) style.CascadingOrder(new Size(),
						   style, selector);
	}
	return size;
    }

    /**
     * Get the marks property
     */
    public final Marks getMarks() {
	if (marks == null) {
	    marks = (Marks) style.CascadingOrder(new Marks(),
						   style, selector);
	}
	return marks;
    }

    /**
     * Get the page-break-before property
     */
    public final PageBreakBefore getPageBreakBefore() {
	if (pageBreakBefore == null) {
	    pageBreakBefore =
		(PageBreakBefore) style.CascadingOrder(new PageBreakBefore(),
						       style, selector);
	}
	return pageBreakBefore;
    }

    public final PageBreakBeforeATSC getPageBreakBeforeATSC() {
	if (pageBreakBeforeATSC == null) {
	    pageBreakBeforeATSC =
		(PageBreakBeforeATSC) style.CascadingOrder(new PageBreakBeforeATSC(),
						       style, selector);
	}
	return pageBreakBeforeATSC;
    }

    /**
     * Get the page-break-after property
     */
    public final PageBreakAfter getPageBreakAfter() {
	if (pageBreakAfter == null) {
	    pageBreakAfter =
		(PageBreakAfter) style.CascadingOrder(new PageBreakAfter(),
						       style, selector);
	}
	return pageBreakAfter;
    }

    public final PageBreakAfterATSC getPageBreakAfterATSC() {
	if (pageBreakAfterATSC == null) {
	    pageBreakAfterATSC =
		(PageBreakAfterATSC) style.CascadingOrder(new PageBreakAfterATSC(),
						       style, selector);
	}
	return pageBreakAfterATSC;
    }

    /**
     * Get the page-break-inside property
     */
    public final PageBreakInside getPageBreakInside() {
	if (pageBreakInside == null) {
	    pageBreakInside =
		(PageBreakInside) style.CascadingOrder(new PageBreakInside(),
						       style, selector);
	}
	return pageBreakInside;
    }

    public final PageBreakInsideATSC getPageBreakInsideATSC() {
	if (pageBreakInsideATSC == null) {
	    pageBreakInsideATSC =
		(PageBreakInsideATSC) style.CascadingOrder(new PageBreakInsideATSC(),
						       style, selector);
	}
	return pageBreakInsideATSC;
    }


    /**
     * Get the page property
     */
    public final Page getPage() {
	if (page == null) {
	    page =
		(Page) style.CascadingOrder(new Page(),
						       style, selector);
	}
	return page;
    }

    public final PageATSC getPageATSC() {
	if (pageATSC == null) {
	    pageATSC =
		(PageATSC) style.CascadingOrder(new PageATSC(),
						       style, selector);
	}
	return pageATSC;
    }

    /**
     * Get the orphans property
     */
    public final Orphans getOrphans() {
	if (orphans == null) {
	    orphans =
		(Orphans) style.CascadingOrder(new Orphans(),
						       style, selector);
	}
	return orphans;
    }

    public final OrphansATSC getOrphansATSC() {
	if (orphansATSC == null) {
	    orphansATSC =
		(OrphansATSC) style.CascadingOrder(new OrphansATSC(),
						       style, selector);
	}
	return orphansATSC;
    }

    /**
     * Get the widows property
     */
    public final Widows getWidows() {
	if (widows == null) {
	    widows =
		(Widows) style.CascadingOrder(new Widows(),
						       style, selector);
	}
	return widows;
    }

    public final WidowsATSC getWidowsATSC() {
	if (widowsATSC == null) {
	    widowsATSC =
		(WidowsATSC) style.CascadingOrder(new WidowsATSC(),
						       style, selector);
	}
	return widowsATSC;
    }

    /**
     * Print this style.
     *
     * @param printer The printer interface.
     */
    public void print(CssPrinterStyle printer) {
	super.print(printer);

	if (size != null) {
	    size.print(printer);
	}
	if (marks != null) {
	    marks.print(printer);
	}
 	if (pageBreakBefore != null) {
	    pageBreakBefore.print(printer);
	}
	if (pageBreakBeforeATSC != null) {
	    pageBreakBeforeATSC.print(printer);
	}

 	if (pageBreakAfter != null) {
	    pageBreakAfter.print(printer);
	}
	if (pageBreakAfterATSC != null) {
	    pageBreakAfterATSC.print(printer);
	}
 	if (pageBreakInside != null) {
	    pageBreakInside.print(printer);
	}
	if (pageBreakInsideATSC != null) {
	    pageBreakInsideATSC.print(printer);
	}
  	if (page != null) {
	    page.print(printer);
	}
	if (pageATSC != null) {
	    pageATSC.print(printer);
	}
  	if (orphans != null) {
	    orphans.print(printer);
	}
	if (orphansATSC != null) {
	    orphansATSC.print(printer);
	}
  	if (widows != null) {
	    widows.print(printer);
	}
	if (widowsATSC != null) {
	    widowsATSC.print(printer);
	}
    }

}
