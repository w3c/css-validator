//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css2;

import org.w3c.css.parser.CssSelectors;
import org.w3c.css.properties.aural.ACssStyle;
import org.w3c.css.properties.css.CssBorderCollapse;
import org.w3c.css.properties.css.CssBottom;
import org.w3c.css.properties.css.CssCaptionSide;
import org.w3c.css.properties.css.CssClip;
import org.w3c.css.properties.css.CssCounterIncrement;
import org.w3c.css.properties.css.CssCounterReset;
import org.w3c.css.properties.css.CssCursor;
import org.w3c.css.properties.css.CssDirection;
import org.w3c.css.properties.css.CssEmptyCells;
import org.w3c.css.properties.css.CssLeft;
import org.w3c.css.properties.css.CssMarkerOffset;
import org.w3c.css.properties.css.CssMaxHeight;
import org.w3c.css.properties.css.CssMaxWidth;
import org.w3c.css.properties.css.CssMinHeight;
import org.w3c.css.properties.css.CssMinWidth;
import org.w3c.css.properties.css.CssOutline;
import org.w3c.css.properties.css.CssOutlineColor;
import org.w3c.css.properties.css.CssOutlineStyle;
import org.w3c.css.properties.css.CssOutlineWidth;
import org.w3c.css.properties.css.CssOverflow;
import org.w3c.css.properties.css.CssPosition;
import org.w3c.css.properties.css.CssQuotes;
import org.w3c.css.properties.css.CssRight;
import org.w3c.css.properties.css.CssTextShadow;
import org.w3c.css.properties.css.CssTop;
import org.w3c.css.properties.css.CssUnicodeBidi;
import org.w3c.css.properties.css.CssVisibility;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;
import org.w3c.css.values.CssIdent;

/**
 * @version $Revision$
 */
public class Css2Style extends ACssStyle {

	static final CssIdent marker = CssIdent.getIdent("marker");
	/**
	 * aural properties
	 */
	public org.w3c.css.properties.css.CssAzimuth cssAzimuth;
	public org.w3c.css.properties.css.CssElevation cssElevation;

	/**
	 * font properties
	 */
	public org.w3c.css.properties.css.CssFontStretch cssFontStretch;
	public org.w3c.css.properties.css.CssFontSizeAdjust cssFontSizeAdjust;

	/**
	 * text properties
	 */
	public CssTextShadow cssTextShadow;

	public CssTop cssTop;
	public CssBottom cssBottom;
	public CssLeft cssLeft;
	public CssRight cssRight;

	public CssPosition cssPosition;

	public CssMinWidth cssMinWidth;
	public CssMaxWidth cssMaxWidth;
	public CssMinHeight cssMinHeight;
	public CssMaxHeight cssMaxHeight;

	public CssOutlineWidth cssOutlineWidth;
	public CssOutlineStyle cssOutlineStyle;
	public CssOutlineColor cssOutlineColor;
	public CssOutline cssOutline;
	public CssCursor cssCursor;

	public CssClip cssClip;
	public CssMarkerOffset cssMarkerOffset;
	public CssDirection cssDirection;
	public CssUnicodeBidi cssUnicodeBidi;
	public CssVisibility cssVisibility;
	public CssOverflow cssOverflow;
	public CssQuotes cssQuotes;
	public CssCounterIncrement cssCounterIncrement;
	public CssCounterReset cssCounterReset;
	
	public CssCaptionSide cssCaptionSide;
	public CssBorderCollapse cssBorderCollapse;
	public CssEmptyCells cssEmptyCells;

	/**
	 * Get the azimuth
	 */
	public org.w3c.css.properties.css.CssAzimuth getAzimuth() {
		if (cssAzimuth == null) {
			cssAzimuth = (org.w3c.css.properties.css.CssAzimuth) style.CascadingOrder(new org.w3c.css.properties.css.CssAzimuth(),
					style, selector);
		}
		return cssAzimuth;
	}

	/**
	 * Get the elevation
	 */
	public org.w3c.css.properties.css.CssElevation getElevation() {
		if (cssElevation == null) {
			cssElevation = (org.w3c.css.properties.css.CssElevation) style.CascadingOrder(new org.w3c.css.properties.css.CssElevation(),
					style, selector);
		}
		return cssElevation;
	}

	/**
	 * Get the border-top-style property
	 */
	public final org.w3c.css.properties.css.CssBorderTopStyle getBorderTopStyle() {
		if (cssBorder.borderStyle.top == null) {
			cssBorder.borderStyle.top =
					(org.w3c.css.properties.css.CssBorderTopStyle) style.CascadingOrder(new org.w3c.css.properties.css.CssBorderTopStyle(),
							style, selector);
		}
		return cssBorder.borderStyle.top;
	}

	/**
	 * Get the border-right-style property
	 */
	public final org.w3c.css.properties.css.CssBorderRightStyle getBorderRightStyle() {
		if (cssBorder.borderStyle.right == null) {
			cssBorder.borderStyle.right =
					(org.w3c.css.properties.css.CssBorderRightStyle) style.CascadingOrder(new org.w3c.css.properties.css.CssBorderRightStyle(),
							style, selector);
		}
		return cssBorder.borderStyle.right;
	}

