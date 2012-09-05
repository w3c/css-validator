//

// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssSelectors;
import org.w3c.css.properties.atsc.ATSCStyle;
import org.w3c.css.properties.css.CssBackgroundClip;
import org.w3c.css.properties.css.CssBackgroundOrigin;
import org.w3c.css.properties.css.CssBackgroundSize;
import org.w3c.css.properties.css.CssBoxDecorationBreak;
import org.w3c.css.properties.css.CssBoxShadow;
import org.w3c.css.properties.css.CssBreakAfter;
import org.w3c.css.properties.css.CssBreakBefore;
import org.w3c.css.properties.css.CssBreakInside;
import org.w3c.css.properties.css.CssColumnCount;
import org.w3c.css.properties.css.CssColumnFill;
import org.w3c.css.properties.css.CssColumnGap;
import org.w3c.css.properties.css.CssColumnRule;
import org.w3c.css.properties.css.CssColumnRuleColor;
import org.w3c.css.properties.css.CssColumnRuleStyle;
import org.w3c.css.properties.css.CssColumnRuleWidth;
import org.w3c.css.properties.css.CssColumnSpan;
import org.w3c.css.properties.css.CssColumnWidth;
import org.w3c.css.properties.css.CssColumns;
import org.w3c.css.properties.css.CssFontFeatureSettings;
import org.w3c.css.properties.css.CssFontKerning;
import org.w3c.css.properties.css.CssFontLanguageOverride;
import org.w3c.css.properties.css.CssFontSynthesis;
import org.w3c.css.properties.css.CssFontVariantAlternates;
import org.w3c.css.properties.css.CssFontVariantCaps;
import org.w3c.css.properties.css.CssFontVariantEastAsian;
import org.w3c.css.properties.css.CssFontVariantLigatures;
import org.w3c.css.properties.css.CssFontVariantNumeric;
import org.w3c.css.properties.css.CssFontVariantPosition;
import org.w3c.css.properties.css.CssHangingPunctuation;
import org.w3c.css.properties.css.CssHyphens;
import org.w3c.css.properties.css.CssLineBreak;
import org.w3c.css.properties.css.CssOpacity;
import org.w3c.css.properties.css.CssOverflowWrap;
import org.w3c.css.properties.css.CssTabSize;
import org.w3c.css.properties.css.CssTextAlignLast;
import org.w3c.css.properties.css.CssTextDecorationColor;
import org.w3c.css.properties.css.CssTextDecorationLine;
import org.w3c.css.properties.css.CssTextDecorationSkip;
import org.w3c.css.properties.css.CssTextDecorationStyle;
import org.w3c.css.properties.css.CssTextEmphasis;
import org.w3c.css.properties.css.CssTextEmphasisColor;
import org.w3c.css.properties.css.CssTextEmphasisPosition;
import org.w3c.css.properties.css.CssTextEmphasisStyle;
import org.w3c.css.properties.css.CssTextJustify;
import org.w3c.css.properties.css.CssTextUnderlinePosition;
import org.w3c.css.properties.css.CssWordBreak;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Util;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;

public class Css3Style extends ATSCStyle {

	CssRubyPosition cssRubyPosition;
	CssRubyAlign cssRubyAlign;
	CssRubyOverhang cssRubyOverhang;
	CssBoxSizing cssBoxSizing;
	CssResizer cssResizer;
	CssToggleGroup cssToggleGroup;
	CssWritingMode cssWritingMode;
	CssGlyphOrVert cssGlyphOrVert;
	CssGlyphHor cssGlyphHor;
	CssDominantBaseLine cssDominantBaseLine;
	CssAlignmentBaseLine cssAlignmentBaseLine;
	CssAlignmentAdjust cssAlignmentAdjust;
	CssBaselineShift cssBaselineShift;
	CssTextSpace cssTextSpace;
	CssTextEndOverflow cssTextEndOverflow;
	CssTextCombine cssTextCombine;
	CssFit cssFit;
	CssFloatCSS3 cssFloatCSS3;
	CssClearCSS3 cssClearCSS3;

	CssScript cssScript;
	CssTextOverflow cssTextOverflow;
	CssTextOverflowEllipsis cssTextOverflowEllipsis;
	CssTextOverflowMode cssTextOverflowMode;

	public CssOpacity cssOpacity;
	public CssBackgroundClip cssBackgroundClip;
	public CssBackgroundSize cssBackgroundSize;
	public CssBackgroundOrigin cssBackgroundOrigin;
	public CssColumns cssColumns;
	public CssColumnCount cssColumnCount;
	public CssColumnFill cssColumnFill;
	public CssColumnGap cssColumnGap;
	public CssColumnRule cssColumnRule;
	public CssColumnRuleColor cssColumnRuleColor;
	public CssColumnRuleStyle cssColumnRuleStyle;
	public CssColumnRuleWidth cssColumnRuleWidth;
	public CssColumnSpan cssColumnSpan;
	public CssColumnWidth cssColumnWidth;
	public CssBreakAfter cssBreakAfter;
	public CssBreakBefore cssBreakBefore;
	public CssBreakInside cssBreakInside;
	public CssBoxShadow cssBoxShadow;
	public CssBoxDecorationBreak cssBoxDecorationBreak;
	public CssFontKerning cssFontKerning;
	public CssFontLanguageOverride cssFontLanguageOverride;
	public CssFontVariantCaps cssFontVariantCaps;
	public CssFontVariantPosition cssFontVariantPosition;
	public CssFontSynthesis cssFontSynthesis;
	public CssFontVariantEastAsian cssFontVariantEastAsian;
	public CssFontVariantLigatures cssFontVariantLigatures;
	public CssFontVariantNumeric cssFontVariantNumeric;
	public CssFontFeatureSettings cssFontFeatureSettings;
	public CssFontVariantAlternates cssFontVariantAlternates;

	public CssOverflowWrap cssOverflowWrap;
	public CssWordBreak cssWordBreak;
	public CssHyphens cssHyphens;
	public CssLineBreak cssLineBreak;
	public CssTextAlignLast cssTextAlignLast;
	public CssTextJustify cssTextJustify;
	public CssTextDecorationColor cssTextDecorationColor;
	public CssTextDecorationLine cssTextDecorationLine;
	public CssTextDecorationSkip cssTextDecorationSkip;
	public CssTextDecorationStyle cssTextDecorationStyle;
	public CssTextEmphasis cssTextEmphasis;
	public CssTextEmphasisColor cssTextEmphasisColor;
	public CssTextEmphasisPosition cssTextEmphasisPosition;
	public CssTextEmphasisStyle cssTextEmphasisStyle;
	public CssTextUnderlinePosition cssTextUnderlinePosition;
	public CssHangingPunctuation cssHangingPunctuation;
	public CssTabSize cssTabSize;

	CssDropInitialAfterAdjust cssDropInitialAfterAdjust;
	CssDropInitialAfterAlign cssDropInitialAfterAlign;
	CssDropInitialBeforeAdjust cssDropInitialBeforeAdjust;
	CssDropInitialBeforeAlign cssDropInitialBeforeAlign;
	CssDropInitialSize cssDropInitialSize;
	CssDropInitialValue cssDropInitialValue;
	CssInlineBoxAlign cssInlineBoxAlign;
	CssLineStacking cssLineStacking;
	CssLineStackingRuby cssLineStackingRuby;
	CssLineStackingShift cssLineStackingShift;
	CssLineStackingStrategy cssLineStackingStrategy;
	CssTextHeight cssTextHeight;
	CssAppearance cssAppearance;
	CssIcon cssIcon;
	CssNavIndex cssNavIndex;
	CssNavUp cssNavUp;
	CssNavRight cssNavRight;
	CssNavDown cssNavDown;
	CssNavLeft cssNavLeft;
	CssOutlineOffset cssOutlineOffset;
	CssOverflowX cssOverflowX;
	CssOverflowY cssOverflowY;
	CssRubySpan cssRubySpan;
	CssTextBlink cssTextBlink;
	CssCrop cssCrop;
	CssFitPosition cssFitPosition;
	CssVisibilityCSS3 cssVisibilityCSS3;
	CssOverflowCSS3 cssOverflowCSS3;
	CssMaxWidthCSS3 cssMaxWidthCSS3;
	CssMaxHeightCSS3 cssMaxHeightCSS3;
	CssMinWidthCSS3 cssMinWidthCSS3;
	CssMinHeightCSS3 cssMinHeightCSS3;
	CssPaddingCSS3 cssPaddingCSS3;
	CssPaddingBottomCSS3 cssPaddingBottomCSS3;
	CssPaddingTopCSS3 cssPaddingTopCSS3;
	CssPaddingLeftCSS3 cssPaddingLeftCSS3;
	CssPaddingRightCSS3 cssPaddingRightCSS3;
	CssMarquee cssMarquee;
	CssMarqueeDirection cssMarqueeDirection;
	CssMarqueeRepetition cssMarqueeRepetition;
	CssMarqueeSpeed cssMarqueeSpeed;
	CssMarqueeStyle cssMarqueeStyle;

	public org.w3c.css.properties.css.CssBorderImageSource getBorderImageSource() {
		if (cssBorder.borderImage.source == null) {
			cssBorder.borderImage.source = (org.w3c.css.properties.css.CssBorderImageSource) style.CascadingOrder(new org.w3c.css.properties.css.CssBorderImageSource(), style, selector);
		}
		return cssBorder.borderImage.source;

	}

	public CssOpacity getOpacity() {
		if (cssOpacity == null) {
			cssOpacity =
					(CssOpacity) style.CascadingOrder(new CssOpacity(),
							style, selector);
		}
		return cssOpacity;
	}

	public CssRubyPosition getRubyPosition() {
		if (cssRubyPosition == null) {
			cssRubyPosition =
					(CssRubyPosition) style.CascadingOrder(
							new CssRubyPosition(), style, selector);
		}
		return cssRubyPosition;
	}

	public CssRubyAlign getRubyAlign() {
		if (cssRubyAlign == null) {
			cssRubyAlign =
					(CssRubyAlign) style.CascadingOrder(
							new CssRubyAlign(), style, selector);
		}
		return cssRubyAlign;
	}

	public CssRubyOverhang getRubyOverhang() {
		if (cssRubyOverhang == null) {
			cssRubyOverhang =
					(CssRubyOverhang) style.CascadingOrder(
							new CssRubyOverhang(), style, selector);
		}
		return cssRubyOverhang;
	}

	public CssBoxSizing getBoxSizing() {
		if (cssBoxSizing == null) {
			cssBoxSizing =
					(CssBoxSizing) style.CascadingOrder(
							new CssBoxSizing(), style, selector);
		}
		return cssBoxSizing;
	}

	public CssResizer getResizer() {
		if (cssResizer == null) {
			cssResizer =
					(CssResizer) style.CascadingOrder(
							new CssResizer(), style, selector);
		}
		return cssResizer;
	}

	public CssToggleGroup getToggleGroup() {
		if (cssToggleGroup == null) {
			cssToggleGroup =
					(CssToggleGroup) style.CascadingOrder(
							new CssToggleGroup(), style, selector);
		}
		return cssToggleGroup;
	}