	/**
	 * Get the border-bottom-style property
	 */
	public final org.w3c.css.properties.css.CssBorderBottomStyle getBorderBottomStyle() {
		if (cssBorder.borderStyle.bottom == null) {
			cssBorder.borderStyle.bottom =
					(org.w3c.css.properties.css.CssBorderBottomStyle) style.CascadingOrder(new org.w3c.css.properties.css.CssBorderBottomStyle(),
							style, selector);
		}
		return cssBorder.borderStyle.bottom;
	}

	/**
	 * Get the border-left-style property
	 */
	public final org.w3c.css.properties.css.CssBorderLeftStyle getBorderLeftStyle() {
		if (cssBorder.borderStyle.left == null) {
			cssBorder.borderStyle.left =
					(org.w3c.css.properties.css.CssBorderLeftStyle) style.CascadingOrder(new org.w3c.css.properties.css.CssBorderLeftStyle(),
							style, selector);
		}
		return cssBorder.borderStyle.left;
	}

	/**
	 * get the font-stretch property
	 *
	 * @return a CssFontStretch instance
	 */
	public org.w3c.css.properties.css.CssFontStretch getFontStretch() {
		if (cssFontStretch == null) {
			cssFontStretch = (org.w3c.css.properties.css.CssFontStretch) style.CascadingOrder(new org.w3c.css.properties.css.CssFontStretch(),
					style, selector);
		}
		return cssFontStretch;
	}

	/**
	 * get the font-size-adjust property
	 *
	 * @return a CssFontSizeAdjust instance
	 */
	public org.w3c.css.properties.css.CssFontSizeAdjust getFontSizeAdjust() {
		if (cssFontSizeAdjust == null) {
			cssFontSizeAdjust = (org.w3c.css.properties.css.CssFontSizeAdjust) style.CascadingOrder(new org.w3c.css.properties.css.CssFontSizeAdjust(),
					style, selector);
		}
		return cssFontSizeAdjust;
	}

	/**
	 * Get the text-shadow property
	 *
	 * @return a CssTextShadow instance
	 */
	public final org.w3c.css.properties.css.CssTextShadow getTextShadow() {
		if (cssTextShadow == null) {
			cssTextShadow =
					(org.w3c.css.properties.css.CssTextShadow) style.CascadingOrder(new org.w3c.css.properties.css.CssTextShadow(),
							style, selector);
		}
		return cssTextShadow;
	}

	/**
	 * Get the top property
	 */
	public final CssTop getTop() {
		if (cssTop == null) {
			cssTop =
					(CssTop) style.CascadingOrder(new CssTop(), style, selector);
		}
		return cssTop;
	}

	/**
	 * Get the bottom property
	 */
	public final CssBottom getBottom() {
		if (cssBottom == null) {
			cssBottom =
					(CssBottom) style.CascadingOrder(new CssBottom(), style, selector);
		}
		return cssBottom;
	}

	/**
	 * Get the left property
	 */
	public final CssLeft getLeft() {
		if (cssLeft == null) {
			cssLeft =
					(CssLeft) style.CascadingOrder(new CssLeft(), style, selector);
		}
		return cssLeft;
	}

	/**
	 * Get the right property
	 */
	public final CssRight getRight() {
		if (cssRight == null) {
			cssRight =
					(CssRight) style.CascadingOrder(new CssRight(), style, selector);
		}
		return cssRight;
	}

	/**
	 * Get the position property
	 */
	public final CssPosition getPosition() {
		if (cssPosition == null) {
			cssPosition =
					(CssPosition) style.CascadingOrder(new CssPosition(), style, selector);
		}
		return cssPosition;
	}

	public final CssMinWidth getMinWidth() {
		if (cssMinWidth == null) {
			cssMinWidth =
					(CssMinWidth) style.CascadingOrder(new CssMinWidth(), style, selector);
		}
		return cssMinWidth;
	}

	public final CssMaxWidth getMaxWidth() {
		if (cssMaxWidth == null) {
			cssMaxWidth =
					(CssMaxWidth) style.CascadingOrder(new CssMaxWidth(), style, selector);
		}
		return cssMaxWidth;
	}

	public final CssMinHeight getMinHeight() {
		if (cssMinHeight == null) {
			cssMinHeight =
					(CssMinHeight) style.CascadingOrder(new CssMinHeight(), style, selector);
		}
		return cssMinHeight;
	}

	public final CssMaxHeight getMaxHeight() {
		if (cssMaxHeight == null) {
			cssMaxHeight =
					(CssMaxHeight) style.CascadingOrder(new CssMaxHeight(), style, selector);
		}
		return cssMaxHeight;
	}

	public final CssOutlineWidth getOutlineWidth() {
		if (cssOutlineWidth == null) {
			cssOutlineWidth =
					(CssOutlineWidth) style.CascadingOrder(new CssOutlineWidth(), style, selector);
		}
		return cssOutlineWidth;
	}