	public CssWritingMode getWritingMode() {
		if (cssWritingMode == null) {
			cssWritingMode =
					(CssWritingMode) style.CascadingOrder(
							new CssWritingMode(), style, selector);
		}
		return cssWritingMode;
	}

	public CssGlyphOrVert getGlyphOrVert() {
		if (cssGlyphOrVert == null) {
			cssGlyphOrVert =
					(CssGlyphOrVert) style.CascadingOrder(
							new CssGlyphOrVert(), style, selector);
		}
		return cssGlyphOrVert;
	}

	public CssGlyphHor getGlyphHor() {
		if (cssGlyphHor == null) {
			cssGlyphHor =
					(CssGlyphHor) style.CascadingOrder(
							new CssGlyphHor(), style, selector);
		}
		return cssGlyphHor;
	}

	public CssTextJustify getTextJustify() {
		if (cssTextJustify == null) {
			cssTextJustify =
					(CssTextJustify) style.CascadingOrder(
							new CssTextJustify(), style, selector);
		}
		return cssTextJustify;
	}

	public CssTextAlignLast getTextAlignLast() {
		if (cssTextAlignLast == null) {
			cssTextAlignLast =
					(CssTextAlignLast) style.CascadingOrder(
							new CssTextAlignLast(), style, selector);
		}
		return cssTextAlignLast;
	}

	public CssDominantBaseLine getDominantBaseLine() {
		if (cssDominantBaseLine == null) {
			cssDominantBaseLine =
					(CssDominantBaseLine) style.CascadingOrder(
							new CssDominantBaseLine(), style, selector);
		}
		return cssDominantBaseLine;
	}

	public CssAlignmentBaseLine getAlignmentBaseLine() {
		if (cssAlignmentBaseLine == null) {
			cssAlignmentBaseLine =
					(CssAlignmentBaseLine) style.CascadingOrder(
							new CssAlignmentBaseLine(), style, selector);
		}
		return cssAlignmentBaseLine;
	}

	public CssAlignmentAdjust getAlignmentAdjust() {
		if (cssAlignmentAdjust == null) {
			cssAlignmentAdjust =
					(CssAlignmentAdjust) style.CascadingOrder(
							new CssAlignmentAdjust(), style, selector);
		}
		return cssAlignmentAdjust;
	}

	public CssBaselineShift getBaselineShift() {
		if (cssBaselineShift == null) {
			cssBaselineShift =
					(CssBaselineShift) style.CascadingOrder(
							new CssBaselineShift(), style, selector);
		}
		return cssBaselineShift;
	}

	public CssLineBreak getLineBreak() {
		if (cssLineBreak == null) {
			cssLineBreak =
					(CssLineBreak) style.CascadingOrder(
							new CssLineBreak(), style, selector);
		}
		return cssLineBreak;
	}

	public CssWordBreak getWordBreak() {
		if (cssWordBreak == null) {
			cssWordBreak =
					(CssWordBreak) style.CascadingOrder(
							new CssWordBreak(), style, selector);
		}
		return cssWordBreak;
	}

	public CssTextSpace getTextSpace() {
		if (cssTextSpace == null) {
			cssTextSpace =
					(CssTextSpace) style.CascadingOrder(
							new CssTextSpace(), style, selector);
		}
		return cssTextSpace;
	}

	public CssTextEndOverflow getTextEndOverflow() {
		if (cssTextEndOverflow == null) {
			cssTextEndOverflow =
					(CssTextEndOverflow) style.CascadingOrder(
							new CssTextEndOverflow(), style, selector);
		}
		return cssTextEndOverflow;
	}

	public CssTextCombine getTextCombine() {
		if (cssTextCombine == null) {
			cssTextCombine =
					(CssTextCombine) style.CascadingOrder(
							new CssTextCombine(), style, selector);
		}
		return cssTextCombine;
	}

	public CssFit getFit() {
		if (cssFit == null) {
			cssFit =
					(CssFit) style.CascadingOrder(
							new CssFit(), style, selector);
		}
		return cssFit;
	}

	public CssFloatCSS3 getFloatCSS3() {
		if (cssFloatCSS3 == null) {
			cssFloatCSS3 =
					(CssFloatCSS3) style.CascadingOrder(
							new CssFloatCSS3(), style, selector);
		}
		return cssFloatCSS3;
	}

	public CssClearCSS3 getClearCSS3() {
		if (cssClearCSS3 == null) {
			cssClearCSS3 =
					(CssClearCSS3) style.CascadingOrder(
							new CssClearCSS3(), style, selector);
		}
		return cssClearCSS3;
	}

	public CssColumns getColumns() {
		if (cssColumns == null) {
			cssColumns =
					(CssColumns) style.CascadingOrder(
							new CssColumns(), style, selector);
		}
		return cssColumns;
	}

	public CssColumnCount getColumnCount() {
		if (cssColumnCount == null) {
			cssColumnCount =
					(CssColumnCount) style.CascadingOrder(
							new CssColumnCount(), style, selector);
		}
		return cssColumnCount;
	}

	public CssColumnWidth getColumnWidth() {
		if (cssColumnWidth == null) {
			cssColumnWidth =
					(CssColumnWidth) style.CascadingOrder(
							new CssColumnWidth(), style, selector);
		}
		return cssColumnWidth;
	}

	public CssBackgroundClip getCssBackgroundClip() {
		if (cssBackgroundClip == null) {
			cssBackgroundClip =
					(CssBackgroundClip) style.CascadingOrder(
							new CssBackgroundClip(), style, selector);
		}
		return cssBackgroundClip;
	}

	public CssBackgroundSize getCssBackgroundSize() {
		if (cssBackgroundSize == null) {
			cssBackgroundSize =
					(CssBackgroundSize) style.CascadingOrder(
							new CssBackgroundSize(), style, selector);
		}
		return cssBackgroundSize;
	}

	public CssBackgroundOrigin getCssBackgroundOrigin() {
		if (cssBackgroundOrigin == null) {
			cssBackgroundOrigin =
					(CssBackgroundOrigin) style.CascadingOrder(
							new CssBackgroundOrigin(), style, selector);
		}
		return cssBackgroundOrigin;
	}

	public CssHangingPunctuation getHangingPunctuation() {
		if (cssHangingPunctuation == null) {
			cssHangingPunctuation =
					(CssHangingPunctuation) style.CascadingOrder(
							new CssHangingPunctuation(), style, selector);
		}
		return cssHangingPunctuation;
	}

	public CssScript getScript() {
		if (cssScript == null) {
			cssScript =
					(CssScript) style.CascadingOrder(
							new CssScript(), style, selector);
		}
		return cssScript;
	}

	public CssTextOverflow getTextOverflow() {
		if (cssTextOverflow == null) {
			cssTextOverflow =
					(CssTextOverflow) style.CascadingOrder(
							new CssTextOverflow(), style, selector);
		}
		return cssTextOverflow;
	}

	public CssTextOverflowEllipsis getTextOverflowEllipsis() {
		if (cssTextOverflowEllipsis == null) {
			cssTextOverflowEllipsis =
					(CssTextOverflowEllipsis) style.CascadingOrder(
							new CssTextOverflowEllipsis(), style, selector);
		}
		return cssTextOverflowEllipsis;
	}

	public CssTextOverflowMode getTextOverflowMode() {
		if (cssTextOverflowMode == null) {
			cssTextOverflowMode =
					(CssTextOverflowMode) style.CascadingOrder(
							new CssTextOverflowMode(), style, selector);
		}
		return cssTextOverflowMode;
	}

	public CssColumnGap getColumnGap() {
		if (cssColumnGap == null) {
			cssColumnGap =
					(CssColumnGap) style.CascadingOrder(
							new CssColumnGap(), style, selector);
		}
		return cssColumnGap;
	}

	public CssBreakBefore getBreakBefore() {
		if (cssBreakBefore == null) {
			cssBreakBefore =
					(CssBreakBefore) style.CascadingOrder(
							new CssBreakBefore(), style, selector);
		}
		return cssBreakBefore;
	}

	public CssBreakAfter getBreakAfter() {
		if (cssBreakAfter == null) {
			cssBreakAfter =
					(CssBreakAfter) style.CascadingOrder(
							new CssBreakAfter(), style, selector);
		}
		return cssBreakAfter;
	}

	public CssBreakInside getBreakInside() {
		if (cssBreakInside == null) {
			cssBreakInside =
					(CssBreakInside) style.CascadingOrder(
							new CssBreakInside(), style, selector);
		}
		return cssBreakInside;
	}

	public CssColumnFill getColumnFill() {
		if (cssColumnFill == null) {
			cssColumnFill =
					(CssColumnFill) style.CascadingOrder(
							new CssColumnFill(), style, selector);
		}
		return cssColumnFill;
	}

	public CssColumnRuleColor getColumnRuleColor() {
		if (cssColumnRuleColor == null) {
			cssColumnRuleColor =
					(CssColumnRuleColor) style.CascadingOrder(
							new CssColumnRuleColor(), style, selector);
		}
		return cssColumnRuleColor;
	}

	public CssColumnRuleStyle getColumnRuleStyle() {
		if (cssColumnRuleStyle == null) {
			cssColumnRuleStyle =
					(CssColumnRuleStyle) style.CascadingOrder(
							new CssColumnRuleStyle(), style, selector);
		}
		return cssColumnRuleStyle;
	}

	public CssColumnRuleWidth getColumnRuleWidth() {
		if (cssColumnRuleWidth == null) {
			cssColumnRuleWidth =
					(CssColumnRuleWidth) style.CascadingOrder(
							new CssColumnRuleWidth(), style, selector);
		}
		return cssColumnRuleWidth;
	}

	public CssColumnRule getColumnRule() {
		if (cssColumnRule == null) {
			cssColumnRule =
					(CssColumnRule) style.CascadingOrder(
							new CssColumnRule(), style, selector);
		}
		return cssColumnRule;
	}

	public CssDropInitialAfterAdjust getDropInitialAfterAdjust() {
		if (cssDropInitialAfterAdjust == null) {
			cssDropInitialAfterAdjust =
					(CssDropInitialAfterAdjust) style.CascadingOrder(
							new CssDropInitialAfterAdjust(), style, selector);
		}
		return cssDropInitialAfterAdjust;
	}

	public CssDropInitialAfterAlign getDropInitialAfterAlign() {
		if (cssDropInitialAfterAlign == null) {
			cssDropInitialAfterAlign =
					(CssDropInitialAfterAlign) style.CascadingOrder(
							new CssDropInitialAfterAlign(), style, selector);
		}
		return cssDropInitialAfterAlign;
	}

	public CssDropInitialBeforeAdjust getDropInitialBeforeAdjust() {
		if (cssDropInitialBeforeAdjust == null) {
			cssDropInitialBeforeAdjust =
					(CssDropInitialBeforeAdjust) style.CascadingOrder(
							new CssDropInitialBeforeAdjust(), style, selector);
		}
		return cssDropInitialBeforeAdjust;
	}

	public CssDropInitialBeforeAlign getDropInitialBeforeAlign() {
		if (cssDropInitialBeforeAlign == null) {
			cssDropInitialBeforeAlign =
					(CssDropInitialBeforeAlign) style.CascadingOrder(
							new CssDropInitialBeforeAlign(), style, selector);
		}
		return cssDropInitialBeforeAlign;
	}