	public final CssOutlineStyle getOutlineStyle() {
		if (cssOutlineStyle == null) {
			cssOutlineStyle =
					(CssOutlineStyle) style.CascadingOrder(new CssOutlineStyle(), style, selector);
		}
		return cssOutlineStyle;
	}

	public final CssOutlineColor getOutlineColor() {
		if (cssOutlineColor == null) {
			cssOutlineColor =
					(CssOutlineColor) style.CascadingOrder(new CssOutlineColor(), style, selector);
		}
		return cssOutlineColor;
	}

	public final CssOutline getOutline() {
		if (cssOutline == null) {
			cssOutline =
					(CssOutline) style.CascadingOrder(new CssOutline(), style, selector);
		}
		return cssOutline;
	}

	public final CssCursor getCursor() {
		if (cssCursor == null) {
			cssCursor =
					(CssCursor) style.CascadingOrder(new CssCursor(), style, selector);
		}
		return cssCursor;
	}

	public final CssMarkerOffset getMarkerOffset() {
		if (cssMarkerOffset == null) {
			cssMarkerOffset =
					(CssMarkerOffset) style.CascadingOrder(new CssMarkerOffset(),
							style, selector);
		}
		return cssMarkerOffset;
	}


	/**
	 * Get the clip property
	 */
	public final CssClip getClip() {
		if (cssClip == null) {
			cssClip =
					(CssClip) style.CascadingOrder(new CssClip(),
							style, selector);
		}
		return cssClip;
	}

	/**
	 * Get the direction property
	 */
	public final CssDirection getDirection() {
		if (cssDirection == null) {
			cssDirection =
					(CssDirection) style.CascadingOrder(new CssDirection(),
							style, selector);
		}
		return cssDirection;
	}

	/**
	 * Get the unicode-bidi property
	 */
	public final CssUnicodeBidi getUnicodeBidi() {
		if (cssUnicodeBidi == null) {
			cssUnicodeBidi =
					(CssUnicodeBidi) style.CascadingOrder(new CssUnicodeBidi(),
							style, selector);
		}
		return cssUnicodeBidi;
	}
	/**
	 * Get the visibility property
	 */
	public final CssVisibility getVisibility() {
		if (cssVisibility == null) {
			cssVisibility =
					(CssVisibility) style.CascadingOrder(new CssVisibility(),
							style, selector);
		}
		return cssVisibility;
	}
	/**
	 * Get the overflow property
	 */
	public final CssOverflow getOverflow() {
		if (cssOverflow == null) {
			cssOverflow =
					(CssOverflow) style.CascadingOrder(new CssOverflow(),
							style, selector);
		}
		return cssOverflow;
	}
	/**
	 * Get the quotes property
	 */
	public final CssQuotes getQuotes() {
		if (cssQuotes == null) {
			cssQuotes =
					(CssQuotes) style.CascadingOrder(new CssQuotes(),
							style, selector);
		}
		return cssQuotes;
	}
	/**
	 * Get the counter-increment property
	 */
	public final CssCounterIncrement getCounterIncrement() {
		if (cssCounterIncrement == null) {
			cssCounterIncrement =
					(CssCounterIncrement) style.CascadingOrder(new CssCounterIncrement(),
							style, selector);
		}
		return cssCounterIncrement;
	}
	/**
	 * Get the counter-reset property
	 */
	public final CssCounterReset getCounterReset() {
		if (cssCounterReset == null) {
			cssCounterReset =
					(CssCounterReset) style.CascadingOrder(new CssCounterReset(),
							style, selector);
		}
		return cssCounterReset;
	}

	public final CssCaptionSide getCaptionSide() {
		if (cssCaptionSide == null) {
			cssCaptionSide =
					(CssCaptionSide) style.CascadingOrder(new CssCaptionSide(),
							style, selector);
		}
		return cssCaptionSide;
	}
	
	public final CssBorderCollapse getBorderCollapse() {
		if (cssBorderCollapse == null) {
			cssBorderCollapse =
					(CssBorderCollapse) style.CascadingOrder(new CssBorderCollapse(),
							style, selector);
		}
		return cssBorderCollapse;
	}
	
	public final CssEmptyCells getEmptyCells() {
		if (cssEmptyCells == null) {
			cssEmptyCells =
					(CssEmptyCells) style.CascadingOrder(new CssEmptyCells(),
							style, selector);
		}
		return cssEmptyCells;
	}
	/**
	 * Find conflicts in this Style
	 *
	 * @param warnings     For warnings reports.
	 * @param allSelectors All contexts is the entire style sheet.
	 */
	public void findConflicts(ApplContext ac, Warnings warnings,
							  CssSelectors selector, CssSelectors[] allSelectors) {
		super.findConflicts(ac, warnings, selector, allSelectors);

		if (cssMarkerOffset != null) {
			if ((cssDisplay == null) || (!marker.equals(cssDisplay.get()))) {
				warnings.addWarning(new Warning(cssMarkerOffset,
						"marker", 1, ac));
			}
		}
	}

}