	public CssDropInitialSize getDropInitialSize() {
		if (cssDropInitialSize == null) {
			cssDropInitialSize =
					(CssDropInitialSize) style.CascadingOrder(
							new CssDropInitialSize(), style, selector);
		}
		return cssDropInitialSize;
	}

	public CssDropInitialValue getDropInitialValue() {
		if (cssDropInitialValue == null) {
			cssDropInitialValue =
					(CssDropInitialValue) style.CascadingOrder(
							new CssDropInitialValue(), style, selector);
		}
		return cssDropInitialValue;
	}

	public CssInlineBoxAlign getInlineBoxAlign() {
		if (cssInlineBoxAlign == null) {
			cssInlineBoxAlign =
					(CssInlineBoxAlign) style.CascadingOrder(
							new CssInlineBoxAlign(), style, selector);
		}
		return cssInlineBoxAlign;
	}

	public CssLineStacking getLineStacking() {
		if (cssLineStacking == null) {
			cssLineStacking =
					(CssLineStacking) style.CascadingOrder(
							new CssLineStacking(), style, selector);
		}
		return cssLineStacking;
	}

	public CssLineStackingRuby getLineStackingRuby() {
		if (cssLineStackingRuby == null) {
			cssLineStackingRuby =
					(CssLineStackingRuby) style.CascadingOrder(
							new CssLineStackingRuby(), style, selector);
		}
		return cssLineStackingRuby;
	}

	public CssLineStackingShift getLineStackingShift() {
		if (cssLineStackingShift == null) {
			cssLineStackingShift =
					(CssLineStackingShift) style.CascadingOrder(
							new CssLineStackingShift(), style, selector);
		}
		return cssLineStackingShift;
	}

	public CssLineStackingStrategy getLineStackingStrategy() {
		if (cssLineStackingStrategy == null) {
			cssLineStackingStrategy =
					(CssLineStackingStrategy) style.CascadingOrder(
							new CssLineStackingStrategy(), style, selector);
		}
		return cssLineStackingStrategy;
	}

	public CssTextHeight getTextHeight() {
		if (cssTextHeight == null) {
			cssTextHeight =
					(CssTextHeight) style.CascadingOrder(
							new CssTextHeight(), style, selector);
		}
		return cssTextHeight;
	}

	public CssAppearance getAppearance() {
		if (cssAppearance == null) {
			cssAppearance =
					(CssAppearance) style.CascadingOrder(
							new CssAppearance(), style, selector);
		}
		return cssAppearance;
	}

	public CssIcon getIcon() {
		if (cssIcon == null) {
			cssIcon =
					(CssIcon) style.CascadingOrder(
							new CssIcon(), style, selector);
		}
		return cssIcon;
	}

	public CssNavIndex getNavIndexCSS3() {
		if (cssNavIndex == null) {
			cssNavIndex =
					(CssNavIndex) style.CascadingOrder(
							new CssNavIndex(), style, selector);
		}
		return cssNavIndex;
	}

	public CssNavUp getNavUpCSS3() {
		if (cssNavUp == null) {
			cssNavUp =
					(CssNavUp) style.CascadingOrder(
							new CssNavUp(), style, selector);
		}
		return cssNavUp;
	}

	public CssNavRight getNavRightCSS3() {
		if (cssNavRight == null) {
			cssNavRight =
					(CssNavRight) style.CascadingOrder(
							new CssNavRight(), style, selector);
		}
		return cssNavRight;
	}

	public CssNavDown getNavDownCSS3() {
		if (cssNavDown == null) {
			cssNavDown =
					(CssNavDown) style.CascadingOrder(
							new CssNavDown(), style, selector);
		}
		return cssNavDown;
	}

	public CssNavLeft getNavLeftCSS3() {
		if (cssNavLeft == null) {
			cssNavLeft =
					(CssNavLeft) style.CascadingOrder(
							new CssNavLeft(), style, selector);
		}
		return cssNavLeft;
	}

	public CssOutlineOffset getOutlineOffset() {
		if (cssOutlineOffset == null) {
			cssOutlineOffset =
					(CssOutlineOffset) style.CascadingOrder(
							new CssOutlineOffset(), style, selector);
		}
		return cssOutlineOffset;
	}

	public CssOverflowX getOverflowX() {
		if (cssOverflowX == null) {
			cssOverflowX =
					(CssOverflowX) style.CascadingOrder(
							new CssOverflowX(), style, selector);
		}
		return cssOverflowX;
	}

	public CssOverflowY getOverflowY() {
		if (cssOverflowY == null) {
			cssOverflowY =
					(CssOverflowY) style.CascadingOrder(
							new CssOverflowY(), style, selector);
		}
		return cssOverflowY;
	}

	public CssRubySpan getRubySpan() {
		if (cssRubySpan == null) {
			cssRubySpan =
					(CssRubySpan) style.CascadingOrder(
							new CssRubySpan(), style, selector);
		}
		return cssRubySpan;
	}

	public CssTextBlink getTextBlink() {
		if (cssTextBlink == null) {
			cssTextBlink =
					(CssTextBlink) style.CascadingOrder(
							new CssTextBlink(), style, selector);
		}
		return cssTextBlink;
	}

	public CssCrop getCrop() {
		if (cssCrop == null) {
			cssCrop =
					(CssCrop) style.CascadingOrder(
							new CssCrop(), style, selector);
		}
		return cssCrop;
	}

	public CssFitPosition getFitPosition() {
		if (cssFitPosition == null) {
			cssFitPosition =
					(CssFitPosition) style.CascadingOrder(
							new CssFitPosition(), style, selector);
		}
		return cssFitPosition;
	}

	public CssVisibilityCSS3 getVisibilityCSS3() {
		if (cssVisibilityCSS3 == null) {
			cssVisibilityCSS3 =
					(CssVisibilityCSS3) style.CascadingOrder(
							new CssVisibilityCSS3(), style, selector);
		}
		return cssVisibilityCSS3;
	}

	public CssOverflowCSS3 getOverflowCSS3() {
		if (cssOverflowCSS3 == null) {
			cssOverflowCSS3 =
					(CssOverflowCSS3) style.CascadingOrder(
							new CssOverflowCSS3(), style, selector);
		}
		return cssOverflowCSS3;
	}

	public CssMaxHeightCSS3 getMaxHeightCSS3() {
		if (cssMaxHeightCSS3 == null) {
			cssMaxHeightCSS3 =
					(CssMaxHeightCSS3) style.CascadingOrder(
							new CssMaxHeightCSS3(), style, selector);
		}
		return cssMaxHeightCSS3;
	}

	public CssMaxWidthCSS3 getMaxWidthCSS3() {
		if (cssMaxWidthCSS3 == null) {
			cssMaxWidthCSS3 =
					(CssMaxWidthCSS3) style.CascadingOrder(
							new CssMaxWidthCSS3(), style, selector);
		}
		return cssMaxWidthCSS3;
	}

	public CssMinHeightCSS3 getMinHeightCSS3() {
		if (cssMinHeightCSS3 == null) {
			cssMinHeightCSS3 =
					(CssMinHeightCSS3) style.CascadingOrder(
							new CssMinHeightCSS3(), style, selector);
		}
		return cssMinHeightCSS3;
	}

	public CssMinWidthCSS3 getMinWidthCSS3() {
		if (cssMinWidthCSS3 == null) {
			cssMinWidthCSS3 =
					(CssMinWidthCSS3) style.CascadingOrder(
							new CssMinWidthCSS3(), style, selector);
		}
		return cssMinWidthCSS3;
	}

	public CssPaddingCSS3 getPaddingCSS3() {
		if (cssPaddingCSS3 == null) {
			cssPaddingCSS3 =
					(CssPaddingCSS3) style.CascadingOrder(
							new CssPaddingCSS3(), style, selector);
		}
		return cssPaddingCSS3;
	}

	public CssPaddingTopCSS3 getPaddingTopCSS3() {
		if (cssPaddingTopCSS3 == null) {
			cssPaddingTopCSS3 =
					(CssPaddingTopCSS3) style.CascadingOrder(
							new CssPaddingTopCSS3(), style, selector);
		}
		return cssPaddingTopCSS3;
	}

	public CssPaddingBottomCSS3 getPaddingBottomCSS3() {
		if (cssPaddingBottomCSS3 == null) {
			cssPaddingBottomCSS3 =
					(CssPaddingBottomCSS3) style.CascadingOrder(
							new CssPaddingBottomCSS3(), style, selector);
		}
		return cssPaddingBottomCSS3;
	}

	public CssPaddingLeftCSS3 getPaddingLeftCSS3() {
		if (cssPaddingLeftCSS3 == null) {
			cssPaddingLeftCSS3 =
					(CssPaddingLeftCSS3) style.CascadingOrder(
							new CssPaddingLeftCSS3(), style, selector);
		}
		return cssPaddingLeftCSS3;
	}

	public CssPaddingRightCSS3 getPaddingRightCSS3() {
		if (cssPaddingRightCSS3 == null) {
			cssPaddingRightCSS3 =
					(CssPaddingRightCSS3) style.CascadingOrder(
							new CssPaddingRightCSS3(), style, selector);
		}
		return cssPaddingRightCSS3;
	}

	public CssMarquee getMarquee() {
		if (cssMarquee == null) {
			cssMarquee =
					(CssMarquee) style.CascadingOrder(
							new CssMarquee(), style, selector);
		}
		return cssMarquee;
	}

	public CssMarqueeDirection getMarqueeDirection() {
		if (cssMarqueeDirection == null) {
			cssMarqueeDirection =
					(CssMarqueeDirection) style.CascadingOrder(
							new CssMarqueeDirection(), style, selector);
		}
		return cssMarqueeDirection;
	}

	public CssMarqueeRepetition getMarqueeRepetition() {
		if (cssMarqueeRepetition == null) {
			cssMarqueeRepetition =
					(CssMarqueeRepetition) style.CascadingOrder(
							new CssMarqueeRepetition(), style, selector);
		}
		return cssMarqueeRepetition;
	}

	public CssMarqueeSpeed getMarqueeSpeed() {
		if (cssMarqueeSpeed == null) {
			cssMarqueeSpeed =
					(CssMarqueeSpeed) style.CascadingOrder(
							new CssMarqueeSpeed(), style, selector);
		}
		return cssMarqueeSpeed;
	}

	public CssMarqueeStyle getMarqueeStyle() {
		if (cssMarqueeStyle == null) {
			cssMarqueeStyle =
					(CssMarqueeStyle) style.CascadingOrder(
							new CssMarqueeStyle(), style, selector);
		}
		return cssMarqueeStyle;
	}

	public org.w3c.css.properties.css.CssBorderTopRightRadius getBorderTopRightRadius() {
		if (cssBorder.borderRadius.topRight == null) {
			cssBorder.borderRadius.topRight =
					(org.w3c.css.properties.css.CssBorderTopRightRadius) style.CascadingOrder(
							new CssBorderTopRightRadius(), style, selector);
		}
		return cssBorder.borderRadius.topRight;
	}

	public org.w3c.css.properties.css.CssBorderBottomRightRadius getBorderBottomRightRadius() {
		if (cssBorder.borderRadius.bottomRight == null) {
			cssBorder.borderRadius.bottomRight =
					(org.w3c.css.properties.css.CssBorderBottomRightRadius) style.CascadingOrder(
							new CssBorderBottomRightRadius(), style, selector);
		}
		return cssBorder.borderRadius.bottomRight;
	}

	public org.w3c.css.properties.css.CssBorderBottomLeftRadius getBorderBottomLeftRadius() {
		if (cssBorder.borderRadius.bottomLeft == null) {
			cssBorder.borderRadius.bottomLeft =
					(org.w3c.css.properties.css.CssBorderBottomLeftRadius) style.CascadingOrder(
							new org.w3c.css.properties.css.CssBorderBottomLeftRadius(), style, selector);
		}
		return cssBorder.borderRadius.bottomLeft;
	}

	public org.w3c.css.properties.css.CssBorderTopLeftRadius getBorderTopLeftRadius() {
		if (cssBorder.borderRadius.topLeft == null) {
			cssBorder.borderRadius.topLeft =
					(org.w3c.css.properties.css.CssBorderTopLeftRadius) style.CascadingOrder(
							new org.w3c.css.properties.css.CssBorderTopLeftRadius(), style, selector);
		}
		return cssBorder.borderRadius.topLeft;
	}

	public org.w3c.css.properties.css.CssBorderRadius getBorderRadius() {
		if (cssBorder.borderRadius == null) {
			cssBorder.borderRadius =
					(org.w3c.css.properties.css.CssBorderRadius) style.CascadingOrder(
							new org.w3c.css.properties.css.CssBorderRadius(), style, selector);
		}
		return cssBorder.borderRadius;
	}

	public CssBoxShadow getBoxShadow() {
		if (cssBoxShadow == null) {
			cssBoxShadow =
					(CssBoxShadow) style.CascadingOrder(
							new CssBoxShadow(), style, selector);
		}
		return cssBoxShadow;
	}

	public CssBoxDecorationBreak getBoxDecorationBreak() {
		if (cssBoxDecorationBreak == null) {
			cssBoxDecorationBreak =
					(CssBoxDecorationBreak) style.CascadingOrder(
							new CssBoxDecorationBreak(), style, selector);
		}
		return cssBoxDecorationBreak;
	}

	public CssFontKerning getFontKerning() {
		if (cssFontKerning == null) {
			cssFontKerning =
					(CssFontKerning) style.CascadingOrder(
							new CssFontKerning(), style, selector);
		}
		return cssFontKerning;
	}

	public CssFontLanguageOverride getFontLanguageOverride() {
		if (cssFontLanguageOverride == null) {
			cssFontLanguageOverride =
					(CssFontLanguageOverride) style.CascadingOrder(
							new CssFontLanguageOverride(), style, selector);
		}
		return cssFontLanguageOverride;
	}

	public CssFontVariantCaps getFontVariantCaps() {
		if (cssFontVariantCaps == null) {
			cssFontVariantCaps =
					(CssFontVariantCaps) style.CascadingOrder(
							new CssFontVariantCaps(), style, selector);
		}
		return cssFontVariantCaps;
	}

	public CssFontVariantPosition getFontVariantPosition() {
		if (cssFontVariantCaps == null) {
			cssFontVariantCaps =
					(CssFontVariantCaps) style.CascadingOrder(
							new CssFontVariantCaps(), style, selector);
		}
		return cssFontVariantPosition;
	}

	public CssFontSynthesis getFontSynthesis() {
		if (cssFontSynthesis == null) {
			cssFontSynthesis =
					(CssFontSynthesis) style.CascadingOrder(
							new CssFontSynthesis(), style, selector);
		}
		return cssFontSynthesis;
	}

	public CssFontVariantEastAsian getFontVariantEastAsian() {
		if (cssFontVariantEastAsian == null) {
			cssFontVariantEastAsian =
					(CssFontVariantEastAsian) style.CascadingOrder(
							new CssFontVariantEastAsian(), style, selector);
		}
		return cssFontVariantEastAsian;
	}

	public CssFontVariantLigatures getFontVariantLigatures() {
		if (cssFontVariantLigatures == null) {
			cssFontVariantLigatures =
					(CssFontVariantLigatures) style.CascadingOrder(
							new CssFontVariantLigatures(), style, selector);
		}
		return cssFontVariantLigatures;
	}

	public CssFontVariantNumeric getFontVariantNumeric() {
		if (cssFontVariantNumeric == null) {
			cssFontVariantNumeric =
					(CssFontVariantNumeric) style.CascadingOrder(
							new CssFontVariantNumeric(), style, selector);
		}
		return cssFontVariantNumeric;
	}

	public CssFontFeatureSettings getFontFeatureSettings() {
		if (cssFontFeatureSettings == null) {
			cssFontFeatureSettings =
					(CssFontFeatureSettings) style.CascadingOrder(
							new CssFontFeatureSettings(), style, selector);
		}
		return cssFontFeatureSettings;
	}

	public CssFontVariantAlternates getFontVariantAlternates() {
		if (cssFontVariantAlternates == null) {
			cssFontVariantAlternates =
					(CssFontVariantAlternates) style.CascadingOrder(
							new CssFontVariantAlternates(), style, selector);
		}
		return cssFontVariantAlternates;
	}

	public CssOverflowWrap getOverflowWrap() {
		if (cssOverflowWrap == null) {
			cssOverflowWrap =
					(CssOverflowWrap) style.CascadingOrder(
							new CssOverflowWrap(), style, selector);
		}
		return cssOverflowWrap;
	}

	public CssHyphens getHyphens() {
		if (cssHyphens == null) {
			cssHyphens =
					(CssHyphens) style.CascadingOrder(
							new CssHyphens(), style, selector);
		}
		return cssHyphens;
	}

	public CssTextDecorationStyle getTextDecorationStyle() {
		if (cssTextDecorationStyle == null) {
			cssTextDecorationStyle =
					(CssTextDecorationStyle) style.CascadingOrder(
							new CssTextDecorationStyle(), style, selector);
		}
		return cssTextDecorationStyle;
	}

	public CssTextDecorationColor getTextDecorationColor() {
		if (cssTextDecorationColor == null) {
			cssTextDecorationColor =
					(CssTextDecorationColor) style.CascadingOrder(
							new CssTextDecorationColor(), style, selector);
		}
		return cssTextDecorationColor;
	}

	public CssTextDecorationLine getTextDecorationLine() {
		if (cssTextDecorationLine == null) {
			cssTextDecorationLine =
					(CssTextDecorationLine) style.CascadingOrder(
							new CssTextDecorationLine(), style, selector);
		}
		return cssTextDecorationLine;
	}

	public CssTextDecorationSkip getTextDecorationSkip() {
		if (cssTextDecorationSkip == null) {
			cssTextDecorationSkip =
					(CssTextDecorationSkip) style.CascadingOrder(
							new CssTextDecorationSkip(), style, selector);
		}
		return cssTextDecorationSkip;
	}

	public CssTextEmphasis getTextEmphasis() {
		if (cssTextEmphasis == null) {
			cssTextEmphasis =
					(CssTextEmphasis) style.CascadingOrder(
							new CssTextEmphasis(), style, selector);
		}
		return cssTextEmphasis;
	}

	public CssTextEmphasisColor getTextEmphasisColor() {
		if (cssTextEmphasisColor == null) {
			cssTextEmphasisColor =
					(CssTextEmphasisColor) style.CascadingOrder(
							new CssTextEmphasisColor(), style, selector);
		}
		return cssTextEmphasisColor;
	}

	public CssTextEmphasisPosition getTextEmphasisPosition() {
		if (cssTextEmphasisPosition == null) {
			cssTextEmphasisPosition =
					(CssTextEmphasisPosition) style.CascadingOrder(
							new CssTextEmphasisPosition(), style, selector);
		}
		return cssTextEmphasisPosition;
	}
	
	public CssTextEmphasisStyle getTextEmphasisStyle() {
		if (cssTextEmphasisStyle == null) {
			cssTextEmphasisStyle =
					(CssTextEmphasisStyle) style.CascadingOrder(
							new CssTextEmphasisStyle(), style, selector);
		}
		return cssTextEmphasisStyle;
	}

	public CssTextUnderlinePosition getTextUnderlinePosition() {
		if (cssTextUnderlinePosition == null) {
			cssTextUnderlinePosition =
					(CssTextUnderlinePosition) style.CascadingOrder(
							new CssTextUnderlinePosition(), style, selector);
		}
		return cssTextUnderlinePosition;
	}

	public CssTabSize getTabSize() {
		if (cssTabSize == null) {
			cssTabSize =
					(CssTabSize) style.CascadingOrder(
							new CssTabSize(), style, selector);
		}
		return cssTabSize;
	}
	///

	/**
	 * Returns the name of the actual selector
	 */
	public String getSelector() {
		return (selector.getElement().toLowerCase());
	}

	/*    public boolean isRubyText() {
		  return(((selector.getElement()).toLowerCase() == "ruby") ||
		  ((selector.getElement()).toLowerCase() == "rb") ||
		  ((selector.getElement()).toLowerCase() == "rt") ||
		  ((selector.getElement()).toLowerCase() == "rbc") ||
		  ((selector.getElement()).toLowerCase() == "rtc"));
		  }

		  public void findConflicts(ApplContext ac) {
		  if ((cssRubyPosition != null)
		  && (selector != null)
		  && (!isRubyText())) {
		  warnings.addWarning(new Warning(cssRubyPosition,
		  "ruby-text", 1, ac));
		  }

		  if ((cssRubyOverhang != null)
		  && (selector != null)
		  && (!isRubyText())) {
		  warnings.addWarning(new Warning(cssRubyOverhang,
		  "ruby-text", 1, ac));
		  }
		  }
		*/

	/**
	 * Find conflicts in this Style
	 * For the float elements
	 *
	 * @param warnings     For warnings reports.
	 * @param allSelectors All contexts is the entire style sheet.
	 */
	private void findConflictsBlockElements(ApplContext ac, Warnings warnings,
											CssSelectors selector,
											CssSelectors[] allSelectors) {
		if (Util.fromHTMLFile) {
			if ((selector != null) &&
					(!selector.isBlockLevelElement())) {
				if (cssColumnCount != null) {
					warnings.addWarning(new Warning(cssColumnCount,
							"block-level", 1, ac));
				}
				if (cssColumnGap != null) {
					warnings.addWarning(new Warning(cssColumnGap,
							"block-level", 1, ac));
				}
				if (cssColumnSpan != null) {
					warnings.addWarning(new Warning(cssColumnSpan,
							"block-level", 1, ac));
				}
				if (cssColumnWidth != null) {
					warnings.addWarning(new Warning(cssColumnWidth,
							"block-level", 1, ac));
				}
			}
		}
	}

	/**
	 * Find conflicts in this Style
	 *
	 * @param warnings     For warnings reports.
	 * @param allSelectors All contexts is the entire style sheet.
	 */
	public void findConflicts(ApplContext ac, Warnings warnings,
							  CssSelectors selector, CssSelectors[] allSelectors) {
		findConflictsBlockElements(ac, warnings, selector, allSelectors);
		super.findConflicts(ac, warnings, selector, allSelectors);
	}
}
